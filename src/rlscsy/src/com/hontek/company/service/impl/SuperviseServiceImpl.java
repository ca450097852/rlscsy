package com.hontek.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.SuperviseDao;
import com.hontek.company.pojo.Supervise;
import com.hontek.company.service.inter.SuperviseServiceInter;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 企业监管信息表</p>
 * <p>Description: 企业监管信息接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class SuperviseServiceImpl extends BaseServiceImpl implements SuperviseServiceInter {

	private SuperviseDao superviseDao;
	
	Logger logger = Logger.getLogger(SuperviseServiceImpl.class);
	
	public void setSuperviseDao(SuperviseDao superviseDao) {
		this.superviseDao = superviseDao;
	}


	/**
	 * 添加企业监管记录
	 */
	public String addSupervise(Supervise supervise,TsUser tsUser) {
		String msg = "添加监管成功!";
		try {
			supervise.setSupId(superviseDao.getTableSequence("tb_supervise".toUpperCase()));
			supervise.setSupEnt(tsUser.getEntId());
			supervise.setSupUser(tsUser.getUserId());
			superviseDao.save(supervise);						
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteSupervise(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Supervise supervise = superviseDao.get(Supervise.class, Integer.valueOf(idArray[i]));
					if(supervise!=null){
						superviseDao.delete(supervise);
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
	 * 分页查询监管记录
	 */
	public String findSupervisePagerList(Supervise supervise,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(supervise!=null){
				//被监管企业名称
				String entName = "";//
				if(entName!=null&&!"".equals(entName)){
					condition.append(" and entName like '%"+entName+"%' ");
				}		
				//被监管企业
				int entId = supervise.getEntId();
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId+" ");
				}
			}		
			//添加排序
			String defalutSort = " supTime desc ";
			condition.append(sortCondtion(sort, order,defalutSort));		
		
			Pager<Supervise>  pager = superviseDao.findPager(Supervise.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询监管记录出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateSupervise(Supervise supervise) {
		String msg = "修改成功!";
		try {
			
			Supervise superviseOld = superviseDao.get(Supervise.class, supervise.getSupId());

			superviseOld.setContents(supervise.getContents());
			superviseOld.setTitle(supervise.getTitle());
			superviseOld.setSupTime(supervise.getSupTime());
			superviseOld.setCompanyContents(supervise.getCompanyContents());
			superviseOld.setCompanyTime(DateUtil.formatDateTime());
			
			superviseDao.update(superviseOld);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 企业回复监管信息
	 * @param supervise
	 * @return
	 */
	public String updateSuperviseByCompany(Supervise supervise){
		String msg = "回复监管信息成功!";
		try {
			
			Supervise superviseOld = superviseDao.get(Supervise.class, supervise.getSupId());

			superviseOld.setCompanyContents(supervise.getCompanyContents());
			superviseOld.setCompanyTime(supervise.getCompanyTime());
			
			superviseDao.update(superviseOld);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "回复监管信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	
	}
	
	
	public String findSuperviseListById(String id){
		String hql = "from Supervise where supId="+id;
		List<Supervise>  list = superviseDao.find(hql);
		return getJSON(list);
	}
	
	public String findSuperviseList(String entCode){
		List<Supervise>  list = superviseDao.findSuperviseList(entCode);
		return getJSON(list);
	}


	public String addSuperviseList(Supervise supervise, TsUser tsUser,
			TsEnterprise enterprise, String ids) {
		String msg = "添加监管成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String[] arr = ids.split(",");
				for(String id:arr){
					Supervise sup = new Supervise();
					sup.setSupId(superviseDao.getTableSequence("tb_supervise".toUpperCase()));
					sup.setEntId(Integer.parseInt(id));
					sup.setTitle(supervise.getTitle());
					sup.setContents(supervise.getContents());
					sup.setSupTime(supervise.getSupTime());
					
					sup.setSupEnt(tsUser.getEntId());
					sup.setSupUser(tsUser.getUserId());
					superviseDao.save(sup);	
				}
			}
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
}
