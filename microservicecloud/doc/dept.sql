# 库
drop database if exists cloudDB01;
create database if not exists cloudDB01;
drop database if exists cloudDB02;
create database if not exists cloudDB02;
drop database if exists cloudDB03;
create database if not exists cloudDB03;

# 表
use cloudDB03;
drop table if exists dept;
CREATE TABLE if not exists dept (
	`deptno` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`dname` VARCHAR(50) NULL DEFAULT '0',
	`db_source` VARCHAR(50) NULL DEFAULT '0',
	PRIMARY KEY (`deptno`)
)
ENGINE=InnoDB DEFAULT COLLATE='utf8_general_ci';

# 数据
insert into dept(dname,db_source) values("name1",database());
insert into dept(dname,db_source) values("name2",database());
insert into dept(dname,db_source) values("name3",database());
insert into dept(dname,db_source) values("name4",database());
insert into dept(dname,db_source) values("name5",database());
insert into dept(dname,db_source) values("name6",database());

