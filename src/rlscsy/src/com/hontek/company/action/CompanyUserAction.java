package com.hontek.company.action;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.company.service.inter.CompanyServiceInter;
import com.hontek.company.service.inter.CompanyUserServiceInter;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 企业用户表</p>
 * <p>Description:企业用户Action 类</p>
 * @version 1.0
 */
public class CompanyUserAction extends BaseAction {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private CompanyUserServiceInter companyUserServiceInter;	
	private CompanyServiceInter companyServiceInter;	
	private CompanyUser companyUser;
	private String ids;	
	
	private String msg;	
	
	private String account;	
	
	public CompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCompanyUserServiceInter(CompanyUserServiceInter companyUserServiceInter) {
		this.companyUserServiceInter = companyUserServiceInter;
	}


	public void setCompanyServiceInter(CompanyServiceInter companyServiceInter) {
		this.companyServiceInter = companyServiceInter;
	}

	/**
	 * 添加企业
	 */
	public void addCompanyUser(){
		if(companyUser!=null){
			companyUser.setRegTime(DateUtil.formatDateTime());
		}
		jsonMsg  = companyUserServiceInter.addCompanyUser(companyUser);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 修改企业
	 */
	public void updateCompanyUser(){
		jsonMsg  = companyUserServiceInter.updateCompanyUser(companyUser);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除企业
	 */
	public void deleteCompanyUser(){
		jsonMsg  = companyUserServiceInter.deleteCompanyUser(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询企业
	 */
	public void findCompanyUserPagerList(){
		
		jsonMsg  = companyUserServiceInter.findCompanyUserPagerList(companyUser, page, rows, sort , order);
		//获取文件夹
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 发布企业
	 */
	public void publishCompanyUser(){
		jsonMsg  = companyUserServiceInter.updatePublishCompanyUser(ids);
		printJsonString(jsonMsg);
	}

	
	/**
	 * 企业登陆
	 * @return
	 * @throws AppException
	 */
	public String comlogin() throws AppException{
		String info="companyLogin";
		if(getSession()==null){
			return "companyLogin";
		}
		Object obj = getSession().getAttribute("loginCompanyUser");
		CompanyUser loginCompanyUser = null;
		if(obj==null){
			try {
				if(companyUser==null){
					return "companyToLogin";
				}
				//普通用户登录
				loginCompanyUser = companyUserServiceInter.findCompanyUser(companyUser.getAccount(),companyUser.getPassword());
				if(loginCompanyUser!=null){								
					
					
					
					Company company = companyServiceInter.getCompanyByComId(loginCompanyUser.getComId());//企业信息
					
					LoginUser loginUser = new LoginUser();
					loginUser.setLoginDate(new Date());
					loginUser.setUserName(loginCompanyUser.getRealName());
					loginUser.setNickname(company.getName());
					
					//流通节点
					Company node = companyServiceInter.getNodeByComId(loginCompanyUser.getComId());
					HttpSession session = getSession();
					session.setAttribute("loginCompanyUser", loginCompanyUser);
					session.setAttribute("loginCompany", company);
					session.setAttribute("node", node);
					
					session.setAttribute("loginUser", loginUser);
					info = "companyLogin";	
				}else{
					msg = "登录失败，用户名或密码错误，请重新输入！";
					info = "companyToLogin";
				}
			} catch (Exception e) {
				msg = "系统繁忙，请稍后登陆！";
				e.printStackTrace();
				info = "companyToLogin";
			}
		}else{
			info = "companyLogin";
		}
		return info;
	}
	
	/**
	 * 企业登陆
	 * @return
	 * @throws AppException
	 */
	public String complogout() throws AppException{
		
		HttpSession session = getSession();
		
		session.setAttribute("loginCompanyUser", null);
		session.setAttribute("loginCompany", null);
		session.setAttribute("loginUser", null);
		session.setAttribute("node", null);
		session.removeAttribute("loginCompanyUser");
		session.removeAttribute("loginCompany");
		session.removeAttribute("loginUser");
		session.removeAttribute("node");
		Object obj = session.getAttribute("systemName");
		this.getSession().invalidate();
		ServletActionContext.getRequest ().getSession (true).setAttribute("systemName", obj);
		return "login";
	}
	
	
	/**
	 * 验证组织帐号是否存在（唯一）
	 */
	public void findAccountIsUnique(){
		printJsonString(companyUserServiceInter.findAccountIsUnique(companyUser.getAccount()));
	}
	

	/**
	 * 验证组织帐号是否存在（唯一）
	 */
	public void findComUserIsUnique(){
		printJsonString(companyUserServiceInter.findAccountIsUnique(account));
	}
}
