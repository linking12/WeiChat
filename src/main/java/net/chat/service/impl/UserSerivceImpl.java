/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

import net.chat.dao.UserDao;
import net.chat.domain.User;
import net.chat.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * 
 */
@Service("userService")
public class UserSerivceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Transactional
	public User save(User t) {

		return dao.save(t);
	}

	@Transactional
	public User edit(User t) {

		return dao.saveAndFlush(t);
	}

	@Transactional
	public void delete(User t) {
		dao.delete(t);

	}

	public User findOne(User t) {

		return dao.findOne(t.getUserId());
	}

	public User findByName(String name) {

		return dao.findByName(name);
	}

	public List<User> finaAll() {

		return dao.findAll();
	}

}
