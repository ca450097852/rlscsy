package com.hontek.element.service.inter;

import com.hontek.element.pojo.Materials;

/**
 * 原料信息表
 * @author IMZK
 *
 */
public interface MaterialsServiceInter {

	String add(Materials materials);

	String update(Materials materials);

	String delete(String ids);

	String findPager(Materials materials, int page, int rows, String sort,
			String order);

}
