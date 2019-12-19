#  SpringBoot

## 1 创建项目工程

### 1.1 通过压缩包创建

网址为：http://start.spring.io。Dependencies选择：Web, AOP, 模板引擎(Freemarker)(Velocity)。选择web：会自动添加Tomcat和Spring MVC的依赖。
然后在Intellij idea中设置import project(导入java项目)	。File -> new -> module from existing sources。然后点击pom.xml 点击next和finish即可 
			

### 1.2 使用Intellij idea直接创建Spring Boot项目

新建Spring Initializr项目
填写信息和需要的模块

### 1.3 使用Maven新建项目

新建项目，选择Maven
输入GroupId和AtrifaceId
选择存储项目路径，创建新项目，此时会生成pom.xml，即Maven的项目对象模型，并生成了源代码目录java，资源目录resources和测试目录test。
