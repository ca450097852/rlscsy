package com.hontek.review.service.inter;

import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;

public interface ProductAppendixServiceInter {

	String findProAppList(TbProductAppendix productAppend, int page, int rows, String order,
			String sort);
	
	String findProductAppList(TbProductAppendix productAppend, int page, int rows);

	String addProAppendix(TbProductAppendix productAppend);

	String deleteApps(String ids);

}
