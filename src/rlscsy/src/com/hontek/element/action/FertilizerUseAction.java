package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.FertilizerUse;
import com.hontek.element.service.inter.FertilizerUseServiceInter;
/**
 * 肥料投入品使用记录信息表
 * @author lzk
 *
 */
public class FertilizerUseAction extends BaseAction{
	
	private FertilizerUseServiceInter fertilizerUseService;
	private FertilizerUse fertilizerUse;
	private String ids;
	public FertilizerUse getFertilizerUse() {
		return fertilizerUse;
	}
	public void setFertilizerUse(FertilizerUse fertilizerUse) {
		this.fertilizerUse = fertilizerUse;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setFertilizerUseService(
			FertilizerUseServiceInter fertilizerUseService) {
		this.fertilizerUseService = fertilizerUseService;
	}
	
	public void findList(){
		jsonMsg = fertilizerUseService.findList(fertilizerUse,page,rows,sort,order);
		printJsonString(jsonMsg);
	}
	
	public void addFertilizerUse(){
		jsonMsg = fertilizerUseService.addFertilizerUse(fertilizerUse);
		printJsonString(jsonMsg);
	}
	
	public void updateFertilizerUse(){
		jsonMsg = fertilizerUseService.updateFertilizerUse(fertilizerUse);
		printJsonString(jsonMsg);
	}
	
	public void deleteFertilizerUse(){
		jsonMsg = fertilizerUseService.deleteFertilizerUse(ids);
		printJsonString(jsonMsg);
	}
	
}
