package com.hontek.company.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.UseguideDao;
import com.hontek.company.pojo.Useguide;
import com.hontek.company.service.inter.UseguideServiceInter;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * <p>Title: 操作指引信息表</p>
 * <p>Description: 操作指引信息接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class UseguideServiceImpl extends BaseServiceImpl implements UseguideServiceInter {

	private UseguideDao useguideDao;
	
	Logger logger = Logger.getLogger(UseguideServiceImpl.class);
	
	public void setUseguideDao(UseguideDao useguideDao) {
		this.useguideDao = useguideDao;
	}


	/**
	 * 添加企业操作记录
	 */
	public String addUseguide(Useguide useguide) {
		String msg = "添加操作成功!";
		try {
			useguide.setUgId(useguideDao.getTableSequence("tb_useguide".toUpperCase()));
			useguide.setCrttime(DateUtil.formatDate());
			useguideDao.save(useguide);						
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
	public String deleteUseguide(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Useguide useguide = useguideDao.get(Useguide.class, Integer.valueOf(idArray[i]));
					if(useguide!=null){
						useguideDao.delete(useguide);
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
	 * 分页查询操作记录
	 */
	public String findUseguidePagerList(Useguide useguide,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(useguide!=null){
				//操作标题名称
				String title = useguide.getTitle();//
				if(title!=null&&!"".equals(title)){
					condition.append(" and title like '%"+title+"%' ");
				}		
				//操作编号
				String ugNo = useguide.getUgNo();
				if(ugNo!=null&&!"".equals(ugNo)){
					condition.append(" and ugNo = '"+ugNo+"' ");
				}
			}		
			//添加排序
			String defalutSort = " crttime desc ";
			condition.append(sortCondtion(sort, order,defalutSort));		
		
			Pager<Useguide>  pager = useguideDao.findPager(Useguide.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询操作记录出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateUseguide(Useguide useguide) {
		String msg = "修改成功!";
		try {
			
			Useguide useguideOld = useguideDao.get(Useguide.class, useguide.getUgId());

			useguideOld.setContents(useguide.getContents());
			useguideOld.setTitle(useguide.getTitle());	
			useguideOld.setUgNo(useguide.getUgNo());
			useguideOld.setCrttime(DateUtil.formatDate());
			
			useguideDao.update(useguideOld);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	
	public String findUseguideList(Useguide useguide){
		String hql = "from Useguide where 1=1";
		StringBuffer condition = new StringBuffer("");
		if(useguide!=null){
			//操作标题名称
			String title = useguide.getTitle();//
			if(title!=null&&!"".equals(title)){
				condition.append(" and title = '"+title+"' ");
			}		
			//操作编号
			String ugNo = useguide.getUgNo();
			if(ugNo!=null&&!"".equals(ugNo)){
				condition.append(" and ugNo = '"+ugNo+"' ");
			}
		}
		hql = hql+condition.toString();
		
		List<Useguide>  list = useguideDao.find(hql);
		return getJSON(list);
	}

}