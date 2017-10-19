package com.hontek.sys.service.inter;

import com.hontek.sys.pojo.TsEntType;

/**
 * 组织机构类型 - 接口
 * @author w.x.w
 * @created 2013-09-05
 */
public interface EntTypeServiceInter {
	
	// 分页查询所有的组织机构类
	public String findEntTypeList(TsEntType entType, int page, int rows) ;

	// 添加
	public String addEntType(TsEntType entType);
	
	// 修改
	public String updateEntType(TsEntType entType);
	
    // 删除
	public String deleteEntType(String entTypeIds);
    
	// 加载组织机构类别-- 下拉
	public String getEntTypeToSelect(String param);
	

	public int getTableSequence();
	
	/**
	 * 检查机构类型名称是否已经存在
	 * @param typeName
	 * @return
	 */
	public String checkEntTypeIsExist(String typeName);
}
