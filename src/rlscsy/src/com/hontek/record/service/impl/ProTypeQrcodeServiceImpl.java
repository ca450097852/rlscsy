package com.hontek.record.service.impl;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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
import com.hontek.company.dao.CompanyDao;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.dao.MassifDao;
import com.hontek.record.dao.ProTypeAreaDao;
import com.hontek.record.dao.ProTypeBatchDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.pojo.Combo;
import com.hontek.record.pojo.TbMassif;
import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.service.inter.ProTypeQrcodeServiceInter;
import com.hontek.review.pojo.TreeVo;
import com.hontek.sys.pojo.TagStyle;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.TagStyleServiceInter;
/**
 * 分类二维码信息实现类
 * @author lzk
 *
 */
public class ProTypeQrcodeServiceImpl extends BaseServiceImpl implements ProTypeQrcodeServiceInter{
	private ProTypeQrcodeDao proTypeQrcodeDao;
	private CompanyDao companyDao;
	private ProTypeAreaDao proTypeAreaDao;
	private ProTypeBatchDao proTypeBatchDao;
	
	private MassifDao massifDao;
	private TagStyleServiceInter tagStyleService;//二维码风格
	
	public void setMassifDao(MassifDao massifDao) {
		this.massifDao = massifDao;
	}
	
	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public TbProTypeQrcode getProTypeQrcodeById(int ptqId) {
		return proTypeQrcodeDao.get(TbProTypeQrcode.class, ptqId);
	}
	
	public void setProTypeBatchDao(ProTypeBatchDao proTypeBatchDao) {
		this.proTypeBatchDao = proTypeBatchDao;
	}

	public void setProTypeAreaDao(ProTypeAreaDao proTypeAreaDao) {
		this.proTypeAreaDao = proTypeAreaDao;
	}


	public void setTagStyleService(TagStyleServiceInter tagStyleService) {
		this.tagStyleService = tagStyleService;
	}


	//log4j 日志处理
	Logger logger = Logger.getLogger(ProTypeQrcodeServiceImpl.class);
	
	
	/**
	 * 企业分类溯源列表
	 */
	public String findProTypeQrcodeList(TbProTypeQrcode ptq,int page,int rows,String sort,String order){
		String jsonMsg = "";
		
		StringBuffer condition = new StringBuffer("");
		if(ptq!=null){
			int ptqId = ptq.getPtqId();
			String typeName = ptq.getTypeName();
			String companyName = ptq.getCompanyName();
			String dimenno = ptq.getDimenno();
			String sysCode = ptq.getSysCode();
			
			if(ptqId !=0){
				condition.append(" and t1.ptq_id  = "+ptqId+"");
			}
			if(dimenno !=null&&!"".equals(dimenno)){
				condition.append(" and t1.dimenno = "+dimenno+"");
			}
			if(ptq.getEntId()!=0){
				condition.append(" and t1.ent_id = "+ptq.getEntId());
			}
			if(companyName !=null&&!"".equals(companyName)){
				condition.append(" and t2.name like '%"+companyName+"%'");
			}
//			if(sysCode !=null&&!"".equals(sysCode)){
//				condition.append(" and t2.sys_code like '%"+sysCode+"%'");
//			}
			if(typeName !=null&&!"".equals(typeName)){
				condition.append(" and t3.type_name like '%"+typeName+"%'");
			}
		}
		condition.append(sortCondtion(sort,order));
		
		try {
			Pager<TbProTypeQrcode> pager = this.proTypeQrcodeDao
					.findProTypeQrcodeList(condition.toString(), page, rows);
			List<TbProTypeQrcode> list = pager.getData();
			for(TbProTypeQrcode ptqcode:list){
				List<TbProTypeArea> list2 = proTypeAreaDao.find("from TbProTypeArea where entId="+ptqcode.getEntId());
				
				List<TbMassif> chlist = this.massifDao.findListByPtqId(ptqcode.getPtqId());
				
				if(list2!=null&&!list2.isEmpty()){
					ptqcode.setProTypeArea(list2);
				}
				if(chlist!=null&&!chlist.isEmpty()){
					ptqcode.setChlist(chlist);
				}
			}
			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		
		return jsonMsg;
	}
	
	/**
	 * 企业分类溯源列表(畜牧类4条，种植类8条)
	 */
	public String findTwoProTypeQrcode(String entId){
		String jsonMsg = "";
		String entCon = "";
		if(entId!=null&&!entId.equals("")){
			entCon = " and (t2.ent_id="+entId+" or t2.parent_id="+entId+")";
		}
		try {
			Pager<TbProTypeQrcode> ptqlist = this.proTypeQrcodeDao.findTwoProTypeQrcode(" and t3.type_class='2' "+entCon, 1, 4);
			
			Pager<TbProTypeQrcode> ptqlist2 = this.proTypeQrcodeDao.findTwoProTypeQrcode(" and t3.type_class='1' "+entCon, 1, 4);
			
			jsonMsg = "{\"animal\":" + getJSON(ptqlist.getData()) + ",\"plant\":"+ getJSON(ptqlist2.getData()) + "}";
			
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		return jsonMsg;
	}

	/**
	 * 门户分页查询
	 */
	public String findWebProTypeQrcode(int page,int rows,String typeClass,String entId){

		String jsonMsg = "";
		String entCon = "";
		if(entId!=null&&!entId.equals("")){
			entCon = " and (t2.ent_id="+entId+" or t2.parent_id="+entId+" or t2.parent_id in (select ent_id from ts_enterprise where parent_id="+entId+") )";
		}
		
		try {
			Pager<TbProTypeQrcode> pager = this.proTypeQrcodeDao.findTwoProTypeQrcode(" and t3.type_class='"+typeClass+"'"+entCon, page, rows);
						
			jsonMsg = createEasyUiJson(pager);
			
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		return jsonMsg;
	
	}
	
	
	/**
	 * app分页查询
	 */
	public String findAppProTypeQrcode(int page,int rows,String param,String typeClass){

		String jsonMsg = "";
		String condition = "";
		try {
			if(param!=null&&!param.equals("")){
			   Pattern pattern = Pattern.compile("[0-9]*"); 
			   Matcher isNum = pattern.matcher(param);
			   if( isNum.matches() ){
				   condition += " and t1.dimenno = "+param;
			   }else{ 
				   condition += " and (t3.type_name like '%"+param+"%' or t2.name like '%"+param+"%' or t1.pro_name like '%"+param+"%') ";
			   }
			}
			
			if(typeClass!=null&&!typeClass.equals("")){
				 condition += " and t3.type_class='"+typeClass+"'";
			}
			
			Pager<TbProTypeQrcode> pager = this.proTypeQrcodeDao.findTwoProTypeQrcode(condition, page, rows);
						
			jsonMsg = createEasyUiJson(pager);
			
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg="查找出现异常"+e.getMessage();
			logger.debug(jsonMsg);
		}
		return jsonMsg;
	
	}
	
	public List<TbProTypeQrcode> findProTypeQrcode(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,Company user) {
		StringBuffer condition = new StringBuffer();
		//condition.append(" and chandi is not null ");
		if(proTypeQrcode!=null){
			if(proTypeQrcode.getEntId()!=0){
				condition.append(" and t1.ent_Id=" + proTypeQrcode.getEntId());
			}
			if(proTypeQrcode.getTypeId()!=0){
				condition.append(" and t1.type_Id="+proTypeQrcode.getTypeId());
			}
			if(proTypeQrcode.getProState()!=null && !"".equals(proTypeQrcode.getProState())){
				condition.append(" and t1.pro_state='"+proTypeQrcode.getProState()+"' ");
			}
			if(proTypeQrcode.getIsbatch() != null){
				condition.append(" and t1.is_batch="+proTypeQrcode.getIsbatch());
			}
		}
		
		List<TbProTypeQrcode> list = proTypeQrcodeDao.getLoginProTypeQrcode(condition.toString());	
		for (TbProTypeQrcode tbProTypeQrcode : list) {		
			String chandi = "";
			List<TbProTypeArea> list2 = proTypeAreaDao.find("from TbProTypeArea where ptaId in(select distinct ptaId from TbMassif where ptqId="+tbProTypeQrcode.getPtqId()+")");
			System.out.println(list2.size());
			if(list2.size()>=1){
				chandi = list2.get(0).getChandi();
			}
			if(list2.size()>=2){
				chandi = list2.get(0).getChandi()+"(...)";
			}

			Company company = companyDao.findCompany(user.getComId());		
			//TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getBodyEntId());//查找主体的二维码风格设置；			
			//创建分类二维码
			String url = createDimennoImg(request,tbProTypeQrcode.getDimenno(),tbProTypeQrcode.getTypeName(),company.getName(),company.getPhone(),chandi);
			//String url = createDimennoImg2(request,tbProTypeQrcode.getDimenno(),tbProTypeQrcode.getTypeName(),enterprise.getName(),enterprise.getTel(),chandi,tagStyle);
			tbProTypeQrcode.setUrl(url);
			//保存URL信息
			proTypeQrcodeDao.update(tbProTypeQrcode);
		}
		return list;
	}
	
	public List<TbProTypeQrcode> findProTypeQrcodebyEntId(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,int entId) {
		StringBuffer condition = new StringBuffer();
		//condition.append(" and chandi is not null ");
		if(proTypeQrcode!=null){
			if(proTypeQrcode.getEntId()!=0){
				condition.append(" and t1.ent_Id=" + proTypeQrcode.getEntId());
			}
			if(proTypeQrcode.getTypeId()!=0){
				condition.append(" and t1.type_Id="+proTypeQrcode.getTypeId());
			}
		}
		
		List<TbProTypeQrcode> list = proTypeQrcodeDao.getLoginProTypeQrcode(condition.toString());
		
		
		for (TbProTypeQrcode tbProTypeQrcode : list) {
			
			String chandi = "";
			List<TbProTypeArea> list2 = proTypeAreaDao.find("from TbProTypeArea where ptaId in(select distinct ptaId from TbMassif where ptqId="+tbProTypeQrcode.getPtqId()+")");
		System.out.println(list2.size());
			if(list2.size()>=1){
				chandi = list2.get(0).getChandi();
			}
			if(list2.size()>=2){
				chandi = list2.get(0).getChandi()+"(...)";
			}
									
			Company enterprise = companyDao.findCompany(entId);
			
			//TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getBodyEntId());//查找主体的二维码风格设置；
			
			//创建分类二维码
			//String url = createDimennoImg(request,tbProTypeQrcode.getDimenno(),tbProTypeQrcode.getTypeName(),enterprise.getName(),enterprise.getTel(),chandi,tagStyle);
			String url = createDimennoImg(request,tbProTypeQrcode.getDimenno(),tbProTypeQrcode.getTypeName(),enterprise.getName(),enterprise.getPhone(),chandi);
			tbProTypeQrcode.setUrl(url);
			//保存URL信息
			proTypeQrcodeDao.update(tbProTypeQrcode);
									
			
		}

		return list;
	}
	
	/**
	 * 批次二维码
	 */
	public List<TbProTypeBatch> findProTypeBatch(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,Company company){
		if(proTypeQrcode!=null){
			int ptqId = proTypeQrcode.getPtqId();
//			 List<TbProTypeBatch> batchList = proTypeBatchDao.findProTypeBatchList(ptqId);
			 String term = " and t1.ptq_Id="+ptqId;
			 if(proTypeQrcode.getProState()!=null && !"".equals(proTypeQrcode.getProState())){
				 term += " and t1.batch_state='"+proTypeQrcode.getProState()+"' ";
			 }
			 List<TbProTypeBatch> batchList = proTypeBatchDao.findProTypeBatchListByTerm(term);

			String chandi = "";
			List<TbProTypeArea> list2 = proTypeAreaDao.find("from TbProTypeArea where ptaId in(select distinct ptaId from TbMassif where ptqId="+ptqId+")");
			if(list2.size()>=1){
				chandi = list2.get(0).getChandi();
			}
			if(list2.size()>=2){
				chandi = list2.get(0).getChandi()+"(...)";
			}
									
			Company enterprise = companyDao.findCompany(company.getComId());
			
			//TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getBodyEntId());//查找主体的二维码风格设置；
			
		 List<TbProTypeQrcode> list = proTypeQrcodeDao.getLoginProTypeQrcode(" and t1.ptq_id="+ptqId);
		 
		 if(list!=null&&list.size()==1){
			 
			 String typeName =  list.get(0).getTypeName();
			 
			 for (TbProTypeBatch tbProTypeBatch : batchList) {				 
					 createDimennoImg(request,tbProTypeBatch.getDimenno(),typeName,enterprise.getName(),enterprise.getPhone(),chandi);
					 //createDimennoImg2(request,tbProTypeBatch.getDimenno(),typeName,enterprise.getName(),enterprise.getTel(),chandi,tagStyle);
			}
			 
		 }
		 return batchList;

		}
		return null;
	}
	
	public List<TbProTypeBatch> findProTypeBatchbyEntId(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,int entId){
		if(proTypeQrcode!=null){
			int ptqId = proTypeQrcode.getPtqId();
			 List<TbProTypeBatch> batchList = proTypeBatchDao.findProTypeBatchList(ptqId);

			String chandi = "";
			List<TbProTypeArea> list2 = proTypeAreaDao.find("from TbProTypeArea where ptaId in(select distinct ptaId from TbMassif where ptqId="+ptqId+")");
			if(list2.size()>=1){
				chandi = list2.get(0).getChandi();
			}
			if(list2.size()>=2){
				chandi = list2.get(0).getChandi()+"(...)";
			}
									
			Company enterprise = companyDao.findCompany(entId);
			
			//TagStyle tagStyle = tagStyleService.getTagStyleInfo(enterprise.getBodyEntId());//查找主体的二维码风格设置；
			
		 List<TbProTypeQrcode> list = proTypeQrcodeDao.getLoginProTypeQrcode(" and t1.ptq_id="+ptqId);
		 
		 if(list!=null&&list.size()==1){
			 
			 String typeName =  list.get(0).getTypeName();
			 
			 for (TbProTypeBatch tbProTypeBatch : batchList) {				 
					 createDimennoImg(request,tbProTypeBatch.getDimenno(),typeName,enterprise.getName(),enterprise.getPhone(),chandi);
					 //createDimennoImg2(request,tbProTypeBatch.getDimenno(),typeName,enterprise.getName(),enterprise.getTel(),chandi,tagStyle);
			}
			 
		 }
		 return batchList;

		}
		return null;
	}

	
	
	
	public List<TbProTypeQrcode> findProTypeQrcode(TbProTypeQrcode proTypeQrcode) {
		StringBuffer condition = new StringBuffer();
		if(proTypeQrcode!=null){
			if(proTypeQrcode.getEntId()!=0){
				condition.append(" and t1.ent_Id=" + proTypeQrcode.getEntId());
			}
			if(proTypeQrcode.getTypeId()!=0){
				condition.append(" and t1.type_Id="+proTypeQrcode.getTypeId());
			}
		}		
		List<TbProTypeQrcode> list = proTypeQrcodeDao.getLoginProTypeQrcode(condition.toString());
		return list;
	}

	public String deleteProTypeQrcode(String ids, TsUser user, HttpServletRequest request) {
		String msg = "删除档案成功！";
		try {
			//删除档案要素关系表
			proTypeQrcodeDao.executeHql("delete from TbObjElement where objId in (select ptqId from TbProTypeQrcode where typeId in ("+ids+")) and objTypeId in (3,4)");
			//删除相关档案
			proTypeQrcodeDao.executeHql("delete from TbRecord where objId in (select ptqId from TbProTypeQrcode where typeId in ("+ids+")) and objTypeId in (3,4)");
			//删除分类二维码信息
			proTypeQrcodeDao.executeHql("delete from TbProTypeQrcode where typeId in ("+ids+")");
			//重新产生企业二维码图片
			//enterpriseService.getLoginCompanyDimenno(user.getEntId(), request, true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除档案失败。";
		}
		return msg;
	}
	
	
	public List<Object> findTypeName(String recId){
		return proTypeQrcodeDao.findTypeName(recId);
	}

	
	public int findTypeConut(int comId){				
		//判断有没有分类
		String sql2 ="select count(*) from tb_pro_type_qrcode t1 where t1.ent_id="+comId; 
		int count2 = proTypeQrcodeDao.countBySql(sql2);
		if(count2==0){
			return 1;
		}
		//String sql = "select count(*) from tb_pro_type_qrcode where chandi is not null and ent_id="+loginUser.getEntId();
		String sql ="select count(*) from tb_pro_type_qrcode t1 LEFT JOIN tb_massif t2 ON t1.ptq_id= t2.ptq_id where t2.pta_id is null AND t1.ent_id="+comId; 
		int count = proTypeQrcodeDao.countBySql(sql);
		 return count;
	}
	//获取登陆用户的分类信息
	public List<TbProTypeQrcode> getLoginProTypeQrcode(CompanyUser user) {
		
		if(user!=null){
			List<TbProTypeQrcode> list = this.proTypeQrcodeDao.getLoginProTypeQrcode(" and t1.ent_id = "+user.getComId());
			return list;
		}
		return null;
	}
	
	
	public String updateProTypeQrcode(TbProTypeQrcode proTypeQrcode){
		String msg = "种类设置失败!";
		if(proTypeQrcode!=null){
			int ptqId = proTypeQrcode.getPtqId();
			if(ptqId>0){
				TbProTypeQrcode proTypeQrcodeOld = proTypeQrcodeDao.get(TbProTypeQrcode.class, ptqId);
				
				proTypeQrcodeOld.setCertificate(proTypeQrcode.getCertificate());
				proTypeQrcodeOld.setQuantity(proTypeQrcode.getQuantity());
				proTypeQrcodeOld.setListed(proTypeQrcode.getListed());
				proTypeQrcodeOld.setSalearea(proTypeQrcode.getSalearea());
				proTypeQrcodeOld.setTypeImg(proTypeQrcode.getTypeImg());
				proTypeQrcodeOld.setProDesc(proTypeQrcode.getProDesc());
				proTypeQrcodeOld.setBrandName(proTypeQrcode.getBrandName());
				
				proTypeQrcodeDao.update(proTypeQrcodeOld);
				msg = "种类设置成功!";
			}
		}
		return msg;
	}
	
	
	private String createDimennoImg(HttpServletRequest request,String dimenno,String typeName,String companyName,String phone,String area){
		
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";				
		ResourceBundle bundle = ResourceBundle.getBundle("dimenno");				
		String url=bundle.getString("url");	
		//String checkUrl = bundle.getString("checkUrl");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =dimenno; // 二维码
		//String proname = typeName;
		//proname=proname==null?"":proname;
							
		String encoderContent =url+code;
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;
		
		/*String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+path+imgName2;
		
		String imgName3 = code+"_3.png";
		String imgPath3 = imgPath+path+imgName3;*/
		
        
        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";
        
		try {
			int width = 200;
			int height = width;
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);					
			/*MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);
			
			BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
			MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
										
		// x.w. 第二步：生成打印二维码 
		// 调用生成的图 类
		CreateImg cg = new CreateImg();
		String login_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"code_top.jpg";
		String codeImg = cg.CreateImg_record(login_img,typeName,companyName,dimenno,phone,area,imgPath1);
		
		return url+code;
	}
	
	/**
	 * new（2017-01-20） 根据主体配置生成二维码标签图
	 * @param request
	 * @param dimenno
	 * @param typeName
	 * @param companyName
	 * @param phone
	 * @param area
	 * @param tagStyle
	 * @return
	 */
	private String createDimennoImg2(HttpServletRequest request,String dimenno,String typeName,String companyName,String phone,String area,TagStyle tagStyle){
		
		String separator = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+separator+"nytsyFiles"+separator+"qrcode";
		String styleimgPath = DirectoryUtil.getProjectParentPath(request)+separator+"nytsyFiles"+separator+"entstyle";
		
		String codelogo = "";
		String toplogo = "";
		String mbDomain = "";//主体域名
		int tsType = 0;
		String tagSize = "";
		if(tagStyle!=null){
			codelogo = tagStyle.getCodelogo();
			toplogo = tagStyle.getToplogo();
			tsType = tagStyle.getTsType();
			mbDomain = tagStyle.getMbDomain()==null?"":"http://"+tagStyle.getMbDomain()+"/lvdunwang/trace.jsp?code=";//主体域名,如：http\://lvdun1.hontek.com.cn/lvdunwang/trace.jsp?code\=
			tagSize = tagStyle.getTagSize();
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle("dimenno");				
		String url=bundle.getString("url");		
		//String checkUrl = bundle.getString("checkUrl");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =dimenno; // 二维码							
		String encoderContent =url+code;
		if(!mbDomain.equals("")){//如果主体域名不为空，则使用主体域名
			encoderContent = mbDomain+code;
		}
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+separator+imgName1;
		
		String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+separator+imgName2;

        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+separator+"static"+separator+"image"+separator+"comm"+separator+"logo.png";
		if(!codelogo.equals("")){//如果二维码logo不为空，则用二维码logo
			logo_path = styleimgPath+separator+codelogo;
		}
		
		try {
			int width = 280;
			int height = width;
			BitMatrix matrix = MatrixToImageWriterEx.createQRCode(encoderContent, width, height);//生成二维码图片
			
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.WHITE, 4);
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath1, logo_path, logoConfig);	//在二维码图片中加入logo
			MatrixToImageWriterEx.writeToFile(matrix, "png", imgPath2, logo_path, logoConfig);
			/*
			BitMatrix matrix800 = MatrixToImageWriterEx.createQRCode(encoderContent, 800, 800);
			MatrixToImageWriterEx.writeToFile(matrix800, "png", imgPath3, logo_path, logoConfig);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
										
		// x.w. 第二步：生成打印二维码 
		// 调用生成的图 类
		CreateImg cg = new CreateImg();
		
		String def_top_img = "code_top.jpg";
		if("1".equals(tagSize)){
			def_top_img = "code_top_1.jpg";
		}
		
		String top_img = imgPath_top+separator+"static"+separator+"image"+separator+"comm"+separator+def_top_img;
		
		//如果主体二维码toplogo不为空，则用主体二维码toplogo
		if(!toplogo.equals("")){
			top_img = styleimgPath+separator+toplogo;
		}		
		if(tsType==2){//二维码在左边；
			if("1".equals(tagSize)){
				cg.CreateImg_dimeno_size1(top_img,typeName,companyName,dimenno,phone,area,imgPath1);
			}else{
				cg.CreateImg_dimeno(top_img,typeName,companyName,dimenno,phone,area,imgPath1);
			}
		}else if(tsType==3){//二维码在右边；
			if("1".equals(tagSize)){
				cg.CreateImg_record_size1(top_img,typeName,companyName,dimenno,phone,area,imgPath1);
			}else{
				cg.CreateImg_record(top_img,typeName,companyName,dimenno,phone,area,imgPath1);
			}
		}

		return url+code;
	}



	public String getLoginProType(int entId) {
		List<Combo> list= proTypeQrcodeDao.getLoginProTypeList(entId);
		return getJSON(list);
	}


	public List<TreeVo> getEntTypeTree(int entId) {
		List<TreeVo> treeList = proTypeQrcodeDao.getEntTypeTree(entId);
		return treeList;
	}

	public List<TbProTypeQrcode> findProTypeQrcodeListByEntId(int entId) {
		return proTypeQrcodeDao.find("from TbProTypeQrcode where entId = "+entId);
	}

	public String updateQrcodeState(int ptqId) {
		String msg = "设置成功";
		
		try {
			String hql = "update TbProTypeQrcode set submitTime='"+DateUtil.formatDateTime()+"' , proState = '1' where ptqId = " + ptqId;
			proTypeQrcodeDao.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "设置失败";
		}
		
		return msg;
	}


	/**
	 * 根据分类二维码查询
	 * @param dimenno
	 * @return
	 */
	public TbProTypeQrcode findProTypeQrcodeByDimenno(String dimenno) {
		List<TbProTypeQrcode> list =  proTypeQrcodeDao.findProTypeQrcodeList(" and t1.dimenno = '"+dimenno+"' ");
		if(!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
}
