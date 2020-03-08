# 第二十一章 Hibernate
## 21.1 Hibernate简介

Dao的编写历程
1. 操作XML数据
2. 使用Jdbc技术(原始的jdbc操作， Connection/Statement/ResultSet)
3. 自定义一个持久层框架, 封装了dao的通用方法
4. DbUtils组件， 轻量级的dao的组件；
5. Hibernate技术，hibernate最终执行的也是jdbc代码

ORM, 对象关系映射！解决什么问题？
存储：   能否把对象的数据直接保存到数据库？ 
获取：  能否直接从数据库拿到一个对象？
想做到上面2点，必须要有映射！

## 21.2 Hibernate HelloWorld案例

搭建一个Hibernate环境，开发步骤：
1. 下载源码
&emsp;  版本：hibernate-distribution-3.6.0.Final
2. 引入jar文件
&emsp;  hibernate3.jar核心  +  required 必须引入的(6个) +  jpa 目录  + 数据库驱动包
3. 写对象以及对象的映射
&emsp; Employee.java            对象
&emsp; Employee.hbm.xml        对象的映射 (映射文件)
4. src/hibernate.cfg.xml  主配置文件
&emsp; 数据库连接配置
&emsp; 加载所用的映射(*.hbm.xml)
5. App.java  测试

具体代码：

Employee.java     对象

	//一、 对象
	public class Employee {
	
		private int empId;
		private String empName;
		private Date workDate;
	
	}

Employee.hbm.xml  对象的映射

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.a_hello">
	
		<class name="Employee" table="employee">
		
			<!-- 主键 ，映射-->
			<id name="empId" column="id">
			<generator class="native"/>
			</id>
		
			<!-- 非主键，映射 -->
			<property name="empName" column="empName"></property>
			<property name="workDate" column="workDate"></property>
		
		</class>
	
	</hibernate-mapping>

hibernate.cfg.xml    主配置文件

	<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
	
	<hibernate-configuration>
		<session-factory>
			<!-- 数据库连接配置 -->
			<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
			<property name="hibernate.connection.url">jdbc:mysql:///hib_demo</property>
			<property name="hibernate.connection.username">root</property>
			<property name="hibernate.connection.password">root</property>
			<property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
		
			<property name="hibernate.show_sql">true</property>
		
			<!-- 加载所有映射 -->
			<mapping resource="cn/itcast/a_hello/Employee.hbm.xml"/>
		</session-factory>
	</hibernate-configuration>

App.java   测试类

	public class App {
	
		@Test
		public void testHello() throws Exception {
			// 对象
			Employee emp = new Employee();
			emp.setEmpName("班长");
			emp.setWorkDate(new Date());
			// 获取加载配置文件的管理类对象
			Configuration config = new Configuration();
			config.configure();  // 默认加载src/hibenrate.cfg.xml文件
			// 创建session的工厂对象
			SessionFactory sf = config.buildSessionFactory();
			// 创建session (代表一个会话，与数据库连接的会话)
			Session session = sf.openSession();
			// 开启事务
			Transaction tx = session.beginTransaction();
			//保存-数据库
			session.save(emp);
			// 提交事务
			tx.commit();
			// 关闭
			session.close();
			sf.close();
		}
	}

## 21.3 Hibernate  Api

	|-- Configuration       配置管理类对象
		config.configure();    加载主配置文件的方法(hibernate.cfg.xml)
							默认加载src/hibernate.cfg.xml
		config.configure(“cn/config/hibernate.cfg.xml”);   加载指定路径下指定名称的主配置文件
		config.buildSessionFactory();   创建session的工厂对象
	
	|-- SessionFactory     session的工厂（或者说代表了这个hibernate.cfg.xml配置文件）
		sf.openSession();   创建一个sesison对象
		sf.getCurrentSession();  创建session或取出session对象
	
	|--Session       session对象维护了一个连接(Connection), 代表了与数据库连接的会话。
				   Hibernate最重要的对象： 只用使用hibernate与数据库操作，都用到这个对象
			session.beginTransaction(); 开启一个事务； hibernate要求所有的与数据库的操作必须有事务的环境，否则报错！


	更新：
		session.save(obj);   保存一个对象
		session.update(emp);  更新一个对象
		session.saveOrUpdate(emp);  保存或者更新的方法：
								没有设置主键，执行保存；
								有设置主键，执行更新操作; 
								如果设置主键不存在报错！
	
	主键查询：
		session.get(Employee.class, 1);    主键查询
		session.load(Employee.class, 1);   主键查询 (支持懒加载)

## 21.4 三种查询方式

1. HQL查询：
	&emsp; HQL查询与SQL查询区别：
		&emsp; &emsp; SQL: (结构化查询语句)查询的是表以及字段;  不区分大小写。
		&emsp; &emsp; HQL: hibernate  query  language 即hibernate提供的面向对象的查询语言。查询的是对象以及对象的属性。区分大小写。
	
2. Criteria查询：完全面向对象的查询。

3. 本地SQL查询：复杂的查询，就要使用原生态的sql查询，也可以，就是本地sql查询的支持！

## 21.5 Hibernate crud

根据主键查询：

	public class EmployeeDaoImpl implements IEmployeeDao{
	
		@Override
		public Employee findById(Serializable id) {
			Session session = null;
			Transaction tx = null;
			try {
				// 获取Session
				session = HibernateUtils.getSession();
				// 开启事务
				tx = session.beginTransaction();
				// 主键查询
				return (Employee) session.get(Employee.class, id);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}

查询所有

	@Override
		public List<Employee> getAll() {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				// HQL查询
				Query q = session.createQuery("from Employee");
				return q.list();
				} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}

条件查询：
			
	@Override
		public List<Employee> getAll(String employeeName) {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				Query q =session.createQuery("from Employee where empName=?");
				// 注意：参数索引从0开始
				q.setParameter(0, employeeName);
				// 执行查询
				return q.list();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}

分页查询：
	
	@Override
		public List<Employee> getAll(int index, int count) {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				Query q = session.createQuery("from Employee");
				// 设置分页参数
				q.setFirstResult(index);  // 查询的其实行 
				q.setMaxResults(count);	  // 查询返回的行数
			
				List<Employee> list = q.list();
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}

添加：
	
	@Override
		public void save(Employee emp) {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				// 执行保存操作
				session.save(emp);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		
		}

更新：
		
	@Override
		public void update(Employee emp) {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				session.update(emp);
			
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		
		}

删除：
	
	@Override
		public void delete(Serializable id) {
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtils.getSession();
				tx = session.beginTransaction();
				// 先根据id查询对象，再判断删除
				Object obj = session.get(Employee.class, id);
				if (obj != null) {
					session.delete(obj);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				tx.commit();
				session.close();
			}
		}
	}

## 21.6 Hibernate.cfg.xml 主配置

Hibernate.cfg.xml：主配置文件中主要配置：数据库连接信息、其他参数、映射信息！

常用配置查看源码：hibernate-distribution-3.6.0.Final\project\etc\hibernate.properties

数据库连接参数配置（MySQL）：

	## MySQL
	
	#hibernate.dialect org.hibernate.dialect.MySQLDialect
	#hibernate.dialect org.hibernate.dialect.MySQLInnoDBDialect
	#hibernate.dialect org.hibernate.dialect.MySQLMyISAMDialect
	#hibernate.connection.driver_class com.mysql.jdbc.Driver
	#hibernate.connection.url jdbc:mysql:///test
	#hibernate.connection.username gavin
	#hibernate.connection.password

自动建表：

	Hibernate.properties
	
	#hibernate.hbm2ddl.auto create-drop 每次在创建sessionFactory时候执行创建表；
									当调用sesisonFactory的close方法的时候，删除表！
	#hibernate.hbm2ddl.auto create   每次都重新建表； 如果表已经存在就先删除再创建
	#hibernate.hbm2ddl.auto update  如果表不存在就创建； 表存在就不创建；
	#hibernate.hbm2ddl.auto validate  (生成环境时候) 执行验证： 当映射文件的内容与数据库表结构不一样的时候就报错！

代码自动建表：

	public class App_ddl {
	
		// 自动建表
		@Test
		public void testCreate() throws Exception {
			// 创建配置管理类对象
			Configuration config = new Configuration();
			// 加载主配置文件
			config.configure();
		
			// 创建工具类对象
			SchemaExport export = new SchemaExport(config);
			// 建表
			// 第一个参数： 是否在控制台打印建表语句
			// 第二个参数： 是否执行脚本
			export.create(true, true);
		}
	}

## 21.7 映射配置

问题：
1. 一个表能否有多个主键？   不能。
2. 为什么要设置主键？       数据库存储的数据都是有效的，必须保持唯一。
3. 为什么把id作为主键？	因为表中通常找不到合适的列作为唯一列即主键，所以为了方法用id列，因为id是数据库系统维护可以保证唯一，所以就把这列作为主键!
4. 联合/复合主键：如果找不到合适的列作为主键，出来用id列以外，我们一般用联合主键，即多列的值作为一个主键，从而确保记录的唯一性。

映射配置：

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">


	<!-- 映射文件: 映射一个实体类对象；  描述一个对象最终实现可以直接保存对象数据到数据库中。  -->
	<!-- 
		package: 要映射的对象所在的包(可选,如果不指定,此文件所有的类都要指定全路径)
		auto-import 默认为true， 在写hql的时候自动导入包名
					如果指定为false, 再写hql的时候必须要写上类的全名；
					  如：session.createQuery("from cn.itcast.c_hbm_config.Employee").list();
	 -->
	<hibernate-mapping package="cn.itcast.c_hbm_config" auto-import="true">
	
	<!-- 
			class 映射某一个对象的(一般情况，一个对象写一个映射文件，即一个class节点)
				name 指定要映射的对象的类型
				table 指定对象对应的表；
					  如果没有指定表名，默认与对象名称一样 
		 -->
		<class name="Employee" table="employee">
		
			<!-- 主键 ，映射-->
			<id name="empId" column="id">
				<!-- 
					主键的生成策略
					identity  自增长(mysql,db2)
						sequence  自增长(序列)， oracle中自增长是以序列方法实现
						native  自增长【会根据底层数据库自增长的方式选择identity或sequence】
								如果是mysql数据库, 采用的自增长方式是identity
								如果是oracle数据库， 使用sequence序列的方式实现自增长
					
						increment  自增长(会有并发访问的问题，一般在服务器集群环境使用会存在问题。)
					
						assigned  指定主键生成策略为手动指定主键的值
						uuid      指定uuid随机生成的唯一的值
						foreign   (外键的方式， one-to-one讲)
				 -->
				<generator class="uuid"/>
			</id>
			<!-- 
				普通字段映射
				property
					name  指定对象的属性名称
					column 指定对象属性对应的表的字段名称，如果不写默认与对象属性一致。
					length 指定字符的长度, 默认为255
					type   指定映射表的字段的类型，如果不指定会匹配属性的类型
						java类型：     必须写全名
						hibernate类型：  直接写类型，都是小写
			-->
			<property name="empName" column="empName" type="java.lang.String" length="20"></property>
			<property name="workDate" type="java.util.Date"></property>
			<!-- 如果列名称为数据库关键字，需要用反引号或改列名。 -->
			<property name="desc" column="`desc`" type="java.lang.String"></property>
		
		</class>


	</hibernate-mapping>

## 21.8 复合主键映射

	// 复合主键类
	public class CompositeKeys implements Serializable{
		private String userName;
		private String address;
	   // .. get/set
	}
	
	public class User {
	
		// 名字跟地址，不会重复
		private CompositeKeys keys;
		private int age;
	}

User.hbm.xml

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.d_compositeKey" auto-import="true">
	
		<class name="User">
		
			<!-- 复合主键映射 -->
			<composite-id name="keys">
				<key-property name="userName" type="string"></key-property>
				<key-property name="address" type="string"></key-property>
			</composite-id>
		
			<property name="age" type="int"></property>	
			</class>


	</hibernate-mapping>

App.java

	public class App2 {
	
		private static SessionFactory sf;
	
		static  {		
			// 创建sf对象
			sf = new Configuration()
				.configure()
				.addClass(User.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
				.buildSessionFactory();
		}
	
		//1. 保存对象
		@Test
		public void testSave() throws Exception {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
		
			// 对象
			CompositeKeys keys = new CompositeKeys();
			keys.setAddress("广州棠东");
			keys.setUserName("Jack");
			User user = new User();
			user.setAge(20);
			user.setKeys(keys);
		
			// 保存
			session.save(user);
		
			tx.commit();
			session.close();
		}
	
		@Test
		public void testGet() throws Exception {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
		
			//构建主键再查询
			CompositeKeys keys = new CompositeKeys();
			keys.setAddress("广州棠东");
			keys.setUserName("Jack");
		
			// 主键查询
			User user = (User) session.get(User.class, keys);
			// 测试输出
		
			if (user != null){
				System.out.println(user.getKeys().getUserName());
				System.out.println(user.getKeys().getAddress());
				System.out.println(user.getAge());
			}


​		
​			tx.commit();
​			session.close();
​		}
​	}

## 21.9 集合映射

	// javabean设计
	public class User {
	
		private int userId;
		private String userName;
		// 一个用户，对应的多个地址
		private Set<String> address;
		private List<String> addressList = new ArrayList<String>(); 
		//private String[] addressArray; // 映射方式和list一样     <array name=""></array>
		private Map<String,String> addressMap = new HashMap<String, String>();
	
	}


​	

```
<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.a_collection">
```

		<class name="User" table="t_user">
			<id name="userId" column="id">
				<generator class="native"></generator>
			</id>	
			<property name="userName"></property>	
			<!-- 
				set集合属性的映射
					name 指定要映射的set集合的属性
					table 集合属性要映射到的表
					key  指定集合表(t_address)的外键字段
					element 指定集合表的其他字段
						type 元素类型，一定要指定
			 -->
			 <set name="address" table="t_address">
			 	<key column="uid"></key>
			 	<element column="address" type="string"></element>
			 </set>
			 
			 <!-- 
			 	list集合映射
			 		list-index  指定的是排序列的名称 (因为要保证list集合的有序)
			  -->
			  <list name="addressList" table="t_addressList">
			  	  <key column="uid"></key>
			  	  <list-index column="idx"></list-index>
			  	  <element column="address" type="string"></element>
			  </list>
			  
			  <!-- 
			  	map集合的映射
			  		key  指定外键字段
			  		map-key 指定map的key 
			  		element  指定map的value
			   -->
			   <map name="addressMap" table="t_addressMap">
			  	<key column="uid"></key>
			  	<map-key column="shortName" type="string" ></map-key>
			  	<element column="address" type="string" ></element>
			  </map>


​			 
​		</class>


	</hibernate-mapping>


​	
​	// 保存set
​		@Test
​		public void testSaveSet() throws Exception {
​			Session session = sf.openSession();
​			session.beginTransaction();
​		
​			//-- 保存
​			Set<String> addressSet = new HashSet<String>();
​			addressSet.add("广州");
​			addressSet.add("深圳");
​			// 用户对象
​			User user = new User();
​			user.setUserName("Jack");
​			user.setAddress(addressSet);
​		
			// 保存
			session.save(user);
		
			session.getTransaction().commit();
			session.close();
		}
	
		// 保存list/map
		@Test
		public void testSaveList() throws Exception {
			Session session = sf.openSession();
			session.beginTransaction();
			User user = new User();
			user.setUserName("Tom");
	//		// 用户对象  --  list
	//		user.getAddressList().add("广州");
	//		user.getAddressList().add("深圳");
	//		// 保存
	//		session.save(user);
		
			// 用户对象  --  Map
			user.getAddressMap().put("A0001", "广州");
			user.getAddressMap().put("A0002", "深圳");
		
			// 保存
			session.save(user);
		
			session.getTransaction().commit();
			session.close();
		}

## 21.10 关联映射

	public class Dept {
	
		private int deptId;
		private String deptName;
		// 【一对多】 部门对应的多个员工
		private Set<Employee> emps = new HashSet<Employee>();
	
	public class Employee {
	
		private int empId;
		private String empName;
		private double salary;
		// 【多对一】员工与部门
		private Dept dept;

Dept.hbm.xml

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.b_one2Many">
	
		<class name="Dept" table="t_dept">
			<id name="deptId">
				<generator class="native"></generator>
			</id>	
			<property name="deptName" length="20"></property>
			<!-- 
				一对多关联映射配置  （通过部门管理到员工）
				Dept 映射关键点：
				1.  指定 映射的集合属性： "emps"
				2.  集合属性对应的集合表： "t_employee"
				3.  集合表的外键字段   "t_employee. dept_id"
				4.  集合元素的类型
			
			 -->
			 <set name="emps">   <!-- table="t_employee" -->
			 	 <key column="dept_id"></key>
			 	 <one-to-many class="Employee"/>
			 </set>
			 </class>


	</hibernate-mapping>

Employee.hbm.xml

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.b_one2Many">
	
		<class name="Employee" table="t_employee">
			<id name="empId">
				<generator class="native"></generator>
			</id>	
			<property name="empName" length="20"></property>
			<property name="salary" type="double"></property>
		
			<!-- 
				多对一映射配置
				Employee 映射关键点：
				1.  映射的部门属性  ：  dept
				2.  映射的部门属性，对应的外键字段: dept_id
				3.  部门的类型
			 -->
			 <many-to-one name="dept" column="dept_id" class="Dept"></many-to-one>
			 
		</class>


	</hibernate-mapping>

测试

	public class App {
	
		private static SessionFactory sf;
		static {
			sf = new Configuration()
				.configure()
				.addClass(Dept.class)   
				.addClass(Employee.class)   // 测试时候使用
				.buildSessionFactory();
		}
	
		// 保存， 部门方 【一的一方法操作】
		@Test
		public void save() {
		
			Session session = sf.openSession();
			session.beginTransaction();
		
			// 部门对象
			Dept dept = new Dept();
			dept.setDeptName("应用开发部");
			// 员工对象
			Employee emp_zs = new Employee();
			emp_zs.setEmpName("张三");
			Employee emp_ls = new Employee();
			emp_ls.setEmpName("李四");
			// 关系
			dept.getEmps().add(emp_zs);
			dept.getEmps().add(emp_ls);
			// 保存
			session.save(emp_zs);
			session.save(emp_ls);
			session.save(dept); // 保存部门，部门下所有的员工  
		
			session.getTransaction().commit();
			session.close();
			/*
			 *  结果
			 *  Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
				Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
				Hibernate: insert into t_dept (deptName) values (?)
				Hibernate: update t_employee set deptId=? where empId=?    维护员工引用的部门的id
				Hibernate: update t_employee set deptId=? where empId=?
			 */
		}
	
		// 【推荐】 保存， 部员方 【多的一方法操作】
		
		@Test
		public void save2() {
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		// 部门对象
		Dept dept = new Dept();
		dept.setDeptName("综合部");
		// 员工对象
		Employee emp_zs = new Employee();
		emp_zs.setEmpName("张三");
		Employee emp_ls = new Employee();
		emp_ls.setEmpName("李四");
		// 关系
		emp_zs.setDept(dept);
		emp_ls.setDept(dept);


​		
​		// 保存
​		session.save(dept); // 先保存一的方法
​		session.save(emp_zs);
​		session.save(emp_ls);// 再保存多的一方，关系回自动维护(映射配置完)
​		
​		session.getTransaction().commit();
​		session.close();
​		/*
​		 *  结果
​		 *  Hibernate: insert into t_dept (deptName) values (?)
​			Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
​			Hibernate: insert into t_employee (empName, salary, dept_id) values (?, ?, ?)
​			少生成2条update  sql
​		 */
​		 	}
​	
	}

## 21.11 Inverse属性

Inverse属性，是在维护关联关系的时候起作用的。表示控制权是否转移。(在一的一方起作用)
nverse , 控制反转。
Inverse = false  不反转；   当前方有控制权
		&emsp; &emsp; &emsp; &emsp; True  控制反转； 当前方没有控制权

维护关联关系中，是否设置inverse属性：
1. 保存数据：有影响。
如果设置控制反转,即inverse=true, 然后通过部门方维护关联关系。在保存部门的时候，同时保存员工， 数据会保存，但关联关系不会维护。即外键字段为NULL

2. 获取数据：无

3. 解除关联关系：有影响
inverse=false，  可以解除关联
inverse=true，  当前方(部门)没有控制权，不能解除关联关系
(不会生成update语句,也不会报错)

4. 删除数据对关联关系的影响：有影响。
inverse=false, 有控制权， 可以删除。先清空外键引用，再删除数据。
inverse=true,  没有控制权: 如果删除的记录有被外键引用，会报错，违反主外键引用约束！  如果删除的记录没有被引用，可以直接删除。

## 21.12 cascade 属性

cascade  表示级联操作  【可以设置到一的一方或多的一方】

	none          不级联操作， 默认值
	save-update     级联保存或更新
	delete		  级联删除
	save-update,delete    级联保存、更新、删除
	all                 同上。级联保存、更新、删除

## 21.13 多对多映射

	/**
	 * 开发人员
	 */
	public class Developer {
		private int d_id;
		private String d_name;
		// 开发人员，参数的多个项目
		private Set<Project> projects = new HashSet<Project>();
	}
	
	/**
	 * 项目
	 */
	public class Project {
		private int prj_id;
		private String prj_name;
		// 项目下的多个员工
		private Set<Developer> developers = new HashSet<Developer>();

Project.hbm.xml

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.c_many2many">
	
		<class name="Project" table="t_project">
			<id name="prj_id">
				<generator class="native"></generator>
			</id>	
			<property name="prj_name" length="20"></property>
			<!-- 
				多对多映射:
				1.  映射的集合属性： “developers”
				2.  集合属性，对应的中间表： “t_relation”
				3. 外键字段:  prjId
				4. 外键字段，对应的中间表字段:  did
				5.   集合属性元素的类型
			 -->
			 <set name="developers" table="t_relation" cascade="save-update">
			 	<key column="prjId"></key>
			 	<many-to-many column="did" class="Developer"></many-to-many>
			 </set>
			 
		</class>


	</hibernate-mapping>

Developer.hbm.xml

	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.c_many2many">
	
		<class name="Developer" table="t_developer">
			<id name="d_id">
				<generator class="native"></generator>
			</id>	
			<property name="d_name" length="20"></property>
		
			<!-- 
				多对多映射配置： 员工方
					name  指定映射的集合属性
					table 集合属性对应的中间表
					key   指定中间表的外键字段(引用当前表t_developer主键的外键字段)
					many-to-many
						column 指定外键字段对应的项目字段
						class  集合元素的类型
			 -->
			<set name="projects" table="t_relation">
				<key column="did"></key>
				<many-to-many column="prjId" class="Project"></many-to-many>
				</set>


​			 
​		</class>


	</hibernate-mapping>

维护关联关系

	设置inverse属性，在多对多种维护关联关系的影响？
	1） 保存数据
	有影响。
		 inverse=false ，有控制权，可以维护关联关系； 保存数据的时候会把对象关系插入中间表；
		inverse=true,  没有控制权， 不会往中间表插入数据。
	2） 获取数据
		无。
	
	3） 解除关系
		// 有影响。
		// inverse=false ,有控制权， 解除关系就是删除中间表的数据。
		// inverse=true, 没有控制权，不能解除关系。
	4） 删除数据
		有影响。
		// inverse=false, 有控制权。 先删除中间表数据，再删除自身。
		// inverse=true, 没有控制权。 如果删除的数据有被引用，会报错！ 否则，才可以删除

## 21.14 对象的状态

Hibernate中对象的状态： 临时/瞬时状态、持久化状态、游离状态。
1. 临时状态:直接new出来的对象;  不处于session的管理;数据库中没有对象的记录;
2. 持久化状态:当调用session的save/saveOrUpdate/get/load/list等方法的时候，对象就是持久化状态。处于持久化状态的对象，当对对象属性进行更改的时候，会反映到数据库中!处于session的管理;数据库中有对应的记录;
3. 游离状态:不处于session的管理；数据库中有对应的记录;Session关闭后，对象的状态；

## 21.15 一级缓存

为什么要用缓存？
	&emsp;目的：减少对数据库的访问次数！从而提升hibernate的执行效率！
	
1）Hibenate中一级缓存，也叫做session的缓存，它可以在session范围内减少数据库的访问次数！  只在session范围有效！ Session关闭，一级缓存失效！
2）当调用session的save/saveOrUpdate/get/load/list/iterator方法的时候，都会把对象放入session的缓存中。 
3）Session的缓存由hibernate维护， 用户不能操作缓存内容； 如果想操作缓存内容，必须通过hibernate提供的evit/clear方法操作。
特点：
	只在(当前)session范围有效，作用时间短，效果不是特别明显！
	在短时间内多次操作数据库，效果比较明显！
	
	缓存相关几个方法的作用
	1. session.flush();       让一级缓存与数据库同步
	2. session.evict(arg0);    清空一级缓存中指定的对象
	3. session.clear();       清空一级缓存中缓存的所有对象
	
	在什么情况用上面方法？
			批量操作使用使用：
				 Session.flush();   // 先与数据库同步
				 Session.clear();   // 再清空一级缓存内容
			 
	面试题1： 不同的session是否会共享缓存数据?
		不会。
		User1  u1 = Session1.get(User.class,1);   把u1对象放入session1的缓存
		Session2.update(u1);     把u1放入session2的缓存
		U1.setName(‘new Name’);
		如果生成2条update sql， 说明不同的session使用不同的缓存区，不能共享。
	
	面试题2： list与iterator查询的区别？
		list() 
			一次把所有的记录都查询出来，
			会放入缓存，但不会从缓存中获取数据
		Iterator
			N+1查询； N表示所有的记录总数
			即会先发送一条语句查询所有记录的主键（1），
			再根据每一个主键再去数据库查询（N）！
			会放入缓存，也会从缓存中取数据！

## 21.16 懒加载

	面试题3： get、load方法区别？
		get: 及时加载，只要调用get方法立刻向数据库查询
		load:默认使用懒加载，当用到数据的时候才向数据库查询。
	
	懒加载：(lazy)
	概念：当用到数据的时候才向数据库查询，这就是hibernate的懒加载特性。
			目的：提供程序执行效率！
	lazy 值
		true   使用懒加载
		false   关闭懒加载
		extra   (在集合数据懒加载时候提升效率)
	在真正使用数据的时候才向数据库发送查询的sql；
	如果调用集合的size()/isEmpty()方法，只是统计，不真正查询数据！ 
	
		懒加载异常
		Session关闭后，不能使用懒加载数据！
		如果session关闭后，使用懒加载数据报错：
		org.hibernate.LazyInitializationException: could not initialize proxy - no Session
			如何解决session关闭后不能使用懒加载数据的问题？
				 // 方式1： 先使用一下数据
			//dept.getDeptName();
			// 方式2：强迫代理对象初始化
			Hibernate.initialize(dept);
			// 方式3：关闭懒加载
				设置lazy=false;
			// 方式4： 在使用数据之后，再关闭session！ 

基于外键的映射

	// 身份证
	public class IdCard {
	
		// 身份证号(主键)
		private String cardNum;// 对象唯一表示(Object Identified, OID)
		private String place; //  身份证地址
		// 身份证与用户，一对一的关系
		private User user;	
		
	// 用户
	public class User {
	
		private int userId;
		private String userName;
		// 用户与身份证信息， 一对一关系
		private IdCard idCard;		


​			
​	

```
<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
```


​	

	<hibernate-mapping package="cn.itcast.c_one2one">
	
		<class name="IdCard" table="t_IdCard">
			<id name="cardNum">
				<generator class="assigned"></generator>
			</id>	
			<property name="place" length="20"></property>	
			<!-- 
				一对一映射，有外键方
				unique="true"   给外键字段添加唯一约束
			 -->
			 <many-to-one name="user" unique="true" column="user_id" class="User" cascade="save-update"></many-to-one>
			
		</class>	
		</hibernate-mapping>	


​			
​	<?xml version="1.0"?>
​	<!DOCTYPE hibernate-mapping PUBLIC 
​		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
​		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
​	
​	<hibernate-mapping package="cn.itcast.c_one2one">
​	
		<class name="User" table="t_user">
			<id name="userId">
				<generator class="native"></generator>
			</id>	
			<property name="userName" length="20"></property>
			<!-- 
				一对一映射： 没有外键方
			 -->	
			 <one-to-one name="idCard" class="IdCard"></one-to-one>
				 
		</class>


	</hibernate-mapping>		


​			
基于主键的映射		

	// 身份证
	public class IdCard {
	
		private int user_id;
		// 身份证号
		private String cardNum;
		private String place; //  身份证地址
		// 身份证与用户，一对一的关系
		private User user;	
			
	<?xml version="1.0"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
		"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
	
	<hibernate-mapping package="cn.itcast.c_one2one2">
	
		<class name="IdCard" table="t_IdCard">
			<id name="user_id">
				<!-- 
					id 节点指定的是主键映射, 即user_id是主键
					主键生成方式： foreign  即把别的表的主键作为当前表的主键；
							property (关键字不能修改)指定引用的对象     对象的全名 cn..User、  对象映射 cn.User.hbm.xml、   table(id)
				 -->		
				 <generator class="foreign">
					<param name="property">user</param>
				</generator>
			</id>	
			<property name="cardNum" length="20"></property>
			<property name="place" length="20"></property>
		
			<!-- 
				一对一映射，有外键方
				（基于主键的映射）
				 constrained="true"  指定在主键上添加外键约束
			 -->
			<one-to-one name="user" class="User" constrained="true" 	
			cascade="save-update"></one-to-one>
			
		</class>


	</hibernate-mapping>	


​	
## 21.17 组件映射

类组合关系的映射，也叫做组件映射！
注意：组件类和被包含的组件类，共同映射到一张表！			
			
	public class Car {
	
		private int id;
		private String name;
		// 车轮
		private Wheel wheel;
	}	
			
	// 车轮
	public class Wheel {
	
		private int count;
		private int size;
	}	
	
	<hibernate-mapping package="cn.itcast.d_component">
	
		<class name="Car" table="t_car">	
		<id name="id">
				<generator class="native"></generator>
			</id>	
			<property name="name" length="20"></property>
		
			<!-- 组件映射 -->
			<component name="wheel">
				<property name="size"></property>
				<property name="count"></property>
			</component>


​						 
​		</class>	
​		</hibernate-mapping>

## 21.18 继承映射

	// 动物类
	public abstract class Animal {
	
		private int id;
		private String name;
		
	<!-- 
		简单继承
	 -->
	<hibernate-mapping package="cn.itcast.e_extends1">
	<class name="Cat" table="t_Cat">
			<!-- 简单继承映射： 父类属性直接写 -->
			<id name="id">
				<generator class="native"></generator>
			</id>
			<property name="na"></property>
			<property name="catchMouse"></property>					 
		</class>


	</hibernate-mapping>
	
	@Test
		public void getSave() {
		
			Session session = sf.openSession();
			session.beginTransaction();
		
			// 保存
	//		Cat cat = new Cat();
	//		cat.setName("大花猫");
	//		cat.setCatchMouse("抓小老鼠");
	//		session.save(cat);
		
			// 获取时候注意：当写hql查询的使用，通过父类查询必须写上类的全名
			Query q = session.createQuery("from cn.itcast.e_extends1.Animal");
			List<Animal> list = q.list();
			System.out.println(list);
			session.getTransaction().commit();
			session.close();
		
		}

## 21.19 HQL查询

	public class App {
		private static SessionFactory sf;
		static {
			sf = new Configuration()
				.configure()
				.addClass(Dept.class)   
				.addClass(Employee.class)   // 测试时候使用
				.buildSessionFactory();
		}
	
		/*
		 * 1)	Get/load主键查询
			2)	对象导航查询
			3)	HQL查询，  Hibernate Query language  hibernate 提供的面向对象的查询语言。
			4)	Criteria 查询，   完全面向对象的查询（Query By Criteria  ,QBC）
			5)	SQLQuery， 本地SQL查询
	
		 */
		 
		 @Test
		public void all() {
		
			Session session = sf.openSession();
			session.beginTransaction();
		
			//1) 主键查询
	//		Dept dept =  (Dept) session.get(Dept.class, 12);
	//		Dept dept =  (Dept) session.load(Dept.class, 12);
		
			//2) 对象导航查询
	//		Dept dept =  (Dept) session.get(Dept.class, 12);
	//		System.out.println(dept.getDeptName());
	//		System.out.println(dept.getEmps());
	// 3)	HQL查询
			// 注意：使用hql查询的时候 auto-import="true" 要设置true，
			//  如果是false，写hql的时候，要指定类的全名
	//		Query q = session.createQuery("from Dept");
	//		System.out.println(q.list());
		
			// a. 查询全部列
	//		Query q = session.createQuery("from Dept");  //OK
	//		Query q = session.createQuery("select * from Dept");  //NOK, 错误，不
	
	支持*
	//		Query q = session.createQuery("select d from Dept d");  // OK
	//		System.out.println(q.list());
	
			// b. 查询指定的列  【返回对象数据Object[] 】
	//		Query q = session.createQuery("select d.deptId,d.deptName from Dept d");  
	//		System.out.println(q.list());
		
			// c. 查询指定的列, 自动封装为对象  【必须要提供带参数构造器】
	//		Query q = session.createQuery("select new Dept(d.deptId,d.deptName) from Dept d");  
	//		System.out.println(q.list());
	
	// d. 条件查询: 一个条件/多个条件and or/between and/模糊查询
			// 条件查询： 占位符
	//		Query q = session.createQuery("from Dept d where deptName=?");
	//		q.setString(0, "财务部");
	//		q.setParameter(0, "财务部");
	//		System.out.println(q.list());
		
			// 条件查询： 命名参数
	//		Query q = session.createQuery("from Dept d where deptId=:myId or deptName=:name");
	//		q.setParameter("myId", 12);
	//		q.setParameter("name", "财务部");
	//		System.out.println(q.list());
	// 范围
	//		Query q = session.createQuery("from Dept d where deptId between ? and ?");
	//		q.setParameter(0, 1);
	//		q.setParameter(1, 20);
	//		System.out.println(q.list());
		
			// 模糊
	//		Query q = session.createQuery("from Dept d where deptName like ?");
	//		q.setString(0, "%部%");
	//		System.out.println(q.list());


			// e. 聚合函数统计
	//		Query q = session.createQuery("select count(*) from Dept");
	//		Long num = (Long) q.uniqueResult();
	//		System.out.println(num);
		
			// f. 分组查询
			//-- 统计t_employee表中，每个部门的人数
			//数据库写法：SELECT dept_id,COUNT(*) FROM t_employee GROUP BY dept_id;
			// HQL写法
	//		Query q = session.createQuery("select e.dept, count(*) from Employee e group by e.dept");
	//		System.out.println(q.list());
	
	session.getTransaction().commit();
			session.close();
		}
	
		// g. 连接查询
		@Test
		public void join() {
		
			Session session = sf.openSession();
			session.beginTransaction();
		
			//1) 内连接   【映射已经配置好了关系，关联的时候，直接写对象的属性即可】
	//		Query q = session.createQuery("from Dept d inner join d.emps");
		
			//2) 左外连接
	//		Query q = session.createQuery("from Dept d left join d.emps");
	
			//3) 右外连接
			Query q = session.createQuery("from Employee e right join e.dept");
			q.list();
		
			session.getTransaction().commit();
			session.close();
		}
	
		// g. 连接查询 - 迫切连接
		@Test
		public void fetch() {
		Session session = sf.openSession();
			session.beginTransaction();
		
			//1) 迫切内连接    【使用fetch, 会把右表的数据，填充到左表对象中！】
	//		Query q = session.createQuery("from Dept d inner join fetch d.emps");
	//		q.list();
		
			//2) 迫切左外连接
			Query q = session.createQuery("from Dept d left join fetch d.emps");
			q.list();
		
			session.getTransaction().commit();
			session.close();
		}
	
		// HQL查询优化
		@Test
		public void hql_other() {
			Session session = sf.openSession();
			session.beginTransaction();
			// HQL写死
	//		Query q = session.createQuery("from Dept d where deptId < 10 ");
		
			// HQL 放到映射文件中
			Query q = session.getNamedQuery("getAllDept");
			q.setParameter(0, 10);
			System.out.println(q.list());
		
			session.getTransaction().commit();
			session.close();
		}
	}

## 21.20 Criteria 查询

	//4)	Criteria 查询，
		@Test
		public void criteria() {
		Session session = sf.openSession();
			session.beginTransaction();
		
			Criteria criteria = session.createCriteria(Employee.class);
			// 构建条件
			criteria.add(Restrictions.eq("empId", 12));
	//		criteria.add(Restrictions.idEq(12));  // 主键查询
		
			System.out.println(criteria.list());


​		
​			session.getTransaction().commit();
​			session.close();
​		}

## 21.21 SQLQuery， 本地SQL查询

	// 5)	SQLQuery， 本地SQL查询
		// 不能跨数据库平台： 如果该了数据库，sql语句有肯能要改。
		@Test
		public void sql() {
		
			Session session = sf.openSession();
			session.beginTransaction();
		
			SQLQuery q = session.createSQLQuery("SELECT * FROM t_Dept limit 5;")
				.addEntity(Dept.class);  // 也可以自动封装
			System.out.println(q.list());
		
			session.getTransaction().commit();
			session.close();
		}


​		
​	// 分页查询
​		@Test
​		public void all() {
​		
​			Session session = sf.openSession();
​			session.beginTransaction();
​		
			 Query q = session.createQuery("from Employee");
			 
			 // 从记录数
			 ScrollableResults scroll = q.scroll();  // 得到滚动的结果集
			 scroll.last();							//  滚动到最后一行
			 int totalCount = scroll.getRowNumber() + 1;// 得到滚到的记录数，即总记录数
			 
			 // 设置分页参数
			 q.setFirstResult(0);
			 q.setMaxResults(3);
			 
			 // 查询
			 System.out.println(q.list());
			 System.out.println("总记录数：" + totalCount);
		
			session.getTransaction().commit();
			session.close();
		}

## 21.22 hibernate对连接池的支持

Hibernate 自带的也有一个连接池，且对C3P0连接池也有支持！
Hbm 自带连接池：只维护一个连接，比较简陋。可以查看hibernate.properties文件查看连接池详细配置:

	#################################
	### Hibernate Connection Pool ###     
	#################################
	
	hibernate.connection.pool_size 1        【Hbm 自带连接池： 只有一个连接】



	###########################
	### C3P0 Connection Pool###		   【Hbm对C3P0连接池支持】
	###########################
	
	#hibernate.c3p0.max_size 2				最大连接数
	#hibernate.c3p0.min_size 2				最小连接数
	#hibernate.c3p0.timeout 5000           超时时间
	#hibernate.c3p0.max_statements 100     最大执行的命令的个数
	#hibernate.c3p0.idle_test_period 3000    空闲测试时间
	#hibernate.c3p0.acquire_increment 2     连接不够用的时候， 每次增加的连接数
	#hibernate.c3p0.validate false

Hibernate.cfg.xml 中增加连接池相关配置：

	<!-- 【连接池配置】 -->
			<!-- 配置连接驱动管理类 -->
			<property name="hibernate.connection.provider_class">org.hibernate.connection.C3P0ConnectionProvider</property>
			<!-- 配置连接池参数信息 -->
			<property name="hibernate.c3p0.min_size">2</property>
			<property name="hibernate.c3p0.max_size">4</property>
			<property name="hibernate.c3p0.timeout">5000</property>
			<property name="hibernate.c3p0.max_statements">10</property>
			<property name="hibernate.c3p0.idle_test_period">30000</property>
			<property name="hibernate.c3p0.acquire_increment">2</property>

## 21.23 二级缓存

二级缓存：

		Hibernate提供了基于应用程序级别的缓存， 可以跨多个session，即不同的session都可以访问缓存数据。 这个换存也叫二级缓存。
		Hibernate提供的二级缓存有默认的实现，且是一种可插配的缓存框架！如果用户想用二级缓存，只需要在hibernate.cfg.xml中配置即可； 不想用，直接移除，不影响代码。
		如果用户觉得hibernate提供的框架不好用，自己可以换其他的缓存框架或自己实现缓存框架都可以。

使用二级缓存

	查看hibernate.properties配置文件，二级缓存如何配置？
	
	##########################
	### Second-level Cache ###
	##########################
	
	#hibernate.cache.use_second_level_cache false【二级缓存默认不开启，需要手动开启】
	#hibernate.cache.use_query_cache true      【开启查询缓存】
	
	## choose a cache implementation		【二级缓存框架的实现】
	
	#hibernate.cache.provider_class org.hibernate.cache.EhCacheProvider
	
		#hibernate.cache.provider_class org.hibernate.cache.EmptyCacheProvider
	hibernate.cache.provider_class org.hibernate.cache.HashtableCacheProvider 默认实现
	#hibernate.cache.provider_class org.hibernate.cache.TreeCacheProvider
	#hibernate.cache.provider_class org.hibernate.cache.OSCacheProvider
	#hibernate.cache.provider_class org.hibernate.cache.SwarmCacheProvider

缓存策略

	<class-cache usage="read-only"/>     放入二级缓存的对象，只读; 
		<class-cache usage="nonstrict-read-write"/>  非严格的读写
		<class-cache usage="read-write"/>    读写； 放入二级缓存的对象可以读、写；
		<class-cache usage="transactional"/>   (基于事务的策略)

集合缓存

	<!-- 集合缓存[集合缓存的元素对象，也加加入二级缓存] -->
			<collection-cache 
	usage="read-write" collection="cn.itcast.b_second_cache.Dept.emps"/>

查询缓存

	list() 默认情况只会放入缓存，不会从一级缓存中取！
	   使用查询缓存，可以让list()查询从二级缓存中取！

完整案例：
Hibernate.cfg.xml

	<!--****************** 【二级缓存配置】****************** -->
			<!-- a.  开启二级缓存 -->
			<property name="hibernate.cache.use_second_level_cache">true</property>
			<!-- b. 指定使用哪一个缓存框架(默认提供的) -->
			<property name="hibernate.cache.provider_class">org.hibernate.cache.HashtableCacheProvider</property>
			<!-- 开启查询缓存 -->
			<property name="hibernate.cache.use_query_cache">true</property>
			<!-- c. 指定哪一些类，需要加入二级缓存 -->
			<class-cache usage="read-write" class="cn.itcast.b_second_cache.Dept"/>
			<class-cache usage="read-only" 
			class="cn.itcast.b_second_cache.Employee"/>
			<!-- 集合缓存[集合缓存的元素对象，也加加入二级缓存] -->
			<collection-cache usage="read-write" collection="cn.itcast.b_second_cache.Dept.emps"/>

App  测试类

	public class App {
	
		private static SessionFactory sf;
		static {
			sf = new Configuration()
				.configure()
				.addClass(Dept.class)   
				.addClass(Employee.class)   // 测试时候使用
				.buildSessionFactory();
		}
		// 1. 测试二级缓存的使用
		// 没有/有用 二级缓存
		@Test
		public void testCache() {
			Session session1 = sf.openSession();
			session1.beginTransaction();
			// a. 查询一次
			Dept dept = (Dept) session1.get(Dept.class, 10);
			dept.getEmps().size();// 集合
			session1.getTransaction().commit();
			session1.close();
		
			System.out.println("------");
		
			// 第二个session
			Session session2 = sf.openSession();
			session2.beginTransaction();
			// a. 查询一次
			dept = (Dept) session2.get(Dept.class, 10);  // 二级缓存配置好； 这里不查询数据库
			dept.getEmps().size();
		
			session2.getTransaction().commit();
			session2.close();
		}
		
		@Test
		public void listCache() {
			Session session1 = sf.openSession();
			session1.beginTransaction();
			// HQL查询  【setCacheable  指定从二级缓存找，或者是放入二级缓存】
			Query q = session1.createQuery("from Dept").setCacheable(true);
			System.out.println(q.list());
			session1.getTransaction().commit();
			session1.close();


​		
​			Session session2 = sf.openSession();
​			session2.beginTransaction();
​			q = session2.createQuery("from Dept").setCacheable(true);
​			System.out.println(q.list());  // 不查询数据库： 需要开启查询缓存
​			session2.getTransaction().commit();
​			session2.close();
​		}
​	}

## 21.24 项目中session的管理方式
Session的创建方式：

	@Test
		public void testSession() throws Exception {
			//openSession:  创建Session, 每次都会创建一个新的session
			Session session1 = sf.openSession();
			Session session2 = sf.openSession();
			System.out.println(session1 == session2);
			session1.close();
			session2.close();
		
			//getCurrentSession 创建或者获取session
			// 线程的方式创建session  
			// 一定要配置：<property name="hibernate.current_session_context_class">thread</property>
			Session session3 = sf.getCurrentSession();// 创建session，绑定到线程
			Session session4 = sf.getCurrentSession();// 从当前访问线程获取session
			System.out.println(session3 == session4);
			// 关闭 【以线程方式创建的session，可以不用关闭； 线程结束session自动关闭】
			//session3.close();
			//session4.close(); 报错，因为同一个session已经关闭了！
		}


​	