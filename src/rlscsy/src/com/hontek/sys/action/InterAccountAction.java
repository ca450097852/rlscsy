package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.service.inter.InterAccountServiceInter;

/**
 * <p>Title: 接入系统账号表</p>
 * <p>Description: 接入系统账号Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class InterAccountAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InterAccountServiceInter interAccountServiceInter;	
	private TbInterAccount interAccount;
	private String ids;	


	public TbInterAccount getInterAccount() {
		return interAccount;
	}

	public void setInterAccount(TbInterAccount interAccount) {
		this.interAccount = interAccount;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setInterAccountServiceInter(InterAccountServiceInter interAccountServiceInter) {
		this.interAccountServiceInter = interAccountServiceInter;
	}

	/**
	 * 添加接入系统账号
	 */
	public void addInterAccount(){
		if(interAccount!=null){
			interAccount.setCrttime(DateUtil.formatDateTime());
			interAccount.setUserId(getLoginUserId());
		}
		jsonMsg  = interAccountServiceInter.addInterAccount(interAccount);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改接入系统账号
	 */
	public void updateInterAccount(){
		jsonMsg  = interAccountServiceInter.updateInterAccount(interAccount);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除接入系统账号
	 */
	public void deleteInterAccount(){
		jsonMsg  = interAccountServiceInter.deleteInterAccount(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询接入系统账号
	 */
	public void findInterAccountPagerList(){
		jsonMsg  = interAccountServiceInter.findInterAccountPagerList(interAccount, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询接入系统账号是否已经存在
	 */
	public void findInterAccountExist(){
		jsonMsg  = interAccountServiceInter.findInterAccountExist(ids);
		printJsonString(jsonMsg);
	}
}
