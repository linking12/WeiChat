/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxGameDao extends JpaRepository<WxGame, Long>,
		JpaSpecificationExecutor<WxGame> {

	@Query("from WxGame game where game.url=:url and game.gtype=:gameType")
	WxGame findByUrlAndGameType(@Param("url") String url,
			@Param("gameType") String gameType);

}
