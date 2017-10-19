package com.hontek.review.action;
import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbPrevention;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.PreventionServiceInter;
/**
 * @ClassName: PlantRaiseAction
 * @Description: TODO(防疫表、植保表)
 * @date 2014-11-19 下午06:52:20
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PreventionAction extends BaseAction {
	private PreventionServiceInter preventionService;
	private TbPrevention prevention;
	private String ids;
	private List<TbTraceAppdix> traceAppList;

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public TbPrevention getPrevention() {
		return prevention;
	}
	public void setPrevention(TbPrevention prevention) {
		this.prevention = prevention;
	}
	public List<TbTraceAppdix> getTraceAppList() {
		return traceAppList;
	}
	public void setTraceAppList(List<TbTraceAppdix> traceAppList) {
		this.traceAppList = traceAppList;
	}
	public void setPreventionService(PreventionServiceInter preventionService) {
		this.preventionService = preventionService;
	}
	
	
	public void findPreventionList(){
		jsonMsg = "";
		if(prevention==null){
			this.prevention = new TbPrevention();
		}
		jsonMsg = preventionService.findPreventionList(prevention,page,rows,order,sort);
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
		jsonMsg = preventionService.findPreventionList(prevention,page,rows);
		printJsonString(jsonMsg);
	}
	public void addPrevention(){
		jsonMsg = preventionService.addPrevention(prevention,traceAppList);
		printJsonString(jsonMsg);
	}
	public void updatePrevention(){
		jsonMsg = preventionService.updatePrevention(prevention,traceAppList);
		printJsonString(jsonMsg);
	}
	public void deletePrevention(){
		jsonMsg = preventionService.deletePrevention(ids);
		printJsonString(jsonMsg);
	}
}
