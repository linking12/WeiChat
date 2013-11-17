package net.chat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import net.chat.dao.WxAccountDao;
import net.chat.dao.WxCmdDao;
import net.chat.dao.WxContentDao;
import net.chat.dao.WxGameDao;
import net.chat.dao.WxLbsDao;
import net.chat.dao.WxMessageDao;
import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxAccount;
import net.chat.domain.WxCmd;
import net.chat.domain.WxContent;
import net.chat.domain.WxGame;
import net.chat.domain.WxLbs;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.integration.vo.CacheContant;
import net.chat.integration.vo.WeChatRespTextBean;
import net.chat.integration.vo.WeiChatRespImageBean;
import net.chat.integration.vo.WeiChatRespImageBean.Article;
import net.chat.integration.vo.WeiChatRespMusicAndVideoBean;
import net.chat.integration.vo.WeiChatRespMusicAndVideoBean.Music;

import org.apache.commons.lang3.StringUtils;

public class InitThreadService extends Thread {

	private static Logger log = Logger.getLogger(InitThreadService.class
			.getName());

	private boolean openThreadFlag = true;

	private WxAccountDao accountDao;

	private WxMsgTypeDao messageTypeDao;

	private WxCmdDao wxCmdDao;

	private WxGameDao gameDao;

	private WxMessageDao messageDao;

	private WxContentDao contentDao;

	private WxLbsDao lbsDao;

	public InitThreadService(WxAccountDao accountDao,
			WxMsgTypeDao messageTypeDao, WxCmdDao wxCmdDao, WxGameDao gameDao,
			WxMessageDao messageDao, WxContentDao contentDao, WxLbsDao lbsDao) {
		this.accountDao = accountDao;
		this.messageTypeDao = messageTypeDao;
		this.wxCmdDao = wxCmdDao;
		this.gameDao = gameDao;
		this.messageDao = messageDao;
		this.contentDao = contentDao;
		this.lbsDao = lbsDao;

	}

	@Override
	public void run() {

		while (openThreadFlag) {
			log.info("====缓存微信回复信息配置开始====");
			CacheContant.publicAccountCache.cleanup();
			CacheContant.sourceCache.cleanup();
			CacheContant.accountCache.cleanup();
			CacheContant.gameCache.cleanup();
			CacheContant.urlseqsCache.cleanup();
			CacheContant.autoReplayAndCmdCache.cleanup();
			initConfig();
			log.info("====缓存微信回复信息配置结束====");
			try {
				sleep(5 * 60 * 1000);
			} catch (InterruptedException e) {
				log.info("Sleep Thread failed,The Exception is " + e);
			}
		}
	}

	private void initConfig() {
		List<WxAccount> accounts = accountDao.findAll();
		// account
		for (WxAccount account : accounts) {
			Long accountId = account.getId();
			WxLbs lbs = lbsDao.findByAccountId(accountId);
			CacheContant.publicAccountCache.put(account.getUrl(),
					lbs.getyPoint() + "," + lbs.getxPoint());
			List<WxMsgType> messageTypes = messageTypeDao
					.findMsgTypeByAccountId(accountId);
			for (WxMsgType msgType : messageTypes) {
				String key = StringUtils.trim(account.getUrl())
						+ StringUtils.trim(msgType.getMsgType());
				if (msgType.getAction().equals("program")) {
					CacheContant.accountCache.put(key, msgType.getSourceId()
							+ "_program");
				} else {
					CacheContant.accountCache.put(key, msgType.getSourceId());
				}
			}
			CacheContant.urlseqsCache.put(account.getUrl(), account.getSeq());

		}
		// cmd
		for (WxAccount account : accounts) {
			Long accountId = account.getId();
			List<WxCmd> cmds = wxCmdDao.findCmdByAccountId(accountId);
			CacheContant.autoReplayAndCmdCache.put(
					StringUtils.trim(account.getUrl()), cmds);
		}
		// game
		for (WxAccount account : accounts) {
			Long accountId = account.getId();
			List<WxGame> accountGames = gameDao.findGameByAccountId(accountId);
			for (WxGame game : accountGames) {
				String key = StringUtils.trim(account.getUrl());
				CacheContant.gameCache.put(key, game.getUrl());
			}
		}
		// message
		for (WxAccount account : accounts) {
			Long accountId = account.getId();
			List<WxMessage> messages = messageDao
					.findAllMessageByAccountId(accountId);
			for (WxMessage message : messages) {
				if (message.getMsgType().equals("text")) {
					WeChatRespTextBean respBean = new WeChatRespTextBean();
					respBean.setMsgType("text");
					respBean.setCreateTime(new Date().getTime());
					respBean.setContent(message.getContent());
					CacheContant.sourceCache.put(message.getId().toString(),
							respBean);
				} else if (message.getMsgType().equals("image")) {
					WeiChatRespImageBean respBean = new WeiChatRespImageBean();
					buildImageResp(respBean, message.getId());
					CacheContant.sourceCache.put(message.getId().toString(),
							respBean);

				} else if (message.getMsgType().equals("voice")
						|| message.getMsgType().equals("video")) {
					WeiChatRespMusicAndVideoBean respBean = new WeiChatRespMusicAndVideoBean();
					buildMusicResp(respBean, message.getId());
					CacheContant.sourceCache.put(message.getId().toString(),
							respBean);
				}

			}

		}

	}

	public void buildImageResp(WeiChatRespImageBean respImageBean,
			Long messageId) {
		List<WxContent> contents = contentDao.findContentByMessageId(messageId);
		respImageBean.setMsgType("news");
		respImageBean.setCreateTime(new Date().getTime());
		respImageBean.setArticleCount(contents.size());
		List<Article> articles = new ArrayList<Article>();
		for (WxContent content : contents) {
			WeiChatRespImageBean.Article article = new Article();
			article.setTitle(content.getTitle());
			article.setDescription(content.getDescription());
			article.setPicUrl(content.getPicUrl());
			article.setUrl(content.getUrl());
			articles.add(article);
		}
		respImageBean.setArticles(articles);

	}

	public void buildMusicResp(WeiChatRespMusicAndVideoBean respMusicBean,
			Long messageId) {
		List<WxContent> contents = contentDao.findContentByMessageId(messageId);
		respMusicBean.setMsgType("music");
		respMusicBean.setCreateTime(new Date().getTime());
		if (contents.size() > 1) {
			log.info("many music url has set");
			// return;
		}
		WeiChatRespMusicAndVideoBean.Music music = new Music();
		music.setTitle(contents.get(0).getTitle());
		music.setDescription(contents.get(0).getDescription());
		music.setMusicUrl(contents.get(0).getMusicUrl());
		music.sethQMusicUrl(contents.get(0).getHqmusicUrl());

		respMusicBean.setMusics(music);

	}

	@SuppressWarnings("deprecation")
	public void destroy() {
		openThreadFlag = false;
		super.destroy();
		this.interrupt();
	}
}
