package com.hontek.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.RoleUserDao;
import com.hontek.sys.dao.UserDao;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.UserServiceInter;
/**
 * <p>Title: 用户表</p>
 * <p>Description: 用户ServiceImpl 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class UserServiceImpl extends BaseServiceImpl implements UserServiceInter{
	
	private UserDao userDao;
	private RoleUserDao roleUserDao;
	

	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 获取登录用户
	 */
	public LoginUser loginByInfo(String userName, String password,String flag) {
		LoginUser loginUser = userDao.loginByInfo(userName,password,flag);
		return loginUser;
	}
	
	/**
	 * 获取登录用户
	 */
	public LoginUser loginByInfo2(String userName, String password,String flag,String entIds) {
		LoginUser loginUser = userDao.loginByInfo2(userName,password,flag,entIds);
		return loginUser;
	}
	
	public LoginUser loginByInfoByUserName(String userName) {
		LoginUser loginUser = userDao.loginByInfoByUserName(userName);
		return loginUser;
	}
	
	
	/**
	 * 分页查询用户列表
	 */
	public String findUserList(TsUser user, int page, int rows) {
		String jsonMsg = "";
		try {
			Pager<TsUser> pager = userDao.findUserList(user, page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询用户出现错误!";
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}
		
	/**
	 * 添加用户
	 */
	public String addUser(TsUser user) {
		String jsonMsg = "添加用户成功!";
		try{
			user.setUserId(String.valueOf(userDao.getTableSequence("ts_user")));
			userDao.save(user);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加用户失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public String deleteUser(String ids) {
		String jsonMsg="删除成功!";
		try{
			userDao.deleteUsers(ids);
		}catch(Exception e){
			e.printStackTrace();
			jsonMsg = "删除用户失败!"+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 获取用户
	 */
	public TsUser getUserById(String userid) {
		TsUser user = (TsUser) userDao.get(TsUser.class, userid);		
		return user;
	}
	
	/**
	 * 修改用户
	 */
	public String updateUser(TsUser user) {		
		String jsonMsg="修改用户成功";
		 try {
			userDao.update(user);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	
	public String findUserIdBy(String userName) {
		TsUser tsUser = userDao.findUserIdBy(userName);
		return getJSON(tsUser);
	}
	
	
	
	
	public String updateUserSts(String ids, String sts) {
		String jsonMsg = "操作成功";
		try {
			userDao.updateUserSts(ids,sts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg ;
	}
	
	/**
	 * 查询用户名是否唯一
	 */
	public String findIsUniqueAccount(String account,String accountentId) {
		boolean b = userDao.findIsUniqueAccount(account,accountentId);		
		return String.valueOf(b);
	}
	
	public String findIsUniqueNoticeUrl(String noticeUrl) {
		boolean b = userDao.findIsUniqueNoticeUrl(noticeUrl);
		return String.valueOf(b);
	}	

	/**
	 * 获取用户列表的下拉框
	 * @param endId
	 * @param page
	 * @param rows
	 * @return
	 */
	public String getUserListToSelect(int entId, int page, int rows) {
		String jsonString = "";
		try {
			TsUser user = new TsUser();
			user.setEntId(entId);
			Pager<TsUser> pager = userDao.findUserList(user, page, rows);
			List<TsUser> list = pager.getData();
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", "-1");
			map.put("text", "全部");
			listResults.add(map);
			for (TsUser u : list) {
				map = new HashMap<String, Object>();
				map.put("id", u.getUserId());
				map.put("text", u.getNickname());
				listResults.add(map);
			}
			jsonString = getJSON(listResults);
		} catch (Exception e) {
			jsonString = "查询失败："+e.getMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}

	
	/**
	 * 提供机构Id获取所有用户
	 * @param entId 机构Id
	 * @return List<TsUser>
	 */
	public List<TsUser> getUserListByEntId(String entId) {
		List<TsUser> list = userDao.find("from TsUser where entId=" + entId);		
		return list;
	}
	
	/**
	 * 获取用户信息
	 */
	public String findUserList(TsUser user) {
		String jsonMsg = "";
		try {
			StringBuffer condition = new StringBuffer("");
			if(user!=null){
				if(user.getEntId()!=0){
					condition.append(" and entId = "+user.getEntId());
				}
			}
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			List<TsUser> list =  userDao.findList(TsUser.class, condition.toString());
			for (int i = 0; i<list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				TsUser tsUser = list.get(i);
				map.put("id", tsUser.getUserId() );
				map.put("text", tsUser.getUserName() );
				listResults.add(map);
			}
			jsonMsg = getJSON(listResults);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	public String getLoginUserInfo(String loginUserId) {
		TsUser user = (TsUser) userDao.get(TsUser.class, loginUserId);
		return getJSON(user);
	}
	
	public String updateLoginUserPass(String loginUserId, String password,
			String newPassword) {
		String jsonMsg = "修改成功";
		try {
			TsUser user = (TsUser) userDao.get(TsUser.class, loginUserId);
			if(user==null){
				jsonMsg = "无此帐号";
			}else{
				if(!user.getPassword().equals(password)){
					jsonMsg = "原密码错误，请重新输入";
				}else{
					user.setPassword(newPassword);
					userDao.update(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	
	public Map<String, Integer> getCountMap(Integer mainbodyId){
		//未处理投诉举报数量
		String sql = "SELECT COUNT(*) from tb_complaint WHERE  sts=0";
		
		String sql2 = "SELECT COUNT(*) from tb_warning WHERE  state=1";
		
		if(mainbodyId!=null){
			sql = "select count(*) from tb_complaint t1 ,ts_enterprise t2 where t1.sts=0 and t1.ent_id = t2.ent_id and t2.body_ent_id = "+mainbodyId;
			sql2 = "SELECT COUNT(*) from tb_warning t1 ,ts_enterprise t2 where t1.state=1 and t1.entid = t2.ent_id and t2.body_ent_id = "+mainbodyId;
		}
		
		int count1 = userDao.countBySql(sql);
		
		int count2 = userDao.countBySql(sql2);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("complaint", count1);
		map.put("warning", count2);
		
		return map;
	}
	
	//**************************** SET AND GET 
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleUserDao getRoleUserDao() {
		return roleUserDao;
	}
	public void setRoleUserDao(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}


	
}
