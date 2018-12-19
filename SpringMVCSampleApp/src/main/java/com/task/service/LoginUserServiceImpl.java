package com.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.dao.LoginUserDAO;
import com.task.model.LoginUser;

/**
 * Login service Implementation.
 * 
 * @author gizem
 *
 */
@Service("userService")
@Transactional
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	private LoginUserDAO dao;

	@Override
	public void deleteUser(String id) {

		dao.deleteUser(id);
	}

	@Override
	public void saveUser(LoginUser user) {
		// TODO Auto-generated method stub
		dao.saveUser(user);
	}

	@Override
	public List<LoginUser> findAllUsers() {
		return dao.findAllUsers();
	}

}
