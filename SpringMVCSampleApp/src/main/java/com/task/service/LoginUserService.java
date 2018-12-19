package com.task.service;

import java.util.List;

import com.task.model.LoginUser;

/**
 * Login Service Interface.
 * 
 * @author gizem
 *
 */
public interface LoginUserService {

	void saveUser(LoginUser user);

	void deleteUser(String id);

	List<LoginUser> findAllUsers();

}
