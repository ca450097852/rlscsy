package com.hontek.company.service.impl;

import java.util.List;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.company.dao.LevelDao;
import com.hontek.company.pojo.Level;
import com.hontek.company.service.inter.LevelServiceInter;

/**
 * <p>Title: 级别表</p>
 * <p>Description: 级别service 类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author ZH
 * @version 1.0
 */
public class LevelServiceImpl extends BaseServiceImpl implements LevelServiceInter {
	
	private LevelDao levelDao;

	public void setLevelDao(LevelDao levelDao) {
		this.levelDao = levelDao;
	}
	
	public Pager<Level> findLevel(Level level, int page, int rows, String sort,
			String order) {
		StringBuffer condition = new StringBuffer();
		if(level!=null){
			if(level.getLevelTitle()!=null){
				condition.append(" and levelTitle like '%"+level.getLevelTitle()+"%'");
			}
			if(level.getTypeClass()!=0){
				condition.append(" and typeClass="+level.getTypeClass());
			}
		}
		
		condition.append(sortCondtion(sort, order));
		
		List<Level> list = levelDao.findList(Level.class, condition.toString());
		int ct = levelDao.getcount(condition.toString());
		
		Pager<Level> pager = new Pager<Level>();
		pager.setData(list);
		pager.setTotal(ct);
		return pager;
	}

	public String addLevel(Level level) {
		String msg = "添加成功";
		try {
			if(level!=null){
				level.setLevelId(levelDao.getTableSequence("TB_LEVEL"));
				levelDao.save(level);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
		}
		return msg;
	}

	public String deleteLevels(String ids) {
		String msg = "删除成功";
		try {
			if(ids!=null){
				levelDao.deleteLevels(ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
		}
		return msg;
	}

	

	public String updateLevel(Level level) {
		String msg = "修改成功";
		try {
			if(level!=null){
				levelDao.update(level);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "修改失败";
		}
		return msg;
	}

	public List<Level> getLevelListByType(String ids) {
		if(ids!=null&&!"".equals(ids)){
			List<Level> list = levelDao.findList(Level.class, " and typeClass="+ids);
			return list;
		}
		return null;
	}
	
	
	
	

}
