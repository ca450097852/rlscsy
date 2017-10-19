package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.complaint.service.inter.ComplaintServiceInter;
/**
 * 
 * <p>Title:前台 投诉举报</p>
 * <p>Description: 投诉举报  Action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WebComplaintAction extends BaseAction {
		
		private TbComplaint complaint;
		private ComplaintServiceInter complaintService;
		
		public TbComplaint getComplaint() {
			return complaint;
		}
		public void setComplaint(TbComplaint complaint) {
			this.complaint = complaint;
		}
		public void setComplaintService(ComplaintServiceInter complaintService) {
			this.complaintService = complaintService;
		}
		
		/**
		 * 
		* <p>Title: 投诉举报分页列表</p> 
		* <p>Description: </p> 
		* param 
		* return
		 */
		public void findList()throws AppException{
			this.printJsonString(this.complaintService.findComplaintList(complaint, page, rows, sort, order));
		}
		
}
