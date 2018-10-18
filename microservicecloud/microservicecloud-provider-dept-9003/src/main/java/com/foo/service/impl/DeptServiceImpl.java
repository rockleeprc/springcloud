package com.foo.service.impl;

import java.util.List;

import com.foo.dao.DeptDao;
import com.foo.entities.Dept;
import com.foo.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl implements DeptClientService
{
	@Autowired
	private DeptDao dao;
	
	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id)
	{
		return dao.findById(id);
	}

	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

}
