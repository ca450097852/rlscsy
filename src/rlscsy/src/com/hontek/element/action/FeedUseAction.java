package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.FeedUse;
import com.hontek.element.service.inter.FeedUseServiceInter;
/**
 * 饲料投入品使用记录信息表
 *
 */
public class FeedUseAction extends BaseAction{
	
	private FeedUseServiceInter feedUseServiceInter;
	private FeedUse feedUse;
	private String ids;
	public FeedUse getFeedUse() {
		return feedUse;
	}
	public void setFeedUse(FeedUse feedUse) {
		this.feedUse = feedUse;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void setFeedUseServiceInter(FeedUseServiceInter feedUseServiceInter) {
		this.feedUseServiceInter = feedUseServiceInter;
	}
	
	
	public void findList(){
		jsonMsg = feedUseServiceInter.findList(feedUse,page,rows,sort,order);
		printJsonString(jsonMsg);
	}
	
	public void addFeedUse(){
		jsonMsg = feedUseServiceInter.addFeedUse(feedUse);
		printJsonString(jsonMsg);
	}
	
	public void updateFeedUse(){
		jsonMsg = feedUseServiceInter.updateFeedUse(feedUse);
		printJsonString(jsonMsg);
	}
	
	public void deleteFeedUse(){
		jsonMsg = feedUseServiceInter.deleteFeedUse(ids);
		printJsonString(jsonMsg);
	}
	
}
