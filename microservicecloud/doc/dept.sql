
create database cloudDB01;
create database cloudDB02;
create database cloudDB03;

# dept表
CREATE TABLE `dept` (
	`deptno` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`dname` VARCHAR(50) NULL DEFAULT '0',
	`db_source` VARCHAR(50) NULL DEFAULT '0',
	PRIMARY KEY (`deptno`)
)
COLLATE='utf8_general_ci';

insert into dept(dname,db_source) values("name1",database());
insert into dept(dname,db_source) values("name2",database());
insert into dept(dname,db_source) values("name3",database());
insert into dept(dname,db_source) values("name4",database());
insert into dept(dname,db_source) values("name5",database());
insert into dept(dname,db_source) values("name6",database());
