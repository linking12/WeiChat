/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxUserRegist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxUserRegistDao extends JpaRepository<WxUserRegist, Long>, JpaSpecificationExecutor<WxUserRegist> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
