package com.hontek.record.service.inter;


import com.hontek.record.pojo.TbElementTemplet;;

public interface ElementTempletServiceInter {
	
	public String addElementTemplet(TbElementTemplet templet);
	public String deleteElementTemplet(String id);
	public String updateElementTemplet(TbElementTemplet templet);
	public String findElementTempletList(TbElementTemplet templet,int page,int rows,String sort,String order);
	
	public String findElementTempletTree(String archivesType);
	
	/**
	 * 保存-----左右结构页面
	 */
	public String addElementTemplets(String archivesType,String elementIds);
	
	/**
	 * 保存-----统一页面
	 */
	public String addAll(String putong,String sanping,String zhongzhi,String xumu);
}
