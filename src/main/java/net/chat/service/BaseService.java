/**
 * 
 */
package net.chat.service;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public interface BaseService<T> {

	T save(T t);

	T edit(T t);

	void delete(T t);

	T findOne(T t);

	List<T> query(T t);

}
