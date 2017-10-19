package com.hontek.company.service.inter;

import com.hontek.company.pojo.AuditCtrl;

/**
 * <p>Title: 企业审核控制表</p>
 * <p>Description: 企业审核控制接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface AuditCtrlServiceInter {

	public String addAuditCtrl(AuditCtrl auditCtrl);
	
	public String deleteAuditCtrl(String ids);
	
	public String updateAuditCtrl(AuditCtrl auditCtrl);
	
	public String findAuditCtrlPagerList(AuditCtrl auditCtrl,int page,int rows,String sort,String order);

	public AuditCtrl getAuditCtrlByEntId(int entId);

}
