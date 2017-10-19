package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.review.pojo.TbTrace;
import com.hontek.review.service.inter.TraceServiceInter;
import com.hontek.sys.pojo.TbInterAccount;
/**
 * <p>Title: 溯源信息同步</p>
 * <p>Description: 溯源信息同步ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TraceAction extends BaseAction{
	private TraceServiceInter traceService; 
	
	public TraceServiceInter getTraceService() {
		return traceService;
	}

	public void setTraceService(TraceServiceInter traceService) {
		this.traceService = traceService;
	}

	public void trace(){
		try {
			OnlyManager onlyManager = OnlyManager.getInstance();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getRequest().getInputStream()));
			String strReq = ""; 
			String line = "";
			while((line = br.readLine())!=null){
				line = new String(line.getBytes(),"GBK");
				strReq += line; 
			}
			
			
			JSONObject obj = JSONObject.fromObject(strReq);
			String token = obj.getString("token");
			TbInterAccount interAccount = onlyManager.getAccount(token);
			if(interAccount!=null){
				List<TbTrace> traceList = new ArrayList<TbTrace>();
				JSONArray arr = obj.getJSONArray("traceList");
				for(int i=0;i<arr.size();i++){
					JSONObject traceObj = arr.getJSONObject(i);
					TbTrace trace = (TbTrace) JSONObject.toBean(traceObj, TbTrace.class);
					trace.setSysCode(interAccount.getSysCode());
					trace.setCrttime(DateUtil.formatDateTime());
					traceList.add(trace);
				}
				String rst = traceService.appendTraceList(traceList);
				jsonMsg = "{\"status\":\"1\",\"retroId\":\""+rst+"\",\"errMsg\":\"同步成功\" }";
			}else{
				jsonMsg = "{\"status\":\"0\",\"retroId\":\"\",\"errMsg\":\"未登陆\" }";
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "{\"status\":\"-1\",\"retroId\":\"\",\"errMsg\":\"系统繁忙,请稍候重试\" }";
		}
		printStringByGBK(jsonMsg);
	}
	
}
