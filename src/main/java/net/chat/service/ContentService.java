/**
 * 
 */
package net.chat.service;

import java.util.List;

import org.springframework.data.domain.Page;

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

	List<WxContent> findAllMultimedia(String msgType);

	List<WxContent> findAllBaseMultimedia(String msgType);
	
	Page<WxContent> findAllBaseMultimedia(String msgType,int pageNo);
}
