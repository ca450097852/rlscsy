package com.hontek.jcmanager.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DrawDesigns;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.MeatOutInfoBaseDao;
import com.hontek.jcmanager.pojo.MeatOutInfoBase;
import com.hontek.jcmanager.service.inter.MeatOutInfoBaseServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

public class MeatOutInfoBaseServiceImpl extends BaseServiceImpl implements MeatOutInfoBaseServiceInter{
	private MeatOutInfoBaseDao meatOutInfoBaseDao;

	public void setMeatOutInfoBaseDao(MeatOutInfoBaseDao meatOutInfoBaseDao) {
		this.meatOutInfoBaseDao = meatOutInfoBaseDao;
	}

	public String addMeatOutInfoBase(MeatOutInfoBase meatOutInfoBase) {
		String msg = "添加成功";
		try {
			if(meatOutInfoBase!=null){
				meatOutInfoBase.setMoibId(meatOutInfoBaseDao.getTableSequence("tb_meat_out_info_base".toUpperCase()));
				meatOutInfoBaseDao.save(meatOutInfoBase);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateMeatOutInfoBase(MeatOutInfoBase meatOutInfoBase) {
		String msg = "添加成功";
		try {
			if(meatOutInfoBase!=null)
				meatOutInfoBaseDao.update(meatOutInfoBase);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteMeatOutInfoBase(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				meatOutInfoBaseDao.executeHql("delete from MeatOutInfoBase where moibId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<MeatOutInfoBase> findList(MeatOutInfoBase meatOutInfoBase,
			int page, int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(meatOutInfoBase!=null){
			if(meatOutInfoBase.getComId()!=0){
				condition.append(" and comId="+meatOutInfoBase.getComId());
			}
			if(meatOutInfoBase.getFlag()!=null && !"".equals(meatOutInfoBase.getFlag())){
				condition.append(" and flag='"+meatOutInfoBase.getFlag()+"' ");
			}
			if(meatOutInfoBase.getPtbId()!=0){
				condition.append(" and ptbId="+meatOutInfoBase.getPtbId());
			}
		}
		condition.append(sortCondtion(sort, order));
		
		Pager<MeatOutInfoBase> pager = meatOutInfoBaseDao.findList(condition.toString(), page,rows);
		
		return pager;
	}
	/**
	 *  销售量分析
	 */
	public String getMarketList(TsEnterprise enterprise) {
		String condtions = "";
		String chartType ="";
		if(enterprise!=null){
			String typeId = enterprise.getTypeId();
			String startDate = enterprise.getStartDate();
			String endDate = enterprise.getEndDate();
			chartType = enterprise.getChartType();
			String flag=enterprise.getFlag();
			Integer comId=enterprise.getEntId();
			/**
			 * 条件统计
			 */
			if(comId!=0&&!"".equals(comId)){
				condtions +=" and ba.com_id="+comId;
			}if(flag!=null&&!"".equals(flag)){
				condtions +=" and ba.flag="+flag+"";
			}
			if(startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and ba.tran_date >='"+startDate+"' and ba.tran_date <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date firstDate=ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,1);
				ca.add(Calendar.DAY_OF_MONTH,-1);
				Date lastDate=ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and ba.tran_date>='"+startDate+"' and ba.tran_date <='"+endDate+"'";

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH, 1);
				Date firstDate=ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH, 11);
				ca.set(Calendar.DATE,31);
				Date lastDate=ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and ba.tran_date >='"+startDate+"' and ba.tran_date <='"+endDate+"'";

			}		
			
		}
		
		List<Map<Object, Object>> oneDimensionsList = meatOutInfoBaseDao.getMarketList(condtions);	
		Object count = meatOutInfoBaseDao.getMarketCount(condtions);
		System.out.println(count);
		System.out.println(oneDimensionsList);
		//配置统计图的参数
		DrawDesigns d = new DrawDesigns();
		d.setYAxisName("");
		String xAxisName = "总销售重量："+count;
		d.setXAxisName(xAxisName);
			
		d.setOneDimensionsList(oneDimensionsList);

		d.setCaption(xAxisName);
		d.setWidth(1100);
		d.setHeight(400);
		String str1 = "";
		str1 += "<tr>";		
		if("".equals(chartType)||"0".equals(chartType)){
			str1 += "<td>" + d.drawColumn3D() + "</td>";	
		}else if("1".equals(chartType)){
			str1 += "<td>" + d.drawLine3D() + "</td>";
		}if("2".equals(chartType)){
			str1 += "<td>" + d.drawPie3D() + "</td>";
		}	
		str1 += "</tr>";			
		return str1;
	}

	
	public String getTranId(String comCode) {		
		 int seq = meatOutInfoBaseDao.getTableSequence(comCode);
		 String tranId = String.valueOf(seq);
		 while(tranId.length()<7){
			 tranId = "0"+tranId;
		 }
		return comCode+tranId;
	}
	
}
