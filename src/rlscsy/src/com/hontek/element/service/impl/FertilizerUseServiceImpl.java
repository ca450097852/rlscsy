package com.hontek.element.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.FertilizerUseDao;
import com.hontek.element.pojo.FertilizerUse;
import com.hontek.element.service.inter.FertilizerUseServiceInter;

/**
 * 肥料投入品使用记录信息表
 * @author lzk
 *
 */
public class FertilizerUseServiceImpl extends BaseServiceImpl implements FertilizerUseServiceInter{
	private FertilizerUseDao fertilizerUseDao;

	public void setFertilizerUseDao(FertilizerUseDao fertilizerUseDao) {
		this.fertilizerUseDao = fertilizerUseDao;
	}

	public String addFertilizerUse(FertilizerUse fertilizerUse) {
		String jsonMsg = "添加成功";
		try {
			if(fertilizerUse!=null){
				fertilizerUse.setFuid(fertilizerUseDao.getTableSequence("tb_fertilizer_use".toUpperCase()));
				fertilizerUse.setCrttime(DateUtil.formatDateTime());
				fertilizerUseDao.save(fertilizerUse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteFertilizerUse(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from FertilizerUse where fuid in ("+ids+")";
				fertilizerUseDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findList(FertilizerUse fertilizerUse, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(fertilizerUse!=null){
			if(fertilizerUse.getRecId()!=0){
				condition.append(" and recId="+fertilizerUse.getRecId());
			}
		}
		condition.append(sortCondtion(sort, order));
		Pager<FertilizerUse> pager = fertilizerUseDao.findPager(FertilizerUse.class, condition.toString(), page, rows);
		return createEasyUiJson(pager);
	}

	public String updateFertilizerUse(FertilizerUse fertilizerUse) {
		String jsonMsg = "修改成功";
		try {
			if(fertilizerUse!=null){
				fertilizerUseDao.update(fertilizerUse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}
	
}
