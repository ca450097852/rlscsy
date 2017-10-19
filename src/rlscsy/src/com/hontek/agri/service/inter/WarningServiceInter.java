package com.hontek.agri.service.inter;

import com.hontek.agri.pojo.TbWarning;
/**
 * <p>Title: 预警信息目录</p>
 * <p>Description: 预警信息service接口</p>
 * @author qql
 */
public interface WarningServiceInter {

	public String addWarning(TbWarning warning);

	public String deleteWarning(String ids);

	public String updateWarning(TbWarning warning);
	
	public String updateState(String ids);
	
	public String updateStates(String ids,String state);

	public String findWarningList(TbWarning warning, int page, int rows, String sort,String order);
	
	public String findWaringEntList(TbWarning warning, int page, int rows, String sort, String order);
}
