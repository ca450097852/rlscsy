package com.hontek.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DrawDesigns;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ScanCountDao;
import com.hontek.company.pojo.ScanCount;
import com.hontek.company.service.inter.ScanCountServiceInter;
/**
 * <p>Title: 溯源统计表</p>
 * <p>Description: 溯源统计service 实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ScanCountServiceImpl extends BaseServiceImpl implements ScanCountServiceInter {

	private ScanCountDao scanCountDao;
	
	
	public void setScanCountDao(ScanCountDao scanCountDao) {
		this.scanCountDao = scanCountDao;
	}

	/**
	 * 扫描次数统
	 */
	public String getScanCount(String comId ) {

		String count_sql = "SELECT COUNT(*) FROM tb_scan_count tb WHERE  com_id ="+comId;	

		 int count =  scanCountDao.countBySql(count_sql);	
		 
		return String.valueOf(count);
		
	}
	
	
	/**
	 * 扫描次数统计表
	 */
	public String getScanCountTable(ScanCount scanCount1,int page,int rows) {

		String cd = " ";			
		if(scanCount1!=null){			
			if(scanCount1.getComId()!=0){
				 cd += " and tb.com_id = '"+scanCount1.getComId()+"' ";
			}				
		}
		String sql = "SELECT a.name,a.com_id,a.dimenno,a.total FROM(SELECT name,tb.com_id,dimenno,COUNT(dimenno) AS total	FROM tb_scan_count tb,tb_company b WHERE tb.com_id = b.com_id "+cd+" GROUP BY tb.com_id,name,dimenno) a ORDER BY a.total DESC";	
		
		List<Object[]> list = scanCountDao.findBySql(sql, null, page, rows);
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		
		for (Object[] scanCount : list) {		
						
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("comName", scanCount[0]);
			map.put("comId", scanCount[1]);
			map.put("dimenno", scanCount[2]);
			map.put("count", scanCount[3]);
			listMaps.add(map);
		}
		
		String count_sql = "SELECT COUNT(*) FROM(SELECT name,tb.com_id,dimenno,COUNT(dimenno) AS total	FROM tb_scan_count tb,tb_company b WHERE tb.com_id = b.com_id "+cd+" GROUP BY tb.com_id,name,dimenno) a";	

		 int count =  scanCountDao.countBySql(count_sql);	
		
		Pager pager = new Pager();
		pager.setData(listMaps);
		pager.setTotal(count);
		return createEasyUiJson(pager);
		
	}
	

	/**
	 * 添加扫描次数
	 */
	public String addScanCount(ScanCount scanCount) {
		String msg = "更新成功!";
		try {
			if(scanCount!=null){
				int scId = scanCountDao.getTableSequence("tb_scan_count");
				scanCount.setScId(scId);
				scanCount.setScanTime(DateUtil.formatDateTime());
				scanCountDao.save(scanCount);	
								
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败!"+e.getLocalizedMessage();
		}		
		return msg;
	}

}
