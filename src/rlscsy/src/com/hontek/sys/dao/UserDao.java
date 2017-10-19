package com.hontek.sys.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TsRole;
import com.hontek.sys.pojo.TsRolePurv;
import com.hontek.sys.pojo.TsUser;

public class UserDao extends BaseDao {
	
	private String entIds="";
	
	@SuppressWarnings("unchecked")
	public void getEnterprise(Object entId){
		String hql = "select t.entId from TsEnterprise t where t.flag=2 and t.parentId="+entId;
		System.out.println(hql);
		List<Object> list =  super.findObject(hql);
		if(list!=null&&!list.isEmpty()){
			for (Object object : list) {
				entIds+=object+",";				
				getEnterprise(object);
			}
		}		
	}

	/**
	 * 获取用户列表
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public Pager<TsUser> findUserList(TsUser user, int page, int rows) throws AppException {
			
		String sql = "select tu.user_id as \"userId\",tu.ent_id as \"entId\",tu.user_name as \"userName\",tu.password as \"password\","
				+ "tu.nickname as \"nickname\",tu.sex as \"sex\",tu.age as \"age\",tu.birth_date as \"birthDate\",tu.intrest as \"intrest\","
				+ "tu.flag as \"flag\",tu.phone as \"phone\",tu.email as \"email\",tu.sts as \"sts\","
				+ "tu.crt_user_id as \"crtUserId\",tu.reg_date as \"regDate\",tu.signature as \"signature\",tu.qqnum as \"qqnum\",tu.tel as \"tel\",tu.fax as \"fax\","
				+ "tu.addr as \"addr\",tu.admin as \"admin\",te.name as \"entName\" from ts_user tu,TS_ENTERPRISE te where tu.ent_id = te.ent_id and admin is null ";
		String temp = "";
		if(user!=null){
			if (!"".equals(user.getEntId()) && user.getEntId() >0) {		
				//获取所有子机构的id
				entIds="";
				getEnterprise(user.getEntId());		
				temp += " and tu.ent_id in(" +entIds+ user.getEntId()+") ";
			} 
			if (!"".equals(user.getUserName()) && user.getUserName() != null) {
				temp += " and tu.user_name like '%" + user.getUserName() + "%'";
			} 
			if (!"".equals(user.getSts()) && user.getSts() != null) {
				temp += " and tu.sts =" + user.getSts();
			}
			if (!"".equals(user.getFlag()) && user.getFlag() != null) {
				temp += " and tu.flag =" + user.getFlag();
			}
			if (!"".equals(user.getUserId()) && user.getUserId() != null) {
				temp += " and tu.user_id !=" + user.getUserId();
			}
		}
		//temp+=" order by tu.reg_date desc ";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql + temp);
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("userName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("password", StandardBasicTypes.STRING);
		sqlQuery.addScalar("nickname", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sex", StandardBasicTypes.STRING);
		sqlQuery.addScalar("age", StandardBasicTypes.STRING);
		sqlQuery.addScalar("birthDate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("intrest", StandardBasicTypes.STRING);

		sqlQuery.addScalar("flag", StandardBasicTypes.STRING);
		sqlQuery.addScalar("phone", StandardBasicTypes.STRING);
		sqlQuery.addScalar("email", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sts", StandardBasicTypes.STRING);

		sqlQuery.addScalar("crtUserId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("regDate", StandardBasicTypes.STRING);
		sqlQuery.addScalar("signature", StandardBasicTypes.STRING);
		sqlQuery.addScalar("qqnum", StandardBasicTypes.STRING);
		sqlQuery.addScalar("tel", StandardBasicTypes.STRING);
		sqlQuery.addScalar("fax", StandardBasicTypes.STRING);
		sqlQuery.addScalar("addr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("admin", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TsUser.class));
		String hql = "select count(*) from ts_user tu,TS_ENTERPRISE te where tu.ent_id = te.ent_id and admin is null " + temp;

		List list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		int count = this.countBySql(hql).intValue();
		Pager<TsUser> pager = new Pager<TsUser>();
		pager.setData(list);
		pager.setTotal(count);
		return pager;
	}

	

	public void deleteUsers(String ids) {
		String hql = "delete from TsUser where userId in (" + ids + ")";
		executeHql(hql);
	}
	

	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	public LoginUser loginByInfo(String userName, String password,String flag) throws AppException {
		String hql = "select new com.hontek.comm.base.LoginUser(userId,entId,userName,password,nickname,"
				+ "sex,age,birthDate,intrest,flag,phone,email,sts,crtUserId,regDate,signature,qqnum,tel,fax,addr,admin) from TsUser where 1 = 1 ";	
		if(flag==null){
			hql += " and userName = '" + userName + "' and password = '" + password + "'";
		}else{
			hql += " and userName = '" + userName + "' and password = '" + password + "' and flag = "+flag;
		}

		List list_login = this.find(hql);
		System.out.println("查出的集合的大小：" + list_login.size());
		if (list_login.size() > 0) {
			LoginUser loginUser = (LoginUser) list_login.get(0);
			loginUser.setLoginDate(new Date());
			return loginUser;
		} else {
			return null;
		}
	}
	
	
	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	public LoginUser loginByInfo2(String userName, String password,String flag,String entIds) throws AppException {
		String hql = "select new com.hontek.comm.base.LoginUser(userId,entId,userName,password,nickname,"
				+ "sex,age,birthDate,intrest,flag,phone,email,sts,crtUserId,regDate,signature,qqnum,tel,fax,addr,admin) from TsUser where 1=1  and userName = '" + userName + "' and password = '" + password + "'";	
		if(flag!=null&&!flag.equals("")){
			hql += " and flag = "+flag;
		}
		
		if(entIds!=null&&!entIds.equals("")){
			hql += " and entId in ( "+entIds+")";
		}

		List list_login = this.find(hql);
		System.out.println("查出的集合的大小：" + list_login.size());
		if (list_login.size() > 0) {
			LoginUser loginUser = (LoginUser) list_login.get(0);
			loginUser.setLoginDate(new Date());
			return loginUser;
		} else {
			return null;
		}
	}
	

	/**
	 * 获取用户所拥有的栏目
	 * 
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findUserRoleByRoleId(int parentId, int roleId) {
		String hql = "from TsRolePurv where parentId=" + parentId + " and roleId=" + roleId + " order by seq";
		List list = find(hql);
		TsRolePurv col = null;
		for (int i = 0; i < list.size(); i++) {
			col = (TsRolePurv) list.get(i);
			List child = findUserRoleByRoleId(col.getColId(), roleId);
			col.setClildrenList(child);
		}
		return list;
	}

	/**
	 * 读取超管菜单
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAdminCol(int parentId) {
		String hql = "select new com.hontek.sys.pojo.TsRolePurv(col.id,col.colName,col.colUrl) from TsSysCol col where _parentId=" + parentId + " order by col.seq";
		List list = find(hql);
		TsRolePurv col = null;
		for (int i = 0; i < list.size(); i++) {
			col = (TsRolePurv) list.get(i);
			List child = findAdminCol(col.getColId());
			col.setClildrenList(child);
		}
		return list;
	}

	public void updateUserSts(String ids, String sts) {
		String hql = "update TsUser set sts='" + sts + "' where userId in (" + ids + ")";
		this.executeHql(hql);
	}
	/**
	 * 查询某个机构下用户名是否存在
	 * @param account 用户名称
	 * @param accountentId 机构id
	 * @return
	 */
	public boolean findIsUniqueAccount(String account,String accountentId) {
		String hql = "from TsUser where userName='" + account + "' and entId = "+accountentId;
		List list = find(hql);
		if (list.size() == 0)
			return false;
		return true;
	}
	
	public boolean findIsUniqueNoticeUrl(String noticeUrl) {
		String hql = "select count(*) from TsUser where noticeUrl='"+noticeUrl+"'";
		return count(hql)!=0;
	}
	
	public TsUser loginByInfomobile(String username, String password) {
		//System.out.println("登录名：" + username + "   密码:" + password);
		String hql = "from TsUser where userName='" + username + "' and password='" + password + "'";

		//System.out.println("执行的HQL： " + hql);
		List list_login = this.find(hql);
		if (list_login.size() > 0) {
			TsUser loginUser = (TsUser) list_login.get(0);
			String findRoleName = "from TsRole where roleId=" + loginUser.getFlag();
			TsRole role = (TsRole) find(findRoleName).get(0);
			loginUser.setRoleName(role.getRoleName());

			return loginUser;
		} else {
			return null;
		}
	}

	public TsUser findUserIdBy(String userName) {
		String sql="from TsUser";
		String temp="";
		if(!"".equals(userName) && userName !=null){
			temp=" where userName='"+userName+"'";
		}
		List list = this.findBySql(sql);
		TsUser u=null;
		for(int i=0;i<list.size();i++){
			u = (TsUser) list.get(0);
		}
		return u;
	}



	public TsUser findUser(TsUser user) {
		String hql="from TsUser where 1=1";
		
		if (user != null) {
			String userId = user.getUserId();
			int entId = user.getEntId();
			String userName = user.getUserName();
			
			if (userId != null && !"".equals(userId))
				hql += " and userId=" + userId;
			if ( entId > 0)
				hql += " and entId=" + entId;
			if (userName != null && !"".equals(userName))
				hql += " and userName=" + userName;
		}
		List<TsUser> list = this.find(hql);
		TsUser u=null;
		for(int i=0;i<list.size();i++){
			u = (TsUser) list.get(0);
		}
		return u;
	}

	public LoginUser loginByInfoByUserName(String userName) {
		String hql = "select new com.hontek.comm.base.LoginUser(userId,entId,userName,password,nickname,"
			+ "sex,age,birthDate,intrest,flag,phone,email,sts,crtUserId,regDate,signature,qqnum,tel,fax,addr,admin) from TsUser where 1 = 1 ";	
		hql += " and userName = '" + userName + "'";
		/*if(flag==null){
		}else{
			hql += " and userName = '" + userName + "' and password = '" + password + "' and flag = "+flag;
		}*/
	
		List list_login = this.find(hql);
		System.out.println("查出的集合的大小：" + list_login.size());
		if (list_login.size() > 0) {
			LoginUser loginUser = (LoginUser) list_login.get(0);
			loginUser.setLoginDate(new Date());
			return loginUser;
		} else {
			return null;
		}
	}

}
