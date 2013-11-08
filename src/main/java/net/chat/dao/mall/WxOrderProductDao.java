package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxOrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxOrderProductDao extends JpaRepository<WxOrderProduct, Long>, JpaSpecificationExecutor<WxOrderProduct> {

	@Query("from WxOrderProduct  where orderId=:orderId")
	List<WxOrderProduct> findOrderProductList(@Param("orderId")long orderId);
}
