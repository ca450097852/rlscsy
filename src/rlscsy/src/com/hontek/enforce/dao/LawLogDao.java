package com.hontek.enforce.dao;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.DirectoryUtil;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawLog;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法人员表
 * @date 2016/6/30
 * @author cjn
 *
 */
public class LawLogDao extends BaseDao<LawLog>{
	
	/**
	 * 分页查询
	 * @param lawLog
	 * @param enterpriseint
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	public Pager<LawLog> findPager(LawLog lawLog, TsEnterprise enterpriseint,int page, int rows,
			String sort, String order) {
		String hql = "from LawLog where 1=1";
		
		if(enterpriseint.getAccount() !=null &&!"".equals(enterpriseint.getAccount())){
			hql+=" and comId = '"+enterpriseint.getAccount()+"'";
		}
		
		List<LawLog> list=this.find(hql);
		String sql = "select count(*) from ts_law_user  where 1=1";
		Integer count = this.countBySql(sql);
		Pager<LawLog>  pager =new Pager<LawLog>();
        pager.setData ( list );
        pager.setTotal(count.intValue());
		return pager;
	}

	/**
	 * 添加
	 * @param lawLog
	 * @param fileDir
	 */
	public void addLawLog(LawLog lawLog,File fileDir ){
		String sql = "select count(*) from ts_lawlog  where 1=1";
		Integer count = this.countBySql(sql);
		Calendar now = Calendar.getInstance(); 
		lawLog.setLogId(count.longValue()+1);
		this.save(lawLog);
		
		
		File Array[] = fileDir.listFiles();
		
	    for (File f : Array) {
	    	String hql="from LawLog where logApp='"+f.getName()+"'";
	    	LawLog list=this.get(hql);
	    	if(list==null){
	    		f.delete();
	    	}
		}	
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public String updLawLog(String ids){
		String msg="删除成功";
		try {
			if (ids != null && !"".equals(ids)) {
				String[] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					String hql = "from LawLog where userId="+Long.valueOf(idArray[i]);
					LawLog lawLog = this.get(hql);
					if (lawLog != null) {
						this.delete(lawLog);	
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg="删除失败";
		}
		return msg;
	}

	

}
