package com.task.dao;

import java.util.List;

import com.task.model.LoginUser;

/**
 * DAO interface for User Operations.
 * 
 * @author gizem
 *
 */
public interface LoginUserDAO {

	void saveUser(LoginUser user);

	List<LoginUser> findAllUsers();

	void deleteUser(String id);
}
