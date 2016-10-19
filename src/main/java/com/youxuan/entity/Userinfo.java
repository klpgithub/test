package com.youxuan.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: Userinfo
 * @Description: 用户实体类
 * @author 孔令朋
 * @date 2016年10月11日 下午2:15:15
 */
@Document(collection = "user_info")
public class Userinfo {

	@Id
	private String userId; // 用户ID
	private String userLoginName;// 登陆名
	private String userLoginPW;// 登陆密码
	private String userName;// 用户姓名
	private String userUnitCode;// 单位编码
	private String userUnitName;// 单位名称
	private String userMgtLevel;// 管理级别
	private String userMobTel;// 移动电话
	private String userFixTel;// 固定电话
	private String userUsrEmail;// 电子邮箱
	private String userPostCode;// 邮政编码
	private Integer userType;// 用户类型ID

	@PersistenceConstructor
	public Userinfo() {
		super();
	}

	@PersistenceConstructor
	public Userinfo(String userId, String userLoginName, String userLoginPW, String userName, String userUnitCode,
			String userUnitName, String userMgtLevel, String userMobTel, String userFixTel, String userUsrEmail,
			String userPostCode, Integer userType) {
		super();
		this.userId = userId;
		this.userLoginName = userLoginName;
		this.userLoginPW = userLoginPW;
		this.userName = userName;
		this.userUnitCode = userUnitCode;
		this.userUnitName = userUnitName;
		this.userMgtLevel = userMgtLevel;
		this.userMobTel = userMobTel;
		this.userFixTel = userFixTel;
		this.userUsrEmail = userUsrEmail;
		this.userPostCode = userPostCode;
		this.userType = userType;
	}

	@PersistenceConstructor
	public Userinfo(String userLoginName, String userLoginPW, String userName, String userUnitCode, String userUnitName,
			String userMgtLevel, String userMobTel, String userFixTel, String userUsrEmail, String userPostCode,
			Integer userType) {
		super();
		this.userLoginName = userLoginName;
		this.userLoginPW = userLoginPW;
		this.userName = userName;
		this.userUnitCode = userUnitCode;
		this.userUnitName = userUnitName;
		this.userMgtLevel = userMgtLevel;
		this.userMobTel = userMobTel;
		this.userFixTel = userFixTel;
		this.userUsrEmail = userUsrEmail;
		this.userPostCode = userPostCode;
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserLoginPW() {
		return userLoginPW;
	}

	public void setUserLoginPW(String userLoginPW) {
		this.userLoginPW = userLoginPW;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserUnitCode() {
		return userUnitCode;
	}

	public void setUserUnitCode(String userUnitCode) {
		this.userUnitCode = userUnitCode;
	}

	public String getUserUnitName() {
		return userUnitName;
	}

	public void setUserUnitName(String userUnitName) {
		this.userUnitName = userUnitName;
	}

	public String getUserMgtLevel() {
		return userMgtLevel;
	}

	public void setUserMgtLevel(String userMgtLevel) {
		this.userMgtLevel = userMgtLevel;
	}

	public String getUserFixTel() {
		return userFixTel;
	}

	public void setUserFixTel(String userFixTel) {
		this.userFixTel = userFixTel;
	}

	public String getUserMobTel() {
		return userMobTel;
	}

	public void setUserMobTel(String userMobTel) {
		this.userMobTel = userMobTel;
	}

	public String getUserUsrEmail() {
		return userUsrEmail;
	}

	public void setUserUsrEmail(String userUsrEmail) {
		this.userUsrEmail = userUsrEmail;
	}

	public String getUserPostCode() {
		return userPostCode;
	}

	public void setUserPostCode(String userPostCode) {
		this.userPostCode = userPostCode;
	}

}
