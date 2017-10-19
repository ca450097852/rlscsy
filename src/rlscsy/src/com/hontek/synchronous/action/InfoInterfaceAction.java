package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.info.pojo.TInfotemp;
import com.hontek.info.service.inter.InfotempServiceInter;
import com.hontek.sys.pojo.TbInterAccount;

/**
 * <p>Title: 同步资讯</p>
 * <p>Description: 同步资讯</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InfoInterfaceAction extends BaseAction{
	private InfotempServiceInter infotempService;
	
	public void setInfotempService(InfotempServiceInter infotempService) {
		this.infotempService = infotempService;
	}

	//同步资讯
	public void info(){
		try {
			OnlyManager onlyManager = OnlyManager.getInstance();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getRequest().getInputStream()));
			String strReq = ""; 
			String line = "";
			while((line = br.readLine())!=null){
				line = new String(line.getBytes(),"GBK");
				System.out.println(line);
				strReq += line; 
			}
			
			JSONObject obj = JSONObject.fromObject(strReq);
			String token = obj.getString("token");
			TbInterAccount interAccount = onlyManager.getAccount(token);
			if(interAccount!=null){
				JSONArray arr = obj.getJSONArray("infoList");
				List<TInfotemp> infoList = new ArrayList<TInfotemp>();
				for(int i=0;i<arr.size();i++){
					JSONObject infoObj = arr.getJSONObject(i);
					TInfotemp info = (TInfotemp) JSONObject.toBean(infoObj,TInfotemp.class);
					info.setSysCode(interAccount.getSysCode());
					info.setCrttime(DateUtil.formatDateTime());
					infoList.add(info);
				}
				infotempService.appendInfoTemp(infoList);
				jsonMsg = "{\"status\":\"1\",\"errMsg\":\"同步成功\" }";
			}else{
				jsonMsg = "{\"status\":\"0\",\"errMsg\":\"未登陆\" }";
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "{\"status\":\"-1\",\"errMsg\":\"系统繁忙，请稍候重试\" }";
		}
		
		this.printStringByGBK(jsonMsg);
	}
	
}
