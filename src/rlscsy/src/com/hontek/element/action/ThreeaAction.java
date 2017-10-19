package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbThreea;
import com.hontek.element.service.inter.ThreeaServiceInter;
/**
 * <p>Title: 三品一标信息</p>
 * <p>Description: 三品一标信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ThreeaAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ThreeaServiceInter threeaServiceInter;
	private TbThreea threea;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbThreea getThreea() {
		return threea;
	}

	public void setThreea(TbThreea trace) {
		this.threea = trace;
	}


	public void setThreeaServiceInter(ThreeaServiceInter threeaServiceInter) {
		this.threeaServiceInter = threeaServiceInter;
	}

	/**
	 * 查找三品一标信息
	 */
	public void findThreeaList(){
		jsonMsg = threeaServiceInter.findThreeaList(threea, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加三品一标
	 */
	public void addThreea(){
		jsonMsg = threeaServiceInter.addThreea(threea);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改三品一标
	 */
	public void updateThreea(){
		jsonMsg = threeaServiceInter.updateThreea(threea);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除三品一标
	 */
	public void deleteThreea(){
		jsonMsg = threeaServiceInter.deleteThreea(ids);
		printJsonString(jsonMsg);
	}
}
