package net.chat.dao.mall;

import java.math.BigDecimal;

import net.chat.domain.mall.WxProductPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxProductPriceDao extends JpaRepository<WxProductPrice, Long>,
		JpaSpecificationExecutor<WxProductPrice> {

	@Query("from WxProductPrice  where productId= :productId  and curdate() between effectiveDate and expiryDate")
	WxProductPrice findPriceByProductId(@Param("productId") long productId);

	@Modifying
	@Query("update WxProductPrice  set salePrice=:salePrice where id = :id")
	void updatePrice(@Param("id") Long id,
			@Param("salePrice") BigDecimal salePrice);

}
