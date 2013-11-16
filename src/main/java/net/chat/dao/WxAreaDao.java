package net.chat.dao;

import java.util.List;

import net.chat.domain.WxArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxAreaDao extends JpaRepository<WxArea, Long>, JpaSpecificationExecutor<WxArea> {

	@Query("from WxArea c where c.parentCode= :parentCode ")
	List<WxArea> findByParentCode(@Param("parentCode") String parentCode);
}
