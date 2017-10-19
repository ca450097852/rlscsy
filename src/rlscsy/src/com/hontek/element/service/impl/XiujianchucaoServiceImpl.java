package com.hontek.element.service.impl;

import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
import com.hontek.element.dao.XiujianchucaoDao;
import com.hontek.element.pojo.Xiujianchucao;
import com.hontek.element.service.inter.XiujianchucaoServiceInter;

public class XiujianchucaoServiceImpl extends BaseServiceImpl implements XiujianchucaoServiceInter{
	
	private XiujianchucaoDao xiujianchucaoDao;

	public void setXiujianchucaoDao(XiujianchucaoDao xiujianchucaoDao) {
		this.xiujianchucaoDao = xiujianchucaoDao;
	}

	public String addXiujianchucao(Xiujianchucao xiujianchucao) {
		String jsonMsg = "添加成功";
		try {
			if(xiujianchucao!=null){
				xiujianchucao.setGiid(xiujianchucaoDao.getTableSequence("tb_xiujianchucao".toUpperCase()));
				xiujianchucaoDao.save(xiujianchucao);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "添加失败";
		}
		return jsonMsg;
	}

	public String deleteXiujianchucao(String ids) {
		String jsonMsg = "删除成功";
		try {
			if(ids!=null&&!"".equals(ids)){
				String hql = "delete from Xiujianchucao where giid in ("+ids+")";
				xiujianchucaoDao.executeHql(hql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "删除失败";
		}
		return jsonMsg;
	}

	public String findList(Xiujianchucao xiujianchucao, int page, int rows,
			String sort, String order) {
		StringBuffer condition = new StringBuffer();
		if(xiujianchucao!=null){
			if(xiujianchucao.getDotype()!=0){
				condition.append(" and dotype="+xiujianchucao.getDotype());
			}
			if(xiujianchucao.getRecId()!=0){
				condition.append(" and recId="+xiujianchucao.getRecId());
			}
		}
		condition.append(sortCondtion(sort, order));
		Pager<Xiujianchucao> pager = xiujianchucaoDao.findPager(Xiujianchucao.class, condition.toString(), page, rows);
		return createEasyUiJson(pager);
	}

	public String updateXiujianchucao(Xiujianchucao xiujianchucao) {
		String jsonMsg = "修改成功";
		try {
			if(xiujianchucao!=null){
				xiujianchucaoDao.update(xiujianchucao);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMsg = "修改失败";
		}
		return jsonMsg;
	}
	
}
