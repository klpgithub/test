package com.youxuan.user.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.youxuan.entity.Userinfo;
import com.youxuan.user.dao.UserDao;

/**
 * 
 * @ClassName: UserDaoImpl
 * @Description: 用户操作dao实现
 * @author 孔令朋
 * @date 2016年10月11日 下午3:09:30
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private MongoTemplate mongoTemplate;

	public List<Userinfo> getUserList() {
		List<Userinfo> findAll = this.mongoTemplate.findAll(Userinfo.class);
		return findAll;
	}

	public void addUser(Userinfo userinfo) {
		this.mongoTemplate.insert(userinfo);
	}

	public void updateUser(Userinfo userinfo) {
		Query query = new Query(Criteria.where("_id").is(userinfo.getUserId()));

		Update update = new Update();
		int i = 0;
		if (userinfo.getUserType() != null && !"".equals(userinfo.getUserType())) {
			update.set("userType", userinfo.getUserType());
			i++;
		}
		if (userinfo.getUserName() != null && !"".equals(userinfo.getUserName())) {
			update.set("userName", userinfo.getUserName());
			i++;
		}
		if (userinfo.getUserUnitCode() != null && !"".equals(userinfo.getUserUnitCode())) {
			update.set("userUnitCode", userinfo.getUserUnitCode());
			i++;
		}
		if (userinfo.getUserUnitName() != null && !"".equals(userinfo.getUserUnitName())) {
			update.set("userUnitName", userinfo.getUserUnitName());
			i++;
		}
		if (userinfo.getUserMgtLevel() != null && !"".equals(userinfo.getUserMgtLevel())) {
			update.set("userMgtLevel", userinfo.getUserMgtLevel());
			i++;
		}
		if (userinfo.getUserFixTel() != null && !"".equals(userinfo.getUserFixTel())) {
			update.set("userFixTel", userinfo.getUserFixTel());
			i++;
		}
		if (userinfo.getUserUsrEmail() != null && !"".equals(userinfo.getUserUsrEmail())) {
			update.set("userUsrEmail", userinfo.getUserUsrEmail());
			i++;
		}
		if (userinfo.getUserPostCode() != null && !"".equals(userinfo.getUserPostCode())) {
			update.set("userPostCode", userinfo.getUserPostCode());
			i++;
		}
		if (userinfo.getUserLoginPW() != null && !"".equals(userinfo.getUserLoginPW())) {
			update.set("userLoginPW", userinfo.getUserLoginPW());
			i++;
		}
		if (userinfo.getUserLoginName() != null && !"".equals(userinfo.getUserLoginName())) {
			update.set("userLoginName", userinfo.getUserLoginName());
			i++;
		}
		if (userinfo.getUserMobTel() != null && !"".equals(userinfo.getUserMobTel())) {
			update.set("userMobTel", userinfo.getUserMobTel());
			i++;
		}

		// Update update = new Update().set("userLoginName",
		// userinfo.getUserLoginName())
		// .set("userLoginPW", userinfo.getUserLoginPW())// 防格式化---
		// .set("userType", userinfo.getUserType())//
		// .set("userName", userinfo.getUserName())//
		// .set("userUnitCode", userinfo.getUserUnitCode())//
		// .set("userUnitName", userinfo.getUserUnitName())//
		// .set("userMgtLevel", userinfo.getUserMgtLevel())//
		// .set("userFixTel", userinfo.getUserFixTel())//
		// .set("userMobTel", userinfo.getUserMobTel())//
		// .set("userUsrEmail", userinfo.getUserUsrEmail())//
		// .set("userPostCode", userinfo.getUserPostCode());
		if (i>0) {
			this.mongoTemplate.updateFirst(query, update, Userinfo.class);
		}
	}

	public void deleteUserById(String userId) {
		Query query = new Query(Criteria.where("_id").is(userId));
		this.mongoTemplate.findAndRemove(query, Userinfo.class, "user_info");
	}

	public void createCollection(Class<?> clazz) {
		this.mongoTemplate.createCollection(clazz);
	}

	public void dropCollection(Class<?> clazz) {
		this.mongoTemplate.dropCollection(clazz);
	}

	public Userinfo getUserById(String userId) {
		Userinfo userinfo = this.mongoTemplate.findById(userId, Userinfo.class);
		return userinfo;
	}

	public void addUserList(List<Userinfo> userLists) {
		this.mongoTemplate.insertAll(userLists);
	}

	public Userinfo getUserByUserLoginName(String userLoginName) {
		Query query = new Query(Criteria.where("userLoginName").is(userLoginName));
		List<Userinfo> list = this.mongoTemplate.find(query, Userinfo.class);
		return list.get(0);
	}

}
