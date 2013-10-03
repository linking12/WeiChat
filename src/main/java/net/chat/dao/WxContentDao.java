/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxContentDao extends JpaRepository<WxContent, Long>, JpaSpecificationExecutor<WxContent> {

	@Modifying
	@Query("delete WxContent  where messageId = :messageId")
	void deleteByMessageId(@Param("messageId") Long messageId);
	

}
