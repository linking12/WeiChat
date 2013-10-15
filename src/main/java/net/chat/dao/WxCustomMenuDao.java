package net.chat.dao;

import net.chat.domain.WxCustomMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxCustomMenuDao extends JpaRepository<WxCustomMenu, Long>, JpaSpecificationExecutor<WxCustomMenu> {

	
}
