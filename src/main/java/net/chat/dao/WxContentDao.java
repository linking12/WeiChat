/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxContent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query("from WxContent  where  messageId = null and baseContentId is null and msgType = :msgType  and userId =:userId")
	List<WxContent> listAllMultimediaContent(@Param("msgType") String msgType,@Param("userId")Long userId);

	@Query("from WxContent  where  messageId is null and baseContentId is null and userId =:userId")
	List<WxContent> listAllMultimediaContent(@Param("userId")Long userId);

	@Query("from WxContent  where messageId = :messageId")
	List<WxContent> findContentByMessageId(@Param("messageId") Long messageId);
	
	@Query("select s from WxContent s where  messageId = null and baseContentId is null and msgType = :msgType and userId =:userId")
	Page<WxContent> findAllBaseMultimedia(@Param("msgType") String msgType,@Param("userId")Long userId,Pageable pageable);
	
	@Query("select s from WxContent s where  messageId = null and baseContentId is null and userId =:userId")
	Page<WxContent> findAllBaseMultimedia(@Param("userId")Long userId,Pageable pageable);
}
