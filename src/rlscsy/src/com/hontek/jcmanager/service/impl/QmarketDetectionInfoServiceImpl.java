package com.hontek.jcmanager.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.dao.QmarketDetectionInfoDao;
import com.hontek.jcmanager.pojo.MarketInInfoDetail;
import com.hontek.jcmanager.pojo.QmarketDetectionInfo;
import com.hontek.jcmanager.service.inter.QmarketDetectionInfoServiceInter;

public class QmarketDetectionInfoServiceImpl extends BaseServiceImpl implements QmarketDetectionInfoServiceInter{
	private QmarketDetectionInfoDao qmarketDetectionInfoDao;

	public void setQmarketDetectionInfoDao(
			QmarketDetectionInfoDao qmarketDetectionInfoDao) {
		this.qmarketDetectionInfoDao = qmarketDetectionInfoDao;
	}

	public String addQmarketDetectionInfo(QmarketDetectionInfo qmarketDetectionInfo) {
		String msg = "添加成功";
		try {
			if(qmarketDetectionInfo!=null){
				qmarketDetectionInfo.setQdiId(qmarketDetectionInfoDao.getTableSequence("tb_qmarket_detection_info".toUpperCase()));
				qmarketDetectionInfoDao.save(qmarketDetectionInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateQmarketDetectionInfo(QmarketDetectionInfo qmarketDetectionInfo) {
		String msg = "添加成功";
		try {
			if(qmarketDetectionInfo!=null)
				qmarketDetectionInfoDao.update(qmarketDetectionInfo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteQmarketDetectionInfo(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				qmarketDetectionInfoDao.executeHql("delete from QmarketDetectionInfo where qdiId in ( "+ids+" )");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<QmarketDetectionInfo> findList(
			QmarketDetectionInfo qmarketDetectionInfo, int page, int rows,
			String sort, String order) {
		
		StringBuffer condition = new StringBuffer();
		if(qmarketDetectionInfo!=null){
			if(qmarketDetectionInfo.getComId()!=0){
				condition.append(" and comId="+qmarketDetectionInfo.getComId());
			}
			if(qmarketDetectionInfo.getPtbId()!=0){
				condition.append(" and ptbId="+qmarketDetectionInfo.getPtbId());
			}
			if(qmarketDetectionInfo.getQuarantineAnimalProductsId()!=null && !"".equals(qmarketDetectionInfo.getQuarantineAnimalProductsId())){
				condition.append(" and quarantineAnimalProductsId='"+qmarketDetectionInfo.getQuarantineAnimalProductsId()+"' ");
			}
		}
		condition.append(sortCondtion(sort, order));
		
		Pager<QmarketDetectionInfo> pager = qmarketDetectionInfoDao.findList(condition.toString(), page,rows);
		
		return pager;
	}
	
}
