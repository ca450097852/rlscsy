package com.hontek.portalweb.action;


import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.element.pojo.DrugUse;
import com.hontek.element.pojo.FeedUse;
import com.hontek.element.pojo.FertilizerUse;
import com.hontek.element.pojo.TbAgriInput;
import com.hontek.element.pojo.TbAgriUse;
import com.hontek.element.pojo.TbCheckinfo;
import com.hontek.element.pojo.TbEartag;
import com.hontek.element.pojo.TbGaininfo;
import com.hontek.element.pojo.TbNodeinfo;
import com.hontek.element.pojo.TbSaleinfo;
import com.hontek.element.pojo.TbThreea;
import com.hontek.element.service.inter.AgriInputServiceInter;
import com.hontek.element.service.inter.AgriUseServiceInter;
import com.hontek.element.service.inter.CheckinfoServiceInter;
import com.hontek.element.service.inter.DrugUseServiceInter;
import com.hontek.element.service.inter.EartagServiceInter;
import com.hontek.element.service.inter.FeedUseServiceInter;
import com.hontek.element.service.inter.FertilizerUseServiceInter;
import com.hontek.element.service.inter.GaininfoServiceInter;
import com.hontek.element.service.inter.NodeinfoServiceInter;
import com.hontek.element.service.inter.SaleinfoServiceInter;
import com.hontek.element.service.inter.ThreeaServiceInter;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbMassif;
import com.hontek.record.pojo.TbRecord;
import com.hontek.record.service.inter.MassifServiceInter;
import com.hontek.record.service.inter.ProTypeAreaServiceInter;
import com.hontek.record.service.inter.RecordServiceInter;
/**
 * <p>Title:档案要素(档案和要素查询入口)</p>
 * <p>Description: 档案要素  Action</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WebElementsAction extends BaseAction {
		
	private TbAgriInput agriInput;		//投入品购买信息
	private AgriInputServiceInter agriInputServiceInter;
	
	private TbAgriUse agriUse;			//投入品使用信息
	private AgriUseServiceInter agriUseServiceInter;
	
	private TbCheckinfo checkinfo;		//检验报告信息
	private CheckinfoServiceInter checkinfoServiceInter;
	
	private TbEartag eartag;			//耳标信息
	private EartagServiceInter eartagServiceInter;
	
	private TbThreea threea;			//三品一标信息
	private ThreeaServiceInter threeaServiceInter;
	
	private TbGaininfo gaininfo;		//采摘收获信息
	private GaininfoServiceInter gaininfoServiceInter;
	
	private TbSaleinfo saleinfo;		//销售信息
	private SaleinfoServiceInter saleinfoServiceInter;
	
	private FertilizerUse fertilizerUse;//肥料使用记录
	private FertilizerUseServiceInter fertilizerUseServiceInter;
	
	private TbNodeinfo nodeinfo;//生产节点信息
	private NodeinfoServiceInter nodeinfoServiceInter;
	
	private DrugUse drugUse;//农业投入品表兽药
	private DrugUseServiceInter drugUseServiceInter;
	
	private FeedUse feedUse;//饲料投入品使用记录
	private FeedUseServiceInter feedUseServiceInter;
	
	
	private TbRecord record;//档案
	private RecordServiceInter recordService;
	private String entId;
	private String dimenno;
	private int recId;
	
	
	
		
	
	/**
	 * 查找投入品购买信息
	 */
	public void findAgriInputList(){
		jsonMsg = agriInputServiceInter.findAgriInputList(agriInput, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找投入品使用信息
	 */
	public void findAgriUseList(){
		jsonMsg = agriUseServiceInter.findAgriUseList(agriUse, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找检验报告信息
	 */
	public void findCheckinfoList(){
		jsonMsg = checkinfoServiceInter.findListWithApp(checkinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找耳标信息
	 */
	public void findEartagList(){
		jsonMsg = eartagServiceInter.findEartagList(eartag, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找三品一标信息
	 */
	public void findThreeaList(){
		jsonMsg = threeaServiceInter.findThreeaList(threea, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找采摘收获信息
	 */
	public void findGaininfoList(){
		jsonMsg = gaininfoServiceInter.findGaininfoList(gaininfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找销售信息
	 */
	public void findSaleinfoList(){
		jsonMsg = saleinfoServiceInter.findSaleinfoList(saleinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 获取肥料使用记录
	 */
	public void findFertilizerUseList(){
		jsonMsg = fertilizerUseServiceInter.findList(fertilizerUse,page,rows,sort,order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找生产节点信息
	 */
	public void findNodeinfoList(){
		jsonMsg = nodeinfoServiceInter.findNodeinfoList(nodeinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 查找兽药投入品使用信息
	 */
	public void findDrugUseList(){
		jsonMsg = drugUseServiceInter.findDrugUseList(drugUse, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	

	/**
	 * 饲料投入品使用记录信息
	 */
	public void findFeedUseList(){
		jsonMsg = feedUseServiceInter.findList(feedUse,page,rows,sort,order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查找档案信息
	 * param entId(不为空时查询企业主体档案和分类档案)
	 * param record(objId与objTypeId组合查询相应档案)
	 */
	public void findRecordList(){
		Pager<TbRecord> pager = recordService.findRecordList(entId,record,page,rows,order,sort);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	
	//根据企业ID获取档案及要素
	public void getRecordByEntId(){
		jsonMsg = recordService.getRecordByEntId(entId);
		printJsonString(jsonMsg);
	}
		
	
	//获取档案要素
	public void getElements(){
		List<TbElement> list = recordService.getElements(recId);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	
	
	/**
	 * 根据产品分类二维码查询档案
	 */
	public void findRecordByPtqDimennno(){
		List<TbRecord> list = recordService.findRecordListByPtqDimennno(dimenno);
		printJsonString(this.getJSON(list));
	}

	
	
	////getter and setter ////////
	public TbAgriInput getAgriInput() {
		return agriInput;
	}

	public void setAgriInput(TbAgriInput agriInput) {
		this.agriInput = agriInput;
	}

	public TbAgriUse getAgriUse() {
		return agriUse;
	}

	public void setAgriUse(TbAgriUse agriUse) {
		this.agriUse = agriUse;
	}

	public TbCheckinfo getCheckinfo() {
		return checkinfo;
	}

	public void setCheckinfo(TbCheckinfo checkinfo) {
		this.checkinfo = checkinfo;
	}

	public TbEartag getEartag() {
		return eartag;
	}

	public void setEartag(TbEartag eartag) {
		this.eartag = eartag;
	}

	public TbThreea getThreea() {
		return threea;
	}

	public void setThreea(TbThreea threea) {
		this.threea = threea;
	}

	public TbGaininfo getGaininfo() {
		return gaininfo;
	}

	public void setGaininfo(TbGaininfo gaininfo) {
		this.gaininfo = gaininfo;
	}

	public TbSaleinfo getSaleinfo() {
		return saleinfo;
	}

	public void setSaleinfo(TbSaleinfo saleinfo) {
		this.saleinfo = saleinfo;
	}

	public FertilizerUse getFertilizerUse() {
		return fertilizerUse;
	}

	public void setFertilizerUse(FertilizerUse fertilizerUse) {
		this.fertilizerUse = fertilizerUse;
	}

	public TbRecord getRecord() {
		return record;
	}

	public void setRecord(TbRecord record) {
		this.record = record;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public void setAgriInputServiceInter(AgriInputServiceInter agriInputServiceInter) {
		this.agriInputServiceInter = agriInputServiceInter;
	}

	public void setAgriUseServiceInter(AgriUseServiceInter agriUseServiceInter) {
		this.agriUseServiceInter = agriUseServiceInter;
	}

	public void setCheckinfoServiceInter(CheckinfoServiceInter checkinfoServiceInter) {
		this.checkinfoServiceInter = checkinfoServiceInter;
	}

	public void setEartagServiceInter(EartagServiceInter eartagServiceInter) {
		this.eartagServiceInter = eartagServiceInter;
	}

	public void setThreeaServiceInter(ThreeaServiceInter threeaServiceInter) {
		this.threeaServiceInter = threeaServiceInter;
	}

	public void setGaininfoServiceInter(GaininfoServiceInter gaininfoServiceInter) {
		this.gaininfoServiceInter = gaininfoServiceInter;
	}

	public void setSaleinfoServiceInter(SaleinfoServiceInter saleinfoServiceInter) {
		this.saleinfoServiceInter = saleinfoServiceInter;
	}

	public void setFertilizerUseServiceInter(
			FertilizerUseServiceInter fertilizerUseServiceInter) {
		this.fertilizerUseServiceInter = fertilizerUseServiceInter;
	}

	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}

	public TbNodeinfo getNodeinfo() {
		return nodeinfo;
	}

	public void setNodeinfo(TbNodeinfo nodeinfo) {
		this.nodeinfo = nodeinfo;
	}

	public void setNodeinfoServiceInter(NodeinfoServiceInter nodeinfoServiceInter) {
		this.nodeinfoServiceInter = nodeinfoServiceInter;
	}

	public DrugUse getDrugUse() {
		return drugUse;
	}

	public void setDrugUse(DrugUse drugUse) {
		this.drugUse = drugUse;
	}

	public FeedUse getFeedUse() {
		return feedUse;
	}

	public void setFeedUse(FeedUse feedUse) {
		this.feedUse = feedUse;
	}

	public void setDrugUseServiceInter(DrugUseServiceInter drugUseServiceInter) {
		this.drugUseServiceInter = drugUseServiceInter;
	}

	public void setFeedUseServiceInter(FeedUseServiceInter feedUseServiceInter) {
		this.feedUseServiceInter = feedUseServiceInter;
	}

	
	
}
