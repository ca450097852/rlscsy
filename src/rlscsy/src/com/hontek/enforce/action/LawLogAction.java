package com.hontek.enforce.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import com.hontek.comm.action.BaseAction;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawLog;
import com.hontek.enforce.pojo.LawUser;
import com.hontek.enforce.service.inter.LawLogServiceInter;
import com.hontek.enforce.service.inter.LawUserServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法人员action
 * 
 * @author cjn
 * 
 */
public class LawLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LawLog lawLog;
	private LawUser lawUser;
	private LawLogServiceInter lawLogServiceInter;
	private LawUserServiceInter lawUserServiceInter;
	private TsEnterprise enterprise = new TsEnterprise();
	private String ids;
	private String fileName;	
	private File uploadify;
	private String uploadifyFileName;
	/**
	 * 方法
	 */
	
	File fileDir = DirectoryUtil.getDirectoryByName(getRequest(), "LawLog");
	/**
	 *  查询方法
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void findList() {
		Pager<LawUser> pagers = new Pager<LawUser>();
		Pager<LawLog> pager = lawLogServiceInter.findPagerList(lawLog,
				enterprise, page, rows, sort, order);
		this.createEasyUiJson(pager);
		ArrayList list = new ArrayList();
		for (LawLog lawLog : pager.getData()) {
			enterprise.setCrtUserId(lawLog.getUserId().toString());
			pagers = lawUserServiceInter.findPagerList(lawUser, enterprise,
					page, rows, sort, order);
			for (LawUser lawUser : pagers.getData()) {
				list.add(lawUser.getUserName());
			}
		}
		String jsonstr = JSONArray.fromObject(pager.getData()).toString();
		String s = "";
		for (int i = 0, j = 0; i < jsonstr.length(); i++) {
			if (list.size() > j) {
				char c = jsonstr.charAt(i);
				if (c == '}') {
					s = s + ",\"userName\":\"" + list.get(j) + "\"}";
					j++;
				} else {
					s += c;
				}
			}
		}
		if (pagers.getTotal() == 0) {
			jsonMsg = "{\"total\":" + pagers.getTotal() + ",\"rows\":[" + s
					+ "]}";
		} else {
			jsonMsg = "{\"total\":" + pagers.getTotal() + ",\"rows\":" + s
					+ "]}";
		}
		printJsonString(jsonMsg);
	}

	/**
	 * 添加
	 */
	public void AddLawLog() {
		Date d = new Date();  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateNowStr = sdf.format(d); 
	    lawLog.setCreateTime(dateNowStr);
		String msg = lawLogServiceInter.addLawLog(lawLog,fileDir);
		printJsonString(msg);
	}

	/**
	 * 修改
	 */
	public void updateLawLog() {
		jsonMsg = lawLogServiceInter.updateLawLog(lawLog,uploadifyFileName,fileDir);
		printJsonString(jsonMsg);
	}

	/**
	 *  删除
	 */
	public void delLawLog() {
		jsonMsg = lawLogServiceInter.deleteLawLog(ids);
		lawLogServiceInter.delApp(uploadifyFileName, fileDir);
		printJsonString(jsonMsg);
	}
	/**
	 * 附件上传
	 */
	public void logApp(){
		String newFileName =lawLogServiceInter.logApp(uploadifyFileName, uploadify, fileDir);
		this.printJsonString(newFileName);
		
	}
	/**
	 * 附件删除
	 */
	public void delLogApp(){
		  File Array[] = fileDir.listFiles();
	        for (File f : Array) {
	                if (f.getName().equals(uploadifyFileName)) {
	                    f.delete();
	                    String msg="删除文件成功";
	                    this.printJsonString(msg);
	                }
	            
	}
}
	/**
	 * 构造函数
	 * 
	 * @return
	 */
	public LawLog getLawLog() {
		return lawLog;
	}

	public void setLawLog(LawLog lawLog) {
		this.lawLog = lawLog;
	}

	public LawLogServiceInter getLawLogServiceInter() {
		return lawLogServiceInter;
	}

	public void setLawLogServiceInter(LawLogServiceInter lawLogServiceInter) {
		this.lawLogServiceInter = lawLogServiceInter;
	}

	public TsEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TsEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public LawUserServiceInter getLawUserServiceInter() {
		return lawUserServiceInter;
	}

	public void setLawUserServiceInter(LawUserServiceInter lawUserServiceInter) {
		this.lawUserServiceInter = lawUserServiceInter;
	}

	public LawUser getLawUser() {
		return lawUser;
	}

	public void setLawUser(LawUser lawUser) {
		this.lawUser = lawUser;
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

}
