package net.chat.dao;

import java.util.List;

import net.chat.domain.WxCustomMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxCustomMenuDao extends JpaRepository<WxCustomMenu, Long>,
		JpaSpecificationExecutor<WxCustomMenu> {

	@Query("from WxCustomMenu where accountId=:accountId and parentId is null")
	List<WxCustomMenu> findParentMenuByAccountId(
			@Param("accountId") Long accountId);

	@Query("from WxCustomMenu where accountId=:accountId and parentId is not null")
	List<WxCustomMenu> findChildMenuByAccountId(
			@Param("accountId") Long accountId);

	@Query("from WxCustomMenu where accountId=:accountId and  eventType is not null eventDesc is not null ")
	List<WxCustomMenu> findCommonButtonByAccountId(
			@Param("accountId") Long accountId);

}
