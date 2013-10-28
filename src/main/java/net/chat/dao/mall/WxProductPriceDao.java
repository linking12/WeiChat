package net.chat.dao.mall;

import net.chat.domain.mall.WxProductPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxProductPriceDao extends JpaRepository<WxProductPrice, Long>, JpaSpecificationExecutor<WxProductPrice> {

	@Query("from WxProductPrice  where productId= :productId  and curdate() between effectiveDate and expiryDate" )
	WxProductPrice findPriceByProductId(@Param("productId")long productId);
}
