package com.hontek.enforce.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DateUtil;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.dao.LawLogDao;
import com.hontek.enforce.pojo.LawLog;
import com.hontek.enforce.service.inter.LawLogServiceInter;
import com.hontek.sys.pojo.TsEnterprise;

import org.apache.log4j.Logger;

/**
 * 执法日志ServiceImpl
 * 
 * @author Administrator
 * 
 */
public class LawLogServiceImpl extends BaseServiceImpl implements
		LawLogServiceInter {
	private LawLogDao lawLogDao;
	Logger logger = Logger.getLogger(LawLogServiceImpl.class);

	public LawLogDao getLawLogDao() {
		return lawLogDao;
	}

	public void setLawLogDao(LawLogDao lawLogDao) {
		this.lawLogDao = lawLogDao;
	}

	/**
	 * 添加
	 */
	public String addLawLog(LawLog lawLog,File fileDir) {
		String msg = "添加成功";
		try {
			lawLogDao.addLawLog(lawLog,fileDir);
			
		} catch (Exception e) {
			msg = "添加失败";
		}
		return msg;
	}

	/**
	 *  修改
	 */
	public String updateLawLog(LawLog LawLog, String uploadifyFileName,
			File fileDir) {
		String msg = "修改成功";
		try {
			lawLogDao.update(LawLog);
			File Array[] = fileDir.listFiles();
			for (File f : Array) {
				if(LawLog.getLogApp().equals(uploadifyFileName)){
					
				}else{
				if (f.getName().equals(uploadifyFileName)) {
					f.delete();
				} 
			}
		}
		} catch (Exception e) {
			msg = "修改失败";
		}
		return msg;
	}

	/**
	 *  删除
	 */
	public String deleteLawLog(String ids) {
		return lawLogDao.updLawLog(ids);
	}
	/**
	 * 附件删除
	 */
	public String  delApp(String uploadifyFileName,File fileDir){
		String msg="";
		try {
			String[] nameArray = uploadifyFileName.split(",");
			for (int i = 0; i < nameArray.length; i++) {
			uploadifyFileName=nameArray[i];
			File Array[] = fileDir.listFiles();
			for (File f : Array) {
				if (f.getName().equals(uploadifyFileName)) {
					f.delete();
				}

			}
			msg="删除成功";
			}
			
		} catch (Exception e) {
			 msg="删除失败";
		}
	return msg;
	}
	/**
	 * 分页查询
	 */
	public Pager<LawLog> findPagerList(LawLog lawLog, TsEnterprise enterprise,
			int page, int rows, String sort, String order) {
		Pager<LawLog> pager = new Pager<LawLog>();
		try {
			pager = lawLogDao.findPager(lawLog, enterprise, page, rows, sort,
					order);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return pager;

	}

	/**
	 * 附件添加
	 */
	public String logApp(String uploadifyFileName, File uploadify, File fileDir) {
		/**
		 *  获取扩展名
		 */
		String extName = "";
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName
					.lastIndexOf("."));
		}
		String newFileName = DateUtil.formatYYYMMDDHHMMSSAnd5Random() + extName;
		File newFile = new File(fileDir, newFileName);
		uploadify.renameTo(newFile);
		return newFileName;
	}

}
