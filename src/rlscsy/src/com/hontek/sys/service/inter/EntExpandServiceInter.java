package com.hontek.sys.service.inter;

import java.util.List;

import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 企业扩展信息
 * @author ZK
 *
 */
public interface EntExpandServiceInter {

	Pager<TsEnterprise> findPager(TsEnterprise enterprise, int page, int rows,
			String sort, String order);

	EntExpand getEntExpandByEntId(int entId);
	
	List<EntExpand> findAllList(EntExpand entExpand);
}
