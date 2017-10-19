package com.hontek.company.service.inter;

import com.hontek.company.pojo.AuditRecord;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title: 企业审核记录表</p>
 * <p>Description: 企业审核记录接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface AuditRecordServiceInter {

	public String addAuditRecord(AuditRecord auditRecord,TsEnterprise enterprise);
	
	public String deleteAuditRecord(String ids);
	
	public String updateAuditRecord(AuditRecord auditRecord);
	
	public String findAuditRecordPagerList(AuditRecord auditRecord,int page,int rows,String sort,String order);

}
