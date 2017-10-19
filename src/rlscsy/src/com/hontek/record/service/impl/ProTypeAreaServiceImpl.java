package com.hontek.record.service.impl;

import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.record.dao.MassifDao;
import com.hontek.record.dao.ProTypeAreaDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.pojo.TbMassif;
import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.service.inter.ProTypeAreaServiceInter;
import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.pojo.TbProType;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EnterpriseServiceInter;
/**
 * 分类产地信息实现类
 *
 */
public class ProTypeAreaServiceImpl extends BaseServiceImpl implements ProTypeAreaServiceInter{
	private ProTypeAreaDao proTypeAreaDao;
	private EnterpriseServiceInter enterpriseService;
	private ProTypeQrcodeDao proTypeQrcodeDao;
	private ProTypeDao proTypeDao;
	

	public void setProTypeAreaDao(ProTypeAreaDao proTypeAreaDao) {
		this.proTypeAreaDao = proTypeAreaDao;
	}

	public void setEnterpriseService(EnterpriseServiceInter enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public TbProTypeArea getProTypeAreaById(int ptqId) {
		return proTypeAreaDao.get(TbProTypeArea.class, ptqId);
	}
	
	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}


	//log4j 日志处理
	Logger logger = Logger.getLogger(ProTypeAreaServiceImpl.class);

	/**
	 * 添加产地
	 */
	public String addProTypeArea(TbProTypeArea proTypeArea,TsUser user,HttpServletRequest request) {

		String jsonMsg = "添加产地成功!";
		try{
			
			proTypeArea.setPtaId(proTypeAreaDao.getTableSequence("tb_pro_type_area"));			
			proTypeArea.setEntId(user.getEntId());
			String chandi = proTypeArea.getProvince()+proTypeArea.getCity();
			if(!"区、县".equals(proTypeArea.getTown())){
				chandi+=proTypeArea.getTown();			
			}else{
				proTypeArea.setTown("");
			}
			proTypeArea.setChandi(chandi);			
			this.proTypeAreaDao.save(proTypeArea);			
			
			/*
			List<TbProTypeArea> list = proTypeAreaDao.find("from TbProTypeArea where ptqId="+proTypeArea.getPtqId());
			if(list.size()>2){
				chandi = list.get(0).getChandi()+"(...)";
			}
			
			TbProTypeQrcode proTypeQrcodeNew = proTypeQrcodeDao.get(TbProTypeQrcode.class, proTypeArea.getPtqId());
			TbProType proType = proTypeDao.get(TbProType.class,proTypeQrcodeNew.getTypeId());
						
			TsEnterprise enterprise = enterpriseService.getEnterPirseByEntId(user.getEntId());
			
			//创建分类二维码
			String url = createDimennoImg(request,proTypeQrcodeNew.getDimenno(),proType.getTypeName(),enterprise.getName(),enterprise.getTel(),chandi);
			proTypeQrcodeNew.setUrl(url);
			
			//保存URL信息
			proTypeQrcodeDao.update(proTypeQrcodeNew);
			
			//更新企业二维码
			enterpriseService.getLoginCompanyDimenno(user.getEntId(), request, true);
			*/
			
		}catch(Exception e){
			e.printStackTrace();
			 jsonMsg = "添加产地失败!"+e.getLocalizedMessage();
			 logger.error(jsonMsg);
		}
		return jsonMsg;			
		
	}

	public String deleteProTypeArea(String ids) {
		String jsonMsg ="删除失败!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (String id : idArray) {
					TbProTypeArea proTypeArea = proTypeAreaDao.get(TbProTypeArea.class, Integer.valueOf(id));
					if(proTypeArea!=null){
						proTypeAreaDao.delete(proTypeArea);										
						jsonMsg ="删除成功!";
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		}
		return jsonMsg;
	}

	public String findProTypeArea(TbProTypeArea proTypeArea,int page, int rows, String order, String sort) {
		String jsonMsg ="";
		if(proTypeArea!=null){
			int ptqId = proTypeArea.getPtqId();
			String hql = "from TbProTypeArea where ptqId ="+ptqId;			
			List<TbProTypeArea> list = proTypeAreaDao.find(hql, page, rows);
			
			String countHQL = "select count(*) from TbProTypeArea where ptqId ="+ptqId;
			int total = proTypeAreaDao.count(countHQL).intValue();
			
			Pager<TbProTypeArea> pager = new Pager<TbProTypeArea>();
			
			
			pager.setData(list);
			pager.setTotal(total);
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}
	
	
	public String findProTypeArea(int entId,int page, int rows, String order, String sort) {
		String jsonMsg ="";
		if(entId!=0){
			
			String hql = "from TbProTypeArea where entId ="+entId;			
			List<TbProTypeArea> list = proTypeAreaDao.find(hql, page, rows);
			
			String countHQL = "select count(*) from TbProTypeArea where entId ="+entId;
			int total = proTypeAreaDao.count(countHQL).intValue();
			
			Pager<TbProTypeArea> pager = new Pager<TbProTypeArea>();
			
			pager.setData(list);
			pager.setTotal(total);
						
			jsonMsg =createEasyUiJson(pager);
		}
		return jsonMsg;
	}

	public String updateProTypeArea(TbProTypeArea proTypeArea) {
		String msg = "修改成功!";
		try {
			String chandi = proTypeArea.getProvince()+proTypeArea.getCity();
			if(!"区、县".equals(proTypeArea.getTown())){
				chandi+=proTypeArea.getTown();			
			}else{
				proTypeArea.setTown("");
			}	
			proTypeArea.setChandi(chandi);
			proTypeAreaDao.update(proTypeArea);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败!"+e.getMessage();
		}
		
		return msg;
	}

	public List<TbProTypeArea> findProTypeAreas(int entId) {
		String hql = "from TbProTypeArea";
		if(entId>0){
			 hql = "from TbProTypeArea where entId="+entId;
		}
		
		List<TbProTypeArea>  list = proTypeAreaDao.find(hql);
		
		return list;
	}
	


}
