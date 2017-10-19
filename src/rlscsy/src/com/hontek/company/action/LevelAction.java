package com.hontek.company.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Level;
import com.hontek.company.service.inter.LevelServiceInter;
/**
 * <p>Title: 级别表</p>
 * <p>Description: 级别action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class LevelAction extends BaseAction{
	private LevelServiceInter levelService;
	private Level level;
	
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public void setLevelService(LevelServiceInter levelService) {
		this.levelService = levelService;
	}
	
	public void findLevel(){
		Pager<Level> pager = levelService.findLevel(level,page,rows,sort,order);
		jsonMsg = createEasyUiJson(pager);
		printJsonString(jsonMsg);
	}
	
	public void getLevelListByType(){
		List<Level> list = levelService.getLevelListByType(ids);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 添加级别
	 */
	public void addLevel(){
		jsonMsg = levelService.addLevel(level);
		printJsonString(jsonMsg);
	}
	/**
	 * 修改级别
	 */
	public void updateLevel(){
		jsonMsg = levelService.updateLevel(level);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除
	 */
	public void deleteLevels(){
		jsonMsg = levelService.deleteLevels(ids);
		printJsonString(jsonMsg);
	}
	
	
}
