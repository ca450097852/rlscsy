package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.quartz.DateIntervalTrigger;

import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.synchronous.service.inter.DataInfoServiceInter;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.service.inter.InterAccountServiceInter;


@SuppressWarnings("serial")
public class SyncDataAction extends BaseAction{

	private DataInfoServiceInter dataInfoServiceInter;
	private String yyyy_mm;
	
	public void setDataInfoServiceInter(DataInfoServiceInter dataInfoServiceInter) {
		this.dataInfoServiceInter = dataInfoServiceInter;
	}

	public String getYyyy_mm() {
		return yyyy_mm;
	}

	public void setYyyy_mm(String yyyyMm) {
		yyyy_mm = yyyyMm;
	}

	/*public void findList(){
		
			dataInfoServiceInter.addList();

			printJsonString("ok");
		
	}

	

	public void findList2(){
		if(yyyy_mm!=null){
			dataInfoServiceInter.addList2(yyyy_mm);
			printJsonString(yyyy_mm+"ok");
		}else{
			printJsonString("yyyy_mm is null");

		}
		

	}*/
}
