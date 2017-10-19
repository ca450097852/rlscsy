package com.hontek.enforce.service.impl;


import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.dao.LawUserDao;
import com.hontek.enforce.pojo.LawUser;
import com.hontek.enforce.service.inter.LawUserServiceInter;
import com.hontek.sys.pojo.TsEnterprise;
import org.apache.log4j.Logger;

/**
 * 执法人员ServiceImpl
 * @author Administrator
 *
 */
public class LawUserServiceImpl extends BaseServiceImpl implements LawUserServiceInter{
	private LawUserDao lawUserDao;
	Logger logger = Logger.getLogger(LawUserServiceImpl.class);
	


	

	public LawUserDao getLawUserDao() {
		return lawUserDao;
	}


	public void setLawUserDao(LawUserDao lawUserDao) {
		this.lawUserDao = lawUserDao;
	}

	/**
	 * 添加
	 */
	public String addLawUser(LawUser lawUser) {
		String msg="添加成功";
		try {
			
			lawUserDao.addLawUser(lawUser);
		} catch (Exception e) {
			msg="添加失败";
		}
		return msg;
   }
	
		


	/**
	 * 修改
	 */
	public String updateLawUser(LawUser lawUser) {
		String msg="修改成功";
		try {
			lawUserDao.update(lawUser);
		} catch (Exception e) {
			msg="修改失败";
		}
		return msg;
	}

	/**
	 * 删除
	 */
	public String deleteLawUser(String ids) {
		String msg = "删除成功!";
		try {
			if(ids!=null&&!"".equals(ids)){
				String [] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					LawUser lawUser = lawUserDao.get(LawUser.class, Long.valueOf(idArray[i]));
					if(lawUser!=null){
						lawUserDao.delete(lawUser);
					}
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			msg = "删除出现异常!"+e.getMessage();
			logger.error(msg);
		}
		return msg;
		
	}
	/**
	 * 分页查询
	 */
	public Pager<LawUser> findPagerList(LawUser lawUser,TsEnterprise enterprise, int page, int rows,
			String sort, String order) {
		Pager<LawUser>  pager=null;
		try {
			 pager = lawUserDao.findPager(lawUser,enterprise, page, rows, sort, order);
		} catch (AppException e) {
			e.printStackTrace();
		}		
		return pager;
		
	}
}



