package net.chat.dao.mall;

import net.chat.domain.mall.WxMall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallDao extends JpaRepository<WxMall, Long>, JpaSpecificationExecutor<WxMall> {

	@Query("from WxMall  where accountId= :accountId")
	WxMall findMallByAccountId(@Param("accountId")long accountId);
}
