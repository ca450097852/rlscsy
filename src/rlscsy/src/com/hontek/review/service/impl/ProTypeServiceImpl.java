package com.hontek.review.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.hontek.review.dao.ProTypeDao;
import com.hontek.review.pojo.TbProType;
import com.hontek.review.service.inter.ProTypeServiceInter;
import com.hontek.review.pojo.TreeVo;
import com.hontek.comm.base.BaseServiceImpl;
import com.hontek.comm.util.Pager;
/**
 * <p>Title: 产品分类</p>
 * <p>Description: 产品分类SERVICE实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProTypeServiceImpl extends BaseServiceImpl implements ProTypeServiceInter {
	private ProTypeDao proTypeDao;

	public ProTypeDao getProTypeDao() {
		return proTypeDao;
	}

	public void setProTypeDao(ProTypeDao proTypeDao) {
		this.proTypeDao = proTypeDao;
	}

	public Pager findProTypeList(Map mapCondition, int page, int rows) {
		return proTypeDao.findProTypeList(mapCondition,page,rows);
	}

	public void addProType(TbProType proType) {
		
		String sql = "SELECT max(type_no) FROM tb_pro_type WHERE upcate_id="+proType.getUpcateId();
		
		Object obj = proTypeDao.uniqueResult(sql);
		if(obj==null){
			String sql2 = "SELECT type_no FROM tb_pro_type WHERE type_id="+proType.getUpcateId();
			Object obj2 = proTypeDao.uniqueResult(sql2);
			String typeNo = obj2.toString()+"01";
			proType.setTypeNo(typeNo);		
		}else{
			
			String typeNo = obj.toString();
			
			String typeNoSeq = typeNo.substring(typeNo.length()-2);
			
			String seq = String.valueOf(Integer.valueOf(typeNoSeq)+1);
			
			if( seq.length()==1){
				seq="0"+seq;	
			}			
			proType.setTypeNo(typeNo.substring(0, typeNo.length()-2)+ seq);
		}
		
		TbProType PproType = proTypeDao.get(TbProType.class, proType.getUpcateId());
		if(PproType!=null){
			proType.setTypeClass(PproType.getTypeClass());
		}
		
		proTypeDao.addProType(proType);
	}
	
	
	public static void main(String[] args) {
		String TypeNo =  "ABCDE";

		String one = TypeNo.substring(0, TypeNo.length()-2);
		
		String two = TypeNo.substring(TypeNo.length()-2);
		
		System.out.println(one);
		System.out.println(two);
	}
	
	
	public void addProType2(TbProType proType)  {
		
		try {
			if(proType.getTypeName().indexOf("@")>0){
				String[] typeNames = proType.getTypeName().split("@");			
				
				String TypeNo =  proType.getTypeNo();

				String one = TypeNo.substring(0, TypeNo.length()-2);
				
				String two = TypeNo.substring(TypeNo.length()-2);
				
				int end = Integer.valueOf(two);
				
				for (String typeName : typeNames) {						
					TbProType proType2 = new TbProType();
					
					end += 1;
					
					String ends = String.valueOf(end);
					if(ends.length()<2){
						ends = "0"+ends;
					}
					
					proType2.setTypeNo(one+ends);
					proType2.setTypeName(new String(typeName.getBytes("iso-8859-1"), "utf-8"));
					proType2.setUpcateId(proType.getUpcateId());
					proType2.setTypeClass(proType.getTypeClass());				
					int id = proTypeDao.getTableSequence("TB_PRO_TYPE");
					proType2.setTypeId(id);
					proTypeDao.addProType(proType2);
				}
			}else{
				int id = proTypeDao.getTableSequence("TB_PRO_TYPE");
				proType.setTypeId(id);
				proTypeDao.addProType(proType);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List findProTypeList(String typeName){
		String hql = "from TbProType where typeName='"+typeName+"'";
		return proTypeDao.find(hql);
	}
	
	
	
	
	public void updateProType(TbProType proType) {
		proTypeDao.updateProType(proType);
	}

	public void updateProTypeState(String ids, String state) {
		proTypeDao.updateProTypeState(ids,state);
	}

	public List getProTypeList() {
		//return proTypeDao.getProTypeList();
		return null;
	}

	public String getProTypeTree(Long parentId) {
		List<TreeVo> treeList = proTypeDao.getProTypeTree(parentId);
		TreeVo firstVo = new TreeVo();
		firstVo.setId(0);
		firstVo.setText("父类");
		treeList.add(0, firstVo);
		String msg = JSONArray.fromObject(treeList).toString();
		return msg;
	}

	public String getProTypeTreeToPro(long parentId) {
		List<TreeVo> treeList = proTypeDao.getProTypeTree(parentId);
		String msg = JSONArray.fromObject(treeList).toString();
		return msg;
	}
	
	public String getProTypeTreeToProbyType(long parentId,String typeClass) {
		List<TreeVo> treeList = proTypeDao.getProTypeTree(parentId,typeClass);
		String msg = JSONArray.fromObject(treeList).toString();
		return msg;
	}
	
	/**
	 * 门户企业注册，选择产品分类
	 */
	public String getProTypeTreeForPortal(long parentId){
		List<TreeVo> treeList = proTypeDao.getProTypeTreeForPortal(parentId);
		String msg = JSONArray.fromObject(treeList).toString();
		return msg;
	}
	
	/**
	 * 获取企业产品分类tree
	 */
	public List<TreeVo> getEntTypeTree(int entId) {
		List<TreeVo> treeList = proTypeDao.getEntTypeTree(entId);
		return treeList;
	}

	public String getProTypeTreeByName(String typeName) {
		List<TreeVo> treeList = proTypeDao.getProTypeTreeByName(typeName);
		String msg = JSONArray.fromObject(treeList).toString();
		return msg;
	}

	public List<TbProType> getList() {
		
		List<TbProType> aList = new ArrayList<TbProType>();
		getListById(aList,null);
		
		return aList;
	}
	
	public void getListById(List<TbProType> list, TbProType tpt){
		int id = 0;
		if(tpt!=null){
			id = tpt.getTypeId();
		}
		String hql = "from TbProType where upcateId ="+id+" ORDER BY typeNo ";
		List<TbProType> tmp = proTypeDao.find(hql);
		
		if(tpt!=null && tmp!=null){
			tpt.setChct(tmp.size());
		}
		
		for(TbProType pt:tmp){
			list.add(pt);
			getListById(list,pt);
		}
		
	}

}
