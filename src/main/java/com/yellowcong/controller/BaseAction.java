package com.yellowcong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yellowcong.dao.BaseDao;
import com.yellowcong.utils.JsonBuilder;

/**
 * 定义 BaseAction 数据的CURD操作
 * @author 狂飙のyellowcong
 *
 * 2015年6月4日
 */
public class BaseAction extends ActionSupport implements ModelDriven<Object> {
	
	//request对象
	protected HttpServletRequest request;
	/**返回对象*/
	protected HttpServletResponse response;
	
	/**项目部署的WebRoot路径*/
	public static String webrootAbsPath;
	/**项目部署的class路径*/
	public static String absClassPath;
	/**Json工具类*/
	protected static JsonBuilder jsonBuilder;
	//传入对象
	protected static BaseDao<Object> baseDao;

	
	/**------------变量声明-------------*/
	/**实体全名称*/
	protected String modelName;
	/**上传文件字段值*/
	protected String uploadFields;
	/** 表名*/
	protected String tableName;
	/**主键名,这个鉴于有的对象主键不是我们的  id,一般情况下 主键是 id*/
	protected String pkName;
	/**主键值*/
	protected String pkValue;
	/**第几页*/
	protected int start=0;
	/**每页几条*/
	protected int limit=30;
	/**排序*/
	protected String sort;
	/**查询条件*/
	protected String whereSql="";
	/** 排序条件*/
	protected String orderSql="";
	/**主键值列表*/
	protected String ids;
	/**传输字符串*/
	protected String strData;
	/**为了json排除的字段*/
	protected String excludes="";  //checked
	
	//静态实例化对象
	static{
		jsonBuilder=JsonBuilder.getInstance();
	}
	
	//获取对象实例化的模型
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 默认的数据加载方法， 加载一个集合数据
	 */
	public void loadList(){
		
	}
	
	/**
	 * 通过id来加载数据
	 */
	public void load(){
		
	}
	
	/**
	 * 默认个更新方法
	 */
	public void update(){
		
	}
	
	/**
	 * 删除对象的方法
	 */
	public void delete(){
		
	}
	
	/**
	 * 更新列表的方法
	 */
	public void updateList(){
		
	}
	
	/**
	 * 获取树的列表
	 */
	public void tree(){
		
	}
	
	/**
	 * 通过json的方法写数据到客户端
	 */
	protected void toWrite(String content){
		
	}
}
