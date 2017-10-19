package com.hontek.record.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.record.pojo.TbMassif;
import com.hontek.record.service.inter.MassifServiceInter;
/**
 * 地块信息ACTION
 * @author
 *
 */
public class MassifAction extends BaseAction{
	
	private MassifServiceInter massifService;
	private TbMassif massif;
	private String ids;
	private String ptaId;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbMassif getMassif() {
		return massif;
	}

	public void setMassif(TbMassif massif) {
		this.massif = massif;
	}

	public String getPtaId() {
		return ptaId;
	}

	public void setPtaId(String ptaId) {
		this.ptaId = ptaId;
	}

	public void setMassifService(
			MassifServiceInter massifService) {
		this.massifService = massifService;
	}
	
	public void addMassif(){
		jsonMsg = String.valueOf(massifService.addMassif(massif,getLoginTsUser(),getRequest()));
		printJsonString(jsonMsg);
	}

	public void findMassifList(){	
		jsonMsg = massifService.findMassif(ptaId,  page, rows,  order,  sort);
		printJsonString(jsonMsg);
	}
	
	
	public void updateMassif(){
		printJsonString(massifService.updateMassif(massif));

	}
	
	public void deleteMassif(){
		printJsonString(massifService.deleteMassif(ids));

	}
}
