package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.company.dao.ProductInfoDao;
import com.hontek.company.service.inter.ProductInfoServiceInter;

/**
 * 产品补充信息Service
 * @author lzk
 *
 */
public class ProductInfoServiceImpl extends BaseServiceImpl implements ProductInfoServiceInter{
	private ProductInfoDao productInfoDao;

	public void setProductInfoDao(ProductInfoDao productInfoDao) {
		this.productInfoDao = productInfoDao;
	}
	
}
