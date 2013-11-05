package net.chat.dao.mall;

import net.chat.domain.mall.WxMallUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallUserDao extends JpaRepository<WxMallUser, Long>,
		JpaSpecificationExecutor<WxMallUser> {

	@Query("from WxMallUser c where c.userName= :name ")
	WxMallUser findByName(@Param("name") String name);
}
