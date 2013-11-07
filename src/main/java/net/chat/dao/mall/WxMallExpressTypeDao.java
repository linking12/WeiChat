package net.chat.dao.mall;

import java.util.List;

import net.chat.domain.mall.WxMallExpressType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxMallExpressTypeDao extends JpaRepository<WxMallExpressType, Long>, JpaSpecificationExecutor<WxMallExpressType> {

	@Query("from WxMallExpressType  where mallId= :mallId")
	List<WxMallExpressType> findExpressTypeListByMall(@Param("mallId")long mallId);
	
}
