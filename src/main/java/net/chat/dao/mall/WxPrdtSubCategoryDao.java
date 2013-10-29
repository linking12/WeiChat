package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxPrdtSubCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxPrdtSubCategoryDao extends JpaRepository<WxPrdtSubCategory, Long>, JpaSpecificationExecutor<WxPrdtSubCategory> {
	
	@Query("from WxPrdtSubCategory  where categoryId= :categoryId")
	List<WxPrdtSubCategory> findSubCategoryByCategoryId(@Param("categoryId")long categoryId);
	
}
