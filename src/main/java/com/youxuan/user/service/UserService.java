package com.youxuan.user.service;

import java.util.List;

import com.youxuan.entity.Userinfo;
import com.youxuan.user.vo.UserData;

public interface UserService {
	
	void addUserList(List<Userinfo> userLists);
	
	Userinfo getUserById(String UserId);
	
	List<UserData> getUserList();

	void addUser(Userinfo userinfo);

	void updateUser(Userinfo userinfo);

	void deleteUserById(String userId);

}
