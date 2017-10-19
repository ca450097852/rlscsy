package com.hontek.element.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.agri.dao.WarningDao;
import com.hontek.agri.pojo.TbWarning;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.AgriUseDao;
import com.hontek.element.dao.GaininfoDao;
import com.hontek.element.pojo.TbAgriUse;
import com.hontek.element.pojo.TbGaininfo;
import com.hontek.element.service.inter.GaininfoServiceInter;
/**
 * @ClassName: GaininfoServiceImpl
 * @Description: TODO(采摘收获信息 service实现类)
 * @date 2015-8-10 下午07:38:59
 * @author qql
 * @version 1.0
 */
public class GaininfoServiceImpl extends BaseServiceImpl implements GaininfoServiceInter {

	private GaininfoDao gaininfoDao;
	/**
	 * 注入DAO
	 * @param gaininfoDao
	 */

	private AgriUseDao agriUseDao;
	private WarningDao warningDao;

	Logger logger = Logger.getLogger(this.getClass());

	
	public void setGaininfoDao(GaininfoDao gaininfoDao) {
		this.gaininfoDao = gaininfoDao;
	}

	public void setAgriUseDao(AgriUseDao agriUseDao) {
		this.agriUseDao = agriUseDao;
	}

	public void setWarningDao(WarningDao warningDao) {
		this.warningDao = warningDao;
	}

	/**
	 * 添加采摘收获
	 */
	public String addGaininfo(TbGaininfo gaininfo) {
		String msg = "添加采摘收获成功!";
		try {
			gaininfo.setGiId(gaininfoDao.getTableSequence("tb_gaininfo".toUpperCase()));
			gaininfo.setCrttime(DateUtil.formatDate());
			
			Object b= gaininfoDao.save(gaininfo);

			addWaringMsg(gaininfo);			
			System.out.println(b);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加采摘收获出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 预警信息
	 * @param gaininfo
	 */
	public void addWaringMsg(TbGaininfo gaininfo){
		
		int recId = gaininfo.getRecId();
		String date = gaininfo.getHarvestDate();
		
		List<TbAgriUse> list = agriUseDao.findList(TbAgriUse.class, " and safeDate >'"+date+"' and recId="+recId+" order by safeDate desc");
		if(!list.isEmpty()){
			TbAgriUse agriUse = list.get(0);
			
			//System.out.println("安全间隔期为："+agriUse.getSafeDate());
			
			String sql = "SELECT t3.type_name,t4.name FROM tb_record t1,tb_pro_type_qrcode t2,tb_pro_type t3,ts_enterprise t4 WHERE t1.obj_ID = t2.ptq_id and t2.type_id = t3.type_id and t2.ent_id = t4.ent_id and t1.rec_ID="+recId;
			List<Object[]> list2 = warningDao.findBySql(sql);
			if(!list2.isEmpty()){
				String contents = list2.get(0)[1]+"生产的【"+list2.get(0)[0]+"】在农药施用安全间隔进行采摘，安全间隔期为【"+agriUse.getSafeDate()+"】，采摘日期为【"+date+"】";
				TbWarning warning = new TbWarning();
				warning.setWid(warningDao.getTableSequence("tb_warning".toUpperCase()));
				warning.setCrttime(DateUtil.formatDateTime());
				warning.setWtype("1");
				warning.setState("1");
				warning.setWarningTime(DateUtil.formatDateTime());
				warning.setContents(contents);
				warningDao.save(warning);
			}

		}
	}
	
	
	
	/**
	 * 删除采摘收获
	 */
	public String deleteGaininfo(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbGaininfo gaininfo = gaininfoDao.get(TbGaininfo.class, Integer.valueOf(idArray[i]));
					if(gaininfo!=null){
						gaininfoDao.delete(gaininfo);
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

	/**
	 * 分页查询采摘收获
	 */
	public String findGaininfoList(TbGaininfo gaininfo, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(gaininfo!=null){						
				int recId = gaininfo.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbGaininfo>  pager = gaininfoDao.findPager(TbGaininfo.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询采摘收获出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改采摘收获
	 */
	public String updateGaininfo(TbGaininfo gaininfo) {
		String msg = "修改采摘收获成功!";
		try {
			gaininfoDao.update(gaininfo);
			
			addWaringMsg(gaininfo);			
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改采摘收获出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
