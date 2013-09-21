package net.chat.dao;

import net.chat.domain.Resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResourcesDao extends JpaRepository<Resources, Long>, JpaSpecificationExecutor<Resources> {

}
