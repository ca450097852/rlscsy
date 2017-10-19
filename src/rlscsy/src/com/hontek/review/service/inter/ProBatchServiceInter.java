package com.hontek.review.service.inter;

import javax.servlet.http.HttpServletRequest;

import com.hontek.comm.base.LoginUser;
import com.hontek.review.pojo.TbProBatch;
/**
 * <p>Title: 批次信息</p>
 * <p>Description: 批次信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface ProBatchServiceInter {

	public String addProBatch(TbProBatch proBatch,HttpServletRequest request);

	public String deleteProBatch(String ids);

	public String updateProBatch(TbProBatch proBatch);
	
	public String updateProBatchState(String ids,LoginUser loginUser);

	public String findProBatchList(TbProBatch proBatch, int page, int rows, String sort,String order);

	public String getProBatchByDimenno(String dimenno);

}
