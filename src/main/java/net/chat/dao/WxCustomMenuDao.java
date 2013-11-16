package net.chat.dao;

import java.util.List;

import net.chat.domain.WxCustomMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxCustomMenuDao extends JpaRepository<WxCustomMenu, Long>, JpaSpecificationExecutor<WxCustomMenu> {

	@Query("from WxCustomMenu where accountId=:accountId and parentId is null")
	List<WxCustomMenu> findParentMenuByAccountId(@Param("accountId") long accountId);

	@Query("from WxCustomMenu where accountId=:accountId and parentId is not null")
	List<WxCustomMenu> findChildMenuByAccountId(@Param("accountId") long accountId);

	@Query("from WxCustomMenu where accountId=:accountId and parentId =:parentId ")
	List<WxCustomMenu> findChildMenuByAccountIdAndParentId(@Param("accountId") long accountId, @Param("parentId") long parentId);

	@Query("from WxCustomMenu where accountId=:accountId and  eventType is not null and eventDesc is not null order by parentId")
	List<WxCustomMenu> findCommonButtonByAccountId(@Param("accountId") long accountId);

}
