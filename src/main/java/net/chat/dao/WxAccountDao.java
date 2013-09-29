/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxAccountDao extends JpaRepository<WxAccount, Long>, JpaSpecificationExecutor<WxAccount> {

	// @Query("from Draw c where c.drawName=:drawName")
	// Draw findByName(@Param("drawName") String drawName);
	@Modifying
	@Query("update WxAccount set name= :name , note = :note where id = :id")
	void updateAccount(@Param("id") Long id, @Param("name") String name, @Param("note") String note);

}
