package com.hontek.review.service.impl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.DrawDesigns;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.ProductInfoDao;
import com.hontek.company.pojo.ProductInfo;
import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.dao.ProductAppendixDao;
import com.hontek.review.dao.ProductDao;
import com.hontek.review.dao.ProqrcodeDao;
import com.hontek.review.pojo.TbProQrcode;
import com.hontek.review.pojo.TbProType;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.pojo.TraceDataVo;
import com.hontek.review.service.inter.ProductServiceInter;
import com.hontek.synchronous.action.OnlyManager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.pojo.TsEnterprise;
/**
 * <p>Title: 产品管理</p>
 * <p>Description: 产品SERVICE实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductServiceImpl extends BaseServiceImpl implements ProductServiceInter{
	private ProductDao productDao;
	private EnterpriseDao enterpriseDao;
	private ProqrcodeDao qrcodeDao;
	private ProTypeDao proTypeDao;
	private ProductAppendixDao productAppendixDao;
	private ProductInfoDao productInfoDao;
	
	
	public void setProductInfoDao(ProductInfoDao productInfoDao) {
		this.productInfoDao = productInfoDao;
	}

	public void setProductAppendixDao(ProductAppendixDao productAppendixDao) {
		this.productAppendixDao = productAppendixDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}

	public void setQrcodeDao(ProqrcodeDao qrcodeDao) {
		this.qrcodeDao = qrcodeDao;
	}

	public EnterpriseDao getEnterpriseDao() {
		return enterpriseDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/**
	 * 查找产品列表
	 */
	@SuppressWarnings("unchecked")
	public Pager<TbProduct> findProductList(TbProduct product, int page, int rows,boolean flag,long entId,String order,String sort) {
		Map mapCondition = new HashMap();
		if(product!=null){
			if(product.getProName()!=null&&!"".equals(product.getProName())){
				mapCondition.put("p.pro_Name", product.getProName());
			}
			if(product.getProId()>0){
				mapCondition.put("p.pro_Id", product.getProId());
			}
			if(product.getState()!=null&&!"".equals(product.getState())){
				mapCondition.put("p.state", product.getState());
			}
			if(product.getDimenno()!=null&&!"".equals(product.getDimenno())){
				mapCondition.put("q.dimenno", product.getDimenno());
			}
			if(product.getTypeId()!=0){
				mapCondition.put("p.type_Id", product.getTypeId());
			}
			
			if(product.getEntName()!=null&&!"".equals(product.getEntName())){
				mapCondition.put("e.name", product.getEntName());
			}
			if(product.getSysCode()!=null&&!"".equals(product.getSysCode())){
				mapCondition.put("p.sys_code", product.getSysCode());
			}
			
			if(product.getTraceState()!=null&&!"".equals(product.getTraceState())){
				mapCondition.put("traceState", product.getTraceState());
			}
			if(product.getEntCode()!=null&&!"".equals(product.getEntCode())){
				mapCondition.put("e.ent_code", product.getEntCode());
			}
			
		}
		mapCondition.put("orderby",sortCondtion(sort, order));
		if(!flag){
			List list = new ArrayList();
			enterpriseDao.findLowerEnt(list,entId);
			String entIds = "";
			if(list!=null&&!list.isEmpty()){
				TsEnterprise ertp = null;
				for(int i=0;i<list.size();i++){
					ertp = (TsEnterprise) list.get(i);
					entIds += ertp.getEntId()+",";
				}
			}
			
			entIds+=entId;
			
			mapCondition.put("entIds", entIds);
		}
		Pager<TbProduct> pager =  productDao.findProductListJoinEnt(mapCondition,page,rows);
		for(TbProduct pro:pager.getData()){
			pro.setProductInfo(productInfoDao.findList(ProductInfo.class, " and proId="+pro.getProId()));
		}
		for(TbProduct pro:pager.getData()){
			pro.setAppendixList(productAppendixDao.findList(TbProductAppendix.class, " and proId="+pro.getProId()));
		}
		return pager;
	}
	/**
	 * 添加产品 
	 */
	public void addProduct(TbProduct product,ProductInfo productInfo) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		
		TsEnterprise en = enterpriseDao.get(TsEnterprise.class, product.getEntId());
		//补全产品编码
		String proCode = en.getEntCode()+String.valueOf(1000+onlyManager.getProCodeSeq(en.getEntCode())).substring(1);
		product.setProCode(proCode);
		//暂时将二维码与产品编码设置为一致
		product.setDimenno(proCode);
		
		productDao.addProduct(product);
		
		if(productInfo!=null){
			productInfo.setProInfoId(productInfoDao.getTableSequence("TB_PRODUCT_INFO"));
			productInfo.setProId(product.getProId());
			productInfoDao.save(productInfo);
		}
		
		
		TbProQrcode qrcode = new TbProQrcode();
		qrcode.setProId(product.getProId());
		qrcode.setDimenno(product.getDimenno());
		qrcode.setUrl("");
		//产生二维图片
		qrcode.setCodeImg(product.getDimenno()+".png");
		qrcode.setCrrtime(DateUtil.formatDateTime());
		qrcodeDao.addQrcode(qrcode);
		
		String encoderContent = DimennoValueManager.URL+"?dimenno="+qrcode.getDimenno();
		
		int width = 200;
		int height = width;
		MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
		BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
		
		String filePath = DimennoValueManager.QrCodePath+qrcode.getDimenno()+".png";
		
		try {
//			MatrixToImageWriterEx.writeToFile(matrix, "png", filePath, "C:\\Users\\lzk\\Desktop\\store.jpg", logoConfig);//加logo
			MatrixToImageWriter.writeToFile(matrix, "png", new File(filePath));//不加logo
		} catch (IOException e) {
			e.printStackTrace();
		}					
		
	}

	public void updateProduct(TbProduct product,ProductInfo productInfo) {
		productDao.updateProduct(product);
		if(productInfo!=null){
			if(productInfo.getProInfoId()!=0){
				productInfoDao.update(productInfo);
			}else{
				productInfo.setProInfoId(productInfoDao.getTableSequence("TB_PRODUCT_INFO"));
				productInfo.setProId(product.getProId());
				productInfoDao.save(productInfo);
			}
		}
	}

	public void updateProductState(String ids, String state) {
		productDao.updateProductState(ids,state);
	}

	public TbProduct getProductByDimenno(String code) {
		TbProduct tp = productDao.getProductByDimenno(code);
		return tp;
	}

	public TbProduct getProductByProId(int proId) {
		return productDao.getProductByProId(proId);
	}

	public boolean findIsUniqueCode(String dimenno) {
		return productDao.findIsUniqueCode(dimenno);
	}

	public void deleteProduct(String ids) {
		productDao.deleteProduct(ids);
	}
	/**
	 * 添加同步产品
	 * @param proList
	 * @param interAccount
	 * @return
	 */
	public List appendProduct(List<TbProduct> proList,TbInterAccount interAccount) {
		
		List respList = new ArrayList();
		
		Map<String, TsEnterprise> enterMap = new HashMap<String, TsEnterprise>();
		Map<String, TbProType> typeMap = new HashMap<String, TbProType>();
		Map<String, String> logoMap = new HashMap<String, String>();
		OnlyManager onlyManager = OnlyManager.getInstance();
		for(TbProduct tp : proList){
			
			
			
			//读取二维码logo
			//判断是否有logo
			String strUrl = tp.getLogoUrl();
			boolean flag = strUrl!=null&&!"".equals(strUrl);
			String logoFilePath = logoMap.get(strUrl);
			if(flag&&logoFilePath==null){
				try {
					URL url = new URL(strUrl);
					URLConnection conn = url.openConnection();
					
					InputStream in = conn.getInputStream();
					
					byte[] b = new byte[1024]; 
					//后缀
					String suffix = strUrl.substring(strUrl.lastIndexOf("."));
					
					String fileName = UUID.randomUUID().toString()+suffix;
					
					logoFilePath = DimennoValueManager.QrCodeLogoPath+fileName;
					
					File file = new File(logoFilePath);
					FileOutputStream out = new FileOutputStream(file);
					while((in.read(b))!=-1){
						out.write(b);
					}
					
					out.close();
					in.close();
					
					logoMap.put(strUrl, logoFilePath);
				} catch (Exception e) {
					e.printStackTrace();
					//出错，则不继续往下执行
					continue;
				}
			}
			
			//同步产品图片；如果获取产品图片失败，则停止同步该产品
			List<TbProductAppendix> appList = tp.getAppendixList();
			if(appList!=null&&!appList.isEmpty()){
				try {
					for(TbProductAppendix tpa:appList){
						String strUrl1 = tpa.getPath();
						URL url = new URL(strUrl1);
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
						
						String fileName = UUID.randomUUID().toString().replace("-", "")+suffix;
						
						String filePath = DimennoValueManager.ProImg+fileName;
						
						File file = new File(filePath);
						FileOutputStream out = new FileOutputStream(file);
						
						out.write(outStream.toByteArray());
						
						out.close();
						in.close();
						
						tpa.setPath(fileName);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			
			
			
			
			tp.setSysCode(interAccount.getSysCode());
			
			tp.setState("0");
			
			//tp.setUpdateTime(DateUtil.formatDateTime());
			
			//查询机构ID
			TsEnterprise enterprise = enterMap.get(tp.getEntCode());
			if(enterprise==null){
				enterprise = enterpriseDao.getEnterprise(" and entCode='"+tp.getEntCode()+"'");
				if(enterprise==null){
					continue;
				}
				enterMap.put(tp.getEntCode(), enterprise);
			}
			tp.setEntId(enterprise.getEntId());
			//查找分类ID
			TbProType proType = typeMap.get(tp.getTypeNo());
			if(proType==null){
				proType = this.proTypeDao.getProType(" and typeNo='"+tp.getTypeNo()+"'");
				if(proType==null){
					continue;
				}
				typeMap.put(tp.getTypeNo(), proType);
			}
			tp.setTypeId(proType.getTypeId());
			
			//设置产品编码 
			String proCode =tp.getEntCode()+ String.valueOf(1000+onlyManager.getProCodeSeq(tp.getEntCode())).substring(1);
			tp.setProCode(proCode);
			
			Map proIdMap = new HashMap();
			proIdMap.put("proId", tp.getProId());
			proIdMap.put("proCode", proCode);
			
			respList.add(proIdMap);
			
			productDao.addProduct(tp);
			
			//*****************************************************************************
			//产品图片表
			if(appList!=null&&!appList.isEmpty()){
				for(TbProductAppendix tpa : appList){
					tpa.setAppId(productAppendixDao.getTableSequence("TB_PRODUCT_APPENDIX"));
					tpa.setProId(tp.getProId());
					tpa.setUploadTime(DateUtil.formatDateTime());
					productAppendixDao.save(tpa);
				}
			}
			
			//*****************************************************************************
			//二维码附件表
			TbProQrcode qrcode = new TbProQrcode();
			qrcode.setProId(tp.getProId());
			qrcode.setDimenno(tp.getDimenno());
			
			qrcode.setUrl(tp.getDimennoUrl());
			//产生二维图片
			String codeImg = UUID.randomUUID().toString().replaceAll("-", "");
			qrcode.setCodeImg(codeImg+".png");
			qrcode.setCrrtime(DateUtil.formatDateTime());
			
			qrcodeDao.addQrcode(qrcode);
			
			String encoderContent = tp.getDimennoUrl();
			
			int width = 200;
			int height = width;
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
			
			String filePath = DimennoValueManager.QrCodePath+codeImg+".png";
			
			try {
				if(flag){
					MatrixToImageWriterEx.writeToFile(matrix, "png", filePath,logoFilePath, logoConfig);//加logo
				}else{
					MatrixToImageWriter.writeToFile(matrix, "png", new File(filePath));//不加logo
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return respList;
	}
	
	
	/**
	 * 统计企业产品数量
	 * @param product
	 * @return
	 */
	public String findEntProductChart(String areaName){
		
		String condtions = "";
		String chartType ="2";
		if(areaName!=null){
			condtions= areaName;
		}	
		
		List<Map<Object, Object>> oneDimensionsList = productDao.getEntProductList(condtions);	
		Object count = productDao.getEntProductListSize(condtions);
		
		//配置统计图的参数
		DrawDesigns d = new DrawDesigns();
		d.setYAxisName("");
		String xAxisName = "总产品数："+count;
		d.setXAxisName(xAxisName);
				
		d.setOneDimensionsList(oneDimensionsList);

		d.setCaption(areaName+"溯源产品数量前20家企业产品分布图");
		d.setWidth(1000);
		d.setHeight(500);
		String str1 = "";
		str1 += "<tr>";		
		if("".equals(chartType)||"0".equals(chartType)){
			str1 += "<td>" + d.drawColumn3D() + "</td>";	
		}else if("1".equals(chartType)){
			str1 += "<td>" + d.drawLine3D() + "</td>";
		}if("2".equals(chartType)){
			str1 += "<td>" + d.drawPie3D() + "</td>";
		}		
		str1 += "</tr>";			
		return str1;
	
	}
	
	
	/**
	 * 统计产品数量
	 * @param product
	 * @return
	 */
	public String findProductChart(TbProduct product){		
		String condtions = "";
		String chartType ="";
		if(product!=null){
			String typeId = String.valueOf(product.getTypeId());
			String startDate = product.getStartDate();
			String endDate = product.getEndDate();
			chartType = product.getChartType();
			//条件统计
			if("0".equals(typeId)&&startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){			
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,   1);
				ca.add(Calendar.DAY_OF_MONTH,   -1);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH,   11);
				ca.set(Calendar.DATE,   31);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";

			}		
		}	
		
		List<Map<Object, Object>> oneDimensionsList = productDao.getProductList(condtions);	
		Object count = productDao.getProductListSize(condtions);
		
		//配置统计图的参数
		DrawDesigns d = new DrawDesigns();
		d.setYAxisName("");
		String xAxisName = "总产品数："+count;
		d.setXAxisName(xAxisName);
				
		d.setOneDimensionsList(oneDimensionsList);

		d.setCaption(xAxisName);
		d.setWidth(1100);
		d.setHeight(400);
		String str1 = "";
		str1 += "<tr>";		
		if("".equals(chartType)||"0".equals(chartType)){
			str1 += "<td>" + d.drawColumn3D() + "</td>";	
		}else if("1".equals(chartType)){
			str1 += "<td>" + d.drawLine3D() + "</td>";
		}if("2".equals(chartType)){
			str1 += "<td>" + d.drawPie3D() + "</td>";
		}		
		str1 += "</tr>";			
		System.out.println(str1);
		return str1;
	}
	
	/**
	 * 统计产品数量
	 * @param product
	 * @return
	 */
	public String findProductTable(TbProduct product){		
		String condtions = "";
		String title ="";
		if(product!=null){
			String typeId = String.valueOf(product.getTypeId());
			String startDate = product.getStartDate();
			String endDate = product.getEndDate();
			//条件统计
			if("0".equals(typeId)&&startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){		
				title=("产品统计表("+startDate+" 到 "+endDate+")");
				startDate = startDate+" 00:00:00";
				endDate = endDate+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";

			}else if("1".equals(typeId)){//统计当前月份
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.add(Calendar.MONTH,   1);
				ca.add(Calendar.DAY_OF_MONTH,   -1);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";
				
				title=("产品统计表("+(ca.get(Calendar.MONTH)+1)+"月份)");

			}else if("2".equals(typeId)){//统计当前年份
				
				Calendar   ca   =   Calendar.getInstance();
				ca.setTime(new Date());              
				ca.set(Calendar.MONTH, 0);
				ca.set(Calendar.DAY_OF_MONTH,   1);
				Date   firstDate   =   ca.getTime();		
				startDate = DateUtil.formatDate(firstDate)+" 00:00:00";		
				
				ca.set(Calendar.MONTH,   11);
				ca.set(Calendar.DATE,   31);
				Date   lastDate   =   ca.getTime();
				endDate = DateUtil.formatDate(lastDate)+" 23:59:59";
				condtions += " and tc.update_time >='"+startDate+"' and tc.update_time <='"+endDate+"'";
				title=("产品统计表("+ca.get(Calendar.YEAR)+"年度)");

			}		
		}	
		
		List<Map<Object, Object>> oneDimensionsList = productDao.getProductList(condtions);	
		Object count = productDao.getProductListSize(condtions);
			
		StringBuffer html = new StringBuffer("");
		if("".equals(title)){
			title=("产品统计表(所有)");
		}
		
		html.append("<table border='1px' bordercolor='#07aa94' cellspacing='0px' style='border-collapse:collapse' width='1000'><tr height='30px'><td colspan='22' align='center'><b>"+title+"</b></td></tr>");
		
		StringBuffer row1 = new StringBuffer("");
		row1.append("<tr height='30px'>");
		
		StringBuffer row2 = new StringBuffer("");
		row2.append("<tr height='30px'>");
		
		for (int i = 0; i < oneDimensionsList.size(); i++) {
			Map<Object, Object> map = oneDimensionsList.get(i);
			row1.append("<td align='center'>");
			row1.append(map.get("x"));
			row1.append("</td>");

			row2.append("<td align='center'>");
			row2.append(map.get("y"));
			row2.append("</td>");
		}
		
		row1.append("<td align='center'>合计</td></tr>");

		row2.append("<td align='center'>");
		row2.append(count);
		row2.append("</td>");

		html.append(row1);
		html.append(row2);
		html.append("</table>");

		
		return html.toString();
	}

	/**
	 * 动物溯源分页查询
	 */
	public String findAnimalProductList(int page, int rows) {
		String msg = "查询动物溯源数据出现异常!";		
		try {
			Pager<TraceDataVo> pager = productDao.findAnimalProductList(page, rows);
			msg = createEasyUiJson(pager);
		} catch (Exception e) {
			e.printStackTrace();
			msg += e.getMessage();		
		}
		
		return msg;
	}

	public Pager<TbProduct> findProductListForEnt(TbProduct product, int page, int rows,boolean flag,long entId,String order,String sort) {

		Map mapCondition = new HashMap();
		if(product!=null){
			if(product.getProName()!=null&&!"".equals(product.getProName())){
				mapCondition.put("p.pro_Name", product.getProName());
			}
			if(product.getProId()>0){
				mapCondition.put("p.pro_Id", product.getProId());
			}
			if(product.getState()!=null&&!"".equals(product.getState())){
				mapCondition.put("p.state", product.getState());
			}
			if(product.getDimenno()!=null&&!"".equals(product.getDimenno())){
				mapCondition.put("q.dimenno", product.getDimenno());
			}
			if(product.getTypeId()!=0){
				mapCondition.put("p.type_Id", product.getTypeId());
			}
			
			if(product.getEntName()!=null&&!"".equals(product.getEntName())){
				mapCondition.put("e.name", product.getEntName());
			}
//			if(product.getSysCode()!=null&&!"".equals(product.getSysCode())){
//				mapCondition.put("p.sys_code", product.getSysCode());
//			}
			
			if(product.getTraceState()!=null&&!"".equals(product.getTraceState())){
				mapCondition.put("traceState", product.getTraceState());
			}
			
		}
		mapCondition.put("orderby",sortCondtion(sort, order));
		if(!flag){
			List list = new ArrayList();
			enterpriseDao.findLowerEnt(list,entId);
			String entIds = "";
			if(list!=null&&!list.isEmpty()){
				TsEnterprise ertp = null;
				for(int i=0;i<list.size();i++){
					ertp = (TsEnterprise) list.get(i);
					entIds += ertp.getEntId()+",";
				}
			}
			
			entIds+=entId;
			
			mapCondition.put("entIds", entIds);
		}
		
		Pager<TbProduct> pager = productDao.findProductListJoinEntForEnt(mapCondition,page,rows);
		for(TbProduct pro:pager.getData()){
			pro.setProductInfo(productInfoDao.findList(ProductInfo.class, " and proId="+pro.getProId()));
		}
		return pager;
	}

}
