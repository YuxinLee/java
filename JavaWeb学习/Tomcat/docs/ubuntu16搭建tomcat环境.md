# 1. ubuntu16 安装jdk
<br>
##1.1  在官网下载linux版本的jdk（jdk-8u111-Linux-x64.tar.gz）
<br>
##1.2 解压文件jdk-8u111-Linux-x64.tar.gz
sudo tar -zxvf jdk-8u111-linux-x64.tar.gz
解压成功后可以看到该目录下多出了文件夹jdk1.8.0_111

先在/usr/local下新建文件夹Java，然后将文件夹jdk1.8.0_111移动到目录/usr/local/java下
sudo mv &nbsp;   jdk1.8.0_111  &nbsp;   /usr/local/java/
<br>
##1.3 配置 java环境
配置系统环境变量，在全局配置文件/etc/profile下配置，即为所有用户配置Java环境。使用vim命令编辑/etc/profile文件，在文件底部加上四条配置信息。
export JAVA_HOME=/usr/local/java/jdk1.8.0_111    
export JRE_HOME=${JAVA_HOME}/jre     
export CLASSPATH=.:%{JAVA_HOME}/lib:%{JRE_HOME}/lib      

export PATH=${JAVA_HOME}/bin:$PATH

环境变量生效：
source /etc/profile

<br>
##1.4 验证 java环境
在shell中输入
java
 javac
 java -version
 
 <br>
# 2. ubuntu16 安装idea
 <br>
## 2.1  在官网下载linux版本的idea(ideaIU-2016.3.3-no-jdk.tar.gz)
<br>
##2.2 解压文件ideaIU-2016.3.3-no-jdk.tar.gz
sudo tar -zxvf ideaIU-2016.3.3-no-jdk.tar.gz
解压成功后可以看到该目录下多出了文件夹 ideaIU-2016.3.3

先在/usr/local下新建文件夹Idea，然后将文件夹ideaIU-2016.3.3移动到目录/usr/local/Idea下
sudo mv &nbsp;    ideaIU-2016.3.3  &nbsp;   /usr/local/Idea/

然后输入./idea.sh即可开启idea

<br>

##2.3 如何激活idea
将“0.0.0.0 account.jetbrains.com”及“0.0.0.0 www.jetbrains.com”添加到hosts文件中 

输入http://idea.lanyus.com/网址，获取激活码
<br>

## 2.4 Ubuntu中创建Intellij IDEA快捷方式
在/home/username/桌面创建文件idea.desktop。
在文件中添加
[Desktop Entry]
Name=IntelliJ IDEA
Comment=IntelliJ IDEA
Exec=/home/username/下载/idea-IU-183.6156.11/bin/idea.sh
Icon=/home/username/下载/idea-IU-183.6156.11/bin/idea.png
Terminal=false
Type=Application
Categories=Developer;

然后chmod +x /home/username/idea.desktop
此时桌面就有idea的快捷方式了

<br>
# 3. ubuntu16 安装tomcat
 <br>
## 3.1  在官网下载linux版本的tomcat(apache-tomcat-8.5.9.tar.gz)
<br>
##3.2 解压文件apache-tomcat-8.5.9.tar.gz
sudo tar -zxvf apache-tomcat-8.5.9.tar.gz
解压成功后可以看到该目录下多出了文件夹 apache-tomcat-8.5.9

先在/usr/local下新建文件夹tomcat，然后将文件夹apache-tomcat-8.5.9移动到目录/usr/local/tomcat下
sudo mv &nbsp;   apache-tomcat-8.5.9 &nbsp;   /usr/local/tomcat/

修改tomcat的权限
sudo chmod 755 -R tomcat

<br>
##3.2配置tomcat环境
3.2.1   进入目录/usr/local/tomcat/apache-tomcat-8.5.9/bin，编辑文件startup.sh，在最后一行之前加入如下信息：
\# set java environment
export JAVA_HOME=/usr/java/jdk1.8.0_111
export JRE_HOME=${JAVA_HOME}/jre     
export CLASSPATH=.:%{JAVA_HOME}/lib:%{JRE_HOME}/lib      

export PATH=${JAVA_HOME}/bin:$PATH


\# tomcat
export TOMCAT_HOME=/usr/tomcat/apache-tomcat-8.5.9

编辑完后保存退出，然后运行startup.sh
sudo ./startup.sh
在浏览器中输localhost:8080会出现tomcat的网站
<br>

3.2.2 如果要关闭tomcat，类似的，需要先在文件shutdown.sh对应位置添加信息：
\# set java environment
export JAVA_HOME=/usr/java/jdk1.8.0_111
export JRE_HOME=${JAVA_HOME}/jre     
export CLASSPATH=.:%{JAVA_HOME}/lib:%{JRE_HOME}/lib      

export PATH=${JAVA_HOME}/bin:$PATH


\# tomcat
export TOMCAT_HOME=/usr/tomcat/apache-tomcat-8.5.9

然后执行如下命令即可：sudo ./shutdown.sh

3.2.3 如果要设置为tomcat开机自启动，需要编辑文件/etc/rc.local，这里存放着开机自启动的程序。配置在/etc/profile和/etc/bash.bashrc文件中的内容是当有用户登录时才起作用，这不符合tomcat需要启动的实际情况） 
编辑/etc/rc.local：
sudo vi /etc/rc.local

在最后一行之前加入如下信息：（配置你自己的tomcat的startup.sh文件的路径）
\# set java environment
export JAVA_HOME=/usr/java/jdk1.8.0_111
export JRE_HOME=${JAVA_HOME}/jre     
export CLASSPATH=.:%{JAVA_HOME}/lib:%{JRE_HOME}/lib      

export PATH=${JAVA_HOME}/bin:$PATH

/usr/tomcat/apache-tomcat-8.5.9/bin/startup.sh






