package com.bruce.base.service;

import java.util.List;

public interface IBaseService<T, Id>{
	
	public int save(T t);
	
	public int updateById(T t);
	
	public int deleteById(Id id);
	
	public T loadById(Id id);
	
	public List<T> queryAll();

}
