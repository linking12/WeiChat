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
public interface MsgTypeService  extends BaseService<WxMsgType>{
	
	List<WxMsgType> findMsgTypeByAccountId(Long accountId);
	
	void save(List<WxMsgType> msgTypes);
}
