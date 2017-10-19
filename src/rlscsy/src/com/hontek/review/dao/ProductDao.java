package com.hontek.review.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.hontek.comm.dao.BaseDao;
import com.hontek.comm.util.Pager;
import com.hontek.review.pojo.TbProduct;
import com.hontek.review.pojo.TraceDataVo;
/**
 * <p>Title: 产品管理</p>
 * <p>Description: 产品DAO类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: **公司</p>
 * @author LZK
 * @version 1.0
 */
public class ProductDao extends BaseDao<TbProduct>{

	public Pager<TbProduct> findProductList(Map mapCondition, int page, int rows) {
		
		//String condition = TranMapCondition.TranMaptoString(mapCondition);
		
		String condition = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if("entIds".equals(key)){
						condition += " and entId in ("+obj+")";
					}else if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
		}
		
		condition += "order by proId desc";
		
		String hql = "from TbProduct " + condition;
		
		List<TbProduct> list = this.find(hql,page,rows);
		
		String hql_ct = "select count(*) from TbProduct "+condition;
		
		int ct = this.count(hql_ct).intValue();
		
		Pager<TbProduct> pager = new Pager<TbProduct>();
		
		pager.setData(list);
		
		pager.setTotal(ct);
		
		return pager;
	}
	
	
	public Pager<TbProduct> findProductListJoinEnt(Map mapCondition, int page, int rows) {
				
		String condition = "";
		String condition_ct = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if("orderby".equals(key)){
						
					}else if("entIds".equals(key)&&!"".equals(obj)){
						condition += " and e.parent_id in ("+obj+")";
					}else if("traceState".equals(key)&&!"".equals(obj)){
						//没有溯源数据
						if("1".equals(obj)){
							condition += " and p.pro_id not in (SELECT DISTINCT(pro_id) FROM tb_pro_batch)";
						}else if("2".equals(obj)){
							//有溯源数据
							condition += " and p.pro_id in (SELECT DISTINCT(pro_id) FROM tb_pro_batch)";
						}												
					}else if("q.dimenno".equals(key)&&!"".equals(obj)){
						condition += " and q.dimenno = '"+obj+"'";
					}else if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
			if(mapCondition.get("orderby")!=null){
				condition_ct = condition;
				condition += mapCondition.get("orderby");
			}
		}
		
		if(!condition.contains("order by")){
			condition += " order by p.update_time desc";
		}
		
		String sql = "select p.PRO_ID as proId,"+
		  "p.PRO_CODE as proCode,"+
		  "q.dimenno as dimenno,"+
		  "q.code_img as codeImg,"+
		  "q.URL as dimennoUrl,"+
		  "p.PRO_NAME as proName,"+         
		  "p.BARCODE as barcode,"+         
		  "p.SIZE_ATTR as sizeAttr,"+       
		  //"p.DIMENNO as dimenno,"+          
		  "p.PRO_DESC as proDesc,"+  
		  "p.source_tel as sourceTel,"+
		  "p.distributor_tel as distributorTel,"+
		  "p.UNIT as unit,"+           
		  "p.STATE as state,"+           
		  "p.SOURCE_ADDR as sourceAddr,"+     
		  "p.REMARK as remark,"+         
		  "p.UPDATE_TIME as updateTime,"+      
		  "p.USER_ID as userId,"+         
		  "p.MANUFACTURER as manufacturer,"+     
		  "p.DISTRIBUTOR as distributor,"+      
		  "p.DISTRIBUTOR_ADDR as distributorAddr," +
		  "p.SYS_CODE as sysCode,"+
		  "e.name as entName," +
		  "e.ENT_ID as entId," +
		  
		  "t.type_id as typeId," +
		  "t.type_name as typeName ," +
		  "t.type_class as typeClass ," +

		  "p.retain as retain,"+  
		  "p.storage_conditions as storageConditions,"+  
		  "p.authentication as authentication,"+  
		  "p.shelf_life as shelfLife "+  
		  
		  "from TB_PRODUCT p " +
		  "left join tb_pro_type t on(p.type_id=t.type_id) " +
		  "left join ts_enterprise e on(p.ent_id=e.ent_id) " +
		  "left join tb_pro_qrcode q on (q.pro_id=p.pro_id) " ;
		     
		
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+condition);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("dimenno", StandardBasicTypes.STRING);
		sqlQuery.addScalar("codeImg", StandardBasicTypes.STRING);
		sqlQuery.addScalar("dimennoUrl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("proName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("barcode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sizeAttr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sourceTel", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributorTel", StandardBasicTypes.STRING);
//		sqlQuery.addScalar("dimenno", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("proDesc", StandardBasicTypes.STRING);
		sqlQuery.addScalar("unit", StandardBasicTypes.STRING);
		sqlQuery.addScalar("state", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sourceAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("updateTime", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("manufacturer", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributor", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributorAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		
		sqlQuery.addScalar("typeId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("typeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("typeClass", StandardBasicTypes.STRING);

		sqlQuery.addScalar("retain", StandardBasicTypes.STRING);
		sqlQuery.addScalar("storageConditions", StandardBasicTypes.STRING);
		sqlQuery.addScalar("authentication", StandardBasicTypes.STRING);
		sqlQuery.addScalar("shelfLife", StandardBasicTypes.STRING);
		
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbProduct.class));
		
		List<TbProduct> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
				
		String hql_ct = "select count(*) from TB_PRODUCT p left join tb_pro_type t on(p.type_id=t.type_id) left join ts_enterprise e on(p.ent_id=e.ent_id) left join tb_pro_qrcode q on (q.pro_id=p.pro_id) "+condition_ct;
		
		//hql_ct = hql_ct.substring(0,hql_ct.lastIndexOf("order by"));
		
		int ct = this.countBySql(hql_ct).intValue();
		
		Pager<TbProduct> pager = new Pager<TbProduct>();
		
		pager.setData(list);
		
		pager.setTotal(ct);
		
		return pager;
	}
	
	/**
	 * 查询动物溯源数据
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pager<TraceDataVo> findAnimalProductList( int page, int rows){
		String sql = /*"SELECT p.pro_id as proId,p.pro_name as proName,e.name as companyName,b.batch_no as batchNo,b.audit_time as auditTime,b.dimenno as dimenno " +
					"FROM tb_product p LEFT JOIN ts_enterprise e ON (p.ent_id=e.ent_id) " +
					"LEFT JOIN tb_pro_batch b ON (p.pro_id=b.pro_id) " +
					"WHERE p.sys_code='A002001' AND b.batch_no is NOT null " +
					"AND b.batch_id in(SELECT MAX(batch_id) FROM tb_pro_batch GROUP BY pro_id) order by b.audit_time desc";*/
		
		"SELECT pro_id as proId,pro_name as proName,name as companyName,batch_no as batchNo,audit_time as auditTime,dimenno as dimenno from animal_trace WHERE sys_code='A002001' AND batch_id in(SELECT MAX(batch_id) FROM animal_trace GROUP BY pro_id) ORDER BY audit_time DESC";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("dimenno", StandardBasicTypes.STRING);
		sqlQuery.addScalar("proName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("companyName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("batchNo", StandardBasicTypes.STRING);
		sqlQuery.addScalar("auditTime", StandardBasicTypes.STRING);
						
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TraceDataVo.class));
		
		List<TraceDataVo> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		
		/*String sql_ct = "SELECT count(*) FROM tb_product p LEFT JOIN ts_enterprise e ON (p.ent_id=e.ent_id) " +
						"LEFT JOIN tb_pro_batch b ON (p.pro_id=b.pro_id) " +
						"WHERE p.sys_code='A002001' AND b.batch_no is NOT null " +
						"AND b.batch_id in(SELECT MAX(batch_id) FROM tb_pro_batch GROUP BY pro_id)";*/
		String sql_ct = "SELECT count(*) from animal_trace WHERE sys_code='A002001' AND batch_id in(SELECT MAX(batch_id) FROM animal_trace GROUP BY pro_id)";
		int ct = this.countBySql(sql_ct).intValue();

		Pager<TraceDataVo> pager = new Pager<TraceDataVo>();
		pager.setData(list);		
		pager.setTotal(ct);
		
		return pager;
	}
	

	public int findPro(String proName ,int entId){
		String hql ="select proId from TbProduct where proName='"+proName+"' and entId="+entId;
		 Object list = getObject(hql);
		if(list!=null){
			return Integer.valueOf(list.toString());
		}
		return 0;
	}
	
	public void addProduct(TbProduct TbProduct) {
		TbProduct.setProId(getTableSequence("TB_PRODUCT"));
		this.save(TbProduct);
	}

	public void updateProduct(TbProduct TbProduct) {
		this.update(TbProduct);
	}

	public void updateProductState(String ids, String state) {
		String hql = "update TbProduct set state='"+state+"' where proId in ("+ids+")";
		this.executeHql(hql);
	}
	// 二维码查询 - 手机接口
	public TbProduct getProductByDimenno(String code) {
		//String hql =" from TbProduct where dimenno='"+code+"'";
		String condition = "and p.DIMENNO='"+code+"'";
		return getProductByCondition(condition);	
	}

	public TbProduct getProductByProId(int proId) {
		//String hql =" from TbProduct where proId="+proId;
		//return super.get(hql);
		String condition = "and p.PRO_ID="+proId;
		return getProductByCondition(condition);	
	}
	
	public Integer getProductByCon(String condition) {
		String sql = "select p.PRO_ID as proId from TB_PRODUCT p " +condition;
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<Integer> list = sqlQuery.list();
		return list.get(0);
	}
	
	// 
	public TbProduct getProductByCondition(String condition) {
		String sql = "select p.PRO_ID as proId,"+          
		  "p.PRO_NAME as proName,"+         
		  "p.BARCODE as barcode,"+         
		  "p.SIZE_ATTR as sizeAttr,"+       
		  "p.PRO_DESC as proDesc,"+         
		  "p.UNIT as unit,"+           
		  "p.STATE as state,"+           
		  "p.SOURCE_ADDR as sourceAddr,"+     
		  "p.REMARK as remark,"+         
		  "p.UPDATE_TIME as updateTime,"+      
		  "p.USER_ID as userId,"+         
		  "p.MANUFACTURER as manufacturer,"+     
		  "p.DISTRIBUTOR as distributor,"+      
		  "p.DISTRIBUTOR_ADDR as distributorAddr," +		  
		  "e.name as entName," +
		  "e.ENT_ID as entId," +		  
		  "t.type_id as typeId," +
		  "t.type_class as typeClass ," +
		  "t.type_name as typeName " +	

		  "from TB_PRODUCT p " +
		  "left join tb_pro_type t on(p.type_id=t.type_id) " +
		  "left join ts_enterprise e on(p.ent_id=e.ent_id) " +
		  "left join tb_pro_qrcode q on (q.pro_id=p.pro_id) where 1=1 " ;		     		
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+condition);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("barcode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sizeAttr", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("proDesc", StandardBasicTypes.STRING);
		sqlQuery.addScalar("unit", StandardBasicTypes.STRING);
		sqlQuery.addScalar("state", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sourceAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("updateTime", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("manufacturer", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributor", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributorAddr", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		
		sqlQuery.addScalar("typeId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("typeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("typeClass", StandardBasicTypes.STRING);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbProduct.class));
		List<TbProduct> list = sqlQuery.list();
		
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public TbProduct getProductByProCode(String condition){
		condition = condition==null?"":condition;
		String hql = "from TbProduct where 1=1 "+condition;
		return this.get(hql);
	}

	public boolean findIsUniqueCode(String dimenno) {
		
		String sql = "select count(*) from tb_product where dimenno='"+dimenno+"'";
		
		int ct = this.countBySql(sql).intValue();
		
		if(ct==0){
			return false;
		}
		
		return true;
	}
	/**
	 * 删除产品
	 * @param ids
	 */
	public void deleteProduct(String ids) {
		String hql = "delete from TbProduct where proId in ("+ids+")";
		this.executeHql(hql);
	}
	/**
	 * 补全二维码
	 * @param entId
	 * @param proCode
	 * @return
	 */
	public int getDimentSeq(String proCode) {
		String sql = "select max(pro_code) from tb_product where pro_code like '"+proCode+"%'";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		Object obj = sqlQuery.uniqueResult();
		if(obj==null){
			return 1;
		}else{
			String tmp = String.valueOf(obj);
			tmp = tmp.substring(tmp.length()-3);
			System.out.println("tmp====="+tmp);
			int seq = Integer.parseInt(tmp)+1;
			return seq;
		}
	}
	
	
	/**
	 * 按区域统计产品数量
	 * @param condtions
	 * @return
	 */
	public List<Map<Object, Object>> getProductList(String condtions){
		String sql ="SELECT t1.name as entName,(SELECT COUNT(*)  FROM tb_product tc where tc.ent_id in( SELECT t2.ent_id FROM ts_enterprise t2 where t2.area_id = t1.ent_id )) as total FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=1 ";
		if(condtions!=null&&!"".equals(condtions)){
			sql ="SELECT t1.name as entName,(SELECT COUNT(*)  FROM tb_product tc where tc.ent_id in( SELECT t2.ent_id FROM ts_enterprise t2 where t2.area_id = t1.ent_id ) "+condtions+") as total FROM ts_enterprise t1 where t1.flag=1 and t1.parent_id=1 ";
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("total", StandardBasicTypes.INTEGER);
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		

		List<Object[]> list =  sqlQuery.list();		
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	}
	
	/**
	 * 按区域统计产品数量
	 * @param condtions
	 * @return
	 */
	public Object getProductListSize(String condtions){
		String sql ="SELECT COUNT(*)  FROM tb_product tc where 1=1";
		if(condtions!=null&&!"".equals(condtions)){
			sql +=condtions;
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);

		return sqlQuery.uniqueResult();
	}
	
	/**
	 * 按企业统计产品数量
	 * @param condtions
	 * @return
	 */
	public List<Map<Object, Object>> getEntProductList(String condtions){
		String sql ="SELECT ts.name as entName,COUNT(*) as total FROM tb_product tp ,ts_enterprise ts WHERE tp.ent_id = ts.ent_id and ts.parent_id in(SELECT ent_id from ts_enterprise t where t.name='惠州市' ) GROUP BY ts.name ORDER BY total desc";
		if(condtions!=null&&!"".equals(condtions)){
			sql ="SELECT ts.name as entName,COUNT(*) as total FROM tb_product tp ,ts_enterprise ts WHERE tp.ent_id = ts.ent_id and ts.parent_id in(SELECT ent_id from ts_enterprise t where t.name='"+condtions+"' ) GROUP BY ts.name ORDER BY total desc";
		}
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("total", StandardBasicTypes.INTEGER);
		List<Map<Object, Object>> listMaps = new ArrayList<Map<Object,Object>>();		

		List<Object[]> list =  sqlQuery.setFirstResult(0).setMaxResults(20).list();//sqlQuery.list();		
		for (Object[] objects : list) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("x", objects[0]);
			map.put("y", objects[1]);
			listMaps.add(map);
		}
		return listMaps;
	}
	/**
	 * 按企业统计产品数量
	 * @param condtions
	 * @return
	 */
	public Object getEntProductListSize(String condtions){
		String sql ="SELECT COUNT(*) FROM tb_product tc,ts_enterprise te where tc.ent_id = te.ent_id and te.parent_id in(SELECT ent_id from ts_enterprise t where t.name='"+condtions+"')";
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);

		return sqlQuery.uniqueResult();
	}
	
	/**
	 * 
	 * @param proId
	 * @return Object[]
	 */
	public Object[] findProductAndEnt(int proId){
		String sql ="SELECT t1.pro_name,t1.pro_code,t2.name FROM tb_product t1,ts_enterprise t2 WHERE t1.ent_id=t2.ent_id and t1.pro_id="+proId;
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<Object[]> list =  sqlQuery.list();//sqlQuery.list();	
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}


	public Pager<TbProduct> findProductListJoinEntForEnt(Map mapCondition,
			int page, int rows) {

		
		String condition = "";
		String condition_ct = "";
		if (mapCondition != null && !mapCondition.isEmpty()) {
			condition = " where 1=1 ";
			Set<String> keys = mapCondition.keySet();
			Iterator itor = keys.iterator();
			while (itor.hasNext()) {
				String key = (String) itor.next();
				Object obj = mapCondition.get(key);
				if (obj != null) {
					if("orderby".equals(key)){
						
					}else if("entIds".equals(key)&&!"".equals(obj)){
						condition += " and e.ent_id in ("+obj+")";
					}else if("traceState".equals(key)&&!"".equals(obj)){
						//没有溯源数据
						if("1".equals(obj)){
							condition += " and p.pro_id not in (SELECT DISTINCT(pro_id) FROM tb_pro_batch)";
						}else if("2".equals(obj)){
							//有溯源数据
							condition += " and p.pro_id in (SELECT DISTINCT(pro_id) FROM tb_pro_batch)";
						}												
					}else if("q.dimenno".equals(key)&&!"".equals(obj)){
						condition += " and q.dimenno = '"+obj+"'";
					}else if (obj.getClass() == String.class) {// 条件查询条件为字符串的情况
						condition += " and " + key + " like '%" + (String) obj+ "%'";
					} else {// 数字
						condition += " and " + key + "=" + obj;
					}
				}
			}
			if(mapCondition.get("orderby")!=null){
				condition_ct = condition;
				condition += mapCondition.get("orderby");
			}
		}
		
		if(!condition.contains("order by")){
			condition += " order by p.update_time desc";
		}
		
		String sql = "select p.PRO_ID as proId,"+
		  "p.PRO_CODE as proCode,"+
		  "q.dimenno as dimenno,"+
		  "q.code_img as codeImg,"+
		  "q.URL as dimennoUrl,"+
		  "p.PRO_NAME as proName,"+         
		  "p.BARCODE as barcode,"+         
		  "p.SIZE_ATTR as sizeAttr,"+       
		  //"p.DIMENNO as dimenno,"+          
		  "p.PRO_DESC as proDesc,"+  
		  "p.source_tel as sourceTel,"+
		  "p.distributor_tel as distributorTel,"+
		  "p.UNIT as unit,"+           
		  "p.STATE as state,"+           
		  "p.SOURCE_ADDR as sourceAddr,"+     
		  "p.REMARK as remark,"+         
		  "p.UPDATE_TIME as updateTime,"+      
		  "p.USER_ID as userId,"+         
		  "p.MANUFACTURER as manufacturer,"+     
		  "p.DISTRIBUTOR as distributor,"+      
		  "p.DISTRIBUTOR_ADDR as distributorAddr," +
		  "p.SYS_CODE as sysCode,"+
		  "e.name as entName," +
		  "e.ENT_ID as entId," +
		  
		  "t.type_id as typeId," +
		  "t.type_name as typeName ," +
		  "t.type_class as typeClass ," +

		  "p.retain as retain,"+  
		  "p.storage_conditions as storageConditions,"+  
		  "p.authentication as authentication,"+  
		  "p.shelf_life as shelfLife "+  
		  
		  "from TB_PRODUCT p " +
		  "left join tb_pro_type t on(p.type_id=t.type_id) " +
		  "left join ts_enterprise e on(p.ent_id=e.ent_id) " +
		  "left join tb_pro_qrcode q on (q.pro_id=p.pro_id) " ;
		     
		
		
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql+condition);
		sqlQuery.addScalar("proId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("proCode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("dimenno", StandardBasicTypes.STRING);
		sqlQuery.addScalar("codeImg", StandardBasicTypes.STRING);
		sqlQuery.addScalar("dimennoUrl", StandardBasicTypes.STRING);
		sqlQuery.addScalar("proName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("barcode", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sizeAttr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sourceTel", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributorTel", StandardBasicTypes.STRING);
//		sqlQuery.addScalar("dimenno", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("proDesc", StandardBasicTypes.STRING);
		sqlQuery.addScalar("unit", StandardBasicTypes.STRING);
		sqlQuery.addScalar("state", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sourceAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("remark", StandardBasicTypes.STRING);
		sqlQuery.addScalar("updateTime", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("userId", StandardBasicTypes.STRING);
		sqlQuery.addScalar("manufacturer", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributor", StandardBasicTypes.STRING);
		sqlQuery.addScalar("distributorAddr", StandardBasicTypes.STRING);
		sqlQuery.addScalar("sysCode", StandardBasicTypes.STRING);
		
		sqlQuery.addScalar("entName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("entId", StandardBasicTypes.INTEGER);
		
		sqlQuery.addScalar("typeId", StandardBasicTypes.INTEGER);
		sqlQuery.addScalar("typeName", StandardBasicTypes.STRING);
		sqlQuery.addScalar("typeClass", StandardBasicTypes.STRING);

		sqlQuery.addScalar("retain", StandardBasicTypes.STRING);
		sqlQuery.addScalar("storageConditions", StandardBasicTypes.STRING);
		sqlQuery.addScalar("authentication", StandardBasicTypes.STRING);
		sqlQuery.addScalar("shelfLife", StandardBasicTypes.STRING);
		
		
		sqlQuery.setResultTransformer(Transformers.aliasToBean(TbProduct.class));
		
		List<TbProduct> list =sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
				
		String hql_ct = "select count(*) from TB_PRODUCT p left join tb_pro_type t on(p.type_id=t.type_id) left join ts_enterprise e on(p.ent_id=e.ent_id) left join tb_pro_qrcode q on (q.pro_id=p.pro_id) "+condition_ct;
		
		//hql_ct = hql_ct.substring(0,hql_ct.lastIndexOf("order by"));
		
		int ct = this.countBySql(hql_ct).intValue();
		
		Pager<TbProduct> pager = new Pager<TbProduct>();
		
		pager.setData(list);
		
		pager.setTotal(ct);
		
		return pager;
	
	}


}
