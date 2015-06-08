package com.yellowcong.dao;

import java.util.List;
import java.util.Map;

import com.yellowcong.model.Pager;

/**
 * 通过BaseDao<T> 来完成 所有的数据库中的操作 
 * @author 狂飙のyellowcong 2015年1月22日
 *
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * 添加对象
	 * @param t 对象
	 * @return 返回添加对象
	 */
	public T add(T t);
	/**
	 * 添加Object 对象
	 * @param obj
	 */
	public void addObj(Object obj);
	/**
	 * 通过ID删除对象
	 * @param id 对象ID
	 */
	public void delete(int id);
	/**
	 * 更新对象
	 * @param t 需要更新的对象
	 */
	public void update(T t);
	
	/**
	 * 通过ID来加载数据
	 * @param id 需要加载 对象的ID
	 * @return 返回ID 对应的对象
	 */
	public T load(int id);
	
	/**
	 * 查询Object 对象
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object executeQuery(String hql,Object [] args);
	public Object executeQuery(String hql,Object args);
	public Object executeQuery(String hql);
	public Object executeQuery(String hql,Object []  args,Map<String,Object>  alias);
	public Object executeQuery(String hql,Map<String,Object> alias);
	
	/**
	 * 基于SQL 查询的方法
	 * @param sql
	 * @param clazz
	 * @param hasEntity
	 * @return
	 */
	public Object executeQueryBySQL(String sql,Class<?> clazz,boolean hasEntity);
	public Object executeQueryBySQL(String sql,Object args,Class<?> clazz,boolean hasEntity);
	public Object executeQueryBySQL(String sql,Object [] args,Class<?> clazz,boolean hasEntity);
	public Object executeQueryBySQL(String sql,Object [] args,Map<String,Object> alias,Class<?> clazz,boolean hasEntity);
	public Object executeQueryBySQL(String sql,Map<String,Object> alias,Class<?> clazz,boolean hasEntity);
	/**
	 * 执行更新语句
	 * @param hql
	 * @param args
	 */
	public void executeUpdate(String hql,Object [] args);
	public void executeUpdate(String hql,Object args);
	public void executeUpdate(String hql);
	
	/**
	 * 不带分页的查询方式
	 * @param hql HQL查询语句
	 * @param args 参数数组
	 * @return  查询对象
	 */
	public List<T> list(String hql,Object [] args);
	public List<T> list(String hql,Object args);
	public List<T> list(String hql);
	/**
	 *  基于别名和参数的查询方式
	 * @param hql
	 * @param args
	 * @param alias
	 * @return
	 */
	public List<T> list(String hql,Object [] args,Map<String,Object> alias);
	/**
	 * 基于别名的查询方式
	 * @param hql
	 * @param alias
	 * @return
	 */
	public List<T> list(String hql, Map<String,Object> alias);
	
	/**
	 * 基于分页的查询方法
	 * @param hql
	 * @param args
	 * @return
	 */
	public Pager<T> queryByPager(String hql,Object [] args);
	public Pager<T> queryByPager(String hql,Object args);
	public Pager<T> queryByPager(String hql);
	
	public Pager<T> queryByPager(String hql,Object [] args,Map<String,Object> alias);
	public Pager<T> queryByPager(String hql,Map<String,Object> alias);
	
	/**
	 * 基于SQL语句查询数据
	 * @param sql sql语句
	 * @param args
	 * @param calzz
	 * @param hasEntity 是否存在实体对象
	 * @return
	 */
	public List<? extends Object> listBySql(String sql,Object [] args,Class<?> calzz,boolean hasEntity);
	public List<? extends Object> listBySql(String sql,Object args,Class<?> calzz,boolean hasEntity);
	public List<? extends Object> listBySql(String sql,Class<?> calzz,boolean hasEntity);
	public List<? extends Object> listBySql(String sql,Object [] args,Map<String,Object> alias,Class<?> calzz,boolean hasEntity);
	public List<? extends Object> listBySql(String sql,Map<String,Object> alias,Class<?> calzz,boolean hasEntity);
	
	/**
	 * 基于SQL语句查询数据 带分页操作
	 * @param sql sql语句
	 * @param args
	 * @param calzz
	 * @param hasEntity 是否存在实体对象
	 * @return
	 */
	public Pager<? extends Object> queryPagerBySql(String sql,Object [] args,Class<?> calzz,boolean hasEntity);
	public Pager<? extends Object> queryPagerBySql(String sql,Object args,Class<?> calzz,boolean hasEntity);
	public Pager<? extends Object> queryPagerBySql(String sql,Class<?> calzz,boolean hasEntity);
	public Pager<? extends Object> queryPagerBySql(String sql,Object [] args,Map<String,Object> alias,Class<?> calzz,boolean hasEntity);
	public Pager<? extends Object> queryPagerBySql(String sql,Map<String,Object> alias,Class<?> calzz,boolean hasEntity);

}
