package com.hontek.company.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ZizhiAppendixDao;
import com.hontek.company.dao.ZizhiDao;
import com.hontek.company.dao.ZizhiTypeDao;
import com.hontek.company.pojo.Zizhi;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.pojo.ZizhiType;
import com.hontek.company.service.inter.ZizhiServiceInter;
import com.hontek.review.service.impl.DimennoValueManager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * <p>Title: 企业资质证书表</p>
 * <p>Description: 企业资质证书接口实现 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class ZizhiServiceImpl extends BaseServiceImpl implements ZizhiServiceInter {

	private ZizhiDao zizhiDao;
	private EnterpriseDao enterpriseDao;
	private ZizhiAppendixDao appendixDao;
	private ZizhiTypeDao zizhiTypeDao;
	
	Logger logger = Logger.getLogger(ZizhiServiceImpl.class);
	
	public void setZizhiTypeDao(ZizhiTypeDao zizhiTypeDao) {
		this.zizhiTypeDao = zizhiTypeDao;
	}

	public void setZizhiDao(ZizhiDao zizhiDao) {
		this.zizhiDao = zizhiDao;
	}

	public void setAppendixDao(ZizhiAppendixDao appendixDao) {
		this.appendixDao = appendixDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	/**
	 * 添加企业资质证书
	 */
	public String addZizhi(Zizhi zizhi) {
		String msg = "添加成功!";
		try {
			zizhi.setId(zizhiDao.getTableSequence("tb_zizhi".toUpperCase()));
			zizhiDao.save(zizhi);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteZizhi(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					Zizhi zizhi = zizhiDao.get(Zizhi.class, Integer.valueOf(idArray[i]));
					if(zizhi!=null){
						zizhiDao.delete(zizhi);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询资质证书
	 */
	public String findZizhiPagerList(Zizhi zizhi,int page,int rows,String sort,String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(zizhi!=null){
				String zizhiName = zizhi.getZizhiName();//名称
				if(zizhiName!=null&&!"".equals(zizhiName)){
					condition.append(" and zizhiName like '%"+zizhiName+"%' ");
				}		
				
				int entId = zizhi.getEntId();
				if(entId>0&&!"".equals(entId)){
					condition.append(" and entId = "+entId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<Zizhi>  pager = zizhiDao.findPager(Zizhi.class,condition.toString(), page, rows);
			
			List<Zizhi> list = pager.getData();
			if(list!=null&&!list.isEmpty()){
				List<ZizhiAppendix> zlist = null;
				List<ZizhiType> zizhiTypeList = null;
				for(Zizhi zi:list){
					zlist = appendixDao.find("from ZizhiAppendix where zid="+zi.getId());
					zi.setAppendix(zlist);
					
					zizhiTypeList = zizhiTypeDao.findList(ZizhiType.class, " and zid="+zi.getId());
					zi.setZizhiTypeList(zizhiTypeList);
				}
			}
			
			
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询资质证书出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改
	 */
	public String updateZizhi(Zizhi zizhi) {
		String msg = "修改成功!";
		try {
			zizhiDao.update(zizhi);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	/**
	 * 添加同步资质
	 * @throws IOException 
	 */
	/* (non-Javadoc)
	 * @see com.hontek.company.service.inter.ZizhiServiceInter#appendZizhiList(java.util.List)
	 */
	public String appendZizhiList(List<Zizhi> zzList) throws IOException {
		String jsonMsg = "";
		Map<String, TsEnterprise> entMap = new HashMap<String, TsEnterprise>();
		for(Zizhi zizhi:zzList){
			TsEnterprise enterprise = entMap.get(zizhi.getEntCode());
			if(enterprise==null){
				enterprise = enterpriseDao.getEnterprise(" and entCode='"+zizhi.getEntCode()+"'");
				entMap.put(zizhi.getEntCode(), enterprise);
			}
			if(enterprise!=null){
				//获取图片，如果获取失败则跳出本次循环
				try {
					for(ZizhiAppendix za:zizhi.getAppendix()){
						String strUrl = za.getPath();
						URL url = new URL(strUrl);
						URLConnection conn = url.openConnection();
						
						InputStream in = conn.getInputStream();
						
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						byte[] b = new byte[1024]; 
						int len = 0;
						
						while((len=in.read(b))!=-1){
							outStream.write(b,0,len);
						}
						
						//后缀
						String suffix = strUrl.substring(strUrl.lastIndexOf("."));
						
						String fileName = UUID.randomUUID().toString()+suffix;
						
						String logoFilePath = DimennoValueManager.ZiZhi+fileName;
						
						File file = new File(logoFilePath);
						FileOutputStream out = new FileOutputStream(file);
						
						out.write(outStream.toByteArray());
						
						out.close();
						in.close();
						
						za.setPath(fileName);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
				zizhi.setId(zizhiDao.getTableSequence("tb_zizhi".toUpperCase()));
				zizhi.setEntId(enterprise.getEntId());
				zizhiDao.save(zizhi);
				
				jsonMsg += ","+zizhi.getZid();
				
				for(ZizhiAppendix za:zizhi.getAppendix()){
					za.setAppId(zizhiDao.getTableSequence("tb_zzappendix".toUpperCase()));
					za.setZid(zizhi.getId());
					za.setOrderby(5);
					
					
					appendixDao.save(za);
				}
			}
		}
		
		if(!"".equals(jsonMsg)){
			jsonMsg = jsonMsg.substring(1);
		}
		
		return jsonMsg;
	}
	/**
	 * 判断当前登录用户有没有上传营业执照
	 */
	public String isCreateZizhi(int entId, int ztype) {
		int ct = zizhiDao.isCreateZizhi(entId,ztype);
		return String.valueOf(ct!=0);
	}
	/**
	 * 查找资质附件----手机接口
	 */
	public String findZizhiPagerListforMobile(String entCode, String zType,
			int page, int rows) {
		List list = zizhiDao.findZizhiPagerListforMobile(entCode,zType,page,rows);
		return getJSON(list);
	}

	public String addZizhiAndAppend(Zizhi zizhi,
			List<ZizhiAppendix> appendixList,ZizhiType zizhiType) {
		String msg = "添加成功!";
		try {
			zizhi.setId(zizhiDao.getTableSequence("tb_zizhi".toUpperCase()));
			zizhi.setState("1");
			zizhiDao.save(zizhi);
			
			if(zizhiType!=null&&zizhiType.getTypeName()!=null){
				String remark = zizhiType.getRemark();
				String typeName = zizhiType.getTypeName();
				if(remark!=null&&!"".equals(remark)){
					String [] idArray = remark.split(",");
					for (int i = 0; i < idArray.length; i++) {
						ZizhiType param = new ZizhiType();
						param.setZizhiTypeId(zizhiTypeDao.getTableSequence("TB_ZIZHI_TYPE"));
						param.setTypeName(typeName);
						param.setLevelId(Integer.parseInt(idArray[i].trim()));
						param.setZid(zizhi.getId());
						zizhiTypeDao.save(param);
					}
				}else{
					zizhiType.setZizhiTypeId(zizhiTypeDao.getTableSequence("TB_ZIZHI_TYPE"));
					zizhiType.setZid(zizhi.getId());
					zizhiTypeDao.save(zizhiType);
				}
				
			}
			
			if(appendixList!=null){
				for(ZizhiAppendix za:appendixList){
					if(za!=null&&za.getPath()!=null&&!za.getPath().equals("")){
						za.setAppId(zizhiDao.getTableSequence("tb_zzappendix".toUpperCase()));
						za.setUploadtime(DateUtil.formatDateTime());
						za.setZid(zizhi.getId());
						za.setOrderby(5);
						appendixDao.save(za);
					}					
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	public String findZizhiList(int entId) {
		List<Zizhi> list = zizhiDao.find("from Zizhi where entId="+entId+" order by id desc");
		if(list!=null&&!list.isEmpty()){
			List<ZizhiAppendix> zlist = null;
			List<ZizhiType> zizhiTypeList = null;
			for(Zizhi zi:list){
				zlist = appendixDao.find("from ZizhiAppendix where zid="+zi.getId());
				zi.setAppendix(zlist);
				
				zizhiTypeList = zizhiTypeDao.findList(ZizhiType.class, " and zid="+zi.getId());
				zi.setZizhiTypeList(zizhiTypeList);
			}
		}
		return this.getJSON(list);
	}

	public String updateZizhiAndAppend(Zizhi zizhi,
			List<ZizhiAppendix> appendixList,ZizhiType zizhiType) {
		String msg = "修改成功!";
		try {
			zizhiDao.update(zizhi);
			
			int zizhiId = zizhi.getId();
			//资质类型
			zizhiTypeDao.deleteByTerm(" and zid="+zizhiId);
			if(zizhiType!=null&&zizhiType.getTypeName()!=null){
				String remark = zizhiType.getRemark();
				String typeName = zizhiType.getTypeName();
				if(remark!=null&&!"".equals(remark)){
					remark = remark.trim();
					String [] idArray = remark.split(",");
					ZizhiType param =null;
					for (int i = 0; i < idArray.length; i++) {
						param = new ZizhiType();
						param.setZizhiTypeId(zizhiTypeDao.getTableSequence("TB_ZIZHI_TYPE"));
						param.setTypeName(typeName);
						param.setLevelId(Integer.valueOf(idArray[i].trim()));
						param.setZid(zizhiId);
						zizhiTypeDao.save(param);
					}
				}else{
					zizhiType.setZizhiTypeId(zizhiTypeDao.getTableSequence("TB_ZIZHI_TYPE"));
					zizhiType.setZid(zizhiId);
					zizhiTypeDao.save(zizhiType);
				}
			}
			
			
			appendixDao.deleteByZid(zizhi.getId());
			if(appendixList!=null){
				for(ZizhiAppendix za:appendixList){
					if(za!=null){
						za.setAppId(zizhiDao.getTableSequence("tb_zzappendix".toUpperCase()));
						za.setUploadtime(DateUtil.formatDateTime());
						za.setZid(zizhi.getId());
						za.setOrderby(5);
						appendixDao.save(za);
					}				
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
}
