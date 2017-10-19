package com.hontek.jcmanager.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.MeatOutInfoBaseDao;
import com.hontek.jcmanager.dao.MeatOutInfoDetailDao;
import com.hontek.jcmanager.pojo.MeatOutInfoBase;
import com.hontek.jcmanager.pojo.MeatOutInfoDetail;
import com.hontek.jcmanager.service.inter.MeatOutInfoDetailServiceInter;

public class MeatOutInfoDetailServiceImpl extends BaseServiceImpl implements MeatOutInfoDetailServiceInter{
	private MeatOutInfoDetailDao meatOutInfoDetailDao;
	private MeatOutInfoBaseDao meatOutInfoBaseDao;

	public void setMeatOutInfoBaseDao(MeatOutInfoBaseDao meatOutInfoBaseDao) {
		this.meatOutInfoBaseDao = meatOutInfoBaseDao;
	}

	public void setMeatOutInfoDetailDao(MeatOutInfoDetailDao meatOutInfoDetailDao) {
		this.meatOutInfoDetailDao = meatOutInfoDetailDao;
	}

	public String addMeatOutInfoDetail(MeatOutInfoDetail meatOutInfoDetail) {
		String msg = "添加成功";
		try {
			if(meatOutInfoDetail!=null){
				MeatOutInfoBase meatOutInfoBase = meatOutInfoBaseDao.get(MeatOutInfoBase.class,meatOutInfoDetail.getMoibId());
				if(meatOutInfoBase!=null){
					meatOutInfoDetail.setTranId(meatOutInfoBase.getTranId());
					meatOutInfoDetail.setDest(meatOutInfoBase.getDest());
					
					meatOutInfoDetail.setMoidId(meatOutInfoDetailDao.getTableSequence("tb_meat_out_info_detail".toUpperCase()));
					meatOutInfoDetailDao.save(meatOutInfoDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateMeatOutInfoDetail(MeatOutInfoDetail meatOutInfoDetail) {
		String msg = "添加成功";
		try {
			if(meatOutInfoDetail!=null){
				meatOutInfoDetailDao.update(meatOutInfoDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteMeatOutInfoDetail(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				meatOutInfoDetailDao.executeHql("delete from MeatOutInfoDetail where moidId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<MeatOutInfoDetail> findList(
			MeatOutInfoDetail meatOutInfoDetail, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(meatOutInfoDetail!=null){
			if(meatOutInfoDetail.getMoibId()!=0){
				condition.append(" and moibId="+meatOutInfoDetail.getMoibId());
			}
			if(meatOutInfoDetail.getQuarantineAnimalProductsId()!=null && !"".equals(meatOutInfoDetail.getQuarantineAnimalProductsId())){
				condition.append(" and quarantineAnimalProductsId='"+meatOutInfoDetail.getQuarantineAnimalProductsId()+"' ");
			}
		}
		condition.append(sortCondtion(sort, order));
		
		Pager<MeatOutInfoDetail> pager = meatOutInfoDetailDao.findList(condition.toString(), page,rows);
		
		return pager;
	}
	
	
}
