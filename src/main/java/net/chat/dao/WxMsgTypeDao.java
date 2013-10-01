/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxMsgType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxMsgTypeDao extends JpaRepository<WxMsgType, Long>, JpaSpecificationExecutor<WxMsgType> {

	@Modifying
	@Query("delete WxMsgType  where accountId = :accountId")
	void deleteByAccountId(@Param("accountId") Long accountId );
	
	
	@Query("from WxMsgType  where accountId = :accountId")
	List<WxMsgType> findMsgTypeByAccountId(@Param("accountId") Long accountId );

}
