package com.hontek.record.service.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.pojo.Combo;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.review.pojo.TreeVo;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

/**
 * 分类二维码信息service接口
 * @author lzk
 *
 */
public interface ProTypeQrcodeServiceInter {

	public TbProTypeQrcode getProTypeQrcodeById(int ptqId);
	
	/**
	 * 企业分类溯源列表
	 */
	public String findProTypeQrcodeList(TbProTypeQrcode ptq,int page,int rows,String sort,String order);
	
	/**
	 * 企业分类溯源列表(畜牧类4条，种植类8条)
	 */
	public String findTwoProTypeQrcode(String entId);
	
	/**
	 * web企业分类溯源列表
	 */
	public String findWebProTypeQrcode(int page,int rows,String typeClass,String entId);
	
	
	/**
	 * app企业分类溯源列表
	 */
	public String findAppProTypeQrcode(int page,int rows,String param,String typeClass);
	

	List<TbProTypeQrcode> findProTypeQrcode(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,Company user);
	
	List<TbProTypeBatch> findProTypeBatch(TbProTypeQrcode proTypeQrcode,HttpServletRequest request,Company user);

	
	
	List<TbProTypeQrcode> findProTypeQrcode(TbProTypeQrcode proTypeQrcode);


	String deleteProTypeQrcode(String ids, TsUser user, HttpServletRequest request);

	List<Object> findTypeName(String recId);
	
	
	public int findTypeConut(int comId);
	/**
	 * 获取登陆用户的分类信息
	 * @param user
	 * @return
	 */
	List<TbProTypeQrcode> getLoginProTypeQrcode(CompanyUser user);
	
	
	String getLoginProType(int entId);
	
	public String updateProTypeQrcode(TbProTypeQrcode proTypeQrcode);


	List<TreeVo> getEntTypeTree(int entId);

	public List<TbProTypeBatch> findProTypeBatchbyEntId(TbProTypeQrcode proTypeQrcode, HttpServletRequest request, int entId);

	public List<TbProTypeQrcode> findProTypeQrcodebyEntId(TbProTypeQrcode proTypeQrcode, HttpServletRequest request, int entId);

	public List<TbProTypeQrcode> findProTypeQrcodeListByEntId(int entId);

	public String updateQrcodeState(int ptqId);

	/**
	 * 根据分类二维码查询
	 * @param dimenno
	 * @return
	 */
	public TbProTypeQrcode findProTypeQrcodeByDimenno(String dimenno);
}
