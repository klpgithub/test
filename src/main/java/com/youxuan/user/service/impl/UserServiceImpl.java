package com.youxuan.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxuan.entity.Userinfo;
import com.youxuan.user.dao.UserDao;
import com.youxuan.user.service.UserService;
import com.youxuan.user.vo.UserData;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户操作service实现
 * @author 孔令朋
 * @date 2016年10月11日 下午3:09:10
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public List<UserData> getUserList() {
		List<Userinfo> list = userDao.getUserList();
		List<UserData> userList = new ArrayList<UserData>();
		for (Userinfo userinfo : list) {
			UserData data = new UserData();
			data.setId(userinfo.getUserId());
			String[] d = { userinfo.getUserId(), userinfo.getUserLoginName(), userinfo.getUserLoginPW(),
					userinfo.getUserName(), userinfo.getUserUnitCode(), userinfo.getUserUnitName(),
					userinfo.getUserMgtLevel(), userinfo.getUserMobTel(), userinfo.getUserFixTel(),
					userinfo.getUserUsrEmail(), userinfo.getUserPostCode(),userinfo.getUserType()+"" };
			data.setData(d);
			userList.add(data);
		}
		return userList;
	}

	public void addUser(Userinfo userinfo) {
		userinfo.setUserId(null);
		userDao.addUser(userinfo);
	}

	public void updateUser(Userinfo userinfo) {
		userDao.updateUser(userinfo);
	}

	public void deleteUserById(String userId) {
		String[] uId = userId.split(",");
		for (String id : uId) {
			userDao.deleteUserById(id);
		}
	}

	public Userinfo getUserById(String UserId) {
		return userDao.getUserById(UserId);
	}

	public void addUserList(List<Userinfo> userLists) {
		userDao.addUserList(userLists);
	}

}
