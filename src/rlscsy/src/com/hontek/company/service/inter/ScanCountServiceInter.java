package com.hontek.company.service.inter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.ScanCount;

/**
 * <p>Title: 溯源统计表</p>
 * <p>Description: 溯源统计service 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ScanCountServiceInter {


	public String getScanCount(String comId);

	public String addScanCount(ScanCount scanCount);

	/**
	 * 扫描次数统计表
	 */
	public String getScanCountTable(ScanCount scanCount,int page,int rows) ;
	
}
