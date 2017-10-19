package com.hontek.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsRolePurv;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EntExpandServiceInter;
import com.hontek.sys.service.inter.EntStyleServiceInter;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
import com.hontek.sys.service.inter.RolePurvServiceInter;
import com.hontek.sys.service.inter.UserServiceInter;

/**
 * <p>Title: 用户表</p>
 * <p>Description: 用户Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserServiceInter userService;
	private EnterpriseServiceInter enterprseService;
	private RolePurvServiceInter rolePurvServiceInter;
	private EntExpandServiceInter entExpandService;
	private EntStyleServiceInter entStyleService;
	private String account;
	private String accountentId;
	private TsUser user ;
	private String ids;
	private String roleids;
	private List<TsRolePurv> cols;
	private String msg;//错误信息
	private String noticeUrl;
	private String password;
	private String newPassword;
	private Map<String, Integer> mapConut;
	/**
	 * 读取用户列表
	 */
	public void findUserList(){
		LoginUser loginUser = this.getLoginUser();	
		if(user==null){
			user = new TsUser();
		}
		if(!isAdmin()){
			int entId = loginUser.getEntId();	
			if ("".equals(user.getEntId())||0==user.getEntId())
				user.setEntId(entId);
		}	
		user.setUserId(getLoginUserId());
		jsonMsg = userService.findUserList(user,page, rows);		
		printJsonString(jsonMsg);
	}

	
	/**
	 * 验证帐户是否唯一
	 */
	public void findIsUniqueAccount(){
		jsonMsg = userService.findIsUniqueAccount(account,accountentId);
		printJsonString(jsonMsg);
	}
	/**
	 * 验证url是否唯一
	 */
	public void findIsUniqueNoticeUrl(){
		jsonMsg = userService.findIsUniqueNoticeUrl(noticeUrl);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 根据机构查询用户进行下拉
	 */
	public void findUserListByEntId(){
		TsUser tsUser = new TsUser();
		tsUser.setEntId(Integer.valueOf(accountentId));
		jsonMsg = userService.findUserList(tsUser);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 统计次数
	 */
	public void getCountMap(){
		Integer mainbodyId = null;
		if(!"Y".equalsIgnoreCase(this.getLoginTsUser().getAdmin())){
			Object obj = this.getSession().getAttribute("companyStyle");
			if(obj!=null){
				EntStyle entStyle = (EntStyle)obj;
				mainbodyId = entStyle.getEntId();
			}
		}
		mapConut = userService.getCountMap(mainbodyId);
		printJsonString(getJSON(mapConut));
	}

	/**
	 * 登录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String login() throws AppException{
		String info="login";
		if(getSession()==null){
			return "login";
		}
		Object obj = getSession().getAttribute("loginUser");
		LoginUser loginUser = null;
		
		Integer mainbodyId = null;
		Object obj_aid = this.getSession().getAttribute("companyStyle");
		if(obj_aid!=null){
			EntStyle entStyle = (EntStyle)obj_aid;
			mainbodyId = entStyle.getEntId();
		}
		
		if(obj==null){
			try {
				if(user==null){
					return "login";
				}
				String userName = user.getUserName();
				/**
				 * 机构用户登陆
				 */					
				/*String entName = user.getEntName();
				TsEnterprise enterprise = null;
				if(entName!=null&&!"".equals(entName)){
					enterprise = enterprseService.findEnterpriseByAccount(entName);
				}
				if(entName!=null&&!"".equals(entName)&&enterprise==null){
					msg = "登录失败，机构账号错误，请重新输入！";
					info = "login";
				}else{*/
					/**
					 * 超级管理员登陆
					 */
					if(userName!=null&&userName.equalsIgnoreCase("administrator")){
						mainbodyId = null;
						loginUser = userService.loginByInfo(user.getUserName(),user.getPassword(),null);	
						if(loginUser!=null){											
							getSession().setAttribute("loginUser", loginUser);							
							TsUser tsUser = userService.getUserById(loginUser.getUserId());
							getSession().setAttribute("tsUser", tsUser);																			
							this.addSysLog("用户登陆", GlobalValueManager.LOGIN);
							info = "loginSuccess";
							cols = rolePurvServiceInter.findLoginUserCol("");							
							getSession().setAttribute("isAdmin", true);		
							
						}else{
							msg = "登录失败，用户名或密码错误，请重新输入！";
							info = "login";
						}
					}else{
						//普通用户登录
						loginUser = userService.loginByInfo(user.getUserName(),user.getPassword(),"1");
						
						if(loginUser!=null){						
							
							TsEnterprise enterprise = enterprseService.getEnterPirseByEntId(loginUser.getEntId());
							
							EntStyle compentStyle = null; 
							Object styObj = this.getSession().getAttribute("companyStyle");
							//获取风格
							if(styObj!=null){
								compentStyle = (EntStyle)styObj;
								EntStyle entStyle = entStyleService.getEntStyleByEntId(compentStyle.getEntId());
								getSession().setAttribute("entStyle", entStyle);
							}
							
							//主体企业
							if("2".equals(enterprise.getFlag())){
								EntExpand entExpand = null;
								if(compentStyle!=null){
									entExpandService.getEntExpandByEntId(compentStyle.getEntId());
								}
								if(entExpand!=null){
									//判断是否过期
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date now = new Date();
									if("1".equals(entExpand.getIscharge())){//收费用户
										String expired = entExpand.getExpired();
										if(expired!=null && !"".equals(expired)){
											Date expiredDate = sdf.parse(expired);
											if(now.after(expiredDate)){
												info = "login";
												msg = "用户账号已过期，请尽快续费";
												return info;
											}
										}
									}else{
										String ontrialEnd = entExpand.getOntrialEnd();
										if(ontrialEnd!=null && !"".equals(ontrialEnd)){
											Date ontrialEndDate = sdf.parse(ontrialEnd);
											if(now.after(ontrialEndDate)){
												info = "login";
												msg = "该用户账号已过试用期";
												return info;
											}
										}
									}
									
									enterprise.setOntrialStart(entExpand.getOntrialStart());
									enterprise.setOntrialEnd(entExpand.getOntrialEnd());
									enterprise.setIscharge(entExpand.getIscharge());
									enterprise.setExpired(entExpand.getExpired());
									enterprise.setIsmainbody(entExpand.getIsmainbody());
									enterprise.setMbName(entExpand.getMbName());
									enterprise.setMbType(entExpand.getMbType());
									enterprise.setMbDomain(entExpand.getMbDomain());
									enterprise.setCreateTime(entExpand.getCreateTime());
									enterprise.setRemark(entExpand.getRemark());
								}
								
							}
							
							getSession().setAttribute("loginUser", loginUser);
							getSession().setAttribute("enterprse", enterprise);
							
							getSession().setAttribute("isAdmin", false);
							getSession().setAttribute("entId", enterprise.getEntId());
							
							TsUser tsUser = userService.getUserById(loginUser.getUserId());
							getSession().setAttribute("tsUser", tsUser);	
							
							//管理员登录，跳转到管理员后台
							cols = rolePurvServiceInter.findLoginUserCol(loginUser.getUserId());
							
							this.addSysLog("用户登陆", GlobalValueManager.LOGIN);							
							info = "loginSuccess";
																					
						}else{
							msg = "登录失败，用户名或密码错误，请重新输入！";
							info = "login";
						}
					}		
										
			} catch (Exception e) {
				msg = "系统繁忙，请稍后登陆！";
				e.printStackTrace();
				info = "login";
			}
		}else{
			loginUser = (LoginUser)obj;
			if("2".equals(loginUser.getFlag())){//跳转到企业管理后台
				info = "companyLogin";
			}else if(isAdmin()){
				cols = rolePurvServiceInter.findLoginUserCol("");
				info = "loginSuccess";
			}else{
				cols = rolePurvServiceInter.findLoginUserCol(loginUser.getUserId());
				info = "loginSuccess";
			}
			
		}
		mapConut = userService.getCountMap(mainbodyId);
		return info;
	}
	
	/**
	 * 企业登陆
	 * @return
	 * @throws AppException
	 */
	public String companyLogin() throws AppException{
		String info="companyLogin";
		if(getSession()==null){
			return "companyLogin";
		}
		String domain = getRequest().getServerName();
		System.out.println("getServerName()==@=="+domain);
		
		Object obj = getSession().getAttribute("loginUser");
		Object styleObj = getSession().getAttribute("companyStyle");
		
		LoginUser loginUser = null;
		if(obj==null){
			try {
				if(user==null){
					return "companyToLogin";
				}
				String userName = user.getUserName();
				/**
				 * 机构用户登陆									
				String entName = user.getEntName();
				TsEnterprise enterprise = null;
				if(entName!=null&&!"".equals(entName)){
					enterprise = enterprseService.findEnterpriseByAccount(entName);
				}
				if(entName!=null&&!"".equals(entName)&&enterprise==null){
					msg = "登录失败，机构账号错误，请重新输入！";
					info = "companyToLogin";
				}else{*/
				/*String entIds = null;//根据域名获取entId范围；
				if(domain!=null&&!domain.equals("127.0.0.1")){
					entIds = enterprseService.getEntIdsByDomain(domain);//根据域名获取entId范围；
				}*/
				//普通用户登录
				loginUser = userService.loginByInfo(user.getUserName(),user.getPassword(),"2");
				if(loginUser!=null){								
					
					TsEnterprise enterprise = enterprseService.getEnterPirseByEntId(loginUser.getEntId());//企业信息
					EntExpand entExpand = entExpandService.getEntExpandByEntId(loginUser.getEntId());//企业拓展表信息				
					EntExpand mainEntExpand = entExpandService.getEntExpandByEntId(enterprise.getBodyEntId());//主体拓展表信息
					
					//System.out.println("mainEntExpand.getMbDomain()==@=="+(mainEntExpand==null?"":mainEntExpand.getMbDomain()));
					//if(mainEntExpand!=null&&mainEntExpand.getMbDomain().equals(domain)){
						//判断改企业用户当前域名是否属于该主体
						
						if(entExpand!=null){
							enterprise.setOntrialStart(entExpand.getOntrialStart());
							enterprise.setOntrialEnd(entExpand.getOntrialEnd());
							enterprise.setIscharge(entExpand.getIscharge());
							enterprise.setExpired(entExpand.getExpired());
							enterprise.setIsmainbody(entExpand.getIsmainbody());
							enterprise.setMbName(entExpand.getMbName());
							enterprise.setMbType(entExpand.getMbType());
							enterprise.setMbDomain(entExpand.getMbDomain());
							enterprise.setCreateTime(entExpand.getCreateTime());
							enterprise.setRemark(entExpand.getRemark());
						}
						
						getSession().setAttribute("loginUser", loginUser);
						getSession().setAttribute("enterprse", enterprise);
						
						getSession().setAttribute("isAdmin", false);
						getSession().setAttribute("entId", enterprise.getEntId());
						
						TsUser tsUser = userService.getUserById(loginUser.getUserId());
						getSession().setAttribute("tsUser", tsUser);
						
						//获取会员后台风格
						EntStyle companyStyle = entStyleService.getEntStyleByType(enterprise.getEntId(),"2");//获取当前主体的风格
						if(companyStyle==null){//当前主体的风格为空，则查找上级主体风格
							companyStyle = entStyleService.getEntStyleByType(enterprise.getParentId(),"2");
						}
						getSession().setAttribute("companyStyle", companyStyle);
						
						info = "companyLogin";	
					/*}else{
						msg = "登录失败，用户名或密码错误，请重新输入！";
						info = "companyToLogin";
					}*/
					
					
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
	
	
	
	
	
	//退出登陆
	public String logout(){
		this.addSysLog("退出登陆", GlobalValueManager.EXIT);
		
		getSession().setAttribute("loginUser", null);
		getSession().setAttribute("enterprse", null);
		getSession().setAttribute("tsUser", null);
		getSession().setAttribute("isAdmin", null);
		getSession().removeAttribute("loginUser");
		getSession().removeAttribute("enterprse");
		getSession().removeAttribute("tsUser");
		getSession().removeAttribute("isAdmin");
		Object obj = getSession().getAttribute("systemName");
		this.getSession().invalidate();
		ServletActionContext.getRequest ().getSession (true).setAttribute("systemName", obj);
		return "login";
	}
	
	/**
	 * 添加新用户成功返回success失败返回error
	 * @return
	 */
	public void addUser(){		
		String regDate = DateUtil.formatDateTime();
		user.setRegDate(regDate);	
		jsonMsg = userService.addUser(user);	    
		printJsonString(jsonMsg);
	}
	
	/**
	 * 根据ID删除用户，可以删除多个
	 */
	public void deleteUsers(){
		jsonMsg = userService.deleteUser(ids);	
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 跟新用户信息
	 */
	public void updateUser(){	
		jsonMsg = userService.updateUser(user);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改当前用户信息
	 */
	public void updateOwnerUser(){
		LoginUser loginuser = this.getLoginUser();
		
		user.setUserName(loginuser.getUserName());
		user.setUserId(loginuser.getUserId());
		user.setEntId(loginuser.getEntId());
		user.setAdmin(loginuser.getAdmin());
		user.setRegDate(loginuser.getRegDate());
		user.setSts(loginuser.getSts());
		user.setFlag(loginuser.getFlag());
		
		jsonMsg = userService.updateUser(user);
		//更新session中的登陆信息
		LoginUser tmp = userService.loginByInfo(user.getUserName(),user.getPassword(),String.valueOf(user.getEntId()));
		getSession().setAttribute("loginUser", tmp);

		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改状态
	 */
	public void changeSts(){
		this.printJsonString(userService.updateUserSts(ids,user.getSts()));
	}
	/**
	 * 获取当前登陆用户的信息
	 */
	public void getLoginUserInfo(){
		this.printJsonString(userService.getLoginUserInfo(this.getLoginUserId()));
	}
	/**
	 * 修改当前登陆用户密码
	 */
	public void updateLoginUserPass(){
		this.printJsonString(userService.updateLoginUserPass(this.getLoginUserId(),password,newPassword));
	}
	
	//*******************SET AND GET

	public UserServiceInter getUserService() {
		return userService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public TsUser getUser() {
		return user;
	}

	public void setUser(TsUser user) {
		this.user = user;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public EnterpriseServiceInter getEnterprseService() {
		return enterprseService;
	}
	public void setEnterprseService(EnterpriseServiceInter enterprseService) {
		this.enterprseService = enterprseService;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<TsRolePurv> getCols() {
		return cols;
	}
	public void setCols(List<TsRolePurv> cols) {
		this.cols = cols;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAccountentId() {
		return accountentId;
	}

	public void setAccountentId(String accountentId) {
		this.accountentId = accountentId;
	}

	public void setRolePurvServiceInter(RolePurvServiceInter rolePurvServiceInter) {
		this.rolePurvServiceInter = rolePurvServiceInter;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
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


	public Map<String, Integer> getMapConut() {
		return mapConut;
	}


	public void setMapConut(Map<String, Integer> mapConut) {
		this.mapConut = mapConut;
	}


	public void setEntExpandService(EntExpandServiceInter entExpandService) {
		this.entExpandService = entExpandService;
	}


	public void setEntStyleService(EntStyleServiceInter entStyleService) {
		this.entStyleService = entStyleService;
	}

}
