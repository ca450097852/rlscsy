package com.hontek.record.service.inter;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.sys.pojo.TsUser;

/**
 * 批次信息service接口
 *
 */
public interface ProTypeBatchServiceInter {
	
	List<TbProTypeBatch> findProTypeBatchList(TbProTypeBatch proTypeBatch);
	
	String findProTypeBatch(TbProTypeBatch proTypeBatch,int page, int rows, String order, String sort);
	//根据交易凭证号找ptbid
	String findPtBIdByProTypeBatch(int ptbId);
	//根据进厂交易凭证号找ptbid
	String findPtBIdByProOut(int ptbId);
	//根据ptbId找批次二维码
	String findDimenNoByPtBId(int ptbId);

	String addProTypeBatch(TbProTypeBatch proTypeBatch,TsUser user,HttpServletRequest request);
	
	String updateProTypeBatch(TbProTypeBatch proTypeBatch);
	
	
	String updateProTypeBatchState(TbProTypeBatch proTypeBatch);

	
	String deleteProTypeBatch(String ids);

	String getCombobox(String ids);

	String updateBatchState(int ptbId);
	
	TbProTypeBatch getProTypeBatchByDimenno(String dimenno);
	
	/**
	 * 根据分类二维码id查找批次列表
	 * @param ptbId
	 * @return
	 */
	List<TbProTypeBatch> findProTypeBatchByPtqId(int ptbId);
	
	
	String getProTypeBatchConut(String ids);

}
