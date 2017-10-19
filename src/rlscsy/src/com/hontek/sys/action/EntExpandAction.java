package com.hontek.sys.action;

import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.service.inter.EntExpandServiceInter;
/**
 * 企业扩展信息
 * @author ZK
 *
 */
public class EntExpandAction extends BaseAction{
	private EntExpandServiceInter entExpandService;
	private TsEnterprise enterprise;
	private EntExpand entExpand;


	public TsEnterprise getEnterprise() {
		return enterprise;
	}


	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}


	private EntExpand getEntExpand() {
		return entExpand;
	}


	private void setEntExpand(EntExpand entExpand) {
		this.entExpand = entExpand;
	}


	public void setEntExpandService(EntExpandServiceInter entExpandService) {
		this.entExpandService = entExpandService;
	}
	
	
	public void findPager(){
		Pager<TsEnterprise> pager = entExpandService.findPager(enterprise,page,rows,sort,order);
		printJsonString(this.createEasyUiJson(pager));
	}
	
	public void findAllList(){
		List<EntExpand> list = entExpandService.findAllList(entExpand);
		printJsonString(getJSON(list));
	}
}
