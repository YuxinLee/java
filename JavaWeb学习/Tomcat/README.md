# 第一章 Tomcat
<br>
## 1.1 web 简单介绍
C/S (Client - Server  客户端-服务器端)    
&nbsp; &nbsp;&nbsp;&nbsp;	特点:   
&nbsp; &nbsp;&nbsp;&nbsp;		(1) 必须下载特定的客户端程序。   
&nbsp; &nbsp;&nbsp;&nbsp;		(2) 服务器端升级,客户端升级。

B/S (Broswer -Server 浏览器端- 服务器端)  
&nbsp; &nbsp;&nbsp;&nbsp;	特点:   
&nbsp; &nbsp;&nbsp;&nbsp;		(1) 不需要安装特定的客户端(只需要安装浏览器即可)   
&nbsp; &nbsp;&nbsp;&nbsp;		(2) 服务器端升级,浏览器不需要升级


<br>
## 1.2 Tomcat 基本使用
### 1.2.1 运行 tomcat
&nbsp; &nbsp; a) 找到%tomcat%/bin/startup.bat， 双击这个文件   
&nbsp; &nbsp; b) 弹出窗口，显示信息(不要关闭此窗口)    
&nbsp; &nbsp; c) 打开浏览器，输入地址(http://localhost.8080)   
&nbsp; &nbsp; d) 看到一只猫，证明软件启动成功

### 1.2.2 关闭 tomcat
&nbsp; &nbsp; a) 找到%tomcat%/bin/shutdown.bat， 双击这个文件    
&nbsp; &nbsp; b) 打开浏览器，输出以下地址。看到“无法连接”(最好先清空浏览器缓存)

### 1.2.3 tomcat 软件使用的常见问题
&nbsp; &nbsp; a) 闪退问题  
&nbsp; &nbsp; 原因：tomcat软件是java语言开发的。 tomcat软件启动时，会默认到系统的环境变量中查找一个名称叫JAVA\_HOME的变量。这个变量的作用找到tomcat启动所需的jvm。   
&nbsp; &nbsp;解决办法； 到环境变量中设置JAVA\_HOME的变量 JAVA_HOME= C:\Program Files\Java\jdk1.6.0_30  (注意别配置到bin目录下)   
<br>
&nbsp; &nbsp; b) 端口占用的错误
&nbsp; &nbsp;原因： tomcat启动所需的端口被其他软件占用了   
&nbsp; &nbsp;解决办法： (1) 关闭其他软件程序，释放所需端口;    
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; (2) 修改tomcat软件所需端口，找到并修改%tomcat%/conf/server.xml文件中的<Connector port\>   
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; (3) CATALINA环境变量问题， tomcat软件启动后，除了查找JAVA\_HOME后，还会再查找一个叫CATALINA_HOME变量，这个变量的作用是设置tomcat的根目录。建议不要设置CATALINA\_HOME变量。检查如果有的话，清除掉

### 1.2.4 tomcat 文件共享    
&emsp;&emsp; webapps目录： tomcat共享目录。需要共享的本地资源放到此目录中。注意：在webapps目录中首先要创建文件夹，然后在文件夹内放入共享的文件。然后通过http://localhost:8081/myweb/test.html访问，此时myweb为创建的文件夹，test.html为共享的文件。如果是其他的计算机需要访问共享文件，将localhost转为服务器的IP地址。

### 1.2.5 tomcat 的目录结构
&emsp;&emsp; |-bin: 存放tomcat的命令。        
&emsp;&emsp; |- conf: 存放tomcat的配置信息。其中server.xml文件是核心的配置文件。    
&emsp;&emsp; |-lib：支持tomcat软件运行的jar包。其中还有技术支持包，如servlet，jsp    
&emsp;&emsp; |-logs：运行过程的日志信息    
&emsp;&emsp; |-temp: 临时目录    
&emsp;&emsp; |-webapps： 共享资源目录。web应用目录。（注意不能以单独的文件进行共享）   
&emsp;&emsp; |-work： tomcat的运行目录。jsp运行时产生的临时文件就存放在这里

<br>   

## 1.3 开发动态资源
&emsp;&emsp; Servlet : 用java语言来编写动态资源的开发技术。   
&emsp;&emsp; Servlet特点：   
&emsp;&emsp; 1）普通的java类，继承HttpServlet类，覆盖doGet方法    
&emsp;&emsp; 2）Servlet类只能交给tomcat服务器运行！！！！（开发者自己不能运行！！！）

Servlet手动编写步骤：   
&emsp;&emsp; 1）编写一个servlet程序，继承HttpServlet    
&emsp;&emsp; 2）找到HelloServlet类的class字节码，然后把拷贝到tomcat的一个web应用中WEB-INF/classes目录下。   
&emsp;&emsp; 3）在当前web应用下的web.xml文件配置Servlet。   
&emsp;&emsp; 4）启动tomcat服务器，运行访问

## 1.4 idea开发servlet
&emsp;&emsp; 1. 创建web项目， 添加Tomcat   
&emsp;&emsp; 2. 编写servlet文件    
&emsp;&emsp; 3. 部署servlet文件   
&emsp;&emsp;&emsp;&emsp; (1) 在web.xml文件的<web-app>标签中添加如下内容：   
&emsp;&emsp;&emsp;&emsp; <servlet\>  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;    <servlet-name>HelloWorld</servlet-name>  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;   <servlet-class>HelloWorld</servlet-class>  
&emsp;&emsp;&emsp;&emsp; </servlet\>  
  
&emsp;&emsp;&emsp;&emsp; <servlet-mapping>  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;    <servlet-name>HelloWorld</servlet-name>  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;    <url-pattern>/HelloWorld</url-pattern>  
&emsp;&emsp;&emsp;&emsp; </servlet-mapping> 

&emsp;&emsp;&emsp;&emsp; (2) 在HelloWorld文件的类前面加上：@WebServlet(value = "/HelloWorld")  

&emsp;&emsp; 4. 运行servlet文件（注意：在编辑Tomcat时，server中的url和Deployment要设置） 

<br>

# 第二章 HTTP协议	- Request
http协议： 对浏览器客户端和服务器端之间数据传输的格式规范    
http请求：请求行、请求头和实体内容组成

## 2.1 请求行
GET /day09/hello HTTP/1.1 

### 2.1.1 http协议版本
http1.0：当前浏览器客户端与服务器端建立连接之后，只能发送一次请求，一次请求之后连接关闭。   
http1.1：当前浏览器客户端与服务器端建立连接之后，可以在一次连接中发送多次请求。（基本都使用1.1）

### 2.1.2 请求资源
URL:  统一资源定位符。http://localhost:8080/day09/testImg.html。只能定位互联网资源。是URI的子集。   
URI： 统一资源标记符。/day09/hello。用于标记任何资源。可以是本地文件系统，局域网的资源（//192.168.14.10/myweb/index.html），可以是互联网。

### 2.1.3 请求方式
常见的请求方式： GET 、 POST、 HEAD、 TRACE、 PUT、 CONNECT 、DELETE	   
常用的请求方式： GET和POST


### 2.1.4 GET和POST的区别
&emsp;&emsp;GET：   
&emsp;&emsp; 1）地址栏（URI）会跟上参数数据。以？开头，多个参数之间以&分割。   
&emsp;&emsp; 2）GET提交参数数据有限制，不超过1KB。   
&emsp;&emsp; 3）GET方式不适合提交敏感密码。   
&emsp;&emsp; 4）注意： 浏览器直接访问的请求，默认提交方式是GET方式 
 
&emsp;&emsp; GET /day09/testMethod.html?name=eric&password=123456 HTTP/1.1 
  
&emsp;&emsp; Host: localhost:8080    
&emsp;&emsp; User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0    
&emsp;&emsp; Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8   
&emsp;&emsp; Accept-Language: zh-cn,en-us;q=0.8,zh;q=0.5,en;q=0.3   
&emsp;&emsp; Accept-Encoding: gzip, deflate   
&emsp;&emsp; Referer: http://localhost:8080/day09/testMethod.html   
&emsp;&emsp; Connection: keep-alive   

&emsp;&emsp;POST：         
&emsp;&emsp; 1） 参数不会跟着URI后面。参数而是跟在请求的实体内容中。没有？开头，多个参数之间以&分割。    
&emsp;&emsp; 2） POST提交的参数数据没有限制。   
&emsp;&emsp; 3） POST方式提交敏感数据。   

&emsp;&emsp; POST /day09/testMethod.html HTTP/1.1 
  
&emsp;&emsp; Host: localhost:8080    
&emsp;&emsp; User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0    
&emsp;&emsp; Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8    
&emsp;&emsp; Accept-Language: zh-cn,en-us;q=0.8,zh;q=0.5,en;q=0.3    
&emsp;&emsp; Accept-Encoding: gzip, deflate    
&emsp;&emsp; Referer: http://localhost:8080/day09/testMethod.html    
&emsp;&emsp; Connection: keep-alive     

&emsp;&emsp; name=eric&password=123456  

## 2.2 请求头 
&emsp;&emsp; Accept: text/html,image/*      -- 浏览器接受的数据类型   
&emsp;&emsp; Accept-Charset: ISO-8859-1     -- 浏览器接受的编码格式   
&emsp;&emsp; Accept-Encoding: gzip,compress  --浏览器接受的数据压缩格式   
&emsp;&emsp; Accept-Language: en-us,zh-       --浏览器接受的语言   
&emsp;&emsp; Host: www.it315.org:80          --（必须的）当前请求访问的目标地址（主机:端口）   
&emsp;&emsp; If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT  --浏览器最后的缓存时间   
&emsp;&emsp; Referer: http://www.it315.org/index.jsp      -- 当前请求来自于哪里   
&emsp;&emsp; User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)  --浏览器类型   
&emsp;&emsp; Cookie:name=eric                     -- 浏览器保存的cookie信息   
&emsp;&emsp; Connection: close/Keep-Alive            -- 浏览器跟服务器连接状态。close: 连接关闭  keep-alive：保存连接。   
&emsp;&emsp; Date: Tue, 11 Jul 2000 18:23:51 GMT      -- 请求发出的时间 

## 2.3 实体内容
&emsp;&emsp; 只有POST提交的参数会放到实体内容中

## 2.4 HttpServletRequest对象
HttpServletRequest对象作用是用于获取请求数据。   
核心的API：   
 &emsp;&emsp; 请求行：          
&emsp;&emsp;						request.getMethod();   请求方式   
&emsp;&emsp;						request.getRequetURI()   / request.getRequetURL()   请求资源   
&emsp;&emsp;   						request.getProtocol()   请求http协议版本

&emsp;&emsp;请求头：   
&emsp;&emsp;						request.getHeader("名称")   根据请求头获取请求值   
&emsp;&emsp;						request.getHeaderNames()    获取所有的请求头名称

&emsp;&emsp;实体内容:  
&emsp;&emsp;request.getInputStream()   获取实体内容数据

## 2.5 获取请求的参数
GET方式： 参数放在URI后面
POST方式： 参数放在实体内容中

获取GET方式参数：   
&emsp;&emsp;					request.getQueryString();    
			获取POST方式参数：   
&emsp;&emsp;					request.getInputStream();   
问题：但是以上两种不通用，而且获取到的参数还需要进一步地解析。所以可以使用统一方便的获取参数的方式：

核心的API：
&emsp;&emsp;request.getParameter("参数名");  根据参数名获取参数值（注意，只能获取一个值的参数）   
&emsp;&emsp;request.getParameterValue("参数名“)；根据参数名获取参数值（可以获取多个值的参数）    
&emsp;&emsp;    request.getParameterNames();   获取所有参数名称列表  

## 2.6 请求参数编码问题 
修改POST方式参数编码：      
&emsp;&emsp;						request.setCharacterEncoding("utf-8");   
				修改GET方式参数编码：    
&emsp;&emsp;						手动解码：String name = new String(name.getBytes("iso-8859-1"),"utf-8"); 

<br>

# 第三章 HTTP协议	- Response  
HTTP/1.1 200 OK                -- 响应行   

Server: Apache-Coyote/1.1         -- 响应头（key-vaule）   
Content-Length: 24    
Date: Fri, 30 Jan 2015 01:54:57 GMT   

this is hello servlet!!!                  -- 实体内容   

## 3.1 响应行   
1.http协议版本

2.状态码: 服务器处理请求的结果（状态）    
					常见的状态：    
						（1） 200：  表示请求处理完成并完美返回   
						（2） 302：   表示请求需要进一步细化。   
						（3） 404：   表示客户访问的资源找不到。   
						（4） 500：   表示服务器的资源发送错误。（服务器内部错误）

3.状态描述

## 3.2 响应头
Location: http://www.it315.org/index.jsp      - 表示重定向的地址，该头和302的状态码一起使用。   
Server:apache tomcat                 --- 表示服务器的类型   
Content-Encoding: gzip                 -- 表示服务器发送给浏览器的数据压缩类型    
Content-Length: 80                    -- 表示服务器发送给浏览器的数据长度   
Content-Language: zh-cn               -- 表示服务器支持的语言     
Content-Type: text/html; charset=GB2312   -- 表示服务器发送给浏览器的数据类型及内容编码    
Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT  -- 表示服务器资源的最后修改时间    
Refresh: 1;url=http://www.it315.org     -- 表示定时刷新    
Content-Disposition: attachment; filename=aaa.zip -- 表示告诉浏览器以下载方式打开资源（下载文件时用到）    
Transfer-Encoding: chunked   
Set-Cookie:SS=Q0=5Lb_nQ; path=/search   -- 表示服务器发送给浏览器的cookie信息（会话管理用到）    
Expires: -1                           -- 表示通知浏览器不进行缓存    
Cache-Control: no-cache    
Pragma: no-cache    
Connection: close/Keep-Alive           -- 表示服务器和浏览器的连接状态。close：关闭连接 keep-alive:保存连接   

## 3.2 HttpServletResponse对象
HttpServletResponse对象修改响应信息：

响应行： 
&emsp;&emsp;			response.setStatus(number)  设置状态码
&emsp;&emsp;			response.sendError(number)  发送状态码以及相应的状态页面

响应头： 
&emsp;&emsp;		response.setHeader("name","value")  设置响应头

实体内容：
&emsp;&emsp;		response.getWriter().writer();   发送字符实体内容
&emsp;&emsp;		response.getOutputStream().writer()  发送字节实体内容 

## 3.3 请求重定向（Location）
当浏览器得到302状态码之后，会再次自动向服务器发出一个请求，请求的地址就是location的value值的地址。请求重定向浏览器一共向服务器发出2次请求。
请求重定向到url：response.sendRedirect("url")

## 3.4 定时刷新（refresh）
response.setHeader("refresh","1");  &emsp;&emsp;	//每隔1秒刷新一次当前网页
response.setHeader("refresh", "3;url=/adv.html");  &emsp;&emsp;	//隔3秒之后跳转到adv.html

## 3.5 设置content-Type
设置服务器发送给浏览器的数据类型和内容编码：
response.setContentType("text/html;charset=utf-8");
编码和解码的解析：![avatar](./项目中的编码问题.png)

# 第四章 Servlet  
## 4.1 如何开发一个Servlet
1. 编写java类，继承HttpServlet类
2. 重写doGet和doPost方法
3. Servlet程序交给tomcat服务器运行，servlet程序的class文件拷贝到WEB-INF/classes目录，在web.xml文件中进行配置。配置如下所示：

< !-- 配置一个servlet -->
  < !-- servlet的配置 -->
  < servlet>
  	&emsp;&emsp;	< !-- servlet的内部名称，自定义。尽量有意义 -->
  	&emsp;&emsp;	< servlet-name>FirstServlet< /servlet-name>
  	&emsp;&emsp;	< !-- servlet的类全名： 包名+简单类名 -->
  	&emsp;&emsp;	< servlet-class>gz.itcast.a_servlet.FirstServlet< /servlet-class>
  < /servlet>
  
  
   < !-- servlet的映射配置 -->
   < servlet-mapping>
  	&emsp;&emsp;	< !-- servlet的内部名称，一定要和上面的内部名称保持一致！！ -->
  	&emsp;&emsp;	< servlet-name>FirstServlet< /servlet-name>
  	&emsp;&emsp;	< !-- servlet的映射路径（访问servlet的名称） -->
  	&emsp;&emsp;	< url-pattern>/first< /url-pattern>
  < /servlet-mapping>
  
  访问URL：  http://localhost:8080/day10/first 的步骤
  前提： tomcat服务器启动时，首先加载webapps中的每个web应用的web.xml配置文件。
 1. http://: http协议
2. localhost： 到本地的hosts文件中查找是否存在该域名对应的IP地址127.0.0.1
3. 8080：    找到tomcat服务器
4. /day10     在tomcat的webapps目录下找 day10的目录
5. /first    资源名称。
 &emsp;&emsp;	 1）在day10的web.xml中查找是否有匹配的url-pattern的内容（/first）
 &emsp;&emsp;  2）如果找到匹配的url-pattern,则使用当前servlet-name的名称到web.xml文件中查询是否相同名称的servlet配置
  &emsp;&emsp;  3）如果找到，则取出对应的servlet配置信息中的servlet-class内容：字符串： gz.itcast.a_servlet.FirstServlet。通过反射：构造FirstServlet的对象，然后调用FirstServlet里面的方法
  
  
## 4.2 Servlet的映射路径 
< servlet-mapping>
  	&emsp;&emsp;  < !-- servlet的内部名称，一定要和上面的内部名称保持一致！！ -->
  	&emsp;&emsp;  < servlet-name>FirstServlet< /servlet-name>
  	&emsp;&emsp;  < !-- servlet的映射路径（访问servlet的名称） -->
  	&emsp;&emsp;  < url-pattern>/first< /url-pattern>
  < /servlet-mapping>
  
  url-pattern                    &emsp; 浏览器输入
  精确匹配    &emsp;&emsp;   /first    &emsp;&emsp; &emsp;&emsp;   &emsp;&emsp;     http://localhost:8080/day10/first
   &emsp;&emsp;    &emsp;&emsp;    &emsp;&emsp;   /itcast/demo1         &emsp;&emsp;    http://localhost:8080/day10/itcast/demo1
   
   模糊匹配        &emsp;&emsp;        /\*           &emsp;&emsp;   &emsp;&emsp;   &emsp;&emsp;   &emsp;&emsp;           http://localhost:8080/day10/任意路径
     &emsp;&emsp;    &emsp;&emsp;    &emsp;&emsp;    /itcast/\*         &emsp;&emsp;    &emsp;&emsp;   &emsp;     http://localhost:8080/day10/itcast/任意路径
    &emsp;&emsp;    &emsp;&emsp;    &emsp;&emsp;      *.do     &emsp;&emsp;    &emsp;&emsp;   &emsp;&emsp;   &emsp;    http://localhost:8080/day10/任意路径.do
    
   注意：
   1. url-pattern要么以 / 开头，要么以*开头。  例如， itcast是非法路径。
   2.  不能同时使用两种模糊匹配，例如 /itcast/*.do是非法路径
   3. 当有输入的URL有多个servlet同时被匹配的情况下：
  &emsp;&emsp;   精确匹配优先。（长的最像优先被匹配）
  &emsp;&emsp;   以后缀名结尾的模糊url-pattern优先级最低！！！
  
## 4.3 Servlet缺省路径
servlet的缺省路径（< url-pattern>/< /url-pattern>）是在tomcat服务器内置的一个路径。该路径对应的是一个DefaultServlet（缺省Servlet）。这个缺省的Servlet的作用是用于解析web应用的静态资源文件。			 

问题： URL输入http://localhost:8080/day10/index.html 如何读取文件？？？？
1. 到当前day10应用下的web.xml文件查找是否有匹配的url-pattern。
2. 如果没有匹配的url-pattern，则交给tomcat的内置的DefaultServlet处理
3. DefaultServlet程序到day10应用的根目录下查找是否存在一个名称为index.html的静态文件。
4. 如果找到该文件，则读取该文件内容，返回给浏览器。
5. 如果找不到该文件，则返回404错误页面。
结论： 先找动态资源，再找静态资源。

## 4.4 Sevlet的生命周期
Servlet程序的生命周期由tomcat服务器控制的！！！！
### 4.4.1 Servlet重要的四个生命周期方法
1. 构造方法： 创建servlet对象的时候调用。默认情况下，第一次访问servlet的时候创建servlet对象。只调用1次。证明servlet对象在tomcat是单实例的。
2. init方法： 创建完servlet对象的时候调用。只调用1次。
3. service方法： 每次发出请求时调用。调用n次。
4. destroy方法： 销毁servlet对象的时候调用。停止服务器或者重新部署web应用时销毁servlet对象。只调用1次。
### 4.4.2 伪代码演示Servlet的生命周期
Tomtcat内部代码运行：
1. 通过映射找到servlet-class的内容，字符串： gz.itcast.a_servlet.FirstServlet
2. 通过反射构造FirstServlet对象
 &emsp; 2.1 得到字节码对象
 &emsp;&emsp;    Class clazz = class.forName("gz.itcast.a_servlet.FirstServlet");
 &emsp; 2.2 调用无参数的构造方法来构造对象
  &emsp;&emsp;   Object obj = clazz.newInstance();     ---1.servlet的构造方法被调用
  3. 创建ServletConfig对象，通过反射调用init方法
   &emsp;  3.1 得到方法对象
   &emsp;&emsp;  Method m = clazz.getDeclareMethod("init",ServletConfig.class);
      &emsp;   3.2 调用方法
     &emsp;&emsp;    m.invoke(obj,config);             --2.servlet的init方法被调用
     4. 创建request，response对象，通过反射调用service方法
     &emsp;  4.1 得到方法对象
     &emsp;&emsp;    Methodm m =clazz.getDeclareMethod("service",HttpServletRequest.class,HttpServletResponse.class);
    &emsp;  4.2 调用方法
   &emsp;&emsp;     m.invoke(obj,request,response);  --3.servlet的service方法被调用
   5. 当tomcat服务器停止或web应用重新部署，通过反射调用destroy方法
       &emsp;  5.1 得到方法对象
     &emsp;&emsp;    Method m = clazz.getDeclareMethod("destroy",null);
      &emsp;  5.2 调用方法
       &emsp;&emsp;   m.invoke(obj,null);            --4.servlet的destroy方法被调用

### 4.4.3 时序图来演示servlet的生命周期
![](imgs/20190622-215526.png)

## 4.5 Servlet的自动加载
默认情况下，第一次访问servlet的时候创建servlet对象。如果servlet的构造方法或init方法中执行了比较多的逻辑代码，那么导致用户第一次访问sevrlet的时候比较慢。改变servlet创建对象的时机： 提前到加载web应用的时候！！！

在servlet的配置信息中，加上一个< load-on-startup>即可！！
< servlet>
    &emsp;&emsp;  < servlet-name>LifeDemo< /servlet-name>
    &emsp;&emsp;  < servlet-class>gz.itcast.c_life.LifeDemo< /servlet-class>
    &emsp;&emsp;  < !-- 让servlet对象自动加载 -->
   &emsp;&emsp;   < load-on-startup>1< /load-on-startup>  注意： 整数值越大，创建优先级越低！！
  < /servlet>

## 4.6 Servlet的多线程并发问题
注意： servlet对象在tomcat服务器是单实例多线程的。因为servlet是多线程的，所以当多个servlet的线程同时访问了servlet的共享数据，如成员变量，可能会引发线程安全问题。
解决办法：
&emsp;1）把使用到共享数据的代码块进行同步（使用synchronized关键字进行同步）
&emsp;2）建议在servlet类中尽量不要使用成员变量。如果确实要使用成员，必须同步。而且尽量缩小同步代码块的范围。（哪里使用到了成员变量，就同步哪里！！），以避免因为同步而导致并发效率降低。

## 4.7 ServletConfig对象
ServletConfig对象: 主要是用于加载servlet的初始化参数。在一个web应用可以存在多个ServletConfig对象（一个Servlet对应一个ServletConfig对象）

### 4.7.1 ServletConfig对象创建和获取
创建时机： 在创建完servlet对象之后，在调用init方法之前创建。
获取对象： 直接从有参数的init方法中得到！！！

### 4.7.2 Servlet的初始化参数配置
< servlet>
      &emsp;&emsp; < servlet-name>ConfigDemo< /servlet-name>
    &emsp;&emsp;  < servlet-class>gz.itcast.f_config.ConfigDemo< /servlet-class>
     &emsp;&emsp; < !-- 初始参数： 这些参数会在加载web应用的时候，封装到ServletConfig对象中 -->
     &emsp;&emsp; < init-param>
    	 &emsp;&emsp;  &emsp;&emsp; < param-name>path< /param-name>
    	 &emsp;&emsp;  &emsp;&emsp; < param-value>e:/b.txt< /param-value>
     &emsp;&emsp; < /init-param>
  < /servlet>
  
  注意： servlet的参数只能由当前的这个sevlet获取！！！！
  
  ServletConfig的API：
1. 	java.lang.String getInitParameter(java.lang.String name)  根据参数名获取参数值
2. 	java.util.Enumeration getInitParameterNames()    获取所有参数
3. 	ServletContext getServletContext()     得到servlet上下文对象
4. 	java.lang.String getServletName()       得到servlet的名称

## 4.8 ServletContext对象
ServletContext对象 ,叫做Servlet的上下文对象。表示一个当前的web应用环境。一个web应用中只有一个ServletContext对象。

### 4.8.1 ServletContext对象创建和获取
创建时机：加载web应用时创建ServletContext对象。
获取对象： 从ServletConfig对象的getServletContext方法得到
设计流程：
1）创建ServletContext对象	  ServletContext  context = new ServletContext()	
2）创建ServletConfig对象   ServetConfig config = new ServletConfig();
class  ServletConfig{
		&emsp;&emsp; ServletContext context;
		&emsp;&emsp;config.setServletContxt(context);
		&emsp;&emsp; public ServletContext getServletContxt(){
		&emsp;&emsp; return contxt;
		&emsp;&emsp; 					}
					} 
					
public void init( ServletConfig config ){
		&emsp;&emsp;	//得到ServletConfig对象
		&emsp;&emsp;	//从ServletConfig对象中得到ServletContext对象
		&emsp;&emsp;	SerlvetContext context = config.getServletContext();
					}
### 4.8.2 ServletContext对象的核心API
			java.lang.String getContextPath()   --得到当前web应用的路径，用在请求重定向的资源名称中
			context.getRequestDispatcher("路径").forward(request,response);
			等价于：request.getContextPath()

			java.lang.String getInitParameter(java.lang.String name)  --得到web应用的初始化参数（全局）
			java.util.Enumeration getInitParameterNames()  
			//web应用参数可以让当前web应用的所有servlet获取！！！

			void setAttribute(java.lang.String name, java.lang.Object object) --域对象有关的方法 保存数据
			java.lang.Object getAttribute(java.lang.String name)   //获取数据
			void removeAttribute(java.lang.String name)  //删除数据

			context.getRequestDispatcher("路径").forward(request,response);  --转发（类似于重定向）
			/*RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/GetDataServlet");
		rd.forward(request, response);*/
			等价于：request.getRequestDispacher("路径").forward(request,response);  
			
			转发和重定向的区别
			1） 转发
				a）地址栏不会改变
				b）转发只能转发到当前web应用内的资源
				c）可以在转发过程中，可以把数据保存到request域对象中
			2）重定向
				a）地址栏会改变，变成重定向到地址。
				b）重定向可以跳转到当前web应用，或其他web应用，甚至是外部域名网站。
				c）不能再重定向的过程，把数据保存到request中。
			结论： 如果要使用request域对象进行数据共享，只能用转发技术！！！


			java.lang.String getRealPath(java.lang.String path)     --得到web应用的资源文件
			java.io.InputStream getResourceAsStream(java.lang.String path)  
## 4.9 Servlet有参数的init和无参的init方法
有参数的init方法：该方法是servlet的生命周期方法，一定会被tomcat服务器调用，有参数的方法内部调用无参数的方法
注意：如果要编写初始代码，不需要覆盖有参数的init方法
无参数的init方法：该方法是servlet的编写初始化代码的方法。是Sun公司设计出来专门给开发者进行覆盖，然后在里面编写servlet的初始逻辑代码的方法。
<br>


# 第五章 Cookie
## 5.1 Cookie技术：会话数据保存在浏览器客户端
Cookie类：用于存储会话数据

				1）构造Cookie对象
					Cookie(java.lang.String name, java.lang.String value)
				2）设置cookie
					void setPath(java.lang.String uri)   ：设置cookie的有效访问路径
					void setMaxAge(int expiry) ： 设置cookie的有效时间
					void setValue(java.lang.String newValue) ：设置cookie的值
				3）发送cookie到浏览器端保存
					void response.addCookie(Cookie cookie)  : 发送cookie
				4）服务器接收cookie
					Cookie[] request.getCookies()  : 接收cookie
					
## 5.2  Cookie原理
1. 服务器创建cookie对象，把会话数据存储到cookie对象中。
new Cookie("name","value");

2. 服务器发送cookie信息到浏览器
response.addCookie(cookie);
举例： set-cookie: name=eric  (隐藏发送了一个set-cookie名称的响应头)

3. 浏览器得到服务器发送的cookie，然后保存在浏览器端。

4. 浏览器在下次访问服务器时，会带着cookie信息
举例： cookie: name=eric  (隐藏带着一个叫cookie名称的请求头)

5. 服务器接收到浏览器带来的cookie信息
request.getCookies();

## 5.3 Cookie的细节
1. void setPath(java.lang.String uri)   ：设置cookie的有效访问路径。有效路径指的是cookie的有效路径保存在哪里，那么浏览器在有效路径下访问服务器时就会带着cookie信息，否则不带cookie信息。
注意：在/usr中设置的Cookie也可以在/usr/*的目录下显示

2. void setMaxAge(int expiry) ： 设置cookie的有效时间。
正整数：表示cookie数据保存浏览器的缓存目录（硬盘中），数值表示保存的时间。
负整数：表示cookie数据保存浏览器的内存中。浏览器关闭cookie就丢失了！！
零：表示删除同名的cookie数据
Cookie cookie = new Cookie("name","xxxx");
		cookie.setMaxAge(0);//删除同名的cookie

3. Cookie数据类型只能保存非中文字符串类型的。可以保存多个cookie，但是浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB。

# 第六章 Session
##6.1 Session技术核心
如果要保存非字符串，超过4kb内容，只能使用session技术
Session特点：会话数据保存在服务器端。（内存中）

HttpSession类：用于保存会话数据

			1）创建或得到session对象
				HttpSession getSession()  
				HttpSession getSession(boolean create)  
			2）设置session对象
				void setMaxInactiveInterval(int interval)  ： 设置session的有效时间
				void invalidate()     ： 销毁session对象
				java.lang.String getId()  ： 得到session编号
			3）保存会话数据到session对象
				void setAttribute(java.lang.String name, java.lang.Object value)  ： 保存数据
				java.lang.Object getAttribute(java.lang.String name)  ： 获取数据
				void removeAttribute(java.lang.String name) ： 清除数据

##6.2 Session原理
问题： 服务器能够识别不同的浏览者！！！
前提： 在哪个session域对象保存数据，就必须从哪个域对象取出！！！！

			浏览器1：(给s1分配一个唯一的标记：s001,把s001发送给浏览器)
					1）创建session对象，保存会话数据
							HttpSession session = request.getSession();   --保存会话数据 s1
			浏览器1	的新窗口（带着s001的标记到服务器查询，s001->s1,返回s1）	
					1）得到session对象的会话数据
						    HttpSession session = request.getSession();   --可以取出  s1

			新的浏览器1：(没有带s001,不能返回s1)
					1）得到session对象的会话数据
						    HttpSession session = request.getSession();   --不可以取出  s2

			浏览器2：(没有带s001,不能返回s1)
					1）得到session对象的会话数据
						    HttpSession session = request.getSession();  --不可以取出  s3
						    
代码解读：HttpSession session = request.getSession();
				
			1）第一次访问创建session对象，给session对象分配一个唯一的ID，叫JSESSIONID
					new HttpSession();
			2）把JSESSIONID作为Cookie的值发送给浏览器保存
					Cookie cookie = new Cookie("JSESSIONID", sessionID);
					response.addCookie(cookie);
			3）第二次访问的时候，浏览器带着JSESSIONID的cookie访问服务器
			4）服务器得到JSESSIONID，在服务器的内存中搜索是否存放对应编号的session对象。
					if(找到){
						return map.get(sessionID);
					}
					Map<String,HttpSession>]


					<"s001", s1>
					<"s001,"s2>
			5）如果找到对应编号的session对象，直接返回该对象
			6）如果找不到对应编号的session对象，创建新的session对象，继续走1的流程
	
			结论：通过JSESSION的cookie值在服务器找session对象！！！！！


##6.3 Session细节
			1）java.lang.String getId()  ： 得到session编号
			2）两个getSession方法：
					getSession(true) / getSession()  : 创建或得到session对象。没有匹配的session编号，自动创建新的session对象。
					getSession(false):              得到session对象。没有匹配的session编号，返回null
			3）void setMaxInactiveInterval(int interval)  ： 设置session的有效时间
						session对象销毁时间：
							3.1 默认情况30分服务器自动回收
							3.2 修改session回收时间
							3.3 全局修改session有效时间
							
	<!-- 修改session全局有效时间:分钟 -->
	<session-config>
		<session-timeout>1</session-timeout>
	</session-config>
手动销毁session对象：void invalidate()  

如何避免浏览器的JSESSIONID的cookie随着浏览器关闭而丢失的问题, 手动发送一个硬盘保存的cookie给浏览器
		
		Cookie c = new Cookie("JSESSIONID",session.getId());
		c.setMaxAge(60*60);
		response.addCookie(c);

# 第七章 JSP
##6.1 JSP特点
Servlet的作用： 用java语言开发动态资源的技术！！！
Jsp的作用：用java语言（+html语言）开发动态资源的技术，Jsp就是servlet！！！（继承自Servlet）

JSP的特点：
		1）jsp的运行必须交给tomcat服务器！！！！
				tomcat的work目录： tomcat服务器存放jsp运行时的临时文件
		2）jsp页面既可以写html代码，也可以写java代码。
				（html页面不能写java代码 。而jsp页面可以写java代码）
				
##6.2 JSP的执行过程
问题： 访问http://localhost:8080/day12/01.hello.jsp  如何显示效果？

			1）访问到01.hello.jsp页面，tomcat扫描到jsp文件，在%tomcat%/work把jsp文件翻译成java源文件
						(01.hello.jsp   ->   _01_hello_jsp.java) （翻译）
			2）tomcat服务器把java源文件编译成class字节码文件 （编译）
					（_01_hello_jsp.java  ->  _01_hello_jsp.class）
			3）tomcat服务器构造_01_hello_jsp类对象
			4）tomcat服务器调用_01_hello_jsp类里面方法，返回内容显示到浏览器。
				第一次访问jsp：
				走（1）（2）（3）（4）
				
				第n次访问jsp：
				走（4）
				
				注意：jsp文件修改了或jsp的临时文件被删除了，要重新走翻译（1）和编译（2）的过程
				
##6.3 Servlet和Jsp的生命周期对比
Servlet的生命周期：
					1）构造方法（第1次访问）
					2）init方法（第1次访问）
					3）service方法
					4）destroy方法	
					
Jsp的生命周期
					1）翻译： jsp->java文件
					2）编译： java文件->class文件（servlet程序）
					3）构造方法（第1次访问）
					4）init方法（第1次访问）：_jspInit()
					5）service方法：_jspService()
					6）destroy方法：_jspDestroy()
					
##6.4 JSP语法
###6.4.1 JSP表达式
语法：<%=变量或表达式%>
作用： 向浏览器输出变量的值或表达式计算的结果
注意：		
	1）表达式的原理就是翻译成out.print(“变量” );通过该方法向浏览器写出内容
	2）表达式后面不需要带分号结束。
	
###6.4.2 JSP的脚本
语法：<%java代码 %>
作用： 执行java代码	
注意：
	原理把脚本中java代码原封不动拷贝到_jspService方法中执行。
	
###6.4.3 JSP的声明
语法：<%! 变量或方法 %>
作用： 声明jsp的变量或方法
注意:
变量翻译成成员变量，方法翻译成成员方法。

###6.4.4 JSP的注释
语法： <%!--  jsp注释  --%>
注意;
	html的注释会被翻译和执行。而jsp的注释不能被翻译和执行。
	
##6.5 Jsp的三大指令
###6.5.1 include指令
作用： 在当前页面用于包含其他页面
语法： <%@include file="common/header.jsp"%>
注意：
1）原理是把被包含的页面（header.jsp）的内容翻译到包含页面(index.jsp)中,合并成翻译成一个java源文件，再编译运行！！，先合并再翻译，这种包含叫静态包含（源码包含）
2）如果使用静态包含，被包含页面中不需要出现全局的html标签了！！！（如html、head、body）

###6.5.2 page指令
作用： 告诉tomcat服务器如何翻译jsp文件
				<%@ page 
					language="java"   --告诉服务器使用什么动态语言来翻译jsp文件
					import="java.util.*" --告诉服务器java文件使用什么包
												导入包，多个包之间用逗号分割
					pageEncoding="utf-8"  --告诉服务器使用什么编码翻译jsp文件成java文件（jsp->java文件）
				 	contentType="text/html; charset=utf-8" 服务器发送浏览器的数据类型和内容编码（tomcat服务器->浏览器）
						注意：在开发工具中，以后只需要设置pageEncoding即可解决中文乱码问题
					errorPage="error.jsp":  指定当前jsp页面的错误处理页面。
					isErrorPage="false"指定当前页面是否为错误处理页面。false，不是错误处理页面，则不能使用exception内置对象；true，是错误处理页面，可以使用exception内置对象。		
					buffer="8kb":  jsp页面的缓存区大小。
					session="true":  是否开启session功能。false，不能用session内置对象；true，可以使用session内置对象。
					isELIgnored="false"： 是否忽略EL表达式。
	%>
	
	 < !-- 全局错误处理页面配置 -->
  < error-page>
  	< error-code>500</error-code>
  	<location>/common/500.jsp</location>
  </error-page>
  <error-page>
  	<error-code>404</error-code>
  	<location>/common/404.html</location>
  </error-page>
  
###6.5.3 taglib指令

##6.6 JSP的内置对象
###6.6.1 什么是内置对象
在jsp开发中，会频繁使用到一些对象。例如：HttpSession, ServletContext, ServletContext, HttpServletRequet。如果我们每次要使用这些对象都去创建这些对象就显的非常麻烦。所以Sun公司设计Jsp时，在jsp页面加载完毕之后就会自动帮开发者创建好这些对象，而开发者只需要直接使用这些对象调用方法即可！，这些创建好的对象就叫内置对象！！！！
举例：
					servlet: 
							HttpSession session = request.getSession(true); （需要开发者做）
				
					jsp:
						tomcat服务器：	HttpSession session = request.getSession(true);(不需要开发者做)
						开发者做的： session.getId();
						
###6.6.2 九大内置对象
内置对象名          类型
reques &emsp;&emsp;t	   HttpServletRequest
response &emsp;&emsp;     HttpServletResponse
config   &emsp;&emsp;     ServletConfig
application    &emsp;&emsp;    ServletContext
session     &emsp;&emsp;    HttpSession
exception     &emsp;&emsp;   Throwable
page       &emsp;&emsp;     Object(this)
out         &emsp;&emsp;    JspWriter
pageContext   &emsp;&emsp;  PageContext 

###6.6.3 Out对象
out对象类型，JspWriter类，相当于带缓存的PrintWriter

					PrintWriter： 
							wrier(内容)： 直接向浏览器写出内容。

					JspWriter
							writer(内容): 向jsp缓冲区写出内容

				当满足以下条件之一，缓冲区内容写出：
						1）缓冲区满了
						2）刷新缓存区
						3）关闭缓存区
						4）执行完毕jsp页面
						
###6.6.4 pageContext对象
pageContext对象的类型是PageContext，它是jsp的上下文对象
1. 可以获取其他八个内置对象

			public class 01_hello_jsp {
					public void _jspService(request,response){
						创建内置对象
						HttpSession session =....;
						ServletConfig config = ....;

						把8个经常使用的内置对象封装到PageContext对象中
						PageContext pageContext  = 封装；
						调用method1方法
						method1(pageContext);
					}
					
					public void method1(PageContext pageContext){
						希望使用内置对象
						从PageContext对象中获取其他8个内置对象
						JspWriter out =pageContext.getOut();
						HttpServletRequest rquest = 	pageContext.getRequest();
						........
					}
			}

				使用场景： 在自定义标签的时候，PageContext对象频繁使用到！！！

2. 保存数据
					1）默认情况下，保存到page域
							pageContext.setAttribute("name");
					2）可以向四个域对象保存数据
							pageContext.setAttribute("name",域范围常量)
							
3. 获取数据
					1）默认情况下，从page域获取
							pageContext.getAttribute("name")
					2）可以从四个域中获取数据
							pageContext.getAttribute("name",域范围常量)

						域范围常量:
							PageContext.PAGE_SCOPE
							PageContext.REQUEST_SCOPE
							PageContext..SESSION_SCOPE
							PageContext.APPLICATION_SCOPE
					3）自动在四个域中搜索数据
							pageContext.findAttribute("name");
						顺序： page域 -> request域 -> session域- > context域（application域）
						
###6.6.5 Jsp中的四个域对象
四个域对象：
					pageContext      page域				
					request          request域
					session          session域
					application       context域
					
1）域对象作用：
					保存数据  和 获取数据 ，用于数据共享。
					
2）域对象方法：
					setAttribute("name",Object) 保存数据
					getAttribute("name")  获取数据
					removeAttribute("name") 清除数据
					
3）域对象作用范围：
					page域： 只能在当前jsp页面中使用（当前页面）
					request域： 只能在同一个请求中使用（转发）
					session域： 只能在同一个会话（session对象）中使用（私有的）
				      	context域： 只能在同一个web应用中使用。（全局的）

##6.7 EL表达式
使用EL表达式替换掉jsp表达式，EL表达式作用： 向浏览器输出域对象中的变量值或表达式计算的结果！！！
语法：
			${变量}
			输出普通字符串： ${name}
			输出对象属性：  ${student.name}  注意： .name 相当于  .getName（）方法
			输出List集合：   ${list[0].name }   注意： [0]  相当于 get（下标）方法
			输出map集合：  ${map[key].name}  注意：　［key］相当于get（key）方法

输出基本数据类型变量：
						1.  从四个域获取
							${name}
						2.  指定域获取
							${pageScope.name}
                    域范围： pageScoep / requestScope / sessionScope / applicationScope
                    
##6.8 jsp标签
jsp标签作用：替换jsp脚本，用于在jsp页面中执行java代码
jsp标签分类：
				1）内置标签（动作标签）： 不需要在jsp页面导入标签
				2）jstl标签： 需要在jsp页面中导入标签
				3）自定义标签 ： 开发者自行定义，需要在jsp页面导入标签
				
动作标签：
				转发标签：	<jsp:forward /> request.getRequesetDipsacher("/路径").foward(request,response);
            			参数标签：  <jsp:pararm/> 参数标签    ？name=eric
				包含标签：  <jsp:include/> 包含其他页面 ，动态包含
				原理： 包含与被包含的页面先各自翻译成java源文件，然后再运行时合并在一起。
						（先翻译再合并），动态包含
						
静态包含  vs  动态包含的区别？

				1） 语法不同
				静态包含语法： <%@inclue file="被包含的页面"%>
				动态包含语法：　<jsp:include page="被包含的页面">

				2）参数传递不同
				静态包含不能向被包含页面传递参数
				动态包含可以向被包含页面传递参数
	
				3）原理不同
				静态包含： 先合并再翻译
				动态包含： 先翻译再合并

##6.9 JSTL标签
使用JSTL标签步骤
			1） 导入jstl支持的jar包（标签背后隐藏的java代码）
					注意：使用javaee5.0的项目自动导入jstl支持jar包
			2）使用taglib指令导入标签库 
							<%@taglib uri="tld文件的uri名称" prefix="简写" %>
			3）在jsp中使用标签		
			
核心标签库的重点标签：
		保存数据：
			<c:set></c:set>   
		获取数据： 
             <c:out value=""></c:out>
		单条件判断
            <c:if test=""></c:if>
		多条件判断
          <c:choose></c:choose>
    	  <c:when test=""></c:when>
          <c:otherwise></c:otherwise>
   		循环数据
          <c:forEach></c:forEach>
          <c:forTokens items="" delims=""></c:forTokens>
		重定向
          <c:redirect></c:redirect>
          
##6.10 JavaBean
JavaBean就是一个普通的java类。只有符合以下规定才能称之为javabean：
			  1）必须提供无参数的构造方法
			  2）类中属性都必须私有化(private)
			  3）该类提供公开的getter 和 setter方法
			  
JavaBean的作用： 用于封装数据，保存数据。
				访问javabean只能使用getter和setter方法			  

JavaBean的使用场景：
				1）项目中用到实体对象（entity）符合javabean规范
				2）EL表达式访问对象属性。${student.name}  调用getName()方法，符合javabean规范。
				3）jsp标签中的属性赋值。 setNum（Integer num）。符合javabean规范。
				4）jsp页面中使用javabean。符合javabean规范
				
##6.11 web开发模式	
MVC开发模式：
						Model - JavaBean实现。用于封装业务数据
						View - Jsp实现。用于显示数据
						Controller-  servlet实现。用于控制model和view
						
三层结构：
						dao层： 和数据访问相关的操作
						service层： 和业务逻辑相关的操作
						web层： 和用户直接交互相关的操作（传接参数，跳转页面）
