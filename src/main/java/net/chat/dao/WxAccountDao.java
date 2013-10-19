/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxAccountDao extends JpaRepository<WxAccount, Long>,
		JpaSpecificationExecutor<WxAccount> {

	@Query("from WxAccount  where customerId = :customerId")
	List<WxAccount> findAccountByUserId(@Param("customerId") Long customerId);

	@Modifying
	@Query("update WxAccount set name= :name , note = :note where id = :id")
	void editAccount(@Param("id") Long id, @Param("name") String name,
			@Param("note") String note);

	@Query("from WxAccount  where customerId = :userId")
	Page<WxAccount> findAccountByUserId(@Param("userId") Long userId,Pageable pageable);
}
