/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxMessageDao extends JpaRepository<WxMessage, Long>, JpaSpecificationExecutor<WxMessage> {

	@Modifying
	@Query("delete WxMessage  where accountId = :accountId")
	void deleteByAccountId(@Param("accountId") Long accountId);

	@Modifying
	@Query("update WxMessage  set msgName=:msgName , content=:content where id = :id")
	void editWxMessage(@Param("id") Long id, @Param("msgName") String msgName, @Param("content") String content);

}
