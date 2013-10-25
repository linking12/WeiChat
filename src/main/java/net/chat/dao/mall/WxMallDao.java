package net.chat.dao.mall;

import net.chat.domain.mall.WxMall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxMallDao extends JpaRepository<WxMall, Long>, JpaSpecificationExecutor<WxMall> {

}
