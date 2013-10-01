/**
 * 
 */
package net.chat.service;

import java.util.List;

import net.chat.domain.WxMsgType;

/**
 * @author Administrator
 * 
 */
public interface MsgTypeService {

	public List<WxMsgType> findMsgTypeByAccountId(Long accountId);

	public void save(List<WxMsgType> msgTypes);

	public WxMsgType save(WxMsgType t);

	public WxMsgType edit(WxMsgType t);

	public void delete(WxMsgType t);

	public WxMsgType findOne(WxMsgType t);

	public List<WxMsgType> finaAll();
}
