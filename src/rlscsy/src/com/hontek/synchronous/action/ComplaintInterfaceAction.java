package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.complaint.service.inter.ComplaintServiceInter;
import com.hontek.sys.pojo.TbInterAccount;

/**
 * <p>Title: 投诉举报接口</p>
 * <p>Description: 投诉举报接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ComplaintInterfaceAction extends BaseAction{
	private ComplaintServiceInter complaintService;

	public void setComplaintService(ComplaintServiceInter complaintService) {
		this.complaintService = complaintService;
	}
	
	public void complaint(){
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
			if(interAccount==null){
				jsonMsg = "{\"status\":\"0\",\"errMsg\":\"未登陆\" }";
			}else{
				//List complaintList = new ArrayList();
				JSONArray arr = obj.getJSONArray("complaintList");
				for(int i=0;i<arr.size();i++){
					TbComplaint com = (TbComplaint) JSONObject.toBean(arr.getJSONObject(i),TbComplaint.class); 
					com.setEntId(interAccount.getEntId()+0L);
					com.setSysCode(interAccount.getSysCode());
					complaintService.appendComplaint(com);
				}
				jsonMsg = "{\"status\":\"1\",\"errMsg\":\"同步成功\" }";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.printStringByGBK(jsonMsg);
	}
	
}
