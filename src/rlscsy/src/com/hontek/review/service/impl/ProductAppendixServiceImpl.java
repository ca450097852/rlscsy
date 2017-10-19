package com.hontek.review.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProductAppendixDao;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.service.inter.ProductAppendixServiceInter;
/**
 * <p>Title: 产品图片</p>
 * <p>Description: 产品图片</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductAppendixServiceImpl extends BaseServiceImpl implements ProductAppendixServiceInter{
	private ProductAppendixDao productAppendixDao;

	public void setProductAppendixDao(ProductAppendixDao productAppendixDao) {
		this.productAppendixDao = productAppendixDao;
	}

	public String findProAppList(TbProductAppendix product, int page, int rows,
			String order, String sort) {
		StringBuffer condition = new StringBuffer();
		if(product!=null){
			if(product.getProId()!=0){
				condition.append(" and proId="+product.getProId());
			}
		}
		if(sort!=null&&order!=null){
		condition.append(sortCondtion(sort, order));
		}
		Pager<TbProductAppendix> pager = productAppendixDao.findProAppList(condition.toString(),page,rows);
		return createEasyUiJson(pager);
	}
	
	/**
	 * 分页列表
	 */
	public String findProductAppList(TbProductAppendix product, int page, int rows) {
		StringBuffer condition = new StringBuffer();
		if(product!=null){
			if(product.getProId()!=0){
				condition.append(" and app.PRO_ID="+product.getProId());
			}
		}
		Pager<TbProductAppendix> pager = productAppendixDao.findProductAppList(condition.toString(),page,rows);
		return createEasyUiJson(pager);
	}

	public String addProAppendix(TbProductAppendix productAppend) {
		String jsonMsg = "添加失败!";
		try {
			productAppend.setAppId(productAppendixDao.getTableSequence("TB_PRODUCT_APPENDIX"));
			productAppend.setUploadTime(DateUtil.formatDateTime());
			productAppendixDao.save(productAppend);
			jsonMsg = "添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String deleteApps(String ids) {
		String jsonMsg = "删除失败";
		try {
			String condition = " and appId in ("+ids+")";
			Pager<TbProductAppendix> pager = productAppendixDao.findProAppList(condition,1,1000);
			List<TbProductAppendix> list = pager.getData();
			for(TbProductAppendix tpa:list){
				File file = new File(DimennoValueManager.ProImg+tpa.getPath());
				if(file.exists()){
					file.delete();
				}
				productAppendixDao.delete(tpa);
			}
			jsonMsg = "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
}
