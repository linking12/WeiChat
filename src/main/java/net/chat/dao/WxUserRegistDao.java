/**
 * 
 */
package net.chat.dao;

import net.chat.domain.WxUserRegist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author bo
 * 
 */
public interface WxUserRegistDao extends JpaRepository<WxUserRegist, Long>,
		JpaSpecificationExecutor<WxUserRegist> {

	@Query("from WxUserRegist c where c.name=:name")
	WxUserRegist findByName(@Param("name") String name);

}
