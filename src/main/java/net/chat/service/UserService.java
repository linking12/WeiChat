package net.chat.service;

import java.util.List;

import net.chat.domain.User;

public interface UserService {

	public User save(User t);

	public User edit(User t);

	public void delete(User t);

	public User findOne(User t);

	public User findByName(String name);

	public List<User> finaAll();
}
