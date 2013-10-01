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

	public WxGame save(WxGame t);

	public WxGame edit(WxGame t);

	public void delete(WxGame t);

	public WxGame findOne(WxGame t);

	public List<WxGame> finaAll();
}
