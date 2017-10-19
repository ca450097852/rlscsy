package com.hontek.sys.service.inter;

import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.Banner;

public interface BannerServiceInter {

	String addBanner(Banner banner);

	String updateBanner(Banner banner);

	String deleteBanners(String ids);

	Pager<Banner> findList(Banner banner, int page, int rows, String sort,
			String order);

}
