package com.hontek.complaint.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.complaint.pojo.TbWorkOrder;
import com.hontek.complaint.service.inter.WorkOrderServiceInter;
/**
 * 
 * <p>Title:举报工单</p>
 * <p>Description: 举报工单  Action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WorkOrderAction extends BaseAction {
		
		private TbWorkOrder workOrder;
		private WorkOrderServiceInter workOrderService;
		private String woIds;
		
		/**
		* <p>Title: 举报工单分页列表</p> 
		* <p>Description: </p> 
		* param 
		* return
		*/
		public void findWorkOrderList()throws AppException{
			this.printJsonString(this.workOrderService.findWorkOrderList(workOrder, page, rows, sort, order));
		}
		
		/**
		 * 添加举报工单
		 */
		public void addWorkOrder(){
			printJsonString(this.workOrderService.addWorkOrder(workOrder));
		}
		
		/**
		 * 修改举报工单
		 */
		public void updateWorkOrder(){
			printJsonString(this.workOrderService.updateWorkOrder(workOrder));
		}
		
		/**
		 * 删除举报工单
		 */
		public void deleteWorkOrder(){
			printJsonString(this.workOrderService.deleteWorkOrder(woIds));
		}

		//getter and setter
		public TbWorkOrder getWorkOrder() {
			return workOrder;
		}

		public void setWorkOrder(TbWorkOrder workOrder) {
			this.workOrder = workOrder;
		}

		public String getWoIds() {
			return woIds;
		}

		public void setWoIds(String woIds) {
			this.woIds = woIds;
		}

		public void setWorkOrderService(WorkOrderServiceInter workOrderService) {
			this.workOrderService = workOrderService;
		}
		
		
}
