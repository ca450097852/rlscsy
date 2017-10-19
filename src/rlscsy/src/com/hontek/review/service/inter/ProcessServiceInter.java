package com.hontek.review.service.inter;

import java.util.List;

import com.hontek.review.pojo.TbProcess;
import com.hontek.review.pojo.TbTraceAppdix;

/**
 * <p>Title: 加工包装</p>
 * <p>Description: 加工包装SERVICE接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public interface ProcessServiceInter {
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String deleteProcessByIds(String ids);
	/**
	 * 新增加工信息
	 * @param process
	 * @return
	 */
	String addProcess(TbProcess process, List<TbTraceAppdix> traceAppList);
	/**
	 * 修改
	 * @param process
	 * @return
	 */
	String updateProcess(TbProcess process, List<TbTraceAppdix> traceAppList);
	/**
	 * 查询
	 * @param process
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	String findProcessList(TbProcess process, int page, int rows, String sort,
			String order);

}
