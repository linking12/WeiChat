package net.chat.dao.mall;

import net.chat.domain.mall.WxProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxPrdtSubCategoryDao extends JpaRepository<WxProductCategory, Long>, JpaSpecificationExecutor<WxProductCategory> {

}
