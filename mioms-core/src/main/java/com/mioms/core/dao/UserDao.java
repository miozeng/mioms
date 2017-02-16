package com.mioms.core.dao;

import java.util.List;

import com.mioms.core.entity.User;

public interface UserDao extends BaseDao<User, Long>{
	
	public List<User> findAllUsers();
	
	public List<User> findUsersByAge(String age);

}
