package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.Zizhi;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.service.inter.ZizhiServiceInter;
import com.hontek.sys.pojo.TbInterAccount;

/**
 * <p>Title: 资质同步</p>
 * <p>Description: 资质同步ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ZizhIInterfaceAction extends BaseAction{
	private ZizhiServiceInter zizhiService;

	public ZizhiServiceInter getZizhiService() {
		return zizhiService;
	}

	public void setZizhiService(ZizhiServiceInter zizhiService) {
		this.zizhiService = zizhiService;
	}
	
	public void zizhi(){
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
				List<Zizhi> zzList = new ArrayList<Zizhi>();
				JSONArray arr = obj.getJSONArray("zzList");
				for(int i=0;i<arr.size();i++){
					JSONObject zzObj = arr.getJSONObject(i);
					Zizhi zizhi = new Zizhi();
					zizhi.setZid(zzObj.getString("zid"));
					zizhi.setAppType(zzObj.getInt("appType"));
					zizhi.setAwardTime(zzObj.getString("awardTime"));
					zizhi.setAwardUnit(zzObj.getString("awardUnit"));
					zizhi.setEntCode(zzObj.getString("entCode"));
					zizhi.setGrantUnit(zzObj.getString("grantUnit"));
					zizhi.setRemark(zzObj.getString("remark"));
					zizhi.setZizhiName(zzObj.getString("zName"));
					
					JSONArray arrList = zzObj.getJSONArray("appendix");
					List<ZizhiAppendix> appList = new ArrayList<ZizhiAppendix>();
					
					for(int j=0;j<arrList.size();j++){
						JSONObject ao = arrList.getJSONObject(j);
						ZizhiAppendix za = new ZizhiAppendix();
						za.setAppName(ao.getString("appName"));
						za.setPath(ao.getString("path"));
						za.setRemark(ao.getString("remark"));
						appList.add(za);
					}
					zizhi.setAppendix(appList);
					zzList.add(zizhi);
				}
				if(zzList.size()<=30){
					jsonMsg = zizhiService.appendZizhiList(zzList);
					
					System.out.println("jsonMsg ==== "+jsonMsg);
					
					jsonMsg = "{\"status\":\"1\",\"zids\":\""+jsonMsg+"\",\"errMsg\":\" 同步成功\" }";
				}else{
					jsonMsg = "{\"status\":\"2\",\"zids\":\"\",\"errMsg\":\"同步失败，每次同步信息不得超过30条\" }";
				}
				
				
			}else{
				jsonMsg = "{\"status\":\"0\",\"zids\":\"\",\"errMsg\":\"未登陆\" }";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.printStringByGBK(jsonMsg);
	}
	
}
