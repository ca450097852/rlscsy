package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
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
public class WebObjElementAction extends BaseAction {
		
		private TbObjElement objElement;
		private ObjElementServiceInter objElementService;
		
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


		/**
		 * 查询档案要素关系
		 * param objElement:objTypeId;objId;
		 * 
		 */
		public void findObjElementList(){
			printJsonString(this.createEasyUiJson(this.objElementService.findObjElementList(objElement,  page, rows,  order,  sort)));
		}
		
}
