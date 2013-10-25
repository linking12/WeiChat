package net.chat.dao.mall;

import net.chat.domain.mall.WxMallUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxMallUserDao extends JpaRepository<WxMallUser, Long>, JpaSpecificationExecutor<WxMallUser> {

}
