/**
 * 
 */
package net.chat.dao;

import java.util.List;

import net.chat.domain.WxGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxGameDao extends JpaRepository<WxGame, Long>, JpaSpecificationExecutor<WxGame> {

	@Query("from WxGame game where game.url=:url and game.gtype=:gameType")
	WxGame findByUrlAndGameType(@Param("url") String url, @Param("gameType") String gameType);

	@Query("select t1 from WxGame t1, WxAccountGame t2 where t1.id= t2.gameId and t2.accountId =:accountId")
	List<WxGame> findGameByAccountId(@Param("accountId") Long accountId);
}
