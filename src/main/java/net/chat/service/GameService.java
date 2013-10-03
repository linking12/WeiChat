/**
 * 
 */
package net.chat.service;

import java.util.List;

import net.chat.domain.WxGame;

/**
 * @author Administrator
 * 
 */
public interface GameService {

	WxGame save(WxGame t);

	WxGame edit(WxGame t);

	void delete(WxGame t);

	WxGame findOne(WxGame t);

	List<WxGame> finaAll();
	
	List<WxGame> findGameByAccountId(Long accountId);

}
