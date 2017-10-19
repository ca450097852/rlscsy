package com.hontek.element.service.inter;

import com.hontek.element.pojo.ProcessUse;

/**
 * 加工投入品信息表
 * @author IMZK
 *
 */
public interface ProcessUseServiceInter {

	String add(ProcessUse processUse);

	String update(ProcessUse processUse);

	String delete(String ids);

	String findPager(ProcessUse processUse, int page, int rows, String sort,
			String order);

}
