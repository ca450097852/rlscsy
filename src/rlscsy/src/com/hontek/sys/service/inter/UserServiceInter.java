package com.hontek.sys.service.inter;

import java.util.List;
import java.util.Map;

import com.hontek.comm.base.LoginUser;
import com.hontek.sys.pojo.TsUser;

public interface UserServiceInter {

	/**
	 * 获取登录用户
	 */
	public LoginUser loginByInfo(String userName, String password,String entId);
	
	/**
	 * 获取登录用户
	 */
	public LoginUser loginByInfo2(String userName, String password,String flag,String entIds);
	
	/**
	 * 分页查询用户列表
	 */
	public String findUserList(TsUser user, int page, int rows);
		
	/**
	 * 添加用户
	 */
	public String addUser(TsUser user) ;
	
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String deleteUser(String ids) ;
	/**
	 * 获取用户
	 */
	public TsUser getUserById(String userid) ;
	
	/**
	 * 修改用户
	 */
	public String updateUser(TsUser user) ;
	
	
	public String findUserIdBy(String userName);	
	
	public String updateUserSts(String ids, String sts);
	
	/**
	 * 查询用户名是否唯一
	 */
	public String findIsUniqueAccount(String account,String accountentId);
	
	
	public String findUserList(TsUser user);

	/**
	 * 获取用户列表的下拉框
	 * @param entId 机构ID
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getUserListToSelect(int entId, int page, int rows);
	
	/**
	 * 通过机构Id获取所有用户
	 * @param entId 机构ID
	 * @return
	 */
	public List<TsUser> getUserListByEntId(String entId);


	public String findIsUniqueNoticeUrl(String noticeUrl);
	/**
	 *  获取当前登陆用户的信息
	 * @param loginUserId
	 * @return
	 */
	public String getLoginUserInfo(String loginUserId);
	/**
	 * 修改当前登陆用户密码
	 * @param loginUserId
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public String updateLoginUserPass(String loginUserId, String password,
			String newPassword);
	/**
	 * 根据用户名获取用户信息
	 * @param loginid
	 * @return
	 */
	public LoginUser loginByInfoByUserName(String loginid);
	
	public Map<String, Integer> getCountMap(Integer mainbodyId);

}
