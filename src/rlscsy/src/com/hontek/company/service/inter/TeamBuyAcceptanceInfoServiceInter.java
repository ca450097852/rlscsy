package com.hontek.company.service.inter;

import com.hontek.company.pojo.TeamBuyAcceptanceInfo;
import com.hontek.review.pojo.TbProType;


/**
 * 团体消费进货验收信息表
 * @author chenan
 *
 */
public interface TeamBuyAcceptanceInfoServiceInter {
	
	//添加
	public String addTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo);
	//根据comId分页查找
	String findTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo,int page, int rows, String order, String sort);
	//删
	String deleteTeamBuyAcceptanceInfo(String id);
	//改
	void updateTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo);
}
