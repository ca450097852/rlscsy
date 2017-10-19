package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.TbSaleinfo;
import com.hontek.element.service.inter.SaleinfoServiceInter;
/**
 * <p>Title: 销售信息</p>
 * <p>Description: 销售信息Action实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author z.l.j
 * @version 1.0
 */
public class SaleinfoAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SaleinfoServiceInter saleinfoServiceInter;
	private TbSaleinfo saleinfo;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbSaleinfo getSaleinfo() {
		return saleinfo;
	}

	public void setSaleinfo(TbSaleinfo trace) {
		this.saleinfo = trace;
	}


	public void setSaleinfoServiceInter(SaleinfoServiceInter saleinfoServiceInter) {
		this.saleinfoServiceInter = saleinfoServiceInter;
	}

	/**
	 * 查找销售信息
	 */
	public void findSaleinfoList(){
		jsonMsg = saleinfoServiceInter.findSaleinfoList(saleinfo, page, rows, sort , order);
		printJsonString(jsonMsg);
	}
	/**
	 * 添加销售
	 */
	public void addSaleinfo(){
		jsonMsg = saleinfoServiceInter.addSaleinfo(saleinfo);
		this.printJsonString(jsonMsg);
	}
	
	/**
	 * 修改销售
	 */
	public void updateSaleinfo(){
		jsonMsg = saleinfoServiceInter.updateSaleinfo(saleinfo);
		this.printJsonString(jsonMsg);
	}

	/**
	 * 删除销售
	 */
	public void deleteSaleinfo(){
		jsonMsg = saleinfoServiceInter.deleteSaleinfo(ids);
		printJsonString(jsonMsg);
	}
}
