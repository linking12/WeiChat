/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxCmdDao extends JpaRepository<WxCmd, Long>,
		JpaSpecificationExecutor<WxCmd> {

	@Query("from WxCmd where accountId = :accountId and cmd like %:cmd%")
	Page<WxCmd> findCmdByAccountId(@Param("accountId") Long accountId,
			@Param("cmd") String cmd, Pageable pageable);

	@Query("from WxCmd where accountId = :accountId and cmd like %:cmd%")
	List<WxCmd> findCmdByAccountId(@Param("accountId") Long accountId,
			@Param("cmd") String cmd);

	@Query("from WxCmd where accountId = :accountId")
	List<WxCmd> findCmdByAccountId(@Param("accountId") Long accountId);

}
