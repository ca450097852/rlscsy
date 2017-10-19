package com.hontek.element.service.inter;

import com.hontek.element.pojo.FeedUse;


/**
 * 饲料投入品使用记录信息表
 *
 */
public interface FeedUseServiceInter {

	String findList(FeedUse feedUse, int page, int rows,String sort, String order);

	String addFeedUse(FeedUse feedUse);

	String updateFeedUse(FeedUse feedUse);

	String deleteFeedUse(String ids);

}
