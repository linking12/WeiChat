/**
 * 
 */
package net.chat.service;

import java.util.List;

import net.chat.domain.WxCmd;

import org.springframework.data.domain.Page;

/**
 * @author Administrator
 * 
 */
public interface CmdService {

	WxCmd save(WxCmd cmd);

	void delete(Long cmdId);

	Page<WxCmd> findCmdByAccountId(Long accountId, String condition, int pageNo);

	List<WxCmd> findCmdByAccountId(Long accountId, String condition);
	
	WxCmd findOne(Long id);
}
