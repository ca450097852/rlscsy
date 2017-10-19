package com.hontek.enforce.dao;
import java.util.Calendar;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawUser;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法人员表
 * @date 2016/6/30
 * @author cjn
 *
 */
public class LawUserDao extends BaseDao<LawUser>{
	
	/**
	 * 分页查询
	 * @param lawUser
	 * @param enterpriseint
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	public Pager<LawUser> findPager(LawUser lawUser, TsEnterprise enterpriseint,int page, int rows,
			String sort, String order) {
		String condition="";
		if(enterpriseint.getCrtUserId() !=null &&!"".equals(enterpriseint.getCrtUserId())){
			condition+=" and userId = '"+enterpriseint.getCrtUserId()+"'";
		}
		if(enterpriseint.getName()!=null && !"".equals(enterpriseint.getName())){
			condition+=" and userName like'%"+enterpriseint.getName()+"%'";
		}
		if((enterpriseint.getStartDate()!=null && !"".equals(enterpriseint.getStartDate())) ||(enterpriseint.getEndDate()!=null&&!"".equals(enterpriseint.getEndDate()))){
			if(enterpriseint.getStartDate()==null){
				enterpriseint.setStartDate("0");
			}
			condition+=" and createTime between '"+enterpriseint.getStartDate()+"' and '"+enterpriseint.getEndDate()+"'";
		}
		Class<LawUser> lawClass = LawUser.class;
		Pager<LawUser> pager=this.findPager(lawClass, condition, page, rows);
		
		return pager;
	}

	/**
	 * 添加
	 * @param lawUser
	 */
	public void addLawUser(LawUser lawUser){
		String sql = "select count(*) from ts_law_user  where 1=1";
		Integer count = this.countBySql(sql);
		Calendar now = Calendar.getInstance(); 
		String no=now.get(Calendar.YEAR)+"0"+count;
		lawUser.setUserNo(no);
		lawUser.setUserId(count.longValue()+1);
		this.save(lawUser);
		
	}
	

	

}
