package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.sys.pojo.TsEntType;
import com.hontek.sys.service.inter.EntTypeServiceInter;

/**
 * <p>Title: 组织机构类别表</p>
 * <p>Description: 组织结构类别Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class EntTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private EntTypeServiceInter entTypeService;
	private TsEntType entType;
	private String List_entTypeId;
	private String typeId;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public EntTypeServiceInter getEntTypeService() {
		return entTypeService;
	}
	public void setEntTypeService(EntTypeServiceInter entTypeService) {
		this.entTypeService = entTypeService;
	}
	public TsEntType getEntType() {
		return entType;
	}
	public void setEntType(TsEntType entType) {
		this.entType = entType;
	}
	
	public String getList_entTypeId() {
		return List_entTypeId;
	}
	public void setList_entTypeId(String listEntTypeId) {
		List_entTypeId = listEntTypeId;
	}
	
	/**
	 * 分页查询所有的组织机构类别
	 */
	public void findEntTypeList() throws AppException{
		printJsonString(entTypeService.findEntTypeList(entType, page, rows)); 
	}
	
	/**
	 * 添加组织机构类型
	 */
	public void addEntType(){		
		printJsonString(entTypeService.addEntType(entType));
	}
	
	/**
	 * 修改组织机构类型
	 */
	public void updateEntType(){
		printJsonString(entTypeService.updateEntType(entType));
	}
	
	/**
	 * 删除一个组织机构
	 */
	public void deleteEntType(){
		printJsonString(entTypeService.deleteEntType(List_entTypeId));
	}
	
	/**
	 * 加载组织机构类型 -- 下拉
	 */
	public void getEntTypeToSelect(){
		printJsonString(entTypeService.getEntTypeToSelect(typeId));
	}
	
	
	/**
	 * 检查机构类型名称是否已经存在
	 * @return
	 */
	public void checkEntTypeIsExist(){
		
		printJsonString(entTypeService.checkEntTypeIsExist(typeId));
	}
}
