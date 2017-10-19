package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbProcess;
import com.hontek.review.pojo.TbTraceAppdix;

/**
 * <p>Title: 产品检验</p>
 * <p>Description: 产品检验SERVICE接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public interface ProCheckServiceInter {
	/**
	 * 添加
	 * @param proCheck
	 * @return
	 */
	String addProCheck(TbProCheck proCheck, List<TbTraceAppdix> traceAppList);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String deleteProCheckByIds(String ids);
	/**
	 * 修改
	 * @param proCheck
	 * @return
	 */
	String updateProCheck(TbProCheck proCheck, List<TbTraceAppdix> traceAppList);
	/**
	 * 查询
	 * @param proCheck
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	String findProCheckList(TbProCheck proCheck, int page, int rows,String sort, String order);

}
