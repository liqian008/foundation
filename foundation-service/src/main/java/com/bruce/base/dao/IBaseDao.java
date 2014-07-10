package com.bruce.base.dao;

import java.util.List;

import com.bruce.base.model.PagingResult;

//@Deprecated
public interface IBaseDao<T, Id, TCriteria> {
	/*新增*/
	public int save(T t);
	
	/*根据主键更新内容*/
	public int updateById(T t);
	
	/*根据条件更新内容*/
	public int updateByCriteria(T t, TCriteria criteria);
	
	/*根据主键删除*/
	public int deleteById(Id id);
	
	/*根据条件删除*/
	public int deleteByCriteria(TCriteria criteria);
	
	/*根据主键加载*/
	public T loadById(Id id);
	
	/*查询所有*/
	public List<T> queryAll();
	
	/*条件查询*/
	public List<T> queryByCriteria(TCriteria criteria);
	
	/*瀑布流方式加载条件查询*/
	public List<T> fallloadByCriteria(int pageSize, TCriteria criteria);
	
	/*分页方式进行条件查询*/
	public PagingResult<T> pagingByCriteria(int pageNo, int pageSize, TCriteria criteria);

}
