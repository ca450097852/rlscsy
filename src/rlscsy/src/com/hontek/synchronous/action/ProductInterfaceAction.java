package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.hontek.comm.action.BaseAction;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.service.inter.ProductServiceInter;
import com.hontek.sys.pojo.TbInterAccount;

public class ProductInterfaceAction extends BaseAction{
	private ProductServiceInter productService;

	public void setProductService(ProductServiceInter productService) {
		this.productService = productService;
	}
	/**
	 * 同步产品信息
	 */
	public void pro(){
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
			if(interAccount==null){
				jsonMsg = "{\"status\":\"0\",\"proCode\":[],\"errMsg\":\"未登陆\" }";
			}else{
				JSONArray arr = obj.getJSONArray("products");
				List<TbProduct> proList = new ArrayList<TbProduct>();
				for(int i=0;i<arr.size();i++){
					JSONObject jsonPro = arr.getJSONObject(i);
					JsonConfig config = new JsonConfig();
					config.setIgnoreDefaultExcludes(false);
					config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
					config.setExcludes(new String[]{"appList"});
					TbProduct pro = (TbProduct) JSONObject.toBean(jsonPro, TbProduct.class);
					//解析附件
					if(jsonPro.containsKey("appList")){
						JSONArray app = jsonPro.getJSONArray("appList");
						List<TbProductAppendix> appList = new ArrayList<TbProductAppendix>();
						for(int j=0;j<app.size();j++){
							JSONObject apJson = app.getJSONObject(j);
							TbProductAppendix ap = (TbProductAppendix) JSONObject.toBean(apJson,TbProductAppendix.class);
							appList.add(ap);
						}
						pro.setAppendixList(appList);
					}
					
					proList.add(pro);
				}
				//每次处理信息量不超过30个
				if(proList.size()<=30){
					List proIdList = productService.appendProduct(proList,interAccount);
					
					jsonMsg = "{\"status\":\"1\",\"proCode\":"+JSONArray.fromObject(proIdList)+",\"errMsg\":\"同步成功\" }";
				}else{
					jsonMsg = "{\"status\":\"2\",\"proCode\":[],\"errMsg\":\"每次同步的信息量不得超过30条\" }";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.printStringByGBK(jsonMsg);
	}
	
}
