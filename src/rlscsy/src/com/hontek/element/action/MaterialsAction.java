package com.hontek.element.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.element.pojo.Materials;
import com.hontek.element.service.inter.MaterialsServiceInter;
import com.hontek.sys.pojo.TsUser;

/**
 * 原料信息表
 * @author IMZK
 *
 */
public class MaterialsAction extends BaseAction{
	private MaterialsServiceInter materialsService;
	private Materials materials;
	private String ids;
	public MaterialsServiceInter getMaterialsService() {
		return materialsService;
	}
	public void setMaterialsService(MaterialsServiceInter materialsService) {
		this.materialsService = materialsService;
	}
	public Materials getMaterials() {
		return materials;
	}
	public void setMaterials(Materials materials) {
		this.materials = materials;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void add(){
		TsUser user = this.getLoginTsUser();
		if(materials!=null){
			materials.setUserId(user.getUserId());
		}
		jsonMsg = materialsService.add(materials);
		this.printJsonString(jsonMsg);
	}
	
	public void update(){
		jsonMsg = materialsService.update(materials);
		this.printJsonString(jsonMsg);
	}
	
	public void delete(){
		jsonMsg = materialsService.delete(ids);
		this.printJsonString(jsonMsg);
	}
	
	public void findPager(){
		jsonMsg = materialsService.findPager(materials, page, rows, sort , order);
		this.printJsonString(jsonMsg);
	}
}
