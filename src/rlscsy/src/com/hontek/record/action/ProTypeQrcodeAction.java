package com.hontek.record.action;

import java.io.File;
import java.util.List;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.pojo.Company;
import com.hontek.company.pojo.CompanyUser;
import com.hontek.record.pojo.TbProTypeBatch;
import com.hontek.record.pojo.TbProTypeQrcode;
import com.hontek.record.service.inter.ProTypeQrcodeServiceInter;
import com.hontek.review.pojo.TreeVo;
/**
 * 分类二维码信息ACTION
 * @author lzk
 *
 */
@SuppressWarnings("serial")
public class ProTypeQrcodeAction extends BaseAction{
	private ProTypeQrcodeServiceInter proTypeQrcodeService;
	private TbProTypeQrcode proTypeQrcode;
	private String ids;
	private String recId;
	private int entId;
	
	private int ptqId;
	
	private String dimenno;
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getUploadify() {
		return uploadify;
	}

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TbProTypeQrcode getProTypeQrcode() {
		return proTypeQrcode;
	}

	public void setProTypeQrcode(TbProTypeQrcode proTypeQrcode) {
		this.proTypeQrcode = proTypeQrcode;
	}

	
	
	public String getDimenno() {
		return dimenno;
	}

	public void setDimenno(String dimenno) {
		this.dimenno = dimenno;
	}

	public void setPtqId(int ptqId) {
		this.ptqId = ptqId;
	}


	public String getRecId() {
		return recId;
	}


	public void setRecId(String recId) {
		this.recId = recId;
	}


	public void setProTypeQrcodeService(
			ProTypeQrcodeServiceInter proTypeQrcodeService) {
		this.proTypeQrcodeService = proTypeQrcodeService;
	}
	
	
	/**
	 * 根据分类二维码查询
	 * @param dimenno
	 * @return
	 */
	public void findProTypeQrcodeByDimenno(){
		TbProTypeQrcode proTypeQrcode = proTypeQrcodeService.findProTypeQrcodeByDimenno(dimenno);
		printJsonString(this.getJSON(proTypeQrcode));
	}

	public void getProTypeQrcodeById(){
		TbProTypeQrcode proTypeQrcode = proTypeQrcodeService.getProTypeQrcodeById(ptqId);
		printJsonString(this.getJSON(proTypeQrcode));
		
	}
	
	public void findProTypeQrcode(){
		List<TbProTypeQrcode> list = proTypeQrcodeService.findProTypeQrcode(proTypeQrcode,getRequest(),getLoginCompanyInSession());
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	public void findProTypeQrcodebyEntId(){
		List<TbProTypeQrcode> list = proTypeQrcodeService.findProTypeQrcodebyEntId(proTypeQrcode,getRequest(),entId);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	
	public void findProTypeBatch(){
		List<TbProTypeBatch> list = proTypeQrcodeService.findProTypeBatch(proTypeQrcode,getRequest(),getLoginCompanyInSession());
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	public void findProTypeBatchbyEntId(){
		List<TbProTypeBatch> list = proTypeQrcodeService.findProTypeBatchbyEntId(proTypeQrcode,getRequest(),entId);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	
	public void findProTypeQrcodeList(){
		List<TbProTypeQrcode> list = proTypeQrcodeService.findProTypeQrcode(proTypeQrcode);
		jsonMsg = getJSON(list);
		printJsonString(jsonMsg);
	}
	
	
	public void deleteProTypeQrcode(){
		printJsonString(proTypeQrcodeService.deleteProTypeQrcode(ids,null,this.getRequest()));
	}
	/**
	 * 读取分类名称
	 */
	public void findTypeName(){
		List<Object> list = proTypeQrcodeService.findTypeName(recId);
		if(!list.isEmpty()){
			jsonMsg = String.valueOf(list.get(0));
		}
		printJsonString(jsonMsg);
	}
	
	
	public void findTypeConut(){
		Object companyObject = getSession().getAttribute("loginCompany");
		if(companyObject!=null){			
			int comId = ((Company)companyObject).getComId();//企业ID
			jsonMsg = String.valueOf(proTypeQrcodeService.findTypeConut(comId));
		}
		printJsonString(jsonMsg);
	}
	
	//获取登陆用户的分类信息
	public void getLoginProTypeQrcode(){		
		CompanyUser user=(CompanyUser)getSession().getAttribute("loginCompanyUser");	
		List<TbProTypeQrcode> list = proTypeQrcodeService.getLoginProTypeQrcode(user);
		jsonMsg = this.getJSON(list);
		printJsonString(jsonMsg);
	}
	
	
	public  void getLoginProType(){
		printJsonString(proTypeQrcodeService.getLoginProType(getLoginUserEntId()));
	}
	
	public void updateProTypeQrcode(){
		printJsonString(proTypeQrcodeService.updateProTypeQrcode(proTypeQrcode));

	}
	
	public void findProTypeQrcodeListByEntId(){
		List<TbProTypeQrcode> list = proTypeQrcodeService.findProTypeQrcodeListByEntId(entId);
		printJsonString(this.getJSON(list));
	}
	
	public void updateQrcodeState(){
		String msg = proTypeQrcodeService.updateQrcodeState(ptqId);
		printJsonString(msg);
	}
	
	
	/**
	 * 上传附件
	 */
	public void uploadTypeImg() {
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "protype");		
		
		System.out.println("uploadifyFileName@@@@@@@@@@@@@=" + uploadifyFileName);
		String newFileName;
		
		try {
			// 获取扩展名
			String extName = "";// 扩展名			
			if (uploadifyFileName.lastIndexOf(".") >= 0) {
				extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
			}
			newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
			File newFile = new File(fileDir,newFileName);
			uploadify.renameTo(newFile);
			System.out.println("newFileName@@@@@@@@@@@@@=" + newFileName);
			this.printJsonString(newFileName);
		} catch (Exception e) {
		}
		
	}
	
	/**
	 * 删除附件
	 */
	public void deleteTypeImg(){
		//获取资质附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "protype");		
		File file = new File(fileDir,fileName);		
		if(file.exists()){
			file.delete();
			this.printJsonString("删除成功！");
		}
	}
	
	/**
	 * 获取登陆用户的entId
	 */
	public void getLoginEntId(){		
		Object companyObject = getSession().getAttribute("loginCompany");
		if(companyObject!=null){			
			int comId = ((Company)companyObject).getComId();//企业ID
			printJsonString(String.valueOf(comId));
		}
		printJsonString(null);
	}
	
	/*public void getIsBatch(){
		if(this.getLoginTsUser()!=null){
			int entId = this.getLoginTsUser().getEntId();
			TsEnterprise enterprise = proTypeQrcodeService.getEntById(entId);
			printJsonString(String.valueOf(enterprise.getIsbatch()));
		}
		printJsonString("0");
	}*/
	
	
	/**
	 * 获取企业产品分类tree
	 */
	public void getEntTypeTree(){		
		Object companyObject = getSession().getAttribute("loginCompany");
		if(companyObject!=null){			
			int comId = ((Company)companyObject).getComId();//企业ID
			
			List<TreeVo> treeList = proTypeQrcodeService.getEntTypeTree(comId);
			jsonMsg = this.getJSON(treeList);
			printJsonString(jsonMsg);
		}
	}
	
}
