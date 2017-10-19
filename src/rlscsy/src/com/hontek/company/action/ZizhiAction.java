package com.hontek.company.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.company.pojo.Zizhi;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.pojo.ZizhiType;
import com.hontek.company.service.inter.ZizhiServiceInter;

/**
 * <p>Title: 资质证书表</p>
 * <p>Description: 资质证书Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ZizhiServiceInter zizhiServiceInter;	
	private Zizhi zizhi;
	private ZizhiType zizhiType;
	private String ids;	
	
	private String entCode;
	private String appType;
	
	private List<ZizhiAppendix> appendixList;
	
	
	public void setZizhiType(ZizhiType zizhiType) {
		this.zizhiType = zizhiType;
	}

	public ZizhiType getZizhiType() {
		return zizhiType;
	}

	public List<ZizhiAppendix> getAppendixList() {
		return appendixList;
	}

	public void setAppendixList(List<ZizhiAppendix> appendixList) {
		this.appendixList = appendixList;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public Zizhi getZizhi() {
		return zizhi;
	}

	public void setZizhi(Zizhi zizhi) {
		this.zizhi = zizhi;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setZizhiServiceInter(ZizhiServiceInter zizhiServiceInter) {
		this.zizhiServiceInter = zizhiServiceInter;
	}

	/**
	 * 添加资质证书
	 */
	public void addZizhi(){
		jsonMsg  = zizhiServiceInter.addZizhi(zizhi);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 后台添加资质证书
	 */
	public void addZizhiAndAppend(){
		CompanyUser user = getLoginCompanyUser();
		int comId = 0;
		if(user!=null){
			comId = user.getComId();
		}
		zizhi.setEntId(comId);
		printJsonString(zizhiServiceInter.addZizhiAndAppend(zizhi,appendixList,zizhiType));
		
	}
	
	/**
	 * app添加资质证书
	 */
	public void appzizhiAdd(){
		printJsonString(zizhiServiceInter.addZizhiAndAppend(zizhi,appendixList,zizhiType));
		
	}
	
	/**
	 * 修改资质证书
	 */
	public void updateZizhi(){
		jsonMsg  = zizhiServiceInter.updateZizhi(zizhi);
		printJsonString(jsonMsg);
	}
	
	public void updateZizhiAndAppend(){
		jsonMsg  = zizhiServiceInter.updateZizhiAndAppend(zizhi,appendixList,zizhiType);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除资质证书
	 */
	public void deleteZizhi(){
		jsonMsg  = zizhiServiceInter.deleteZizhi(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询资质证书
	 */
	public void findZizhiPagerList(){
		jsonMsg  = zizhiServiceInter.findZizhiPagerList(zizhi, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	public void findZizhiList(){
		CompanyUser user = getLoginCompanyUser();
		int comId = 0;
		if(user!=null){
			comId = user.getComId();
		}
		jsonMsg  = zizhiServiceInter.findZizhiList(comId);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 判断当前登录用户有没有上传营业执照
	 */
	public void isCreateZizhi(){
		CompanyUser user = getLoginCompanyUser();
		int comId = 0;
		if(user!=null){
			comId = user.getComId();
		}
		printJsonString(zizhiServiceInter.isCreateZizhi(comId,2));
	}
	/**
	 * 查找资质附件----手机接口
	 */
	public void findZizhiPagerListforMobile(){
		printJsonString(zizhiServiceInter.findZizhiPagerListforMobile(entCode,appType,page,rows));
	}
}
