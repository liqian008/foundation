package com.bruce.foundation.dao;

import java.util.List;


/**
 * 基础数据操作的dao接口
 * @author liqian
 *
 * @param <T>
 * @param <Id>
 * @param <TCriteria>
 */
public interface IFoundationDao<T, Id, TCriteria> {
	
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
	
	/*查询所有&排序*/
	public List<T> queryAll(String orderByClause);
	
	/*条件查询*/
	public List<T> queryByCriteria(TCriteria criteria);
	

}
