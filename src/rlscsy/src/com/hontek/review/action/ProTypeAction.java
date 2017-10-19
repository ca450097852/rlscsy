package com.hontek.review.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProType;
import com.hontek.review.pojo.TreeVo;
import com.hontek.review.service.inter.ProTypeServiceInter;
import com.hontek.sys.pojo.TsUser;
/**
 * <p>Title: 产品分类</p>
 * <p>Description: 产品分类ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProTypeAction extends BaseAction{
	
	private Logger logger = Logger.getLogger(ProTypeAction.class);
	
	private ProTypeServiceInter proTypeService;
	
	private TbProType proType;
	
	private String ids;
	private String state;
	private String typeClass;
	private String typeName;
	
	private int entId;
	
	public int getEntId() {
		return entId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public TbProType getProType() {
		return proType;
	}
	public void setProType(TbProType proType) {
		this.proType = proType;
	}

	public void setProTypeService(ProTypeServiceInter proTypeService) {
		this.proTypeService = proTypeService;
	}
	public void findProTypeList(){
		Map mapCondition = new HashMap();
		
		Pager pager = proTypeService.findProTypeList(mapCondition,this.page,this.rows);
		
		this.jsonMsg = this.createEasyUiJson(pager);
		
		this.printJsonString(jsonMsg);
		
	}
	
	public void addProType(){
		jsonMsg = "添加失败!";
		try {
			//Long entId = this.isAdmin()?0:this.getEnterprse().getEntId();
			
			proType.setEntId(entId);
			
			proTypeService.addProType(proType);
			
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加分类出错："+e.getMessage());
		}
		this.printJsonString(jsonMsg);
	}
	
	
	/**
	 * 多个分类一起添加，多个分类使用@分割
	 */
	public void addProType2(){
		jsonMsg = "添加失败!";
		try {
			proTypeService.addProType2(proType);
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加分类出错："+e.getMessage());
		}
		this.printJsonString(jsonMsg);
	}
	
	public void updateProType(){
		jsonMsg = "修改失败!";
		try{
			proTypeService.updateProType(proType);
			jsonMsg = "修改成功!";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改分类出错："+e.getMessage());
		}
		this.printJsonString(jsonMsg);
	}
	
	public void updateProTypeState(){
		jsonMsg = "操作失败!";
		try {
			proTypeService.updateProTypeState(ids,state);
			jsonMsg = "操作成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新分类状态出错："+e.getMessage());
		}
		this.printJsonString(jsonMsg);
	}
	
	public void getProTypeTree(){
		jsonMsg = proTypeService.getProTypeTree(0L);
		this.printJsonString(jsonMsg);
	}
	
	public void getProTypeTreeToPro(){
		jsonMsg = proTypeService.getProTypeTreeToPro(0L);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 根据分类名搜索，有子节点的不显示
	 */
	public void getProTypeTreeByName(){
		jsonMsg = proTypeService.getProTypeTreeByName(typeName);
		this.printJsonString(jsonMsg);
	}
	
	public void getProTypeTreeToProbyType(){
		jsonMsg = proTypeService.getProTypeTreeToProbyType(0L,typeClass);
		this.printJsonString(jsonMsg);
	}
	/**
	 * 门户企业注册，选择产品分类
	 */
	public void getProTypeTreeForPortal(){
		jsonMsg = proTypeService.getProTypeTreeForPortal(0L);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 获取企业产品分类tree
	 */
	public void getEntTypeTree(){
		TsUser user = this.getLoginTsUser();
		if(user!=null){
			List<TreeVo> treeList = proTypeService.getEntTypeTree(user.getEntId());
			jsonMsg = this.getJSON(treeList);
			printJsonString(jsonMsg);
		}
	}
	
	public void getList(){
		List<TbProType> list = proTypeService.getList();
		jsonMsg = this.getJSON(list);
		printJsonString(jsonMsg);
	}
	
}
