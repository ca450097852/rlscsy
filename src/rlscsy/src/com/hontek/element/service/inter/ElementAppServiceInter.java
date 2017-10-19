package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbElementApp;
/**
 * <p>Title: 附件信息</p>
 * <p>Description: 附件信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface ElementAppServiceInter {

	public String addElementApp(TbElementApp elementApp);

	public String deleteElementApp(String ids);

	public String updateElementApp(TbElementApp elementApp);

	public String findElementAppList(TbElementApp elementApp, int page, int rows, String sort,String order);	

}
