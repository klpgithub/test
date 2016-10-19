package com.youxuan.user.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youxuan.entity.Userinfo;
import com.youxuan.user.service.UserService;
import com.youxuan.user.vo.UserData;

/**
 * 
* @ClassName: UserController 
* @Description: 用户操作controller
* @author 孔令朋
* @date 2016年10月11日 下午3:09:45
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	
	/**
	 * @Description 获取用户列表
	 * @Title: test 
	 * 时间:2016年10月11日下午4:42:53
	 * @author:孔令朋
	 * @return String返回类型
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value="list")
	@ResponseBody
	public List<UserData> test(){
		List<UserData> list = userService.getUserList();
		return list;
	}
	
	/**
	 * @Description 用户信息修改 
	 * @Title: userUpdate 
	 * 时间:2016年10月14日上午11:00:20
	 * @author:孔令朋
	 * @return void返回类型
	 * @param userinfo
	 *
	 */
	@RequestMapping("userUpdate")
	@ResponseBody
	private void userUpdate(Userinfo userinfo){
		userService.updateUser(userinfo);
	}
	
	/**
	 * @Description  根据用户ID查用户信息 
	 * @Title: getUserById 
	 * 时间:2016年10月16日下午12:17:16
	 * @author:孔令朋
	 * @return Userinfo返回类型
	 * @param userId
	 * @return
	 *
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public Userinfo getUserById(String userId){
		return userService.getUserById(userId);
	}
	
	/**
	 * @Description 添加用户 
	 * @Title: addUser 
	 * 时间:2016年10月16日下午12:18:50
	 * @author:孔令朋
	 * @return void返回类型
	 * @param userinfo
	 *
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public void addUser(Userinfo userinfo){
		userService.addUser(userinfo);
	}
	
	/**
	 * @Description 根据ID删除用户 
	 * @Title: removeUserById 
	 * 时间:2016年10月17日上午10:21:06
	 * @author:孔令朋
	 * @return void返回类型
	 * @param userId
	 *
	 */
	@RequestMapping("removeUserById")
	@ResponseBody
	public void removeUserById(String userId){
		userService.deleteUserById(userId);
	}
	
}
