/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

import net.chat.dao.UserDao;
import net.chat.domain.User;
import net.chat.service.UserSerivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * 
 */
@Service("userService")
public class UserSerivceImpl implements UserSerivce {
	@Autowired
	UserDao dao;

	@Override
	public User save(User t) {

		return dao.save(t);
	}

	@Override
	public User edit(User t) {

		return dao.saveAndFlush(t);
	}

	@Override
	public void delete(User t) {
		dao.delete(t);

	}

	@Override
	public User findOne(User t) {

		return dao.findOne(t.getUserId());
	}

	@Override
	public List<User> query(User t) {

		return null;
	}

	@Override
	public User findByName(String name) {

		return dao.findByName(name);
	}

}
