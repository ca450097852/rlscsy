package com.hontek.review.action;
import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbPlantRaise;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.PlantRaiseServiceInter;
/**
 * 
 * @ClassName: PlantRaiseAction
 * @Description: TODO(施肥喂养表)
 * @date 2014-11-19 下午06:52:20
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlantRaiseAction extends BaseAction {
	private PlantRaiseServiceInter plantRaiseService;
	private TbPlantRaise plantRaise;
	private String ids;
	private List<TbTraceAppdix> traceAppList;

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public TbPlantRaise getPlantRaise() {
		return plantRaise;
	}
	public void setPlantRaise(TbPlantRaise plantRaise) {
		this.plantRaise = plantRaise;
	}
	public void setPlantRaiseService(PlantRaiseServiceInter plantRaiseService) {
		this.plantRaiseService = plantRaiseService;
	}
	
	public List<TbTraceAppdix> getTraceAppList() {
		return traceAppList;
	}
	public void setTraceAppList(List<TbTraceAppdix> traceAppList) {
		this.traceAppList = traceAppList;
	}
	
	
	
	
	public void findPlantRaiseList(){
		jsonMsg = "";
		if(plantRaise==null){
			this.plantRaise=new TbPlantRaise();
		}
		jsonMsg = plantRaiseService.findPlantRaiseList(plantRaise,page,rows,order,sort);
		printJsonString(jsonMsg);
	}
	/**
	 * @Title: findList
	 * @Description: TODO(门户调用)
	 * @param  
	 * @return void    返回类型
	 * @throws
	 */
	public void findList(){
		jsonMsg = "";
		if(plantRaise==null){
			this.plantRaise=new TbPlantRaise();
		}
		jsonMsg = plantRaiseService.findPlantRaiseList(plantRaise,page,rows);
		printJsonString(jsonMsg);
	}
	
	public void addPlantRaise(){
		jsonMsg = plantRaiseService.addPlantRaise(plantRaise,traceAppList);
		printJsonString(jsonMsg);
	}
	public void updatePlantRaise(){
		jsonMsg = plantRaiseService.updatePlantRaise(plantRaise,traceAppList);
		printJsonString(jsonMsg);
	}
	public void deletePlantRaise(){
		jsonMsg = plantRaiseService.deletePlantRaise(ids);
		printJsonString(jsonMsg);
	}
}
