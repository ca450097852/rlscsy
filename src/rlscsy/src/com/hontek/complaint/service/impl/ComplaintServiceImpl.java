package com.hontek.complaint.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.complaint.dao.ComplaintDao;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.complaint.pojo.TbWorkOrder;
import com.hontek.complaint.service.inter.ComplaintServiceInter;

import com.hontek.complaint.dao.WorkOrderDao;

import com.hontek.info.service.impl.InfoTypeServiceImpl;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * <p>Title: 投诉举报  service</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ComplaintServiceImpl extends BaseServiceImpl implements ComplaintServiceInter {
	
	private ComplaintDao complaintDao;
	private WorkOrderDao workOrderDao;
	

	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}

	public void setWorkOrderDao(WorkOrderDao workOrderDao) {
		this.workOrderDao = workOrderDao;
	}

	/**
	 * 添加投诉
	 */
	public String addComplaint(TbComplaint complaint) {
		String jsonMsg = "添加信息成功!";
		try{
			complaint.setCid(complaintDao.getTableSequence("tb_complaint")+"");
			if(complaint.getSts() == null){
				complaint.setSts(0l);
			}
			this.complaintDao.save(complaint);
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
	public String deleteComplaint(String ids) {
		String jsonMsg = "";
		 
		try {
			String [] complaintArray = ids.split(",");
			int count = 0;
			for (int i = 0; i < complaintArray.length; i++) {
				TbComplaint complaint = this.complaintDao.get(TbComplaint.class, complaintArray[i]);
				this.complaintDao.delete(complaint);
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
	 * 举报列表
	 */
	public String findComplaintList(TbComplaint complaint, int page, int rows,
			String sort, String order) {
		String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(complaint!=null){
			String title = complaint.getTitle();
			Long entId = complaint.getEntId();
			String cid = complaint.getCid();
			Long sts = complaint.getSts();
			Long mainentid = complaint.getMainentid();
			if(cid!=null&&!"".equals(cid)){
				condition.append(" and t1.C_ID = "+cid);
			}
			if(title != null && !"".equals(title)){
				condition.append(" and t1.TITLE like '%"+title+"%'");
			}
			if(entId != null && entId!=1l){
				condition.append(" and t1.ENT_ID ="+entId);
			}
			if(null != sts && sts!=-1l){
				condition.append(" and t1.STS ="+sts);
			}
			if(mainentid != null && mainentid>1l){
				condition.append(" and t1.MAINENTID ="+mainentid);
			}
		}
		condition.append(sortCondtion(sort,order));
		try {
			Pager<TbComplaint> pager = complaintDao.findComplaintList(condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}

	/**
	 * 更新举报
	 */
	public String updateComplaint(TbComplaint complaint) {
		String jsonMsg="修改举报信息成功";
		 try {
			if(complaint != null)
			{
				String isCreatGd = complaint.getIsCreatGd();
				String remark = complaint.getRemark();
				int count = 1;
				String cid ="";
				if(isCreatGd!=null&&"1".equals(isCreatGd))		//是否创建工单，1：是	
				{
					cid = complaint.getCid();
					if(cid!=null&&!"".equals(cid))
					{
						count = this.workOrderDao.countWorkOrder(" and wo.C_ID ="+cid);
					}
					if(count==0)//验证是否已有工单，无工单;->则创建
					{
						TbWorkOrder workOrder = new TbWorkOrder();
						try{
							workOrder.setWoId(this.workOrderDao.getTableSequence("tb_work_order")+0l);
							workOrder.setSts(0l);
							workOrder.setCid(cid);
							this.workOrderDao.save(workOrder);
							complaint.setSts(1l);//举报状态更改为1：处理中
							jsonMsg="处理举报信息成功,并生成工单!";
						}catch(Exception e){
							e.printStackTrace();
						}
					}else if(count>0){
						jsonMsg="修改举报信息成功,已有工单!";
					}
					
				}
				else// if(isCreatGd!=null&&"0".equals(isCreatGd))//是否创建工单，0：否;并且处理结果不为空
				{
					if(remark!=null&&!"".equals(remark))
					{
						complaint.setSts(2l);//举报状态更改为2：已处理
						jsonMsg="操作成功!";
					}
				}
				complaintDao.update(complaint);
			}
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "修改举报信息失败";
		}
		return jsonMsg;
	}
	
	/**
	 * 附件下载
	 */
	@SuppressWarnings("deprecation")
	public InputStream getFileInputStream(String cid) {
		TbComplaint tbComplaint = complaintDao.get(TbComplaint.class, cid);
		//获取项目存放文件目录
		File parent = DirectoryUtil.getProjectFileDataPath(ServletActionContext.getRequest());
		String appName = "/jubao/"+tbComplaint.getAppName();
		File file = new File(parent,appName);
		InputStream in = null;
		try {
			/**
			 * 把filename对象放入到栈顶
			 */
			String filename = new String(tbComplaint.getAppName().getBytes("utf-8"), "ISO8859-1");
			ActionContext.getContext().put("filename",filename);
			in = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in; 
	}

	public void appendComplaint(TbComplaint complaint) {
		// TODO Auto-generated method stub
		try{
			complaint.setCid(complaintDao.getTableSequence("tb_complaint")+"");
			if(complaint.getSts() == null){
				complaint.setSts(0l);
			}
			this.complaintDao.save(complaint);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
