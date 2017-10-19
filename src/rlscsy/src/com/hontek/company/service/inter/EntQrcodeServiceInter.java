package com.hontek.company.service.inter;

import com.hontek.company.pojo.EntQrcode;

/**
 * <p>Title: 企业二维码信息表</p>
 * <p>Description: 企业二维码信息接口 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface EntQrcodeServiceInter {

	public String addEntQrcode(EntQrcode entQrcode);
	
	public String deleteEntQrcode(String ids);
	
	public String updateEntQrcode(EntQrcode entQrcode);
	
	public String findEntQrcodePagerList(EntQrcode entQrcode,int page,int rows,String sort,String order);
}
