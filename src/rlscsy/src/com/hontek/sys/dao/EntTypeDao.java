package com.hontek.sys.dao;

import java.util.ArrayList;
import java.util.List;
import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;
import com.hontek.sys.pojo.TsEntType;

public class EntTypeDao extends BaseDao<TsEntType> {

	/**
	 * 根据对象分页查询所有的组织机构类型 - 集合
	 * @param entType
	 * @param page
	 * @param rows
	 * @return List
	 */
	public Pager<TsEntType> findEntTypeList(TsEntType entType, int page, int rows) throws AppException {
		List<TsEntType> list = new ArrayList<TsEntType>();
		String hql = "from TsEntType where 1=1 ";
		if(entType!=null){
			if(entType.getTypeName() !=null && !"".equals(entType.getTypeName())){
				 hql+=" and typeName like '%"+entType.getTypeName()+"%'";
			}
			if(entType.getRemarks() !=null && !"".equals(entType.getRemarks())){
				 hql+=" and remarks like '%"+entType.getRemarks()+"%'";				
			}
		}
		
		list = this.find(hql, page, rows);		
		Number count = this.count("select count(*) "+hql);
		Pager<TsEntType> pager = new Pager<TsEntType> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue () );
		return pager;
	}
	
	/**
	 * 检查机构类型是否已经存在
	 * @param typeName
	 * @return
	 */
	public boolean checkEntTypeIsExist(String typeName){
		String sql ="select count(*) from TS_ENT_TYPE where TYPE_NAME='"+typeName+"'";
		return countBySql(sql).intValue()>0;
	}
	
    /**
     * 加载组织机构类别 -- 下拉使用
     * @return
     */
	public List<TsEntType> getEntTypeToSelect() throws AppException {
		List<TsEntType> list = new ArrayList<TsEntType>();
		String hql="from TsEntType order by remarks";
		list  = this.find(hql);
		return list;
	}
}
