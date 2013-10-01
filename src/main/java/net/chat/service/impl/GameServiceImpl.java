/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public WxGame save(WxGame game) {
		return gameDao.save(game);
	}

	@Transactional
	public WxGame edit(WxGame t) {
		WxGame gameEntity = gameDao.findOne(t.getId());
		BeanUtils.copyProperties(t, gameEntity);
		return t;
	}

	@Transactional
	public void delete(WxGame t) {
		gameDao.delete(t);
	}

	public WxGame findOne(WxGame t) {
		return gameDao.findOne(t.getId());
	}

	public List<WxGame> finaAll() {
		return gameDao.findAll();
	}

}
