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
 
 （11）分页查询（limit 起始行,查询几行）
 -- 起始行从0开始
 -- 分页：当前页  每页显示多少条
 -- 分页查询当前页的数据的sql: SELECT * FROM student LIMIT (当前页-1)*每页显示多少条,每页显示多少条;
 
 -- 需求： 查询第1,2条记录（第1页的数据）
SELECT * FROM student LIMIT 0,2;
-- 查询第3,4条记录（第2页的数据）
SELECT * FROM student LIMIT 2,2;
-- 查询第5,6条记录（第3页的数据）
SELECT * FROM student LIMIT 4,2;
-- 查询第7,8条记录 (没有记录不显示)
SELECT * FROM student LIMIT 6,2;

（12）查询排序（order by ）
-- 语法 ：order by 字段 asc/desc
-- asc: 顺序，正序。数值：递增，字母：自然顺序（a-z）
-- desc: 倒序，反序。数值：递减，字母：自然反序(z-a)
-- 默认情况下，按照插入记录顺序排序

-- 需求： 按照id顺序排序
SELECT * FROM student ORDER BY id ASC;
SELECT * FROM student ORDER BY id DESC;-- 反序

-- 注意：多个排序条件
-- 需求： 按照servlet正序，按照jsp的倒序
SELECT * FROM student ORDER BY servlet ASC,jsp DESC;

（13）分组查询(group by)
- 需求： 查询男女的人数
SELECT gender,COUNT(*) FROM student GROUP BY gender;

（14）分组查询后筛选
-- 需求： 查询总人数大于2的性别
--- 注意： 分组之前条件使用where关键字，分组之后条件使用having关键字
SELECT gender,COUNT(*) FROM student WHERE GROUP BY gender HAVING COUNT(*)>2;

## 1.6 数据约束
### 1.6.1 默认值
作用： 当用户对使用默认值的字段不插入值的时候，就使用默认值。
注意： 			
1）对默认值字段插入null是可以的。
2）对默认值字段可以插入非null

CREATE TABLE student(
	id INT,
	NAME VARCHAR(20),
	address VARCHAR(20) DEFAULT '广州天河'  -- 默认值
）
-- 当字段没有插入值的时候，mysql自动给该字段分配默认值
INSERT INTO student(id,NAME) VALUES(1,'张三');

### 1.6.2 非空
作用： 限制字段必须赋值
注意：
1）非空字符必须赋值
2）非空字符不能赋null

-- 需求： gender字段必须有值（不为null）
CREATE TABLE student(
	id INT,
	NAME VARCHAR(20),
	gender VARCHAR(2) NOT NULL -- 非空
)

### 1.6.3 唯一
作用： 对字段的值不能重复
注意：
1）唯一字段可以插入null				
2）唯一字段可以插入多个null

CREATE TABLE student(
	id INT UNIQUE, -- 唯一
	NAME VARCHAR(20)
)

### 1.6.4 主键
作用： 非空+唯一
注意：			
1）通常情况下，每张表都会设置一个主键字段。用于标记表中的每条记录的唯一性。
2）建议不要选择表的包含业务含义的字段作为主键，建议给每张表独立设计一个非业务含义的id字段。

CREATE TABLE student(
	id INT PRIMARY KEY, -- 主键
	NAME VARCHAR(20)
)

### 1.6.5 自增长
CREATE TABLE student(
	id INT(4) ZEROFILL PRIMARY KEY AUTO_INCREMENT, -- 自增长，从0开始  ZEROFILL 零填充
	NAME VARCHAR(20)
)
-- 自增长字段可以不赋值，自动递增
INSERT INTO student(NAME) VALUES('张三');
INSERT INTO student(NAME) VALUES('李四');
INSERT INTO student(NAME) VALUES('王五');

-- 不能影响自增长约束
DELETE FROM student;
-- 可以影响自增长约束
TRUNCATE TABLE student;

### 1.6.6 外键
作用：约束两张表的数据
出现两种表的情况：
			解决数据冗余高问题： 独立出一张表		
				例如： 员工表  和  部门表
		问题出现：在插入员工表数据的时候，员工表的部门ID字段可以随便插入！！！！！	
		使用外键约束：约束插入员工表的部门ID字段值
		解决办法： 在员工表的部门ID字段添加一个外键约束

-- 部门表（主表）
CREATE TABLE dept(
	id INT PRIMARY KEY,
	deptName VARCHAR(20)
)

-- 修改员工表（副表/从表）
CREATE TABLE employee(
	id INT PRIMARY KEY,
	empName VARCHAR(20),
	deptId INT,-- 把部门名称改为部门ID
	-- 声明一个外键约束
	CONSTRAINT emlyee_dept_fk（外键名称 ） FOREIGN KEY(deptId) REFERENCES dept(id)
)

注意：
			1）被约束的表称为副表，约束别人的表称为主表，外键设置在副表上的！！！
			2）主表的参考字段通用为主键！
			3）添加数据： 先添加主表，再添加副表
			4）修改数据： 先修改副表，再修改主表
			5）删除数据： 先删除副表，再删除主表

### 1.6.7 级联
问题： 当有了外键约束的时候，必须先修改或删除副表中的所有关联数据，才能修改或删除主表！但是，我们希望直接修改或删除主表数据，从而影响副表数据。可以使用级联操作实现！！！
级联修改： ON UPDATE CASCADE
级联删除： ON DELETE CASCADE

CREATE TABLE employee(
	id INT PRIMARY KEY,
	empName VARCHAR(20),
	deptId INT,-- 把部门名称改为部门ID
	-- 声明一个外键约束
	CONSTRAINT emlyee_dept_fk FOREIGN KEY(deptId) REFERENCES dept(id) ON UPDATE CASCADE ON DELETE CASCADE  -- ON CASCADE UPDATE ：级联修改
)
注意： 级联操作必须在外键基础上使用

## 1.7 三大范式
1. 第一范式： 要求表的每个字段必须是不可分割的独立单元
2. 第二范式： 在第一范式的基础上，要求每张表只表达一个意思。表的每个字段都和表的主键有依赖。
3. 第三范式： 在第二范式基础，要求每张表的主键之外的其他字段都只能和主键有直接决定依赖关系。

## 1.8 关联查询(多表查询)
-- 多表查询规则：1）确定查询哪些表   2）确定哪些哪些字段   3）表与表之间连接条件 (规律：连接条件数量是表数量-1)

1. 内连接查询：只有满足条件的结果才会显示(使用最频繁)
-- 需求：查询员工及其所在部门(显示员工姓名，部门名称)
SELECT empName,deptName       -- 2）确定哪些字段
	FROM employee,dept    -- 1）确定查询哪些表
	WHERE employee.deptId=dept.id  -- 3）表与表之间连接条件
	
-- 内连接的另一种语法
SELECT empName,deptName
	FROM employee
	INNER JOIN dept
	ON employee.deptId=dept.id;
	
-- 使用别名
SELECT e.empName,d.deptName
	FROM employee e
	INNER JOIN dept d
	ON e.deptId=d.id;

2. 左[外]连接查询： 使用左边表的数据去匹配右边表的数据，如果符合连接条件的结果则显示，如果不符合连接条件则显示null
-- （注意： 左外连接：左表的数据一定会完成显示！）
SELECT d.deptName,e.empName
	FROM dept d
	LEFT OUTER JOIN employee e
	ON d.id=e.deptId;
	
3. -- 2.3 右[外]连接查询: 使用右边表的数据去匹配左边表的数据，如果符合连接条件的结果则显示，如果不符合连接条件则显示null
 -- （注意： 右外连接：右表的数据一定会完成显示！）
SELECT d.deptName,e.empName
	FROM employee e
	RIGHT OUTER JOIN dept d
	ON d.id=e.deptId;
	
## 1.9 存储过程
###1.9 存储过程介绍
存储过程：带有逻辑的sql语句
存储过程特点：				
			1）执行效率非常快！存储过程是在数据库的服务器端执行的！！！
			2）移植性很差！不同数据库的存储过程是不能移植。
			
###1.9 存储过程语法			
-- 创建存储过程
DELIMITER $       -- 声明存储过程的结束符
CREATE PROCEDURE pro_test()           --存储过程名称(参数列表)
BEGIN             -- 开始
	-- 可以写多个sql语句;          -- sql语句+流程控制
	SELECT * FROM employee;
END $            -- 结束 结束符

-- 执行存储过程
CALL pro_test();          -- CALL 存储过程名称(参数);

参数：
IN：   表示输入参数，可以携带数据带存储过程中
OUT： 表示输出参数，可以从存储过程中返回结果
INOUT： 表示输入输出参数，既可以输入功能，也可以输出功能

###1.9.1 带有输入参数的存储过程
-- 需求：传入一个员工的id，查询员工信息
DELIMITER $
CREATE PROCEDURE pro_findById(IN eid INT)  -- IN: 输入参数
BEGIN
	SELECT * FROM employee WHERE id=eid;
END $ 

-- 调用
CALL pro_findById(4);

-- 删除存储过程
DROP PROCEDURE pro_testOut;

###1.9.2 带有输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_testOut(OUT str VARCHAR(20))  -- OUT：输出参数
BEGIN
        -- 给参数赋值
	SET str='helljava';
END $

- 调用
-- 如何接受返回参数的值？？
-- ***mysql的变量******
--  全局变量（内置变量）：mysql数据库内置的变量 （所有连接都起作用）
        -- 查看所有全局变量： show variables
        -- 查看某个全局变量： select @@变量名
        -- 修改全局变量： set 变量名=新值
        -- character_set_client: mysql服务器的接收数据的编码
        -- character_set_results：mysql服务器输出数据的编码
        
--  会话变量： 只存在于当前客户端与数据库服务器端的一次连接当中。如果连接断开，那么会话变量全部丢失！
        -- 定义会话变量: set @变量=值
        -- 查看会话变量： select @变量
        
-- 局部变量： 在存储过程中使用的变量就叫局部变量。只要存储过程执行完毕，局部变量就丢失！！

定义一个会话变量name, 2)使用name会话变量接收存储过程的返回值
CALL pro_testOut(@NAME);
-- 查看变量值
SELECT @NAME;

###1.9.3 带有输入输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_testInOut(INOUT n INT)  -- INOUT： 输入输出参数
BEGIN
   -- 查看变量
   SELECT n;
   SET n =500;
END $

-- 调用
SET @n=10;

CALL pro_testInOut(@n);

SELECT @n;

###1.9.4 带有条件判断的存储过程
-- 需求：输入一个整数，如果1，则返回“星期一”,如果2，返回“星期二”,如果3，返回“星期三”。其他数字，返回“错误输入”;
DELIMITER $
CREATE PROCEDURE pro_testIf(IN num INT,OUT str VARCHAR(20))
BEGIN
	IF num=1 THEN
		SET str='星期一';
	ELSEIF num=2 THEN
		SET str='星期二';
	ELSEIF num=3 THEN
		SET str='星期三';
	ELSE
		SET str='输入错误';
	END IF;
END $
CALL pro_testIf(4,@str);
 
SELECT @str;
	
###1.9.5 带有循环功能的存储过程
-- 需求： 输入一个整数，求和。例如，输入100，统计1-100的和
DELIMITER $
CREATE PROCEDURE pro_testWhile(IN num INT,OUT result INT)
BEGIN
	-- 定义一个局部变量
	DECLARE i INT DEFAULT 1;
	DECLARE vsum INT DEFAULT 0;
	WHILE i<=num DO
	      SET vsum = vsum+i;
	      SET i=i+1;
	END WHILE;
	SET result=vsum;
END $

DROP PROCEDURE pro_testWhile;


CALL pro_testWhile(100,@result);

SELECT @result;

USE day16;

###1.9.6 使用查询的结果赋值给变量（INTO）
DELIMITER $
CREATE PROCEDURE pro_findById2(IN eid INT,OUT vname VARCHAR(20) )
BEGIN
	SELECT empName INTO vname FROM employee WHERE id=eid;
END $

CALL pro_findById2(1,@NAME);

SELECT @NAME;

##1.10 触发器
当操作了某张表时，希望同时触发一些动作/行为，可以使用触发器完成！！
例如： 当向员工表插入一条记录时，希望同时往日志表插入数据

-- 需求： 当向员工表插入一条记录时，希望mysql自动同时往日志表插入数据
-- 创建触发器(添加)
CREATE TRIGGER tri_empAdd AFTER INSERT ON employee FOR EACH ROW    -- 当往员工表插入一条记录时
     INSERT INTO test_log(content) VALUES('员工表插入了一条记录');
     
-- 插入数据
INSERT INTO employee(id,empName,deptId) VALUES(7,'扎古斯',1);
INSERT INTO employee(id,empName,deptId) VALUES(8,'扎古斯2',1);

-- 创建触发器(修改)
CREATE TRIGGER tri_empUpd AFTER UPDATE ON employee FOR EACH ROW    -- 当往员工表修改一条记录时
     INSERT INTO test_log(content) VALUES('员工表修改了一条记录');
     
 -- 修改
 UPDATE employee SET empName='eric' WHERE id=7;
 
-- 创建触发器(删除)
CREATE TRIGGER tri_empDel AFTER DELETE ON employee FOR EACH ROW    -- 当往员工表删除一条记录时
     INSERT INTO test_log(content) VALUES('员工表删除了一条记录');
  
 -- 删除
 DELETE FROM employee WHERE id=7;
 
 ##1.11 权限问题
- mysql数据库权限问题：root ：拥有所有权限（可以干任何事情）
 -- 权限账户，只拥有部分权限（CURD）例如，只能操作某个数据库的某张表
 -- 如何修改mysql的用户密码？
 -- password: md5加密函数(单向加密)
 SELECT PASSWORD('root'); -- *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B
 
--  mysql数据库，用户配置 : user表
USE mysql;

SELECT * FROM USER;

-- 修改密码
UPDATE USER SET PASSWORD=PASSWORD('123456') WHERE USER='root';

-- 分配权限账户
GRANT SELECT ON day16.employee TO 'eric'@'localhost' IDENTIFIED BY '123456';
GRANT DELETE ON day16.employee TO 'eric'@'localhost' IDENTIFIED BY '123456';
