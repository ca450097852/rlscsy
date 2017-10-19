package com.hontek.record.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.service.inter.ElementServiceInter;
/**
 * <p>Title:档案要素</p>
 * <p>Description: 档案要素  Action</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ElementAction extends BaseAction {
		
		private TbElement element;
		private ElementServiceInter elementService;
		private String elementIds;
		private File fileQueue;
		private String fileQueueFileName;
		
		public TbElement getElement() {
			return element;
		}

		public void setElement(TbElement element) {
			this.element = element;
		}

		public String getElementIds() {
			return elementIds;
		}

		public void setElementIds(String elementIds) {
			this.elementIds = elementIds;
		}

		public void setElementService(ElementServiceInter elementService) {
			this.elementService = elementService;
		}

		public File getFileQueue() {
			return fileQueue;
		}

		public void setFileQueue(File fileQueue) {
			this.fileQueue = fileQueue;
		}

		public String getFileQueueFileName() {
			return fileQueueFileName;
		}

		public void setFileQueueFileName(String fileQueueFileName) {
			this.fileQueueFileName = fileQueueFileName;
		}

		/**
		 * 
		* <p>Title: 档案要素分页列表</p> 
		* <p>Description: </p> 
		* param 
		* return
		 */
		public void findElementList()throws AppException{
			this.printJsonString(this.elementService.findElementList(element, page, rows, sort, order));
		}
		
		/**
		 * 添加档案要素
		 */
		public void addElement(){
			printJsonString(this.elementService.addElement(element));
		}
		
		/**
		 * 修改档案要素
		 */
		public void updateElement(){
			printJsonString(this.elementService.updateElement(element));
		}
		
		/**
		 * 删除档案要素
		 */
		public void deleteElement(){
			printJsonString(this.elementService.deleteElement(elementIds));
		}
		
		/**
		 * 档案要素下拉
		 */
		public void getElementCombobox(){
			printJsonString(this.elementService.getElementCombobox());
		}
		
		
		/**
		 * 上传图标
		 */
		public void uploadFile() {			
			File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "element");		
			
			// 获取扩展名
			String extName = "";// 扩展名			
			if (fileQueueFileName.lastIndexOf(".") >= 0) {
				extName = fileQueueFileName.substring(fileQueueFileName.lastIndexOf("."));
			}
			
			String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
			
			fileQueue.renameTo(new File(fileDir,newFileName));
							
			this.printJsonString(newFileName);
		}
		
		/**
		 * 删除图标
		 */
		public void deleteFile(){
			//获取资质附件目录
			File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "element");		
			File file = new File(fileDir,fileQueueFileName);		
			if(file.exists()){
				this.printJsonString(String.valueOf(file.delete()));
			}		
		}
		
}
