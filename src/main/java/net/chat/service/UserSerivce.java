/**
 * 
 */
package net.chat.service;

import net.chat.domain.User;

/**
 * @author Administrator
 * 
 */
public interface UserSerivce extends BaseService<User> {

	User findByName(String name);

}
