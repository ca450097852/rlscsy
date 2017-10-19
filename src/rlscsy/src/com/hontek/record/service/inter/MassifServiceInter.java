package com.hontek.record.service.inter;


import javax.servlet.http.HttpServletRequest;

import com.hontek.record.pojo.TbMassif;
import com.hontek.sys.pojo.TsUser;

/**
 * 地块信息service接口
 *
 */
public interface MassifServiceInter {

	public String findMassif(String ptaId,int page, int rows, String order, String sort) ;
	
	String findMassif(TbMassif massif,int page, int rows, String order, String sort);

	String addMassif(TbMassif massif,TsUser user,HttpServletRequest request);
	
	String updateMassif(TbMassif massif);
	
	String deleteMassif(String ids);

}
