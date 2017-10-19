package com.hontek.info.service.inter;

import java.util.List;

import com.hontek.info.pojo.TInfo;

public interface InfoServiceInter {
	public String addInfo(TInfo info);
	public String deleteInfo(String id);
	public String updateInfo(TInfo info);
	public String findInfoList(TInfo info,int page,int rows,String sort,String order);
	public String updateAuditInfo(String ids, String rsts);
	public String findNews(String con);
}
