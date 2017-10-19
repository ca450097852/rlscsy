package com.hontek.record.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.record.pojo.TbElementTemplet;
import com.hontek.record.service.inter.ElementTempletServiceInter;
/**
 * <p>Title:对象档案参照</p>
 * <p>Description: 对象档案参照  Action</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ElementTempletAction extends BaseAction {
		
		private TbElementTemplet elementTemplet;
		private ElementTempletServiceInter elementTempletService;
		private String ids;
		private String archivesType;
		
		private String putong;
		private String sanping;
		private String zhongzhi;
		private String xumu;
		
		

		public TbElementTemplet getElementTemplet() {
			return elementTemplet;
		}

		public void setElementTemplet(TbElementTemplet elementTemplet) {
			this.elementTemplet = elementTemplet;
		}

		public ElementTempletServiceInter getElementTempletService() {
			return elementTempletService;
		}

		public void setElementTempletService(
				ElementTempletServiceInter elementTempletService) {
			this.elementTempletService = elementTempletService;
		}

		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}

		public String getArchivesType() {
			return archivesType;
		}

		public void setArchivesType(String archivesType) {
			this.archivesType = archivesType;
		}

		public String getPutong() {
			return putong;
		}

		public void setPutong(String putong) {
			this.putong = putong;
		}

		public String getSanping() {
			return sanping;
		}

		public void setSanping(String sanping) {
			this.sanping = sanping;
		}

		public String getZhongzhi() {
			return zhongzhi;
		}

		public void setZhongzhi(String zhongzhi) {
			this.zhongzhi = zhongzhi;
		}

		public String getXumu() {
			return xumu;
		}

		public void setXumu(String xumu) {
			this.xumu = xumu;
		}

		/**
		 * 
		* <p>Title: 对象档案参照分页列表</p> 
		* <p>Description: </p> 
		* param 
		* return
		 */
		public void findElementTempletList()throws AppException{
			this.printJsonString(this.elementTempletService.findElementTempletList(elementTemplet, page, rows, sort, order));
		}
		
		/**
		 * 添加对象档案参照
		 */
		public void addElementTemplet(){
			printJsonString(this.elementTempletService.addElementTemplet(elementTemplet));
		}
		
		/**
		 * 修改对象档案参照
		 */
		public void updateElementTemplet(){
			printJsonString(this.elementTempletService.updateElementTemplet(elementTemplet));
		}
		
		/**
		 * 删除对象档案参照
		 */
		public void deleteElementTemplet(){
			printJsonString(this.elementTempletService.deleteElementTemplet(ids));
		}
		
		
		/**
		 * 获取要素--参照--树
		 */
		public void findElementTempletTree(){
			printJsonString(this.elementTempletService.findElementTempletTree(archivesType));
		}
		
		/**
		 * 添加对象档案参照-----左右结构页面
		 */
		public void addElementTemplets(){
			printJsonString(this.elementTempletService.addElementTemplets(archivesType,ids));
		}
		
		/**
		 * 保存对象档案参照-----统一页面
		 */
		public void addAll(){
			printJsonString(this.elementTempletService.addAll(putong, sanping, zhongzhi, xumu));
		}
}
