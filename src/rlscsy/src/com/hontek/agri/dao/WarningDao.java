package com.hontek.agri.dao;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hontek.comm.dao.BaseDao;
import com.hontek.agri.pojo.TbWarning;
/**
 * <p>Title: 预警信息记录</p>
 * <p>Description: 预警信息记录DAO类</p>
 * @author qql
 */
public class WarningDao extends BaseDao<TbWarning> {

	public JSONArray findWaringEntList(TbWarning warning, int page, int rows, String sort, String order){
		String sql = "SELECT entId,entName,count(entId) FROM tb_warning WHERE state=1 GROUP BY entId,entName";		
		List<Object[]> list = findBySql(sql,page,rows);
		System.out.println(list.size());
		JSONArray jsonArray = new JSONArray(); 
		if(list!=null&&!list.isEmpty()){
			for (Object[] objects : list) {
				JSONObject object = new JSONObject();
				object.put("entId", objects[0]);
				object.put("entName", objects[1]);
				object.put("count", objects[2]);
				jsonArray.add(object);
			}
		}
		return jsonArray;
	}
	
}
