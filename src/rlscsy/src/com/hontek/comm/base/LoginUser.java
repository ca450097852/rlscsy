package com.hontek.comm.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.hontek.sys.pojo.TsRoleUser;

public class LoginUser {
	private String userId;        // 用户编号
	private int entId;         // 机构编号
	private String userName;    // 登录帐号
	private String password;    // 密码
	private String nickname;    // 用户名称
	private String sex;         // 性别
	private String age;         // 年龄
	private String birthDate;   // 生日
	private String intrest;     // 用户简介
	private String flag;        // 用户类型
	private String phone;       // 手机号码
	private String email;       // 电子邮箱
	private String sts;         // 使用状态
	private String crtUserId;   // 创建者
	private String regDate;     // 注册日期
	private String signature;   // 用户签名
	private String qqnum;       // QQ
	private String tel;         // 国定电话
	private String fax;         // 传真号码
	private String addr;        // 详细地址
	private Date loginDate;     // 登录时间
	private String admin;		//超管标识
	private TsRoleUser RoleUser ;  // 用户与角色对应关系
	
	// 虚构造函数
	public LoginUser(){
		
	}
	// 实构造函数
    public LoginUser(String userId,int entId,String userName,String password,String nickname,String sex,String age,String birthDate,String intrest,String flag
    		,String phone,String email,String sts,String crtUserId,String regDate,String signature,String qqnum,String tel,String fax,String addr,String admin){
    	super();
    	setUserId(userId);
    	setEntId(entId);
    	setUserName(userName);
    	setPassword(password);
    	setNickname(nickname);
    	setSex(sex);
    	setAge(age);
    	setBirthDate(birthDate);
    	setIntrest(intrest);
    	setFlag(flag);
    	setPhone(phone);
    	setEmail(email);
    	setSts(sts);
    	setCrtUserId(crtUserId);
    	setRegDate(regDate);
    	setSignature(signature);
    	setQqnum(qqnum);
    	setTel(tel);
    	setFax(fax);
    	setAddr(addr);
    	setLoginDate(loginDate);
    	setAdmin(admin);
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getIntrest() {
		return intrest;
	}
	public void setIntrest(String intrest) {
		this.intrest = intrest;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public String getCrtUserId() {
		return crtUserId;
	}
	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getQqnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public TsRoleUser getRoleUser() {
		return RoleUser;
	}
	public void setRoleUser(TsRoleUser roleUser) {
		RoleUser = roleUser;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
}
