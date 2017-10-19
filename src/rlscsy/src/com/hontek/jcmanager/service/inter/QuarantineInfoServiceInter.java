package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.QuarantineInfo;

/**
 * 屠宰厂检疫检验信息表
 * @author Administrator
 *
 */
public interface QuarantineInfoServiceInter {

	String addQuarantineInfo(QuarantineInfo quarantineInfo);

	String updateQuarantineInfo(QuarantineInfo quarantineInfo);

	String deleteQuarantineInfo(String ids);

	Pager<QuarantineInfo> findList(QuarantineInfo quarantineInfo, int page, int rows, String sort, String order);

	QuarantineInfo findByJyh(String quarantineAnimalProductsId);

	QuarantineInfo getQuarantineInfoByCode(String code);
}
