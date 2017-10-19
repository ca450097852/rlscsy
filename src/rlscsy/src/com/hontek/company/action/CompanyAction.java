package com.hontek.company.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Decoder;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.company.pojo.Company;
import com.hontek.company.service.inter.CompanyServiceInter;

/**
 * <p>Title: 企业表</p>
 * <p>Description:企业Action 类</p>
 * @version 1.0
 */
public class CompanyAction extends BaseAction {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private CompanyServiceInter companyServiceInter;	
	private Company company;
	private int comId;
	private String ids;	
	private String state;	
	private String area;	
	private String comCode;//经营者编码
	
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCompanyServiceInter(CompanyServiceInter companyServiceInter) {
		this.companyServiceInter = companyServiceInter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getUploadify() {
		return uploadify;
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

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	/**
	 * 添加企业
	 */
	public void addCompany(){
		
		jsonMsg  = companyServiceInter.addCompany(company);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 企业注册
	 */
	public void register(){
		jsonMsg  = companyServiceInter.addCompany2(company);
		printJsonString(jsonMsg);
	}
	

	/**
	 * 上传Logo
	 */
	public void uploadLogo() {			
		
		//获取项目存放文件目录
		File parent = DirectoryUtil.getProjectFileDataPath(getRequest());	
		//获取具体附件目录
		File fileDir = new File(parent, "company");	
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		uploadify.renameTo(new File(fileDir,newFileName));
				
		this.printJsonString(newFileName);
	}
	
	
	/**
	 * 上传附件
	 */
	public void uploadFile() {			
		
		//获取项目存放文件目录
		File parent = DirectoryUtil.getProjectFileDataPath(getRequest());	
		//获取具体附件目录
		File fileDir = new File(parent, "company");	
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName.lastIndexOf("."));
		}
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		uploadify.renameTo(new File(fileDir,newFileName));
				
		this.printJsonString(newFileName);
	}
	
	public void uploadFileForBase64(){
        String success = "fail";
        
        if(uploadifyFileName==null){
			this.printJsonString(success);
			return;
		}
        // 只允许jpg/png
        String header ="data:image/jpeg;base64";    
        String header2 ="data:image/png;base64";     
        String postfix = ".jpg";       
		if(uploadifyFileName.startsWith(header)){
			postfix = ".jpg";
		}else if(uploadifyFileName.startsWith(header2)){
			postfix = ".png";
		}else{
			System.out.println("文件上传失败！不是图片jpg、png类型");
			this.printJsonString(success);
			return;
		}		
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "company");				   		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = sdf.format(new Date())+postfix;	
		
		
        try{    		
	            // 去掉头部
        		uploadifyFileName=uploadifyFileName.split(",")[1];
	            //image = image.substring(header.length());
	            // 写入磁盘
	            BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(uploadifyFileName);        //将字符串格式的image转为二进制流（biye[])的decodedBytes
                String imgFilePath =fileDir.getAbsolutePath()+File.separator+fileName;                        //指定图片要存放的位置
                FileOutputStream out = new FileOutputStream(imgFilePath);        //新建一个文件输出器，并为它指定输出位置imgFilePath
                out.write(decodedBytes); //利用文件输出器将二进制格式decodedBytes输出
                out.close();                        //关闭文件输出器
                System.out.println("文件上传成功！文件名称为："+imgFilePath);
        		this.printJsonString(fileName);
        }catch(Exception e){
                success = "上传文件失败！|"+e.getMessage();
                e.printStackTrace();
        }
	}
	
	/**
	 * 删除附件
	 */
	public void deleteFile(){
		//获取附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "company");		
		File file = new File(fileDir,fileName);		
		if(file.exists()){
			this.printJsonString(String.valueOf(file.delete()));
		}
	}
	
	/**
	 * 修改企业
	 */
	public void updateCompany(){
		jsonMsg  = companyServiceInter.updateCompany(company);
		printJsonString(jsonMsg);
	}
	/**
	 * 上传修改企业
	 */
	public void updateCompanyLogo(){
		jsonMsg  = companyServiceInter.updateCompanyLogo(company);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 删除企业
	 */
	public void deleteCompany(){
		jsonMsg  = companyServiceInter.deleteCompany(ids);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 查询企业
	 */
	public void findCompanyPagerList(){
		
		//jsonMsg  = companyServiceInter.findCompanyPagerList(company, page, rows, sort , order);
		jsonMsg  = companyServiceInter.findPagerList(company, page, rows, sort , order);
		//获取文件夹
		printJsonString(jsonMsg);
	}
	
	public void findCompanyAreaName(){
		printJsonString(companyServiceInter.findCompanyAreaName(comId));
	}
	
	
	/**
	 * 修改状态
	 */
	public void updateCompanyState(){
		jsonMsg  = companyServiceInter.updateCompanyState(ids,state);
		printJsonString(jsonMsg);
	}
	
	
	/**
	 * 节点下拉
	 */
	public void getCompanyToSelect(){
		jsonMsg  = companyServiceInter.getCompanyToSelect(area,"1");
		printJsonString(jsonMsg);
	}
	
	/**
	 * app节点下拉
	 */
	public void getAppNodeToSelect(){
		jsonMsg  = companyServiceInter.getCompanyToSelect(area,null);
		printJsonString(jsonMsg);
	}
	
	/**
	 * 修改状态
	 */
	public void getLoginCompany(){
		
		Company loginCompany = (Company)getSession().getAttribute("loginCompany");
		if(loginCompany!=null){
			System.out.println("loginCompany=="+loginCompany.getComId());
			List<Company> list = companyServiceInter.findList(loginCompany);
			if(list.size()>0){
				printJsonString(getJSON(list.get(0)));
			}
		}
	}
	/**
	 * 验证组织帐号是否存在（唯一）
	 */
	public void findNameIsUnique(){
		printJsonString(companyServiceInter.findNameIsUnique(company.getName()));
	}

	
	/**
	 * 根据经营者编码，查找企业信息
	 */
	public void getCompanyInfoByComCode(){
		printJsonString(getJSON(companyServiceInter.getCompanyByComCode(comCode)));
	}

	/**
	 * 根据企业id，查找企业信息
	 */
	public void getCompanyInfoByComId(){
		printJsonString(getJSON(companyServiceInter.getCompanyByComId(comId)));
	}
	
	
	public void findNodeCompanyList(){
		printJsonString(companyServiceInter.findNodeCompanyList());

	}
	
}
