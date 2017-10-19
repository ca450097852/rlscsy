package com.hontek.sys.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.aspectj.lang.JoinPoint;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.comm.util.PropertiesUitl;
import com.hontek.sys.pojo.TsLog;
import com.hontek.sys.pojo.TsLogVo;
import com.hontek.sys.service.inter.LogServiceInter;
/**
 * <p>Title: 系统日志表</p>
 * <p>Description: 系统日志Action 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LogAction extends BaseAction{
	private LogServiceInter logService;
	private TsLog log;
	private TsLogVo logVo;
	private String startDate;
	private String endDate;
	
	@SuppressWarnings("unchecked")
	public String findLogList(){
		Map mapCondition = new HashMap();
		if(logVo != null){
			String user_name = logVo.getUserName();
			if(user_name != null){
				mapCondition.put("user_name", user_name);
			}
			String acttype = logVo.getActType();
			if(acttype != null){
				mapCondition.put("act_type", Integer.parseInt(acttype));
			}
			
			int entId = logVo.getEntId();
			if(entId != 0){
				mapCondition.put("tl.ent_id", entId);
			}
		}
		
		if(startDate!=null){
			mapCondition.put("startDate", startDate);
		}
		if(endDate!=null){
			mapCondition.put("endDate", endDate);
		}
		Pager pager = logService.findLogList(mapCondition,page,rows);
		
		String jsonStr = createEasyUiJson(pager);
		printJsonString(jsonStr);
		return null;
	}
		
	/**
	 * 日志功能
	 * @param point
	 * @param returnObj
	 */
	public void saveLog(JoinPoint point, Object returnObj){
		long startTime = System.currentTimeMillis();
    	String methodName =  point.getSignature().getName();   	    	   	
    	Properties properties = PropertiesUitl.getProperties();
    	/*   	Set<Object> keySets = properties.keySet();
    	Iterator<Object> iterator = keySets.iterator();
    	while (iterator.hasNext()) {
    		Object key = iterator.next();
			System.out.println(key+"---"+properties.getProperty(String.valueOf(key)));
			
		}*/
    	
    	String name ="";
		Object entityDisPlayName = "";
		String funType = "";
		String actType = "";
    	if(methodName.startsWith("add")){
    		 name =methodName.substring(3);   		
    		 funType = "添加";   		
    		 actType = "1";
    	}else if(methodName.startsWith("update")){
    		 name =methodName.substring(6);    		 
    		 funType = "修改";
    		 actType = "3";
    	}else if(methodName.startsWith("delete")){
    		 name =methodName.substring(6); 		 
    		 funType = "删除";
    		 actType = "2";
    	}
    	
		entityDisPlayName = properties.getProperty(name);

		if(entityDisPlayName==null){
			entityDisPlayName = methodName;
		}
		
		/*System.out.println("entityName：" + name);
        System.out.println("methodName：" + methodName);
        System.out.println("entityDisPlayName：" + entityDisPlayName);
        System.out.println("funType：" + funType);*/
		if(null!=getLoginUserId() && !"".equals(getLoginUserId())){
			TsLog tsLog = new TsLog( funType+entityDisPlayName, actType, String.valueOf(getLoginUserId()), getLoginUserEntId(), DateUtil.formatDateTime(), getOptionIp());
	        //System.out.println(tsLog.toString());
	        if(getColId()!=0){
	    		tsLog.setColId(getColId());
	    	}
	        logService.saveLog(tsLog);
		}
        System.out.println("添加日志用时：" + (System.currentTimeMillis()-startTime));
	}
	
	public LogServiceInter getLogService() {
		return logService;
	}

	public void setLogService(LogServiceInter logService) {
		this.logService = logService;
	}

	public TsLog getLog() {
		return log;
	}

	public void setLog(TsLog log) {
		this.log = log;
	}

	public TsLogVo getLogVo() {
		return logVo;
	}

	public void setLogVo(TsLogVo logVo) {
		this.logVo = logVo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
