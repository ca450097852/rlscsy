package com.hontek.record.service.inter;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.sys.pojo.TsUser;

/**
 * 分类产地信息service接口
 *
 */
public interface ProTypeAreaServiceInter {

	public String findProTypeArea(int entId,int page, int rows, String order, String sort) ;
	
	String findProTypeArea(TbProTypeArea proTypeArea,int page, int rows, String order, String sort);

	String addProTypeArea(TbProTypeArea proTypeArea,TsUser user,HttpServletRequest request);
	
	String updateProTypeArea(TbProTypeArea proTypeArea);
	
	String deleteProTypeArea(String ids);
	
	public List<TbProTypeArea> findProTypeAreas(int entId);

}
