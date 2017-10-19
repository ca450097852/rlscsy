package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.Xiujianchucao;
import com.hontek.element.service.inter.XiujianchucaoServiceInter;

public class XiujianchucaoAction extends BaseAction{
	
	private XiujianchucaoServiceInter XiujianchucaoService;
	
	private Xiujianchucao xiujianchucao;
	
	private String ids;

	public void setXiujianchucaoService(
			XiujianchucaoServiceInter xiujianchucaoService) {
		XiujianchucaoService = xiujianchucaoService;
	}

	public Xiujianchucao getXiujianchucao() {
		return xiujianchucao;
	}

	public void setXiujianchucao(Xiujianchucao xiujianchucao) {
		this.xiujianchucao = xiujianchucao;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void findList(){
		jsonMsg = XiujianchucaoService.findList(xiujianchucao,page,rows,sort,order);
		printJsonString(jsonMsg);
	}
	
	public void addXiujianchucao(){
		jsonMsg = XiujianchucaoService.addXiujianchucao(xiujianchucao);
		printJsonString(jsonMsg);
	}
	
	public void updateXiujianchucao(){
		jsonMsg = XiujianchucaoService.updateXiujianchucao(xiujianchucao);
		printJsonString(jsonMsg);
	}
	
	public void deleteXiujianchucao(){
		jsonMsg = XiujianchucaoService.deleteXiujianchucao(ids);
		printJsonString(jsonMsg);
	}
}
