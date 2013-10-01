/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chat.dao.WxGameDao;
import net.chat.domain.WxGame;
import net.chat.service.GameService;

/**
 * @author Administrator
 * 
 */
@Service("gameSerivce")
public class GameServiceImpl implements GameService {

	@Autowired
	private WxGameDao gameDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public WxGame save(WxGame t) {
		// TODO Auto-generated method stub
		return gameDao.save(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.BaseService#edit(java.lang.Object)
	 */
	@Override
	public WxGame edit(WxGame t) {
		// TODO Auto-generated method stub
		return gameDao.save(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public void delete(WxGame t) {
		gameDao.delete(t);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.BaseService#findOne(java.lang.Object)
	 */
	@Override
	public WxGame findOne(WxGame t) {
		// TODO Auto-generated method stub
		return gameDao.findOne(t.getId());
	}

	@Override
	public List<WxGame> finaAll() {
		// TODO Auto-generated method stub
		return gameDao.findAll();
	}

	

}
