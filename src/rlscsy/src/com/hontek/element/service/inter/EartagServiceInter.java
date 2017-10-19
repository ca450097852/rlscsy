package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbEartag;
/**
 * <p>Title: 耳标信息</p>
 * <p>Description: 耳标信息service接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public interface EartagServiceInter {

	public String addEartag(TbEartag eartag);

	public String deleteEartag(String ids);

	public String updateEartag(TbEartag eartag);

	public String findEartagList(TbEartag eartag, int page, int rows, String sort,String order);	

}
