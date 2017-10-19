package com.hontek.synchronous.service.impl;

import java.awt.Color;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.element.dao.EartagDao;
import com.hontek.element.pojo.TbEartag;
import com.hontek.record.dao.ObjElementDao;
import com.hontek.record.dao.ProTypeAreaDao;
import com.hontek.record.dao.ProTypeQrcodeDao;
import com.hontek.record.dao.RecordDao;
import com.hontek.record.pojo.TbObjElement;
import com.hontek.record.pojo.TbProTypeArea;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.pojo.TbRecord;
import com.hontek.review.dao.ProBatchDao;
import com.hontek.review.dao.ProductDao;
import com.hontek.review.pojo.TbProBatch;
import com.hontek.review.pojo.TbProduct;
import com.hontek.synchronous.action.OnlyManager;
import com.hontek.synchronous.dao.DataInfoDao;
import com.hontek.synchronous.pojo.DataInfo;
import com.hontek.synchronous.service.inter.DataInfoServiceInter;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TsEnterprise;

public class DataInfoServiceImpl extends BaseServiceImpl implements DataInfoServiceInter {

	private DataInfoDao dataInfoDao;
	private EnterpriseDao enterpriseDao;
	private RecordDao recordDao;
	private ObjElementDao objElementDao;
	private EartagDao eartagDao;
	private ProTypeQrcodeDao proTypeQrcodeDao;
	
	private ProTypeAreaDao proTypeAreaDao;
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("dimenno");
	
	public void setDataInfoDao(DataInfoDao dataInfoDao) {
		this.dataInfoDao = dataInfoDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	public void setObjElementDao(ObjElementDao objElementDao) {
		this.objElementDao = objElementDao;
	}

	public void setEartagDao(EartagDao eartagDao) {
		this.eartagDao = eartagDao;
	}

	public void setProTypeQrcodeDao(ProTypeQrcodeDao proTypeQrcodeDao) {
		this.proTypeQrcodeDao = proTypeQrcodeDao;
	}

	public void setProTypeAreaDao(ProTypeAreaDao proTypeAreaDao) {
		this.proTypeAreaDao = proTypeAreaDao;
	}



	private Map<String, Integer> entCodeSeq = new HashMap<String, Integer>();	
	
	Map<String, Integer> map = new HashMap<String, Integer>();

	public void setMap(){
		map.put("韶关市", 27240);
		map.put("深圳市", 27241);
		map.put("珠海市", 27242);
		map.put("湛江市", 27243);
		map.put("茂名市", 27244);
		map.put("清远市", 27248);
		map.put("揭阳市", 27249);
		map.put("云浮市", 27250);
		map.put("广州市", 413);
		map.put("惠州市", 414);
		map.put("东莞市", 415);
		map.put("江门市", 441);
		map.put("梅州市", 789);
		map.put("汕尾市", 27246);
		map.put("河源市", 27247);
		map.put("汕头市", 27251);
		map.put("阳江市", 27252);
		map.put("中山市", 27253);
		map.put("潮州市", 27254);
		map.put("佛山市", 27255);
		map.put("肇庆市", 27245);
	}


	public List<DataInfo> addList() {
				
		String yyyyMMdd = DateUtil.formatDate();
		//String yyyyMMdd = "2015-08-31 2";
		List<DataInfo> list = dataInfoDao.findDataInfo12(yyyyMMdd);
		 
		Map<String, TsEnterprise> areaMaps = enterpriseDao.getAreaEnterpriseList();
		if(map.isEmpty()){
			setMap();
		}
		 
		TsEnterprise enterprise = null;
		int size = list.size();
		int count =0;
		
		 for (DataInfo dataInfo : list) {
			 
			System.out.println(yyyyMMdd+"-企业数量："+size+",正在处理第："+(++count));
			String addr = dataInfo.getFromAddress()==null?"":dataInfo.getFromAddress();			
			String fromProvince = dataInfo.getFromProvince();
			
			String city = dataInfo.getFromCity();		
			String ownerName = dataInfo.getOwnerName()==null?"":dataInfo.getOwnerName();
			String sysTime = dataInfo.getSystemUserTime();
			ownerName = ownerName.replaceAll(" ", "");
			addr = addr.replaceAll(" ", "");
			String entName = "";
			if(!"".equals(ownerName)&&ownerName.length()>=4){			
				entName = ownerName;
			}else{
				entName = "个体户-"+ownerName;
			}			
			int entId = 0;			
			String entCode = "";
			Object [] ent = enterpriseDao.getEnterpriseByCondi(entName, fromProvince);
			if(ent!=null){
				entId = Integer.valueOf(ent[0].toString());
				entCode = ent[1].toString();
			}else{				
				enterprise = new TsEnterprise();
				enterprise.setFlag("3");
				int parentId = 10;
				if(map.get(city)!=null){
					parentId = map.get(city);
				}
				
				enterprise.setParentId(parentId);
				enterprise.setEntType(2);
				enterprise.setIsReported("1");
				enterprise.setCompanyRsts("4");
				enterprise.setSysCode("A002001");
				enterprise.setRegAddr(addr);
				enterprise.setManageAddr(addr);
				enterprise.setCrtDt(sysTime);
				TsEnterprise areaEnterprise = areaMaps.get(city);
				if(areaEnterprise!=null){
					enterprise.setAreaId(areaEnterprise.getAreaId());
					String areaCode = areaEnterprise.getEntCode();
										
					Integer s = entCodeSeq.get(areaCode);					
					
					int seq =0;
					if(s!=null){
						seq =s+1;
					}else{
						// seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");								 
							seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
					}
					entCodeSeq.put(areaCode, seq);										
					entCode = areaCode+ String.valueOf(1000000+seq).substring(1);
					enterprise.setEntCode(entCode);					
					enterprise.setName(entName);
				}							
				//System.out.println(enterprise.toString());
				entId = enterpriseDao.getTableSequence("TS_ENTERPRISE");			
				enterprise.setEntId(entId);
				enterpriseDao.addEnterprise(enterprise);
			}
						 
			String proName = dataInfo.getAnimalTypeName();
			String earMark = dataInfo.getEarMark()==null?"":dataInfo.getEarMark();
			TbProTypeQrcode proTypeQrcode = null;
			int proId = 0;
			//没有该产品			
			Object ids[] = proTypeQrcodeDao.findProTypeCode(proName, entId);
			int recId = 0;
			//int id = productDao.findPro(proName, entId);
			if(ids!=null){
				proId = Integer.valueOf(String.valueOf(ids[0]));
				recId = Integer.valueOf(String.valueOf(ids[1]));
				
			}else{

				proTypeQrcode = new TbProTypeQrcode();					
				proTypeQrcode.setEntId(entId);				
				
				if(proName.contains("猪")){
					proTypeQrcode.setTypeId(52);
				}else if(proName.contains("牛")){
					proTypeQrcode.setTypeId(53);
				}else if(proName.contains("羊")){
					proTypeQrcode.setTypeId(54);
				}else if(proName.contains("鸡")){
					proTypeQrcode.setTypeId(56);
				}else if(proName.contains("鸭")){
					proTypeQrcode.setTypeId(57);
				}else if(proName.contains("鹅")){
					proTypeQrcode.setTypeId(58);
				}else{
					proTypeQrcode.setTypeId(51);
				}					
				//暂时将二维码与产品编码设置为一致
				
				//产生二维码
				String dimenno = entCode;

				List<TbProTypeQrcode> list2 = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId="+entId+" order by ptqId desc");
				String dimennoSeq = "01";
				if(list2!=null&&!list2.isEmpty()){
					String tmp = list2.get(0).getDimenno();
					dimennoSeq = String.valueOf(100+(Integer.parseInt(tmp.substring(tmp.length()-2))+1)).substring(1);
					
				}
				dimenno += dimennoSeq;					
				dimenno += "01";
				
				proTypeQrcode.setUrl("http://sy.hontek.com.cn/ncpsy/trace.jsp?code="+dimenno);
				proTypeQrcode.setListed("全年");
				proTypeQrcode.setSalearea("内销");
				proTypeQrcode.setCrrtime(sysTime);
				proTypeQrcode.setDimenno(dimenno);							
				proId = proTypeQrcodeDao.getTableSequence("tb_pro_type_qrcode");
				proTypeQrcode.setPtqId(proId);
				//创建企业分类
				proTypeQrcodeDao.save(proTypeQrcode);			
				
				TbProTypeArea proTypeArea = new TbProTypeArea();
				
				proTypeArea.setPtaId(proTypeAreaDao.getTableSequence("tb_pro_type_area"));
				proTypeArea.setChandi(fromProvince);
				proTypeArea.setPtqId(proId);
				
				proTypeAreaDao.save(proTypeArea);
				
				//创建企业分类档案
				TbRecord record = new TbRecord();
				
				recId = recordDao.getTableSequence("TB_RECORD");
				
				record.setRecId(recId);
				record.setCrttime(sysTime);
				record.setObjId(proId);
				record.setObjTypeId(4);
				record.setRecName(proName+"档案");
				
				recordDao.save(record);//保存档案
				
				
				TbObjElement objEl = new TbObjElement();
				objEl.setRelId(objElementDao.getTableSequence("TB_OBJ_ELEMENT"));
				objEl.setElementId(3);
				objEl.setSeq(5);
				objEl.setObjTypeId(4);
				objEl.setObjId(proId);
				objElementDao.save(objEl);
				
			}						
			
			earMark = replaceString(earMark);
						
			//添加批次
			if(earMark!=null&&!"".equals(earMark)){
				String [] earMarks = earMark.split(",");
				
				if(earMarks!=null&&earMarks.length>0){
					int length = earMarks.length;
					String firstEarMark = earMarks[0];
					int firstLength = firstEarMark.length();//1440881
					
					if(firstLength==7&&length>2){
						if(earMarks[1].length()==8){
							firstEarMark = firstEarMark+earMarks[1];
						}
					}					
					for (int i = 0; i < length; i++) {
						String earMarkStr  = earMarks[i];
						if(i==0){
							earMarkStr = firstEarMark;
						}																		
						if(earMarkStr.length()==3&&firstLength==15){
							earMarkStr = firstEarMark.substring(0, 12)+earMarkStr;
						}
						if(earMarkStr.length()==4&&firstLength==15){
							earMarkStr = firstEarMark.substring(0, 11)+earMarkStr;
						}
						if(earMarkStr!=null&&earMarkStr.length()==15){
							
							TbEartag eartag = new TbEartag();
														
							eartag.setEarId(eartagDao.getTableSequence("tb_eartag".toUpperCase()));
							eartag.setEarNo(earMarkStr);
							eartag.setRecId(recId);
							
							eartagDao.save(eartag);
						}
					}
				}					
			}								
		}
		System.out.println("ok.");		 
		 return list;
	}
	
	
	
	
	public String replaceString(String earMark){
		if(earMark.contains("-")){
			if(earMark.indexOf("-")<10){
				earMark = earMark.replaceFirst("-", "");
			}
			earMark = earMark.replaceAll("-", ",");
		}
		if(earMark.contains("——")){
			if(earMark.indexOf("——")<10){
				earMark = earMark.replaceFirst("——", "");
			}
			earMark = earMark.replaceAll("——", ",");
		}
		if(earMark.contains("，")){
			earMark = earMark.replaceAll(",", "");
		}
		if(earMark.contains("/")){
			earMark = earMark.replaceAll("/", "");
		}
		if(earMark.contains(";")){
			earMark = earMark.replaceAll(";", "");
		}
		if(earMark.contains(".")){
			earMark = earMark.replaceAll(".", ",");
		}
		if(earMark.contains("、")){
			earMark = earMark.replaceAll("、", ",");
		}
		if(earMark.contains("   ")){
			earMark = earMark.replaceAll("   ", ",");
		}
		if(earMark.contains("  ")){
			earMark = earMark.replaceAll("  ", ",");
		}
		if(earMark.contains(" ")){
			earMark = earMark.replaceAll(" ", ",");
		}
		return earMark;
	}

	public List<DataInfo> addList2(String type) {
				
		//String yyyyMMdd = DateUtil.formatDate();
		String yyyyMMdd = type;
		List<DataInfo> list = dataInfoDao.findDataInfo12(yyyyMMdd);
		 
		Map<String, TsEnterprise> areaMaps = enterpriseDao.getAreaEnterpriseList();
		if(map.isEmpty()){
			setMap();
		}
		 
		TsEnterprise enterprise = null;
		int size = list.size();
		int count =0;
		
		 for (DataInfo dataInfo : list) {
			 
			System.out.println(yyyyMMdd+"-企业数量："+size+",正在处理第："+(++count));
			String addr = dataInfo.getFromAddress()==null?"":dataInfo.getFromAddress();			
			String fromProvince = dataInfo.getFromProvince();
			
			String city = dataInfo.getFromCity();		
			String ownerName = dataInfo.getOwnerName()==null?"":dataInfo.getOwnerName();
			String sysTime = dataInfo.getSystemUserTime();
			ownerName = ownerName.replaceAll(" ", "");
			addr = addr.replaceAll(" ", "");
			String entName = "";
			if(!"".equals(ownerName)&&ownerName.length()>=4){			
				entName = ownerName;
			}else{
				entName = "个体户-"+ownerName;
			}			
			int entId = 0;			
			String entCode = "";
			Object [] ent = enterpriseDao.getEnterpriseByCondi(entName, fromProvince);
			if(ent!=null){
				entId = Integer.valueOf(ent[0].toString());
				entCode = ent[1].toString();
			}else{				
				enterprise = new TsEnterprise();
				enterprise.setFlag("3");
				int parentId = 10;
				if(map.get(city)!=null){
					parentId = map.get(city);
				}
				
				enterprise.setParentId(parentId);
				enterprise.setEntType(2);
				enterprise.setIsReported("1");
				enterprise.setCompanyRsts("4");
				enterprise.setSysCode("A002001");
				enterprise.setRegAddr(addr);
				enterprise.setManageAddr(addr);
				enterprise.setCrtDt(sysTime);
				TsEnterprise areaEnterprise = areaMaps.get(city);
				if(areaEnterprise!=null){
					enterprise.setAreaId(areaEnterprise.getAreaId());
					String areaCode = areaEnterprise.getEntCode();
										
					Integer s = entCodeSeq.get(areaCode);					
					
					int seq =0;
					if(s!=null){
						seq =s+1;
					}else{
						// seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");								 
							seq = enterpriseDao.getTableSequence("company_code");//全省顺序码
					}
					entCodeSeq.put(areaCode, seq);										
					entCode = areaCode+ String.valueOf(1000000+seq).substring(1);
					enterprise.setEntCode(entCode);					
					enterprise.setName(entName);
				}							
				//System.out.println(enterprise.toString());
				entId = enterpriseDao.getTableSequence("TS_ENTERPRISE");			
				enterprise.setEntId(entId);
				enterpriseDao.addEnterprise(enterprise);
			}
						 
			String proName = dataInfo.getAnimalTypeName();
			String earMark = dataInfo.getEarMark()==null?"":dataInfo.getEarMark();
			TbProTypeQrcode proTypeQrcode = null;
			int proId = 0;
			//没有该产品			
			Object ids[] = proTypeQrcodeDao.findProTypeCode(proName, entId);
			int recId = 0;
			//int id = productDao.findPro(proName, entId);
			if(ids!=null){
				proId = Integer.valueOf(String.valueOf(ids[0]));
				recId = Integer.valueOf(String.valueOf(ids[1]));
				
			}else{

				proTypeQrcode = new TbProTypeQrcode();					
				proTypeQrcode.setEntId(entId);				
				
				if(proName.contains("猪")){
					proTypeQrcode.setTypeId(52);
				}else if(proName.contains("牛")){
					proTypeQrcode.setTypeId(53);
				}else if(proName.contains("羊")){
					proTypeQrcode.setTypeId(54);
				}else if(proName.contains("鸡")){
					proTypeQrcode.setTypeId(56);
				}else if(proName.contains("鸭")){
					proTypeQrcode.setTypeId(57);
				}else if(proName.contains("鹅")){
					proTypeQrcode.setTypeId(58);
				}else{
					proTypeQrcode.setTypeId(51);
				}					
				//暂时将二维码与产品编码设置为一致
				
				//产生二维码
				String dimenno = entCode;

				List<TbProTypeQrcode> list2 = proTypeQrcodeDao.findList(TbProTypeQrcode.class, " and entId="+entId+" order by ptqId desc");
				String dimennoSeq = "01";
				if(list2!=null&&!list2.isEmpty()){
					String tmp = list2.get(0).getDimenno();
					dimennoSeq = String.valueOf(100+(Integer.parseInt(tmp.substring(tmp.length()-2))+1)).substring(1);
					
				}
				dimenno += dimennoSeq;					
				dimenno += "01";
				
				proTypeQrcode.setListed("全年");
				proTypeQrcode.setSalearea("内销");
				proTypeQrcode.setCrrtime(sysTime);
				proTypeQrcode.setDimenno(dimenno);							
				proId = proTypeQrcodeDao.getTableSequence("tb_pro_type_qrcode");
				proTypeQrcode.setPtqId(proId);
				//创建企业分类
				proTypeQrcodeDao.save(proTypeQrcode);			
				
				TbProTypeArea proTypeArea = new TbProTypeArea();
				
				proTypeArea.setPtaId(proTypeAreaDao.getTableSequence("tb_pro_type_area"));
				proTypeArea.setChandi(fromProvince);
				proTypeArea.setPtqId(proId);
				
				proTypeAreaDao.save(proTypeArea);
				
				//创建企业分类档案
				TbRecord record = new TbRecord();
				
				recId = recordDao.getTableSequence("TB_RECORD");
				
				record.setRecId(recId);
				record.setCrttime(sysTime);
				record.setObjId(proId);
				record.setObjTypeId(4);
				record.setRecName(proName+"档案");
				
				recordDao.save(record);//保存档案
				
				
				TbObjElement objEl = new TbObjElement();
				objEl.setRelId(objElementDao.getTableSequence("TB_OBJ_ELEMENT"));
				objEl.setElementId(3);
				objEl.setSeq(5);
				objEl.setObjTypeId(4);
				objEl.setObjId(proId);
				objElementDao.save(objEl);
				
			}						
			
			earMark = replaceString(earMark);
						
			//添加批次
			if(earMark!=null&&!"".equals(earMark)){
				String [] earMarks = earMark.split(",");
				
				if(earMarks!=null&&earMarks.length>0){
					int length = earMarks.length;
					String firstEarMark = earMarks[0];
					int firstLength = firstEarMark.length();//1440881
					
					if(firstLength==7&&length>2){
						if(earMarks[1].length()==8){
							firstEarMark = firstEarMark+earMarks[1];
						}
					}					
					for (int i = 0; i < length; i++) {
						String earMarkStr  = earMarks[i];
						if(i==0){
							earMarkStr = firstEarMark;
						}																		
						if(earMarkStr.length()==3&&firstLength==15){
							earMarkStr = firstEarMark.substring(0, 12)+earMarkStr;
						}
						if(earMarkStr.length()==4&&firstLength==15){
							earMarkStr = firstEarMark.substring(0, 11)+earMarkStr;
						}
						if(earMarkStr!=null&&earMarkStr.length()==15){
							
							TbEartag eartag = new TbEartag();
														
							eartag.setEarId(eartagDao.getTableSequence("tb_eartag".toUpperCase()));
							eartag.setEarNo(earMarkStr);
							eartag.setRecId(recId);
							
							eartagDao.save(eartag);
						}
					}
				}					
			}								
		}
		System.out.println("ok.");		 
		 return list;
	}
}
