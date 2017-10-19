package com.hontek.enforce.service.inter;
import java.io.File;

import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawLog;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法日志ServiceInter
 * @author cjn
 *
 */
public interface LawLogServiceInter {

	String addLawLog(LawLog lawLog,File fileDir);

	String updateLawLog(LawLog lawLog,String uploadifyFileName,File fileDir);

	String deleteLawLog(String ids);

	Pager<LawLog> findPagerList(LawLog lawLog,TsEnterprise enterprise,int page,int rows,String sort,String order);
	
	String logApp(String uploadifyFileName,File uploadify,File fileDir);
	
	String delApp(String uploadifyFileName,File fileDir);
}
