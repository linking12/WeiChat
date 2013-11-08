package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxPrdtCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxPrdtCategoryDao extends JpaRepository<WxPrdtCategory, Long>, JpaSpecificationExecutor<WxPrdtCategory> {

	@Query("from WxPrdtCategory  where subCategoryId= :subCategoryId order by createDate desc")
	List<WxPrdtCategory> findPrdtBySubCategoryId(@Param("subCategoryId")long subCategoryId);
}
