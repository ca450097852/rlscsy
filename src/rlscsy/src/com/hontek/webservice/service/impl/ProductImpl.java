package com.hontek.webservice.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.review.dao.PlantRaiseDao;
import com.hontek.review.dao.PreventionDao;
import com.hontek.review.dao.ProAreaDao;
import com.hontek.review.dao.ProBatchDao;
import com.hontek.review.dao.ProCheckDao;
import com.hontek.review.dao.ProSeedDao;
import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.dao.ProcessDao;
import com.hontek.review.dao.ProductAppendixDao;
import com.hontek.review.dao.ProductDao;
import com.hontek.review.dao.StoreTransportDao;
import com.hontek.review.dao.TraceAppdixDao;
import com.hontek.review.pojo.TbPlantRaise;
import com.hontek.review.pojo.TbPrevention;
import com.hontek.review.pojo.TbProArea;
import com.hontek.review.pojo.TbProBatch;
import com.hontek.review.pojo.TbProCheck;
import com.hontek.review.pojo.TbProSeed;
import com.hontek.review.pojo.TbProType;
import com.hontek.review.pojo.TbProcess;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TbProductAppendix;
import com.hontek.review.pojo.TbStoreTransport;
import com.hontek.review.pojo.TbTraceAppdix;
import com.hontek.review.service.inter.ProBatchServiceInter;
import com.hontek.review.service.inter.ProductServiceInter;
import com.hontek.synchronous.action.OnlyManager;
import com.hontek.sys.dao.EnterpriseDao;
import com.hontek.sys.pojo.TbInterAccount;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.webservice.pojo.PlantRaise;
import com.hontek.webservice.pojo.Prevention;
import com.hontek.webservice.pojo.ProArea;
import com.hontek.webservice.pojo.ProCheck;
import com.hontek.webservice.pojo.ProSeed;
import com.hontek.webservice.pojo.ProcessPack;
import com.hontek.webservice.pojo.Product;
import com.hontek.webservice.pojo.ProductAppendix;
import com.hontek.webservice.pojo.ResultClass;
import com.hontek.webservice.pojo.StoreTransport;
import com.hontek.webservice.pojo.TraceAppendix;
import com.hontek.webservice.service.inter.ProductInter;

@WebService
@SOAPBinding(style = Style.RPC)
public class ProductImpl implements ProductInter{
	
	private ProductDao productDao;
	private EnterpriseDao enterpriseDao;
	private ProTypeDao proTypeDao;
	private ProductServiceInter productService;
	private ProAreaDao proAreaDao;
	private ProSeedDao proSeedDao;
	private PreventionDao preventionDao;
	private ProcessDao processDao;
	private StoreTransportDao storeTransportDao;
	private ProCheckDao proCheckDao;
	private PlantRaiseDao plantRaiseDao;
	private ProductAppendixDao productAppendixDao;
	private TraceAppdixDao traceAppdixDao;
	private ProBatchServiceInter proBatchServiceInter;
	private ProBatchDao proBatchDao;
	
	
	public void setProBatchDao(ProBatchDao proBatchDao) {
		this.proBatchDao = proBatchDao;
	}

	public void setProBatchServiceInter(ProBatchServiceInter proBatchServiceInter) {
		this.proBatchServiceInter = proBatchServiceInter;
	}

	public void setTraceAppdixDao(TraceAppdixDao traceAppdixDao) {
		this.traceAppdixDao = traceAppdixDao;
	}

	public void setProductAppendixDao(ProductAppendixDao productAppendixDao) {
		this.productAppendixDao = productAppendixDao;
	}

	public void setPlantRaiseDao(PlantRaiseDao plantRaiseDao) {
		this.plantRaiseDao = plantRaiseDao;
	}

	public void setProCheckDao(ProCheckDao proCheckDao) {
		this.proCheckDao = proCheckDao;
	}

	public void setStoreTransportDao(StoreTransportDao storeTransportDao) {
		this.storeTransportDao = storeTransportDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	public void setPreventionDao(PreventionDao preventionDao) {
		this.preventionDao = preventionDao;
	}

	public void setProSeedDao(ProSeedDao proSeedDao) {
		this.proSeedDao = proSeedDao;
	}

	public void setProAreaDao(ProAreaDao proAreaDao) {
		this.proAreaDao = proAreaDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public void setProductService(ProductServiceInter productService) {
		this.productService = productService;
	}
	
	
	@Resource
	private WebServiceContext context;
	
	private HttpServletRequest getRequest(){
		MessageContext ctx = context.getMessageContext();
		
		HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
		
		return request;
	}
	
	
	
	
	public ResultClass addProducts(@WebParam(name="token")String token, @WebParam(name="productList")List<Product> productList) {
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		Map<String, TsEnterprise> entMap = new HashMap<String, TsEnterprise>();
		Map<String , TbProType> proTypeMap = new HashMap<String, TbProType>();
		
		
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(productList!=null&&!productList.isEmpty()){
				
				TsEnterprise enterprise = null;
				TbProType proType = null;
				TbProduct tbPro = null;
				
				List resultList = new ArrayList();
				
				for(Product product:productList){
					if(product!=null){
						
						String tmpProId = product.getProId();
						
						if((product.getTypeNo()==null||"".equals(product.getTypeNo()))
								&&(product.getEntCode()==null||"".equals(product.getEntCode()))
								&&(tmpProId==null||"".equals(tmpProId))){
							continue;
						}
						
						
						//查找产品类型ID
						proType = proTypeMap.get(product.getTypeNo());
						if(proType==null){
							proType = proTypeDao.getProType(" and typeNo='"+product.getTypeNo()+"'");
							if(proType==null){
								continue;
							}
						}
						//查找企业ID
						enterprise = entMap.get(product.getEntCode());
						if(enterprise==null){
							enterprise = enterpriseDao.getEnterprise(" and entCode='"+product.getEntCode()+"'");
							if(enterprise==null){
								continue;
							}
						}
						
						tbPro = new TbProduct();
						
						tbPro.setEntId(enterprise.getEntId());
						tbPro.setTypeId(proType.getTypeId());
						tbPro.setProName(product.getProName());
						tbPro.setBarcode(product.getBarcode());
						tbPro.setSizeAttr(product.getSizeAttr());
						tbPro.setProDesc(product.getProDesc());
						tbPro.setUnit(product.getUnit());
						tbPro.setManufacturer(product.getManufacturer());
						tbPro.setSourceTel(product.getSourceTel());
						tbPro.setSourceAddr(product.getSourceAddr());
						tbPro.setDistributor(product.getDistributor());
						tbPro.setDistributorTel(product.getDistributorTel());
						tbPro.setDistributorAddr(product.getDistributorAddr());
						tbPro.setRetain(product.getRetain());
						tbPro.setStorageConditions(product.getStorageConditions());
						tbPro.setShelfLife(product.getShelfLife());
						tbPro.setAuthentication(product.getAuthentication());
						
						
						tbPro.setSysCode(interaccount.getSysCode());
						tbPro.setState("0");//设置状态
						tbPro.setUpdateTime(DateUtil.formatDateTime());//最后修改时间 
						tbPro.setUserId(interaccount.getUserId());//修改用户，同步产品以创建接入帐号USERID为准
						
						
						//入库
						productService.addProduct(tbPro,null);
						
						Map resultMap = new HashMap();
						
						
						resultMap.put("proId", tmpProId);
						
						resultMap.put("proCode", tbPro.getProCode());
						
						resultList.add(resultMap);
						
					}
				}
				
				resultClass.setSuccess(true);
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
				
			}else{
				resultClass.setErrorInfo("同步产品信息不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	/**
	 * 同步产地信息
	 */
	public ResultClass addProArea(@WebParam(name="token")String token, @WebParam(name="proAreaList")List<ProArea> proAreaList) {
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			//更新心跳
			onlyManager.active(interaccount);
			
			if(proAreaList!=null&&!proAreaList.isEmpty()){
				TbProArea tbProArea = null;
				
				
				List resultList = new ArrayList();
				
				for(ProArea parea : proAreaList){
					if(parea!=null){
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+parea.getProCode()+"'");
						
						if(tmpPro==null){
							continue;
						}
						
						tbProArea = new TbProArea();
						
						tbProArea.setAreaId(proAreaDao.getTableSequence("tb_pro_area".toUpperCase()));
						
						
						tbProArea.setProId(tmpPro.getProId());
						tbProArea.setAreaName(parea.getAreaName());
						tbProArea.setAreaAddr(parea.getAreaAddr());
						tbProArea.setAreaIntro(parea.getAreaIntro());
						tbProArea.setEdatope(parea.getEdatope());
						tbProArea.setAreaWater(parea.getAreaWater());
						tbProArea.setClimatope(parea.getClimatope());
						
						proAreaDao.save(tbProArea);
						Map<String, String> resultMap = new HashMap<String, String>();
						
						resultMap.put("areaId", parea.getAreaId());
						resultMap.put("areaNo", String.valueOf(tbProArea.getAreaId()));
						
						resultList.add(resultMap);
					}
				}
				resultClass.setSuccess(true);
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
			}else{
				resultClass.setErrorInfo("请选择要上传的产地信息");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	
	/**
	 * 同步种苗信息
	 */
	public ResultClass addProSeed(@WebParam(name="token")String token, @WebParam(name="proSeedList")List<ProSeed> proSeedList) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(proSeedList!=null&&!proSeedList.isEmpty()){
				
				List resultList = new ArrayList();
				
				for(ProSeed proSeed:proSeedList){
					if(proSeed!=null){
						
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+proSeed.getProCode()+"'");
						
						if(tmpPro==null){
							continue;
						}
						
						
						TbProSeed tbProSeed = new TbProSeed();
						tbProSeed.setSeedId(proSeedDao.getTableSequence("tb_pro_seed".toUpperCase()));
						tbProSeed.setProId(tmpPro.getProId());
						tbProSeed.setSeedName(proSeed.getSeedName());
						tbProSeed.setSeedAddr(proSeed.getSeedAddr());
						tbProSeed.setSeedCompany(proSeed.getSeedCompany());
						tbProSeed.setFeature(proSeed.getFeature());
						
						proSeedDao.save(tbProSeed);
						
						Map<String, String> resultMap = new HashMap<String, String>();
						
						resultMap.put("seedId",proSeed.getSeedId());
						resultMap.put("seedNo", String.valueOf(tbProSeed.getSeedId()));
						
						resultList.add(resultMap);
					}
					
				}
				
				resultClass.setSuccess(true);
				
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
				
			}else{
				resultClass.setErrorInfo("请选择上传种苗信息");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}
	
	
	/**
	 * 同步防疫信息
	 */
	public ResultClass addPrevention(@WebParam(name="token")String token,
			@WebParam(name="preventionList")List<Prevention> preventionList) {
		
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(preventionList!=null&&!preventionList.isEmpty()){
				
				List resultList = new ArrayList();
				
				for(Prevention prevention:preventionList){
					if(prevention!=null){
						
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+prevention.getProCode()+"'");
						
						if(tmpPro==null){
							continue;
						}
						
						
						TbPrevention tprevention = new TbPrevention();
						tprevention.setPtId(preventionDao.getTableSequence("tb_prevention".toUpperCase()));
						tprevention.setProId(tmpPro.getProId());
						tprevention.setDrugName(prevention.getDrugName());
						tprevention.setDrugCompany(prevention.getDrugCompany());
						tprevention.setDrugWay(prevention.getDrugWay());
						tprevention.setDrugTime(prevention.getDrugTime());
						tprevention.setDrugObject(prevention.getDrugObject());
						tprevention.setDrugCycle(prevention.getDrugCycle());
						tprevention.setDosage(prevention.getDosage());
						
						preventionDao.save(tprevention);
						
						Map<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("ptId", prevention.getPtId());
						resultMap.put("ptNo", String.valueOf(tprevention.getPtId()));
						
						resultList.add(resultMap);
						
					}
				}
				
				resultClass.setSuccess(true);
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
				
			}else{
				resultClass.setErrorInfo("同步内容不可为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	
	/**
	 * 同步加工包装信息
	 */
	public ResultClass addProcess(String token, List<ProcessPack> processList) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(processList!=null&&!processList.isEmpty()){
				
				List resultList = new ArrayList();
				
				for(ProcessPack process:processList){
					TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+process.getProCode()+"'");
					if(tmpPro==null){
						continue;
					}
					TbProcess tprocess = new TbProcess();
					tprocess.setProcessId(processDao.getTableSequence("tb_process".toUpperCase()));
					tprocess.setProId(tmpPro.getProId());
					tprocess.setProcessUser(process.getProcessUser());
					tprocess.setProcessAddr(process.getProcessAddr());
					tprocess.setProcessCompany(process.getProcessCompany());
					tprocess.setProcessTime(process.getProcessTime());
					
					processDao.save(tprocess);
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap.put("processId", process.getProcessId());
					resultMap.put("processNo", String.valueOf(tprocess.getProcessId()));
					
					resultList.add(resultMap);
				}
				
				resultClass.setSuccess(true);
				
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
				
			}else{
				resultClass.setErrorInfo("同步信息不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	
	/**
	 * 同步仓储运输信息
	 */
	public ResultClass addStoreTransport(String token,
			List<StoreTransport> tranList) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			
			//更新心跳
			onlyManager.active(interaccount);
			
			if(tranList!=null&&!tranList.isEmpty()){
				
				List resultList = new ArrayList();
				
				for(StoreTransport sport:tranList){
					if(sport!=null){
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+sport.getProCode()+"'");
						if(tmpPro==null){
							continue;
						}
						
						TbStoreTransport tsport = new  TbStoreTransport();
						tsport.setStId(storeTransportDao.getTableSequence("tb_store_transport".toUpperCase()));
						tsport.setStorageWay(sport.getStorageWay());
						tsport.setStorageCondi(sport.getStorageCondi());
						tsport.setTransportWay(sport.getTransportWay());
						tsport.setProId(tmpPro.getProId());
						
						storeTransportDao.save(tsport);
						
						Map<String, String> resultMap = new HashMap<String, String>();
						
						resultMap.put("stId", sport.getStId());
						resultMap.put("stNo", String.valueOf(tsport.getStId()));
						
						resultList.add(resultMap);
					}
				}
				
				resultClass.setSuccess(true);
				resultClass.setInfo(JSONArray.fromObject(resultList).toString());
				
			}else{
				resultClass.setErrorInfo("同步信息不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	
	/**
	 * 同步检验报告信息
	 */
	public ResultClass addProCheck(String token, List<ProCheck> proCheckList) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			//更新心跳
			onlyManager.active(interaccount);
			
			if(proCheckList!=null&&!proCheckList.isEmpty()){
				
				List resultList = new ArrayList();
				
				for(ProCheck pc:proCheckList){
					if(pc!=null){
						
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+pc.getProCode()+"'");
						if(tmpPro==null){
							continue;
						}
						
						TbProCheck proCheck = new TbProCheck();
						
						proCheck.setCheckId(proCheckDao.getTableSequence("tb_pro_check".toUpperCase()));
						proCheck.setCheckName(pc.getCheckName());
						proCheck.setCheckNum(pc.getCheckNum());
						proCheck.setCheckUnit(pc.getCheckUnit());
						proCheck.setCheckTime(pc.getCheckTime());
						proCheck.setCheckResult(pc.getCheckResult());
						proCheck.setProId(tmpPro.getProId());
						
						proCheckDao.save(proCheck);
						
						Map<String, String> resultMap = new HashMap<String, String>();
						
						resultMap.put("checkId", pc.getCheckId());
						resultMap.put("checkNo",String.valueOf(proCheck.getCheckId()));
						
						resultList.add(resultMap);
					}
					
					resultClass.setSuccess(true);
					
					resultClass.setInfo(JSONArray.fromObject(resultList).toString());
					
				}
			}else{
				resultClass.setErrorInfo("同步信息不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}
	
	/**
	 * 同步施肥喂养信息
	 */
	public ResultClass addPlantRaise(String token,
			List<PlantRaise> plantRaiseList) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			//更新心跳
			onlyManager.active(interaccount);
			
			if(plantRaiseList!=null&&!plantRaiseList.isEmpty()){
				
				List resultList = new ArrayList();
				for(PlantRaise pr:plantRaiseList){
					if(pr!=null){
						TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+pr.getProCode()+"'");
						if(tmpPro==null){
							continue;
						}
						
						TbPlantRaise tpr = new TbPlantRaise();
						
						tpr.setPrId(plantRaiseDao.getTableSequence("tb_plant_raise".toUpperCase()));
						tpr.setProId(tmpPro.getProId());
						tpr.setFeedName(pr.getFeedName());
						tpr.setFeedCompany(pr.getFeedCompany());
						tpr.setFeedWay(pr.getFeedWay());
						tpr.setFeedCycle(pr.getFeedCycle());
						tpr.setFeedTime(pr.getFeedTime());
						tpr.setDosage(pr.getDosage());
						
						plantRaiseDao.save(tpr);
						Map<String, String> resultMap = new HashMap<String, String>();
						resultMap.put("prId", pr.getPrId());
						resultMap.put("prNo", String.valueOf(tpr.getPrId()));
						
						resultList.add(resultMap);
					}
					
					resultClass.setSuccess(true);
					
					resultClass.setInfo(JSONArray.fromObject(resultList).toString());
					
				}
			}else{
				resultClass.setErrorInfo("同步信息不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}

	
	/**
	 * 同步产品图片
	 */
	public ResultClass addProductAppendix(String token, ProductAppendix proImg) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			try {
				//更新心跳
				onlyManager.active(interaccount);
				
				TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+proImg.getProCode()+"'");
				if(tmpPro==null){
					resultClass.setErrorInfo("找不到对应的产品");
					return resultClass;
				}
				
				String path = DirectoryUtil.getProjectParentPath(getRequest())+File.separator+"nytsyFiles"+File.separator+"proimg"+File.separator;
				
				
				String pre = proImg.getAppName().substring(proImg.getAppName().lastIndexOf("."));
				
				String fileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random()+pre;
				
				File file = new File(path+fileName);
				System.out.println(file.getAbsolutePath());
				//file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(proImg.getBytes());
				out.flush();
				out.close();
				
				TbProductAppendix appendix = new TbProductAppendix();
				
				appendix.setAppId(productAppendixDao.getTableSequence("tb_product_appendix".toUpperCase()));
				appendix.setProId(tmpPro.getProId());
				appendix.setAppName(proImg.getAppName());
				appendix.setPath(fileName);
				appendix.setOrderby((proImg.getOrderby()==null||"".equals(proImg.getOrderby()))?5:Integer.parseInt(proImg.getOrderby()));
				appendix.setRemark(proImg.getRemark());
				appendix.setUploadTime(DateUtil.formatDateTime());
				
				productAppendixDao.save(appendix);
				
				resultClass.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		return resultClass;
	}

	public ResultClass addTraceAppendix(String token, TraceAppendix traceApp) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			try {
				//更新心跳
				onlyManager.active(interaccount);
				
				TbTraceAppdix trace = new TbTraceAppdix();
				
				if("1".equals(traceApp.getAppdixType())){
					TbProArea pa = proAreaDao.get(TbProArea.class, traceApp.getId());
					trace.setProId(pa.getProId());
				}else if("2".equals(traceApp.getAppdixType())){
					TbProSeed ps = proSeedDao.get(TbProSeed.class, traceApp.getId());
					trace.setProId(ps.getProId());
				}else if("3".equals(traceApp.getAppdixType())){
					TbPlantRaise pr = plantRaiseDao.get(TbPlantRaise.class, traceApp.getId());
					trace.setProId(pr.getProId());
				}else if("4".equals(traceApp.getAppdixType())){
					TbPrevention tp = preventionDao.get(TbPrevention.class, traceApp.getId());
					trace.setProId(tp.getProId());
				}else if("5".equals(traceApp.getAppdixType())){
					TbProcess prc = processDao.get(TbProcess.class, traceApp.getId());
					trace.setProId(prc.getProId());
				}else if("6".equals(traceApp.getAppdixType())){
					TbStoreTransport tst = storeTransportDao.get(TbStoreTransport.class, traceApp.getId());
					trace.setProId(tst.getProId());
				}else if("7".equals(traceApp.getAppdixType())){
					TbProCheck pc = proCheckDao.get(TbProCheck.class, traceApp.getId());
					trace.setProId(pc.getProId());
				}
				
				
				String path = DirectoryUtil.getProjectParentPath(getRequest())+File.separator+"nytsyFiles"+File.separator+"proimg"+File.separator;
				
				String pre = traceApp.getAppendixName().substring(traceApp.getAppendixName().lastIndexOf("."));
				
				String fileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random()+pre;
				
				File file = new File(path+fileName);
				//file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(traceApp.getBytes());
				out.flush();
				out.close();
				
				trace.setAppdixId(traceAppdixDao.getTableSequence("tb_trace_appdix".toUpperCase()));
				trace.setAppdixName(traceApp.getAppendixName());
				trace.setAppdixUrl(fileName);
				trace.setUploadTime(DateUtil.formatDateTime());
				trace.setAppdixType(traceApp.getAppdixType());
				trace.setPid(traceApp.getId());
				
				traceAppdixDao.save(trace);
				
				resultClass.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		return resultClass;
	}

	
	/**
	 * 添加批次
	 */
	public ResultClass addProBatch(String token, String proCode,String batchNo, String proTime) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		TbProduct tmpPro = productDao.getProductByProCode(" and proCode='"+proCode+"'");
		if(tmpPro==null){
			resultClass.setErrorInfo("找不到对应的产品");
			return resultClass;
		}
		
		if(interaccount!=null){
			//更新心跳
			onlyManager.active(interaccount);
			
			if(proTime!=null&&!"".equals(proTime)){
				TbProBatch proBatch = new TbProBatch();
				proBatch.setProId(tmpPro.getProId());
				proBatch.setProTime(proTime);
				proBatch.setBatchNo(batchNo);
				proBatchServiceInter.addProBatch(proBatch, getRequest());
				
				resultClass.setSuccess(true);
				
				resultClass.setInfo(proBatch.getBatchNo());
				
			}else{
				resultClass.setErrorInfo("生产日期不能为空");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}
	
	public ResultClass getProBatchImg(String token, String batchNo) {
		OnlyManager onlyManager = OnlyManager.getInstance();
		TbInterAccount interaccount = onlyManager.getAccount(token);
		
		ResultClass resultClass = new ResultClass();
		resultClass.setSuccess(false);
		
		if(interaccount!=null){
			//更新心跳
			onlyManager.active(interaccount);
			
			TbProBatch proBatch = proBatchDao.getProBatchByNo(batchNo);
			if(proBatch!=null){
				
				try {
					String path = System.getProperty("file.separator") ;
					String imgPath = DirectoryUtil.getProjectParentPath(getRequest())+path+"nytsyFiles"+path+"qrcode";
					
					
					//判断是否已经生成二维码图片
					File dimennoFile = new File(imgPath+path+proBatch.getDimenno()+".png");
					
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(dimennoFile));
					
					ByteArrayOutputStream out = new ByteArrayOutputStream(1024); 
					
					byte[] temp = new byte[1024];  
					
					int size = 0;         
			        while ((size = in.read(temp)) != -1) {         
			            out.write(temp, 0, size);         
			        }         
			        in.close();    
			        
			        resultClass.setQrcodeImgName(proBatch.getDimenno()+".png");
			        resultClass.setQrcodeImg(out.toByteArray());
			        
			        out.close();
			        //******************
			        dimennoFile = new File(imgPath+path+proBatch.getDimenno()+"_2.png");
			        in = new BufferedInputStream(new FileInputStream(dimennoFile));
					
					out = new ByteArrayOutputStream(1024); 
					
					temp = new byte[1024];  
					
					size = 0;         
			        while ((size = in.read(temp)) != -1) {         
			            out.write(temp, 0, size);         
			        }         
			        in.close();    
			        
			        resultClass.setQrcodeSmallName(proBatch.getDimenno()+"_2.png");
			        resultClass.setQrcodeSmall(out.toByteArray());
			        
			        out.close();
			        
			      //******************
			        resultClass.setQrcodeBigName(proBatch.getDimenno()+"_3.png");
			        dimennoFile = new File(imgPath+path+proBatch.getDimenno()+"_3.png");
			        in = new BufferedInputStream(new FileInputStream(dimennoFile));
					
					out = new ByteArrayOutputStream(1024); 
					
					temp = new byte[1024];  
					
					size = 0;         
			        while ((size = in.read(temp)) != -1) {         
			            out.write(temp, 0, size);         
			        }         
			        in.close();    
			        
			        resultClass.setQrcodeBig(out.toByteArray());
			        
			        resultClass.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
					resultClass.setErrorInfo("系统繁忙，请稍候重试");
				}
				
			}else{
				resultClass.setErrorInfo("无此编号信息");
			}
		}else{
			resultClass.setErrorInfo("登陆超时，请重新登陆");
		}
		
		return resultClass;
	}
	
	
	
}
