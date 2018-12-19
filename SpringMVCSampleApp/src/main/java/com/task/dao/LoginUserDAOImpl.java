package com.task.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.task.model.LoginUser;

/**
 * DAO Implementation for User Operations.
 * 
 * @author gizem
 *
 */
@Repository("loginUserDao")
public class LoginUserDAOImpl extends AbstractDAO<Integer, LoginUser> implements LoginUserDAO {

	@Override
	public void deleteUser(String id) {
		Query query = getSession().createSQLQuery("delete from User where id = :id");
		query.setInteger("id", Integer.valueOf(id));
		query.executeUpdate();
	}

	@Override
	public void saveUser(LoginUser user) {

		persist(user);
	}

	@Override
	public List<LoginUser> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<LoginUser>) criteria.list();
	}

}
