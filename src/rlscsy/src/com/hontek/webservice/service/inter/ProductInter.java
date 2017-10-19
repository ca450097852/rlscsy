package com.hontek.webservice.service.inter;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

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
/**
 * 产品溯源同步接口
 * @author lzk
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public interface ProductInter {
	/**
	 * 同步产品信息
	 * @param token
	 * @param productList
	 * @return
	 */
	public ResultClass addProducts(@WebParam(name="token")String token,@WebParam(name="productList")List<Product> productList);
	
	/**
	 * 同步产地信息
	 * @param token
	 * @param proAreaList
	 * @return
	 */
	public ResultClass addProArea(@WebParam(name="token")String token,@WebParam(name="proAreaList")List<ProArea> proAreaList);
	
	/**
	 * 同步种苗信息
	 * @param token
	 * @param proSeedList
	 * @return
	 */
	public ResultClass addProSeed(@WebParam(name="token")String token,@WebParam(name="proSeedList")List<ProSeed> proSeedList);
	/**
	 * 同步防疫信息
	 * @param token
	 * @param preventionList
	 * @return
	 */
	public ResultClass addPrevention(@WebParam(name="token")String token,@WebParam(name="preventionList")List<Prevention> preventionList);
	/**
	 * 同步加工包装信息
	 * @param token
	 * @param processList
	 * @return
	 */
	public ResultClass addProcess(@WebParam(name="token")String token,@WebParam(name="processList")List<ProcessPack> processList);
	/**
	 * 同步仓储运输信息
	 * @param token
	 * @param tranList
	 * @return
	 */
	public ResultClass addStoreTransport(@WebParam(name="token")String token,@WebParam(name="tranList")List<StoreTransport> tranList);
	/**
	 * 同步检验报告信息
	 * @param token
	 * @param proCheckList
	 * @return
	 */
	public ResultClass addProCheck(@WebParam(name="token")String token,@WebParam(name="proCheckList")List<ProCheck> proCheckList);
	/**
	 * 同步施肥喂养信息
	 * @param token
	 * @param plantRaiseList
	 * @return
	 */
	public ResultClass addPlantRaise(@WebParam(name="token")String token,@WebParam(name="plantRaiseList")List<PlantRaise> plantRaiseList);
	/**
	 * 同步产品图片信息
	 * @param token
	 * @param proImg
	 * @return
	 */
	public ResultClass addProductAppendix(@WebParam(name="token")String token,@WebParam(name="proImg")ProductAppendix proImg);
	/**
	 * 添加附件信息
	 * @param token
	 * @return
	 */
	public ResultClass addTraceAppendix(@WebParam(name="token")String token,@WebParam(name="traceApp")TraceAppendix traceApp);
	/**
	 * 添加批次
	 * @param token
	 * @param batchNo  批准编号，为空的话系统自动产生
	 * @param proTime  生产日期 	格式：yyyy-MM-dd
	 * @return
	 */
	public ResultClass addProBatch(@WebParam(name="token")String token,@WebParam(name="proCode")String proCode,@WebParam(name="batchNo")String batchNo,@WebParam(name="proTime")String proTime);
	/**
	 * 获取批次二维码
	 * @param token
	 * @param batchNo 批次编码
	 * @return
	 */
	public ResultClass getProBatchImg(@WebParam(name="token")String token,@WebParam(name="batchNo")String batchNo);
}
