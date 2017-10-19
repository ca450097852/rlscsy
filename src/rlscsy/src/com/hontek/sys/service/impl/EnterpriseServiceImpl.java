package com.hontek.sys.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ctc.wstx.io.EBCDICCodec;
import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.GlobalValueManager;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.DrawDesigns;
import com.hontek.comm.util.PDFUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AuditCtrl;
import com.hontek.company.service.inter.AuditCtrlServiceInter;
import com.hontek.record.dao.ProTypeAreaDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.record.service.inter.RecordServiceInter;
import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.pojo.TbProType;
import com.hontek.sys.dao.EntExpandDao;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.ComboboxData;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TagStyle;
import com.hontek.sys.pojo.TbSysconfig;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsRole;
import com.hontek.sys.pojo.TsRoleUser;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
import com.hontek.sys.service.inter.RolePurvServiceInter;
import com.hontek.sys.service.inter.RoleServiceInter;
import com.hontek.sys.service.inter.SysconfigServiceInter;
import com.hontek.sys.service.inter.TagStyleServiceInter;
import com.hontek.sys.service.inter.UserServiceInter;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
/**
 * <p>Title: 组织机构表</p>
 * <p>Description: 组织机构Service 接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EnterpriseServiceImpl extends BaseServiceImpl implements EnterpriseServiceInter {

	private EnterpriseDao enterpriseDao;
	private RoleServiceInter roleService;
	private UserServiceInter userService;
	private EntExpandDao entExpandDao;
	
	private RolePurvServiceInter rolePurvServiceInter;
	
	private ProTypeDao proTypeDao;
	private ProTypeAreaDao proTypeAreaDao;
	
	private AuditCtrlServiceInter auditCtrlServiceInter;
	
	private SysconfigServiceInter sysconfigService;
	
	private RecordServiceInter recordService;//档案
	
	private TagStyleServiceInter tagStyleService;//二维码风格

	public ProTypeDao getProTypeDao() {
		return proTypeDao;
	}

	public void setEntExpandDao(EntExpandDao entExpandDao) {
		this.entExpandDao = entExpandDao;
	}

	public void setProTypeAreaDao(ProTypeAreaDao proTypeAreaDao) {
		this.proTypeAreaDao = proTypeAreaDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}

	private static final ResourceBundle bundle = ResourceBundle.getBundle("dimenno");
	

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}
	
	public void setRoleService(RoleServiceInter roleService) {
		this.roleService = roleService;
	}

	public void setUserService(UserServiceInter userService) {
		this.userService = userService;
	}

	public void setRolePurvServiceInter(RolePurvServiceInter rolePurvServiceInter) {
		this.rolePurvServiceInter = rolePurvServiceInter;
	}	

	public void setAuditCtrlServiceInter(AuditCtrlServiceInter auditCtrlServiceInter) {
		this.auditCtrlServiceInter = auditCtrlServiceInter;
	}

	public void setSysconfigService(SysconfigServiceInter sysconfigService) {
		this.sysconfigService = sysconfigService;
	}

	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}

	public void setTagStyleService(TagStyleServiceInter tagStyleService) {
		this.tagStyleService = tagStyleService;
	}

	private Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 查询机构
	 */
	public String findEnterpriseList(TsEnterprise enterprise, int page, int rows){
		String jsonMsg = "";
		try {
			Pager<TsEnterprise> pager = enterpriseDao.findEnterpriseList(enterprise,page,rows);
			jsonMsg = createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查询组织机构错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	/**
	 * 门户查询企业
	 */
	public String findList(TsEnterprise enterprise, int page, int rows){
		String jsonMsg = "";
		try {
			Pager<TsEnterprise> pager = enterpriseDao.findPortalCompanyList(enterprise,page,rows);
			jsonMsg = createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查询企业错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	/**
	 * 查询企业信息列表
	 * @param enterprise
	 * @param page
	 * @param rows
	 * @return
	 */
	public String findCompanyList(TsEnterprise enterprise, int page, int rows){
		String jsonMsg = "";
		try {
			Pager<TsEnterprise> pager = enterpriseDao.findCompanyList(enterprise,page,rows);
			jsonMsg = createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查询企业错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;	
	}
	
	
	/**
	 * 查询待审核企业
	 */
	public String findWaitAuditCompanyList(TsEnterprise enterprise,LoginUser loginUser, int page, int rows){
		String jsonMsg = "";
		try {
			String entId = String.valueOf(loginUser.getEntId());
			int entLevel = enterpriseDao.getEntLevel(entId);
			/*if(entLevel==2){
				enterprise.setProvinceRsts(GlobalValueManager.RSTS_DS);
			}else */
			if(entLevel==3){
				enterprise.setCityRsts(GlobalValueManager.RSTS_DS);
			}else if(entLevel==4){
				enterprise.setAreaRsts(GlobalValueManager.RSTS_DS);
			}/*else if(entLevel==5){
				enterprise.setTownRsts(GlobalValueManager.RSTS_DS);
			}*/
			//企业标识
			enterprise.setFlag("3");
			
			Pager<TsEnterprise> pager = enterpriseDao.findCompanyList(enterprise,page,rows);
			jsonMsg = createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查询企业错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;	
	
	}
	
	
	/**
	 * 根据组织账号查询组织
	 */
	public TsEnterprise findEnterpriseByAccount(String account) {
		TsEnterprise enterprise =null;
    	try {
    		 enterprise = enterpriseDao.findEnterpriseByAccount(account);   		
		} catch (Exception e) {
			logger.error("根据账号【"+account+"】查询组织机构出错：" + e.getMessage());
		}		
		return enterprise; 
	}

	/**
	 * 添加组织机构
	 */
	public String addEnterprise(TsEnterprise enterprise,String loginUserId){
		String jsonString = "";
		try {
			System.out.println("从页面传过来的上级ID是："+enterprise.getParentId());
			
			enterprise.setEntId(getTableSequence());
			enterprise.setCrtDt(DateUtil.formatDateTime());
			enterprise.setCrtUserId(loginUserId);
						
			enterpriseDao.addEnterprise(enterprise);		
			
			jsonString = "保存成功!";						
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="添加组织机构错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	

	/**
	 * 添加企业信息
	 */
	public String addCompany(TsEnterprise enterprise,String loginUserId){
		String jsonString = "";
		try {		
			enterprise.setEntId(getTableSequence());
			enterprise.setCrtDt(DateUtil.formatDateTime());
			enterprise.setCrtUserId(loginUserId);			
			
			int areaId = enterprise.getAreaId();
			
			TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
			String areaCode = tsEnterpriseArae.getEntCode();
			
			int seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");
			String entCode = areaCode+ String.valueOf(100000+seq+1).substring(1);
			enterprise.setEntCode(entCode);
			
			enterpriseDao.addEnterprise(enterprise);					
			jsonString = String.valueOf(enterprise.getEntId());						
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="添加企业信息错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	
	
	/**
	 * 开通账号
	 * 创建机构、角色、用户
	 * 分配角色、权限
	 */
	public String addEntAndUser(TsEnterprise enterprise,String loginUserId){
		String jsonString = "";
		try {

			enterprise.setEntId(getTableSequence());
			enterpriseDao.addEnterprise(enterprise);
			
			jsonString = "账号开通成功!";

			String addUser = enterprise.getAddUser();
			int entid = enterprise.getEntId();
			String account = enterprise.getAccount();
			
			if(addUser!=null&&!addUser.equals("")){
				//添加管理员
				if(addUser.contains("0")){
					//添加角色
					TsRole role = new TsRole(entid, "机构管理员", "", 2, "1");					
					roleService.addRole(role);
					//添加用户
					TsUser tsUser = new TsUser(null, entid, account, "gd0000", "机构管理员", String.valueOf(loginUserId), DateUtil.formatDateTime(),"1");
					
					userService.addUser(tsUser);
										
					
					TsRoleUser roleuser = new TsRoleUser(tsUser.getUserId(), role.getRoleId());					
					roleService.addRoleUser(roleuser);
					//授权
					rolePurvServiceInter.addRolePurv(role.getRoleId(),entid,0);
					
					jsonString += "机构帐号为："+account+"，用户："+account+"，密码：gd0000";
				}

				if(addUser.contains("1")){
					//添加角色
					TsRole role = new TsRole(entid, "普通用户", "", 2, "1");					
					roleService.addRole(role);
					//添加用户
					TsUser tsUser = new TsUser(null, enterprise.getEntId(), account, "gd0000", "普通用户", String.valueOf(loginUserId), DateUtil.formatDateTime(),"1");

					userService.addUser(tsUser);
					
					
					TsRoleUser roleuser = new TsRoleUser(tsUser.getUserId(), role.getRoleId());					
					roleService.addRoleUser(roleuser);
					//授权
					rolePurvServiceInter.addRolePurv(role.getRoleId(),entid, 1);
					
					jsonString += "机构帐号为："+account+"，用户："+account+"，密码：gd0000";
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="开通组织机构账号错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	
	/**
	 * 添加主体
	 */
	public String addMainBody(TsEnterprise enterprise,String loginUserId){
		String jsonString = "";
		try {

			enterprise.setEntId(getTableSequence());
			enterpriseDao.addEnterprise(enterprise);
			
			//添加主体信息
			EntExpand entExpand = new EntExpand();
			
			entExpand.setOntrialStart(enterprise.getOntrialStart());
			entExpand.setOntrialEnd(enterprise.getOntrialEnd());
			entExpand.setIscharge(enterprise.getIscharge());
			entExpand.setExpired(enterprise.getExpired());
			entExpand.setIsmainbody("1");//设置为主体 
			entExpand.setMbName(enterprise.getMbName());
			entExpand.setMbType(enterprise.getMbType());
			entExpand.setMbDomain(enterprise.getMbDomain());
			
			entExpand.setEntId(enterprise.getEntId());
			entExpand.setUserId(loginUserId);
			entExpand.setCreateTime(DateUtil.formatDateTime());
			
			entExpand.setShowCode(enterprise.getShowCode());
			entExpand.setValidCode(enterprise.getValidCode());
			
			entExpand.setAuditPro(enterprise.getAuditPro());
			entExpand.setAuditBatch(enterprise.getAuditBatch());
			
			entExpandDao.save(entExpand);
			
			jsonString = "账号开通成功!";

			String addUser = enterprise.getAddUser();
			int entid = enterprise.getEntId();
			String account = enterprise.getAccount();
			
			if(addUser!=null&&!addUser.equals("")){
				//添加管理员
				if(addUser.contains("0")){
					//添加角色
					TsRole role = new TsRole(entid, "机构管理员", "", 2, "1");					
					roleService.addRole(role);
					//添加用户
					TsUser tsUser = new TsUser(null, entid, account, "gd0000", "机构管理员", String.valueOf(loginUserId), DateUtil.formatDateTime(),"1");
					
					userService.addUser(tsUser);
										
					
					TsRoleUser roleuser = new TsRoleUser(tsUser.getUserId(), role.getRoleId());					
					roleService.addRoleUser(roleuser);
					//授权
					rolePurvServiceInter.addRolePurv(role.getRoleId(),entid,0);
					
					jsonString += "机构帐号为："+account+"，用户："+account+"，密码：gd0000";
					
				}

				if(addUser.contains("1")){
					//添加角色
					TsRole role = new TsRole(entid, "普通用户", "", 2, "1");					
					roleService.addRole(role);
					//添加用户
					TsUser tsUser = new TsUser(null, enterprise.getEntId(), account, "gd0000", "普通用户", String.valueOf(loginUserId), DateUtil.formatDateTime(),"1");

					userService.addUser(tsUser);
					
					
					TsRoleUser roleuser = new TsRoleUser(tsUser.getUserId(), role.getRoleId());					
					roleService.addRoleUser(roleuser);
					//授权
					rolePurvServiceInter.addRolePurv(role.getRoleId(),entid, 1);
					
					jsonString += "机构帐号为："+account+"，用户："+account+"，密码：gd0000";
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="开通组织机构账号错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	

	/**
	 * 开通企业账号
	 * 创建机构、角色、用户
	 * 分配角色、权限
	 */
	public String addCompanyAccount(TsEnterprise enterprise,LoginUser loginUser){
		String jsonString = "";
		try {
			String ctrtime = DateUtil.formatDateTime();

			int areaId = enterprise.getAreaId();
			
			TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
			String areaCode = tsEnterpriseArae.getEntCode();
			
//			int seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");
//			String entCode = areaCode+ String.valueOf(100000+seq+1).substring(1);
			int seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
			String entCode = areaCode+ String.valueOf(1000000+seq).substring(1);
			enterprise.setEntCode(entCode);
			
			enterprise.setEntId(getTableSequence());
			enterprise.setCrtDt(ctrtime);
			enterprise.setCrtUserId(loginUser.getUserId());
			enterpriseDao.addEnterprise(enterprise);
			
			int entid = enterprise.getEntId();
			String account = enterprise.getAccount();
			//添加企业审核控制
			String entName = enterprise.getName();
			String auditEnt = enterprise.getAuditEnt();
			String ctrEnt = String.valueOf(loginUser.getEntId());
			String ctrUser = loginUser.getUserName();
			if(auditEnt!=null&&auditEnt.contains(",")){
				auditEnt = auditEnt.replaceAll(" ", "");

			}
			AuditCtrl auditCtrl = new AuditCtrl(entName, entid, auditEnt, ctrEnt, ctrUser, ctrtime, "1");
			auditCtrlServiceInter.addAuditCtrl(auditCtrl);
			
			//添加角色
			//TsRole role = new TsRole(entid, "企业用户", "企业用户角色", 3, "1");					
			//roleService.addRole(role);
			//添加用户
			TsUser tsUser = new TsUser(null, enterprise.getEntId(), account, "gd0000", "企业用户", loginUser.getUserId(), DateUtil.formatDateTime(),"2");

			userService.addUser(tsUser);
			
			
			//TsRoleUser roleuser = new TsRoleUser(tsUser.getUserId(), role.getRoleId());					
			//roleService.addRoleUser(roleuser);
			//授权
			//rolePurvServiceInter.addRolePurv(role.getRoleId(),entid, 1);
			
			jsonString += "企业帐号为："+account+"，用户："+account+"，密码：gd0000";
				
									
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="开通企业账号错误："+e.getMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}
	
	/**
	 * 企业注册
	 * 创建机构、用户
	 */
	public String addCompany2(TsEnterprise enterprise,HttpServletRequest request){
		String jsonString = "";
		try {
			//验证是否已注册；
			String yanzhen_name = enterprise.getName();
			String yanzhen_account = enterprise.getAccount();
			
			boolean fname = enterpriseDao.findNameIsUnique(yanzhen_name);
			boolean faccount = enterpriseDao.findAccountIsUnique(yanzhen_account);
			if(!fname&&!faccount){
			
			String ctrtime = DateUtil.formatDateTime();
			//获取区域编码
			int areaId = enterprise.getAreaId();
			
			TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
			String areaCode = tsEnterpriseArae.getEntCode();
			
//			int seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");
			int seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
			String entCode = areaCode+ String.valueOf(1000000+seq).substring(1);//生成企业编码		
			enterprise.setEntCode(entCode);
			
			//所属主体
			String mbDomain = request.getServerName();//注册页面的域名		
			EntExpand entExpand = entExpandDao.getEntExpandByMbDomain(mbDomain);
			if(entExpand!=null){
				enterprise.setBodyEntId(entExpand.getEntId());
			}
			
			//根据区域指定，所属机构
			String parentId = getEntManagerId(areaId+""); 
			int pid=entExpand.getEntId();
			if(null!=parentId&&!"".equals(parentId)){
				pid = Integer.valueOf(parentId);
			}
			enterprise.setParentId(pid);

			//指定审核机构
			enterprise = getAuditEnterprise(enterprise,String.valueOf(pid));
			enterprise.setFlag("3");
			enterprise.setEntId(getTableSequence());
			enterprise.setCrtDt(ctrtime);
			enterprise.setCrtUserId("0");
			
			if(enterprise.getAuditEnt()==null||enterprise.getAuditEnt().equals("")){
				enterprise.setCompanyRsts("4");
			}
			enterpriseDao.addEnterprise(enterprise);//添加企业（insert）
			
			int entid = enterprise.getEntId();
			String account = enterprise.getAccount();
			//添加企业审核控制
			String entName = enterprise.getName();
			String auditEnt = enterprise.getAuditEnt();
			String ctrEnt = String.valueOf(0);
			String ctrUser = "administrator";
			if(auditEnt!=null&&auditEnt.contains(",")){
				auditEnt = auditEnt.replaceAll(" ", "");

			}
			if(auditEnt!=null&&!auditEnt.equals("")){
				AuditCtrl auditCtrl = new AuditCtrl(entName, entid, auditEnt, ctrEnt, ctrUser, ctrtime, "1");
				auditCtrlServiceInter.addAuditCtrl(auditCtrl);//添加审核控制（insert）
			}
			
			//添加用户
			String password = enterprise.getPassword();
			TsUser tsUser = new TsUser(null, enterprise.getEntId(), account, password, "企业用户", "0", DateUtil.formatDateTime(),"2");
			userService.addUser(tsUser);//添加用户（insert）
			
			
			jsonString = "请牢记您的登录帐号："+account+"，密码："+password;
			
			
//			//获取档案要素参照
//			String recordType = enterprise.getRecordType();//档案类型
//			recordType = (Integer.parseInt(recordType)+2)+"";//分类属性type_class:1(种植类)；2（畜牧类）		
			//生成企业-产品分类-二维码
//			String proType = enterprise.getProType();//产品分类
						
			TbRecord record = new TbRecord();
			record.setRecName("主体档案");
			record.setCrttime(DateUtil.formatDateTime());
			record.setSeq(5);
			record.setRecSts("1");
			
			recordService.addRecord("1", null, tsUser, record, request);
			
			}else{
				jsonString="-2";//该企业已被注册；
			}
									
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="-1";
			logger.error("注册企业账号错误："+e.getMessage());
			
		}
		return jsonString;
	}
	
	/**
	 * 添加同步企业
	 */
	public Map<String, String> addSynchCompany(TsEnterprise enterprise, HttpServletRequest request) {
		String jsonString = "";
		String flag = "1";
		try {
			//验证是否已注册；
			String yanzhen_name = enterprise.getName();
			
			boolean fname = enterpriseDao.findNameIsUnique(yanzhen_name);
			if(!fname){
				
				String ctrtime = DateUtil.formatDateTime();
				//获取区域编码
				int areaId = enterprise.getAreaId();
				
				enterprise.setEntId(enterpriseDao.getTableSequence("TS_ENTERPRISE"));
				
				TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
				String areaCode = tsEnterpriseArae.getEntCode();
				
				int seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
				String entCode = areaCode+ String.valueOf(1000000+seq).substring(1);//生成企业编码
				
				enterprise.setEntCode(entCode);
				
				enterprise.setCompanyRsts("4");
				
				enterpriseDao.addEnterprise(enterprise);//添加企业（insert）
				flag = "0";
				/*
				//指定审核机构
				enterprise = getAuditEnterprise(enterprise,String.valueOf(enterprise.getParentId()));
				
				
				if(enterprise.getAuditEnt()==null||enterprise.getAuditEnt().equals("")){
					enterprise.setCompanyRsts("4");
				}
				enterpriseDao.addEnterprise(enterprise);//添加企业（insert）
				
				int entid = enterprise.getEntId();
				String account = enterprise.getAccount();
				//添加企业审核控制
				String entName = enterprise.getName();
				String auditEnt = enterprise.getAuditEnt();
				String ctrEnt = String.valueOf(0);
				String ctrUser = "administrator";
				if(auditEnt!=null&&auditEnt.contains(",")){
					auditEnt = auditEnt.replaceAll(" ", "");
	
				}
				if(auditEnt!=null&&!auditEnt.equals("")){
					AuditCtrl auditCtrl = new AuditCtrl(entName, entid, auditEnt, ctrEnt, ctrUser, ctrtime, "1");
					auditCtrlServiceInter.addAuditCtrl(auditCtrl);//添加审核控制（insert）
				}
				*/
				/*TbRecord record = new TbRecord();
				record.setRecName("主体档案");
				record.setCrttime(DateUtil.formatDateTime());
				record.setSeq(5);
				record.setRecSts("1");
				
				recordService.addRecord("1", null, tsUser, record, request);
				*/
			}else{
				jsonString="该企业名称已存在";//该企业已被注册；
			}
									
		} catch (Exception e) {
			e.printStackTrace();
			jsonString="同步该企业失败，请稍候重试或是联系管理员";
			logger.error("注册企业账号错误："+e.getMessage());
			
		}
		
		Map<String, String> rest = new HashMap<String, String>();
		rest.put("flag", flag);
		rest.put("msg", jsonString);
		return rest;
	}
	
	
	/**
	 * 修改组织机构
	 */
	public String updateEnterprise(TsEnterprise enterprise){
		String jsonString = "修改成功";
		try {
			enterprise.setCrtDt(DateUtil.formatDateTime());
			enterpriseDao.updateEnterprise(enterprise);
		} catch (AppException e) {
			e.printStackTrace();
			jsonString="修改出现错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	
	/**
	 * 删除机构
	 */
	public String deleteEnterprise(String entId) {
		String jsonString = "";
		try {
			String[] entIds = entId.split(",");
			int w = 0;
			for (int i = 0; i < entIds.length; i++) {
				TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(Integer.valueOf(entIds[i]));
				if(enterprise!=null){
					enterpriseDao.deleteEnterprise(enterprise);
					w++;
				}				
			}
			jsonString = "操作成功,共删除" + w + "记录!";
			
			//删除企业扩展信息表
			entExpandDao.deleteByIds(entId);
			
			
		} catch (Exception e) {
			jsonString = "删除失败："+e.getMessage();
			logger.error(jsonString);
		}
		return jsonString;
	}

	/**
	 * 查询机构下拉
	 */
	public String getEnterpriseToSelect() {
		String jsonString = "";
		try {
			List<TsEnterprise> list = enterpriseDao.getEnterpriseToSelect();
			List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
			for (TsEnterprise et : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", et.getEntId());
				map.put("text", et.getName());
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
	 * 查询机构树
	 * @param user
	 * @param isAdmin
	 * @return
	 */
	public String getEntTree(LoginUser user,boolean isAdmin) {		
		int id = 0;
		List list = null;
		if(!isAdmin){
			TsEnterprise tsEnterprise = enterpriseDao.getEnterPirseByEntId(user.getEntId());
			id = tsEnterprise.getParentId();
			String flag = tsEnterprise.getFlag();
			if("1".equals(flag)){
				 list = enterpriseDao.getEntTree(id,user.getEntId());
			}else{
				 list = enterpriseDao.getEntRow(user.getEntId());
			}			
		}else{
			 list = enterpriseDao.getEntTree(0,0);
		}
		return getJSON(list);
	}
	

	/**
	 * 查询指定机构树
	 * @param user
	 * @param isAdmin
	 * @return
	 */
	public String getAuditEntTree(String entId) {		
		List<TreeVo> list = enterpriseDao.getParentEntList(Integer.valueOf(entId));	
		StringBuffer buffer = new StringBuffer("");
		//<input id="ck4" name="enterprise.auditEnt" type="checkbox" value="4">省级
		int size = list.size();
		for (int i = 0; i < size; i++) {
			TreeVo treeVo = list.get(i);
			int id = treeVo.getId();
			String entName = treeVo.getText();
			if(i==0){
				buffer.append("<input id='ck"+i+"' name='enterprise.auditEnt' checked='checked' onclick='return false;' type='checkbox' value='"+id+"'>").append(entName);
			}else{
				buffer.append("<input id='ck"+i+"' name='enterprise.auditEnt' checked='checked' onclick='checkFlag("+size+","+i+")' type='checkbox' value='"+id+"'>").append(entName);
			}
		}
		return buffer.toString();
	}
	
	
	/**
	 * 企业注册；指定上级审核机构
	 * @param entId
	 * @return
	 */
	public TsEnterprise getAuditEnterprise(TsEnterprise enterprise,String entId){
		TbSysconfig sysconfig = sysconfigService.getSysconfig2();
		String needareqaudit = null;//NEEDAREQAUDIT:是否需要区县审核;y需要，n不需要；默认n
		String needcityaudit = null;//NEEDCITYAUDIT:是否需要地市审核;y需要，n不需要；默认n
		if(sysconfig!=null){
			needareqaudit = sysconfig.getNeedareqaudit();
			needcityaudit = sysconfig.getNeedcityaudit();
		}
		
		if((needareqaudit==null||needareqaudit.equals("n"))&&(needcityaudit==null||needcityaudit.equals("n"))){
			//不需要审核；
			enterprise.setCompanyRsts("4");//通过
		}else{
			enterprise.setCompanyRsts("1");//待审
			String auditEnt = "";
			String parentId = getEntManagerId(enterprise.getAreaId()+""); //区域的审核机构

			if(needareqaudit!=null&&needareqaudit.equals("y")){		
				if(parentId!=null&&!"".equals(parentId)){
					if(auditEnt.equals("")){
						auditEnt =auditEnt+parentId;
					}else{
						auditEnt =auditEnt+","+parentId;
					}
					enterprise.setAreaRsts("1");//区域审核
				}
			}
			
			if(needcityaudit!=null&&needcityaudit.equals("y")){			
				if(parentId!=null&&!"".equals(parentId)){
					TsEnterprise enterprise2 = enterpriseDao.getEnterPirseByEntId(Integer.valueOf(parentId));
					if(auditEnt.equals("")){
						auditEnt =auditEnt+enterprise2.getParentId();
					}else{
						auditEnt =auditEnt+","+enterprise2.getParentId();
					}
					enterprise.setCityRsts("1");//地市审核

				}else{
					if(auditEnt.equals("")){
						auditEnt =auditEnt+enterprise.getParentId();
					}else{
						auditEnt =auditEnt+","+enterprise.getParentId();
					}
					enterprise.setCityRsts("1");//地市审核
				}
				
				
			}
			
			enterprise.setAuditEnt(auditEnt);

			/*
			List<TreeVo> list = enterpriseDao.getParentEntList(Integer.valueOf(entId));	
			int size = list.size();
			String auditEnt = "";
			if(size==3){//区县级别
				enterprise.setCompanyRsts("1");//待审
				
				if(needareqaudit!=null&&needareqaudit.equals("y")){
					TreeVo treeVo = list.get(0);
					if(auditEnt.equals("")){
						auditEnt =auditEnt+treeVo.getId();
					}else{
						auditEnt =auditEnt+","+treeVo.getId();
					}
					enterprise.setAreaRsts("1");//区域审核
				}
				if(needcityaudit!=null&&needcityaudit.equals("y")){
					TreeVo treeVo = list.get(1);
					if(auditEnt.equals("")){
						auditEnt =auditEnt+treeVo.getId();
					}else{
						auditEnt =auditEnt+","+treeVo.getId();
					}
					enterprise.setCityRsts("1");//地市审核
				}
			}
			if(size==2){//地市级别
				enterprise.setCompanyRsts("1");//待审
				if(needcityaudit!=null&&needcityaudit.equals("y")){
					TreeVo treeVo = list.get(0);
					if(auditEnt.equals("")){
						auditEnt =auditEnt+treeVo.getId();
					}else{
						auditEnt =auditEnt+","+treeVo.getId();
					}
					enterprise.setCityRsts("1");//地市审核
				}
			}
			enterprise.setAuditEnt(auditEnt);
						
		*/}
		
		return enterprise;
	}
	
	
	/**
	 * 企业注册；指定上级审核机构
	 * @param entId
	 * @return
	 */
	public String getAuditTree(String entId){
		StringBuffer buffer = new StringBuffer("");
		TbSysconfig sysconfig = sysconfigService.getSysconfig2();
		String needareqaudit = null;//NEEDAREQAUDIT:是否需要区县审核;y需要，n不需要；默认n
		String needcityaudit = null;//NEEDCITYAUDIT:是否需要地市审核;y需要，n不需要；默认n
		if(sysconfig!=null){
			needareqaudit = sysconfig.getNeedareqaudit();
			needcityaudit = sysconfig.getNeedcityaudit();
		}
		
		if(needareqaudit!=null&&needareqaudit.equals("n")&&needcityaudit!=null&&needcityaudit.equals("n")){
			//不需要审核；
			buffer.append("<input type=\"hidden\" name=\"enterprise.companyRsts\" id=\"companyRsts\" value=\"4\">");//企业状态为4：通过
		}else{
			List<TreeVo> list = enterpriseDao.getParentEntList(Integer.valueOf(entId));	
			int size = list.size();
			if(size==3){//区县级别
				buffer.append("<input type=\"hidden\" name=\"enterprise.companyRsts\" id=\"companyRsts\" value=\"1\">");
				if(needareqaudit!=null&&needareqaudit.equals("y")){
					TreeVo treeVo = list.get(0);
					buffer.append("<input id='ck"+0+"' name='enterprise.auditEnt' checked='checked' onclick='return false;' type='checkbox'  style=\"display: none;\" value='"+treeVo.getId()+"'>");
					buffer.append("<input type=\"hidden\" name=\"enterprise.areaRsts\" id=\"areaRsts\" value=\"1\">");
				}
				if(needcityaudit!=null&&needcityaudit.equals("y")){
					TreeVo treeVo = list.get(1);
					buffer.append("<input id='ck"+1+"' name='enterprise.auditEnt' checked='checked' onclick='return false;' type='checkbox'  style=\"display: none;\" value='"+treeVo.getId()+"'>");
					buffer.append("<input type=\"hidden\" name=\"enterprise.cityRsts\" id=\"cityRsts\" value=\"1\">");
				}
			}
			if(size==2){//地市级别
				buffer.append("<input type=\"hidden\" name=\"enterprise.companyRsts\" id=\"companyRsts\" value=\"1\">");
				if(needcityaudit!=null&&needcityaudit.equals("y")){
					TreeVo treeVo = list.get(0);
					buffer.append("<input id='ck"+0+"' name='enterprise.auditEnt' checked='checked' onclick='return false;' type='checkbox'  style=\"display: none;\" value='"+treeVo.getId()+"'>");
					buffer.append("<input type=\"hidden\" name=\"enterprise.cityRsts\" id=\"cityRsts\" value=\"1\">");
				}
			}
			if(size<2){//省级
				buffer.append("0");
			}
			
		}
		
		return buffer.toString();
	}

	
	/**
	 * 查询区域树
	 * @param user
	 * @param isAdmin
	 * @return
	 */
	public String getEntAreaTree(LoginUser user,boolean isAdmin) {		
		List list = enterpriseDao.getEntAreaTree(0,0);
		String jsonstr = getJSON(list);
		return jsonstr;
	}
	
	public String getEntTreeByAreaId(Integer areaId) {		
		List list = enterpriseDao.getEntAreaTree(areaId,0);
		String jsonstr = getJSON(list);
		return jsonstr;
	}
	
	
	//企业注册，级联下拉
	public String getAreaTree(String parentId){
		List list = enterpriseDao.getAreaTree(parentId);
		String jsonstr = getJSON(list);
		return jsonstr;
	}

	/**
	 * 修改机构状态
	 * @param entId
	 * @param sts
	 * @return
	 */
	public String updateSts(String entId,String sts) {		
		String jsonString = "操作成功!";	
		try {
			System.out.println("获得的值是："+entId + " 状态是："+sts);
			int id = Integer.parseInt(entId);
			TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(id);
			enterprise.setSts(sts);
			enterprise.setCompanyRsts(sts);
			enterpriseDao.updateSts(enterprise);			
		} catch (Exception e) {
			 jsonString = "操作失败!";	
			logger.error(jsonString + e.getMessage());
		}
		return jsonString;
	}

	/**
	 * 查询机构
	 */
	public TsEnterprise getEnterPirseByEntId(int id) {
		TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(id);		
		return enterprise;
	}

	/**
	 * 查询机构
	 */
	public TsEnterprise getEnterprise(TsEnterprise enterprise) {
		TsEnterprise enterprise2 = enterpriseDao.getEnterprise(enterprise);		
		return enterprise2;
	}

	/**
	 * 查询账号是否存在
	 */
	public String findAccountIsUnique(String account){
		boolean f = enterpriseDao.findAccountIsUnique(account);
		return String.valueOf(f);
	}
	
	/**
	 * 查询名称是否存在
	 */
	public String findNameIsUnique(String name){
		boolean f = enterpriseDao.findNameIsUnique(name);
		return String.valueOf(f);
	}
	
	public int getTableSequence() {
		return enterpriseDao.getTableSequence("TS_ENTERPRISE");
	}
	
	/**
	 * 区域查询
	 */
	public String findAreaList(TsEnterprise enterprise, int page, int rows){
		String jsonMsg = "";
		try {
			Pager<TsEnterprise> pager = enterpriseDao.findAreaList(enterprise,page,rows);
			jsonMsg = createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查询区域错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	/**
	 * 添加同步企业
	 * @param entList
	 */
	public List<Map> appendInterEnt(List<TsEnterprise> entList) {
		if(entList!=null&&!entList.isEmpty()){
			 List<Map> mapList = new ArrayList<Map>();
			 				
			int areaId = entList.get(0).getAreaId();			
			TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
			String areaCode = tsEnterpriseArae.getEntCode();
			 
			int seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");
			for(TsEnterprise tse:entList){
				seq++;
				String code = areaCode+ String.valueOf(100000+seq).substring(1);
				Map m = new HashMap();
				m.put("entId",tse.getEntId());
				m.put("entCode", code);
				mapList.add(m);
				tse.setEntId(getTableSequence());
				tse.setEntCode(code);
				enterpriseDao.save(tse);
			}
			return mapList;
		}
		return null;
	}
	
	
	/**
	 * 统计企业数量
	 */
	public String getEntAreaList(TsEnterprise enterprise){
		
		String condtions = "";
		String chartType ="";
		if(enterprise!=null){
			String typeId = enterprise.getTypeId();
			String startDate = enterprise.getStartDate();
			String endDate = enterprise.getEndDate();
			chartType = enterprise.getChartType();

			//条件统计
			if("0".equals(typeId)&&startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,   1);
				ca.add(Calendar.DAY_OF_MONTH,   -1);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH,   11);
				ca.set(Calendar.DATE,   31);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";

			}		
		}	
		int areaId = enterprise.getAreaId()==null?1:enterprise.getAreaId();
		
		List<Map<Object, Object>> oneDimensionsList = enterpriseDao.getEntAreaList(condtions,areaId);	
		
		if(areaId!=1){
			condtions += " and area_id in (select ent_id from ts_enterprise where parent_id = "+areaId+")";
		}
		
		Object count = enterpriseDao.getEntAreaCount(condtions);
		
		//配置统计图的参数
		DrawDesigns d = new DrawDesigns();
		d.setYAxisName("");
		String xAxisName = "总企业数："+count;
		d.setXAxisName(xAxisName);
				
		d.setOneDimensionsList(oneDimensionsList);

		d.setCaption(xAxisName);
		d.setWidth(1100);
		d.setHeight(400);
		String str1 = "";
		str1 += "<tr>";		
		if("".equals(chartType)||"0".equals(chartType)){
			str1 += "<td>" + d.drawColumn3D() + "</td>";	
		}else if("1".equals(chartType)){
			str1 += "<td>" + d.drawLine3D() + "</td>";
		}if("2".equals(chartType)){
			str1 += "<td>" + d.drawPie3D() + "</td>";
		}	
		str1 += "</tr>";			
		return str1;
	}
	
	/**
	 * 统计企业数量
	 */
	public String getEntAreaListTable(TsEnterprise enterprise){
		String title="";
		String condtions = "";
		if(enterprise!=null){
			String typeId = enterprise.getTypeId();
			String startDate = enterprise.getStartDate();
			String endDate = enterprise.getEndDate();
			//条件统计
			if("0".equals(typeId)&&startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				title=("企业统计表("+startDate+" 到 "+endDate+")");
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,   1);
				ca.add(Calendar.DAY_OF_MONTH,   -1);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";
				title=("企业统计表("+(ca.get(Calendar.MONTH)+1)+"月份)");

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH,   11);
				ca.set(Calendar.DATE,   31);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";
				title=("企业统计表("+ca.get(Calendar.YEAR)+"年度)");

			}		
		}	
		
		List<Map<Object, Object>> oneDimensionsList = enterpriseDao.getEntAreaList(condtions);	
		
		Object count = enterpriseDao.getEntAreaCount(condtions);
		if("".equals(title)){
			title=("企业统计表(所有)");
		}
		StringBuffer html = new StringBuffer("");
		html.append("<table border='1px' bordercolor='#07aa94' cellspacing='0px' style='border-collapse:collapse' width='1000'><tr height='30px'><td colspan='22' align='center'><b>"+title+"</b></td></tr>");
		
		StringBuffer row1 = new StringBuffer("");
		row1.append("<tr height='30px'>");
		
		StringBuffer row2 = new StringBuffer("");
		row2.append("<tr height='30px'>");
		
		for (int i = 0; i < oneDimensionsList.size(); i++) {
			Map<Object, Object> map = oneDimensionsList.get(i);
			row1.append("<td align='center'>");
			row1.append(map.get("x"));
			row1.append("</td>");

			row2.append("<td align='center'>");
			row2.append(map.get("y"));
			row2.append("</td>");
		}
		
		row1.append("<td align='center'>合计</td></tr>");

		row2.append("<td align='center'>");
		row2.append(count);
		row2.append("</td>");

		html.append(row1);
		html.append(row2);
		html.append("</table>");

		
		return html.toString();
	}
	
	
	
	public String getEntAreaListTableNew(TsEnterprise enterprise){
		String title="";
		String condtions = "";
		if(enterprise!=null){
			String typeId = enterprise.getTypeId();
			String startDate = enterprise.getStartDate();
			String endDate = enterprise.getEndDate();
			//条件统计
			if("0".equals(typeId)&&startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				title=("企业统计表("+startDate+" 到 "+endDate+")");
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,   1);
				ca.add(Calendar.DAY_OF_MONTH,   -1);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";
				title=("企业统计表("+(ca.get(Calendar.MONTH)+1)+"月份)");

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH,   11);
				ca.set(Calendar.DATE,   31);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.crt_dt >='"+startDate+"' and tc.crt_dt <='"+endDate+"'";
				title=("企业统计表("+ca.get(Calendar.YEAR)+"年度)");

			}		
		}	
		
		List<Map<Object, Object>> oneDimensionsList = enterpriseDao.getEntAreaListNew(condtions,enterprise);	
		
		condtions += " and body_ent_id="+enterprise.getBodyEntId()+" and area_id in (SELECT ent_id from ts_enterprise where parent_id = "+enterprise.getAreaId()+")";
		
		Object count = enterpriseDao.getEntAreaCount(condtions);
		if("".equals(title)){
			title=("企业统计表(所有)");
		}
		StringBuffer html = new StringBuffer("");
		html.append("<table border='1px' bordercolor='#07aa94' cellspacing='0px' style='border-collapse:collapse' width='1000'><tr height='30px'><td colspan='22' align='center'><b>"+title+"</b></td></tr>");
		
		StringBuffer row1 = new StringBuffer("");
		row1.append("<tr height='30px'>");
		
		StringBuffer row2 = new StringBuffer("");
		row2.append("<tr height='30px'>");
		
		for (int i = 0; i < oneDimensionsList.size(); i++) {
			Map<Object, Object> map = oneDimensionsList.get(i);
			row1.append("<td align='center'>");
			row1.append(map.get("x"));
			row1.append("</td>");

			row2.append("<td align='center'>");
			row2.append(map.get("y"));
			row2.append("</td>");
		}
		
		row1.append("<td align='center'>合计</td></tr>");

		row2.append("<td align='center'>");
		row2.append(count);
		row2.append("</td>");

		html.append(row1);
		html.append(row2);
		html.append("</table>");

		
		return html.toString();
	}
	
	
	
	/**
	 * 统计企业数量
	 */
	public String getEntAreaListTableTotal(){

		String sql = "SELECT MAX(total) from ( SELECT (SELECT COUNT(*)  FROM ts_enterprise tc where tc.area_id = t1.ent_id ) as total FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=55) t";
		int total = enterpriseDao.countBySql(sql);
		
		return String.valueOf(total);
	}
	
	/**
	 * 统计企业数量
	 */
	public String getEntAreaListTable(){
		
		List<Map<Object, Object>> oneDimensionsList = enterpriseDao.getEntAreaList("");	
				
		StringBuffer row1 = new StringBuffer("");				
		for (int i = 0; i < oneDimensionsList.size(); i++) {
			Map<Object, Object> map = oneDimensionsList.get(i);
			row1.append("{name: '");
			row1.append(map.get("x"));
			row1.append("',value:");
			row1.append(map.get("y"));
			row1.append("},");
		}
		
		String data = row1.toString();
		if(data.endsWith(",")){
			data = data.substring(0, data.length()-1);
		}		
		return data.toString();
	}
	
	

	public String getEntListJson(String jsonType) {
		List<ComboboxData> list = enterpriseDao.getEntListJson(jsonType);
		return this.getJSON(list);
	}
	/**
	 * 获取当前登陆用户的企业信息
	 */
	public String getLoginEntInfo(int entId) {
		TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, entId);
		if(enterprise!=null){
			TsEnterprise enterprise1 = enterpriseDao.get(TsEnterprise.class, enterprise.getAreaId());
			enterprise.setAreaName(enterprise1.getName());
		}
		
		
		return getJSON(enterprise);
	}
	
	
	/**
	 * 获取当前登陆用户的二维码信息
	 */
	public String getLoginCompanyDimenno(int entId, HttpServletRequest request,boolean flag) {
		
		TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, entId);
		
		TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getBodyEntId());//查找主体的二维码风格设置；

		Map resMap = new HashMap();		
		resMap.put("companyRsts", enterprise.getCompanyRsts());
		resMap.put("dimImg", enterprise.getEntCode()+".png");
		resMap.put("isbatch", enterprise.getIsbatch());
		resMap.put("entId", enterprise.getEntId());

		//没有审核通过
//		if(!"4".equals(enterprise.getCompanyRsts())){
//			return this.getJSON(resMap);
//		}
		//String path = System.getProperty("file.separator") ;
		//String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";
		
		String codelogo = "";
		String toplogo = "";
		String mbDomain = "";//主体域名
		int tsType = 0;
		String tagSize = "";
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";
		String styleimgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"entstyle";
		if(tagStyle!=null){
			codelogo = tagStyle.getCodelogo();
			toplogo = tagStyle.getToplogo();
			tsType = tagStyle.getTsType();
			tagSize = tagStyle.getTagSize();
			mbDomain = tagStyle.getMbDomain()==null?"":"http://"+tagStyle.getMbDomain()+"/lvdunwang/trace.jsp?code=";//主体域名,如：http\://lvdun1.hontek.com.cn/lvdunwang/trace.jsp?code\=
		}				
		//判断是否已经生成二维码图片
		/*File dimennoFile = new File(imgPath+path+enterprise.getEntCode()+".png");
		if(!flag&&dimennoFile.exists()){//二维码图片存在
			return this.getJSON(resMap);
		}*/		
		String url=mbDomain;
		
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =enterprise.getEntCode(); // 二维码		
		
		String proname = enterprise.getName();
		proname=proname==null?"":proname;
							
		String encoderContent =url+code;
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;
		
		String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+path+imgName2;
		/*
		String imgName3 = code+"_3.png";
		String imgPath3 = imgPath+path+imgName3;*/
		        
        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";
        
		try {
			int width = 200;
			int height = width;
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
			
			if(!codelogo.equals("")){//如果主体二维码logo不为空，则用二维码logo
				logo_path = styleimgPath+path+codelogo;				
			}
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);					
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);			
			
			//BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
			//MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
										
		// x.w. 第二步：生成打印二维码 
		// 调用生成的图 类
		CreateImg cg = new CreateImg();
		String def_top_img = "code_top.jpg";
		if("1".equals(tagSize)){
			def_top_img = "code_top_1.jpg";
		}		
		String top_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+def_top_img;		
		
		if(!toplogo.equals("")){//如果二维码login_img不为空，则用二维码login_img
			top_img = styleimgPath+path+toplogo;
		}
		
		List<TbProType> typeList = proTypeDao.findList(TbProType.class, " and typeId in (select typeId from TbProTypeQrcode where entId="+enterprise.getEntId()+") ");
		
		String typeName = "";
		
		for(TbProType type:typeList){
			typeName += ","+type.getTypeName();
		}
		if(!"".equals(typeName)){
			typeName = typeName.substring(1);
			if(typeList.size()>5){
				typeName = typeName.substring(0, typeName.lastIndexOf(",")-1)+("...");
			}
		}else{
			typeName = "无";
		}
		/*//获取区信息
		TsEnterprise areaPo2 = enterpriseDao.getEnterPirseByEntId(enterprise.getAreaId());
		//获取市信息
		TsEnterprise areaPo1 = enterpriseDao.getEnterPirseByEntId(areaPo2.getParentId());
		String areaName = ((areaPo1==null)?"":areaPo1.getName())+((areaPo2==null)?"":areaPo2.getName());*/
		//and chandi is not null
		//List<TbProTypeQrcode> qrcodeList = proTypeQrcodeDao.findList(TbProTypeQrcode.class," and entId="+enterprise.getEntId());
		List<TbProTypeArea> list = proTypeAreaDao.findProTypeAreaList(enterprise.getEntId());
		
		String areaName = "无";
		
		if(list!=null && !list.isEmpty()){
			areaName = list.get(0).getChandi();
			if(list.size()>1){
				areaName = areaName+"(...)";
			}
		}
				
		if(tsType==2){//二维码在左边；
			if("1".equals(tagSize)){
				cg.CreateImg_dimeno_size1(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}else{
				 cg.CreateImg_dimeno(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}
		}else if(tsType==3){//二维码在右边；
			if("1".equals(tagSize)){
				 cg.CreateImg_record_size1(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}else{
				 cg.CreateImg_record(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}			
		}		
		return this.getJSON(resMap);
	}
	
	

	/**
	 * new 2017-01-20
	 * (根据主体配置生产标签)
	 * 获取当前登陆用户的二维码信息
	 */
	public String getCompanyDimenno(int entId, HttpServletRequest request,boolean flag) {
		
		TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, entId);
		
		TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getParentId());//查找主体的二维码风格设置；
		String codelogo = "";
		String toplogo = "";
		String mbDomain = "";//主体域名
		int tsType = 0;
		String tagSize = "";
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";
		String styleimgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"entstyle";
		if(tagStyle!=null){
			codelogo = tagStyle.getCodelogo();
			toplogo = tagStyle.getToplogo();
			tsType = tagStyle.getTsType();
			tagSize = tagStyle.getTagSize();
			mbDomain = tagStyle.getMbDomain()==null?"":"http://"+tagStyle.getMbDomain()+"/lvdunwang/trace.jsp?code=";//主体域名,如：http\://lvdun1.hontek.com.cn/lvdunwang/trace.jsp?code\=
		}
		
		Map resMap = new HashMap();
		resMap.put("companyRsts", enterprise.getCompanyRsts());
		resMap.put("dimImg", enterprise.getEntCode()+".png");
		resMap.put("isbatch", enterprise.getIsbatch());
		resMap.put("entId", enterprise.getEntId());
			

		String url=bundle.getString("url");
		
		String checkUrl = bundle.getString("checkUrl");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =enterprise.getEntCode(); // 二维码		
		
		String proname = enterprise.getName();
		proname=proname==null?"":proname;
							
		String encoderContent =url+code;
		if(!mbDomain.equals("")){//如果主体域名不为空，则使用主体域名
			encoderContent = mbDomain+code;
		}
        
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;
		
		/*String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+path+imgName2;
		
		String imgName3 = code+"_3.png";
		String imgPath3 = imgPath+path+imgName3;*/
		        
        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";//默认的二维码logo
		
		if(!codelogo.equals("")){//如果主体二维码logo不为空，则用二维码logo
			logo_path = styleimgPath+path+codelogo;
		}
        
		try {
			int width = 200;
			int height = width;
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);//生成二维码图片
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);//在二维码图片中加入logo		
			//MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);			
			//BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
			//MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
										
		// x.w. 第二步：生成打印二维码 
		// 调用生成的图 类
		CreateImg cg = new CreateImg();		
		String def_top_img = "code_top.jpg";
		if("1".equals(tagSize)){
			def_top_img = "code_top_1.jpg";
		}		
		String top_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+def_top_img;	
		
		if(!toplogo.equals("")){//如果二维码login_img不为空，则用二维码login_img
			top_img = styleimgPath+path+toplogo;
		}
		
		List<TbProType> typeList = proTypeDao.findList(TbProType.class, " and typeId in (select typeId from TbProTypeQrcode where entId="+enterprise.getEntId()+") ");
		
		String typeName = "";
		
		for(TbProType type:typeList){
			typeName += ","+type.getTypeName();
		}
		if(!"".equals(typeName)){
			typeName = typeName.substring(1);
			if(typeList.size()>5){
				typeName = typeName.substring(0, typeName.lastIndexOf(",")-1)+("...");
			}
		}else{
			typeName = "无";
		}
		/*//获取区信息
		TsEnterprise areaPo2 = enterpriseDao.getEnterPirseByEntId(enterprise.getAreaId());
		//获取市信息
		TsEnterprise areaPo1 = enterpriseDao.getEnterPirseByEntId(areaPo2.getParentId());
		String areaName = ((areaPo1==null)?"":areaPo1.getName())+((areaPo2==null)?"":areaPo2.getName());*/
		//and chandi is not null
		//List<TbProTypeQrcode> qrcodeList = proTypeQrcodeDao.findList(TbProTypeQrcode.class," and entId="+enterprise.getEntId());
		List<TbProTypeArea> list = proTypeAreaDao.findProTypeAreaList(enterprise.getEntId());
		
		String areaName = list.get(0).getChandi();
		if(list.size()>1){
			areaName = areaName+"(...)";
		}
		
		if(tsType==2){//二维码在左边；
			if("1".equals(tagSize)){
				cg.CreateImg_dimeno_size1(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}else{
				 cg.CreateImg_dimeno(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}
		}else if(tsType==3){//二维码在右边；
			if("1".equals(tagSize)){
				 cg.CreateImg_record_size1(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}else{
				 cg.CreateImg_record(top_img,typeName,enterprise.getName(),code,enterprise.getTel(),areaName,imgPath1);
			}			
		}		
		return this.getJSON(resMap);
	}

	/**
	 * 获取机构的等级；
	 */
	public int getEntLevel(String entId) {
		return enterpriseDao.getEntLevel(entId);
	}
	

	/**
	 * 提交审核
	 */
	public String updateEntRsts(int entId,String state) {
		try {
			TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, entId);
			if((enterprise.getRegAddr()==null||"".equals(enterprise.getRegAddr()))
					||(enterprise.getManageAddr()==null||"".equals(enterprise.getManageAddr()))
					||(enterprise.getTel()==null||"".equals(enterprise.getTel()))){
				return "unFull";
			}
			if(enterprise!=null){
				//AuditCtrl auditCtrl = auditCtrlServiceInter.getAuditCtrlByEntId(enterprise.getEntId());
				int entLevel = this.enterpriseDao.getEntLevel(String.valueOf(enterprise.getParentId()));
				
				if(entLevel==5){
					enterprise.setTownRsts("1");
				}else if(entLevel==4){
					enterprise.setAreaRsts("1");
				}else if(entLevel==3){
					enterprise.setCityRsts("1");
				}else if(entLevel==2){
					enterprise.setProvinceRsts("1");
				}
				enterprise.setCompanyRsts(state);
				enterpriseDao.update(enterprise);
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}

	public String getCompanyInfoByEntCode(String entCode) {
		TsEnterprise enterprise = enterpriseDao.get("from TsEnterprise where flag=3 and entCode='"+entCode+"'");
		return this.getJSON(enterprise);
	}
	/**
	 * 根据ENTCODE查找企业信息
	 * @param entCode
	 * @return
	 */
	public TsEnterprise getEnterpriseByEntCode(String entCode) {
		return enterpriseDao.get("from TsEnterprise where entCode='"+entCode+"'");
	}
	
	/**
	 * 生成企业信息PDF文件
	 */
	public String findCompanyInfo(String entId,HttpServletRequest request){
		String flag = "false";
		try {
			TsEnterprise enterprise = enterpriseDao.findCompany(entId);
			if(enterprise!=null){
				String fileName = enterprise.getAccount().concat("-").concat(entId);
				File fileDir = DirectoryUtil.getDirectoryByName(request, "company");
				File file = new File(fileDir,fileName+".pdf");
				if(file.exists()){
					file.delete();
				}
				file.createNewFile();

				PDFUtil pdf =new PDFUtil(file);
				PdfPTable table = pdf.createTable(4);
				//标题
				table.addCell(pdf.createCell("企业信息", PDFUtil.headfont,Element.ALIGN_CENTER,4,false));			
				//第1行
				table.addCell(pdf.createCell("企业名称:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));				
				table.addCell(pdf.createCell("简称:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getSimpleName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));
				//第2行
				table.addCell(pdf.createCell("所属区域:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getAreaName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));				
				table.addCell(pdf.createCell("企业类型:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getTypeName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));
				//第3行
				table.addCell(pdf.createCell("企业法人:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getLegalPerson(), PDFUtil.textfont, Element.ALIGN_LEFT,false));				
				table.addCell(pdf.createCell("组织机构代码:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getOrgCode(), PDFUtil.textfont, Element.ALIGN_LEFT,false));
				//第4行
				table.addCell(pdf.createCell("联系电话:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getTel(), PDFUtil.textfont, Element.ALIGN_LEFT,false));				
				table.addCell(pdf.createCell("邮政编码:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getPostCode(), PDFUtil.textfont, Element.ALIGN_LEFT,false));
				//第5行
				table.addCell(pdf.createCell("企业网址:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getDomName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));				
				table.addCell(pdf.createCell("电子邮箱:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getSimpleName(), PDFUtil.textfont, Element.ALIGN_LEFT,false));
				//第6行
				table.addCell(pdf.createCell("注册地址:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getRegAddr(), PDFUtil.textfont, Element.ALIGN_LEFT,false));	
				table.addCell(pdf.createCell("经营地址:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getManageAddr(), PDFUtil.textfont, Element.ALIGN_LEFT,false));	
				//第7行
				table.addCell(pdf.createCell("企业签名:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getSign(), PDFUtil.textfont, Element.ALIGN_LEFT,3,false));	
				//第8行
				table.addCell(pdf.createCell("企业简介:", PDFUtil.keyfont, Element.ALIGN_RIGHT,false));
				table.addCell(pdf.createCell(enterprise.getIntro(), PDFUtil.textfont, Element.ALIGN_LEFT,3,false));	
								
				pdf.generatePDF(table);							
				flag = "true";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 企业注册；根据区域查找管理机构ID；
	 * @param entId
	 * @return
	 */
	public String getEntManagerId(String entId){
		return this.enterpriseDao.getEntManagerId(entId);
	}

	/**
	 * 获取有档案记录的企业
	 */
	public String getEntHasRecord(TsEnterprise enterprise,int page, int rows) {
		Pager<TsEnterprise> pager = enterpriseDao.getEntHasRecord(enterprise,page, rows);
		
		String jsonMsg = this.createEasyUiJson(pager);
		return jsonMsg;
	}

	
	
	
	
	public void updateCode(){
		
		String hql = "from TsEnterprise where sysCode='086020'";
		
		List<TsEnterprise> enterprises = enterpriseDao.find(hql);
		
		for (TsEnterprise tsEnterprise : enterprises) {
			if(tsEnterprise.getEntCode()!=null&&!"".equals(tsEnterprise.getEntCode())){
				String firstStr = tsEnterprise.getEntCode().substring(0,1);
				if(firstStr.matches("[A-Z]")){
					
					TsEnterprise areaEnterprise = getEnterPirseByEntId(tsEnterprise.getAreaId());
					if(areaEnterprise!=null){
						int seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
						String entCode = areaEnterprise.getEntCode()+ String.valueOf(1000000+seq).substring(1);//生成企业编码		
						tsEnterprise.setEntCode(entCode);
						enterpriseDao.update(tsEnterprise);
					}
					
				}
			}
			
						
		}
		
		
	}

	public List<TsEnterprise> findCompanyList(TsEnterprise enterprise) {
		String condi = "and flag=3 ";
		if(enterprise.getEntId()>0){
			condi += "and entId="+enterprise.getEntId();
		}

		return enterpriseDao.findList(TsEnterprise.class, condi);
	}
	
	/**
	 * app查询企业
	 */
	public Pager<TsEnterprise> findCompanyPagerList(TsEnterprise enterprise, int page, int rows) {
		String condi = "and flag=3 ";
		
		String lng = "113.302027";
		String lat = "23.133796";
		
		if(enterprise!=null){
			if(enterprise.getEntId()>0){
				condi += "and entId="+enterprise.getEntId();
			}
			if(enterprise.getName()!=null&&!enterprise.getName().equals("")){
				condi += "and name like '%"+enterprise.getName()+"%' ";
			}
			
			if(enterprise.getLng()!=null&&!enterprise.getLng().equals("")){
				lng = enterprise.getLng();
			}
			
			if(enterprise.getLat()!=null&&!enterprise.getLat().equals("")){
				lat = enterprise.getLat();
			}
		}
		
		System.out.println("lng@=="+lng);
		System.out.println("lat@=="+lat);
		
		double mapLng = Double.valueOf(lng);
		double mapLat = Double.valueOf(lat);
		
		Pager<TsEnterprise> pager = enterpriseDao.findPager(TsEnterprise.class, condi,page,rows);
		
		List<TbProTypeArea>  proTypeAreas = proTypeAreaDao.find(" from TbProTypeArea ");
		
		List<TsEnterprise> enterprises = pager.getData();
		
		for (TsEnterprise ent : enterprises) {
			int entId = ent.getEntId();
			for (TbProTypeArea tbProTypeArea : proTypeAreas) {
				int ptaEntId = tbProTypeArea.getEntId();
				
				
				
				String ptaLng = tbProTypeArea.getLng();
					   ptaLng = ptaLng==null?"":ptaLng;
					   ptaLng = ptaLng.equals("")?"0":ptaLng;
				String ptaLat = tbProTypeArea.getLat();
					   ptaLat = ptaLat==null?"":ptaLat;
					   ptaLat = ptaLat.equals("")?"0":ptaLat;
				
				System.out.println("@@##ptaLng===="+ptaLng);
				System.out.println("@@##ptaLat===="+ptaLat);
				if(entId==ptaEntId){
					ent.setLng(ptaLng);
					ent.setLat(ptaLat);
					
					if(ptaLng.equals("")||ptaLat.equals("")||ptaLng.equals("0")||ptaLat.equals("0")){
						ent.setDistance("-1");
					}else{
						double mapLng2 = Double.valueOf(ptaLng);
						double mapLat2 = Double.valueOf(ptaLat);			
						double d = Distance(mapLng, mapLat, mapLng2, mapLat2)/1000;		
						DecimalFormat format = new DecimalFormat("0.0"); 
						String distance = format.format(d);
						ent.setDistance(distance);
						//System.out.println("distance===="+distance);
					}
					continue;
				}
				
				
			}
		}
		
		enterprises = sort(enterprises);
		
		pager.setData(enterprises);

		return pager;
	}
	
	
	private static List sort(List data) {
        Collections.sort(data, new Comparator<TsEnterprise>() {
            public int compare(TsEnterprise e1, TsEnterprise e2) {
            	
				BigDecimal a = new BigDecimal(e1.getDistance()==null?"0":e1.getDistance());
			    BigDecimal b = new BigDecimal(e2.getDistance()==null?"0":e2.getDistance());
				
                // 升序
                return a.compareTo(b);
                // 降序
                // return b.compareTo(a);
            }
        });
        
        return data;
    }
	
	
	
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public String updateMainBody(TsEnterprise enterprise) {
		String jsonString = "修改成功";
		try {
			enterpriseDao.updateEnterprise(enterprise);
			
			//添加主体信息
			EntExpand entExpand = new EntExpand();
			
			entExpand.setOntrialStart(enterprise.getOntrialStart());
			entExpand.setOntrialEnd(enterprise.getOntrialEnd());
			entExpand.setIscharge(enterprise.getIscharge());
			entExpand.setExpired(enterprise.getExpired());
			entExpand.setIsmainbody("1");//设置为主体 
			entExpand.setMbName(enterprise.getMbName());
			entExpand.setMbType(enterprise.getMbType());
			entExpand.setMbDomain(enterprise.getMbDomain());
			
			entExpand.setEntId(enterprise.getEntId());
			entExpand.setUserId(enterprise.getUserId());
			entExpand.setCreateTime(enterprise.getCreateTime());
			
			entExpand.setShowCode(enterprise.getShowCode());
			entExpand.setValidCode(enterprise.getValidCode());
			
			entExpand.setAuditPro(enterprise.getAuditPro());
			entExpand.setAuditBatch(enterprise.getAuditBatch());
			
			entExpandDao.update(entExpand);
		} catch (AppException e) {
			e.printStackTrace();
			jsonString="修改出现错误："+e.getLocalizedMessage();
		}
		return jsonString;
	}
	
	
	/**
	 * 根据域名查找当前主体和旗下企业的ids
	 * @param domain
	 * @return
	 */
	public String getEntIdsByDomain(String domain){
		String res = "";
		
		if(domain!=null&&!domain.equals("")){
			EntExpand entExpand = null;
			List<EntExpand> list = entExpandDao.findEntExpandList(domain);
			if(list!=null&&!list.isEmpty()){
				entExpand = list.get(0);
				if(entExpand!=null){
					int entId = entExpand.getEntId();
					
					List<TsEnterprise> entList = enterpriseDao.findList(TsEnterprise.class, " and parentId="+entId);
					if(entList!=null){
						for(TsEnterprise entp :entList){
							res += entp.getEntId()+",";
						}
					}
					res = res+entId;
				}
			}
		}
		
		return res;
	}

	public String findSuperviseEnt(int entid) {
		List<TsEnterprise> list = enterpriseDao.find("from TsEnterprise where (parentId="+entid+" and flag = 2 ) or entId = "+entid+" order by entId ");
		return this.getJSON(list);
	}

	public String updateSuperviseEnt(TsEnterprise enterprise) {
		String msg = "设置成功";
		try {
			String hql = "update TsEnterprise set parentId = "+enterprise.getParentId()+" where entId = "+enterprise.getEntId();
			enterpriseDao.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "设置失败";
		}
		return msg;
	}

	
}
