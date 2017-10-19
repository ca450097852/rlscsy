package com.hontek.record.service.impl;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.CompanyDao;
import com.hontek.company.pojo.AuditRecord;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.dao.ElementDao;
import com.hontek.record.dao.ElementTempletDao;
import com.hontek.record.dao.ObjElementDao;
import com.hontek.record.dao.ProTypeBatchDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.dao.RecordDao;
import com.hontek.record.pojo.ProAndBatchRecord;
import com.hontek.record.pojo.TbElement;
import com.hontek.record.pojo.TbObjElement;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.record.service.inter.RecordServiceInter;
import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.pojo.TbProType;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;
import com.hontek.sys.service.inter.EnterpriseServiceInter;

/**
 * 档案service实现类
 * @author lzk
 *
 */
public class RecordServiceImpl extends BaseServiceImpl implements RecordServiceInter{
	private RecordDao recordDao;
	private ElementTempletDao elementTempletDao;
	private ElementDao elementDao;
	private ObjElementDao objElementDao;
	private ProTypeQrcodeDao proTypeQrcodeDao;
	private CompanyDao companyDao;
	private ProTypeDao proTypeDao;
	private ProTypeBatchDao proTypeBatchDao;
	

	public void setProTypeBatchDao(ProTypeBatchDao proTypeBatchDao) {
		this.proTypeBatchDao = proTypeBatchDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setObjElementDao(ObjElementDao objElementDao) {
		this.objElementDao = objElementDao;
	}

	public void setElementDao(ElementDao elementDao) {
		this.elementDao = elementDao;
	}

	public void setElementTempletDao(ElementTempletDao elementTempletDao) {
		this.elementTempletDao = elementTempletDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}
	
	public Pager<TbRecord> findRecordList(String entId, TbRecord record,
			int page, int rows, String order, String sort) {
		
		StringBuffer condition = new StringBuffer();
		if(entId!=null){
			condition.append(" and (objId="+entId+" and objTypeId in (1,2))");//查找企业档案
			condition.append(" or (objId in (select ptqId from TbProTypeQrcode where entId="+entId+" ) and objTypeId in (3,4))");
		}
		if(record!=null){
			if(record.getRecName()!=null){
				condition.append(" and recName like '%"+record.getRecName()+"%'");
			}
			if(entId==null&&record.getObjId()!=0){
				condition.append(" and objId = "+record.getObjId()+"");
			}
			if(entId==null&&record.getObjTypeId()!=0){
				condition.append(" and objTypeId = "+record.getObjTypeId()+"");
			}
		}
		
		condition.append(sortCondtion(sort, order));
		
		Pager<TbRecord> pager = recordDao.findRecordList(condition.toString(),page,rows);
		
		return pager;
	}
	
	/**
	 * 企业分类档案
	 */
	public Pager<TbRecord> findRecordTypeList(String entId, TbRecord record,
			int page, int rows, String order, String sort) {
		
		StringBuffer condition = new StringBuffer();
		if(record!=null){
			if(record.getRecName()!=null){
				condition.append(" and rec_name like '%"+record.getRecName()+"%'");
			}
			if(entId==null&&record.getObjId()!=0){
				condition.append(" and obj_id = "+record.getObjId()+"");
			}
			if(entId==null&&record.getObjTypeId()!=0){
				condition.append(" and obj_type_id = "+record.getObjTypeId()+"");
			}
		}
		
		condition.append(sortCondtion(sort, order));
		
		Pager<TbRecord> pager = recordDao.findRecordTypeList(condition.toString(),entId,page,rows);
		
		return pager;
	}
	
	
	
	/**
	 * 添加档案
	 * 
	 */
	public String addRecord(String recordType,String typeId, TsUser user, TbRecord record,HttpServletRequest request) {
		String msg = "添加成功";
		try {
			//获取要素
			List<TbElement> elementList = elementDao.findListByrecordType(recordType);
			//标识关系对象表中有没有数据
			boolean haveData = false;
			
			int objId = 0;//对象ID
			int objTypeId = 1;
			if("1".equals(recordType)||"2".equals(recordType)){//主体档案
				objId = user.getEntId();
//				objTypeId = 1;
				haveData = objElementDao.checkData(user.getEntId(),recordType);
			}else if("3".equals(recordType)||"4".equals(recordType)){//分类档案
//				objTypeId = 2;
				//分类档案先保存二维码信息
				if(typeId==null||"".equals(typeId)){
					return "参数错误";
				}
				
				TbProTypeQrcode ptq = proTypeQrcodeDao.get("from TbProTypeQrcode where typeId="+typeId+" and entId="+user.getEntId());
				
				haveData = ptq==null;
				//分类二维码信息不存在 
				if(ptq==null){
					ptq = new TbProTypeQrcode();
					ptq.setPtqId(proTypeQrcodeDao.getTableSequence("TB_PRO_TYPE_QRCODE"));
					ptq.setEntId(user.getEntId());
					ptq.setTypeId(Integer.parseInt(typeId));
					
					//产生二维码
					String dimenno = "";
					//TsEnterprise enterprise = enterpriseDao.getEnterPirseByEntId(user.getEntId());					
					//dimenno = enterprise.getEntCode();//entCode = 6位行政区域编码+6位（全省）顺序码
					//dimenno += String.valueOf(100+ptq.getTypeId()).substring(1);	//2位（企业）产品类型编码					
					//List<TbProTypeQrcode> list = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId="+user.getEntId()+" and typeId="+typeId+" order by ptqId desc");
					List<TbProTypeQrcode> list = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId="+user.getEntId()+" order by ptqId desc");
					String dimennoSeq = "01";
					if(list!=null&&!list.isEmpty()){
						String tmp = list.get(0).getDimenno();
						dimennoSeq = String.valueOf(100+(Integer.parseInt(tmp.substring(tmp.length()-2))+1)).substring(1);
						
					}
					dimenno += dimennoSeq;					
					//dimenno += "01";
													
					String url = bundle.getString("url")+dimenno;//createDimennoImg(request,dimenno,proType.getTypeName(),enterprise.getName(),enterprise.getTel(),areaName);
					
					ptq.setUrl(url);
					ptq.setDimenno(dimenno);
					ptq.setCodeImg(dimenno+".png");
					ptq.setCrrtime(DateUtil.formatDateTime());
					
					proTypeQrcodeDao.save(ptq);
					
					//重新产生企业二维码图片
					//enterprseService.getLoginCompanyDimenno(enterprise.getEntId(), request, true);
				}
				objId = ptq.getPtqId();
				
			}
			objTypeId = Integer.parseInt(recordType);
			//保存档案关系
			
			//判断档案关系表中是否有该类型的数据如果有的话不添加，没有添加
			
			if(haveData){
				for(TbElement element:elementList){
					TbObjElement objEl = new TbObjElement();
					objEl.setRelId(objElementDao.getTableSequence("TB_OBJ_ELEMENT"));
					objEl.setElementId(element.getElementId().intValue());
					objEl.setSeq(Integer.parseInt(element.getSeq()));
					objEl.setObjTypeId(objTypeId);
					objEl.setObjId(objId);
					objElementDao.save(objEl);
				}
			}
			record.setRecId(recordDao.getTableSequence("TB_RECORD"));
			record.setCrttime(DateUtil.formatDateTime());
			record.setCrtUser(user.getUserId());
			record.setObjId(objId);
			record.setObjTypeId(objTypeId);
			
			recordDao.save(record);//保存档案
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		
		return msg;
	}
	
	ResourceBundle bundle = ResourceBundle.getBundle("dimenno");		

	
	private String createDimennoImg(HttpServletRequest request,String dimenno,String typeName,String companyName,String phone,String area){
		
		String path = System.getProperty("file.separator") ;
		String imgPath = DirectoryUtil.getProjectParentPath(request)+path+"nytsyFiles"+path+"qrcode";	
		String url=bundle.getString("url");
		
		String checkUrl = bundle.getString("checkUrl");
		// 创建文件夹
		File file = new File(imgPath);
		if(!file.exists()){
			file.mkdirs();
		}
		// 产品信息
		String code =dimenno; // 二维码
		
		String codeMaker = companyName;
		codeMaker=codeMaker==null?"":codeMaker;			
		String long_codeMaker="";  // 用于保存截取后的剩余长度.
		if(codeMaker!=null&&codeMaker.length()>13){	
			long_codeMaker=codeMaker.substring(13);
			codeMaker=codeMaker.substring(0,13);					
		}
		
		String proname = typeName;
		proname=proname==null?"":proname;
							
		String encoderContent =url+code;
		
		// 生成二维码图片的名字： 二维码.png
		String imgName1 = code+".png";
		String imgPath1 = imgPath+path+imgName1;
		
		String imgName2 = code+"_2.png";
		String imgPath2 = imgPath+path+imgName2;
		
		String imgName3 = code+"_3.png";
		String imgPath3 = imgPath+path+imgName3;
		
        
        String imgPath_top = request.getRealPath("/");
		String logo_path = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"logo.png";
        
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
		String login_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"code_top.jpg";
		String codeImg = cg.CreateImg_record(login_img,typeName,companyName,dimenno,phone,area,imgPath1);
		
		return url+code;
	}

	public String updateRecord(TbRecord record) {
		String msg = "修改成功";
		try {
			if(record!=null&&record.getRecId()!=0){
				TbRecord oldRecord = recordDao.get(TbRecord.class, record.getRecId());
				oldRecord.setRecName(record.getRecName());
				oldRecord.setSeq(record.getSeq());
				oldRecord.setRecSts(record.getRecSts());
				recordDao.update(oldRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public List<TbElement> getElements(int recId) {
		if(recId!=0){
			TbRecord record = recordDao.get(TbRecord.class, recId);
			if(record!=null){
				List<TbElement> list = elementDao.findList(TbElement.class, " and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId="+record.getObjTypeId()+")");
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 根据产品分类二维码查询档案
	 */
	public List<TbRecord> findRecordListByPtqDimennno(String dimenno){
		List<TbRecord> list =null;
		try {
			if (dimenno != null && !"".equals(dimenno)) {
				list = this.recordDao.findRecordListByPtqDimennno(dimenno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String deleteRecord(String ids) {
		String msg = "删除档案成功";
		try {
			if(ids!=null){
				recordDao.deleteRecord(ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除档案失败";
		}
		return msg;
	}
	
	/**
	 * 获取企业要素权限信息
	 */
	public String getEntElements(String entId) {
		
		//获取企业要素
		List<TbElement> elList1 = elementDao.findList(TbElement.class, " and elementId in (select elementId from  TbObjElement where objId="+entId+" and objTypeId=1)");
		List<TbElement> elList2 = elementDao.findList(TbElement.class, " and elementId in (select elementId from  TbObjElement where objId="+entId+" and objTypeId=2)");
		
		//List<TbProType> typeList = proTypeDao.findList(TbProType.class, " and typeId in (select typeId from TbProTypeQrcode where entId="+entId+")"); 
		
		List<TbProType> typeList = proTypeDao.findQrcodeType(entId);
		
		List<TbProTypeQrcode> qrcodeList = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId = "+entId); 
		
		Map<Integer, List> typeMap = new HashMap<Integer, List>();
		for(TbProType proType:typeList){
			List<TbElement> tyeL = elementDao.findList(TbElement.class, " and elementId in (select elementId from  TbObjElement where objId="+proType.getEntId()+" and objTypeId="+(Integer.parseInt(proType.getTypeClass())+2)+")");
			typeMap.put(proType.getEntId(), tyeL);
		}
		
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("ent1", elList1);
		jsonMap.put("ent2", elList2);
		jsonMap.put("type", typeList);
		jsonMap.put("typeMap",typeMap);
		
		String msg = this.getObjectJSON(jsonMap);
		
		return msg;
	}

	/*public String getElementsByEntId(String entId) {
		List<TbRecord> recordList = recordDao.findList(TbRecord.class, " and objTypeId in (3,4) and objId in (select ptqId from TbProTypeQrcode where entId="+entId+")");
		if(recordList!=null&&!recordList.isEmpty()){
			//默认读取第一个
			TbRecord record = recordList.get(0);
			if(record!=null){
				List<TbElement> elList = elementDao.findList(TbElement.class, " and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId in (3,4) ) order by seq");
				Map jsonMap = new HashMap();
				jsonMap.put("recId", record.getRecId());
				jsonMap.put("elements", elList);
				String msg = this.getObjectJSON(jsonMap);
				return msg;
			}
			
		}
		return null;
	}*/
	
	public String getElementsByEntId(String entId, TsEnterprise enterprise) {
		StringBuffer condition = new StringBuffer();
		if(entId!=null){
			condition.append(" and (objId="+entId+" and objTypeId in (1))");//查找企业档案
			//condition.append(" or (objId in (select ptqId from TbProTypeQrcode where entId="+entId+" ) and objTypeId in (3,4))");
			
			List<TbRecord> recordEntList = recordDao.findList(TbRecord.class, condition.toString());
			if(recordEntList!=null&&!recordEntList.isEmpty()){
				for(TbRecord record:recordEntList){
					List<TbElement> els = elementDao.findList(TbElement.class, 
							" and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId="+record.getObjTypeId()+")");
					record.setElements(els);
				}
			}
			
			Pager<TbRecord> pager = recordDao.findRecordTypeList("",entId,1,10000);
			List<TbRecord> recordTypeList = pager.getData();
			if(recordTypeList!=null&&!recordTypeList.isEmpty()){
				for(TbRecord record:recordTypeList){
					List<TbElement> els = elementDao.findList(TbElement.class, 
							" and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId="+record.getObjTypeId()+")");
					record.setElements(els);
					List<TbProTypeBatch> batch = proTypeBatchDao.findProTypeBatchList(record.getObjId());
					record.setBatch(batch);
				}
			}
			
			Map<String, List> jsonMap = new HashMap<String, List>();
			jsonMap.put("ent",recordEntList);
			jsonMap.put("type", recordTypeList);
			
			String jsonMsg = this.getObjectJSON(jsonMap);
			return jsonMsg;
		}
		
		return null;
	}

	public String addRecordByTypeId(String ids, CompanyUser user, HttpServletRequest request) {
		String msg = "添加产品分类成功";
		System.out.println("ids=============="+ids);
		try {
//			List<TbProType> proTypeList = proTypeDao.findList(TbProType.class, " and typeId in ("+ids+")");
			
			if(ids!=null && !"".equals(ids)){
				String[] items = ids.split("@--@");
				for(String item:items){
					if(item!=null && !"".equals(item)){
						String[] idAndName = item.split("@-@");
						TbProType proType = proTypeDao.getProType(" and typeId="+idAndName[0]);
						if(proType!=null){
							//proType.setTypeName(idAndName[1]);
							
							//创建分类二维码信息
							TbProTypeQrcode ptq = new TbProTypeQrcode();
							ptq.setPtqId(proTypeQrcodeDao.getTableSequence("tb_pro_type_qrcode".toUpperCase()));
							ptq.setEntId(user.getComId());
							ptq.setTypeId(proType.getTypeId());
							
							//产生二维码
							//本规范主要规定蔬菜的批次码，由流通节点主体码+流水号组成，共16位数字。
							//其中，流通节点主体码是指为某批蔬菜首次建立电子台账的流通节点的主体码；
							//流水号是指建立电子台帐的顺序号，为7位数字。
							String dimenno = companyDao.getNodeCode(user.getComId());						
							
							List<TbProTypeQrcode> list = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId="+user.getComId()+" order by ptqId desc");
							String dimennoSeq = "0000001";
							if(list!=null&&!list.isEmpty()){
								String tmp = list.get(0).getDimenno();							
								int temp2 = Integer.valueOf(tmp.substring(9))+1;																
								dimennoSeq = getPrefixNum(7, String.valueOf(temp2));							
								System.out.println("dimennoSeq=========="+dimennoSeq);							
							}
							dimenno += dimennoSeq;							
							//dimenno += "01";							
							/*
							//获取区信息
							TsEnterprise areaPo2 = enterpriseDao.getEnterPirseByEntId(enterprise.getAreaId());
							//获取市信息
							TsEnterprise areaPo1 = enterpriseDao.getEnterPirseByEntId(areaPo2.getParentId());
							String areaName = ((areaPo1==null)?"":areaPo1.getName())+((areaPo2==null)?"":areaPo2.getName());						
							String url = createDimennoImg(request,dimenno,proType.getTypeName(),enterprise.getName(),enterprise.getTel(),areaName);						
							ptq.setUrl(url);
							*/
							ptq.setDimenno(dimenno);
							ptq.setCodeImg(dimenno+".png");
							ptq.setCrrtime(DateUtil.formatDateTime());
							ptq.setProName(idAndName[1]);
							ptq.setIsbatch(1);
							
							proTypeQrcodeDao.save(ptq);
							
							
							//暂时去掉生成生产档案
							/*String typeClass = proType.getTypeClass()==null?"1": proType.getTypeClass();
							if("1".equals(typeClass)||"2".equals(typeClass)){//种植，养植类
								typeClass = String.valueOf(Integer.parseInt(proType.getTypeClass())+2);
							}else if("3".equals(typeClass)){//加工类
								typeClass = "2";
							}
							
							int objTypeId = Integer.parseInt(typeClass);
							
							//创建档案信息
							TbRecord record = new TbRecord();
							record.setRecName(idAndName[2]+"生产档案");
							record.setCrttime(DateUtil.formatDateTime());
							record.setSeq(5);
							record.setRecSts("1");
							record.setRecId(recordDao.getTableSequence("tb_record".toUpperCase()));
							record.setObjId(ptq.getPtqId());
							record.setObjTypeId(objTypeId);
							
							recordDao.save(record);
							
							//对象要素关系 
							List<TbElement> elementList = elementDao.findListByrecordType(typeClass);
							
							for(TbElement element:elementList){
								TbObjElement objEl = new TbObjElement();
								objEl.setRelId(objElementDao.getTableSequence("TB_OBJ_ELEMENT"));
								objEl.setElementId(element.getElementId().intValue());
								objEl.setSeq(Integer.parseInt(element.getSeq()));
								objEl.setObjTypeId(objTypeId);
								objEl.setObjId(ptq.getPtqId());
								objElementDao.save(objEl);
							}*/
							
						}
					}
				}
				
			}
			
			/*if(proTypeList.size()!=0){
				//重新产生企业二维码图片
				enterprseService.getLoginCompanyDimenno(user.getEntId(), request, true);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加产品分类失败";
		}
		return msg;
	}
	
	public String addOrDelRecord(String ids, String dels, CompanyUser user,HttpServletRequest request) {
		String msg = "设置产品分类成功";
		try {
			if(dels!=null&&!"".equals(dels)){
				//删除档案要素关系表
				//proTypeQrcodeDao.executeHql("delete from TbObjElement where objId in (select ptqId from TbProTypeQrcode where typeId in ("+dels+") and entId="+user.getEntId()+") and objTypeId in (3,4)");
				proTypeQrcodeDao.executeHql("delete from TbObjElement where objId in ("+dels+") and objTypeId in (3,4)");
				//删除相关档案
				//proTypeQrcodeDao.executeHql("delete from TbRecord where objId in (select ptqId from TbProTypeQrcode where typeId in ("+dels+") and entId="+user.getEntId()+") and objTypeId in (3,4)");
				proTypeQrcodeDao.executeHql("delete from TbRecord where objId in ("+dels+") and objTypeId in (3,4)");
				//删除分类二维码信息
				//proTypeQrcodeDao.executeHql("delete from TbProTypeQrcode where typeId in ("+dels+") and entId="+user.getEntId());
				proTypeQrcodeDao.executeHql("delete from TbProTypeQrcode where ptqId in ("+dels+")");
			}
			//添加产品分类
			if(ids!=null&&!"".equals(ids)){
				addRecordByTypeId(ids,user,request);
			}
			//enterprseService.getLoginCompanyDimenno(user.getEntId(), request, true);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "设置产品分类失败";
		}
		return msg;
	}
	
	/**
	 * 保存产地信息，同时生成分类二维码图片和更新主体二维码图片
	 */
	public String saveChandi(TbProTypeQrcode proTypeQrcode, Company company,HttpServletRequest request) {
		String msg = "保存产地信息成功";
		try {		
			TbProTypeQrcode proTypeQrcodeNew = proTypeQrcodeDao.get(TbProTypeQrcode.class, proTypeQrcode.getPtqId());
			proTypeQrcodeNew.setChandi(proTypeQrcode.getChandi());
			TbProType proType = proTypeDao.get(TbProType.class,proTypeQrcodeNew.getTypeId());
			//生成二维码图片
			//产生二维码
			String url = createDimennoImg(request,proTypeQrcodeNew.getDimenno(),proType.getTypeName(),company.getName(),company.getPhone(),proTypeQrcode.getChandi());
			proTypeQrcodeNew.setUrl(url);
			//保存产地信息
			proTypeQrcodeDao.save(proTypeQrcodeNew);
			//更新企业二维码
			//enterprseService.getLoginCompanyDimenno(user.getEntId(), request, true);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "保存产地信息失败";
		}
		return msg;
	}
	
	
	
	

	/**
	 * web,溯源查询
	 */
	public String getRecordByEntId(String entId) {
		StringBuffer condition = new StringBuffer();
		if(entId!=null){
			condition.append(" and (objId="+entId+" and objTypeId in (1,2))");//查找企业档案
			
			List<TbRecord> recordEntList = recordDao.findList(TbRecord.class, condition.toString());
			if(recordEntList!=null&&!recordEntList.isEmpty()){
				for(TbRecord record:recordEntList){
					List<TbElement> els = elementDao.findList(TbElement.class, " and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId="+record.getObjTypeId()+")");
					record.setElements(els);
				}
			}
			
			Pager<TbRecord> pager = recordDao.findRecordTypeList("",entId,1,10000);
			List<TbRecord> recordTypeList = pager.getData();
			if(recordTypeList!=null&&!recordTypeList.isEmpty()){
				for(TbRecord record:recordTypeList){
					List<TbElement> els = elementDao.findList(TbElement.class, " and elementId in (select elementId from TbObjElement where objId="+record.getObjId()+" and objTypeId="+record.getObjTypeId()+")");
					record.setElements(els);
					List<TbProTypeBatch> batch = proTypeBatchDao.findProTypeBatchList(record.getObjId());
					for(TbProTypeBatch ptb :batch){
						ptb.setRecId(getPTBRecId(ptb.getPtbId()+""));
					}
					record.setBatch(batch);
				}
			}
			
			Map<String, List> jsonMap = new HashMap<String, List>();
			jsonMap.put("ent",recordEntList);
			jsonMap.put("type", recordTypeList);
			
			String jsonMsg = this.getObjectJSON(jsonMap);
			return jsonMsg;
		}
		
		return null;
	}
	
	public int getPTBRecId(String pjbId){
		int recid = 0;
		Object obj = recordDao.uniqueResult("select rec_ID from tb_record where obj_ID="+pjbId+" and obj_type_id = 5");
		if(obj!=null){
			recid = (Integer)obj;
		}
		return recid;
	}

	public Pager<TbRecord> getAuditRecord(int entId,int page, int rows) {
		return recordDao.getAuditRecord(entId,page, rows);
	}

	public String updateAuditState(String typeId, int entId, TbRecord record) {
		String msg = "审核成功";
		try {
			String state = "3";
			if("1".equals(typeId)){
				state = "2";
			}
			if(record.getObjTypeId() == 5){//批次
				String hql = "update TbProTypeBatch set auditTime='"+DateUtil.formatDateTime()+"' , batchState = '"+state+"',auditEntId="+entId+" where ptbId = " + record.getObjId();
				proTypeBatchDao.executeHql(hql);
			}else{//非批次
				String hql = "update TbProTypeQrcode set auditTime='"+DateUtil.formatDateTime()+"' , proState = '"+state+"',auditEntId="+entId+" where ptqId = " + record.getObjId();
				proTypeQrcodeDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "审核失败";
		}
		return msg;
	}

	public List<ProAndBatchRecord> getLoginAuditRecord(String entId) {
		return recordDao.getLoginAuditRecord(entId);
	}
	
}
