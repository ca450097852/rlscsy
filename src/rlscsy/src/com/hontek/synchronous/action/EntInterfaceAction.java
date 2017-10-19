package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
/**
 * <p>Title: 同步用户权限控制</p>
 * <p>Description: 同步用户权限控制ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class EntInterfaceAction extends BaseAction{
	private EnterpriseServiceInter enterpriseService;
	
	public void setEnterpriseService(EnterpriseServiceInter enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public EnterpriseServiceInter getEnterpriseService() {
		return enterpriseService;
	}

	/**
	 * 同步企业信息
	 */
	public void ent(){
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
				jsonMsg = "{\"status\":\"0\",\"entCode\":\"[]\",\"errMsg\":\"未登陆\" }";
			}else{
				JSONArray ents = obj.getJSONArray("ents");
				List<TsEnterprise> entList = new ArrayList<TsEnterprise>();
				for(int i=0;i<ents.size();i++){
					TsEnterprise ent = (TsEnterprise) JSONObject.toBean(ents.getJSONObject(i),TsEnterprise.class);
					entList.add(ent);
				}
				
				//补充值
				for(TsEnterprise tse : entList){
					tse.setParentId(interAccount.getEntId());
					tse.setAreaId(interAccount.getAreaId());
					tse.setSysCode(interAccount.getSysCode());
					tse.setFlag("3");//设置为企业
					tse.setSts("0");
					//tse.setCrtDt(DateUtil.formatDateTime());
					tse.setIsReported("1");//设置为上报企业
					tse.setEntType(2);//默认设置为私企
				}
				
				List<Map> mapList = enterpriseService.appendInterEnt(entList);
				String entCode = JSONArray.fromObject(mapList).toString();
				entCode = entCode==null?"":entCode;
				jsonMsg = "{\"status\":\"1\",\"entCode\":"+entCode+",\"errMsg\":\"同步成功\" }";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "{\"status\":\"-1\",\"entCode\":[],\"errMsg\":\"系统繁忙请稍后重试\" }";
		}
		
		this.printStringByGBK(jsonMsg);
	}
	
}
