package com.hontek.synchronous.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import net.sf.json.JSONObject;

import com.hontek.comm.action.BaseAction;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.service.inter.InterAccountServiceInter;

/**
 * <p>Title: 同步用户权限控制</p>
 * <p>Description: 同步用户权限控制ACTION</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AccountAction extends BaseAction{
	
	private InterAccountServiceInter interAccountServiceInter;	
	
	
	public void setInterAccountServiceInter(
			InterAccountServiceInter interAccountServiceInter) {
		this.interAccountServiceInter = interAccountServiceInter;
	}


	/**
	 * 同步用户登陆
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public void Login(){
		try {
			String rst = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getRequest().getInputStream()));
			String xml = ""; 
			String line = "";
			while((line = br.readLine())!=null){
				line = new String(line.getBytes(),"GBK");
				System.out.println(line);
				xml += line; 
			}
			System.out.println(xml);
			
			JSONObject obj = JSONObject.fromObject(xml);
			String userName = obj.getString("userName");
				userName = userName==null?"":userName;
			String password = obj.getString("password");
				password = password==null?"":password;
			if(!"".equals(userName)&&!"".equals(password)){
				String condition = " and account='"+userName+"' and pass='"+password+"'";
				TbInterAccount interAcount = interAccountServiceInter.getInterAccount(condition);
				if(interAcount!=null){
					String token = UUID.randomUUID().toString().replace("-", "");
					OnlyManager onlyManager = OnlyManager.getInstance();
					interAcount.setTime(System.currentTimeMillis());
					onlyManager.putAccount(token, interAcount);
					rst = "{\"status\":\"success\",\"token\":\""+token+"\",\"errMsg\":\"\" }";
				}else{//没有该用户
					rst = "{\"status\":\"fail\",\"token\":\"\",\"errMsg\":\"帐号或密码错误\" }";
				}
			}else{
				rst = "{\"status\":\"fail\",\"token\":\"\",\"errMsg\":\"帐号或密码不可为空\" }";
			}
			
			this.printStringByGBK(rst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
