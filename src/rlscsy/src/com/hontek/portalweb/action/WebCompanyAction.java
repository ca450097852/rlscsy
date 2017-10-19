package com.hontek.portalweb.action;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.exception.AppException;
import com.hontek.company.pojo.Company;
import com.hontek.company.service.inter.CompanyServiceInter;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.service.inter.ProTypeQrcodeServiceInter;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.service.inter.EntTypeServiceInter;

/**
 * 加盟企业
 * 
 * @author qql
 * @created 2014
 */
public class WebCompanyAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private CompanyServiceInter companyServiceInter;	
	private Company company;
	
	private int entId;
	
	private ProTypeQrcodeServiceInter proTypeQrcodeService;
	private TbProTypeQrcode ptq;
	
	private String param;
	
	
	private EntTypeServiceInter entTypeService;
	private String typeId;
	

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public void setEntTypeService(EntTypeServiceInter entTypeService) {
		this.entTypeService = entTypeService;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public TbProTypeQrcode getPtq() {
		return ptq;
	}

	public void setPtq(TbProTypeQrcode ptq) {
		this.ptq = ptq;
	}


	public void setProTypeQrcodeService(ProTypeQrcodeServiceInter proTypeQrcodeService) {
		this.proTypeQrcodeService = proTypeQrcodeService;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setCompanyServiceInter(CompanyServiceInter companyServiceInter) {
		this.companyServiceInter = companyServiceInter;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * 分页查询所有的企业
	 * 
	 */
	public void findList(){
		if(company==null){
			company = new Company();
		}		
		/*Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			int entid = entStyle.getEntId()==177792?0:entStyle.getEntId();
			company.setParentId(entid);
		}*/
		company.setParentId(-1);//不是流通节点
		printJsonString(companyServiceInter.findCompanyPagerList(company, page, rows, sort, order));
		
	}
	
	/**
	 * 分页查询分区所有的企业
	 * 
	 */
	public void findListByArea(){
		if(company==null){
			company = new Company();
		}	
		company.setParentId(-1);//不是流通节点
		printJsonString(companyServiceInter.findCompanyPagerList(company, page, rows, sort, order));
		/*Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			int entid = entStyle.getEntId()==177792?0:entStyle.getEntId();			
			TsUser user = this.getLoginTsUser();
			if(user.getEntId()!=entid){
				entid = user.getEntId();
			}			
			company.setParentId(entid);
		}
		
		printJsonString(enterprseService.findList(company, page, rows));
		*/
	}
	
	/**
	 * 企业分类溯源列表
	 */
	public void findProTypeQrcodeList()throws AppException{
		printJsonString(this.proTypeQrcodeService.findProTypeQrcodeList(ptq, page, rows, sort, order));
	}
	
	/**
	 * 企业分类溯源列表(畜牧类4条，种植类8条)(根据域名主体进行数据筛选)
	 */
	public void findTwoProTypeQrcode()throws AppException{
		String mainEntId = "";
		Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			mainEntId = (entStyle.getEntId()==0||entStyle.getEntId()==177792)?"":entStyle.getEntId()+"";
		}
		printJsonString(this.proTypeQrcodeService.findTwoProTypeQrcode(mainEntId));
	}
	
	/**
	 * 企业分类溯源列表-畜牧类
	 */
	public void findAnimalProTypeQrcode(){
		String mainEntId = "";
		Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			mainEntId = (entStyle.getEntId()==0||entStyle.getEntId()==177792)?"":entStyle.getEntId()+"";
		}
		printJsonString(this.proTypeQrcodeService.findWebProTypeQrcode(page, rows, "2",mainEntId));//type_class = 2 为畜牧类
	}
	
	/**
	 * 企业分类溯源列表-种植类
	 */
	public void findPlantProTypeQrcode(){
		String mainEntId = "";
		/*Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			mainEntId = (entStyle.getEntId()==0||entStyle.getEntId()==177792)?"":entStyle.getEntId()+"";
		}*/
		printJsonString(this.proTypeQrcodeService.findWebProTypeQrcode(page, rows, "2",mainEntId));//type_class = 1 为种植类
	}
	
	/**
	 * app企业分类溯源列表-种植类
	 */
	public void appFindPTQ(){
		printJsonString(this.proTypeQrcodeService.findAppProTypeQrcode(page, rows,param, "2"));//type_class = 1 为种植类，2 为畜牧类
	}
	
	/**
	 * 加载组织机构类型 -- 下拉
	 */
	public void getEntTypeToSelect(){
		printJsonString(entTypeService.getEntTypeToSelect(typeId));
	}
	
}
