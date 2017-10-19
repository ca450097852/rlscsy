package com.hontek.agri.service.inter;

import com.hontek.agri.pojo.TbAgriDis;
/**
 * <p>Title: 禁用投入品目录</p>
 * <p>Description: 禁用投入品目录service接口</p>
 * @author qql
 */
public interface AgriDisServiceInter {

	public String addAgriDis(TbAgriDis agriDis);

	public String deleteAgriDis(String ids);

	public String updateAgriDis(TbAgriDis agriDis);

	public String findAgriDisList(TbAgriDis agriDis, int page, int rows, String sort,String order);
	

}
