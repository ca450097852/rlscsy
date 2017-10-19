package com.hontek.company.service.inter;

import com.hontek.company.pojo.Tongbao;


/**
 * <p>Title: 通报处罚企业信息表</p>
 * <p>Description: 通报处罚企业信息表接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface TongbaoServiceInter {

	public String addTongbao(Tongbao tongbao);
	
	public String deleteTongbao(String ids);
	
	public String updatePublishTongbao(String ids);
	
	public String updateTongbao(Tongbao tongbao);
	
	public String findTongbaoPagerList(Tongbao tongbao,int page,int rows,String sort,String order);
}
