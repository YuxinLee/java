# java基础知识

## 1. 基础篇

### 1.1 面向对象的特征

面向对象的特征有哪些方面？

**1.抽象**

抽象只关注对象有哪些属性和行为，并不关注这些行为的细节是什么

**2.继承**

继承是从已有类得到继承信息创建新的类的过程

**3.封装**

封装是把数据和操作数据的方法绑定起来，对数据的访问只能通过已定义的接口

**4.多态性**

多态性是指允许不同子类型的对象对同一消息作出不同的响应。



### 1.2 int和Integer有什么区别

区别：

1.Integer是int的包装类，int则是java的一种基本数据类型

2.Integer变量必须实例化后才能使用，而int变量不需要

3.Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象，而int则是直接存储数据值

4.Integer的默认值是null，int的默认值是0

5.由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的

（因为new生成的是两个对象，其内存地址不同）。

6.Integer变量和int变量比较时，只要两个变量的值是相等的，则结果为true(因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较)

7.非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。（因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）

8.对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false。

原因如下：java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；，而java API中对Integer类型的valueOf的定义如下：

public static Integer valueOf(int i){

​    assert IntegerCache.high >= 127;

​    if (i >= IntegerCache.low && i <= IntegerCache.high){

​        return IntegerCache.cache[i + (-IntegerCache.low)];

​    }

​    return new Integer(i);

}

java对于-128到127之间的数，会进行缓存，Integer i = 127时，会将127进行缓存，下次再写Integer j = 127时，就会直接从缓存中取，就不会new了



### 1.3 重载和重写的区别

重载(overload)

(1)方法重载是让类以统一的方式处理不同类型数据的一种手段，多个同名函数同时存在，具体要不同的参数个数/类型

重载是一个类中多态性的一种表现

(2)Java的方法重载，就是在类中可以创建多个方法，它们具有相同的名字，但具有不同的参数和不同的定义。

调用方法时通过传递给它们的不同参数个数和参数类型来决定具体使用哪个方法, 这就是多态性。 

(3)重载的时候，方法名要一样，重载是在一个类中定义了多个不同参数(个数，类型，顺序)的同名方法，不能通过方法的访问权限、返回值类型和抛出的异常类型来进行重载。一句话，重载由参数列表决定

重写（Override）：

(1)父类与子类之间的多态性，对父类的函数进行重新定义。如果在子类中定义某方法与其父类有相同的名称和参数，

我们说该方法被重写 (Overriding)。在Java中，子类可继承父类中的方法，而不需要重新编写相同的方法。

但有时子类并不想原封不动地继承父类的方法，而是想作一定的修改，这就需要采用方法的重写。方法重写又称方法覆盖。

(2)若子类中的方法与父类中的某一方法具有相同的方法名、返回类型和参数表，则新方法将覆盖原有的方法。

如需父类中原有的方法，可使用super关键字，该关键字引用了当前类的父类。 

(3)子类函数的访问修饰权限不能小于父类的

重载的规则： 

1.必须具有不同的参数列表； 

2.可以有不同的返回类型，只要参数列表不同就可以了； 

3.可以有不同的访问修饰符； 

4.可以抛出不同的异常；

重写方法的规则: 

1.参数列表必须完全与被重写的方法的相同,否则不能称其为重写而是重载. 

2.返回的类型必须一直与被重写的方法的返回类型相同,否则不能称其为重写而是重载. 

3.访问修饰符的限制一定要大于被重写方法的访问修饰符（public>protected>default>private） 

4.重写方法一定不能抛出新的检查异常或者比被重写方法申明更加宽泛的检查型异常．例如， 

父类的一个方法申明了一个检查异常IOException，在重写这个方法时就不能抛出Exception，只能抛出IOException的子类异常，可以抛出非检查异常．

重写和重载的区别在于：

Overloaded的方法是可以改变返回值的类型。



### 1.4 equals 与 == 的区别

1.==在基本数据类型中的比较

“==” 属于关系运算符,比较的是两个基本数据类型的值是否相等.

2.==和equals()

(1)不new String类对象时的比较

在引用数据类型中==比较的是引用的对象是否相等,即是否引用了同一个对象

当执行String s1 = “chance”;这条语句时,会在堆中的字符常量池里找”chance”这个字符串,若没有找到,则将”chance”这个字符串放入字符串常量池中.而在栈中开辟一块名为s1的空间存放”chance”,这块空间的引用.

当执行String s2 = “chance”;这条语句时,会在堆中的字符串常量池里找”chance”这个字符串,很显然,可以找到,

于是便把字符常量池里”chance”这个字符串的引用地址赋给s2,因此s1与s2存放的都是堆中字符常量池中的同一个”chance”的引用 

equals在String类中被重写过,用来比较两个字符串的实际内容是否相等,即每一个字符是否相等,因为比较的是字符串内容,

s1,s2内容都是chance当然是相等的。

(2)new String类时的比较

当程序执行String s3 = new String(“chance”); 这一句时,会在堆内存中开辟一块空间用于存放”chance”这一字符串.

并在栈中开辟一块名为s3的内存空间存放堆中刚刚创建的”chance”对象的引用.

程序执行String s4 = new String(“chance”); 这句时,会在堆内存中开辟另一块空间用于存放”chance”这一字符串,

并在栈中开辟一块名为s4的内存空间存放堆中刚刚创建的”chance”对象的引用. 

因为s3和s4中存放的是两个不同对象的引用,自然System.out.println(s3 == s4); 返回的是false; 

因为还是String类 因此equals方法比较的还是字符串中内容是否相等,即每个字符是否相等。可见s3和s4这两个对象中中存放都是”chance”,

自然每个字符都相等. 

(3)不在String类中的比较

Scanner scanner = new Scanner(System.in);      

Scanner scanner2 = new Scanner(System.in);

System.out.println(scanner.equals(scanner2));       //false

Scanner sc = scanner;

System.out.println(scanner.equals(sc));            //true

System.out.println(scanner==sc);            //true

执行第一条语句Scanner scanner = new Scanner(System.in); 时在堆中开辟了一块内存存放Scanner对象,

在栈内存中开辟一块名为scanenr的内存存放Scanner对象的引用.

执行第二条语句Scanner scanner2 = new Scanner(System.in); 时时在堆中另外开辟了一块内存存放Scanner对象,

在栈内存中开辟一块名为scanenr2的内存存放Scanner对象的引用. 

因为这里调用的是一般对象的equals方法,因此比较的是两个对象是否属于同一个对象,显然不是同一个对象. 

至于最后一个Scanner sc = scanner;则将scanner对象的引用复制给sc因此,sc和scanner指向都是堆中同一个Scanner对象,自然比较的都是相等的. 



### 1.5 for循环省略部分研究

for(int i=0; i<10; i++){}

for(;;) 相当于 while(1)

第一条省略：没有初始化语句	可以在循环前定义初始值

第二条省略：没有判断条件，默认为true	可以在循环内部设置break，来跳出循环

第三条省略：没有改变条件	循环体中可以终止循环的操作或者在循环体中设置步长



### 1.6 如何跳出多重循环

ok:

for(int i=0;i<10;i++) {

​	for(int j=0;j<10;j++) {

​	System.out.println(“i=” + i + “,j=” + j);

​	if(j == 5) break ok;

​	}

}



### 1.7 java的优点以及与c++的异同

**优点：**

1.纯面向对象的语言

2.平台无关性，一次编译，到处运行。编译器会把java代码变成“中间代码”，然后在JVM上解释执行。

3.提供了垃圾回收器，使得开发人员从对内存的管理中解脱出来。

**异同：**

1.java可以跨平台，c++不能。java的执行速度比c++慢。

2.java是纯面向对象，所有代码必须在类中实现，不存在全局变量和全局函数，c++兼顾面向对象和面向过程。

3.java没有指针

4.java语言不支持多继承，但c++支持，java可以通过实现多个接口来实现多继承

5.java的垃圾回收器自动回收垃圾，c++开发人员去管理内存的分配，将释放资源的代码放到析构函数中。java中当垃圾回收器释放对象内存时，会首先调用该对象的finalize()方法。

6.c++语言支持运算符重载，java不支持。

7.java具有平台无关性，对每种数据结构在不同的平台都分配固定的长度。例如，int数据类型占据32为，而c++会根据平台的变化而变化。



### 1.8 main()方法

public static void main(String[] args){

​	for(int i=0;i<arg.length;i++){

​	Sysyem.out.println(arg[i]);

​	}

}

java Test arg1 arg2 arg3

由于main方法是程序的入口，此时还没有实例化对象，所以需要加上static。

虽然每个类都可以定义main()方法，但是只有与文件名相同的用public修饰的类中的main方法才能作为整个程序的入口方法。

main方法不能用abstract修饰。



### 1.9 java程序初始化的顺序

父类静态变量->父类静态代码块->子类静态变量->子类静态代码块->父类非静态变量->父类非静态代码块->父类构造方法->子类非静态变量->子类非静态代码块->子类构造函数

静态变量可以理解为全局变量，被所有的实例共享。



### 1.10 接口没有任何方法

接口中的默认常量使用public static void修饰。

没有任何方法的接口称为标识接口，用来表示实现它的类属于哪一个特定的类型。

标识接口：Cloneable和Serializable，在使用时会经常用instanceof来判断实例对象是否是否实现了一个给定的标识接口。



### 1.11 反射的用途以及实现

反射的用途及实现：

java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法；

这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

Java反射框架提供以下功能：

①、在运行时判断任意一个对象所属的类

②、在运行时构造任意一个类的对象

③、在运行时判断任意一个类所具有的成员变量和方法（通过反射设置可以调用 private）

④、在运行时调用任意一个对象的方法

反射的主要用途：

很多框架（比如 Spring）都是配置化的（比如通过 XML文件配置JavaBean，Action之类的），为了保证框架的通用性，

他们可能根据配置文件加载不同的对象或类，调用不同的方法，这个时候就必须用到反射——运行时动态加载需要加载的对象。

Class c = Class.forName("Sub");

类名.class

实例.getClass()



### 1.12 不可变类

1.不可变类：创建了这个类的实例后，就不允许修改它的值了。可读不可写，类似于常量。

所有的基本类型的包装类都是不可变类，String也是不可变的

s = "Hello";

s+="world"

s首先指向了“Hello”，之后指向了另一个对象("Hello world")，原来那个字符串常量“Hello”还在内存中，并没有改变。

将一个String传递给一个方法时，不用担心该方法会改变字符串中的字符顺序，但是当把数组传递给一个方法时，方法可以自由改变数组的内容。



### 1.13 jdk和jre的区别

JDK，开发java程序用的开发包，JDK里面有java的运行环境(JRE)，包括client和server端的。需要配置环境变量。

JRE，运行java程序的环境，JVM，JRE里面只有client运行环境，安装过程中，会自动添加PATH。

1.JDK,JRE,JVM的作用及关系(掌握)

​	(1)作用

​		JVM：保证Java语言跨平台

​		JRE：Java程序的运行环境

​		JDK：Java程序的开发环境

(2)关系

​		JDK：JRE+工具

​		JRE：JVM+类库

2.Java程序的开发执行流程：

​		A:编写java源程序(.java)

​		B:通过javac命令编译生成.class文件

​		C:通过java命令运行.class文件

3.path环境变量(掌握)

​	(1)path环境变量的作用

​		保证javac命令可以在任意目录下运行。

​		程序的执行需要使用外部指令javac，但是javac指令仅仅能在JDK安装目录下的bin目录下时候，因此程序只能写入bin目录。程序开发过程中，不能将源代码写入JDK的安装目录，因此需要将源程序保存到任意位置的指定目录(英文目录)，所以需要使javac指令在任意目录下可以运行

​	(2)path配置的两种方案：

​		A:方案1(了解)

​		B:方案2

​			找到环境变量的位置，在系统变量里面

​			新建：

​				变量名：JAVA_HOME

​				变量值：D:\develop\Java\jdk1.7.0_60

​			修改：

​				变量名：Path

​				变量值：%JAVA_HOME%\bin;以前的内容

4.classpath环境变量(理解)

​	(1)classpath环境变量的作用

​		保证class文件可以在任意目录下运行，使classpath目录中的.class文件可以在任意目录运行

​	(2)classpath环境变量的配置

​		找到环境变量的位置，在系统变量里面

​		新建：

​			变量名：classpath

​			变量值：E:\JavaSE\day01\code\HelloWorld案例

5.path和classpath的区别

path环境变量里面记录的是可执行性文件，如.exe文件，对可执行文件先在当前路径去找，如果没找到就去path环境变量中配置的路径去找

classpath环境变量里记录的是java类的运行文件所在的目录



### 1.14 构造函数

1.构造函数必须与类的名字相同，不能有void

2.每个类可以有多个构造方法，当没有构造方法时，会提供一个默认的无参的构造方法。如果提供了构造方法，系统就不会默认创建构造方法了。

3.构造方法只能运行一次，普通方法可以被调用多次。

4.构造方法不能被继承，所以不能被重写，但是可以重载。

5.子类可以通过super显式的调用父类的构造方方法，当父类没有提供无参数的构造方法时，子类的构造方法中必须显式地调用父类的构造方法。如果父类提供了无参的构造方法，子类可以不显式地调用父类的构造方法，默认调用父类的无参构造方法。实例化时先执行父类的构造方法，然后执行子类的构造方法。

6.当父类和子类都没有构造方法时，系统会默认为父类和子类各生成一个默认的无参构造函数。

7.子类中所有的构造方法默认都会访问父类中空参数的构造方法，子类每一个构造方法的第一条语句默认都是：super();



### 1.15 static

1.static的作用：为对象分配单一的空间，而与创建对象的个数无关；实现的方法或属性与类而不是对象绑定在一起。

2.static成员变量

静态变量只有一个，被类拥有，所有对象共享。

3.static成员方法

static方法是类方法，不需要创建对象就可以被调用。static方法中不能使用this和super，不能调用非static方法。只能访问所属类的静态方法和成员。

4.代码的运行顺序：  

  静态代码块Fu

​		静态代码块Zi

​		构造代码块Fu

​		构造方法Fu

​		构造代码块Zi

​		构造方法Zi



### 1.16 final，finally，finalize的区别

**1.final**

final可以用于成员变量，方法和类

(1)变量

变量一旦被初始化便不可以改变(基本类型：值不变，对象类型：引用不变)

(2)方法

不可以重写

(3)类

不可继承

面试题：final修饰局部变量的问题

​		基本类型：基本类型的值不能发生改变。

​		引用类型：引用类型的地址值不能发生改变，但是，该对象的堆内存的值是可以改变的。

final Student ss = new Student();

​		System.out.println(ss.age);

​		ss.age = 100;

​		System.out.println(ss.age);

​		//上面运行没有任何问题

​		//重新分配内存空间

​		//无法为最终变量ss分配值

​		ss = new Student();

**2.finally**

异常处理关键字，finally中的主体总会执行，不管异常是否发生。

try语句在返回前，将其他所有的操作执行完，保留好要返回的值，而后转入执行finally中的语句，而后分为以下三种情况：

(1)如果finally中有return语句，则会将try中的return语句”覆盖“掉，直接执行finally中的return语句，得到返回值，

这样便无法得到try之前保留好的返回值。

(2)如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的return语句，返回之前保留的值。

(3)如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况，：

a.如果return的数据是基本数据类型，则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。

b.如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，

try中的return语句返回的就是在finally中改变后的该属性的值。

**3.finalize**

类的finalize方法，可以告诉垃圾回收器应该执行的操作，该方法从Object类继承而来。在从堆中永久删除对象之前，

垃圾回收器调用该对象的Finalize方法。注意，无法确切地保证垃圾回收器何时调用该方法，也无法保证调用不同对象的方法的顺序。

即使一个对象包含另一个对象的引用，或者在释放一个对象很久以前就释放了另一个对象，也可能会以任意的顺序调用这两个对象的Finalize方法。

如果必须保证采用特定的顺序，则必须提供自己的特有清理方法。



### 1.17 多态

1.当同一个操作作用在不同的对象时，会有不同的语义，从而产生不同的结果。

2.两种表现形式：

(1)方法的重载：方法名一样，参数不同。在编译时可以确定使用哪个方法，编译时多态，类中的方法多态性

(2)方法的覆盖：子类可以覆盖父类的方法，所以同样的方法在子父类会有不同的表现形式。运行时多态。

成员变量无法实现多态，Person p = new Son(); p.name  调用的是父类的成员变量。(编译期决定)

多态中的成员访问特点：

​		A:成员变量

​			编译看左边，运行看左边。

​		B:构造方法

​			创建子类对象的时候，访问父类的构造方法，对父类的数据进行初始化。

​		C:成员方法

​			编译看左边，运行看右边。

​		D:静态方法

​			编译看左边，运行看左边。

​			(静态和类相关，算不上重写，所以，访问还是左边的)

多态的弊端：

​		不能使用子类的特有功能。

  解决办法：向下转型：

​			Zi z = (Zi)f;



### 1.18 抽象类和接口有什么区别

抽象类和接口有什么区别？

抽象类：

含有abstract修饰符的class 即为抽象类。abstract类不能创建实例对象；含有abstract的方法的类必须定义为abstract class ；

abstract class 里的方法不必是抽象的；抽象类中定义抽象方法必须放在具体子类中实现；所以呀，不能有抽象的构造方法或抽象的静态方法，

如果子类没有实现抽象父类中的所有方法，那么，子类也必须定义为抽象类。

一个类如果没有抽象方法，可不可以定义为抽象类?如果可以，有什么意义?

​	A:可以。

​	B:不让创建对象。

abstract不能和哪些关键字共存?

​	private	冲突

​	final		冲突	

​	static	无意义

接口（interface）可以说成是抽象类的特例。接口中的所有方法都必须是抽象的，接口中的方法定义默认为public  abstract 。可以单继承,也可以多继承。

接口中的变量是全局常量，即public static final修饰的。

相同点：不能被实例化，接口的实现类与抽象类的子类都只有实现了接口的方法才能被实例化。

区别：

1，抽象类中可以包含非抽象的普通方法，而接口中所有的方法必须是抽象的，不能有非抽象的普通方法。

2，一个类可以实现多个接口，但只能继承一个抽象类。

3，抽象类里可以有构造方法，而接口内不能有构造方法

4，抽象类中的抽象方法的访问类型可以是public ，protected和默认类型，但接口中的抽象方法只能是public类型的，

并且默认即为public abstract类型。

5，抽象类中可以包含静态方法，接口内不能包含静态方法。

6，抽象类和接口中都可以包含静态成员变量，抽象类中的静态成员变量的访问类型可以任意，但接口中定义的变量只能是public static final类型，必须赋值。

并且默认为public static final类型。

7，抽象类中可以有普通成员变量，而接口中不能有普通成员变量。

8，接口更多的是在系统框架设计方法发挥作用，主要定义模块之间的通信，而抽象类在代码实现方面发挥作用，可以实现代码的重用。

抽象类和接口的区别：

A:成员区别

​	抽象类：

​		成员变量：可以变量，也可以常量

​		构造方法：有

​		成员方法：可以抽象，也可以非抽象

​	接口：

​		成员变量：只可以常量

​		成员方法：只可以抽象

​		

B:关系区别

​	类与类

​		继承，单继承

​	类与接口

​		实现，单实现，多实现

​	接口与接口

​		继承，单继承，多继承

​		

C:设计理念区别

​	抽象类 被继承体现的是：”is a”的关系。抽象类中定义的是该继承体系的共性功能。

​	接口 被实现体现的是：”like a”的关系。接口中定义的是该继承体系的扩展功能。



### 1.19 java修饰符

public：所有包

private：当前类

protected：子类

default：当前包

对于default：子类和父类位于同一个包，子类具有访问权限。但是子类和父类没在一个包下，就没有访问权限。

这些修饰符只能修饰成员变量，不能修饰局部变量。

private和protect不能用来修饰类(只有public，abstract，或final能用来修饰类)

权限修饰符：

​			        本类		同一个包下(子类和无关类)	不同包下(子类)		不同包下(无关类)

private 		Y		

默认			  Y		     Y

protected	Y		      Y						                     Y

public		   Y		      Y						                     Y				                  Y



### 1.20 内部类

1.内部类：静态内部类，成员内部类，局部内部类，匿名内部类

2.静态内部类：只能访问外部类中的静态成员变量和静态方法。

3.成员内部类：可以访问外部类的任何方法，

3.局部内部类：局部静态内部类与静态内部类的基本特征相同，局部内部类和内部类的特征相同

4.匿名内部类：必须继承其他类或实现其他接口

内部的访问特点：

​		A:内部类可以直接访问外部类的成员，包括私有。

​		B:外部类要访问内部类的成员，必须创建对象。

成员内部类:

​		如何直接访问内部类的成员。

​		外部类名.内部类名 对象名 = 外部类对象.内部类对象;

  Outer.Inner oi = new Outer().new Inner();

成员内部类的修饰符：

​		private 为了保证数据的安全性

​		static 为了方便访问数据

​			注意：静态内部类访问的外部类数据必须用静态修饰。

//加了private后，就不能Outer.Inner oi = new Outer().new Inner();

静态内部类可以如下访问：

Outer.Inner oi = new Outer.Inner();

​	

### 1.21 数据类型

\1. 默认转换(从小到大的转换)

​		A:byte,short,char—int—long—float—double

​		B:byte,short,char相互之间不转换，他们参与运算首先转换为int类型

  boolean类型不能转换为其他的数据类型

ASCII码：

'a'		97

'A'		65

'0'		48

\2. 面试题：

​		short s=1;s = s+1; 

​		

​		short s=1;s+=1;

​		上面两个代码有没有问题，如果有，那里有问题。

​		

​		为什么第二个木有问题呢?

​			扩展的赋值运算符其实隐含了一个强制类型转换。

​			

​			s += 1;

​			不是等价于 s = s + 1;

​			而是等价于 s = (s的数据类型)(s + 1);

\3. &&和&的区别? 同理||和|的区别?

​		A:最终结果一样。

​		B:&&具有短路效果。左边是false，右边不执行。

4.位运算符：

​		&,|,^,~

​		<<,>>,>>>

​	注意：

​		要做位运算，首先要把数据转换为二进制。

5.	<<:左移	左边最高位丢弃，右边补齐0

​	>>:右移	最高位是0，左边补齐0；最高为是1，左边补齐1

​	>>>:无符号右移 无论最高位是0还是1，左边补齐0



### 1.22 String的方法

\* 构造方法：

 \* 		public String():空构造

 *		public String(byte[] bytes):把字节数组转成字符串

 *		public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串

 *		public String(char[] value):把字符数组转成字符串

 *		public String(char[] value,int index,int count):把字符数组的一部分转成字符串

 *		public String(String original):把字符串常量值转成字符串

 *

 \* 字符串的方法：

 \* 		public int length()：返回此字符串的长度。

\* String类的判断功能：

 \* boolean equals(Object obj):比较字符串的内容是否相同,区分大小写

 \* boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,忽略大小写

 \* boolean contains(String str):判断大字符串中是否包含小字符串

 \* boolean startsWith(String str):判断字符串是否以某个指定的字符串开头

 \* boolean endsWith(String str):判断字符串是否以某个指定的字符串结尾

 \* boolean isEmpty():判断字符串是否为空。

\* String类的获取功能

 \* int length():获取字符串的长度。

 \* char charAt(int index):获取指定索引位置的字符

 \* int indexOf(int ch):返回指定字符在此字符串中第一次出现处的索引。

 \* 		为什么这里是int类型，而不是char类型?

 \* 		原因是：'a'和97其实都可以代表'a'

 \* int indexOf(String str):返回指定字符串在此字符串中第一次出现处的索引。

 \* int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引。

 \* int indexOf(String str,int fromIndex):返回指定字符串在此字符串中从指定位置后第一次出现处的索引。

 \* String substring(int start):从指定位置开始截取字符串,默认到末尾。

 \* String substring(int start,int end):从指定位置开始到指定位置结束截取字符串。

\* String的转换功能：

 \* byte[] getBytes():把字符串转换为字节数组。

 \* char[] toCharArray():把字符串转换为字符数组。

 \* static String valueOf(char[] chs):把字符数组转成字符串。

 \* static String valueOf(int i):把int类型的数据转成字符串。

 \* 		注意：String类的valueOf方法可以把任意类型的数据转成字符串。

 \* String toLowerCase():把字符串转成小写。

 \* String toUpperCase():把字符串转成大写。

 \* String concat(String str):把字符串拼接。

\* String类的其他功能：

 \* 

 \* 替换功能：

 \* String replace(char old,char new)

 \* String replace(String old,String new)

 *

 \* 去除字符串两空格	

 \* String trim()

 \* 

 \* 按字典顺序比较两个字符串  

 \* int compareTo(String str)

 \* int compareToIgnoreCase(String str)



### 1.23 String，StringBuffer，StringBuilder和StringToken

String：不可变类

StringBuffer：可变类

String s = "Hello";

s += "World";

等价于：

StringBuffer sb = new StringBuffer(s);

s.append("World"); s = sb.toString();

StringBuilder不是线程安全的

效率值高->低 : StringBuilder->StringBuffer->String

\* 面试题：

 \* 1：String,StringBuffer,StringBuilder的区别?

 \* A:String是内容不可变的，而StringBuffer,StringBuilder都是内容可变的。

 \* B:StringBuffer是同步的，数据安全,效率低;StringBuilder是不同步的,数据不安全,效率高

\* 3:形式参数问题

 \* String作为参数传递

 \* StringBuffer作为参数传递 

 \* 

 \* 形式参数：

 \* 		基本类型：形式参数的改变不影响实际参数

 \* 		引用类型：形式参数的改变直接影响实际参数

 \* 

 \* 注意：

 \* 		String作为参数传递，效果和基本类型作为参数传递是一样的。



\* StringBuffer的构造方法：

 \* 		public StringBuffer():无参构造方法

 *		public StringBuffer(int capacity):指定容量的字符串缓冲区对象

 *		public StringBuffer(String str):指定字符串内容的字符串缓冲区对象

 \* StringBuffer的方法：

 *		public int capacity()：返回当前容量。	理论值

 *		public int length():返回长度（字符数）。 实际值

\* StringBuffer的添加功能：

 \* public StringBuffer append(String str):可以把任意类型数据添加到字符串缓冲区里面,并返回字符串缓冲区本身

 \* 

 \* public StringBuffer insert(int offset,String str):在指定位置把任意类型的数据插入到字符串缓冲区里面,并返回字符串缓冲区本身

\* StringBuffer的删除功能

 \* public StringBuffer deleteCharAt(int index):删除指定位置的字符，并返回本身

 \* public StringBuffer delete(int start,int end):删除从指定位置开始指定位置结束的内容，并返回本身

\* StringBuffer的替换功能：

 \* public StringBuffer replace(int start,int end,String str):从start开始到end用str替换

\* StringBuffer的反转功能：

 \* public StringBuffer reverse()

\* StringBuffer的截取功能:注意返回值类型不再是StringBuffer本身了

 \* public String substring(int start)

 \* public String substring(int start,int end)



### 1.24 BigDecimal

\* 构造方法：

 \* 		public BigDecimal(String val)

 \* 

 \* public BigDecimal add(BigDecimal augend)

 \* public BigDecimal subtract(BigDecimal subtrahend)

 \* public BigDecimal multiply(BigDecimal multiplicand)

 \* public BigDecimal divide(BigDecimal divisor)

 \* public BigDecimal divide(BigDecimal divisor,int scale,int roundingMode):商，几位小数，如何舍取

\* public BigInteger add(BigInteger val):加

 \* public BigInteger subtract(BigInteger val):减

 \* public BigInteger multiply(BigInteger val):乘

 \* public BigInteger divide(BigInteger val):除

 \* public BigInteger[] divideAndRemainder(BigInteger val):返回商和余数的数组



### 1.25 Date

Date d = new Date();

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

String s = sdf.format(d);

System.out.println(s);



### 1.26 Math

\* Math:用于数学运算的类。

 \* 成员变量：

 \* 		public static final double PI

 \* 		public static final double E

 \* 成员方法：

 \* 		public static int abs(int a)：绝对值

 *		public static double ceil(double a):向上取整

 *		public static double floor(double a):向下取整

 *		public static int max(int a,int b):最大值 (min自学)

 *		public static double pow(double a,double b):a的b次幂

 *		public static double random():随机数 [0.0,1.0)

 *		public static int round(float a) 四舍五入(参数为double的自学)

* public static double sqrt(double a):正平方根

  

### 1.27 Random

\* Random:产生随机数的类

 \* 

 \* 构造方法：

 \* 		public Random():没有给种子，用的是默认种子，是当前时间的毫秒值

 *		public Random(long seed):给出指定的种子

 *

 *		给定种子后，每次得到的随机数是相同的。

 *

 \* 成员方法：

 \* 		public int nextInt()：返回的是int范围内的随机数

* public int nextInt(int n):返回的是[0,n)范围的内随机数

  

### 1.28 正则表达式

A:字符

​	x 字符 x。举例：'a'表示字符a

​	\\ 反斜线字符。

​	\n 新行（换行）符 ('\u000A') 

​	\r 回车符 ('\u000D')

​	

B:字符类

​	[abc] a、b 或 c（简单类） 

​	[^abc] 任何字符，除了 a、b 或 c（否定） 

​	[a-zA-Z] a到 z 或 A到 Z，两头的字母包括在内（范围） 

​	[0-9] 0到9的字符都包括

​	

C:预定义字符类

​	. 任何字符。我的就是.字符本身，怎么表示呢? \.

​	\d 数字：[0-9]

​	\w 单词字符：[a-zA-Z_0-9]

​		在正则表达式里面组成单词的东西必须有这些东西组成

D:边界匹配器

​	^ 行的开头 

​	$ 行的结尾 

​	\b 单词边界

​		就是不是单词字符的地方。

​		举例：hello world?haha;xixi

​	

E:Greedy 数量词 

​	X? X，一次或一次也没有

​	X* X，零次或多次

​	X+ X，一次或多次

​	X{n} X，恰好 n 次 

​	X{n,} X，至少 n 次 

​	X{n,m} X，至少 n 次，但是不超过 m 次 



^[1-9]\d*$　 　 //匹配正整数

^-[1-9]\d*$ 　 //匹配负整数

^-?[1-9]\d*$　　 //匹配整数

^[1-9]\d*|0$　 //匹配非负整数（正整数 + 0）

^-[1-9]\d*|0$　　 //匹配非正整数（负整数 + 0）

^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$　　 //匹配正浮点数

^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$　 //匹配负浮点数

^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$　 //匹配浮点数

^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　　 //匹配非负浮点数（正浮点数 + 0）

^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$　　//匹配非正浮点数（负浮点数 + 0）

^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$　 //匹配浮点数

^-?(([1-9]\d*( | ([1-9]\d*\.[0-9]+) | (0\.\d*[1-9]\d*))$

^[1-9]\d*$		 // 匹配正整数

^[1-9]\d*$|^0$		 // 匹配非负整数（正整数 + 0）

^-?[1-9]\d*$		// 正负整数

^-?[1-9]\d*$|^0$		// 整数

^-?[1-9]\d*\.[0-9]+$|^-?0\.\d*[1-9]\d*$  	//正负浮点数（非0）

^-?([1-9]\d*|[1-9]\d*\.[0-9]+|0\.\d*[1-9]\d*)$		// 数字（非0）

^0$|^0\.0+$|^-?([1-9]\d*|[1-9]\d*\.[0-9]+|0\.\d*[1-9]\d*)$		// 数字（包括0）

^-?[1-9]\\d*(\\.\\d+)?$|^-?0(\\.\\d+)?$数字（包括0）

^0$|^0\.0+|^-?([1-9]\d*|[1-9]\d*\.[0-9]+|0\.\d*[1-9]\d*)$



### 1.29 JDBC 流程

1.JDBC （Java Data Base Connectivity,java 数据库连接）

JDBC是一种用于执行sql语句的java API，可以为多种关系数据库提供统一访问，它由一组用java语言编写的类和接口组成。

2.JDBC架构由两层组成：

(1)JDBC  API : 提供了应用程序对JDBC的管理连接

(2)JDBC  Driver API： 支持JDBC管理到驱动连接

3.数据库驱动

安装好数据库之后，我们的应用程序并不能直接使用数据库，必须通过相应的数据库驱动程序，通过数据库驱动程序去和数据库打交道。

其实也就是数据库厂商的JDBC接口的实现，即对Connection等接口的实现类的jar文件。

4.常用接口

(1)Driver接口

Driver接口由数据库厂商提供，作为java开发人员，只需要使用Driver接口就可以了。在编程中要连接数据库必须先安装特定厂商的数据库驱动程序，

不同的数据库有不同的装载方法。

装载Mysql驱动：Class.forName("com.mysql.jdbc.Driver")

装载Oracle驱动：Class.forName("oracle.jabc.driver.OracleDriver")

(2)DriverManager

这个类管理数据库驱动程序的列表。确定内容是否符合从java 应用程序使用的通信子协议正确的数据库驱动程序的连接请求。

(3)Connection 接口

Connection 与特定数据库的连接（会话），在上下文中执行sql语句并返回结果。此接口有接触数据库的所有方法，连接对象便是通信上下文，

即，与数据库中所有的通信都是通过此唯一的连接对象。

DriverManager.getConnection(url,user,password) 方法来建立url 中定义的数据库来连接

连接Mysql 数据库：Connection connection = DriverManager.getConnection("jdbc:mysql://host:port/database","user","password");

常用的方法：

​                 createStatement(): 创建向数据库发送sql的statement对象。

​                  prepareStatement（sql）：创建相数据库发送预编译sql的PrepredStatement对象。

​                 prepareCall(sql) : 创建执行存储过程的callableStatement对象。

​                 setAutoCommit(boolean autoCommit) : 设置事务是否自动提交。

​                 commit() :在连接上提交事务

​                 rollback（）：在此连接上回滚事务

(4)Statement 接口

用于执行静态sql语句并返回它所生成结果的对象

三种Statement 类：

Statement  由createStatement创建，用于发送简单的sql语句（不带参数）

PreparedStatement    继承自Statement 接口，由prepareStatement（sql）创建，用于发送含有一个或者多个参数的sql语句。

PreparedStatement对象采用预编译，比Statement对象效率更高。并且可以防止sql注入。

CallableStatement： 继承自PreparedStatement接口，由方法prepareCall（）创建，用于存储调用过程。

常用的Statement方法：

execute(String sql)          运行语句，返回是否有结果集

executeQuery（String sql） 运行select语句，返回ResultSet结果集

executeUpdate（String sql） 运行insert/delete/update 操作，返回更新的行数。

addBatch（String sql） 把多条sql语句放到一个批处理中

executeBatch（） 想数据库发送一批sql语句执行

(5)ResultSet接口

查询结果集，提供了检索不同类型字段的方法，常用的有：

getString(int index)   /  getString(String columnName)  获得在数据库里是varchar ，char 类型的数据对象

getFloat

getDate

getBoolean

getObjejct

ResultSet还提供了对结果集进行滚动的方法：

next() 移动到下一行

previous（） 移动到前一行

absolute（int row） 移动到指定行

beforeFirst（）移动到ResultSet 的最前面

afterLast（）移动到ResultSet 的最后面

使用后依次关闭对象及连接：ResultSet ->  Statement ->Connection

5.使用JDBC步骤

加载JDBC驱动程序 -->建立数据库连接Connection  --> 创建执行sql的语句Statement  --> 处理执行结果 ResultSet  --> 释放资源

(1). 注册驱动（一次即可）

​        方式一：Class.forName("com.Mysql.jdbc.Driver");

​     推荐使用该方式，不会对具体的驱动列产生依赖。

​       方式二: DriverManager.registerDriver(com.mysql.jdbc.Driver);

​     会造成DriverManager中产生两个一样的驱动，并会对具体的驱动类产生依赖

(2).建立连接

​      Connection con = DriverManager.getConnection(url,uesr,password);

(3).创建执行sql语句的Statement

Statement st = conn.createStatement();  

st.executeQuery(sql);

(4).处理结果集ResultSet

ResultSet rs = ps.executeQuery();

while(rs.next()){

​    rs.getString("col_name");

​    rs.getInt(2);

(5)释放资源

rs.close();

st.close();

conn.close();

6.事务（ACID特点，隔离级别，提交，回滚）

(1)事务的基本概念

​                      一组要么同时成功，要么同时失败的sql语句，是数据库操作的一个基本执行单元。

​                      事务开始于：

​                                      连接到数据库上，并执行一条DML语句（Insert/delete/update）。

​                                      前一个事务结束后，有输入了另外一条DML语句。

​                      事务结束于：

​                                      执行commit或rollback 语句。

​                                      执行一条DCL语句，例如create table 语句，在次此情况下，会自动执行commit语句

​                                      执行一条DCL语句，例如grant语句，在此情况下，会自动自行commit语句

​                                      断开与数据库的连接

​                                      执行了一条DML语句，该语句失败了，在此情况下，会为这个无效的DML语句执行rollback。

(2)事务的四大特点

​                    --atomicity 原子性

​                                  表示一个事务内的所有操作是一个整体，要么全成功，哟啊么全失败

​                    --consistency 一致性

​                                  表示一个事务内有一个操作失败时，所有的更改过的数据都必须回滚到修改前的状态

​                    --isolation 隔离性

​                                  事务 查看数据时数据所处的状态，要么时另一个并发事务修改它之前的状态，

​				  要么是另一事物修改它之后的状态，事务不会查看中间状态的数据。

​                    --durability 持久性

​                                  持久性事务完成之后，它对于系统的影响是永久性的

(3)事务的隔离级别

​                     --读取未提交数据

​                     --读取已提交数据

​                     --可重复读

​                     --序列化



## 2. 进阶篇

### 2.1 自定义注解的场景以及实现

一、元注解

java中有四种元注解：@Retention、@Inherited、@Documented、@Target

二、自定义注解使用场景

类属性自动赋值。

验证对象属性完整性。

代替配置文件功能，像spring基于注解的配置。

可以生成文档，像java代码注释中的@see,@param等

三、新建一个自定义注解

@Retention(RetentionPolicy.RUNTIME)

@Inherited

@Documented

@Target({ElementType.FIELD,ElementType.METHOD})

@interface MyAnno{

​    public String name() default "zhangsan";

​    public String email() default "hello@example.com";

}

 

//创建用户类

class  User{

 

​    @MyAnno(name = "zhang")

​    private String name;

 

​    @MyAnno(name = "zhang@example.com")

​    private String email;

 

​    @MyAnno(name = "sayHelloWorld")

​    public String sayHello(){

​        return "";

​    }



### 2.2 MVC 设计思想

1.mvc模式

mvc全名是model view controller，是模型视图和控制器的缩写，一种设计方式，是一种业务逻辑、数据和界面分离的方式组织代码

2、MVC框架

是为了解决传统MVC模式（JSP+Servlet+JavaBean）的一些问题而出现的框架。

传统的mvc模式问题：

①所有的Servlet和Servlet映射都要配置在web.xml中，如果项目太大，web.xml就非常庞大，并且不能实现模块化管理；

②Servlet 的主要功能就是 接受表单数据、处理逻辑业务、分发转向；

③接受参数比较麻烦，不能通过model来接受，只能单个接受，接受完成后转换封装model。

④跳转页面比较单一，forward和redirect；

常见的框架有struts和springmvc。



### 2.3 动态规划

1.动态规划基本概念：

  动态规划过程是：每次决策依赖于当前的状态，又随机引起状态的转移。一个决策序列就是在变化的状态中产生的，所以，

这种多阶段最优化决策解决问题的过程就称为动态规划

2.基本思想与策略

  基本思想与分治法类似，也是将待求解的问题分解为若干个子问题（阶段），按顺序求解子阶段，前一子问题的解，

为后一子问题的求解提供了有用的信息。在求解任一子问题时，列出各种可能的局部解，通过决策保留那些有可能达到最优的局部解，

丢弃其他局部解。依次解决各子问题，最后一个子问题就是初始问题的解。

由于动态规划解决的问题多数有重叠子问题这个特点，为减少重复计算，对每一个子问题只解一次，将其不同阶段的不同状态保存在一个二维数组中。

与分治法最大的差别是：适合于用动态规划法求解的问题，经分解后得到的子问题往往不是互相独立的

（即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解）。

3.适用的情况

能采用动态规划求解的问题的一般要具有3个性质：

(1) 最优化原理：如果问题的最优解所包含的子问题的解也是最优的，就称该问题具有最优子结构，即满足最优化原理。

(2) 无后效性：即某阶段状态一旦确定，就不受这个状态以后决策的影响。也就是说，某状态以后的过程不会影响以前的状态，只与当前状态有关。

(3) 有重叠子问题：即子问题之间是不独立的，一个子问题在下一阶段决策中可能被多次使用到。

（该性质并不是动态规划适用的必要条件，但是如果没有这条性质，动态规划算法同其他算法相比就不具备优势）

4.求解的基本步骤

动态规划所处理的问题是一个多阶段决策问题，一般由初始状态开始，通过对中间阶段决策的选择，达到结束状态。这些决策形成了一个决策序列，

同时确定了完成整个过程的一条活动路线(通常是求最优的活动路线)。如图所示。动态规划的设计都有着一定的模式，一般要经历以下几个步骤。

初始状态→│决策１│→│决策２│→…→│决策ｎ│→结束状态

(1)划分阶段：按照问题的时间或空间特征，把问题分为若干个阶段。在划分阶段时，注意划分后的阶段一定要是有序的或者是可排序的，

否则问题就无法求解。

(2)确定状态和状态变量：将问题发展到各个阶段时所处于的各种客观情况用不同的状态表示出来。当然，状态的选择要满足无后效性。

(3)确定决策并写出状态转移方程：因为决策和状态转移有着天然的联系，状态转移就是根据上一阶段的状态和决策来导出本阶段的状态。

所以如果确定了决策，状态转移方程也就可写出。但事实上常常是反过来做，根据相邻两个阶段的状态之间的关系来确定决策方法和状态转移方程。

(4)寻找边界条件：给出的状态转移方程是一个递推式，需要一个递推的终止条件或边界条件

一般，只要解决问题的阶段、状态和状态转移决策确定了，就可以写出状态转移方程（包括边界条件）。

实际应用中可以按以下几个简化的步骤进行设计：

(1)分析最优解的性质，并刻画其结构特征。

(2)递归的定义最优解。

(3)以自底向上或自顶向下的记忆化方式（备忘录法）计算出最优值

(4)根据计算最优值时得到的信息，构造问题的最优解

5.算法实现的说明

使用动态规划求解问题，最重要的就是确定动态规划三要素：

（1）问题的阶段

（2）每个阶段的状态

（3）从前一个阶段转化到后一个阶段之间的递推关系。

确定了动态规划的这三要素，整个求解过程就可以用一个最优决策表来描述，最优决策表是一个二维表，其中行表示决策的阶段，

列表示问题状态，表格需要填写的数据一般对应此问题的在某个阶段某个状态下的最优值（如最短路径，最长公共子序列，最大价值等），

填表的过程就是根据递推关系，从1行1列开始，以行或者列优先的顺序，依次填写表格，最后根据整个表格的数据通过简单的取舍或者运算

求得问题的最优解。



### 2.4 记忆化搜索（搜索+dp思想）

记忆化搜索（搜索+dp思想）

1.简介：

记忆化搜索：搜索+动态规划数组记录上一层计算结果，避免过多的重复计算

算法上依然是搜索的流程，但是搜索到的一些解用动态规划的那种思想和模式作一些保存；一般说来，动态规划总要遍历所有的状态，

而搜索可以排除一些无效状态。更重要的是搜索还可以剪枝，可能剪去大量不必要的状态，因此在空间开销上往往比动态规划要低很多。

记忆化算法在求解的时候还是按着自顶向下的顺序，但是每求解一个状态，就将它的解保存下来，以后再次遇到这个状态的时候，就不必重新求解了。

这种方法综合了搜索和动态规划两方面的优点，因而还是很有实用价值的。可以归纳为：记忆化搜索=搜索的形式+动态规划的思想

