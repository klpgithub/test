package com.youxuan.user.dao;

import java.util.List;

import com.youxuan.entity.Userinfo;

public interface UserDao {
	
	Userinfo getUserById(String UserId);
	
	Userinfo getUserByUserLoginName(String userLoginName);
	
	List<Userinfo> getUserList();
	
	void addUserList(List<Userinfo> userLists);
	
	void addUser(Userinfo userinfo);

	void updateUser(Userinfo userinfo);

	void deleteUserById(String userId);

	void createCollection(Class<?> clazz);

	void dropCollection(Class<?> clazz);

}
