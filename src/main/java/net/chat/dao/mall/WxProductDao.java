package net.chat.dao.mall;

import net.chat.domain.mall.WxProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxProductDao extends JpaRepository<WxProduct, Long>, JpaSpecificationExecutor<WxProduct> {

}
