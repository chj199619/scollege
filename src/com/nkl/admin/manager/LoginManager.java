package com.nkl.admin.manager;

import java.util.List;

import com.nkl.common.util.Md5;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.User;

public class LoginManager {

	UserDao userDao;
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<Picnews>
	 */
	public List<User> listUsers(User user){
		List<User> users = userDao.listUsers(user);
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public User getUser(User user){
		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		User _user = userDao.getUser(user);
		
		return _user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
