package com.hontek.record.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.pojo.ProAndBatchRecord;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.record.service.inter.RecordServiceInter;
import com.hontek.sys.pojo.TsUser;
/**
 * 档案Action类 
 * @author lzk
 *
 */
public class RecordAction extends BaseAction{
	
	private RecordServiceInter recordService;
	private TbRecord record;
	private TbProTypeQrcode proTypeQrcode;

	private String entId;
	private String typeId;
	private String recordType;//档案类型
	private int recId;
	private String ids;
	private String dels;
	
	public TbProTypeQrcode getProTypeQrcode() {
		return proTypeQrcode;
	}
	public String getDels() {
		return dels;
	}
	public void setDels(String dels) {
		this.dels = dels;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public TbRecord getRecord() {
		return record;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public void setProTypeQrcode(TbProTypeQrcode proTypeQrcode) {
		this.proTypeQrcode = proTypeQrcode;
	}
	public void setRecord(TbRecord record) {
		this.record = record;
	}
	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}
	
	public void findRecordList(){
		Pager<TbRecord> pager = recordService.findRecordList(entId,record,page,rows,order,sort);
		printJsonString(this.createEasyUiJson(pager));
	}
	//查找企业分类档案
	public void findRecordTypeList(){
		Pager<TbRecord> pager = recordService.findRecordTypeList(entId,record,page,rows,order,sort);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	//添加档案信息
	public void addRecord(){
		TsUser user = getLoginTsUser();
		HttpServletRequest request = this.getRequest();
		jsonMsg = recordService.addRecord(recordType,typeId,user,record,request);
		
		printJsonString(jsonMsg);
	}
	//修改档案信息
	public void updateRecord(){
		jsonMsg = recordService.updateRecord(record);
		printJsonString(jsonMsg);
	}
	//获取档案要素
	public void getElements(){
		List<TbElement> list = recordService.getElements(recId);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	public void deleteRecord(){
		jsonMsg = recordService.deleteRecord(ids);
		printJsonString(jsonMsg);
	}
	
	
	public void getEntElements(){
		jsonMsg = recordService.getEntElements(entId);
		printJsonString(jsonMsg);
	}
	//根据企业ID获取要素
	public void getElementsByEntId(){
		jsonMsg = recordService.getElementsByEntId(entId,this.getEnterprse());
		printJsonString(jsonMsg);
	}
	
	public void addRecordByTypeId(){
		CompanyUser user = this.getLoginCompanyUser();
		jsonMsg = recordService.addRecordByTypeId(ids,user,this.getRequest());
		printJsonString(jsonMsg);
	}
	
	public void addOrDelRecord(){
		CompanyUser user = this.getLoginCompanyUser();
		jsonMsg = recordService.addOrDelRecord(ids,dels,user,this.getRequest());
		printJsonString(jsonMsg);
	}
	
	/**
	 * 获取待审核档案
	 */
	public void getAuditRecord(){
		int entId = 0;
		if(!this.isAdmin()){
			entId = this.getEnterprse().getEntId();
		}
		
		Pager<TbRecord> pager = recordService.getAuditRecord(entId,page,rows);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	//保存产地信息
	public void saveChandi(){
		Object companyObject = getSession().getAttribute("loginCompany");
		if(companyObject!=null){			
			Company company = ((Company)companyObject);//企业ID
			jsonMsg = recordService.saveChandi(proTypeQrcode,company,this.getRequest());
		}
		printJsonString(jsonMsg);
	}
	
	public void updateAuditState(){
		int entId = 0;
		if(!this.isAdmin()){
			entId = this.getEnterprse().getEntId();
		}
		jsonMsg = recordService.updateAuditState(typeId,entId,record);
		printJsonString(jsonMsg);
	}
	
	public void getLoginAuditRecord(){
		List<ProAndBatchRecord> list = recordService.getLoginAuditRecord(entId);
		jsonMsg = this.getJSON(list);
		printJsonString(jsonMsg);
	}
}
