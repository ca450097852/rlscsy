package com.hontek.complaint.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.complaint.pojo.TbComplaint;
import com.hontek.complaint.service.inter.ComplaintServiceInter;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
/**
 * 
 * <p>Title:投诉举报</p>
 * <p>Description: 投诉举报  Action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ComplaintAction extends BaseAction {
		
		private TbComplaint complaint;
		private ComplaintServiceInter complaintService;
		private EnterpriseServiceInter enterprseService;	//区域
		private String cids;
		private File upload;				//上传文件
		private String uploadFileName;
		private String entId1;
		//文件下載字段
		private InputStream inputStream;
		
		public InputStream getInputStream() {
			return inputStream;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		public String getEntId1() {
			return entId1;
		}
		public void setEntId1(String entId1) {
			this.entId1 = entId1;
		}
		public String getUploadFileName() {
			return uploadFileName;
		}
		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}
		
		public File getUpload() {
			return upload;
		}
		public void setUpload(File upload) {
			this.upload = upload;
		}
		public EnterpriseServiceInter getEnterprseService() {
			return enterprseService;
		}
		public void setEnterprseService(EnterpriseServiceInter enterprseService) {
			this.enterprseService = enterprseService;
		}
		public TbComplaint getComplaint() {
			return complaint;
		}
		public void setComplaint(TbComplaint complaint) {
			this.complaint = complaint;
		}
		public void setComplaintService(ComplaintServiceInter complaintService) {
			this.complaintService = complaintService;
		}
		public String getCids() {
			return cids;
		}
		public void setCids(String cids) {
			this.cids = cids;
		}
		
		/**
		 * 
		* <p>Title: 投诉举报分页列表</p> 
		* <p>Description: </p> 
		* param 
		* return
		 */
		public void findComplaintList()throws AppException{
			this.printJsonString(this.complaintService.findComplaintList(complaint, page, rows, sort, order));
		}
		
		/**
		 * 添加投诉举报
		 */
		public void addComplaint(){
			long mainEntId = 177792;
			Object stobj = getSession().getAttribute("entStyle_QT");
			if(stobj!=null){
				EntStyle entStyle = (EntStyle)stobj;
				mainEntId =entStyle.getEntId()==0?177792:entStyle.getEntId();
			}
			System.out.println("addComplaint---mainEntId=="+mainEntId);
			complaint.setMainentid(mainEntId);//所属主体id
			complaint.setSysCode("086020");
			complaint.setCrttime(DateUtil.formatDateTime());
			if(upload!=null && uploadFileName!=null){
				//获取项目存放文件目录
				File parent = DirectoryUtil.getProjectFileDataPath(getRequest());
				//获取具体附件目录
				File fileDir = new File(parent, "jubao");	
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				// 获取扩展名
				String extName = "";// 扩展名			
				if (uploadFileName.lastIndexOf(".") >= 0) {
					extName = uploadFileName.substring(uploadFileName.lastIndexOf("."));
				}
				
				String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
				upload.renameTo(new File(fileDir,newFileName));
				complaint.setAppName(newFileName);
			}
			printJsonString(this.complaintService.addComplaint(complaint));
		}
		
		/**
		 * 修改投诉举报
		 */
		public void updateComplaint(){
			printJsonString(this.complaintService.updateComplaint(complaint));
		}
		
		/**
		 * 删除投诉举报
		 */
		public void deleteComplaint(){
			printJsonString(this.complaintService.deleteComplaint(cids));
		}
		/**
		 * 获得树菜单
		 */
		public void getEntTree(){
			LoginUser user = new LoginUser();
			printJsonString(enterprseService.getEntAreaTree(user, true));
		}
		
		public void getEntTreeByAreaId(){
			Integer areaId = 0;
			Object obj = this.getSession().getAttribute("areaId");
			if(obj!=null && !this.isAdmin())
				areaId = (Integer) obj;
			printJsonString(enterprseService.getEntTreeByAreaId(areaId));
		}
		
		/**
		 * 附件下载
		 */
		public String downloadFile(){
			String cid = ServletActionContext.getRequest().getParameter("cid");
			inputStream = complaintService.getFileInputStream(cid);
			return "download";
		}
}
