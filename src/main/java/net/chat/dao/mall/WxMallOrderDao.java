package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxMallOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallOrderDao extends JpaRepository<WxMallOrder, Long>, JpaSpecificationExecutor<WxMallOrder> {

	@Query("from WxMallOrder  where mallId=:mallId and userId=:userId order by id desc")
	List<WxMallOrder> findOrderList(@Param("mallId")long mallId,@Param("userId")long userId);
	
	@Query("from WxMallOrder where mallId=:mallId and userId=:userId and id=:orderId")
	WxMallOrder findOrder(@Param("mallId")long mallId,@Param("userId")long userId,@Param("orderId")long orderId);
}
