package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.ValidCode;
import com.hontek.company.service.inter.ValidCodeServiceInter;

/**
 * 二维码验证表
 * @author IMZK
 *
 */
public class ValidCodeAction extends BaseAction{
	private ValidCodeServiceInter validCodeService;
	
	private String objId;
	private String typeId;
	private int count;
	private int entId;
	
	private ValidCode validCode;

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ValidCode getValidCode() {
		return validCode;
	}

	public void setValidCode(ValidCode validCode) {
		this.validCode = validCode;
	}

	public void setValidCodeService(ValidCodeServiceInter validCodeService) {
		this.validCodeService = validCodeService;
	}
	
	public void addValidCode(){
		String msg = validCodeService.addValidCode(objId,typeId,count,entId,this.getRequest(),this.getResponse());
		this.printJsonString(msg);
	}
	/**
	 * 验证验证码是否有效
	 */
	public void updateValidCode(){
		String ip = this.getRequest().getRemoteAddr();
		if(validCode!=null){
			validCode.setValidUserr(ip);
			String msg = validCodeService.updateValidCode(validCode);
			this.printJsonString(msg);
		}
	}
	
}
