package net.chat.dao.mall;

import net.chat.domain.mall.WxPrdtCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxPrdtCategoryDao extends JpaRepository<WxPrdtCategory, Long>, JpaSpecificationExecutor<WxPrdtCategory> {

}
