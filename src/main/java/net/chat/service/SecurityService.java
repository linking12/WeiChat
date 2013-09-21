package net.chat.service;

import java.util.List;

import net.chat.domain.Resources;
import net.chat.domain.User;


public interface SecurityService {

	public List<Resources> getAllResource();

	public User findUserByName(String name);

}
