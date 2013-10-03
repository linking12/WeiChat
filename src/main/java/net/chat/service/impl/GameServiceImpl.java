/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

import net.chat.dao.WxGameDao;
import net.chat.domain.WxGame;
import net.chat.service.GameService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * 
 */
@Service("gameSerivce")
public class GameServiceImpl implements GameService {

	@Autowired
	private WxGameDao gameDao;

	
	public WxGame save(WxGame game) {
		return gameDao.save(game);
	}

	
	public WxGame edit(WxGame t) {
		WxGame gameEntity = gameDao.findOne(t.getId());
		BeanUtils.copyProperties(t, gameEntity);
		return t;
	}

	
	public void delete(WxGame t) {
		gameDao.delete(t);
	}

	public WxGame findOne(WxGame t) {
		return gameDao.findOne(t.getId());
	}

	public List<WxGame> finaAll() {
		return gameDao.findAll();
	}

	@Override
	public List<WxGame> findGameByAccountId(Long accountId) {
		
		List<WxGame> games= gameDao.findGameByAccountId(accountId);
		
		return games;
	}

}
