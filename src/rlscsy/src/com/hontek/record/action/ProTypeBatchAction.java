package com.hontek.record.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.service.inter.ProTypeBatchServiceInter;
/**
 * 批次信息ACTION
 * @author 
 *
 */
public class ProTypeBatchAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProTypeBatchServiceInter proTypeBatchService;
	private TbProTypeBatch proTypeBatch;
	private String ids;	
	
	private String dimenno;
	
	private int ptbId;
	
	public String getIds() {
		return ids;
	}

	public int getPtbId() {
		return ptbId;
	}

	public void setPtbId(int ptbId) {
		this.ptbId = ptbId;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbProTypeBatch getProTypeBatch() {
		return proTypeBatch;
	}

	public void setProTypeBatch(TbProTypeBatch proTypeBatch) {
		this.proTypeBatch = proTypeBatch;
	}


	public void setProTypeBatchService(ProTypeBatchServiceInter proTypeBatchService) {
		this.proTypeBatchService = proTypeBatchService;
	}
	
	
	
	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public void addProTypeBatch(){
		jsonMsg = String.valueOf(proTypeBatchService.addProTypeBatch(proTypeBatch,getLoginTsUser(),getRequest()));
		printJsonString(jsonMsg);
	}

	public void findProTypeBatch(){	
		jsonMsg = proTypeBatchService.findProTypeBatch(proTypeBatch,  page, rows,  order,  sort);
		printJsonString(jsonMsg);
	}
	
	//批发节点根据交易凭证号找上级节点
	public void findPtBIdByProTypeBatch(){	
		jsonMsg = proTypeBatchService.findPtBIdByProTypeBatch(ptbId);
		printJsonString(jsonMsg);
	}
	
	//零售节点根据交易凭证号找上级节点
	public void findPtBIdByProOut(){	
		jsonMsg = proTypeBatchService.findPtBIdByProOut(ptbId);
		printJsonString(jsonMsg);
	}
	
	//根据批次id找批次二维码
	public void findDimenNoByPtBId(){	
		jsonMsg = proTypeBatchService.findDimenNoByPtBId(ptbId);
		printJsonString(jsonMsg);
	}
	
	public void getCombobox(){
		jsonMsg = proTypeBatchService.getCombobox(ids);
		printJsonString(jsonMsg);
	}
	
	public void findProTypeBatchList(){	
		jsonMsg = getJSON(proTypeBatchService.findProTypeBatchList(proTypeBatch));
		printJsonString(jsonMsg);
	}
	
	public void updateProTypeBatch(){
		printJsonString(proTypeBatchService.updateProTypeBatch(proTypeBatch));

	}
	
	public void updateProTypeBatchState(){
		printJsonString(proTypeBatchService.updateProTypeBatchState(proTypeBatch));

	}
	
	public void deleteProTypeBatch(){
		printJsonString(proTypeBatchService.deleteProTypeBatch(ids));

	}
	
	public void updateBatchState(){
		String msg = proTypeBatchService.updateBatchState(ptbId);
		printJsonString(msg);
	}
	
	public void getProTypeBatchByDimenno(){	
		jsonMsg = getJSON(proTypeBatchService.getProTypeBatchByDimenno(dimenno));
		printJsonString(jsonMsg);
	}
	
	public void getProTypeBatchConut(){	
		jsonMsg = proTypeBatchService.getProTypeBatchConut(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 根据分类二维码id查找批次列表
	 */
	public void findProTypeBatchByPtqId(){	
		jsonMsg = getJSON(proTypeBatchService.findProTypeBatchByPtqId(proTypeBatch.getPtqId()));
		printJsonString(jsonMsg);
	}
}
