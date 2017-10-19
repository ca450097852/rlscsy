package com.hontek.element.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.AgriInputDao;
import com.hontek.element.pojo.TbAgriInput;
import com.hontek.element.pojo.TbAgriType;
import com.hontek.element.service.inter.AgriInputServiceInter;
/**
 * <p>Title: 农业投入品</p>
 * <p>Description: 农业投入品service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class AgriInputServiceImpl extends BaseServiceImpl implements AgriInputServiceInter {

	private AgriInputDao agriInputDao;
	/**
	 * 注入DAO
	 * @param agriInputDao
	 */
	public void setAgriInputDao(AgriInputDao agriInputDao) {
		this.agriInputDao = agriInputDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加农业投入品
	 */
	public String addAgriInput(TbAgriInput agriInput) {
		String msg = "添加农业投入品成功!";
		try {
			agriInput.setAgriId(agriInputDao.getTableSequence("tb_agri_input".toUpperCase()));
			agriInput.setCrttime(DateUtil.formatDate());
			agriInputDao.save(agriInput);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加农业投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除农业投入品
	 */
	public String deleteAgriInput(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbAgriInput agriInput = agriInputDao.get(TbAgriInput.class, Integer.valueOf(idArray[i]));
					if(agriInput!=null){
						agriInputDao.delete(agriInput);
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
	 * 分页查询农业投入品
	 */
	public String findAgriInputList(TbAgriInput agriInput, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(agriInput!=null){						
				int recId = agriInput.getRecId();
				if(recId!=0){
					condition.append(" and rec_Id = "+recId+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order));				
			Pager<TbAgriInput>  pager = agriInputDao.findPager(condition.toString(), page, rows);			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询农业投入品出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	public String findAgriInputComboList(LoginUser loginUser,String ids){
		String entId = String.valueOf(loginUser.getEntId());
		List<TbAgriInput> list = agriInputDao.findCompanyAgriInputList(entId,ids);
		List<Map<String, Object>> listResults = new ArrayList<Map<String, Object>>();
		for (TbAgriInput et : list) {
			String agriName = et.getAgriName();
			String buyDate = et.getBuyDate();
			String option = agriName+" "+buyDate;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", option);
			map.put("text", option);
			listResults.add(map);
		}
		return  getJSON(listResults);
	
	}

	
	
	/**
	 * 修改农业投入品
	 */
	public String updateAgriInput(TbAgriInput agriInput) {
		String msg = "修改农业投入品成功!";
		try {
			agriInputDao.update(agriInput);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改农业投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
