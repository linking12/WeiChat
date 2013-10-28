package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxProductCategoryDao extends JpaRepository<WxProductCategory, Long>, JpaSpecificationExecutor<WxProductCategory> {
	
	@Query("from WxProductCategory  where mallId= :mallId")
	List<WxProductCategory> findProductCategoryByMallId(@Param("mallId") long mallId);
	
}
