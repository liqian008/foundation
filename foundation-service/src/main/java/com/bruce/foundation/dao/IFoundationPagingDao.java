package com.bruce.foundation.dao;

import java.util.List;

import com.bruce.foundation.model.paging.PagingResult;

/**
 * 额外支持分页加载的基础dao接口
 * @author liqian
 *
 * @param <T>
 * @param <Id>
 * @param <TCriteria>
 */
public interface IFoundationPagingDao<T, Id, TCriteria> extends IFoundationDao<T, Id, TCriteria>{
	
	/*瀑布流方式加载条件查询*/
	public List<T> fallloadByCriteria(int pageSize, TCriteria criteria);
	
	/*分页方式进行条件查询*/
	public PagingResult<T> pagingByCriteria(int pageNo, int pageSize, TCriteria criteria);


}
