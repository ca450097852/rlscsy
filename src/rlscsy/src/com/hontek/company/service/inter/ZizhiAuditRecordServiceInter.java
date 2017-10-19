package com.hontek.company.service.inter;

import com.hontek.company.pojo.ZizhiAuditRecord;
import com.hontek.sys.pojo.TsUser;


/**
 * <p>Title: 资质审核记录信息表</p>
 * <p>Description: 资质审核记录表接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface ZizhiAuditRecordServiceInter {

	public String addZizhiAuditRecord(ZizhiAuditRecord record);
	
	public String deleteZizhiAuditRecord(String ids);
	
	public String updateAuditZizhiAuditRecord(String ids,TsUser tsUser);
	
	public String updateZizhiAuditRecord(ZizhiAuditRecord record);
	
	public String findZizhiAuditRecordPagerList(ZizhiAuditRecord tongbao,int page,int rows,String sort,String order);
}
