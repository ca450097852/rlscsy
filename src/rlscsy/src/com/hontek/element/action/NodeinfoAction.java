package com.hontek.element.action;

import java.io.File;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.element.pojo.TbNodeinfo;
import com.hontek.element.service.inter.NodeinfoServiceInter;
/**
 * @ClassName: NodeinfoAction
 * @Description: TODO(生产节点信息 Action类)
 * @author qql
 * @version 1.0
 */
public class NodeinfoAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeinfoServiceInter nodeinfoServiceInter;
	private TbNodeinfo nodeinfo;
	private String ids;
	

	public TbNodeinfo getNodeinfo() {
		return nodeinfo;
	}
	public void setNodeinfo(TbNodeinfo nodeinfo) {
		this.nodeinfo = nodeinfo;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setNodeinfoServiceInter(NodeinfoServiceInter nodeinfoServiceInter) {
		this.nodeinfoServiceInter = nodeinfoServiceInter;
	}
	
	/**
	 * 查找生产节点信息
	 */
	public void findNodeinfoList(){
		jsonMsg = nodeinfoServiceInter.findNodeinfoList(nodeinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加生产节点信息
	 */
	public void addNodeinfo(){
		jsonMsg = nodeinfoServiceInter.addNodeinfo(nodeinfo);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改生产节点信息
	 */
	public void updateNodeinfo(){
		jsonMsg = nodeinfoServiceInter.updateNodeinfo(nodeinfo);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除生产节点信息
	 */
	public void deleteNodeinfo(){
		jsonMsg = nodeinfoServiceInter.deleteNodeinfo(ids);
		printJsonString(jsonMsg);
	}
	

	
}
