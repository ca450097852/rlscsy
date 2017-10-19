package com.hontek.element.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.FeedUseDao;
import com.hontek.element.pojo.FeedUse;
import com.hontek.element.service.inter.FeedUseServiceInter;

/**
 * 饲料投入品使用记录信息表
 *
 */
public class FeedUseServiceImpl extends BaseServiceImpl implements FeedUseServiceInter{
	private FeedUseDao feedUseDao;

	public void setFeedUseDao(FeedUseDao feedUseDao) {
		this.feedUseDao = feedUseDao;
	}

	public String addFeedUse(FeedUse feedUse) {
		String jsonMsg = "添加成功";
		try {
			if(feedUse!=null){
				feedUse.setFeedid(feedUseDao.getTableSequence("tb_feed_use".toUpperCase()));
				feedUse.setCrttime(DateUtil.formatDateTime());
				feedUseDao.save(feedUse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteFeedUse(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from FeedUse where feedid in ("+ids+")";
				feedUseDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findList(FeedUse feedUse, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(feedUse!=null){
			if(feedUse.getRecId()!=0){
				condition.append(" and recId="+feedUse.getRecId());
			}
		}
		condition.append(sortCondtion(sort, order));
		Pager<FeedUse> pager = feedUseDao.findPager(FeedUse.class, condition.toString(), page, rows);
		return createEasyUiJson(pager);
	}

	public String updateFeedUse(FeedUse feedUse) {
		String jsonMsg = "修改成功";
		try {
			if(feedUse!=null){
				feedUseDao.update(feedUse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}
	
}
