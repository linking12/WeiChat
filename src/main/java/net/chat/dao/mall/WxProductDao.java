package net.chat.dao.mall;

import net.chat.domain.mall.WxProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxProductDao extends JpaRepository<WxProduct, Long>, JpaSpecificationExecutor<WxProduct> {
	
	
	@Query("from WxProduct  where id= :id and curdate() between effectiveDate and expiryDate")
	WxProduct findProductById(@Param("id")long id);
}
