package com.hontek.sys.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.sys.dao.BannerDao;
import com.hontek.sys.pojo.Banner;
import com.hontek.sys.service.inter.BannerServiceInter;

public class BannerServiceImpl extends BaseServiceImpl implements BannerServiceInter{
	private BannerDao bannerDao;

	public void setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
	}

	public String addBanner(Banner banner) {
		String msg = "添加成功";
		try {
			if(banner!=null){
				banner.setImgId(bannerDao.getTableSequence("tb_banner".toUpperCase()));
				banner.setCrttime(DateUtil.formatDateTime());
				banner.setImgName(banner.getImgUrl());
				bannerDao.save(banner);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String updateBanner(Banner banner) {
		String msg = "修改成功";
		try {
			if(banner!=null){
				banner.setCrttime(DateUtil.formatDateTime());
				banner.setImgName(banner.getImgUrl());
				bannerDao.update(banner);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public String deleteBanners(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null && !"".equals(ids)){
				bannerDao.executeHql("delete from Banner where imgId in ("+ids+")");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	public Pager<Banner> findList(Banner banner, int page, int rows,
			String sort, String order) {
		String condition = "";
		if(banner!=null){
			int position = banner.getPosition();
			int entId = banner.getEntId();
			if(position>0){
				condition += " and position = "+position;
			}
			if(entId>0){
				condition += " and entId = "+entId;
			}
		}
		return bannerDao.findPager(Banner.class, condition, page, rows);
	}
	
}
