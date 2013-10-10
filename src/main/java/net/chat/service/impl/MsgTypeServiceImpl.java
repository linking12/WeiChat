package net.chat.service.impl;

import java.util.List;

import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxMsgType;
import net.chat.service.MsgTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("msgTypeSerivce")
public class MsgTypeServiceImpl implements MsgTypeService {
	@Autowired
	private WxMsgTypeDao msgTypeDao;

	public List<WxMsgType> findMsgTypeByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return msgTypeDao.findMsgTypeByAccountId(accountId);
	}

	@Transactional
	public void save(List<WxMsgType> msgTypes) {
		msgTypeDao.save(msgTypes);
	}

	public WxMsgType save(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.save(t);
	}

	public WxMsgType edit(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.save(t);
	}

	public void delete(Long id) {
		msgTypeDao.delete(id);

	}

	public WxMsgType findOne(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.findOne(t.getId());
	}

	public List<WxMsgType> finaAll() {
		// TODO Auto-generated method stub
		return msgTypeDao.findAll();
	}

}
