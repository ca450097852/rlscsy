package com.hontek.jcmanager.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DrawDesigns;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.AnimalInInfoDao;
import com.hontek.jcmanager.pojo.AnimalInInfo;
import com.hontek.jcmanager.service.inter.AnimalInInfoServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 生猪进厂表
 * @author Administrator
 *
 */
public class AnimalInInfoServiceImpl extends BaseServiceImpl implements AnimalInInfoServiceInter{
	private AnimalInInfoDao animalInInfoDao;

	public void setAnimalInInfoDao(AnimalInInfoDao animalInInfoDao) {
		this.animalInInfoDao = animalInInfoDao;
	}

	public String addAnimalInInfo(AnimalInInfo animalInInfo) {
		String msg = "添加成功";
		try {
			if(animalInInfo!=null){
				animalInInfo.setVrId(animalInInfoDao.getTableSequence("tb_animal_in_info".toUpperCase()));
				animalInInfo.setCreateTime(DateUtil.formatDateTime());
				animalInInfoDao.save(animalInInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateAnimalInInfo(AnimalInInfo animalInInfo) {
		String msg = "修改成功";
		try {
			if(animalInInfo!=null)
				animalInInfoDao.update(animalInInfo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public String deleteAnimalInInfo(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				animalInInfoDao.executeHql("delete from AnimalInInfo where vrId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<AnimalInInfo> findList(AnimalInInfo animalInInfo,int page, int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(animalInInfo!=null){
			if(animalInInfo.getComId()!=0){
				condition.append(" and comId="+animalInInfo.getComId());
			}
			if(animalInInfo.getQuarantineId()!=null && !"".equals(animalInInfo.getQuarantineId())){
				condition.append(" and quarantineId='"+animalInInfo.getQuarantineId()+"' ");
			}			
			if(animalInInfo.getPtbId()!=0){
				condition.append(" and ptbId="+animalInInfo.getPtbId());
			}
		}		
		condition.append(sortCondtion(sort, order));
		Pager<AnimalInInfo> pager = animalInInfoDao.findList(condition.toString(),page,rows);
		return pager;
	}

	public String getAnimalInList(TsEnterprise enterprise) {
		String condtions = "";
		String chartType ="";
		if(enterprise!=null){
			String typeId = enterprise.getTypeId();
			String startDate = enterprise.getStartDate();
			String endDate = enterprise.getEndDate();
			chartType = enterprise.getChartType();
			Integer comId=enterprise.getEntId();
			/**
			 * 条件统计
			 */
			if(comId!=0&&!"".equals(comId)){
				condtions +=" and tb.com_id="+comId;
			}
			if(startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tb.in_date >='"+startDate+"' and tb.in_date <='"+endDate+"'";

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
				condtions += " and tb.in_date>='"+startDate+"' and tb.in_date <='"+endDate+"'";

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
				condtions += " and tb.in_date >='"+startDate+"' and tb.in_date <='"+endDate+"'";

			}		
		}	
		List<Map<Object, Object>> oneDimensionsList = animalInInfoDao.getAnimalInList(condtions);	
		Object count = animalInInfoDao.getAnimalInCount(condtions);
		System.out.println(oneDimensionsList);
		//配置统计图的参数
		DrawDesigns d = new DrawDesigns();
		
		String xAxisName = "总屠宰数："+count;
		d.setYAxisName("");
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

	public String getAnimalInListTable(AnimalInInfo animalInInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAnimalInListTable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAnimalInListTableTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	public AnimalInInfoDao getAnimalInInfoDao() {
		return animalInInfoDao;
	}

	public String getAnimalInList(AnimalInInfo animalInInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
