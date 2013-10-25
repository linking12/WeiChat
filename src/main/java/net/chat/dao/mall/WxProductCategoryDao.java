package net.chat.dao.mall;

import net.chat.domain.mall.WxPrdtSubCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxProductCategoryDao extends JpaRepository<WxPrdtSubCategory, Long>, JpaSpecificationExecutor<WxPrdtSubCategory> {

}
