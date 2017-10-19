package com.hontek.complaint.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.complaint.pojo.TbWorkOrder;
import com.hontek.complaint.service.inter.WorkOrderServiceInter;
import com.hontek.complaint.dao.ComplaintDao;
import com.hontek.complaint.dao.WorkOrderDao;
import com.hontek.info.service.impl.InfoTypeServiceImpl;
/**
 * <p>Title: 举报工单 service</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class WorkOrderServiceImpl extends BaseServiceImpl implements WorkOrderServiceInter {
	
	private WorkOrderDao workOrderDao;
	private ComplaintDao complaintDao;
	

	public void setWorkOrderDao(WorkOrderDao workOrderDao) {
		this.workOrderDao = workOrderDao;
	}


	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}


	/**
	 * 添加工单
	 */
	public String addWorkOrder(TbWorkOrder workOrder) {
		String jsonMsg = "添加信息成功!";
		try{
			workOrder.setWoId(workOrderDao.getTableSequence("tb_work_order")+0l);
			if(workOrder.getSts() == null){
				workOrder.setSts(0l);
			}
			this.workOrderDao.save(workOrder);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加信息失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	//log4j 日志处理
	Logger logger = Logger.getLogger(InfoTypeServiceImpl.class);

	/**
	 * 批量删除
	 */
	public String deleteWorkOrder(String ids) {
		String jsonMsg = "";
		 
		try {
			String [] workOrderArray = ids.split(",");
			int count = 0;
			for (int i = 0; i < workOrderArray.length; i++) {
				TbWorkOrder workOrder = this.workOrderDao.get(TbWorkOrder.class, workOrderArray[i]);
				this.workOrderDao.delete(workOrder);
				count++;
			}
			
			jsonMsg = "成功删除 "+count+" 条数据!";
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "删除数据出现异常!"+e.getMessage();
			logger.debug(jsonMsg);
		}
		return jsonMsg;
	}

	/**
	 * 工单列表
	 */
	public String findWorkOrderList(TbWorkOrder workOrder, int page, int rows,
			String sort, String order) {
		String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(workOrder!=null){
			String douser = workOrder.getDouser();
			String title = workOrder.getTitle();
			Long sts = workOrder.getSts();
			if(douser!=null&&!"".equals(douser)){
				condition.append(" and wo.DOUSER like '%"+douser+"%'");
			}
			if(title!=null&&!"".equals(title)){
				condition.append(" and cp.TITLE like '%"+title+"%'");
			}
			if(sts!=null){
				condition.append(" and wo.STS ="+sts);
			}
		}
		condition.append(sortCondtion(sort,order));
		try {
			Pager<TbWorkOrder> pager = workOrderDao.findWorkOrderList(condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}

	public String updateWorkOrder(TbWorkOrder workOrder) {
		String jsonMsg="修改工单信息成功";
		 try {
			if(workOrder != null){
				
				Long sts = workOrder.getSts();
				if(sts!=null&&sts==2){
					String cid = workOrder.getCid();
					TbComplaint complaint = this.complaintDao.get(TbComplaint.class, cid);
					complaint.setSts(2l);
					this.complaintDao.update(complaint);
				}
				workOrder.setDotime(DateUtil.formatDateTime());
				workOrderDao.update(workOrder);
			}
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "修改信息失败";
		}
		return jsonMsg;
	}

}
