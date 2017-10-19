package com.hontek.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.ColDao;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsSysCol;
import com.hontek.sys.service.inter.ColServiceInter;
/**
 * <p>Title: 栏目菜单表</p>
 * <p>Description: 栏目菜单service 实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ColServiceImpl extends BaseServiceImpl implements ColServiceInter{
	private ColDao colDao;

	public void setColDao(ColDao colDao) {
		this.colDao = colDao;
	}
	//日志管理
	Logger logger = Logger.getLogger(ColServiceImpl.class);
	
	/**
	 * 查询栏目
	 */
	public String findColList(TsSysCol col) {
		String jsonMsg ="";
		try {
			Pager<TsSysCol> pager = colDao.findColList("");
			jsonMsg = super.createEasyUiJson(pager);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "查询栏目菜单出现错误："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	/**
	 * 添加栏目
	 * @param col
	 * @return
	 */
	public String addSysCol(TsSysCol col) {
		String jsonMsg = "新增栏目成功";
		try{
			col.setId(getTableSequence());
			colDao.addCol(col);
		}catch(Exception e){
			e.printStackTrace();
			jsonMsg = "新增栏目失败："+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;	
	}

	/**
	 * 修改栏目
	 */
	public String updateSysCol(TsSysCol col) {
		String jsonMsg = "修改栏目成功";
		try{
			colDao.updateCol(col);
		}catch(Exception e){
			e.printStackTrace();
			jsonMsg = "修改栏目失败："+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;	
	}

	/**
	 * 删除栏目
	 */
	public String deleteSysCol(String ids) {
		String jsonMsg = "删除栏目成功";
		try {
			colDao.deleteCol(ids);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除栏目失败："+e.getLocalizedMessage();
			logger.error(jsonMsg);
		}
		return jsonMsg;
	}

	/**
	 * 查询栏目
	 */
	public String findFirstColList() {
		List<TsSysCol> list = colDao.findFirstColList();
		TsSysCol syscol = new TsSysCol();
		syscol.setId(0);
		syscol.setColName("无父栏目");
		List<TsSysCol> cols = new ArrayList<TsSysCol>();
		cols.add(syscol);
		cols.addAll(list);
		String jsonstr = getJSON(cols);
		return jsonstr;
	}
	
	/**
	 * 查询栏目
	 * @return
	 */
	public String getSysColTree() {		 
		List<TreeVo> list = colDao.getSysColTree(0);		
		TreeVo syscol = new TreeVo();
		syscol.setId(0);
		syscol.setText("无父栏目");
		List<TreeVo> cols = new ArrayList<TreeVo>();
		cols.add(syscol);
		cols.addAll(list);		
		String jsonstr = getJSON(cols);
		return jsonstr;
	}
	
	/**
	 * 获取序列号
	 * @return
	 */
	public int getTableSequence() {
		return colDao.getTableSequence("TS_SYS_COL");
	}

}
