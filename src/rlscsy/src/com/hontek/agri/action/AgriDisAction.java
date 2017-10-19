package com.hontek.agri.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.agri.pojo.TbAgriDis;
import com.hontek.agri.service.inter.AgriDisServiceInter;
/**
 * <p>Title: 禁用投入品</p>
 * <p>Description: 禁用投入品Action实现类</p>
 * @author qql
 * @version 1.0
 */
public class AgriDisAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgriDisServiceInter agriDisService;
	private TbAgriDis agriDis;
	private String ids;
	
	


	public TbAgriDis getAgriDis() {
		return agriDis;
	}


	public void setAgriDis(TbAgriDis agriDis) {
		this.agriDis = agriDis;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}



	public void setAgriDisService(AgriDisServiceInter agriDisService) {
		this.agriDisService = agriDisService;
	}


	/**
	 * 查找禁用投入品
	 */
	public void findAgriDisList(){
		jsonMsg = agriDisService.findAgriDisList(agriDis, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 添加禁用投入品
	 */
	public void addAgriDis(){
		jsonMsg = agriDisService.addAgriDis(agriDis);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改禁用投入品
	 */
	public void updateAgriDis(){
		jsonMsg = agriDisService.updateAgriDis(agriDis);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除禁用投入品
	 */
	public void deleteAgriDis(){
		jsonMsg = agriDisService.deleteAgriDis(ids);
		printJsonString(jsonMsg);
	}
}
