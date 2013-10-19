package net.chat.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxAccountDao;
import net.chat.dao.WxMessageDao;
import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxAccount;
import net.chat.domain.WxMsgType;
import net.chat.service.AccountService;
import net.chat.utils.AppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountSerivce")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private WxAccountDao accountDao;

	@Autowired
	private WxMessageDao messageDao;

	@Autowired
	private WxMsgTypeDao messageTypeDao;

	@Transactional
	public void saveAccount(WxAccount account) {
		if (account.getId() == null) {
			Long userId = AppContext.getUserId();
			account.setCustomerId(userId);
			accountDao.save(account);
		}

	}

	@Transactional
	public void editAccount(WxAccount account) {
		accountDao.editAccount(account.getId(), account.getName(), account.getNote());
	}

	@Transactional
	public void deleteAccount(Long accountId) {
		messageTypeDao.deleteByAccountId(accountId);
		messageDao.deleteByAccountId(accountId);
		accountDao.delete(accountId);
	}

	public List<WxAccount> findAccountByUserId(Long userId) {
		return accountDao.findAccountByUserId(userId);
	}

	public Page<WxAccount> listAllAcount(int pageNo, int pageSize) {
		if (pageSize == 0)
			pageSize = 20;
		if (pageNo == 0)
			pageNo = 1;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(new Order("id")));
		return accountDao.findAll(pageable);

	}

	public WxAccount findAcountById(Long accountId) {
		return accountDao.findOne(accountId);
	}

	public void configAccount(ConfigPropertity configPertity) {

		WxMsgType messageType = messageTypeDao.findOne(configPertity.getMessageTypeId());
		if (messageType.getAccountId().equals(configPertity.getAccountId())) {
			messageType.setAction(configPertity.getAction());
			messageType.setSourceId(configPertity.getSourceId());
		}

	}

	public Page<WxMsgType> queryAllMessageTypeInAccount(Long accountId, int pageNo, int pageSize) {
		if (pageSize == 0)
			pageSize = 20;
		if (pageNo == 0)
			pageNo = 0;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(new Order("id")));
		final Long _accountId = accountId;
		Specification<WxMsgType> spec = new Specification<WxMsgType>() {
			public Predicate toPredicate(Root<WxMsgType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get("accountId"), _accountId);
			}

		};
		return messageTypeDao.findAll(spec, pageable);
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

	public List<WxAccount> findAll() {

		return accountDao.findAll();
	}

	
	public Page<WxAccount> findAccountByUserId(int pageNo, final Long userId) {
		int pageSize = 5;
		if (pageNo == 0)
			pageNo = 1;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(new Order("id")));

		Specification<WxAccount> spec = new Specification<WxAccount>() {
			public Predicate toPredicate(Root<WxAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get("customerId"), userId);
			}
		};
		return accountDao.findAll(spec, pageable);

	}

}
