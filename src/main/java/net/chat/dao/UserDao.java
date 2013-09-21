package net.chat.dao;

import net.chat.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query("from User c where c.account=:name")
	User findByName(@Param("name") String name);
}
