package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxMallCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallCartDao extends JpaRepository<WxMallCart, Long>, JpaSpecificationExecutor<WxMallCart> {

	@Query("from WxMallCart  where mallUserId= :mallUserId order by id desc")
	List<WxMallCart> findCartByUserId(@Param("mallUserId")long mallUserId);
	
	
	@Query("from WxMallCart  where mallUserId= :mallUserId and productId= :productId")
	WxMallCart findCartByUserIdAndProductId(@Param("mallUserId")long mallUserId,@Param("productId")long productId);
	
	@Modifying
	@Query("delete from  WxMallCart  where mallUserId= :mallUserId and productId= :productId")
	void deleteCartByProductId(@Param("mallUserId")long mallUserId,@Param("productId")long productId);
}
