package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxMall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallDao extends JpaRepository<WxMall, Long>, JpaSpecificationExecutor<WxMall> {

	@Query("from WxMall  where accountId= :accountId")
	WxMall findMallByAccountId(@Param("accountId")long accountId);
	
	@Query("from WxMall  where accountId in (select id from WxAccount where customerId = :userId)")
	List<WxMall> findMallByUserId(@Param("userId")long userId);
}
