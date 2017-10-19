package com.hontek.record.service.inter;


import com.hontek.record.pojo.TbElement;;

public interface ElementServiceInter {
	
	public String addElement(TbElement element);
	public String deleteElement(String id);
	public String updateElement(TbElement element);
	public String findElementList(TbElement element,int page,int rows,String sort,String order);
	public String getElementCombobox( );
}
