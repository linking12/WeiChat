package net.chat.dao.mall;

import net.chat.domain.mall.WxProductPic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxProductPicDao extends JpaRepository<WxProductPic, Long>, JpaSpecificationExecutor<WxProductPic> {

}
