package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbStoreTransport;
import com.hontek.review.pojo.TbTraceAppdix;

/**
 * <p>Title: 仓储运输</p>
 * <p>Description: 仓储运输SERVICE接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public interface StoreTransportServiceInter {
	/**
	 * 添加
	 * @param storeTransport
	 * @return
	 */
	String addStoreTransport(TbStoreTransport storeTransport, List<TbTraceAppdix> traceAppList);
	/**
	 * 依据ID集合删除
	 * @param ids
	 * @return
	 */
	String deleteStoreTransportByIds(String ids);
	/**
	 * 修改
	 * @param storeTransport
	 * @return
	 */
	String updateStoreTransport(TbStoreTransport storeTransport, List<TbTraceAppdix> traceAppList);
	/**
	 * 查询
	 * @param storeTransport
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	String findStoreTransportList(TbStoreTransport storeTransport, int page,
			int rows, String sort, String order);

}
