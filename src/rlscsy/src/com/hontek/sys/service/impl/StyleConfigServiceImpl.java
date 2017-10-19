package com.hontek.sys.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.StyleConfigDao;
import com.hontek.sys.pojo.StyleConfig;
import com.hontek.sys.service.inter.StyleConfigServiceInter;

/**
 * 门户风格设置表
 * @author IMZK
 *
 */
public class StyleConfigServiceImpl extends BaseServiceImpl implements StyleConfigServiceInter{
	
	private StyleConfigDao styleConfigDao;

	public void setStyleConfigDao(StyleConfigDao styleConfigDao) {
		this.styleConfigDao = styleConfigDao;
	}

	public String addStyleConfig(StyleConfig styleConfig) {
		String msg = "添加失败";
		if(styleConfig!=null){
			
			try {
				styleConfig.setScId(styleConfigDao.getTableSequence("t_style_config".toUpperCase()));
				styleConfig.setCreateTime(DateUtil.formatDateTime());
				styleConfigDao.save(styleConfig);
				msg = "添加成功";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	public String updateStyleConfig(StyleConfig styleConfig) {
		String msg = "修改失败";
		try {
			if(styleConfig!=null){
				styleConfigDao.update(styleConfig);
				msg = "修改成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String deleteSc(String ids) {
		String msg = "删除失败";
		try {
			if(ids!=null && !"".equals(ids)){
				styleConfigDao.deleteByIds(ids);
				msg = "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public Pager<StyleConfig> findPage(StyleConfig styleConfig, int page,
			int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(styleConfig!=null){
			if(styleConfig.getScName()!=null && !"".equals(styleConfig.getScName())){
				condition.append(" and scName like '%"+styleConfig.getScName()+"%' ");
			}
			if(styleConfig.getScCss()!=null && !"".equals(styleConfig.getScCss())){
				condition.append(" and scCss like '%"+styleConfig.getScCss()+"%'");
			}
			if(styleConfig.getScType()!=null && !"".equals(styleConfig.getScType())){
				condition.append(" and scType='"+styleConfig.getScType()+"'");
			}
			if(styleConfig.getState()!=null && !"".equals(styleConfig.getState())){
				condition.append(" and state='"+styleConfig.getState()+"'");
			}
		}
		
		if(sort!=null && !"".equals(sort)){
			order = order==null?"":order;
			condition.append(" order by "+sort+" "+order);
		}
		return styleConfigDao.findPager(StyleConfig.class, condition.toString(), page, rows);
	}
	
	/**
	 * 根据风格类型查找风格下拉
	 */
	public String getStyleCombobox(String scType) {		
		List list = styleConfigDao.getStyleCombobox(scType);
		String jsonstr = getJSON(list);
		return jsonstr;
	}
}
