package com.foo.service;

import com.foo.entities.Dept;

import java.util.List;


public interface DeptService
{
	public Dept get(Long id);


	public List<Dept> list();


	public boolean add(Dept dept);
}
