package com.hontek.agri.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.agri.dao.AgriDisDao;
import com.hontek.agri.pojo.TbAgriDis;
import com.hontek.agri.service.inter.AgriDisServiceInter;
/**
 * <p>Title: 禁用投入品</p>
 * <p>Description: 禁用投入品service接口实现类</p>
 * @author qql
 */
public class AgriDisServiceImpl extends BaseServiceImpl implements AgriDisServiceInter {

	private AgriDisDao agriDisDao;
	/**
	 * 注入DAO
	 * @param agriDisDao
	 */
	public void setAgriDisDao(AgriDisDao agriDisDao) {
		this.agriDisDao = agriDisDao;
	}

	Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 添加禁用投入品
	 */
	public String addAgriDis(TbAgriDis agriDis) {
		String msg = "添加禁用投入品成功!";
		try {
			agriDis.setAgId(agriDisDao.getTableSequence("tb_agri_dis".toUpperCase()));
			agriDis.setCrttime(DateUtil.formatDateTime());
			agriDisDao.save(agriDis);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加禁用投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	
	/**
	 * 删除禁用投入品
	 */
	public String deleteAgriDis(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbAgriDis agriDis = agriDisDao.get(TbAgriDis.class, Integer.valueOf(idArray[i]));
					if(agriDis!=null){
						agriDisDao.delete(agriDis);
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
	 * 分页查询禁用投入品
	 */
	public String findAgriDisList(TbAgriDis agriDis, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(agriDis!=null){						
				String agName = agriDis.getAgName();
				if(agName!=null&&!"".equals(agName)){
					condition.append(" and agName like '%"+agName+"%' ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order,"crttime desc"));		
		
			Pager<TbAgriDis>  pager = agriDisDao.findPager(TbAgriDis.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询禁用投入品出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改禁用投入品
	 */
	public String updateAgriDis(TbAgriDis agriDis) {
		String msg = "修改禁用投入品成功!";
		try {
			agriDisDao.update(agriDis);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改禁用投入品出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
