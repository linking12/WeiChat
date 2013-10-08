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

	@Override
	public List<WxMsgType> findMsgTypeByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return msgTypeDao.findMsgTypeByAccountId(accountId);
	}

	@Override
	@Transactional
	public void save(List<WxMsgType> msgTypes) {
		msgTypeDao.save(msgTypes);
	}

	@Override
	public WxMsgType save(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.save(t);
	}

	@Override
	public WxMsgType edit(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.save(t);
	}

	@Override
	public void delete(WxMsgType t) {
		msgTypeDao.delete(t);

	}

	@Override
	public WxMsgType findOne(WxMsgType t) {
		// TODO Auto-generated method stub
		return msgTypeDao.findOne(t.getId());
	}



	@Override
	public List<WxMsgType> finaAll() {
		// TODO Auto-generated method stub
		return msgTypeDao.findAll();
	}

}
