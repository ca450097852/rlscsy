package com.hontek.record.service.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.AuditRecord;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.pojo.ProAndBatchRecord;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbObjElement;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * 档案service接口
 * @author lzk
 *
 */
public interface RecordServiceInter {

	Pager<TbRecord> findRecordList(String entId, TbRecord record, int page,int rows, String order, String sort);

	String addRecord(String recordType,String typeId, TsUser user, TbRecord record,HttpServletRequest request);

	String updateRecord(TbRecord record);

	List<TbElement> getElements(int recId);
	
	List<TbRecord> findRecordListByPtqDimennno(String dimenno);

	String deleteRecord(String ids);

	String getEntElements(String entId);

	String getElementsByEntId(String entId, TsEnterprise tsEnterprise);

	Pager<TbRecord> findRecordTypeList(String entId, TbRecord record, int page,int rows, String order, String sort);

	String addRecordByTypeId(String ids, CompanyUser user, HttpServletRequest httpServletRequest);

	String addOrDelRecord(String ids, String dels, CompanyUser user,HttpServletRequest request);

	String saveChandi(TbProTypeQrcode proTypeQrcode, Company company,HttpServletRequest request);
	
	/**
	 * web,溯源查询
	 */
	String getRecordByEntId(String entId);

	Pager<TbRecord> getAuditRecord(int entId ,int page, int rows);

	String updateAuditState(String typeId, int entId, TbRecord record);

	List<ProAndBatchRecord> getLoginAuditRecord(String entId);

}
