/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bo
 * 
 */
public interface WxAccountDao extends JpaRepository<WxAccount, Long>, JpaSpecificationExecutor<WxAccount> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);

}
