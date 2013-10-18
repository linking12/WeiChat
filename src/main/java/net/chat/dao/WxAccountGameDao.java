/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxAccountGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * @author bo
 * 
 */
public interface WxAccountGameDao extends JpaRepository<WxAccountGame, Long>, JpaSpecificationExecutor<WxAccountGame> {

	 @Query("from WxAccountGame c where c.accountId=:accountId")
	 List<WxAccountGame> findByAccountId(@Param("accountId") Long accountId);

}
