package com.hontek.sys.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TsUser entity. @author MyEclipse Persistence Tools
 */

public class TsUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private int entId;
	private String userName;
	private String password;
	private String nickname;
	private String sex;
	private String age;
	private String birthDate;
	private String intrest;
	private String flag;  //
	private String roleName;  // 角色名字
	private String phone;
	private String email;
	private String sts;
	private String crtUserId;
	private String regDate;
	private String signature;
	private String qqnum;
	private String tel;
	private String fax;
	private String addr;
	private String admin;
	private Set tsRoleUsers = new HashSet(0);
	private String entName;
	
	
	private String noticeUrl;
	
	// Constructors

	/** default constructor */
	public TsUser() {
	}


	public TsUser(String userId, int entId, String userName, String password,
			String nickname, String crtUserId, String regDate,String flag) {
		super();
		this.userId = userId;
		this.entId = entId;
		this.userName = userName;
		this.password = password;
		this.nickname = nickname;
		this.crtUserId = crtUserId;
		this.regDate = regDate;
		this.flag = flag;
	}


	// Property accessors

	public TsUser(String userId, int entId, String userName, String password,
			String nickname, String sex, String age, String birthDate,
			String intrest, String flag, String roleName, String phone,
			String email, String sts, String crtUserId,
			String regDate, String signature, String qqnum, String tel,
			String fax, String addr, String admin, Set tsRoleUsers,
			String entName) {
		super();
		this.userId = userId;
		this.entId = entId;
		this.userName = userName;
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
		this.birthDate = birthDate;
		this.intrest = intrest;
		this.flag = flag;
		this.roleName = roleName;
		this.phone = phone;
		this.email = email;
		this.sts = sts;
		this.crtUserId = crtUserId;
		this.regDate = regDate;
		this.signature = signature;
		this.qqnum = qqnum;
		this.tel = tel;
		this.fax = fax;
		this.addr = addr;
		this.admin = admin;
		this.tsRoleUsers = tsRoleUsers;
		this.entName = entName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public int getEntId() {
		return entId;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getIntrest() {
		return this.intrest;
	}

	public void setIntrest(String intrest) {
		this.intrest = intrest;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSts() {
		return this.sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getCrtUserId() {
		return this.crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	public String getRegDate() {
		return this.regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQqnum() {
		return this.qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Set getTsRoleUsers() {
		return this.tsRoleUsers;
	}

	public void setTsRoleUsers(Set tsRoleUsers) {
		this.tsRoleUsers = tsRoleUsers;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	
}