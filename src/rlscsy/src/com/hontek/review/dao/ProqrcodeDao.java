package com.hontek.review.dao;

import org.hibernate.SQLQuery;

import com.hontek.comm.dao.BaseDao;
import com.hontek.review.pojo.TbProQrcode;
/**
 * <p>Title: 产品二维码信息</p>
 * <p>Description: 产品二维码信息DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProqrcodeDao extends BaseDao<TbProQrcode> {

	public void addQrcode(TbProQrcode qrcode) {
		qrcode.setRetroId(this.getTableSequence("TB_PRO_QRCODE"));
		this.save(qrcode);
	}

	
}
