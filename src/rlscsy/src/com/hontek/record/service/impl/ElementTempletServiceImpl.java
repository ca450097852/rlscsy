package com.hontek.record.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.record.dao.ElementDao;
import com.hontek.record.dao.ElementTempletDao;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbElementTemplet;
import com.hontek.record.service.inter.ElementTempletServiceInter;
import com.hontek.sys.pojo.TreeVo;
import com.hontek.sys.pojo.TsRolePurv;
import com.hontek.sys.pojo.TsSysCol;

/**
 * <p>Title: 对象档案参照  service</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: **公司</p>
 * @author qql
 * @version 1.0
 */
public class ElementTempletServiceImpl extends BaseServiceImpl implements ElementTempletServiceInter {
	
	private ElementTempletDao elementTempletDao;
	
	private ElementDao elementDao;


	public void setElementTempletDao(ElementTempletDao elementTempletDao) {
		this.elementTempletDao = elementTempletDao;
	}
	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	/**
	 * 添加对象档案参照
	 */
	public String addElementTemplet(TbElementTemplet elementTemplet) {
		String jsonMsg = "添加对象档案参照成功!";
		try{
			if(elementTemplet!=null){
				int archivesType = elementTemplet.getArchivesType();
				int elementId = elementTemplet.getElementId();
				List<TbElementTemplet> list = elementTempletDao.find(" from TbElementTemplet where archivesType ="+archivesType+" and elementId ="+elementId);
				
				if(list==null||list.isEmpty()){
					elementTemplet.setTempId(elementTempletDao.getTableSequence("tb_element_templet"));
					this.elementTempletDao.save(elementTemplet);
				}else{
					jsonMsg = "对象档案参照已经存在!";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加对象档案参照失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;
	}
	
	//log4j 日志处理
	Logger logger = Logger.getLogger(ElementTempletServiceImpl.class);

	/**
	 * 批量删除
	 */
	public String deleteElementTemplet(String ids) {
		String jsonMsg = "";
		 
		try {
			String [] elementArray = ids.split(",");
			int count = 0;
			for (int i = 0; i < elementArray.length; i++) {
				TbElementTemplet elementTemplet = this.elementTempletDao.get(TbElementTemplet.class, Integer.parseInt(elementArray[i]));
				this.elementTempletDao.delete(elementTemplet);
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
	 * 对象档案参照列表
	 */
	public String findElementTempletList(TbElementTemplet elementTemplet, int page, int rows,
			String sort, String order) {
		String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(elementTemplet!=null){
			int archivesType = elementTemplet.getArchivesType();
			if(archivesType != 0){
				condition.append(" and t1.archives_type = "+archivesType+"");
			}
			
		}
		condition.append(sortCondtion(sort,order));
		try {
			Pager<TbElementTemplet> pager = elementTempletDao.findElementTempletList(condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}

	/**
	 * 更新对象档案参照
	 */
	public String updateElementTemplet(TbElementTemplet elementTemplet) {
		String jsonMsg="修改对象档案参照信息成功";
		 try {
			if(elementTemplet != null)
			{
				elementTempletDao.update(elementTemplet);
			}
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "修改对象档案参照信息失败";
		}
		return jsonMsg;
	}
	
	
	public String findElementTempletTree(String archivesType){
		String jsonMsg="";		
		//查询当前所有的要素-树形
		List<TreeVo> list = new ArrayList<TreeVo>();
		list = this.elementDao.findElementTree();
		
		//查询当前类型下参照表中的要素
		List<TbElementTemplet> plist = this.elementTempletDao.findElementTempletList(archivesType);
		
		//遍历要素树-参照list，附加选中效果
		for(TreeVo vo:list){
			for(TbElementTemplet elt:plist){
				if(vo.getId()==elt.getElementId()){
					vo.setChecked(true);
					break;
				}
			}
		}
		
		jsonMsg = getJSON(list);
		return jsonMsg;
	}
	
	
	/**
	 * 保存-----左右结构页面
	 */
	public String addElementTemplets(String archivesType,String elementIds) {
		String jsonMsg = "保存成功!";
		try{			
			List<TbElement> elementlist = elementDao.findByElementIds(elementIds);
			
			this.elementTempletDao.deleteByArchivesType(archivesType);

			for(TbElement elem:elementlist){
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(Integer.parseInt(archivesType));
				temp.setElementId(elem.getElementId().intValue());
				this.elementTempletDao.addElementTemplet(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonMsg = "保存失败!"+e.getLocalizedMessage();
		}
		return jsonMsg;
	}
	
	/**
	 * 保存-----统一页面
	 */
	public String addAll(String putong,String sanping,String zhongzhi,String xumu) {
		String jsonMsg = "保存成功!";
		try{
			
			String [] putongArray = putong.split(",");
			String [] sanpingArray = sanping.split(",");
			String [] zhongzhiArray = zhongzhi.split(",");
			String [] xumuArray = xumu.split(",");
			
			this.elementTempletDao.deleteAll();//全部清空
			this.elementTempletDao.setTableSequence();//重置tb_element_templet序列表
			
			for (int i = 0; i < putongArray.length; i++) {
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(1);
				temp.setElementId(Integer.parseInt(putongArray[i]));
				this.elementTempletDao.addElementTemplet(temp);
			}
			
			for (int i = 0; i < sanpingArray.length; i++) {
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(2);
				temp.setElementId(Integer.parseInt(sanpingArray[i]));
				this.elementTempletDao.addElementTemplet(temp);
			}
			
			for (int i = 0; i < zhongzhiArray.length; i++) {
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(3);
				temp.setElementId(Integer.parseInt(zhongzhiArray[i]));
				this.elementTempletDao.addElementTemplet(temp);
			}
			
			for (int i = 0; i < xumuArray.length; i++) {
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(4);
				temp.setElementId(Integer.parseInt(xumuArray[i]));
				this.elementTempletDao.addElementTemplet(temp);
			}
			
			/*
			List<TbElement> putonglist = elementDao.findByElementIds(putong);
			List<TbElement> sanpinglist = elementDao.findByElementIds(sanping);
			List<TbElement> zhongzhilist = elementDao.findByElementIds(zhongzhi);
			List<TbElement> xumulist = elementDao.findByElementIds(xumu);
			
			this.elementTempletDao.deleteAll();//全部清空

			for(TbElement elem:putonglist){
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(1);
				temp.setElementId(elem.getElementId().intValue());
				this.elementTempletDao.addElementTemplet(temp);
			}
			for(TbElement elem:sanpinglist){
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(2);
				temp.setElementId(elem.getElementId().intValue());
				this.elementTempletDao.addElementTemplet(temp);
			}
			for(TbElement elem:zhongzhilist){
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(3);
				temp.setElementId(elem.getElementId().intValue());
				this.elementTempletDao.addElementTemplet(temp);
			}
			for(TbElement elem:xumulist){
				TbElementTemplet temp = new TbElementTemplet();
				temp.setTempId(this.elementTempletDao.getTableSequence("tb_element_templet"));
				temp.setArchivesType(4);
				temp.setElementId(elem.getElementId().intValue());
				this.elementTempletDao.addElementTemplet(temp);
			}
			
			*/
			
			
		}catch(Exception e){
			e.printStackTrace();
			jsonMsg = "保存失败!"+e.getLocalizedMessage();
		}
		return jsonMsg;
	}
	
	
}
