package com.hontek.sys.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.BannerDao;
import com.hontek.sys.dao.EntStyleDao;
import com.hontek.sys.pojo.Banner;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.service.inter.EntStyleServiceInter;

/**
 * 风格关系表
 * @author qql
 *
 */
public class EntStyleServiceImpl extends BaseServiceImpl implements EntStyleServiceInter{
	
	private EntStyleDao entStyleDao;
	private BannerDao bannerDao;

	

	Logger logger = Logger.getLogger(this.getClass());
	
	public void setEntStyleDao(EntStyleDao entStyleDao) {
		this.entStyleDao = entStyleDao;
	}
	public void setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String addEntStyle(EntStyle entStyle) {
		String msg = "添加失败";
		if(entStyle!=null){
			try {
				entStyleDao.deleteByIds(entStyle.getEntId()+"", entStyle.getScType());//添加前先清除
				
				entStyle.setEsId(entStyleDao.getTableSequence("t_ent_style".toUpperCase()));
				entStyle.setCreateTime(DateUtil.formatDateTime());
				entStyleDao.save(entStyle);
				msg = "添加成功";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("添加风格关系失败");
			}
		}
		return msg;
	}

	public String updateEntStyle(EntStyle entStyle) {
		String msg = "修改失败";
		try {
			if(entStyle!=null){
				entStyleDao.update(entStyle);
				msg = "修改成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String deleteEntStyle(String ids) {
		String msg = "删除失败";
		try {
			if(ids!=null && !"".equals(ids)){
				entStyleDao.deleteByIds(ids);
				msg = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public Pager<EntStyle> findPage(EntStyle entStyle, int page,
			int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(entStyle!=null){
			int scId = entStyle.getScId();
			int esId = entStyle.getEsId();
			int entId = entStyle.getEntId();
			/*if(entStyle.getScName()!=null && !"".equals(entStyle.getScName())){
				condition.append(" and scName like '%"+entStyle.getScName()+"%' ");
			}
			if(entStyle.getScCss()!=null && !"".equals(entStyle.getScCss())){
				condition.append(" and scCss like '%"+entStyle.getScCss()+"%'");
			}
			if(entStyle.getScType()!=null && !"".equals(entStyle.getScType())){
				condition.append(" and scType='"+entStyle.getScType()+"'");
			}
			if(entStyle.getState()!=null && !"".equals(entStyle.getState())){
				condition.append(" and state='"+entStyle.getState()+"'");
			}*/
		}
		
		if(sort!=null && !"".equals(sort)){
			order = order==null?"":order;
			condition.append(" order by "+sort+" "+order);
		}
		return entStyleDao.findPager(EntStyle.class, condition.toString(), page, rows);
	}
	
	/**
	 * 根据主体Id 和风格类型 获取风格；
	 */
	public EntStyle getEntStyleInfo(String entId, String scType){
		EntStyle entStyle = null;
		if(entId!=null&&!entId.equals("")&&scType!=null&&!scType.equals("")){
			entStyle = entStyleDao.getEntStyleInfo(entId, scType);
		}
		return entStyle;
	}

	public EntStyle getEntStyleByEntId(int entId) {
		return entStyleDao.getEntStyleByEntId(entId);
	}
	
	public EntStyle getEntStyleByDomain(String domain,String scType) {
		EntStyle res = entStyleDao.getEntStyleByDomain(domain,scType);
		if(res!=null&&scType.equals("1")){
			List<Banner> banner = bannerDao.find(" from Banner where entId = "+res.getEntId());
			if(banner!=null&&!banner.isEmpty()){
				res.setBanner(banner);
			}
		}
		return res;
	}
	
	public EntStyle getEntStyleByType(int entId,String scType){
		return entStyleDao.getEntStyleByType(entId,scType);//当前主体的风格;
	}
}
