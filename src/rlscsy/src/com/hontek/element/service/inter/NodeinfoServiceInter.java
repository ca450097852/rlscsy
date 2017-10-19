package com.hontek.element.service.inter;

import com.hontek.element.pojo.TbNodeinfo;
/**
 * @ClassName: NodeinfoServiceInter
 * @Description: TODO(生产节点信息 service 接口)
 * @author qql
 * @version 1.0
 */
public interface NodeinfoServiceInter {

	public String addNodeinfo(TbNodeinfo nodeinfo);

	public String deleteNodeinfo(String ids);

	public String updateNodeinfo(TbNodeinfo nodeinfo);

	public String findNodeinfoList(TbNodeinfo nodeinfo, int page, int rows, String sort,String order);	
	

}
