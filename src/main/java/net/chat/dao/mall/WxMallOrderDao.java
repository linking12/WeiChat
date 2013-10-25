package net.chat.dao.mall;

import net.chat.domain.mall.WxMallOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxMallOrderDao extends JpaRepository<WxMallOrder, Long>, JpaSpecificationExecutor<WxMallOrder> {

}
