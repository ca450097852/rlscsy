package com.hontek.company.service.inter;

import com.hontek.company.pojo.ValidCodeRecord;


/**
 *
 */
public interface ValidCodeRecordServiceInter {

	public String addValidCodeRecord(ValidCodeRecord validCodeRecord);
	
	public String findPagerList(ValidCodeRecord validCodeRecord,int page,int rows,String sort,String order);

}
