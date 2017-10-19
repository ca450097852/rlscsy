package com.hontek.agri.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.agri.dao.WarningDao;
import com.hontek.agri.pojo.TbWarning;
import com.hontek.agri.service.inter.WarningServiceInter;
/**
 * <p>Title: 预警信息</p>
 * <p>Description: 预警信息service接口实现类</p>
 * @author qql
 */
public class WarningServiceImpl extends BaseServiceImpl implements WarningServiceInter {

	private WarningDao warningDao;
	private EnterpriseDao enterpriseDao;
	
	/**
	 * 注入DAO
	 * @param warningDao
	 */
	public void setWarningDao(WarningDao warningDao) {
		this.warningDao = warningDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加预警信息
	 */
	public String addWarning(TbWarning warning) {
		String msg = "添加预警信息成功!";
		try {
			warning.setWid(warningDao.getTableSequence("tb_warning".toUpperCase()));
			warning.setCrttime(DateUtil.formatDateTime());
			warningDao.save(warning);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加预警信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	
	
	/**
	 * 添加预警信息
	 */
	public String addReportWarning() {
		String msg = "上报预警信息成功!";
		try {
			
			//查询出企业
			String hql = "from TsEnterprise where flag=3";
			List<TsEnterprise> enterprise = enterpriseDao.find(hql);
			if(!enterprise.isEmpty()){
				//档案要素
				Map<String, String> tableColumns = new HashMap<String, String>();				
				tableColumns.put("tb_agri_use", "use_date");
				tableColumns.put("tb_fertilizer_use", "use_date");
				tableColumns.put("tb_gaininfo", "harvest_date");
				tableColumns.put("tb_checkinfo", "check_time");
				tableColumns.put("tb_saleinfo", "crttime");				
				Object [] keys =  tableColumns.keySet().toArray();
			
				for (TsEnterprise tsEnterprise : enterprise) {					
					int entId = tsEnterprise.getEntId();
					String name = tsEnterprise.getName();					
					String sql = "SELECT rec_ID,type_name FROM tb_pro_type,tb_pro_type_qrcode,tb_record "
										+ "WHERE tb_pro_type.type_id=tb_pro_type_qrcode.type_id "
										+ "and tb_pro_type_qrcode.ptq_id = tb_record.obj_ID and tb_record.obj_type_id=3 "
										+ "and tb_pro_type_qrcode.ent_id="+entId;
					//查询档案
					List<Object[]> recIds =  warningDao.findBySql(sql);					
					if(!recIds.isEmpty()){
						for (Object[] objects : recIds) {
							Object recId = objects[0];
							Object typeName = objects[1];
							
							int count = 0;
							int keyLength =  keys.length;
							for (int i = 0; i < keyLength; i++) {
								
								String tableName = keys[i].toString();
								String dateColumn = tableColumns.get(tableName);
								
								StringBuffer sqlBuffer = new StringBuffer("select max(");
								sqlBuffer.append(dateColumn);
								sqlBuffer.append(" ) from ");
								sqlBuffer.append(tableName);
								sqlBuffer.append(" where rec_id= ");
								sqlBuffer.append(recId);
								
								Object dateObj = warningDao.uniqueResult(sqlBuffer.toString());			
								if(dateObj!=null){
									Date date = DateUtil.getDate(dateObj.toString());									
									//判断时间是否有超过15天时间
									int d =daysBetween(date, new Date());					
									
									System.out.println(d+">15="+(d > 15 ));

									if(d > 15 ){
										count++;
									}	
								}															
							}	
														
							//所有档案信息超过15天未填写内容
							if(count==keyLength){								
								String contents = name+"生产的【"+typeName+"】生产档案信息已超过15天未进行任何信息更新！";
								TbWarning warning = new TbWarning();
								warning.setWid(warningDao.getTableSequence("tb_warning".toUpperCase()));
								warning.setCrttime(DateUtil.formatDateTime());
								warning.setWtype("2");
								warning.setState("1");
								warning.setWarningTime(DateUtil.formatDateTime());
								warning.setContents(contents);
								warning.setEntId(entId);
								warning.setEntName(name);
								
								warningDao.save(warning);							
								logger.info(contents);								
							}else if(count==0){
								String contents = name+"生产的【"+typeName+"】生产档案信息从未进行任何信息上报及更新！";
								TbWarning warning = new TbWarning();
								warning.setWid(warningDao.getTableSequence("tb_warning".toUpperCase()));
								warning.setCrttime(DateUtil.formatDateTime());
								warning.setWtype("2");
								warning.setState("1");
								warning.setWarningTime(DateUtil.formatDateTime());
								warning.setContents(contents);
								warning.setEntId(entId);
								warning.setEntName(name);
								
								warningDao.save(warning);							
								logger.info(contents);	
							}
						}												
					}
				}							
			}									
		} catch (AppException e) {
			e.printStackTrace();
			msg = "上报预警信息出现异常!"+e.getMessage();
			logger.error(msg);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return msg;	
	}
	
	/**
	 * 2个日期隔天数
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) { 
	     
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
  
	
	/**
	 * 删除预警信息
	 */
	public String deleteWarning(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbWarning warning = warningDao.get(TbWarning.class, Integer.valueOf(idArray[i]));
					if(warning!=null){
						warningDao.delete(warning);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String findWaringEntList(TbWarning warning, int page, int rows, String sort, String order){
		
		JSONArray jsonArray = warningDao.findWaringEntList(warning, page, rows, sort, order);
		
    	return "{\"total\":" +jsonArray.size() + ",\"rows\":"+ getJSON(jsonArray) + "}";

	}
	
	/**
	 * 分页查询预警信息
	 */
	public String findWarningList(TbWarning warning, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(warning!=null){						
				String wtype = warning.getWtype();
				if(wtype!=null&&!"".equals(wtype)){
					condition.append(" and wtype = "+wtype+" ");
				}
				int entId = warning.getEntId();
				if(entId!=0){
					condition.append(" and entId = "+entId+" ");
				}
				String state = warning.getState();
				if(state!=null&&!"".equals(state)){
					condition.append(" and state = "+state+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order,"crttime desc"));		
		
			Pager<TbWarning>  pager = warningDao.findPager(TbWarning.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询预警信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改预警信息
	 */
	public String updateWarning(TbWarning warning) {
		String msg = "修改预警信息成功!";
		try {
			warningDao.update(warning);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改预警信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	
	/**
	 * 修改预警信息State
	 */
	public String updateState(String ids) {
		String msg = "修改成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbWarning warning = warningDao.get(TbWarning.class, Integer.valueOf(idArray[i]));
					if(warning!=null){
						warning.setState("2");
						warningDao.update(warning);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String updateStates(String ids, String state) {
		String msg = "操作成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbWarning warning = warningDao.get(TbWarning.class, Integer.valueOf(idArray[i]));
					if(warning!=null){
						warning.setState(state);
						warningDao.update(warning);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	
/*	public static void main(String[] args) {
		try {
			System.out.println(daysBetween( DateUtil.getDate("2015-12-30"), new  Date()));		
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}*/
}
