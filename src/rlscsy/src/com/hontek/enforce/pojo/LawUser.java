package com.hontek.enforce.pojo;

import java.io.Serializable;

/**
 * 
 * @author cjn
 * @date 2017/6/30
 * 执法人员表
 *
 */
public class LawUser  implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;		//人员ID
	private String entName;		//执法单位
	private String userName;	//执法人员
	private String userNo;		//执法编号
	private String addr;		//联系地址
	private String phone;		//联系电话
	private String sex;			//性别
	private String age;			//年龄
	private String createTime;	//创建时间
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public LawUser() {
	// TODO Auto-generated constructor stub
	}
	public LawUser(Long userId, String entName, String userName, String userNo,
			String addr, String phone, String sex, String age, String createTime) {
		super();
		this.userId = userId;
		this.entName = entName;
		this.userName = userName;
		this.userNo = userNo;
		this.addr = addr;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.createTime = createTime;
	}
	
	
	
	
}
