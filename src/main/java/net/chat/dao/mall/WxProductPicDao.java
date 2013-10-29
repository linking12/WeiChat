package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxProductPic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxProductPicDao extends JpaRepository<WxProductPic, Long>, JpaSpecificationExecutor<WxProductPic> {

	@Query("from WxProductPic  where productId = :productId" )
	List<WxProductPic> findPicByProductId(@Param("productId")long productId);

}
