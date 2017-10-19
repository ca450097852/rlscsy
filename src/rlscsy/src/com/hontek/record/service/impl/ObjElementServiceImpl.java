package com.hontek.record.service.impl;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.dao.ElementDao;
import com.hontek.record.dao.ObjElementDao;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbObjElement;
import com.hontek.record.service.inter.ObjElementServiceInter;

/**
 * <p>Title: 档案要素关系  service</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ObjElementServiceImpl extends BaseServiceImpl implements ObjElementServiceInter {
	
	private ObjElementDao objElementDao;
	private ElementDao elementDao;


	public void setObjElementDao(ObjElementDao objElementDao) {
		this.objElementDao = objElementDao;
	}
	
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}


	/**
	 * 查询档案要素关系
	 */
	public Pager<TbObjElement> findObjElementList(TbObjElement objElement,
			int page, int rows, String order, String sort) {
		
		StringBuffer condition = new StringBuffer();
		
		if(objElement!=null){
			if(objElement.getObjTypeId()!=0){
				condition.append(" and obj_type_id = "+objElement.getObjTypeId()+" ");
			}
			if(objElement.getObjId()!=0){
				condition.append(" and obj_ID = "+objElement.getObjId()+" ");
			}
		}
		
		condition.append(sortCondtion(sort, order));
		
		Pager<TbObjElement> pager = objElementDao.findObjElementList(condition.toString(),page,rows);
		
		return pager;
	}

	/**
	 * 添加档案要素关系
	 */
	public String addObjElement(TbObjElement objElement) {
		String jsonMsg = "添加档案要素关系成功!";
		try{
			objElement.setRelId(objElementDao.getTableSequence("tb_obj_element"));
			this.objElementDao.save(objElement);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加档案要素关系失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	//log4j 日志处理
	Logger logger = Logger.getLogger(ObjElementServiceImpl.class);

	/**
	 * 批量删除
	 */
	public String deleteObjElement(String ids) {
		String jsonMsg = "";
		 
		try {
			String [] elementArray = ids.split(",");
			int count = 0;
			for (int i = 0; i < elementArray.length; i++) {
				TbObjElement objElement = this.objElementDao.get(TbObjElement.class, elementArray[i]);
				this.objElementDao.delete(objElement);
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
	 * 更新档案要素关系
	 */
	public String updateObjElement(TbObjElement objElement) {
		String jsonMsg="修改档案要素关系成功";
		 try {
			if(objElement != null)
			{
				objElementDao.update(objElement);
			}
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "修改档案要素关系失败";
		}
		return jsonMsg;
	}


	public String updateObjElementList(String jsonMsg) {
		
		String msg = "修改成功！";
		if(jsonMsg!=null&&!"".equals(jsonMsg)){
			try {
				JSONArray arr = JSONArray.fromObject(jsonMsg);
				Iterator<JSONObject> it = arr.iterator();
				
				while(it.hasNext()){
					JSONObject obj = it.next();
					String objId = obj.getString("objId");
					String objTypeId = obj.getString("objTypeId");
					String value = obj.getString("value");
					if(value==null||"".equals(value)){
						continue;
					}
					objElementDao.executeHql("delete from TbObjElement where objId="+objId+" and objTypeId="+objTypeId);
					
					List<TbElement> elements = elementDao.findList(TbElement.class, " and elementId in ("+value+")");
					for(TbElement el : elements){
						TbObjElement objE = new TbObjElement();
						objE.setRelId(objElementDao.getTableSequence("tb_obj_element".toUpperCase()));
						objE.setObjId(Integer.parseInt(objId));
						objE.setObjTypeId(Integer.parseInt(objTypeId));
						objE.setSeq(Integer.parseInt(el.getSeq()));
						objE.setElementId(el.getElementId().intValue());
						objElementDao.save(objE);
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				msg = "修改失败";
			}
			
		}
		
		return msg;
	}
	
	

}
