package net.chat.dao;

import net.chat.domain.WxLbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxLbsDao extends JpaRepository<WxLbs, Long>, JpaSpecificationExecutor<WxLbs> {

	@Query("from WxLbs c where c.accountId= :accountId ")
	WxLbs findByAccountId(@Param("accountId") long accountId);
}
