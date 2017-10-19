package com.hontek.company.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.company.pojo.TeamBuyAcceptanceInfo;
import com.hontek.company.service.inter.TeamBuyAcceptanceInfoServiceInter;
/**
 * 团体消费进货验收信息
 * @author chenan
 *
 */
public class TeamBuyAcceptanceInfoAction extends BaseAction{

	private static final long serialVersionUID = 1763372787142057078L;
	
	private TeamBuyAcceptanceInfoServiceInter teamBuyAcceptanceInfoServiceInter;
	
	private TeamBuyAcceptanceInfo teamBuyAcceptanceInfo;
	private String id;
	
	/*
	 * 添加
	 */
	public void addTeamBuyAcceptanceInfo(){
		jsonMsg = teamBuyAcceptanceInfoServiceInter.addTeamBuyAcceptanceInfo(teamBuyAcceptanceInfo);
		printJsonString(jsonMsg);
	}
	
	/*
	 * 根据comId分页查找
	 */
	public void findTeamBuyAcceptanceInfo(){	
		jsonMsg = teamBuyAcceptanceInfoServiceInter.findTeamBuyAcceptanceInfo(teamBuyAcceptanceInfo,  page, rows,  order,  sort);
		printJsonString(jsonMsg);
	}
	
	/*
	 * 根据id单个删除
	 */
	public void deleteTeamBuyAcceptanceInfo(){
		printJsonString(teamBuyAcceptanceInfoServiceInter.deleteTeamBuyAcceptanceInfo(id));

	}
	
	/*
	 * 修改
	 */
	public void updateTeamBuyAcceptanceInfo(){
		jsonMsg = "修改失败!";
		try{
			teamBuyAcceptanceInfoServiceInter.updateTeamBuyAcceptanceInfo(teamBuyAcceptanceInfo);
			jsonMsg = "修改成功!";
		}catch(Exception e){
			e.printStackTrace();
		}
		this.printJsonString(jsonMsg);
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public TeamBuyAcceptanceInfoServiceInter getTeamBuyAcceptanceInfoServiceInter() {
		return teamBuyAcceptanceInfoServiceInter;
	}

	public void setTeamBuyAcceptanceInfoServiceInter(
			TeamBuyAcceptanceInfoServiceInter teamBuyAcceptanceInfoServiceInter) {
		this.teamBuyAcceptanceInfoServiceInter = teamBuyAcceptanceInfoServiceInter;
	}
	
	public TeamBuyAcceptanceInfo getTeamBuyAcceptanceInfo() {
		return teamBuyAcceptanceInfo;
	}

	public void setTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo) {
		this.teamBuyAcceptanceInfo = teamBuyAcceptanceInfo;
	}


	
	
}
