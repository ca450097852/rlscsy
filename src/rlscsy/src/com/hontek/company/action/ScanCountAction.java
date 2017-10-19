package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.ApplicationContextUtil;
import com.hontek.company.pojo.ScanCount;
import com.hontek.company.service.inter.ScanCountServiceInter;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EntStyleServiceInter;
/**
 * <p>Title: 溯源统计表</p>
 * <p>Description: 溯源统计action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ScanCountAction extends BaseAction{
	
	private ScanCountServiceInter scanCountServiceInter;
	private ScanCount scanCount;
	private String comId;
	


	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public ScanCount getScanCount() {
		return scanCount;
	}

	public void setScanCount(ScanCount scanCount) {
		this.scanCount = scanCount;
	}

	public void setScanCountServiceInter(ScanCountServiceInter scanCountServiceInter) {
		this.scanCountServiceInter = scanCountServiceInter;
	}


	/**
	 * 溯源统计
	 */
	public void findScanCount(){		
		jsonMsg = scanCountServiceInter.getScanCount(comId);
		printJsonString(jsonMsg);
	}

	public void findScanCountTable(){
				
		jsonMsg = scanCountServiceInter.getScanCountTable(scanCount, page, rows);
		printJsonString(jsonMsg);
	}
	


	/**
	 * 溯源统计
	 */
	public void addScanCount(){		
		if(scanCount==null){
			scanCount = new ScanCount();
		}
		TsUser u =  getLoginTsUser();
		if(u!=null){
			scanCount.setUserId(""+u.getUserId());
		}
		scanCount.setFromIp(getOptionIp());
		
		jsonMsg = scanCountServiceInter.addScanCount(scanCount);
		printJsonString(jsonMsg);
	}
	
	
}
