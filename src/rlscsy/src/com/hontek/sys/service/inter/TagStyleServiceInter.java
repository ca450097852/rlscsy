package com.hontek.sys.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TagStyle;

/**
 * 二维码风格设置
 *
 */
public interface TagStyleServiceInter {

	String addTagStyle(TagStyle tagStyle);

	String updateTagStyle(TagStyle tagStyle);

	String deleteTagStyle(String ids);
	
	/**
	 * 根据主体Id获取风格；
	 */
	public TagStyle getTagStyleInfo(int entId);

}
