package com.hontek.record.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.dao.ElementDao;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.service.inter.ElementServiceInter;
import com.hontek.sys.pojo.ComboboxData;

/**
 * <p>Title: 档案要素  service</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ElementServiceImpl extends BaseServiceImpl implements ElementServiceInter {
	
	private ElementDao elementDao;

	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	/**
	 * 添加档案要素
	 */
	public String addElement(TbElement element) {
		String jsonMsg = "添加档案要素成功!";
		try{
			element.setElementId(elementDao.getTableSequence("tb_element")+0l);
			this.elementDao.save(element);
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加档案要素失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	//log4j 日志处理
	Logger logger = Logger.getLogger(ElementServiceImpl.class);

	/**
	 * 批量删除
	 */
	public String deleteElement(String ids) {
		String jsonMsg = "";
		 
		try {
			String [] elementArray = ids.split(",");
			int count = 0;
			for (int i = 0; i < elementArray.length; i++) {
				TbElement element = this.elementDao.get(TbElement.class, Long.parseLong(elementArray[i]));
				this.elementDao.delete(element);
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
	 * 档案要素列表
	 */
	public String findElementList(TbElement element, int page, int rows,
			String sort, String order) {
		String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(element!=null){
			String elementName = element.getElementName();
			if(elementName != null && !"".equals(elementName)){
				condition.append(" and t1.element_name like '%"+elementName+"%'");
			}
			
		}
		condition.append(sortCondtion(sort,order));
		try {
			Pager<TbElement> pager = elementDao.findElementList(condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}

	/**
	 * 更新档案要素
	 */
	public String updateElement(TbElement element) {
		String jsonMsg="修改档案要素信息成功";
		 try {
			if(element != null)
			{
				elementDao.update(element);
			}
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "修改档案要素信息失败";
		}
		return jsonMsg;
	}
	
	
	public String getElementCombobox( ) {
		List<ComboboxData> list = elementDao.getElementCombobox();
		return this.getJSON(list);
	}

}
