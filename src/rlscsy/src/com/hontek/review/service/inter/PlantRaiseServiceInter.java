package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbPlantRaise;
import com.hontek.review.pojo.TbTraceAppdix;

/**
 * 施肥/喂养
 * @author dream
 *
 */
public interface PlantRaiseServiceInter {

	String findPlantRaiseList(TbPlantRaise plantRaise, int page, int rows, String order,String sort);
	
	String findPlantRaiseList(TbPlantRaise plantRaise, int page, int rows);

	String addPlantRaise(TbPlantRaise plantRaise, List<TbTraceAppdix> traceAppList);

	String deletePlantRaise(String ids);
	
	String updatePlantRaise(TbPlantRaise plantRaise, List<TbTraceAppdix> traceAppList);

}
