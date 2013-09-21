/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxGameDao extends JpaRepository<WxGame, Long>, JpaSpecificationExecutor<WxGame> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
