#  第一章 MySQL
## 1.1 数据库简单介绍
1. 数据保存到内存：
					优点：
						1）读写非常快
					缺点：
						1）程序关闭导致数据丢失
						
2. 数据保存到文件：
					优点：
						1）数据可以永久保存
					缺点：
						1）频繁地IO操作，效率不高！
						2）数据管理不方便。例如查询某个数据需要全部读取出来，再匹配。	
						
3. 数据保存到数据库软件：
					优点：
						1）数据永久保存下来
						2）数据管理非常方便。（例如查询非常快速和方便）	
						
4. 市面上数据库软件
		Oracle，甲骨文公司的产品。 当前最流行应用最广泛的数据库软件。和java语言兼容非常好。适合中大型，中大应用。

		SQL Server: 是微软公司的产品。window平台应用非常广泛。和c#，net平台兼容非常好。

		DB2： IBM公司的产品。IBM服务器--> UNIX -> DB2- > Websphere

		MySQL: 开源组织的产品。甲骨文公司的产品。免费！！！和java语言兼容非常好！适合中小企业，中小应用

		MongoDB： 非关系型数据库。
		
## 1.2 数据库管理
1. 	查询所有数据库
show databases;

 information_schema |     -- mysql元数据，基础数据
| mysql              |    --mysql配置数据库，其中包含用户信息。（用户名和密码，权限管理）
| performance_schema |    --mysql数据库软件的运行数据，日志信息，性能数据
| test               |     --测试数据库。空的

2. 创建数据库
create database database_name default character set utf8; -- 指定默认字符集创建数据库

3. 查看数据库的默认字符集
show create database database_name;

| database_name    | CREATE DATABASE `database_name` /*!40100 DEFAULT CHARACTER SET utf8 */ | --显示字符集

4. 删除数据库
drop database database_name;

5. 修改数据库
alter database database_name default character set gbk;

## 1.3 表管理
1. 选择数据库
use database_name;

2. 查看所有表
show tables;

3. 创建表
create table table_name(sid int, sname varchar(20), sage int);

4. 查看表结构	
desc table_name;

5. 删除表
drop table table_name;

6. 修改表
(1) 添加字段
alter table student add column sgender varchar(2);

(2) 删除字段
alter table student drop column sgender;

(3) 修改字段类型
alter table student modify column remark varchar(100);

(4) 修改字段名称
alter table student change column sgender gender varchar(2);

(5) 修改表名称
alter table student rename to teacher;

## 1.4 曾删改数据
（1）插入所有字段。一定依次按顺序插入
INSERT INTO student VALUES(1,'张三','男',20);
注意：不能少或多字段值

（2）插入部分字段
INSERT INTO student(id,NAME) VALUES(2,'李四');

（3）修改所有数据（建议少用）
UPDATE student SET gender='女';

（4）带条件的修改（推荐使用）修改id为1的学生，修改性别为男
UPDATE student SET gender='男' WHERE id=1; 

（5）修改多个字段,注意: SET 字段名=值,字段名=值,....
UPDATE student SET gender='男',age=30 WHERE id=2;

（6）删除所有数据（建议少用）
DELETE FROM student;

（7）带条件的删除(推荐使用)
DELETE FROM student WHERE id=2;

（8）DELETE和TRUNCATE的区别
-- delete from: 可以全表删除      1)可以带条件删除   2）只能删除表的数据，不能删除表的约束     3) 使用delete from删除的数据可以回滚（事务）
-- truncate table: 可以全表删除   1）不能带条件删除  2）即可以删除表的数据，也可以删除表的约束 3）使用truncate table删除的数据不能回滚

## 1.5 查询数据
（1）查询所有列
SELECT * FROM student;

（2）查询指定列
SELECT id,NAME,gender FROM student;

（3）查询时添加常量列
-- 需求： 在查询student表时添加一个班级列，内容为“java就业班”
SELECT id,NAME,gender,age,'java就业班' AS '年级'  FROM student;

（4）查询时合并列
- 需求： 查询每个学生的servlet和jsp的总成绩
SELECT id,NAME,(servlet+jsp) AS '总成绩' FROM student;
注意：合并列只能合并数值类型的字段

（5）查询时去除重复记录
-- 需求： 查询学生的性别     男 女
SELECT DISTINCT gender FROM student;
另一种语法：SELECT DISTINCT(gender) FROM student;

（6）条件查询
-- 需求： 查询id为2，且姓名为李四的学生
SELECT * FROM student WHERE id=2 AND NAME='李四'; -- 交集

-- 需求： 查询id为2，或姓名为张三的学生
SELECT * FROM student WHERE id=2 OR NAME='张三'; -- 并集

（7）比较条件： >   <   >=  <=  =  <>(不等于)     between and (等价于>= 且 <=)
-- 需求： 查询servlet成绩大于70分的学生
SELECT * FROM student WHERE servlet>70;

-- 需求： 查询jsp成绩大于等于75，且小于等于90分的学生
SELECT * FROM student WHERE jsp>=75 AND jsp<=90;
另一个语法：SELECT * FROM student WHERE jsp BETWEEN 75 AND 90; -- (包前包后)

（8）判空条件(null 空字符串)：  is null / is not null / =\'\'  /  <>\'\'
-- null：表示没有值
-- 空字符串：有值的！

-- 判断null
SELECT * FROM student WHERE address IS NULL ;

-- 判断空字符串
SELECT * FROM student WHERE address=\'\';

SELECT * FROM student WHERE address IS NULL OR address=''; -- （包括null和空字符串）

-- 需求： 查询有地址的学生(不包括null和空字符串)
SELECT * FROM student WHERE address IS NOT NULL AND address<>\'\';

（9）模糊条件： like
-- % : 表示任意个字符
-- _ : 表示一个字符

-- 需求： 查询姓‘李’的学生
SELECT * FROM student WHERE NAME LIKE '李%';

-- 需求： 查询姓‘李’，且姓名只有两个字的学生
SELECT * FROM student WHERE NAME LIKE '李_';

（10）聚合查询
 -- 常用的聚合函数： sum()  avg()  max()  min()  count()
 
 -- 需求：查询学生的servlet的总成绩 (sum() :求和函数)
 SELECT SUM(servlet) AS 'servlet的总成绩' FROM student;
 
 -- 需求： 查询学生的servlet的平均分
 SELECT AVG(servlet) AS 'servlet的平均分' FROM student;
 
 -- 需求: 查询当前servlet最高分
 SELECT MAX(servlet) AS '最高分' FROM student;
 
 -- 需求： 查询最低分
 SELECT MIN(servlet) AS '最低分' FROM student;
 
 -- 需求： 统计当前有多少学生(count(字段))
 SELECT COUNT(*) FROM student;
 SELECT COUNT(id) FROM student;
 -- 注意：count（）函数统计的数量不包含null的数据，使用count统计表的记录数，要使用不包含null值的字段







										