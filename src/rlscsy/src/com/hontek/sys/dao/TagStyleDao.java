package com.hontek.sys.dao;

import java.util.List;

import com.hontek.comm.dao.BaseDao;
import com.hontek.sys.pojo.TagStyle;

/**
 *二维码 风格
 *
 */
public class TagStyleDao extends BaseDao<TagStyle>{

	
	public TagStyle getTagStyleInfo(int entId) {
		String hql = "from TagStyle where entId="+entId;
		List<TagStyle> tagStyles = find(hql);
		if(tagStyles!=null&&!tagStyles.isEmpty()){
			return tagStyles.get(0);
		}
		return null;
	}

}
