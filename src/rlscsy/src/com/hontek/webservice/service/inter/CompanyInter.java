package com.hontek.webservice.service.inter;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.hontek.webservice.pojo.Appendix;
import com.hontek.webservice.pojo.Company;
import com.hontek.webservice.pojo.Production;
import com.hontek.webservice.pojo.ResultClass;
import com.hontek.webservice.pojo.Zizhi;

/**
 * 企业信息同步接口
 * @author lzk
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public interface CompanyInter {
	/**
	 * 获取口令
	 * @param account
	 * @param password
	 * @return
	 */
	public ResultClass getToken(@WebParam(name="account")String account,@WebParam(name="password")String password);
	/**
	 * 上传企业信息
	 * @param token
	 * @param comList
	 * @return
	 */
//	public ResultClass addcompanyInfo(@WebParam(name="token")String token,@WebParam(name="comList")List<Company> comList);
	public ResultClass addCompany(@WebParam(name="token")String token,@WebParam(name="company")Company company);
	
	/**
	 * 附件上传
	 * @param token 口令
	 * @param type	附件类型  0:资质附件   1:生产信息附件
	 * @param appendix	附件
	 * @return
	 */
	//public ResultClass addAppendix(@WebParam(name="token")String token,@WebParam(name="type")String type,@WebParam(name="appendix")Appendix appendix);
	/**
	 * 上传企业生产信息
	 * @param token
	 * @param productList
	 * @return
	 */
	//public ResultClass addProductList(@WebParam(name="token")String token,@WebParam(name="productList")List<Production> productList);
	/**
	 * 上传企业资质信息
	 * @param token
	 * @param zzList
	 * @return
	 */
	//public ResultClass addZizhiInterface(@WebParam(name="token")String token,@WebParam(name="zzList")List<Zizhi> zzList);
	/**
	 * 获取企业图二维码图片
	 * @param token
	 * @param entCode
	 * @return
	 */
	public ResultClass getDimenImg(@WebParam(name="token")String token,@WebParam(name="entCode")String entCode);
	/**
	 * 心跳方法
	 * @param token
	 * @return
	 */
	public ResultClass active(@WebParam(name="token")String token);
	
	public ResultClass addZizhi(@WebParam(name="token")String token ,@WebParam(name="zizhi")Zizhi zizhi);
	
}
