package net.chat.dao.mall;

import net.chat.domain.mall.WxOrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxOrderProductDao extends JpaRepository<WxOrderProduct, Long>, JpaSpecificationExecutor<WxOrderProduct> {

}
