package com.hontek.sys.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.EntStyle;

/**
 * 风格关系表
 * @author qql
 *
 */
public interface EntStyleServiceInter {

	String addEntStyle(EntStyle entStyle);

	String updateEntStyle(EntStyle entStyle);

	String deleteEntStyle(String ids);

	Pager<EntStyle> findPage(EntStyle entStyle, int page, int rows,
			String sort, String order);
	
	/**
	 * 根据主体Id 和风格类型 获取风格；
	 */
	public EntStyle getEntStyleInfo(String entId, String scType);

	EntStyle getEntStyleByEntId(int entId);
	public EntStyle getEntStyleByDomain(String domain,String scType);
	
	EntStyle getEntStyleByType(int entId,String scType);

}
