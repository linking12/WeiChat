/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxMessageDao extends JpaRepository<WxMessage, Long>, JpaSpecificationExecutor<WxMessage> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
