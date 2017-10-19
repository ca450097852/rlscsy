package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.AssessItemDao;
import com.hontek.company.pojo.AssessItem;
import com.hontek.company.service.inter.AssessItemServiceInter;
/**
 * 考核项目表
 * @author chenan
 *
 */
public class AssessItemServiceImpl extends BaseServiceImpl implements  AssessItemServiceInter{
	
	private AssessItemDao assessItemDao;

	public String addAssessItem(AssessItem assessItem) {
		String jsonMsg = "添加成功";
		try {
			if(assessItem!=null){
				assessItem.setItemId(assessItemDao.getTableSequence("tb_assess_item".toUpperCase()));
				assessItem.setCrrtime(DateUtil.formatDateTime());
				assessItemDao.save(assessItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteAssessItem(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from AssessItem where item_id in ("+ids+")";
				assessItemDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	

	public String updateAssessItem(AssessItem assessItem) {
		String jsonMsg = "修改成功";
		try {
			if(assessItem!=null){
				assessItemDao.update(assessItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}

	
	
	public String findAssessItemList(AssessItem assessItem, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		Pager<AssessItem> pager = assessItemDao.findAssessItemList(condition.toString(), page, rows, sort, order);
		return this.createEasyUiJson(pager);
	}
	
	public String findAssessItemListByNodeType(AssessItem assessItem,String nodeType, int page, int rows,
			String sort, String order) {
		Pager<AssessItem> pager = assessItemDao.findAssessItemListByNodeType(nodeType, page, rows, sort, order);
		return this.createEasyUiJson(pager);
	}
	
	public AssessItemDao getAssessItemDao() {
		return assessItemDao;
	}

	public void setAssessItemDao(AssessItemDao assessItemDao) {
		this.assessItemDao = assessItemDao;
	}


}
