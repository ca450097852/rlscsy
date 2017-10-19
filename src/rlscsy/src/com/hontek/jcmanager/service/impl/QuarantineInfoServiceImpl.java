package com.hontek.jcmanager.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.QuarantineInfoDao;
import com.hontek.jcmanager.pojo.QuarantineInfo;
import com.hontek.jcmanager.service.inter.QuarantineInfoServiceInter;

/**
 * 屠宰厂检疫检验信息表
 * @author Administrator
 *
 */
public class QuarantineInfoServiceImpl extends BaseServiceImpl implements QuarantineInfoServiceInter{
	private QuarantineInfoDao quarantineInfoDao;

	public void setQuarantineInfoDao(QuarantineInfoDao quarantineInfoDao) {
		this.quarantineInfoDao = quarantineInfoDao;
	}

	public String addQuarantineInfo(QuarantineInfo quarantineInfo) {
		String msg = "添加成功";
		try {
			if(quarantineInfo!=null){
				quarantineInfo.setQiId(quarantineInfoDao.getTableSequence("tb_quarantine_info".toUpperCase()));
				quarantineInfo.setCreateTime(DateUtil.formatDateTime());
				quarantineInfoDao.save(quarantineInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateQuarantineInfo(QuarantineInfo quarantineInfo) {
		String msg = "修改成功";
		try {
			if(quarantineInfo!=null)
				quarantineInfoDao.update(quarantineInfo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public String deleteQuarantineInfo(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				quarantineInfoDao.executeHql("delete from QuarantineInfo where qiId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<QuarantineInfo> findList(QuarantineInfo quarantineInfo,int page, int rows, String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(quarantineInfo!=null){
			if(quarantineInfo.getComId()!=0){
				condition.append(" and comId="+quarantineInfo.getComId());
			}
			if(quarantineInfo.getQuarantineId()!=null && !"".equals(quarantineInfo.getQuarantineId())){
				condition.append(" and quarantineId='"+quarantineInfo.getQuarantineId()+"' ");
			}			
			if(quarantineInfo.getPtbId()!=0){
				condition.append(" and ptbId="+quarantineInfo.getPtbId());
			}
			if(quarantineInfo.getQuarantineAnimalProductsId()!=null && !"".equals(quarantineInfo.getQuarantineAnimalProductsId())){
				condition.append(" and quarantineAnimalProductsId='"+quarantineInfo.getQuarantineAnimalProductsId()+"' ");
			}
		}		
		condition.append(sortCondtion(sort, order));
		Pager<QuarantineInfo> pager = quarantineInfoDao.findList(condition.toString(),page,rows);
		return pager;
	}

	public QuarantineInfo findByJyh(String quarantineAnimalProductsId){
		List<QuarantineInfo> list = quarantineInfoDao.find(" from QuarantineInfo where quarantineAnimalProductsId = '"+quarantineAnimalProductsId+"'");
		if(list.isEmpty()){
			return null;
		}else {
			return list.get(0);
		}
	}

	public QuarantineInfo getQuarantineInfoByCode(String code) {
		if(code!=null && !"".equals(code)){
			List<QuarantineInfo> list = quarantineInfoDao.find(" from QuarantineInfo where ptbId in (select ptbId from TbProTypeBatch where dimenno='"+code+"') order by checkDate desc");
			if(list!=null && !list.isEmpty())
				return list.get(0);
		}
		
		
		return null;
	}
}
