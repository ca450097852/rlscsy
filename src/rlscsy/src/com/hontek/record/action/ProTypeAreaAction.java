package com.hontek.record.action;


import com.hontek.comm.action.BaseAction;
import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.record.service.inter.ProTypeAreaServiceInter;
/**
 * 分类二维码信息ACTION
 * @author lzk
 *
 */
public class ProTypeAreaAction extends BaseAction{
	private ProTypeAreaServiceInter proTypeAreaService;
	private TbProTypeArea proTypeArea;
	private String ids;
	private String recId;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbProTypeArea getProTypeArea() {
		return proTypeArea;
	}

	public void setProTypeArea(TbProTypeArea proTypeArea) {
		this.proTypeArea = proTypeArea;
	}

	private int ptqId;
	
	public void setPtqId(int ptqId) {
		this.ptqId = ptqId;
	}


	public String getRecId() {
		return recId;
	}


	public void setRecId(String recId) {
		this.recId = recId;
	}


	public void setProTypeAreaService(
			ProTypeAreaServiceInter proTypeAreaService) {
		this.proTypeAreaService = proTypeAreaService;
	}
	
	
	
	public void addProTypeArea(){
		jsonMsg = String.valueOf(proTypeAreaService.addProTypeArea(proTypeArea,getLoginTsUser(),getRequest()));
		printJsonString(jsonMsg);
	}

	public void findProTypeAreaList(){
		int entId = getLoginUserEntId();
		if(proTypeArea!=null&&proTypeArea.getEntId()>0){
			entId = proTypeArea.getEntId();
		}		
		jsonMsg = proTypeAreaService.findProTypeArea(entId,  page, rows,  order,  sort);
		printJsonString(jsonMsg);
	}
	
	
	public void updateProTypeArea(){
		printJsonString(proTypeAreaService.updateProTypeArea(proTypeArea));

	}
	
	public void deleteProTypeArea(){
		printJsonString(proTypeAreaService.deleteProTypeArea(ids));

	}
}
