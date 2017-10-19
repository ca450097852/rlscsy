package com.hontek.sys.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.sys.dao.EntExpandDao;
import com.hontek.sys.dao.TagStyleDao;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TagStyle;
import com.hontek.sys.service.inter.TagStyleServiceInter;

public class TagStyleServiceImpl extends BaseServiceImpl implements TagStyleServiceInter{

	private TagStyleDao tagStyleDao;	
	
	private EntExpandDao entExpandDao;

	public void setEntExpandDao(EntExpandDao entExpandDao) {
		this.entExpandDao = entExpandDao;
	}
	public void setTagStyleDao(TagStyleDao tagStyleDao) {
		this.tagStyleDao = tagStyleDao;
	}



	Logger logger = Logger.getLogger(this.getClass());
	
	public String addTagStyle(TagStyle tagStyle) {
		String msg = "添加失败";
		if(tagStyle!=null){
			try {
				tagStyle.setTsId(tagStyleDao.getTableSequence("t_ent_style".toUpperCase()));
				tagStyle.setCreateTime(DateUtil.formatDateTime());
				tagStyleDao.save(tagStyle);
				msg = "添加成功";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("添加二维码风格失败");
			}
		}
		return msg;
	}

	public String updateTagStyle(TagStyle tagStyle) {
		String msg = "修改失败";
		try {
			if(tagStyle!=null){
				tagStyleDao.update(tagStyle);
				msg = "修改成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String deleteTagStyle(String ids) {
		return null;
	}



	public TagStyle getTagStyleInfo(int entId) {	
		TagStyle tgs = tagStyleDao.getTagStyleInfo(entId);
		EntExpand eep = entExpandDao.get(EntExpand.class, entId);
		if(tgs!=null&&eep!=null){
			tgs.setMbDomain(eep.getMbDomain());
		}
		return tgs;
	}

}
