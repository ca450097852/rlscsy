package com.hontek.review.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.base.LoginUser;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.review.dao.ProBatchDao;
import com.hontek.review.dao.ProductDao;
import com.hontek.review.pojo.TbProBatch;
import com.hontek.review.service.inter.ProBatchServiceInter;
/**
 * <p>Title: 批次信息</p>
 * <p>Description: 批次信息service接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class ProBatchServiceImpl extends BaseServiceImpl implements ProBatchServiceInter {

	private ProBatchDao proBatchDao;
	private ProductDao productDao;
	/**
	 * 注入DAO
	 * @param proBatchDao
	 */
	public void setProBatchDao(ProBatchDao proBatchDao) {
		this.proBatchDao = proBatchDao;
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	Logger logger = Logger.getLogger(this.getClass());
	private static final ResourceBundle bundle = ResourceBundle.getBundle("dimenno");

	
	/**
	 * 添加批次信息
	 * 生成批次二维码
	 */
	public String addProBatch(TbProBatch proBatch,HttpServletRequest request) {
		String msg = "添加批次信息成功!";
		try {
			int proId = proBatch.getProId();
			
			Object[] object = productDao.findProductAndEnt(proId);
			
			if(object==null){
				return "添加批次信息失败!";
			}
			
			//生产日期
			String proTime = proBatch.getProTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			String endNum = dateFormat.format(df.parse(proTime));
			
			String proname = String.valueOf(object[0]);

			String code = String.valueOf(object[1]).concat(endNum);
			
			String companyName = String.valueOf(object[2]);		
			
			if(proBatch.getBatchNo()==null||"".equals(proBatch.getBatchNo())){
				proBatch.setBatchNo(code);
			}		
			proBatch.setDimenno(code);		
			proBatch.setUrl(code.concat(".png"));	
			proBatch.setCodeImg(code.concat(".png"));
			
			proBatch.setBatchId(proBatchDao.getTableSequence("tb_pro_batch".toUpperCase()));
			proBatch.setCrrtime(DateUtil.formatDateTime());
			proBatchDao.save(proBatch);

			
			String url=bundle.getString("proBatchUrl");
			
			String checkUrl = bundle.getString("checkUrl");
			
			String separator = System.getProperty("file.separator") ;
			String imgPath = DirectoryUtil.getProjectParentPath(request)+separator+"nytsyFiles"+separator+"qrcode";
			
			// 创建文件夹
			File file = new File(imgPath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			companyName=companyName==null?"":companyName;			
			String long_codeMaker="";  // 用于保存截取后的剩余长度.
			if(companyName!=null&&companyName.length()>13){	
				long_codeMaker=companyName.substring(13);
				companyName=companyName.substring(0,13);					
			}
			//二维码内容 			
			String encoderContent =url+code;
			
			// 生成二维码图片的名字： 二维码.png
			String imgName1 = code+".png";
			String imgPath1 = imgPath+separator+imgName1;
			
			String imgName2 = code+"_2.png";
			String imgPath2 = imgPath+separator+imgName2;
			
			String imgName3 = code+"_3.png";
			String imgPath3 = imgPath+separator+imgName3;
				   
	        String imgPath_top = request.getRealPath("/");
			String logo_path = imgPath_top+separator+"static"+separator+"image"+separator+"comm"+separator+"logo.png";
	        
			try {
				int width = 200;
				int height = width;
				MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
				BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
				MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);					
				MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);
				
				BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
				MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
											
			// x.w. 第二步：生成打印二维码 
			// 调用生成的图 类
			CreateImg cg = new CreateImg();
			String login_img = imgPath_top+"static"+separator+"image"+separator+"comm"+separator+"code_top.jpg";
			cg.CreateImg_proBatch(login_img,proname,companyName,long_codeMaker,code,checkUrl,imgName1,imgPath1);
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加批次信息出现异常!"+e.getMessage();
			logger.error(msg);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return msg;	
	}
	
	/**
	 * 删除批次信息
	 */
	public String deleteProBatch(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbProBatch proBatch = proBatchDao.get(TbProBatch.class, Integer.valueOf(idArray[i]));
					if(proBatch!=null){
						proBatchDao.delete(proBatch);
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
	 * 分页查询批次信息
	 */
	public String findProBatchList(TbProBatch proBatch, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(proBatch!=null){
				String batchNo = proBatch.getBatchNo();//名称
				if(batchNo!=null&&!"".equals(batchNo)){
					condition.append(" and batchNo like '%"+batchNo+"%' ");
				}
				String dimenno = proBatch.getDimenno();//二维码
				if(dimenno!=null&&!"".equals(dimenno)){
					condition.append(" and dimenno = '"+dimenno+"' ");
				}
				String batchSts = proBatch.getBatchSts();//状态
				if(batchSts!=null&&!"".equals(batchSts)){
					condition.append(" and batchSts = '"+batchSts+"' ");
				}
				int proId = proBatch.getProId();
				if(proId>0&&!"".equals(proId)){
					condition.append(" and proId = "+proId+" ");
				}
				int areaId = proBatch.getBatchId();				
				if(areaId>0&&!"".equals(areaId)){
					condition.append(" and areaId = "+areaId+" ");
				}
			}		
			//添加排序
			condition.append(sortCondtion(sort, order, "crrtime desc"));		
		
			Pager<TbProBatch>  pager = proBatchDao.findPager(TbProBatch.class,condition.toString(), page, rows);
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询批次信息出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改批次信息
	 */
	public String updateProBatch(TbProBatch proBatch) {
		String msg = "修改批次信息成功!";
		try {
			proBatchDao.update(proBatch);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改批次信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	/**
	 * 审核批次信息
	 */
	public String updateProBatchState(String ids,LoginUser loginUser){
		String msg = "审核成功!";
		try {
			TbProBatch proBatch = proBatchDao.get(TbProBatch.class, Integer.valueOf(ids));
			proBatch.setBatchSts("1");
			proBatch.setAuditTime(DateUtil.formatDateTime());
			proBatch.setAuditUser(loginUser.getUserName());
			
			proBatchDao.update(proBatch);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "审核批次信息出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}
	
	/**
	 * 根据二维码获取批次信息
	 */
	public String getProBatchByDimenno(String dimenno) {
		if(dimenno!=null&&!"".equals(dimenno.trim())){
			TbProBatch proBatch = proBatchDao.getProBatchByDimenno(dimenno);
			if(proBatch!=null){
				return this.getJSON(proBatch);
			}
		}
		return "[]";
	}


}
