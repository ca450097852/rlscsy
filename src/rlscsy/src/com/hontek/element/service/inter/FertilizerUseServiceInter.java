package com.hontek.element.service.inter;

import com.hontek.element.pojo.FertilizerUse;


/**
 * 肥料投入品使用记录信息表
 * @author lzk
 *
 */
public interface FertilizerUseServiceInter {

	String findList(FertilizerUse fertilizerUse, int page, int rows,
			String sort, String order);

	String addFertilizerUse(FertilizerUse fertilizerUse);

	String updateFertilizerUse(FertilizerUse fertilizerUse);

	String deleteFertilizerUse(String ids);

}
