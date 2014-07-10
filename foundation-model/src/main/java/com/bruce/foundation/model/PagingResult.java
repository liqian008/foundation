package com.bruce.foundation.model;

import java.util.List;

public class PagingResult<T> {

	private int totalCount;
	private int pageIndex;
	private int totalPage;
	private int offset;
	private int pageSize;
	private List<T> pageData;
	
	public PagingResult(int pageIndex, int pageSize){
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
	}
	

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getOffset() {
		offset = (pageIndex-1) * pageSize;
		return offset;
	}

	public int getTotalPage() {
		totalPage = (int) Math.ceil(totalCount/Double.parseDouble(String.valueOf(pageSize)));
		return totalPage;
	}
	
}
