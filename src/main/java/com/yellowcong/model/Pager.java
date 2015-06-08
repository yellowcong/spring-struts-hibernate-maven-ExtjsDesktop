package com.yellowcong.model;

import java.util.List;

/**
 * 分类对象
 * @author 狂飙のyellowcong 2015年1月22日
 *
 */
public class Pager<T> {
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 当前页面
	 */
	private int pageNow;
	/**
	 * 页面数
	 */
	private int pageCount;
	/**
	 * 记录条数
	 */
	private int rowCount;
	/**
	 * 存储的数据
	 */
	private List<T> data;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
