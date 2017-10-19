package com.hontek.company.service.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hontek.company.pojo.ValidCode;

/**
 * 二维码验证表
 * @author IMZK
 *
 */
public interface ValidCodeServiceInter {

	String addValidCode(String objId, String typeId, int count,
			int entId, HttpServletRequest request, HttpServletResponse response);

	String updateValidCode(ValidCode validCode);

}
