package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.AnimalInInfo;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 生猪进厂表
 * @author Administrator
 *
 */
public interface AnimalInInfoServiceInter {

	String addAnimalInInfo(AnimalInInfo animalInInfo);

	String updateAnimalInInfo(AnimalInInfo animalInInfo);

	String deleteAnimalInInfo(String ids);

	Pager<AnimalInInfo> findList(AnimalInInfo animalInInfo, int page,int rows, String sort, String order);
	/**
	 * 查询屠宰量数量
	 * @return
	 */
	 String getAnimalInList(TsEnterprise enterprise);
	
	 

}
