package net.chat.dao;

import java.util.List;

import net.chat.domain.WxProvince;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WxProvinceDao extends JpaRepository<WxProvince, Long>, JpaSpecificationExecutor<WxProvince> {

	@Query("from WxProvince c")
	List<WxProvince> findAll();
}
