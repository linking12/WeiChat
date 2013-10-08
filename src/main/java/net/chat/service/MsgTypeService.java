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
public interface MsgTypeService  {
	
	List<WxMsgType> findMsgTypeByAccountId(Long accountId);
	
	void save(List<WxMsgType> msgTypes);
	
	WxMsgType save(WxMsgType t);
	
	WxMsgType edit(WxMsgType t) ;
	
	void delete(WxMsgType t);
	
	WxMsgType findOne(WxMsgType t);
	
	List<WxMsgType> finaAll();
}
