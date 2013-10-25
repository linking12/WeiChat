package net.chat.dao.mall;

import net.chat.domain.mall.WxProductPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxProductPriceDao extends JpaRepository<WxProductPrice, Long>, JpaSpecificationExecutor<WxProductPrice> {

}
