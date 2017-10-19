package com.hontek.sys.service.inter;

import com.hontek.sys.pojo.TsSysCol;
/**
 * <p>Title: 栏目菜单表</p>
 * <p>Description: 栏目菜单service 接口类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ColServiceInter {

	public String findColList(TsSysCol col);

	public String addSysCol(TsSysCol col);

	public String updateSysCol(TsSysCol col);

	public String deleteSysCol(String ids);

	public String findFirstColList();

	public String getSysColTree() ;
}
