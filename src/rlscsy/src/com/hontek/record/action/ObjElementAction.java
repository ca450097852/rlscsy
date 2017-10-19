package com.hontek.record.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.record.pojo.TbObjElement;
import com.hontek.record.service.inter.ObjElementServiceInter;
/**
 * <p>Title:档案要素关系</p>
 * <p>Description: 档案要素关系  Action</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ObjElementAction extends BaseAction {
		
		private TbObjElement objElement;
		private ObjElementServiceInter objElementService;
		private String ids;
		
		public TbObjElement getObjElement() {
			return objElement;
		}

		public void setObjElement(TbObjElement objElement) {
			this.objElement = objElement;
		}

		public ObjElementServiceInter getObjElementService() {
			return objElementService;
		}

		public void setObjElementService(ObjElementServiceInter objElementService) {
			this.objElementService = objElementService;
		}

		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}

		/**
		 * 查询档案要素关系
		 */
		public void findObjElementList(){
			printJsonString(this.createEasyUiJson(this.objElementService.findObjElementList(objElement,  page, rows,  order,  sort)));
		}
		
		/**
		 * 添加档案要素关系
		 */
		public void addObjElement(){
			printJsonString(this.objElementService.addObjElement(objElement));
		}
		
		/**
		 * 修改档案要素关系
		 */
		public void updateObjElement(){
			printJsonString(this.objElementService.updateObjElement(objElement));
		}
		
		/**
		 * 删除档案要素关系
		 */
		public void deleteObjElement(){
			printJsonString(this.objElementService.deleteObjElement(ids));
		}
		
		/**
		 * 
		 */
		public void updateObjElementList(){
			printJsonString(objElementService.updateObjElementList(jsonMsg));
		}
		
}
