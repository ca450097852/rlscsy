package com.hontek.comm.dao;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;

import com.hontek.comm.exception.AppException;
import com.hontek.comm.util.Pager;

/**
 * 公共数据库操作类
 * 其他DAO继承此类获取常用的数据库操作方法
 * @author w.x.w
 * @param <T>
 * @version 2013-09-05           
 */
public class BaseDao<T>{

	private SessionFactory sessionFactory;
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获得当前事物的session
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
    /**
     * 字符集转换
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public String convertString(String str) throws UnsupportedEncodingException{
    	if(str==null||"".equals(str)){
    		return "";
    	}else{
    		return new String(str.toString().getBytes("GBK"),"iso8859-1");
    	}
    }
	
	/**
	 * 获取table的序列
	 * @param tableName
	 * @return
	 */
	public int getTableSequence(String tableName){
		long starttime = System.currentTimeMillis();
		int sq = 1;
		try {
			//首先查询
			String sql ="select current_value from t_sequence where table_name = '"+tableName+"'";						
			SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
			List<Integer> list = sqlQuery.list();			
			if(!list.isEmpty()){
				sq = list.get(0).intValue();
				sql = "update t_sequence set current_value="+(sq+1)+ " where table_name = '"+tableName+"'";		
			}else{
				sql = "insert into t_sequence (table_name,current_value,min_value,max_value,head_mark,cycle,head,sequence_len,head_value) values" +
						"('"+tableName+"',"+(2)+",1,999999999,'',0,0,9,'')";		
			}
			//新增或者更新
			sqlQuery = getCurrentSession().createSQLQuery(sql);
			sqlQuery.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		System.out.println("get "+tableName+" table sequence need time "+(System.currentTimeMillis()-starttime)+" ms");

		return sq;
	}
	
	/**
	 * 保存一个对象
	 * @param o 对象
	 * @return
	 */
	public Serializable save(T o) throws AppException {
		if (o != null) {		
			return this.getCurrentSession().save(o);
		}
		return null;
	}
	
	/**
	 * 删除一个对象
	 * @param o 对象
	 * @return
	 */
	public void delete(T o) throws AppException {
		if (o != null) {
			this.getCurrentSession().delete(o);
		}
	}
	
	/**
	 * 更新一个对象
	 * @param o 对象
	 * @return
	 */
	public void update(T o) throws AppException {
		if (o != null) {
			this.getCurrentSession().update(o);
		}
	}
	/**
	 * 保存或更新一个对象
	 * @param o 对象
	 * @return
	 */
	public void saveOrUpdate(T o) throws AppException {
		if (o != null) {
			this.getCurrentSession().saveOrUpdate(o);
		}
	}
	/**
	 * 获得对象列表
	 * @param HQL语句
	 * @return List
	 */
	public List<Object> findObject(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	/**
	 * 获得对象列表
	 * @param HQL语句
	 * @return List
	 */
	public List<String> findObjectList(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	/**
	 * 获得对象列表
	 * @param HQL语句
	 * @return List
	 */
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	/**
	 * 通过主键获得对象
	 * @param c 类名.class
	 * @param id 主键
	 * @return 对象
	 */
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/**
	 * 通过HQL语句获取一个对象
	 * @param hql HQL语句
	 * @return 对象
	 */
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 通过HQL语句获取一个对象
	 * @param hql HQL语句
	 * @param params 参数
	 * @return 对象
	 */
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * 获得对象列表
	 * @param hql HQL语句
	 * @param params 参数
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/**
	 * 获得分页后的对象列表
	 * @param hql HQL语句
	 * @param params 参数
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	/**
	 * 获得分页后的对象列表
	 * @param hql HQL语句
	 * @param params 参数
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return List
	 */
	public List<T> findSQL(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	/**
	 * 获得分页后的对象列表
	 * @param hql HQL语句
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(page==0&&rows==0){
			return find(hql);
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	
	
	/**
	 * 获得分页后的对象列表
	 * @param cl class
	 * @param condition 查询条件 如 " and id = 1"
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return Pager
	 */
	public Pager<T> findPager(Class<T> cl, String condition, int page, int rows) {
		String hql = "from "+cl.getName()+" where 1=1 ";
		//System.out.println(hql);
		//拼接条件查询语句
		hql = hql.concat(condition);	
		List<T> list = this.find(hql, page, rows);		
		
		String countHQL = "select count(*) "+hql;
		if(hql.contains("order by")){
			 countHQL = countHQL.substring(0, countHQL.indexOf("order by"));
		}
		if(hql.contains("ORDER BY")){
			 countHQL = countHQL.substring(0, countHQL.indexOf("ORDER BY"));
		}
		Long count = this.count(countHQL);
		Pager<T> pager = new Pager<T> ();
        pager.setData ( list );
        pager.setTotal ( count.intValue () );
		return pager;
	}
	
	
	/**
	 * 获得对象列表
	 * @param cl class
	 * @param condition 查询条件 如 " and id = 1"
	 * @return List
	 */
	public List<T> findList(Class<T> cl, String condition) {
		String hql = "from "+cl.getName()+" where 1=1 ";		
		//拼接条件查询语句
		hql = hql.concat(condition);	
		return find(hql);
	}
	
	/**
	 * 获取唯一值
	 * @param hql HQL语句(select name from TUser)
	 * @return Object
	 */
	public Object getObject(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.uniqueResult();
	}

	/**
	 * 统计数目
	 * @param hql HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	/**
	 * 统计数目
	 * @param hql HQL语句(select count(*) from T where xx = :xx)
	 * @param params  参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	/**
	 * 执行一条HQL语句
	 * @param hql HQL语句
	 * @return 响应结果数目
	 */
	public int executeHql(String hql) throws RuntimeException{
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	/**
	 * 执行一条HQL语句
	 * @param hql HQL语句
	 * @param params 参数
	 * @return 响应结果数目
	 */
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}
	/**
	 * 获得结果集
	 * @param hql  hql语句
	 * @return 结果集
	 */
	public List<Object> findObjectListByHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	
	/**
	 * 获得结果集
	 * @param sql  SQL语句
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}

	

	/**
	 * 获得结果集
	 * @param sql  SQL语句
	 * @return 结果集
	 */
	public List<Object> findObjectListBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}

	
	/**
	 * 获得结果集
	 * @param sql  SQL语句
	 * @return 结果集
	 */
	public Object findObjectBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.uniqueResult();
	}
	
	/**
	 * 获得结果集
	 * @param sql SQL语句
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 获得结果集
	 * @param sql SQL语句
	 * @param params  参数
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/**
	 * 获得结果集
	 * @param sql SQL语句
	 * @param params 参数
	 * @param page 要显示第几页
	 * @param rows 每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 执行SQL语句
	 * @param sql SQL语句
	 * @return 响应行数
	 */
	public int executeSql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	/**
	 * 执行SQL语句
	 * @param sql SQL语句
	 * @param params  参数
	 * @return 响应行数
	 */
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	/**
	 * 统计
	 * @param sql  SQL语句
	 * @return 数目
	 */
	public Integer countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return (Integer) q.uniqueResult();
	}

	
	/**
	 * 统计
	 * @param sql  SQL语句
	 * @return 数目
	 */
	public Object uniqueResult(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return  q.uniqueResult();
	}
}
