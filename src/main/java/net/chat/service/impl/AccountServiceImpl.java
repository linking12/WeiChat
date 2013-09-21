package net.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.chat.dao.WxAccountDao;
import net.chat.dao.WxAccountGameDao;
import net.chat.dao.WxGameDao;
import net.chat.dao.WxMessageDao;
import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxAccount;
import net.chat.domain.WxAccountGame;
import net.chat.domain.WxGame;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.service.AccountService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountSerivce")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private WxAccountDao accountDao;

	@Autowired
	private WxMessageDao messageDao;

	@Autowired
	private WxGameDao gameDao;

	@Autowired
	private WxAccountGameDao accountGameDao;

	@Autowired
	private WxMsgTypeDao messageTypeDao;

	public void saveAccount(WxAccount account) {
		if (account.getId() == null) {
			accountDao.save(account);
			Long accountId = account.getId();
			WxMessage message = new WxMessage();
			message.setAccountId(accountId);
			message.setMsgType("text");
			message.setMsgName("欢迎词");
			message.setContent("谢谢关注此账号！");
			// 获取聊天机器人
			WxGame defaultGame = gameDao.findByUrlAndGameType("autoreply.jsp",
					"program");
			WxAccountGame accountGame = new WxAccountGame();
			accountGame.setGameId(defaultGame.getId());
			accountGame.setAccountId(accountId);
			accountGameDao.save(accountGame);
			List<WxMsgType> messageTypeList = new ArrayList<WxMsgType>(10);
			messageTypeList.add(new WxMsgType(accountId, "text", "program",
					message.getId(), "文本"));
			messageTypeList.add(new WxMsgType(accountId, "image", "direct",
					message.getId(), "图片"));
			messageTypeList.add(new WxMsgType(accountId, "voice", "direct",
					message.getId(), "声音"));
			messageTypeList.add(new WxMsgType(accountId, "subscribe", "direct",
					message.getId(), "关注"));
			messageTypeList.add(new WxMsgType(accountId, "video", "direct",
					message.getId(), "视频"));
			messageTypeList.add(new WxMsgType(accountId, "unsubscribe",
					"direct", message.getId(), "取消关注"));
			messageTypeDao.save(messageTypeList);
		}

	}

	public void editAccount(WxAccount account) {
		if (account.getId() != null) {
			WxAccount accountEntity = accountDao.findOne(account.getId());
			BeanUtils.copyProperties(account, accountEntity);
		} else
			this.saveAccount(account);
	}

	public void deleteAccount(Long accountId) {
		accountDao.delete(accountId);
	}

	public void ListAllAcount(int pageNo, int pageSize) {
		if (pageSize == 0)
			pageSize = 20;
		if (pageNo == 0)
			pageNo = 0;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(
				new Order("Order")));
		accountDao.findAll(pageable);

	}

	public WxAccount findAcountById(Long accountId) {
		return accountDao.findOne(accountId);
	}

	public void configAccount(ConfigPropertity configPertity) {

		WxMsgType messageType = messageTypeDao.findOne(configPertity
				.getMessageTypeId());
		if (messageType.getAccountId().equals(configPertity.getAccountId())) {
			messageType.setAction(configPertity.getAction());
			messageType.setSourceId(configPertity.getSourceId());
		}

	}

	public class ConfigPropertity {
		private String action;

		private Long sourceId;

		private Long messageTypeId;

		private Long accountId;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public Long getSourceId() {
			return sourceId;
		}

		public void setSourceId(Long sourceId) {
			this.sourceId = sourceId;
		}

		public Long getMessageTypeId() {
			return messageTypeId;
		}

		public void setMessageTypeId(Long messageTypeId) {
			this.messageTypeId = messageTypeId;
		}

		public Long getAccountId() {
			return accountId;
		}

		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}

	}

}
