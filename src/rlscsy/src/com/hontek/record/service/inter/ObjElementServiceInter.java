package com.hontek.record.service.inter;


import com.hontek.comm.util.Pager;
import com.hontek.record.pojo.TbObjElement;

public interface ObjElementServiceInter {
	
	public String addObjElement(TbObjElement objElement);
	public String deleteObjElement(String ids);
	public String updateObjElement(TbObjElement objElement);
	/**
	 * 查询档案要素关系
	 */
	Pager<TbObjElement> findObjElementList(TbObjElement objElement, int page,
			int rows, String order, String sort);
	public String updateObjElementList(String jsonMsg);
}
