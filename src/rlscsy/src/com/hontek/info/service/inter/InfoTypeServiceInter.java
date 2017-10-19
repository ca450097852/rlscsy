package com.hontek.info.service.inter;

import com.hontek.info.pojo.TInfoType;



public interface InfoTypeServiceInter {

	public String addInfoType(TInfoType tinfoType);
	
	public String deleteInfoType(String tinfoTypes);
	
	public String findInfoTypeList(TInfoType tinfoType, int page, int rows,
			String sort, String order);
	
	public String getInfoTypeToSelect();
	
	public String updateInfoType(TInfoType tinfoType);
	
	public String getInfoTypeTree();
}
