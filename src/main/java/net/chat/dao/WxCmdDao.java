/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxCmd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxCmdDao extends JpaRepository<WxCmd, Long>, JpaSpecificationExecutor<WxCmd> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
