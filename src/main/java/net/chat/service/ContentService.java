/**
 * 
 */
package net.chat.service;

import net.chat.domain.WxContent;

/**
 * @author Administrator
 * 
 */
public interface ContentService {

	void deleteByMessageId(Long messageId);

	void delete(Long id);

	WxContent save(WxContent wxContent);

	WxContent findOne(Long id);

	WxContent findByMessageId(Long messageId);
}
