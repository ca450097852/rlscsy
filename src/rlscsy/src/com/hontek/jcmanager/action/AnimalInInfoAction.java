package com.hontek.jcmanager.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.Pager;
import com.hontek.jcmanager.pojo.AnimalInInfo;
import com.hontek.jcmanager.service.inter.AnimalInInfoServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 生猪进厂表
 * 
 * @author Administrator
 * 
 */
public class AnimalInInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AnimalInInfoServiceInter animalInInfoService;
	private AnimalInInfo animalInInfo;
	private String ids;
	private TsEnterprise enterprise = new TsEnterprise();
	
	public TsEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public AnimalInInfoServiceInter getAnimalInInfoService() {
		return animalInInfoService;
	}

	public void setAnimalInInfoService(AnimalInInfoServiceInter animalInInfoService) {
		this.animalInInfoService = animalInInfoService;
	}

	public AnimalInInfo getAnimalInInfo() {
		return animalInInfo;
	}

	public void setAnimalInInfo(AnimalInInfo animalInInfo) {
		this.animalInInfo = animalInInfo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void add() {
		this.printJsonString(animalInInfoService.addAnimalInInfo(animalInInfo));
	}

	public void update() {
		this.printJsonString(animalInInfoService.updateAnimalInInfo(animalInInfo));
	}

	public void delete() {
		this.printJsonString(animalInInfoService.deleteAnimalInInfo(ids));
	}
	/**
	 * 屠宰量统计查询
	 */
	public void getAnimalInList(){
		printJsonString(animalInInfoService.getAnimalInList(enterprise));
	}
	public void findList() {
		Pager<AnimalInInfo> pager = animalInInfoService.findList(animalInInfo, page, rows, sort, order);
		this.printJsonString(this.createEasyUiJson(pager));
	}
}
