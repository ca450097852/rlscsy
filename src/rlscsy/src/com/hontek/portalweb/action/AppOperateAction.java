package com.hontek.portalweb.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.company.service.inter.CompanyServiceInter;
import com.hontek.company.service.inter.CompanyUserServiceInter;

public class AppOperateAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1159790052550824110L;
	private CompanyUserServiceInter companyUserServiceInter;	
	private CompanyServiceInter companyServiceInter;	
	private CompanyUser companyUser;
	private Company company;

	private String password;
	private String newPassword;
	/**
	 * APP登录
	 * 传入参数 
	 * user.userName 
	 * user.password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void applogin() throws AppException{
		
		JSONObject jsonObject = new JSONObject();
		if(companyUser==null||companyUser.getAccount()==null||companyUser.getPassword()==null){
			jsonObject.put("code", "10001");
			jsonObject.put("msg", "用户户和密码不能为空!");
			printJsonString(jsonObject.toString());
			return;
		}
		
		CompanyUser loginCompanyUser = null;
		loginCompanyUser = companyUserServiceInter.findCompanyUser(companyUser.getAccount(),companyUser.getPassword());
		if(loginCompanyUser==null){
			jsonObject.put("code", "10001");
			jsonObject.put("msg", "用户名或密码错误!");
			printJsonString(jsonObject.toString());
			return;
		}else{
			Company companydb = companyServiceInter.getCompanyByComId(loginCompanyUser.getComId());//企业信息
			
			LoginUser loginUser = new LoginUser();
			
			loginUser.setEntId(companydb.getComId());
			loginUser.setLoginDate(new Date());
			loginUser.setUserName(loginCompanyUser.getRealName());
			loginUser.setNickname(companydb.getName());
			
			//流通节点
			Company node = companyServiceInter.getNodeByComId(loginCompanyUser.getComId());
			
			HttpSession session = getSession();
			if(session==null){
				session = getRequest().getSession();
			}
			session.setAttribute("loginCompanyUser", loginCompanyUser);
			session.setAttribute("loginCompany", companydb);
			session.setAttribute("node", node);
			session.setAttribute("loginUser", loginUser);
			
			
			jsonObject.put("code", "10000");
			jsonObject.put("msg", "登录成功");
			jsonObject.put("loginCompanyUser", loginCompanyUser);
			jsonObject.put("company", companydb);
			jsonObject.put("node", node);
			printJsonString(jsonObject.toString());
			return;
		}
	}
	

	
	/**
	 * 是否登录
	 */
	public void isLogin(){
		Company loginCompany = (Company)getSession().getAttribute("loginCompany");
		if(loginCompany!=null){
			printJsonString("true");
		}else{
			printJsonString("false");
		}
	}
	
	/**
	 * 是否登录
	 */
	public void exitSession(){
		HttpSession session = getSession();
		Object loginCompanyUser = session.getAttribute("loginCompanyUser");
		Object loginCompany = session.getAttribute("loginCompany");
		Object node = session.getAttribute("node");
		Object loginUser = session.getAttribute("loginUser");
		if(loginCompanyUser!=null){
			System.out.println("正在删除服务器：loginCompanyUser");
			session.removeAttribute("loginCompanyUser");
		}
		if(loginCompany!=null){
			System.out.println("正在删除服务器：loginCompany");
			session.removeAttribute("loginCompany");
		}
		if(node!=null){
			System.out.println("正在删除服务器：node");
			session.removeAttribute("node");
		}
		if(loginUser!=null){
			System.out.println("正在删除服务器：loginUser");
			session.removeAttribute("loginUser");
		}
	}
	
	
	/**
	 * 获取当前登录企业
	 */
	public void getLoginCompany(){
		
		Company loginCompany = (Company)getSession().getAttribute("loginCompany");
		if(loginCompany!=null){
			System.out.println("loginCompany=="+loginCompany.getComId());
			List<Company> list = companyServiceInter.findList(loginCompany);
			if(list.size()>0){
				printJsonString(getJSON(list.get(0)));
			}
		}
	}
	

	

	
	/**
	 * 修改登录密码
	 * 需要登录调用
	 */
	public void updatePassword(){
		JSONObject jsonObject = new JSONObject();
		HttpSession session = getSession();
		if(session==null){
			session = getRequest().getSession();
		}
		Object loginCompanyUser = session.getAttribute("loginCompanyUser");					
		if(loginCompanyUser==null){
			jsonObject.put("code", "10001");
			jsonObject.put("msg", "修改失败！请求过期，请重新登录！");
		}else{
			if(password!=null&&!"".equals(password)&&newPassword!=null&&!"".equals(newPassword)){
				CompanyUser lcompanyUser = (CompanyUser)loginCompanyUser;
				CompanyUser companyUser = companyUserServiceInter.findCompanyUserById(lcompanyUser.getComUserId());
				
				if(companyUser!=null&&!password.equals(companyUser.getPassword())){
					jsonObject.put("code", "10004");
					jsonObject.put("msg", "修改失败！原密码错误！");
				}else if(companyUser!=null&&password.equals(companyUser.getPassword())){
					companyUser.setPassword(newPassword);
					companyUserServiceInter.updateCompanyUser(companyUser);
					jsonObject.put("code", "10000");
					jsonObject.put("msg", "修改成功！");
				}
				
			}else if(password==null||"".equals(password)){
				jsonObject.put("code", "10002");
				jsonObject.put("msg", "修改失败！原密码不能为空！");
			}else if(newPassword==null||"".equals(newPassword)){
				jsonObject.put("code", "10003");
				jsonObject.put("msg", "修改失败！新密码不能为空！");
			}
		}
		printJsonString(jsonObject.toString());
	}
	
	

	
	/**
	 * 修改方法
	 * 需要登录调用
	 */
	public void updateCompany(){
		JSONObject jsonObject = new JSONObject();
		HttpSession session = getSession();
		if(session==null){
			session = getRequest().getSession();
		}
		Object loginUser = session.getAttribute("loginUser");					
		if(loginUser==null){
			jsonObject.put("code", "10001");
			jsonObject.put("msg", "修改失败！请求过期，请重新登录！");
		}else{
			int id = ((LoginUser)loginUser).getEntId();
			Company companydb = companyServiceInter.getCompanyByComId(id);
			
			if(company.getName()!=null&&!"".equals(company.getName())){
				companydb.setName(company.getName());
			}
			if(company.getArea()!=null&&!"".equals(company.getArea())){
				companydb.setArea(company.getArea());
			}
			if(company.getParentId()!=0){
				companydb.setParentId(company.getParentId());
			}
			if(company.getLicenseImg()!=null&&!"".equals(company.getLicenseImg())){
				companydb.setLicenseImg(company.getLicenseImg());
			}
			if(company.getCorporate()!=null&&!"".equals(company.getCorporate())){
				companydb.setCorporate(company.getCorporate());
			}
			if(company.getAddr()!=null&&!"".equals(company.getAddr())){
				companydb.setAddr(company.getAddr());
			}
			if(company.getLinkMan()!=null&&!"".equals(company.getLinkMan())){
				companydb.setLinkMan(company.getLinkMan());
			}
			if(company.getPhone()!=null&&!"".equals(company.getPhone())){
				companydb.setPhone(company.getPhone());
			}
			if(company.getEmail()!=null&&!"".equals(company.getEmail())){
				companydb.setEmail(company.getEmail());
			}
			if(company.getRegCode()!=null&&!"".equals(company.getRegCode())){
				companydb.setRegCode(company.getRegCode());
			}
			if(company.getLicenseNum()!=null&&!"".equals(company.getLicenseNum())){
				companydb.setLicenseNum(company.getLicenseNum());
			}
			if(company.getRecordDate()!=null&&!"".equals(company.getRecordDate())){
				companydb.setRecordDate(company.getRecordDate());
			}
			if(company.getIntroduction()!=null&&!"".equals(company.getIntroduction())){
				companydb.setIntroduction(company.getIntroduction());
			}
			
			companyServiceInter.updateCompany(companydb);
			
			jsonObject.put("code", "10000");
			jsonObject.put("msg", "修改成功！");
			jsonObject.put("company", company);
			printJsonString(jsonObject.toString());
		}
		
	}


	/*****************************get and set************************************************/

	public CompanyUserServiceInter getCompanyUserServiceInter() {
		return companyUserServiceInter;
	}


	public void setCompanyUserServiceInter(
			CompanyUserServiceInter companyUserServiceInter) {
		this.companyUserServiceInter = companyUserServiceInter;
	}


	public CompanyServiceInter getCompanyServiceInter() {
		return companyServiceInter;
	}


	public void setCompanyServiceInter(CompanyServiceInter companyServiceInter) {
		this.companyServiceInter = companyServiceInter;
	}


	public CompanyUser getCompanyUser() {
		return companyUser;
	}


	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}



	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNewPassword() {
		return newPassword;
	}



	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

	
	/*****************************get and set************************************************/
	
	
	
}
