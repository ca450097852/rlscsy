package com.hontek.webservice.service.impl;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import net.sf.json.JSONArray;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;

import com.google.zxing.common.BitMatrix;
import com.hontek.comm.qrcode.MatrixToImageWriterEx;
import com.hontek.comm.qrcode.MatrixToLogoImageConfig;
import com.hontek.comm.util.CreateImg;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.dao.ProductionAppendixDao;
import com.hontek.company.dao.ProductionDao;
import com.hontek.company.dao.ZizhiAppendixDao;
import com.hontek.company.dao.ZizhiDao;
import com.hontek.company.pojo.AuditCtrl;
import com.hontek.company.pojo.ProductionAppendix;
import com.hontek.company.pojo.ZizhiAppendix;
import com.hontek.company.service.inter.AuditCtrlServiceInter;
import com.hontek.synchronous.action.OnlyManager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.dao.InterAccountDao;
import com.hontek.sys.dao.UserDao;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;
import com.hontek.webservice.pojo.Appendix;
import com.hontek.webservice.pojo.Company;
import com.hontek.webservice.pojo.Production;
import com.hontek.webservice.pojo.ResultClass;
import com.hontek.webservice.pojo.Zizhi;
import com.hontek.webservice.service.inter.CompanyInter;

/**
 * 企业同步接口
 * @author lzk
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public class CompanyImpl_bak /*implements CompanyInter*/{
	
	private Logger logger = Logger.getLogger(CompanyImpl_bak.class);
	
	private InterAccountDao interAccountDao;
	private EnterpriseDao enterpriseDao;
	private UserDao userDao;
	private ProductionDao productionDao;
	private ProductionAppendixDao productionAppendixDao;
	private ZizhiDao zizhiDao;
	private AuditCtrlServiceInter auditCtrlServiceInter;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAuditCtrlServiceInter(AuditCtrlServiceInter auditCtrlServiceInter) {
		this.auditCtrlServiceInter = auditCtrlServiceInter;
	}

	public void setZizhiDao(ZizhiDao zizhiDao) {
		this.zizhiDao = zizhiDao;
	}

	public void setProductionDao(ProductionDao productionDao) {
		this.productionDao = productionDao;
	}

	private ZizhiAppendixDao zizhiAppendixDao;

	public void setInterAccountDao(InterAccountDao interAccountDao) {
		this.interAccountDao = interAccountDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}
	
	public void setProductionAppendixDao(ProductionAppendixDao productionAppendixDao) {
		this.productionAppendixDao = productionAppendixDao;
	}

	public void setZizhiAppendixDao(ZizhiAppendixDao zizhiAppendixDao) {
		this.zizhiAppendixDao = zizhiAppendixDao;
	}


	@Resource
	private WebServiceContext context;
	
	
	
	private HttpServletRequest getRequest(){
		MessageContext ctx = context.getMessageContext();
		
		HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
		
		return request;
	}

	/**
	 * 获取使用口令
	 */
	public ResultClass getToken(@WebParam(name="account")String account, @WebParam(name="password")String password) {
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		try {
			String condition = " and account='"+account+"' and pass='"+password+"'";
			
			TbInterAccount interAccount = interAccountDao.getInterAccount(condition);
			
			if(interAccount!=null){
				String token = UUID.randomUUID().toString().replace("-", "");
				OnlyManager onlyManager = OnlyManager.getInstance();
				interAccount.setTime(System.currentTimeMillis());
				onlyManager.putAccount(token, interAccount);
				resultClass.setInfo(token);
				resultClass.setSuccess(true);
			}else{
				resultClass.setErrorInfo("帐号或密码出错");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultClass.setErrorInfo("系统繁忙，请稍后重试");
		}
		
		return resultClass;
	}
	/**
	 * 
	 */
	public ResultClass addcompanyInfo(@WebParam(name="token")String token, @WebParam(name="comList")List<Company> comList) {
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		
		resultClass.setSuccess(false);
		
		
		try {
			if(interaccount!=null){
				//更新心跳
				onlyManager.active(interaccount);
				
				List<TsEnterprise> entList = new ArrayList<TsEnterprise>();
				
				TsEnterprise ent = null;
				for(Company com:comList){
					ent = new TsEnterprise();
//					ent.setTmpId(com.getComId());
					ent.setAreaId(interaccount.getAreaId());
					ent.setParentId(interaccount.getEntId());
					ent.setName(com.getCompanyName());
					ent.setSimpleName(com.getSimpleName());
					ent.setIntro(com.getIntro());
					ent.setTel(com.getTel());
					ent.setOrgCode(com.getOrgCode());
					ent.setLegalPerson(com.getLegalPerson());
					ent.setRegAddr(com.getRegAddr());
					ent.setManageAddr(com.getManagerAddr());
					ent.setPostCode(com.getPostCode());
					ent.setEmail(com.getEmail());
					ent.setDomName(com.getDomName());
					ent.setCrtUserId(interaccount.getUserId());
//					ent.setCrtDt(com.getCrtDt());
					ent.setFlag("3");//设置为企业
					ent.setSts("0");
					ent.setIsReported("1");//设置为上报企业
					ent.setEntType(2);//默认设置为私企
					ent.setCrtDt(DateUtil.formatDateTime());
					ent.setMobile(com.getMobile());
					ent.setSysCode(interaccount.getSysCode());
					
					ent.setPurl(com.getPurl());
					ent.setMurl(com.getMurl());
					
					if(com.getLogoUrl()!=null){
						String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
						path = path.substring(0,path.lastIndexOf("webapps")+8)+"nytsyFiles"+File.separator+"company"+File.separator;
						String logoUrl = DateUtil.formatYYYMMDDHHMMSSAnd5Random()+com.getLogoUrl().substring(com.getLogoUrl().indexOf("."));
						ent.setLogoUrl(logoUrl);
						File file = new File(path+logoUrl);
						System.out.println(file.getAbsolutePath());
						//file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write(com.getLogoBytes());
						out.flush();
						out.close();
					}
					
					entList.add(ent);
					
				}
				
				List<Map> rest = this.appendInterEnt(entList);
				
				resultClass.setInfo(JSONArray.fromObject(rest).toString());
				
				resultClass.setSuccess(true);
			}else{
				resultClass.setErrorInfo("登陆超时,请重新登陆");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultClass;
	}
	
	private List<Map> appendInterEnt(List<TsEnterprise> entList) {
		if(entList!=null&&!entList.isEmpty()){
			 List<Map> mapList = new ArrayList<Map>();
			 				
//			int areaId = entList.get(0).getParentId();			
//			TsEnterprise tsEnterpriseArae = enterpriseDao.getEnterPirseByEntId(areaId);
			String areaCode = entList.get(0).getSysCode();
			 System.out.println("***************************************************");
			 System.out.println("                     "+areaCode);
			 System.out.println("***************************************************");
			int seq = enterpriseDao.getEntCodeSeq(" and ent_code like '"+areaCode+"%'");
			for(TsEnterprise tse:entList){
				seq++;
				String code = areaCode+ String.valueOf(1000+seq).substring(1);
				Map m = new HashMap();
				m.put("comId",tse.getTmpId());
				m.put("entCode", code);
				mapList.add(m);
				tse.setEntId(enterpriseDao.getTableSequence("TS_ENTERPRISE"));
				tse.setEntCode(code);
				tse.setAccount(code);
				
				
				//AuditCtrl auditCtrl = auditCtrlServiceInter.getAuditCtrlByEntId(enterprise.getEntId());
				int entLevel = this.enterpriseDao.getEntLevel(String.valueOf(tse.getParentId()));
				
				if(entLevel==5){
					tse.setTownRsts("1");
				}else if(entLevel==4){
					tse.setAreaRsts("1");
				}else if(entLevel==3){
					tse.setCityRsts("1");
				}else if(entLevel==2){
					tse.setProvinceRsts("1");
				}
				tse.setCompanyRsts("1");
				
				enterpriseDao.save(tse);
				
				//添加企业审核控制
				String entName = tse.getName();
				String auditEnt = String.valueOf(tse.getParentId());
				TsEnterprise enterprise = enterpriseDao.get(TsEnterprise.class, tse.getParentId());
				String ctrEnt = enterprise.getName();
				TsUser user = (TsUser) userDao.get(TsUser.class, tse.getCrtUserId());
				String ctrUser = user.getUserName();
				
				AuditCtrl auditCtrl = new AuditCtrl(tse.getName(), tse.getEntId(), auditEnt, ctrEnt, ctrUser, DateUtil.formatDateTime(), "1");
				auditCtrlServiceInter.addAuditCtrl(auditCtrl);
				
			}
			return mapList;
		}
		return null;
	}
	/**
	 * 附件上传接口
	 */
	public ResultClass addAppendix(@WebParam(name="token")String token, @WebParam(name="type")String type,
			@WebParam(name="appendix")Appendix appendix) {
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		if(interaccount!=null){
			try {
				//更新心跳
				onlyManager.active(interaccount);
				
				if("0".equals(type)){//资质附件
					//System.out.println("@@@@@@@@@@@@@@@@==============="+appendix.getBytes().length);
					if(appendix!=null&&appendix.getBytes()!=null){
						
						String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
						path = path.substring(0,path.lastIndexOf("webapps")+8)+"nytsyFiles"+File.separator+"zizhi"+File.separator;
						
						String pre = appendix.getAppName().substring(appendix.getAppName().lastIndexOf("."));
						
						String fileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random()+pre;
						
						File file = new File(path+fileName);
						System.out.println(file.getAbsolutePath());
						//file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write(appendix.getBytes());
						out.flush();
						out.close();
						
						
						ZizhiAppendix zappendix = new ZizhiAppendix();
						zappendix.setAppId(zizhiAppendixDao.getTableSequence("tb_zzappendix".toUpperCase()));
						zappendix.setZid(Integer.parseInt(appendix.getId()));
						zappendix.setAppName(appendix.getAppName());
						zappendix.setUploadtime(appendix.getUploadTime());
						zappendix.setOrderby(appendix.getOrderBy());
						zappendix.setPath(fileName);
						
						zizhiAppendixDao.save(zappendix);
						resultClass.setSuccess(true);
					}else{
						resultClass.setErrorInfo("附件不可为空");
					}
				}else if("1".equals(type)){//生产信息附件
					if(appendix!=null&&appendix.getBytes()!=null){
						if(appendix.getAppType()!=null&&!"".equals(appendix.getAppType())){//生产信息附件类型不可为空
							String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
							path = path.substring(0,path.lastIndexOf("webapps")+8)+"nytsyFiles"+File.separator+"production"+File.separator;
							
							String pre = appendix.getAppName().substring(appendix.getAppName().lastIndexOf("."));
							
							String fileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random()+pre;
							
							File file = new File(path+fileName);
							System.out.println(file.getAbsolutePath());
							//file.createNewFile();
							FileOutputStream out = new FileOutputStream(file);
							out.write(appendix.getBytes());
							out.flush();
							out.close();
							
							ProductionAppendix proApp = new ProductionAppendix();
							
							proApp.setAppId(productionAppendixDao.getTableSequence("tb_pro_appendix".toUpperCase()));
							proApp.setProId(Integer.parseInt(appendix.getId()));
							proApp.setAppType(appendix.getAppType());
							proApp.setAppName(appendix.getAppName());
							proApp.setPath(fileName);
							proApp.setUploadtime(appendix.getUploadTime());
							proApp.setOrderby(appendix.getOrderBy());
							
							
							this.productionAppendixDao.save(proApp);
							
							resultClass.setSuccess(true);
						}else{
							resultClass.setErrorInfo("生产信息附件类型不可为空");
						}
					}else{
						resultClass.setErrorInfo("附件不可为空");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resultClass.setErrorInfo("系统繁忙,请稍候重试");
			}
			
		}else{
			resultClass.setErrorInfo("登陆超时,请重新登陆");
		}
		
		return resultClass;
	}
	
	public ResultClass addProductList(@WebParam(name="token")String token, @WebParam(name="productList")List<Production> productList) {
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			try {
				
				//更新心跳
				onlyManager.active(interaccount);
				
				if(productList!=null&&!productList.isEmpty()){
					List<Map> rest = new ArrayList<Map>();
					
					com.hontek.company.pojo.Production product = null;
					TsEnterprise enterprise = null;
					for(Production pro:productList){
						
						enterprise = enterpriseDao.getEnterprise(" and entCode='"+pro.getEntCode()+"'");
						
						if(enterprise==null){
							continue;
						}
						
						product = new com.hontek.company.pojo.Production();
						product.setProId(productionDao.getTableSequence("tb_production".toUpperCase()));
						product.setEntId(enterprise.getEntId());
						product.setUserId(interaccount.getUserId());
						
						product.setProductinfo(pro.getProductInfo());
						product.setLicense(pro.getLicense());
						product.setCrttime(pro.getCrttime());
						
						Map map = new HashMap();
						map.put("proid", pro.getProId());
						map.put("proNo", product.getProId());
						
						rest.add(map);
						
						productionDao.save(product);
						
					}
					resultClass.setSuccess(true);
					
					resultClass.setInfo(JSONArray.fromObject(rest).toString());
					
				}else{
					resultClass.setInfo("请选择要上传的生产信息");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
				resultClass.setInfo("系统错误,请稍候重试");
			}
		}else{
			resultClass.setInfo("登陆超时,请重新登陆");
		}
		
		return resultClass;
	}
	
	/**
	 * 添加资质信息
	 */
	public ResultClass addZizhiInterface(@WebParam(name="token")String token,@WebParam(name="zzList")List<Zizhi> zzList) {
		ResultClass resultClass = new  ResultClass();
		resultClass.setSuccess(false);
		
		try {
			
			OnlyManager onlyManager = OnlyManager.getInstance();
			TbInterAccount interaccount = onlyManager.getAccount(token);
			
			if(interaccount!=null){
			
				//更新心跳
				onlyManager.active(interaccount);
				
				//非空判断
				if(zzList!=null&&!zzList.isEmpty()){
					com.hontek.company.pojo.Zizhi zi_tmp = new com.hontek.company.pojo.Zizhi();
					
					List<Map> rest = new ArrayList<Map>();
					
					String condition = "";
					TsEnterprise enterprise = null;
					
					for(Zizhi zz:zzList){
						if(zz!=null){
							
							condition = " and entCode='"+zz.getEntCode()+"'";
							enterprise = enterpriseDao.getEnterprise(condition);
							
							if(enterprise==null){
								continue;
							}
							
							
							zi_tmp = new com.hontek.company.pojo.Zizhi();
							zi_tmp.setId(zizhiDao.getTableSequence("tb_zizhi".toUpperCase()));
							zi_tmp.setAppType(zz.getAppType());
							zi_tmp.setZizhiName(zz.getZname());
//							zi_tmp.setGrantUnit(zz.getGrantUnit());
							zi_tmp.setAwardUnit(zz.getAwardUnit());
							zi_tmp.setAwardTime(zz.getAwardTime());
							zi_tmp.setRemark(zz.getRemark());
							zi_tmp.setEntId(enterprise.getEntId());
							
							Map map = new HashMap();
//							map.put("zid", zz.getZid());
							map.put("zNo", zi_tmp.getId());
							
							zizhiDao.save(zi_tmp);
							
							rest.add(map);
						}
					}
					resultClass.setSuccess(true);
					resultClass.setInfo(JSONArray.fromObject(rest).toString());
				}else{
					resultClass.setInfo("请选择要上传的资质信息");
				}
			}else{
				resultClass.setInfo("登陆超时,请重新登陆");
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return resultClass;
	}
	/**
	 * 获取企业二维码
	 */
	public ResultClass getDimenImg(String token, String entCode) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(entCode!=null&&!"".equals(entCode)){
				try {
					TsEnterprise enterprise = enterpriseDao.getEnterprise(" and entCode='"+entCode+"'");
					
					ResourceBundle bundle = ResourceBundle.getBundle("dimenno");
					
					
					//没有审核通过
					if("4".equals(enterprise.getCompanyRsts())){
						
						String path = System.getProperty("file.separator") ;
						String imgPath = DirectoryUtil.getProjectParentPath(getRequest())+path+"nytsyFiles"+path+"qrcode";
						
						
						//判断是否已经生成二维码图片
						File dimennoFile = new File(imgPath+path+enterprise.getEntCode()+".png");
						
						if(!dimennoFile.exists()){//二维码图片存在
							String url=bundle.getString("url");
							
							String checkUrl = bundle.getString("checkUrl");
							// 创建文件夹
							File file = new File(imgPath);
							if(!file.exists()){
								file.mkdirs();
							}
							// 产品信息
							String code =enterprise.getEntCode(); // 二维码
							
							String codeMaker = enterprise.getSimpleName();
							codeMaker=codeMaker==null?"":codeMaker;			
							String long_codeMaker="";  // 用于保存截取后的剩余长度.
							if(codeMaker!=null&&codeMaker.length()>13){	
								long_codeMaker=codeMaker.substring(13);
								codeMaker=codeMaker.substring(0,13);					
							}
							
							String proname = enterprise.getName();
							proname=proname==null?"":proname;
							
							String encoderContent =url+code;
							
							// 生成二维码图片的名字： 二维码.png
							String imgName1 = code+".png";
							String imgPath1 = imgPath+path+imgName1;
							
							String imgName2 = code+"_2.png";
							String imgPath2 = imgPath+path+imgName2;
							
							String imgName3 = code+"_3.png";
							String imgPath3 = imgPath+path+imgName3;
							
							
							String imgPath_top = getRequest().getRealPath("/");
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
							// x.w. 第二步：生成打印二维码 
							// 调用生成的图 类
							CreateImg cg = new CreateImg();
							String login_img = imgPath_top+path+"static"+path+"image"+path+"comm"+path+"code_top.jpg";
							String codeImg = cg.CreateImg_new(login_img,proname,enterprise.getLegalPerson(),long_codeMaker,code,checkUrl,code+".png",imgPath1);
						}
						
						BufferedInputStream in = new BufferedInputStream(new FileInputStream(dimennoFile));
						
						ByteArrayOutputStream out = new ByteArrayOutputStream(1024); 
						
						byte[] temp = new byte[1024];  
						
						int size = 0;         
				        while ((size = in.read(temp)) != -1) {         
				            out.write(temp, 0, size);         
				        }         
				        in.close();    
				        
				        resultClass.setQrcodeImgName(enterprise.getEntCode()+".png");
				        resultClass.setQrcodeImg(out.toByteArray());
				        
				        out.close();
				        //******************
				        dimennoFile = new File(imgPath+path+enterprise.getEntCode()+"_2.png");
				        in = new BufferedInputStream(new FileInputStream(dimennoFile));
						
						out = new ByteArrayOutputStream(1024); 
						
						temp = new byte[1024];  
						
						size = 0;         
				        while ((size = in.read(temp)) != -1) {         
				            out.write(temp, 0, size);         
				        }         
				        in.close();    
				        
				        resultClass.setQrcodeSmallName(enterprise.getEntCode()+"_2.png");
				        resultClass.setQrcodeSmall(out.toByteArray());
				        
				        out.close();
				        
				      //******************
				        resultClass.setQrcodeBigName(enterprise.getEntCode()+"_3.png");
				        dimennoFile = new File(imgPath+path+enterprise.getEntCode()+"_3.png");
				        in = new BufferedInputStream(new FileInputStream(dimennoFile));
						
						out = new ByteArrayOutputStream(1024); 
						
						temp = new byte[1024];  
						
						size = 0;         
				        while ((size = in.read(temp)) != -1) {         
				            out.write(temp, 0, size);         
				        }         
				        in.close();    
				        
				        resultClass.setQrcodeBig(out.toByteArray());
				        
				        out.close();
				        
				        resultClass.setSuccess(true);
						
					}else{
						resultClass.setErrorInfo("审核中，暂时不能下载二维码信息");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(e.getMessage());
					resultClass.setErrorInfo("系统繁忙，请稍候重试");
				}
			}
		}else{
			resultClass.setErrorInfo("登陆超时,请重新登陆");
		}
		
		return resultClass;
	}

	public ResultClass active(String token) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		if(interaccount!=null){
			interaccount.setTime(System.currentTimeMillis());
			resultClass.setSuccess(true);
		}else{
			resultClass.setErrorInfo("该令牌无效");
		}
		return resultClass;
	}
	
	
}
