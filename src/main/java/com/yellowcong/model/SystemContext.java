package com.yellowcong.model;

public class SystemContext {
	/**
	 * 分页的大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 当前页面大小
	 */
	private static ThreadLocal<Integer> pageNow = new ThreadLocal<Integer>();
	/**
	 * 排序字段
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 排序方式
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	
	private static ThreadLocal<String> realPath = new ThreadLocal<String>();
	
	
	public static Integer getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(Integer _pageSize) {
		SystemContext.pageSize.set(_pageSize);;
	}
	public static Integer getPageNow() {
		return pageNow.get();
	}
	public static void setPageNow(Integer _pageNow) {
		SystemContext.pageNow.set(_pageNow);
	}
	public static String getSort() {
		return sort.get();
	}
	public static void setSort(String _sort) {
		SystemContext.sort.set(_sort);
	}
	public static String getOrder() {
		return order.get();
	}
	public static void setOrder(String _order) {
		SystemContext.order.set(_order);
	}
	
	public static void removePageSize(){
		pageSize.remove();
	}
	public static void removePageNow(){
		pageNow.remove();
	}
	public static void removePageOrder(){
		order.remove();
	}
	public static void removePageSort(){
		sort.remove();
	}
	public static String getRealPath() {
		return realPath.get();
	}
	public static void setRealPath(String _realPath) {
		realPath.set(_realPath);
	}
	public static void removeRealPath(){
		realPath.remove();
	}
	
	
	
	
}
