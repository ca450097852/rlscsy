package com.hontek.sys.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.sys.pojo.TbSysconfig;
import com.hontek.sys.service.inter.SysconfigServiceInter;
/**
 * @ClassName: SysconfigAction
 * @Description: TODO(系统配置信息表 Action 类)
 * @date 2015-7-21 下午03:42:25
 * @author qql
 * @version 1.0
 */
public class SysconfigAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private SysconfigServiceInter sysconfigService;
	private TbSysconfig sysconfig;
	
	public TbSysconfig getSysconfig() {
		return sysconfig;
	}

	public void setSysconfig(TbSysconfig sysconfig) {
		this.sysconfig = sysconfig;
	}
	
	public void setSysconfigService(SysconfigServiceInter sysconfigService) {
		this.sysconfigService = sysconfigService;
	}

	/**
	 * 查询系统配置信息
	 */
	public void findSysconfig(){
		jsonMsg = sysconfigService.getSysconfig();		
		System.out.println(jsonMsg);
		printJsonString(jsonMsg);		
	}
	
	/**
	 * 修改系统配置信息
	 */
	public void updateSysconfig(){
		sysconfig.setUpdatetime(DateUtil.formatDateTime());
		sysconfig.setUpdateuser(getLonginUserId());
		jsonMsg = sysconfigService.updateSysconfig(sysconfig);
		printJsonString(jsonMsg);
		
	}

}
