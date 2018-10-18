package com.foo.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class Dept implements Serializable
{
	private Long 	deptno;
	private String 	dname;
	private String 	dbSource;
	
	public Dept(String dname)
	{
		super();
		this.dname = dname;
	}
}
