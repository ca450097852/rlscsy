package com.hontek.company.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.TeamBuyAcceptanceInfoDao;
import com.hontek.company.pojo.TeamBuyAcceptanceInfo;
import com.hontek.company.service.inter.TeamBuyAcceptanceInfoServiceInter;
/**
 * 团体消费进货验收信息表
 * @author chenan
 *
 */
public class TeamBuyAcceptanceInfoServiceImpl extends BaseServiceImpl implements  TeamBuyAcceptanceInfoServiceInter{
	
	private TeamBuyAcceptanceInfoDao teamBuyAcceptanceInfoDao;
	
	
	public String addTeamBuyAcceptanceInfo(TeamBuyAcceptanceInfo teamBuyAcceptanceInfo) {
		String jsonMsg = "添加成功";
		try {
			if(teamBuyAcceptanceInfo!=null){
				teamBuyAcceptanceInfo.setTbaiId(teamBuyAcceptanceInfoDao.getTableSequence("tb_agri_inventory_record".toUpperCase()));
				teamBuyAcceptanceInfoDao.save(teamBuyAcceptanceInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}
	
	public String findTeamBuyAcceptanceInfo(
			TeamBuyAcceptanceInfo teamBuyAcceptanceInfo,int page, int rows,
			String order, String sort) {
		String jsonMsg ="";
		if(teamBuyAcceptanceInfo!=null){

			Pager<TeamBuyAcceptanceInfo> pager =teamBuyAcceptanceInfoDao.findTeamBuyAcceptanceInfo(teamBuyAcceptanceInfo, page, rows);
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}
	
	
	public String deleteTeamBuyAcceptanceInfo(String id) {
		String jsonMsg ="删除失败!";
		try {
			if(id!=null&&!"".equals(id)){
				TeamBuyAcceptanceInfo teamBuyAcceptanceInfo = teamBuyAcceptanceInfoDao.get(TeamBuyAcceptanceInfo.class, Integer.valueOf(id));
					if(teamBuyAcceptanceInfo!=null){
						teamBuyAcceptanceInfoDao.delete(teamBuyAcceptanceInfo);										
						jsonMsg ="删除成功!";
					}
				}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}
	
	public void updateTeamBuyAcceptanceInfo(
			TeamBuyAcceptanceInfo teamBuyAcceptanceInfo) {
		teamBuyAcceptanceInfoDao.update(teamBuyAcceptanceInfo);
		
	}

	public TeamBuyAcceptanceInfoDao getTeamBuyAcceptanceInfoDao() {
		return teamBuyAcceptanceInfoDao;
	}

	public void setTeamBuyAcceptanceInfoDao(TeamBuyAcceptanceInfoDao teamBuyAcceptanceInfoDao) {
		this.teamBuyAcceptanceInfoDao = teamBuyAcceptanceInfoDao;
	}

	




	

}
