/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxContentDao extends JpaRepository<WxContent, Long>,
		JpaSpecificationExecutor<WxContent> {

	@Query("from WxContent  where messageId = :messageId")
	List<WxContent> deleteByMessageId(@Param("messageId") Long messageId);

	@Query("from WxContent  where  messageId = null and baseContentId is null and msgType = :msgType")
	List<WxContent> listAllMultimediaContent(@Param("msgType") String msgType);

	@Query("from WxContent  where  messageId is null and baseContentId is null")
	List<WxContent> listAllMultimediaContent();

	@Query("from WxContent  where messageId = :messageId")
	List<WxContent> findContentByMessageId(@Param("messageId") Long messageId);

}
