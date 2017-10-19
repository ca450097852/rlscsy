package com.hontek.company.service.inter;

import java.util.List;

import com.hontek.comm.util.Pager;
import com.hontek.company.pojo.Level;

/**
 * <p>Title: 级别表</p>
 * <p>Description: 级别service 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public interface LevelServiceInter {

	Pager<Level> findLevel(Level level, int page, int rows, String sort,
			String order);

	String addLevel(Level level);

	String updateLevel(Level level);

	String deleteLevels(String ids);

	List<Level> getLevelListByType(String ids);

}
