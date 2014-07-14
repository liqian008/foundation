package com.bruce.foundation.model.paging;

import java.util.List;
import java.util.Map;

public class PagingResult<T> {
	/*当前页数*/
	private int pageNo;
	/*每页数量*/
	private int pageSize;
	/*总记录条数*/
	private int totalCount;
	/*总页数*/
	private int totalPage;
	
	private int offset;
	private List<T> dataList;
	
	/*查询的requestUri*/
	private String requestUri;
	/*查询参数的map*/
	private Map<String, Object> queryMap;
	
	public PagingResult(int pageNo, int pageSize, int totalCount, List<T> dataList){
		this.pageNo = pageNo <= 0 ? 1 : pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.dataList = dataList;
	}
	
	public int getPageNo() {
		return pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<T> getPageData() {
		return dataList;
	}

	public int getOffset() {
		offset = (pageNo-1) * pageSize;
		return offset;
	}

	public int getTotalPage() {
		totalPage = (int) Math.ceil(totalCount/Double.parseDouble(String.valueOf(pageSize)));
		return totalPage;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}

	
	
}
