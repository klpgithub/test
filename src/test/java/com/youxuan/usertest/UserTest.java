package com.youxuan.usertest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.youxuan.entity.Userinfo;
import com.youxuan.user.service.UserService;
import com.youxuan.user.vo.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-servlet.xml", "classpath:spring-common.xml" })
public class UserTest {

	@Resource
	private UserService userService;
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Test
	public void testAddUser() {
		Userinfo userinfo = new Userinfo("登陆名", "登陆密码", "用户姓名", "单位编码", "单位名称", "管理级别", "移动电话", "固定电话", "电子邮箱", "邮政编码",
				1);
		userService.addUser(userinfo);
		Userinfo userinfo1 = new Userinfo("zhangsan", "123", "张三", "001", "百度", "职员", "115252", "063526", "96524152@qq.com", "025536",
				1);
		Userinfo userinfo2 = new Userinfo("lisi", "1230", "李四", "002", "阿里巴巴", "总监", "152635", "20520", "5252@163.com", "023635",
				1);
		List<Userinfo> list = new ArrayList<Userinfo>();
		list.add(userinfo1);
		list.add(userinfo2);
		userService.addUserList(list);
	}

	@Test
	public void testUpdateUser() {
//		Userinfo userinfo = new Userinfo("登陆名", "登陆密码", "用户姓名", "单位编码", "单位名称","管理级别", "移动电话", "固定电话", "电子邮箱", "邮政编码", 1);
		Userinfo userinfo = new Userinfo(null, "登陆密码", "用户姓名", null, null,null, null, null, null, null, null);
		userinfo.setUserId("58004fb4c9427219f4d56932");
		userService.updateUser(userinfo);
		
//		Query query = new Query(Criteria.where("_id").is("58004fb4c9427219f4d56932"));
//		Update update = new Update().set("userLoginName", "9999999999");
//		mongoTemplate.updateFirst(query, update, Userinfo.class);
	}

	@Test
	public void testDeleteUser() {
		userService.deleteUserById("a");
	}

	@Test
	public void testGetUser() {
		Userinfo userinfo = userService.getUserById("c");
		System.out.println(userinfo);

		List<UserData> list = userService.getUserList();
		for (UserData userData : list) {
			System.out.println(userData.getData());
		}
	}

}
