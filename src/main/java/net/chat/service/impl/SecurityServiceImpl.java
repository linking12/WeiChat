package net.chat.service.impl;

import java.util.List;

import net.chat.dao.ResourcesDao;
import net.chat.dao.UserDao;
import net.chat.domain.Resources;
import net.chat.domain.User;
import net.chat.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("secutiryService")
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService {

	@Autowired 
	private ResourcesDao resourcesDao;

	@Autowired 
	private UserDao userDao;

	public List<Resources> getAllResource() {
		return resourcesDao.findAll();
	}

	public User findUserByName(String name) {
		return userDao.findByName(name);
	}

}
