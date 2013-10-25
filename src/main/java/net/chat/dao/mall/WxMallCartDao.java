package net.chat.dao.mall;

import net.chat.domain.mall.WxMallCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxMallCartDao extends JpaRepository<WxMallCart, Long>, JpaSpecificationExecutor<WxMallCart> {

}
