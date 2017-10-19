package com.hontek.sys.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;

import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.sys.pojo.EntExpand;
import com.hontek.sys.pojo.EntStyle;
import com.hontek.sys.pojo.TsEnterprise;
import com.hontek.sys.pojo.TsUser;

import com.hontek.sys.service.inter.EnterpriseServiceInter;


/**
 * 组织机构
 * 
 * @author w.x.w
 * @created 2013-09-09
 */
public class EnterpriseActon extends BaseAction {
	private static final long serialVersionUID = 1L;
	private EnterpriseServiceInter enterprseService;
	private TsEnterprise enterprise = new TsEnterprise();
	private String list_enterprise; 
	private String entId;
	private String sts;
	private String account;
	private File fileQueue;
	private String fileQueueFileName;
	private String jsonType;
	private String entCode; 
	
	
	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
	
	public File getFileQueue() {
		return fileQueue;
	}

	public void setFileQueue(File fileQueue) {
		this.fileQueue = fileQueue;
	}

	public String getFileQueueFileName() {
		return fileQueueFileName;
	}

	public void setFileQueueFileName(String fileQueueFileName) {
		this.fileQueueFileName = fileQueueFileName;
	}

	public String getJsonType() {
		return jsonType;
	}

	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public EnterpriseServiceInter getEnterprseService() {
		return enterprseService;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setEnterprseService(EnterpriseServiceInter enterprseService) {
		this.enterprseService = enterprseService;
	}

	public TsEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getList_enterprise() {
		return list_enterprise;
	}

	public void setList_enterprise(String listEnterprise) {
		list_enterprise = listEnterprise;
	}
	

	/**
	 * 分页查询所有的组织机构
	 * 
	 */
	public void findEnterpriseList()  {	
		if(enterprise==null){
			enterprise = new TsEnterprise();
		}
		if(!isAdmin()){
			enterprise.setEntId(getEnterprse().getEntId());
		}
		printJsonString(enterprseService.findEnterpriseList(enterprise, page, rows));
		
	}
	
	
	/**
	 * 分页查询所有待审核的企业
	 * 
	 */
	public void findWaitAuditCompanyList()  {	
		if(enterprise==null){
			enterprise = new TsEnterprise();
		}
		if(!isAdmin()){
			enterprise.setEntId(getEnterprse().getEntId());
		}
		printJsonString(enterprseService.findWaitAuditCompanyList(enterprise,getLoginUser(), page, rows));
		
	}
	
	/**
	 * 分页查询所有的企业
	 * 
	 */
	public void findCompanyList()  {	
		if(enterprise==null){
			enterprise = new TsEnterprise();
		}
		if(!isAdmin()){
			enterprise.setEntId(getEnterprse().getEntId());
		}
		printJsonString(enterprseService.findCompanyList(enterprise, page, rows));
		
	}
	
	/**
	 * 根据 entId 检测是否存在此组织
	 */
    public void findEnterpriseByAccount(){
		printJsonString(enterprseService.findAccountIsUnique(account));
    }
    
	/**
	 * 下拉 - 组织机构
	 * 
	 */
	public void getEnterpriseToSelect(){
		printJsonString(enterprseService.getEnterpriseToSelect());
	}

	/**
	 * 添加组织机构
	 * 
	 */
	public void addEnterprise()  {
		printJsonString(enterprseService.addEnterprise(enterprise,getLoginUserId()));
	}

	/**
	 * 添加组织机构-开通管理账号
	 * 
	 */
	public void addEntAndUser()  {
		printJsonString(enterprseService.addEntAndUser(enterprise,getLoginUserId()));
	}
	
	/**
	 * 添加主体
	 */
	public void addMainBody(){
		printJsonString(enterprseService.addMainBody(enterprise,getLoginUserId()));
	}
	
	public void updateMainBody(){
		printJsonString(enterprseService.updateMainBody(enterprise));
	}
	
	/**
	 * 添加组织机构-开通企业账号
	 * 
	 */
	public void addCompanyAccount()  {
		//System.out.println(enterprise.getAuditEnt());
		printJsonString(enterprseService.addCompanyAccount(enterprise,getLoginUser()));
	}
	
	
	/**
	 * 添加企业信息
	 * 
	 */
	public void addCompany()  {
		printJsonString(enterprseService.addCompany(enterprise,getLoginUserId()));
	}
	
	/**
	 * 企业注册
	 * 
	 */
	public void addCompany2()  {
		HttpServletRequest request = this.getRequest();
		printJsonString(enterprseService.addCompany2(enterprise,request));
	}
	
	/**
	 * 企业注册
	 * 
	 */
	public void register()  {
		HttpServletRequest request = this.getRequest();
		String msg = enterprseService.addCompany2(enterprise,request);
		printJsonString(msg);
	}
	
	/**
	 * 修改组织机构
	 */
	public void updateEnterprise(){		
		printJsonString(enterprseService.updateEnterprise(enterprise));
	}
	
	/**
	 * 企业操作-修改组织机构
	 * 
	 */
	public void updateEnterpriseInfo(){
		printJsonString(enterprseService.updateEnterprise(enterprise));
		getSession().setAttribute("enterprse", enterprise);
	}

	/**
	 * 删除一个组织机构
	 * 
	 */
	public void deleteEnterprise(){
		printJsonString(enterprseService.deleteEnterprise(list_enterprise));
	}

	/**
	 *修改机构状态 
	 */
	public void updateSts(){
		printJsonString(enterprseService.updateSts( entId, sts));
	}
	
	/**
	 * 获得树菜单
	 */
	public void getEntTree(){
		printJsonString(enterprseService.getEntTree(getLoginUser(), isAdmin()));
	}
	
	/**
	 * 门户获得树菜单
	 */
	public void getEntTree2(){
		printJsonString(enterprseService.getEntTree(getLoginUser(), true));
	}
	
	/**
	 * 获得指定树菜单
	 */
	public void getAuditEntTree(){
		printJsonString(enterprseService.getAuditEntTree(entId));
	}
	
	/**
	 * 验证组织帐号是否存在（唯一）
	 */
	public void findAccountIsUnique(){
		printJsonString(enterprseService.findAccountIsUnique(enterprise.getAccount()));
	}
	
	/**
	 * 验证组织帐号是否存在（唯一）
	 */
	public void findNameIsUnique(){
		printJsonString(enterprseService.findNameIsUnique(enterprise.getName()));
	}
	
	/**
	 * 获得区域树菜单
	 */
	public void getEntAreaTree(){
		printJsonString(enterprseService.getEntAreaTree(getLoginUser(), isAdmin()));
	}
	/**
	 * 门户获得区域树菜单
	 */
	public void getEntAreaTree2(){
		printJsonString(enterprseService.getEntAreaTree(getLoginUser(), true));
	}
	
	/**
	 * 企业注册，区域级联下拉
	 */
	public void getAreaTree(){
		printJsonString(enterprseService.getAreaTree(entId));
	}
	
	/**
	 * 获取企业列表
	 */
	public void getEntListJson(){
		printJsonString(enterprseService.getEntListJson(jsonType));
	}
	/**
	 * 区域分页查询
	 */
	public void findAreaList(){		
		printJsonString(enterprseService.findAreaList(enterprise, page, rows));		
	}
	

	/**
	 * 获取机构级别
	 * @param entId
	 */
	public void getEntLevel() {
		printJsonString(String.valueOf(enterprseService.getEntLevel(entId)));		

	}
	
	
	/**
	 * 门户获得指定上级审核机构树
	 */
	public void getAuditTree(){
		printJsonString(enterprseService.getAuditTree(entId));
	}
	
	/**
	 * 上传附件
	 */
	public void uploadFile() {			
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "company");		
		
		// 获取扩展名
		String extName = "";// 扩展名			
		if (fileQueueFileName.lastIndexOf(".") >= 0) {
			extName = fileQueueFileName.substring(fileQueueFileName.lastIndexOf("."));
		}
		
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		
		fileQueue.renameTo(new File(fileDir,newFileName));
						
		this.printJsonString(newFileName);
	}
	
	public void uploadFileForBase64(){
        String success = "fail";
        
        if(fileQueueFileName==null){
			this.printJsonString(success);
			return;
		}
        // 只允许jpg/png
        String header ="data:image/jpeg;base64";    
        String header2 ="data:image/png;base64";     
        String postfix = ".jpg";       
		if(fileQueueFileName.startsWith(header)){
			postfix = ".jpg";
		}else if(fileQueueFileName.startsWith(header2)){
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
        		fileQueueFileName=fileQueueFileName.split(",")[1];
	            //image = image.substring(header.length());
	            // 写入磁盘
	            BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(fileQueueFileName);        //将字符串格式的image转为二进制流（biye[])的decodedBytes
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
		//获取资质附件目录
		File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "company");		
		File file = new File(fileDir,fileQueueFileName);		
		if(file.exists()){
			this.printJsonString(String.valueOf(file.delete()));
		}		
	}
	
	
	/**
	 * 企业统计
	 */
	public void findCompanyChart(){	
		printJsonString(enterprseService.getEntAreaList(enterprise));
	}
	
	public void findCompanyAreaChart(){	
		Object areaObj = this.getSession().getAttribute("areaId");
		if(!this.isAdmin() && areaObj!=null){
			enterprise.setAreaId((Integer)areaObj);
		}
		printJsonString(enterprseService.getEntAreaList(enterprise));
	}
	
	public void findCompanyAreaTable(){	
		Object obj = this.getSession().getAttribute("areaId");
		if(!this.isAdmin() && obj!=null){
			enterprise.setAreaId((Integer)obj);
			obj = this.getSession().getAttribute("entStyle_QT");
			if(obj!=null){
				EntStyle es = (EntStyle) obj;
				enterprise.setBodyEntId(es.getEntId());
			}
			printJsonString(enterprseService.getEntAreaListTableNew(enterprise));
		}
	}
	
	public String findCompanyTable2(){	
		return (enterprseService.getEntAreaListTable());
	}
	public String getEntAreaListTableTotal(){	
		return (enterprseService.getEntAreaListTableTotal());
	}
	
	/**
	 * 企业统计
	 */
	public void findCompanyTable(){	
		printJsonString(enterprseService.getEntAreaListTable(enterprise));
	}
	/**
	 * 获取当前登陆用户的企业信息
	 */
	public void getLoginEntInfo(){
		printJsonString(enterprseService.getLoginEntInfo(this.getLoginUser().getEntId()));
	}
	/**
	 * 获取当前登陆用户的二维码信息
	 */
	public void getLoginCompanyDimenno(){
		if(entId!=null&&!"".equals(entId)){
			jsonMsg=enterprseService.getLoginCompanyDimenno(Integer.valueOf(entId),this.getRequest(),false);
		}else{
			jsonMsg=enterprseService.getLoginCompanyDimenno(this.getLoginUser().getEntId(),this.getRequest(),false);
		}
		printJsonString(jsonMsg);
	}
	/**
	 * 企业提交审核
	 */
	public void submitAud(){
		printJsonString(enterprseService.updateEntRsts(this.getLoginUser().getEntId(),"1"));
		this.getEnterprise().setCompanyRsts("1");
		//更新机构状态
		super.getEnterprse().setCompanyRsts("1");
	}
	/**
	 * 根据entCode获取企业信息 ,企业标识（flag）必须为3
	 */
	public void getCompanyInfoByEntCode(){
		printJsonString(enterprseService.getCompanyInfoByEntCode(entCode));
	}
	
	/**
	 * 生成企业信息PDF文件
	 */
	public void findCompanyInfo(){
		printJsonString(enterprseService.findCompanyInfo(entId,getRequest()));
	}
	
	/**
	 * 企业注册；根据区域查找管理机构ID；
	 * @param entId
	 * @return
	 */
	public void getEntManagerId(){
		printJsonString(enterprseService.getEntManagerId(entId));
	}
	/**
	 * 获取有档案记录的企业
	 */
	public void getEntHasRecord(){
		printJsonString(enterprseService.getEntHasRecord(enterprise,page,rows));
	}
	
	/**
	 * 获取监管单位
	 */
	public void findSuperviseEnt(){
		Object stobj = getSession().getAttribute("entStyle_QT");
		if(stobj!=null){
			EntStyle entStyle = (EntStyle)stobj;
			int entid = entStyle.getEntId();
			printJsonString(enterprseService.findSuperviseEnt(entid));
		}
	}
	/**
	 * 修改监管单位
	 */
	public void updateSuperviseEnt(){
		printJsonString(enterprseService.updateSuperviseEnt(enterprise));
	}
}
