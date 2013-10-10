package net.chat.dao;

import net.chat.domain.WxRecevieMessageLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxRecevieMessageLogDao extends
		JpaRepository<WxRecevieMessageLog, Long>,
		JpaSpecificationExecutor<WxRecevieMessageLog> {

}
