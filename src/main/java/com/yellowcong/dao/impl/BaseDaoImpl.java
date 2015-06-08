package com.yellowcong.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;
import com.yellowcong.dao.BaseDao;
import com.yellowcong.model.Pager;
import com.yellowcong.model.SystemContext;

/**
 * 实现BaseDao 这个版本的BaseDao功能更加的强大
 * 
 * @author 狂飙のyellowcong 2015年1月22日
 * 
 * @param <T>
 */
@SuppressWarnings({ "unused", "unchecked" })
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<T> clazz;

	private Class<T> getClazz() {
		//当不存在Class数据的时候
		if (clazz == null) {
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			clazz = (Class<T>) type.getActualTypeArguments()[0];
		}
		return clazz;
	}

	@Resource(name = "sessionFactory")
	private void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	  
	public T add(T t) {
		this.getHibernateTemplate().save(t);
		return t;
	}

	  
	public void delete(int id) {
		super.getHibernateTemplate().delete(this.load(id));
	}

	  
	public void update(T t) {
		super.getHibernateTemplate().update(t);
	}

	  
	public T load(int id) {
		return super.getHibernateTemplate().get(getClazz(), id);
	}

	  
	public Object executeQuery(String hql, Object[] args) {
		return this.executeQuery(hql, args, null);
	}

	/**
	 * 定义一个设定参数的方法， 这个用于 简单数据 Update 和单个数据查询Query参数设定
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	private Query setParameter(String hql, Object[] args) {

		Query query = this.getSession().createQuery(hql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query;
	}

	  
	public Object executeQuery(String hql, Object args) {
		return this.executeQuery(hql, new Object[] {args });
	}

	  
	public Object executeQuery(String hql) {
		return this.executeQuery(hql, null, null);
	}

	  
	public void executeUpdate(String hql, Object[] args) {
		Query query = this.setParameter(hql, args);
		query.executeUpdate();
	}

	  
	public void executeUpdate(String hql, Object args) {
		this.executeQuery(hql, new Object[]{args});
	}

	  
	public void executeUpdate(String hql) {
		this.executeUpdate(hql, null);
	}

	  
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}

	  
	public List<T> list(String hql) {
		return this.list(hql, null, null);
	}

	  
	public List<T> list(String hql, Object args) {
		return this.list(hql, new Object[] { args });
	}

	/**
	 * 初始化HQL中的排序
	 * 
	 * @param hql
	 *            hql原始语句
	 * @return 格式化后的对象
	 */
	private String initSortHql(String hql) {
		// 带别名的设定方法
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		// 当需要排序的字段不为空的情况
		if (sort != null && !"".equals(sort)) {
			hql += " order by " + sort;
			// 当排序设定为desc的时候，才会添加，不然默认 asc
			if ("desc".equals(order)) {
				hql += " desc ";
			}
		}else{
			//将数据设定到  SystemContext 中
			SystemContext.setOrder("desc");
			SystemContext.setSort("id");
		}
		return hql;
	}

	/**
	 * 设定参数方法 中的数据
	 * 
	 * @param alias
	 *            参数 Map<String,Object> 集合
	 * @param query
	 *            需要设定Query数据的对象
	 */
	@SuppressWarnings("rawtypes")
	private void setAlias(Map<String, Object> alias, Query query) {
		// 当集合中不为空
		// 设定排序后，我们需要设定别名，有 别名和参数同时存在的情况，我们先设定别名，然后设定参数，不然会报错
		// 设定别名的时候，我们需要考虑是否是集合类型的数据
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				// 获取到 我们的别名参数，判断数据类型
				Object obj = alias.get(key);
				if (obj instanceof Collection){
					//设定数据的时候，是setParameterList
					query.setParameterList(key, (Collection) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
	}

	/**
	 * 给列表数据使用 的参数设定，不同于查询 参数设定
	 * 
	 * @param args
	 *            参数数组 Object [] args
	 * @param query
	 *            Query对象
	 */
	private void setParameter(Object[] args, Query query) {
		// 设定参数数据
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
	}

	  
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		// 初始化排序HQL语句
		hql = initSortHql(hql);
		// 创建Query
		Query query = this.getSession().createQuery(hql);
		// 设定别名
		this.setAlias(alias, query);
		// 设定参数
		this.setParameter(args, query);
		return query.list();
	}

	/**
	 * 通过 HQL 语句 来格式化，然后获取多少条数据
	 * 
	 * @param hql
	 * @return
	 */
	private int getRowCount(String hql, Object[] args, Map<String, Object> alias) {
		// 获取sql 语句
		String countHql = "select count(*) "+hql.substring(hql.indexOf("from"));
		countHql.replaceAll("fetch", "");
		// 获取 RowCount 中的数据
		int rowCount = Integer.parseInt(this
				.executeQuery(countHql, args, alias).toString());
		return rowCount;
	}
	/**
	 * 通过 HQL 语句 来格式化，然后获取多少条数据
	 * 
	 * @param hql
	 * @return
	 */
	private int getSQLRowCount(String hql, Object[] args, Map<String, Object> alias) {
		// 获取sql 语句
		String countHql = "select count(*) "+hql.substring(hql.indexOf("from"));
		countHql.replaceAll("fetch", "");
		// 获取 RowCount 中的数据
		SQLQuery query  = this.getSession().createSQLQuery(countHql);
		this.setAlias(alias, query);
		this.setParameter(args, query);
		Integer rowCount = Integer.parseInt(query.uniqueResult().toString());
		return rowCount;
	}
	  
	public List<T> list(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}

	  
	public Pager<T> queryByPager(String hql, Object[] args) {
		return this.queryByPager(hql, args, null);
	}

	  
	public Pager<T> queryByPager(String hql, Object args) {
		return this.queryByPager(hql, new Object[] { args });
	}

	  
	public Pager<T> queryByPager(String hql) {
		return this.queryByPager(hql, null, null);
	}

	  
	public Pager<T> queryByPager(String hql, Object[] args,
			Map<String, Object> alias) {
		// 获取总的记录数
		int rowCount = this.getRowCount(hql, args, alias);
		hql = initSortHql(hql);
		Query query = this.getSession().createQuery(hql);
		
		// 获取SystemContext 中的数据
		Integer pageSize = SystemContext.getPageSize();
		Integer pageNow = SystemContext.getPageNow();
		// 设定默认大小
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		// 设定默认 当前页面
		if (pageNow == null || pageNow <= 0) {
			pageNow = 1;
		}
		setAlias(alias, query);
		setParameter(args, query);
		query.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
		List<T> data = query.list();

		// 设定Pager 中的 数据
		Pager<T> pager = new Pager<T>();
		pager.setData(data);
		pager.setPageNow(pageNow);
		pager.setPageSize(pageSize);
		pager.setPageCount((rowCount - 1) / pageSize + 1);
		pager.setRowCount(rowCount);

		return pager;
	}

	  
	public Pager<T> queryByPager(String hql, Map<String, Object> alias) {
		return this.queryByPager(hql, null, alias);
	}

	  
	public List<? extends Object> listBySql(String sql, Object[] args, Class<?> clazz,
			boolean hasEntity) {
		return this.listBySql(sql, args, null, clazz, hasEntity);
	}

	  
	public List<? extends Object> listBySql(String sql, Object args, Class<?> clazz,
			boolean hasEntity) {
		return this.listBySql(sql, new Object[] { args }, null, clazz,
				hasEntity);
	}

	  
	public List<? extends Object> listBySql(String sql, Class<?> clazz, boolean hasEntity) {
		return this.listBySql(sql, null,null, clazz, hasEntity);
	}

	  
	public List<? extends Object> listBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clazz, boolean hasEntity) {
		sql = this.initSortHql(sql);
		// 设定数据查询 获取SQLQuery 查询工具
		SQLQuery query = this.getSession().createSQLQuery(sql);
		this.setAlias(alias, query);
		this.setParameter(args, query);
		
		// 查看是否有实体类
		if (hasEntity) {
			query.addEntity(clazz);
		} else {
			query.setResultTransformer(Transformers.aliasToBean(clazz));
		}
		return query.list();
	}

	  
	public List<? extends Object> listBySql(String sql, Map<String, Object> alias,
			Class<?> clazz, boolean hasEntity) {
		return this.listBySql(sql, null, alias, clazz, hasEntity);
	}

	  
	public Pager<? extends Object> queryPagerBySql(String sql, Object[] args, Class<?> clazz,
			boolean hasEntity) {
		return this.queryPagerBySql(sql, args, null, clazz, hasEntity);
	}

	  
	public Pager<? extends Object> queryPagerBySql(String sql, Object args, Class<?> clazz,
			boolean hasEntity) {
		return this.queryPagerBySql(sql, new Object[]{args}, null, clazz, hasEntity);
	}

	  
	public Pager<? extends Object> queryPagerBySql(String sql, Class<?> clazz,
			boolean hasEntity) {
		return this.queryPagerBySql(sql, null, null, clazz, hasEntity);
	}

	  
	public Pager<? extends Object> queryPagerBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clazz, boolean hasEntity) {
		
		int rowCount = this.getSQLRowCount(sql, args, alias);
		// 设定查询 顺序
		sql = this.initSortHql(sql);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		// 设定别名
		this.setAlias(alias, query);
		// 设定参数
		this.setParameter(args, query);
		// 获取SystemContext 中的数据
		Integer pageSize = SystemContext.getPageSize();
		Integer pageNow = SystemContext.getPageNow();
		// 设定默认大小
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		// 设定默认 当前页面
		if (pageNow == null || pageNow <= 0) {
			pageNow = 1;
		}
		query.setMaxResults(pageSize).setFirstResult((pageNow-1)*pageSize);
		//设定实体类
		if(hasEntity){
			query.addEntity(clazz);
		}else{
			query.setResultTransformer(Transformers.aliasToBean(clazz));
		}
		//获取数据
		List<T> data = query.list();
		//获取分页 界面
		Pager<T> pager = new Pager<T>();
		pager.setData(data);
		pager.setRowCount(rowCount);
		pager.setPageCount((rowCount-1)/pageSize +1);
		pager.setPageSize(pageSize);
		pager.setPageNow(pageNow);
		
		return pager;
	}

	  
	public Pager<? extends Object> queryPagerBySql(String sql, Map<String, Object> alias,
			Class<?> clazz, boolean hasEntity) {
		return this.queryPagerBySql(sql, null, alias, clazz, hasEntity);
	}

	  
	public Object executeQuery(String hql, Object[] args,
			Map<String, Object> alias) {
		Query query = this.getSession().createQuery(hql);
		setAlias(alias, query);
		setParameter(args, query);
		return query.uniqueResult();
	}

	  
	public Object executeQuery(String hql, Map<String, Object> alias) {
		return this.executeQuery(hql, null, alias);
	}

	  
	public Object executeQueryBySQL(String sql, Class<?> clazz,
			boolean hasEntity) {
		return this.executeQueryBySQL(sql,null,null, clazz, hasEntity);
	}

	  
	public Object executeQueryBySQL(String sql, Object args, Class<?> clazz,
			boolean hasEntity) {
		return this.executeQueryBySQL(sql,new Object[]{args},null, clazz, hasEntity);
	}

	  
	public Object executeQueryBySQL(String sql, Object[] args, Class<?> clazz,
			boolean hasEntity) {
		return this.executeQueryBySQL(sql,args,null, clazz, hasEntity);
	}

	  
	public Object executeQueryBySQL(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clazz, boolean hasEntity) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		this.setAlias(alias, query);
		this.setParameter(args, query);
		
		//设定实体类
		if(hasEntity){
			query.addEntity(clazz);
		}else{
			query.setResultTransformer(Transformers.aliasToBean(clazz));
		}
		return query.uniqueResult();
	}

	  
	public Object executeQueryBySQL(String sql, Map<String, Object> alias,
			Class<?> clazz, boolean hasEntity) {
		return this.executeQueryBySQL(sql,null,alias, clazz, hasEntity);
	}

	public void addObj(Object obj) {
		this.getHibernateTemplate().save(obj);
	}
}
