package com.hontek.jcmanager.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.QmarketDetectionInfo;

public interface QmarketDetectionInfoServiceInter {

	String addQmarketDetectionInfo(QmarketDetectionInfo qmarketDetectionInfo);

	String updateQmarketDetectionInfo(QmarketDetectionInfo qmarketDetectionInfo);

	String deleteQmarketDetectionInfo(String ids);

	Pager<QmarketDetectionInfo> findList(
			QmarketDetectionInfo qmarketDetectionInfo, int page, int rows,
			String sort, String order);

}
