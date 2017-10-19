package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.ValidCodeRecord;
import com.hontek.company.service.inter.ValidCodeRecordServiceInter;
import com.hontek.company.service.inter.ValidCodeServiceInter;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.service.inter.ProTypeQrcodeServiceInter;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.EnterpriseServiceInter;

/**
 *
 */
public class ValidCodeRecordAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ValidCodeRecordServiceInter validCodeRecordServiceInter ;
	
	private EnterpriseServiceInter enterprseService;

	private ProTypeQrcodeServiceInter proTypeQrcodeService;
	
	
	private ValidCodeRecord validCodeRecord;

	public ValidCodeRecord getValidCodeRecord() {
		return validCodeRecord;
	}

	public void setValidCodeRecord(ValidCodeRecord validCodeRecord) {
		this.validCodeRecord = validCodeRecord;
	}


	public void setValidCodeRecordServiceInter(ValidCodeRecordServiceInter validCodeRecordServiceInter) {
		this.validCodeRecordServiceInter = validCodeRecordServiceInter;
	}

	public void setEnterprseService(EnterpriseServiceInter enterprseService) {
		this.enterprseService = enterprseService;
	}

	public void setProTypeQrcodeService(ProTypeQrcodeServiceInter proTypeQrcodeService) {
		this.proTypeQrcodeService = proTypeQrcodeService;
	}

	/**
	 * 
	 */
	public void findPagerList(){
		jsonMsg  = validCodeRecordServiceInter.findPagerList(validCodeRecord, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	public void findPagerList2(){
		
		ValidCodeRecord validCodeRecord = new ValidCodeRecord();
		
		String objId = getRequest().getParameter("objId");
		String typeId = getRequest().getParameter("typeId");
		if("1".equals(typeId)){
			TsEnterprise enterprise = enterprseService.getEnterPirseByEntId(Integer.valueOf(objId));
			validCodeRecord.setDimenno(enterprise.getEntCode());
		}else{
			TbProTypeQrcode proTypeQrcode = proTypeQrcodeService.getProTypeQrcodeById(Integer.valueOf(objId));
			validCodeRecord.setDimenno(proTypeQrcode.getDimenno());
		}
				
		jsonMsg  = validCodeRecordServiceInter.findPagerList(validCodeRecord, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
}
