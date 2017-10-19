package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ComAssessDao;
import com.hontek.company.pojo.ComAssess;
import com.hontek.company.service.inter.ComAssessServiceInter;
/**
 * 企业考核表
 * @author chenan
 *
 */
public class ComAssessServiceImpl extends BaseServiceImpl implements  ComAssessServiceInter{
	
	private ComAssessDao comAssessDao;
	public int addComAssess(ComAssess comAssess) {
		try {
			if(comAssess!=null){
				comAssess.setCaId(comAssessDao.getTableSequence("tb_com_assess".toUpperCase()));
				comAssess.setCrrtime(DateUtil.formatDateTime());
				comAssess.setState("2"); //设置为未审核
				comAssessDao.save(comAssess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comAssess.getCaId();
	}

	

	public String updateComAssess(int id,String tanwei2,String suyuanchen2 , String lingsou2) {
		String jsonMsg = "修改成功";
		try {
			if(id!=0){
				ComAssess comAssess=comAssessDao.get(ComAssess.class, id);
				comAssess.setState("3"); //设置为已审核
				comAssess.setTanwei2(tanwei2);
				comAssess.setSuyuanchen2(suyuanchen2);
				comAssess.setLingsou2(lingsou2);
				comAssessDao.update(comAssess);
			}
		} catch (Exception e) {
		e.printStackTrace();
		jsonMsg = "修改失败";
	}
		return jsonMsg;
	}

	public String deleteComAssess(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from ComAssess where ca_id in ("+ids+")";
				comAssessDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findComAssessList(ComAssess comAssess, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		Pager<ComAssess> pager = comAssessDao.findComAssessList(condition.toString(), page, rows, sort, order);
		return this.createEasyUiJson(pager);
	}

	public String findComAssessListByCaId(ComAssess comAssess, String caId,
			int page, int rows, String sort, String order) {
		Pager<Object> pager = comAssessDao.findComAssessListByCaId(caId, page, rows, sort, order);
		return this.createEasyUiJson(pager);
	}
	
	public ComAssessDao getComAssessDao() {
		return comAssessDao;
	}

	public void setComAssessDao(ComAssessDao comAssessDao) {
		this.comAssessDao = comAssessDao;
	}
	




}
