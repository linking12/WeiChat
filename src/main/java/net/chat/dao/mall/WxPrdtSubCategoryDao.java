package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxPrdtSubCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxPrdtSubCategoryDao extends
		JpaRepository<WxPrdtSubCategory, Long>,
		JpaSpecificationExecutor<WxPrdtSubCategory> {

	@Query("select s from WxPrdtSubCategory s where categoryId= :categoryId")
	Page<WxPrdtSubCategory> findSubCategoryByCategoryId(
			@Param("categoryId") long categoryId, Pageable pageable);

	@Query("select s from WxPrdtSubCategory s where categoryId in :categoryIds")
	Page<WxPrdtSubCategory> findSubCategoryByCategoryIds(
			@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

}
