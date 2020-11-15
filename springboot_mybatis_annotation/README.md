# 第一章：Oracle详解

## 1.1 范式

**第一范式**：最基本的范式，指导原则为

（1）每个属性只有一个值

（2）每个数组必须包含相同数量的值

（3）每个数组一定不能相同

比如学生的班级为计算机系3班，因为计算机和3班属于两个信息，因此不满足第一范式，因此需要添加一个系别的字段，而班级字段只显示是几班。

**第二范式**：必须满足第一范式

每一行数据必须可以被唯一的区分。

**第三范式**：必须满足第二范式

一个关系表中不能包含已在其他表中包含的非主关键字信息。传递函数依赖即如果存在关键字段A决定非关键字段B，而非关键字段B决定非关键字段C，则称非关键字段依赖于关键字段A。



## 1.2 体系结构

### 1.2.1 逻辑存储结构

主要由表空间、段、区间和数据块组成。

#### 1.2.1.1 数据块

数据块是Oracle逻辑存储结构中的最小逻辑单元。Oracle数据块由多个操作系统块组成。Oracle数据块由块头、表目录、行目录、空余空间和行数据这5部分组成。

**块头**：存放基本信息，如块的物理地址、块所属的段的类型等

**表目录**：存放表的相关信息。

**行目录**：存放行的信息，包括行的地址

**空余空间**：未使用，用于新行的插入和已存在行的更新

**行数据**：存放表数据和索引数据

块头、表目录和行目录称为头部信息区，不存放数据，存放整个块的引导信息，因此出现问题则无法读取数据。空余空间和行数据则存放真实的数据。

#### 1.2.1.2 数据区

数据区是由一组连续的Oracle数据块构成，用来保存特定数据类型的数据。在数据库中，分配存储空间是以数据区间为单位的。

#### 1.2.1.3 段

段是由一个或多个数据区构成。是一个独立的逻辑存储结构。用于存储表、索引或簇等数据对象。

**数据段**：保存表中的数据记录

**索引段**：保存了索引数据

**回滚段**：保存了回滚信息，Oracle将修改前的旧值保存在回滚段中。

**临时段**：当执行sql的时候，临时段会保存解析过的查询语句和一些临时数据

#### 1.2.1.4 表空间

表空间是数据库中最大的逻辑划分区域，当数据表、索引、回滚段等数据对象被创建时，都需要指定对应的表空间。一个表空间由一个或多个数据文件组成，一个数据文件只属于一个表空间。

**SYSTEM表空间**：系统表空间，用于存放系统内部表和数据字典的数据，例如表名、列名、用户名等。

**SYSAUX表空间**：属于系统表空间的辅助表空间

**UODO表空间**：撤销表空间，用于存储撤销信息的表空间。当对数据表进行修改时，撤销表空间会储存修改前的旧数据。

**USER表空间**：用户表空间，用户使用的表空间。



### 1.2.2 物理存储结构

Oracle数据在逻辑上存储在表空间中，在物理上存储在表空间所包含的数据文件中。

#### 1.2.2.1 数据文件

数据文件：存储普通的Oracle数据，当读取数据时，会将数据文件中的数据存储到内存的缓冲区中。当修改数据时，数据会在缓冲区中进行操作，然后按照一定的规则写入到相应的数据文件中。减少了I/O的操作次数。数据文件主要包括：系统数据文件、撤销数据文件、用户数据文件。

**系统数据文件**：比如用户建立的表名、列名以及字段类型等。还有数据字典的内容，主要存储系统表空间的数据。

**撤销数据文件**：存储撤销表空间的数据。

**用户数据文件**：存放用户应用系统的文件。

#### 1.2.2.2 控制文件

控制文件主要包括数据库名、数据文件与日志文件的名字和位置、数据库建立的时间等信息。控制文件一般存在多个，为了防止其中一个无法访问，而自动切换到另一个控制文件。

#### 1.2.2.3 日志文件

日志文件主要记录对数据所做的修改、对数据库所做的修改几乎都存在于日志文件中。包括重做日志文件和归档日志文件。

**重做日志文件**：记录对于数据库更改的信息（包括增删改信息、创建表和索引等）。在数据库恢复的时候，可以从日志中读取到原始记录。在更新数据时，首先会将更新的操作写入日志文件，写入成功后才会真正更新记录。写入日志由一个独立的日志线程来实现。日志信息首先被写入到SGA系统全局区的重做日志缓冲区中，当commit或缓冲区信息量大于1/3时，日志进程将这些日志写入到系列号较小的文件中，写满接着写下一个。重做日志文件记录变更向量，记录更新前和更新后的数据块的相关信息。

**归档日志文件**：在所有的日志文件被写入一遍，再次写入就会覆盖掉之前的日志，因此使用归档日志来存储写入的日志，避免被覆盖。在归档操作中，日志写入进程需要等到归档进程结束才可以覆写日志。

#### **1.2.2.4 服务器参数文件**

数据库在启动的时候，会去参数文件中读取对应的参数，从而根据参数来配置和启动实例。



### 1.2.3 系统全局区

系统全局区是所有用户进程共享的内存区域，SGA主要由高速数据缓冲区、重做日志缓冲区、共享池、大型池和java池等内存结构构成。

#### 1.2.3.1 高速数据缓冲区

高速数据缓冲区存放着最近访问过的数据块，当读取数据时数据会存在此处，再次访问也会先读取此处。所有的用户都可以共享。

脏数据区：存放着已被修改过的数据。等待提交命令后写入数据文件。

#### 1.2.3.2 重做日志缓冲区

重做日志缓冲区用于存放对数据进行修改操作的日志信息。到达一定限度时会被写入到重做日志文件中。

#### 1.2.3.3 共享池

共享池为SGA保留的内存区域，用于缓存SQL语句、数据字典、资源锁及其他控制等。

包括库高速缓冲区和字典高速缓冲区。

库高速缓冲区包括共享SQL区和私有SQL区，共享SQL区保存SQL的语法分析结果和执行计划，私有SQL中存储SQL语句中的绑定变量、环境和会话等属于用户私有信息，其他用户无法访问。



### 1.2.4 程序全局区

程序全局区PGA是用户独享的私有进程区。

私有SQL区：存储变量以及SQL运行时的内存结构

会话区：存放用户的会话信息，如登录用户名。



### 1.2.5 模式

模式是一个数据库对象，为一个数据库用户所有，并且与用户名称相同。在一个模式下有访问其他模式权限的话，需要指定其他模式的名字才能访问。



## 1.3 SQL语句

### 1.3.1 查询

​	所有字段： select * from 表;

​	指定字段： select 字段1,字段2.... from 表;

​	指定别名： select 字段1 as 别名 from 表;

​        合并列： select (字段1+字段2) from 表;

​	去重： select distinct 字段 from 表;

​	条件查询：

​		a)逻辑条件 ：and(与)     or（或）

​			select * from 表 where 条件1 and/or 条件2

​		b）比较条件： >  <  >=  <=  =  <>   between 。。。and 。。。(在。。。之间)

​			select * from 表 where servlet>=90;

​			select * from 表 where servlet >=all(90, 100,  100); 	// 必须符合所有的条件

​			select * from 表 where servlet >=any(90, 100,  100);	// 符合其中一个条件即可

​		c）判空条件： 

​			判断null： is null   /  is not null

​			判断空字符串： =''    /  <>''

​		d）模糊条件： like 

​			select * from student where name like '李%';

​			%:  替换任意个字符

​			_:   替换一个字符

​		e） in：满足条件的任何一个 

​	分页查询：limit 起始行,查询行数

​		select * from student (当前页-1)*每页显示多少条,每页显示多少条

​		select * from student 2,2

​		起始行从0开始

​	排序： order by 字段 asc/desc

​		select * from student order by id asc;

​		asc: 正序，顺序

​		desc：反序，倒序

​	分组查询：group by 字段

​	分组后筛选： having 条件

select deptno, avg(sal) from emp group by deptno having avg(sal) > 2000;

### 1.3.2 多表关联查询

#### 1.3.2.1 表的别名

select e.empno, e.ename, d.dname from emp p, dept d where e.deptno = d.deptno and e.job = 'MANNGER';

#### 1.3.2.2 内连接（只返回匹配的数据）

select e.empno, e.ename, d.dname from emp p inner join dept d on e.deptno = d.deptno and e.job = 'MANNGER';

#### 1.3.2.3 左外连接（匹配的数据以及左表不匹配的数据）

select e.empno, e.ename, d.dname from emp p left join dept d on e.deptno = d.deptno；// 左表的数据都会被打印出来

#### 1.3.2.4 右外连接（匹配的数据以及右表不匹配的数据）

select e.empno, e.ename, d.dname from emp p right join dept d on e.deptno = d.deptno；// 右表的数据都会被打印出来

#### 1.3.2.5 完全外连接

会执行一个完整的额左连接和右连接，然后将结果合并并去重。

select e.empno, e.ename, d.dname from emp p full join dept d on e.deptno = d.deptno；

### 1.3.3 子查询

select empno, ename, sal from emp where sal > (select min(sal) from emp) and sal <  (select max(sal) from emp) ;

select empno, ename, sal from emp where sal in (select sal from dept where dname = 'yuhs') ;



### 1.3.4 增加

insert into emp (empno, ename, job, hiredate) values (1234, 'sdcs', 'dscw', to_date('2020-12-12'，'YYYY-MM-DD'));

批量增加： insert into emp select * from jobs；



### 1.3.5 更新

修改：	update 表 set 字段1=值1，字段2=值2...... where 条件;

update emp set sal=2460 where ename = 'hello';

update emp set sal=2460 and hiredate = to_date('2020-12-12'，'YYYY-MM-DD') where ename = 'hello';



### 1.3.6 删除

删除：	delete from 表 where 条件;

用delete操作后，被删除的数据占用的存储空间还在(类似于回收站)，还可以恢复。Oracle系统会产生回滚记录，因此可以通过ROLLBACK来撤销。

而用truncate删除的数据，被删除的数据会立即释放存储空间，被删除的数据是不能恢复的。truncate的执行速度比delete快

### 1.3.7 表的操作

选择：	use 数据库;

增加：	create table 表(字段名1 字段类型, ....)

删除：	drop table 表;

修改：

​	修改表名称：    alter table 表 rename [to] 新表名;

​	添加字段：	alter table 表 add [column] 字段名 字段类型; 

alter table student add column sgender varchar(2)，add column jsp int;在表student中添加多条字段

​	删除字段:       alter table 表 drop [column] 字段名;

​	修改字段类型：  alter table 表 modify 字段名 新的字段类型;

​	修改字段名称：  alter table 表 change 旧字段名 新字段名 字段类型;

查询：	show tables  /  desc student;

## 1.4 函数

1. concat(s1, s2) 类似于||

   将s2加在s1的后面，s1为null返回s2，s2为null返回s1，s1和s2都为null返回null

2. length(s)：返回s的长度，s为nul则返回null。
3. lower(s)和upper(s)：返回小写或者大写形式
4. ltrim(s1,s2)、rtrim(s1,s2)和trim(s1,s2)：删除字符串s1左边、右边、两边的字符串s2，s2不指定默认为空格。
5. replace(s1, s2, s3)：replace('hello world', 'h', 'w')：将字符串中的h替换为w
6. substr(s, i, j)：把字符串s从第i位开始截取长度为j的子字符串。
7. ceil(n)：返回大于或等于数值n的最小整数。
8. round(n1,n2)：四舍五入，取n2位小数。
9. sysdate()：返回系统当前日期
10. add_months(d, i)：日期d加上i个月：add_months(sysdate(), 6)：当前日期+6个月
11. to_char()：转换为字符串：to_char(sysdate()，'YYYY-MM-DD')：将系统日期转化为如格式上的字符串
12. to_date()：将字符串转换为date类型to_date('2020-12-12'，'YYYY-MM-DD')
13. to_number()：字符串转换为数值：to_number('18', 'xxx')：把十六进制的18转换为十进制的数

注释：

单行：--

多行：/* */



## 1.5 数据类型

NUMBER：存储整数或浮点数，NUMBER(P, S)：P表示有效数字个数，S表示小数位数。如果插入数字大于指定位数，Oracle会自动进行四舍五入，例如NUMBER(5,2)，插入3.1415926，实际保存的值为3.14。

VARCHAR2：可变长度字符串

CHAR：定长字符串，空格补全，超过长度会报错

LONG：可变字符串

DATE：日期类型 