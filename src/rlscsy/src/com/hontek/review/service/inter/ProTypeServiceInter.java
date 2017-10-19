package com.hontek.review.service.inter;

import java.util.List;
import java.util.Map;

import com.hontek.review.pojo.TbProType;
import com.hontek.review.pojo.TreeVo;
import com.hontek.comm.util.Pager;
/**
 * <p>Title: 产品分类</p>
 * <p>Description: 产品分类SERVICE接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public interface ProTypeServiceInter {

	Pager findProTypeList(Map mapCondition, int page, int rows);

	void addProType(TbProType proType);
	
	void addProType2(TbProType proType);
	
	List findProTypeList(String typeName);

	void updateProType(TbProType proType);

	void updateProTypeState(String ids, String state);

	List getProTypeList();

	String getProTypeTree(Long parentId);

	String getProTypeTreeToPro(long parentId);
	
	/**
	 * 门户企业注册，选择产品分类
	 */
	String getProTypeTreeForPortal(long parentId);

	String getProTypeTreeToProbyType(long l, String typeClass);
	/**
	 * 获取企业产品分类tree
	 * @param entId
	 * @return
	 */
	List<TreeVo> getEntTypeTree(int entId);

	String getProTypeTreeByName(String typeName);

	List<TbProType> getList();


}
