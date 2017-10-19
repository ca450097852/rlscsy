package com.hontek.enforce.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawUser;
import com.hontek.enforce.service.inter.LawLogServiceInter;
import com.hontek.enforce.service.inter.LawUserServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法日志action
 * @author cjn
 *
 */
public class LawUserAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LawUser lawUser;
	private LawUserServiceInter lawUserServiceInter;
	private LawLogServiceInter lawLogServiceInter;
	private TsEnterprise enterprise = new TsEnterprise();
	private String ids;
	File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "LawLog");
	/**
	 * 查询
	 */
	public void findList() {
	enterprise.setCrtUserId(ids);
		Pager<LawUser> pager = lawUserServiceInter.findPagerList(lawUser,enterprise, page, rows, sort , order);
		jsonMsg = this.createEasyUiJson(pager);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加
	 */
	public void AddLawUser(){
		 Date d = new Date();  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	     String dateNowStr = sdf.format(d);  
	     lawUser.setCreateTime(dateNowStr); 
	     String msg=lawUserServiceInter.addLawUser(lawUser);
	     printJsonString(msg);
	}
	
	/**
	 * 修改
	 */
	public void updateLawUser(){
		jsonMsg=lawUserServiceInter.updateLawUser(lawUser);
		printJsonString(jsonMsg);
	}
	/**
	 * 删除
	 */
	public void delLawUser(){	
		jsonMsg=lawUserServiceInter.deleteLawUser(ids);
		String uploadifyFileName=lawLogServiceInter.deleteLawLog(ids);
		lawLogServiceInter.delApp(uploadifyFileName, fileDir);
		printJsonString(jsonMsg);    
	}
	
	/**
	 * 构造函数
	 * @return
	 */
	public LawUser getLawUser() {
		return lawUser;
	}


	public void setLawUser(LawUser lawUser) {
		this.lawUser = lawUser;
	}


	public LawUserServiceInter getLawUserServiceInter() {
		return lawUserServiceInter;
	}


	public void setLawUserServiceInter(LawUserServiceInter lawUserServiceInter) {
		this.lawUserServiceInter = lawUserServiceInter;
	}


	public TsEnterprise getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public LawLogServiceInter getLawLogServiceInter() {
		return lawLogServiceInter;
	}
	public void setLawLogServiceInter(LawLogServiceInter lawLogServiceInter) {
		this.lawLogServiceInter = lawLogServiceInter;
	}
	
	
}
