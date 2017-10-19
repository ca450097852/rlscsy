package com.hontek.sys.service.inter;

import com.hontek.sys.pojo.TbSysconfig;
/**
 * @ClassName: SysconfigServiceInter
 * @Description: TODO(系统配置信息表 service 接口)
 * @date 2015-7-21 下午03:26:05
 * @author qql
 * @version 1.0
 */
public interface SysconfigServiceInter {

	public String updateSysconfig(TbSysconfig col);

	public String getSysconfig() ;
	
	public TbSysconfig getSysconfig2();
}
