# dept表
CREATE TABLE `dept` (
	`deptno` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`dname` VARCHAR(50) NULL DEFAULT '0',
	`db_source` VARCHAR(50) NULL DEFAULT '0',
	PRIMARY KEY (`deptno`)
)
COLLATE='utf8_general_ci'

insert into dept(dname,db_source) values("name1",database()+1);
insert into dept(dname,db_source) values("name2",database()+2);
insert into dept(dname,db_source) values("name3",database()+3);
insert into dept(dname,db_source) values("name4",database()+4);
insert into dept(dname,db_source) values("name5",database()+5);
insert into dept(dname,db_source) values("name6",database()+6);