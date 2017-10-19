package com.hontek.review.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProductDao;
import com.hontek.review.dao.TraceDao;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbTrace;
import com.hontek.review.service.inter.ProductServiceInter;
import com.hontek.review.service.inter.TraceServiceInter;
/**
 * <p>Title: 溯源信息</p>
 * <p>Description: 溯源信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class TraceServiceImpl extends BaseServiceImpl implements TraceServiceInter {
	private TraceDao traceDao;
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public TraceDao getTraceDao() {
		return traceDao;
	}
	public void setTraceDao(TraceDao traceDao) {
		this.traceDao = traceDao;
	}
	/**
	 * 查找溯源信息
	 */
	public String findTraceList(TbTrace trace, int page, int rows, String sort,String order) {
		String jsonMsg = "";
		StringBuffer condition = new StringBuffer();
		if(trace!=null){
			if(trace.getTitle()!=null){
				condition.append(" title like '%"+trace.getTitle()+"%'");
			}
		}
		
		//添加排序
		condition.append(sortCondtion(sort, order));	
		
		Pager<TbTrace> pager = traceDao.findTraceList(condition.toString(), page, rows);
		
		jsonMsg = createEasyUiJson(pager);
		
		return jsonMsg;
	}
	/**
	 * 添加溯源信息
	 */
	public String addTrace(TbTrace trace) {
		String jsonMsg = "添加失败";
		try {
			trace.setTid(traceDao.getTableSequence("TB_TRACE"));
			traceDao.save(trace);
			jsonMsg = "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonMsg;
	}
	/**
	 * 添加同步溯源信息
	 */
	public String appendTraceList(List<TbTrace> traceList) {
		StringBuffer rst = new StringBuffer();
		for(TbTrace trace:traceList){
			String condition = " and p.PRO_CODE='"+trace.getProCode()+"'";
			TbProduct product = productDao.getProductByCondition(condition);
			if(product!=null){
				trace.setTid(traceDao.getTableSequence("TB_TRACE"));
				trace.setProId(product.getProId());
				traceDao.save(trace);
				rst.append(","+trace.getRetroId());
			}
		}
		if(rst.toString()!=null&&!"".equals(rst.toString())){
			return rst.toString().substring(1);
		}
		return "";
	}
	/**
	 * 修改
	 */
	public String updateTrace(TbTrace trace) {
		String jsonMsg = "修改失败!";
		try {
			traceDao.update(trace);
			jsonMsg = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	/**
	 * 删除
	 */
	public String deleteTrace(String ids) {
		String jsonMsg = "删除失败";
		try {
			traceDao.deleteTrace(ids);
			jsonMsg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	
	/**
	 * 根据二维码查询溯源信息
	 */
	public String findTrace(String qrCode) {
		String jsonMsg = "";
		try {
			TbTrace tbTrace = traceDao.findTrace(qrCode);
			if(tbTrace!=null){
				jsonMsg  = getObjectJSON(tbTrace);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	
}
