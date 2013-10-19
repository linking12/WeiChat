package net.chat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxAccountGameDao;
import net.chat.dao.WxContentDao;
import net.chat.dao.WxGameDao;
import net.chat.dao.WxMessageDao;
import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxAccountGame;
import net.chat.domain.WxGame;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("messageSerivce")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private WxMessageDao messageDao;

	@Autowired
	private WxContentDao contentDao;

	@Autowired
	private WxGameDao gameDao;

	@Autowired
	private WxAccountGameDao accountGameDao;

	@Autowired
	private WxMsgTypeDao messageTypeDao;

	public Page<WxMessage> listALlMessageByAccountId(Long accountId,
			int pageNo, int pageSize) {
		pageSize = 5;
		if (pageNo == 0)
			pageNo = 1;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(
				new Order("id")));
		Page<WxMessage> pageMessages = null;
		if (accountId == null)
			pageMessages = messageDao.findAll(pageable);
		else {
			final Long _accountId = accountId;
			Specification<WxMessage> spec = new Specification<WxMessage>() {
				public Predicate toPredicate(Root<WxMessage> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<Long> get("accountId"), _accountId);
				}

			};
			pageMessages = messageDao.findAll(spec, pageable);
		}
		return pageMessages;

	}

	public void saveMessage(WxMessage message) {
		if (message.getId() == null) {
			message.setCreateTime(new Date());
			messageDao.save(message);
		}

		Long accountId = message.getAccountId();
		List<WxAccountGame> accountGameList=accountGameDao.findByAccountId(accountId);
		if(CollectionUtils.isEmpty(accountGameList)){
		// 获取聊天机器人
		WxGame defaultGame = gameDao.findByUrlAndGameType("autoreply",
				"program");
		WxAccountGame accountGame = new WxAccountGame();
		accountGame.setGameId(defaultGame.getId());
		accountGame.setAccountId(accountId);
		accountGameDao.save(accountGame);
		}
		WxMsgType mmsgType = null;
		if (message.getMsgType().equals("text")) {
			mmsgType = new WxMsgType(accountId, "text", "program",
					message.getId(), "文本");
		} else if (message.getMsgType().equals("image")) {
			mmsgType = new WxMsgType(accountId, "image", "direct",
					message.getId(), "图文");
		} else if (message.getMsgType().equals("voice")) {
			mmsgType = new WxMsgType(accountId, "voice", "direct",
					message.getId(), "声音");
		} else if (message.getMsgType().equals("video")) {
			mmsgType = new WxMsgType(accountId, "video", "direct",
					message.getId(), "视频");
		}
		messageTypeDao.save(mmsgType);
	}
	@Transactional
	public void editMessage(WxMessage message) {
		messageDao.editWxMessage(message.getId(), message.getMsgName(), message.getContent());
	}

	@Transactional
	public void delteMessage(Long messageId) {
		messageDao.delete(messageId);
		contentDao.deleteByMessageId(messageId);
	}

	public WxMessage findyMessageByMessageId(Long messageId) {
		return messageDao.findOne(messageId);
	}

	public List<WxMessage> findMessageByAccountId(Long accountId) {
		List<WxMessage>  messages=new ArrayList<WxMessage>();
		List<WxMessage> textMessage=messageDao.findTextMessageByAccountId(accountId);
		List<WxMessage> multMessage=messageDao.findMultMessageByAccountId(accountId);
		messages.addAll(textMessage);
		messages.addAll(multMessage);
		return messages;
	}

	public List<WxMessage> findTextMessageByAccountId(Long accountId) {

		return messageDao.findTextMessageByAccountId(accountId);
	}

}
