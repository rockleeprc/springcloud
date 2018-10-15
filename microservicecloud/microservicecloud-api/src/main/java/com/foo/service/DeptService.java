package com.foo.service;

import java.util.List;
import com.foo.entities.Dept;


public interface DeptService
{
	public Dept get(Long id);


	public List<Dept> list();


	public boolean add(Dept dept);
}
