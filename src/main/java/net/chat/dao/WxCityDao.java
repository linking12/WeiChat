package net.chat.dao;

import java.util.List;

import net.chat.domain.WxCity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxCityDao extends JpaRepository<WxCity, Long>, JpaSpecificationExecutor<WxCity> {

	@Query("from WxCity c where c.parentCode= :parentCode ")
	List<WxCity> findByParentCode(@Param("parentCode") String parentCode);
}
