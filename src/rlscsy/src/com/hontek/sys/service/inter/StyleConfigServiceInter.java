package com.hontek.sys.service.inter;

import com.hontek.comm.base.LoginUser;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.StyleConfig;

/**
 * 门户风格设置表
 * @author IMZK
 *
 */
public interface StyleConfigServiceInter {

	String addStyleConfig(StyleConfig styleConfig);

	String updateStyleConfig(StyleConfig styleConfig);

	String deleteSc(String ids);

	Pager<StyleConfig> findPage(StyleConfig styleConfig, int page, int rows,
			String sort, String order);
	
	String getStyleCombobox(String scType);
}
