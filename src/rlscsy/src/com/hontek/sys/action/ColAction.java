package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.sys.pojo.TsSysCol;
import com.hontek.sys.service.inter.ColServiceInter;
/**
 * <p>Title: 栏目菜单表</p>
 * <p>Description: 栏目菜单Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ColAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ColServiceInter colService;
	private TsSysCol col;
	private String ids;
	
	/**
	 * 查询栏目
	 */
	public void findColList(){
		jsonMsg = colService.findColList(col);		
		printJsonString(jsonMsg);		
	}
	
	/**
	 * 添加新栏目菜单
	 */
	public void addCol(){
		jsonMsg = colService.addSysCol(col);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改新栏目菜单
	 */
	public void updateCol(){
		jsonMsg = colService.updateSysCol(col);
		printJsonString(jsonMsg);
		
	}
	
	/**
	 * 删除新栏目菜单
	 */
	public void deleteCol(){
		jsonMsg = colService.deleteSysCol(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询栏目
	 */
	public void findFirstColList(){
		jsonMsg = colService.findFirstColList();
		printJsonString(jsonMsg);
	}	
	
	/**
	 * 获得树菜单
	 * @return
	 * @throws AppException
	 */
	public void getSysColTree(){
		jsonMsg = colService.getSysColTree();
		printJsonString(jsonMsg);
	}

	public void setColService(ColServiceInter colService) {
		this.colService = colService;
	}


	public TsSysCol getCol() {
		return col;
	}


	public void setCol(TsSysCol col) {
		this.col = col;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
