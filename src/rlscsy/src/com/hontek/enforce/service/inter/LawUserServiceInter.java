package com.hontek.enforce.service.inter;
import com.hontek.comm.util.Pager;
import com.hontek.enforce.pojo.LawUser;
import com.hontek.sys.pojo.TsEnterprise;

/**
 * 执法人员ServiceInter
 * @author cjn
 *
 */
public interface LawUserServiceInter {

	String addLawUser(LawUser lawUser);

	String updateLawUser(LawUser lawUser);

	String deleteLawUser(String ids);
	
	Pager<LawUser> findPagerList(LawUser lawUser,TsEnterprise enterprise,int page,int rows,String sort,String order);
	
	
	 

}
