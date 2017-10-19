package com.hontek.element.service.impl;

import org.apache.log4j.Logger;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.NodeinfoDao;
import com.hontek.element.pojo.TbNodeinfo;
import com.hontek.element.service.inter.NodeinfoServiceInter;
/**
 * @ClassName: NodeinfoServiceImpl
 * @Description: TODO(生产节点信息 service实现类)
 * @date 2015-8-10 下午07:38:59
 * @author qql
 * @version 1.0
 */
public class NodeinfoServiceImpl extends BaseServiceImpl implements NodeinfoServiceInter {

	private NodeinfoDao nodeinfoDao;
	/**
	 * 注入DAO
	 * @param nodeinfoDao
	 */

	Logger logger = Logger.getLogger(this.getClass());

	
	public void setNodeinfoDao(NodeinfoDao nodeinfoDao) {
		this.nodeinfoDao = nodeinfoDao;
	}

	
	/**
	 * 添加生产节点
	 */
	public String addNodeinfo(TbNodeinfo nodeinfo) {
		String msg = "添加生产节点成功!";
		try {
			nodeinfo.setNiId(nodeinfoDao.getTableSequence("tb_node_info".toUpperCase()));
			//nodeinfo.setCrttime(DateUtil.formatDate());
			Object b= nodeinfoDao.save(nodeinfo);

			System.out.println(b);
		} catch (AppException e) {
			e.printStackTrace();
			msg = "添加生产节点出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;	
	}
	

	
	/**
	 * 删除生产节点
	 */
	public String deleteNodeinfo(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					TbNodeinfo nodeinfo = nodeinfoDao.get(TbNodeinfo.class, Integer.valueOf(idArray[i]));
					if(nodeinfo!=null){
						nodeinfoDao.delete(nodeinfo);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

	/**
	 * 分页查询生产节点
	 */
	public String findNodeinfoList(TbNodeinfo nodeinfo, int page, int rows, String sort, String order) {		
		String jsonMsg ="";		
		try {
			//拼接查询条件
			StringBuffer condition = new StringBuffer("");
			if(nodeinfo!=null){						
				int recId = nodeinfo.getRecId();
				if(recId!=0){
					condition.append(" and recId = "+recId+" ");
				}
			}
			//添加排序
			condition.append(sortCondtion(sort, order));		
		
			Pager<TbNodeinfo>  pager = nodeinfoDao.findPager(TbNodeinfo.class,condition.toString(), page, rows);			

			jsonMsg = this.createEasyUiJson(pager);
		} catch (AppException e) {
			e.printStackTrace();
			jsonMsg = "查询生产节点出现异常!"+e.getMessage();
			logger.error(jsonMsg);
		}		
		return jsonMsg;
	}

	/**
	 * 修改生产节点
	 */
	public String updateNodeinfo(TbNodeinfo nodeinfo) {
		String msg = "修改生产节点成功!";
		try {
			nodeinfoDao.update(nodeinfo);
			
		} catch (AppException e) {
			e.printStackTrace();
			msg = "修改生产节点出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
	}

}
