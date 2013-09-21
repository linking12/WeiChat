/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxAccountGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxAccountGameDao extends JpaRepository<WxAccountGame, Long>, JpaSpecificationExecutor<WxAccountGame> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
