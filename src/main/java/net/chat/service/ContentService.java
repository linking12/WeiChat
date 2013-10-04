/**
 * 
 */
package net.chat.service;

import java.util.List;

import net.chat.domain.WxContent;

/**
 * @author Administrator
 * 
 */
public interface ContentService {

	void deleteByMessageId(Long messageId);

	void delete(Long id);

	void delete(List<Long> ids);

	WxContent save(WxContent wxContent);

	void saveContents(Iterable<WxContent> wxContent);

	WxContent findOne(Long id);

	Iterable<WxContent> findByContentIds(List<Long> contentIds);

	List<WxContent> findByMessageId(final Long messageId);

	List<WxContent> findAllMultimedia();

}
