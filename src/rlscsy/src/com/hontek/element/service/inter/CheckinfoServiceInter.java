package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbCheckinfo;
/**
 * <p>Title: 检验信息</p>
 * <p>Description: 检验信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface CheckinfoServiceInter {

	public String addCheckinfo(TbCheckinfo checkinfo, String jsonApp);

	public String deleteCheckinfo(String ids);

	public String updateCheckinfo(TbCheckinfo checkinfo, String jsonApp,String ids);

	public String findCheckinfoList(TbCheckinfo checkinfo, int page, int rows, String sort,String order);	
	
	/**
	 * 查找检验信息，带附件
	 */
	public String findListWithApp(TbCheckinfo checkinfo, int page, int rows, String sort,String order);

}
