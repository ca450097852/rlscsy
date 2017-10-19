package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.company.dao.AssessDetailDao;
import com.hontek.company.pojo.AssessDetail;
import com.hontek.company.service.inter.AssessDetailServiceInter;
/**
 * 企业考核明细表
 * @author chenan
 *
 */
public class AssessDetailServiceImpl extends BaseServiceImpl implements  AssessDetailServiceInter{
	
	private AssessDetailDao assessDetailDao;

	public String addAssessDetail(AssessDetail assessDetail) {
		String jsonMsg = "添加成功";
		try {
			if(assessDetail!=null){
				assessDetail.setCadId(assessDetailDao.getTableSequence("tb_com_assess_detail".toUpperCase()));
				assessDetail.setCrrtime(DateUtil.formatDateTime());
				assessDetailDao.save(assessDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String updateAssessDetail(int id,String checkAudit) {
		String jsonMsg = "修改成功";
		AssessDetail assessDetail= assessDetailDao.get(AssessDetail.class,id);
		assessDetail.setCheckAudit(checkAudit);//更新是否审核
		try {
			assessDetailDao.update(assessDetail);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}


	public String deleteAssessDetailByCaId(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from AssessDetail where ca_id in ("+ids+")";
				assessDetailDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}
	
	public AssessDetailDao getAssessDetailDao() {
		return assessDetailDao;
	}

	public void setAssessDetailDao(AssessDetailDao assessDetailDao) {
		this.assessDetailDao = assessDetailDao;
	}




}
