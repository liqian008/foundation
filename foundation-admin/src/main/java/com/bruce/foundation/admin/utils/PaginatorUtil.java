package com.bruce.foundation.admin.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.bruce.foundation.model.paging.PagingResult;

public class PaginatorUtil{
	
	public static <T> String buildPageingHtml(PagingResult<T> pagingResult, int displayLimit) {
		if(pagingResult!=null && pagingResult.getPageSize()>0){
			return buildPageingHtml(pagingResult.getRequestUri(), pagingResult.getQueryMap(), pagingResult.getPageNo(), pagingResult.getPageSize(), pagingResult.getTotalCount(), displayLimit);
		}
		return "";
	}
	
	
	/**
	 * 
	 * @param requestUri
	 * @param queryMap 参数map
	 * @param currentPage 当前页数
	 * @param pageSize 每页条数
	 * @param totalRows 总条数
	 * @param displayLimit 最多显示
	 * @return
	 */
	public static String buildPageingHtml(String requestUri, Map<String, Object> queryMap, int currentPage, int pageSize, int totalRows, int displayLimit) {
		StringBuilder pageNavHtml = new StringBuilder();
		if(queryMap==null){
			queryMap = new HashMap<String, Object>();
		}
		
		if(pageSize<0){
			pageSize=20;
		}
		if (displayLimit < 1||displayLimit > 5) {
			displayLimit = 5;
		}
		// 计算总页数
		int totalPage = (int) Math.ceil((double)totalRows / pageSize);
		// 计算中间页码数字
		int midNum = (int) Math.ceil((double)displayLimit / 2);
		// 计算起始页数字
		int beginNum = currentPage <= midNum ? 1 : currentPage - midNum+1;
		
		// 计算结尾页数字
		int endNum = beginNum + displayLimit - 1;
		if (endNum > totalPage) {
			endNum = totalPage;
		}
		pageNavHtml.append("<div class='dataTables_info' id='dataTables_info'>总计&nbsp;" + totalRows + "&nbsp;条, &nbsp;第&nbsp;"+currentPage+"&nbsp;/&nbsp;"+totalPage+"&nbsp;页</div>");

		// 至少有1页以上 才显示分页导航
		if (totalPage >= 1) {
			
			pageNavHtml.append("<div class='dataTables_paginate paging_full_numbers' id='DataTables_Table_paginate'>");
			
			String firstPageUrl = "javascript:void(0)";//默认首页无点击效果
			if (totalPage > 1) {//全部页>1
				queryMap.put("pageNo", "1");
				firstPageUrl = buildPagingUrl(requestUri, queryMap);
			}
			pageNavHtml.append("<a href='"+firstPageUrl+"' class='first paginate_button' id='DataTables_Table_first'>首页</a>");

			// 如果有上一页
			if (currentPage > beginNum) {
				queryMap.put("pageNo", String.valueOf(currentPage-1));
				pageNavHtml.append("<a href='"+buildPagingUrl(requestUri, queryMap)+"' class='previous paginate_button' id='DataTables_Table_previous'>&lt;</a>");
			}
			
			pageNavHtml.append("<span>");
			
			for (int i = beginNum; i <= endNum; i++) {
				queryMap.put("pageNo", String.valueOf(i));
				if (i == currentPage) {
					pageNavHtml.append("<a href='"+buildPagingUrl(requestUri, queryMap)+"' class='paginate_active'>"+i+"</a>");
				} else {
					pageNavHtml.append("<a href='"+buildPagingUrl(requestUri, queryMap)+"' class='paginate_button'>"+i+"</a>");
				}
			}
			pageNavHtml.append("</span>");

			// 如果有下一页
			if (currentPage < endNum) {
				queryMap.put("pageNo", String.valueOf(currentPage+1));
				pageNavHtml.append("<a href='"+buildPagingUrl(requestUri, queryMap)+"' class='next paginate_button' id='DataTables_Table_next'>&gt;</a>");
			}

//			// 需要显示 尾页
//			// if (currentPage < totalPage) {
//			//默认首页无点击效果
//			queryMap.put("pageNo", String.valueOf(totalPage));
//			pageNavHtml.append("<a href='"+buildPagingUrl(requestUri, queryMap)+"' class='last paginate_button' id='DataTables_Table_first'>尾页</a>");
//			// }
			
			String lastPageUrl = "javascript:void(0)";//默认首页无点击效果
			if (totalPage > 1) {//全部页>1
				queryMap.put("pageNo", String.valueOf(totalPage));
				lastPageUrl = buildPagingUrl(requestUri, queryMap);
			}
			pageNavHtml.append("<a href='"+lastPageUrl+"' class='last paginate_button' id='DataTables_Table_first'>尾页</a>");
			
			
			pageNavHtml.append("</div>");
		}
		return pageNavHtml.toString();
	}
	
	/**
	 * 构造分页链接url
	 * @param requestUri
	 * @param queryMap
	 * @return
	 */
	private static String buildPagingUrl(String requestUri, Map<String, Object> queryMap){
		if(requestUri!=null&&queryMap!=null){
			StringBuilder sb= new StringBuilder();
			for(Entry<String, Object> entry: queryMap.entrySet()){
				String key = entry.getKey();
				Object valueObj = entry.getValue();
				if(valueObj==null){
					//do nothing
				}else if(valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for(String value: values){
						sb.append(key);
						sb.append("=");
//						sb.append(URLEncoder.encode(value));
						sb.append(value);
						sb.append("&");
					}
				}else if(valueObj instanceof String) {
					String value = (String) valueObj;
					sb.append(key);
					sb.append("=");
					if(StringUtils.isNotBlank(value)){
//						sb.append(URLEncoder.encode(value));
						sb.append(value);
					}
					sb.append("&");
				}
			}
			return requestUri + "?" + sb.toString();
		}
		return requestUri;
		
	}
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "我");
		System.out.println(buildPageingHtml("www.jinwanr.com.cn", map, 4, 2, 17, 5));
	}

}
