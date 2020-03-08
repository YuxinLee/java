# 注解

## 1.注解作用

1.告诉编译器如何运行程序

2.简化（取代）配置文件

 注解的本质就是一个继承了 Annotation 接口的接口。 

## 2.元注解

### 2.1 @Target  

指定注解的可用范围：

```
@Target({
	TYPE,   类
	FIELD,   字段
	METHOD,  方法
	PARAMETER,  参数
	CONSTRUCTOR, 构造器
	LOCAL_VARIABLE 局部变量
	PACKAGE 包
})
```

### 2.2 @Retention  

指定注解的生命周期  

```
@Retention(RetentionPolicy.SOURCE)  注解只在源码级别有效，不会写入class文件
@Retention(RetentionPolicy.CLASS)   注解在字节码即别有效，会写入class文件 **默认值**
@Retention(RetentionPolicy.RUNTIME)  注解在运行时期有效，永久保存，可以反射获取
```

### 2.3 @Inherited

 @Inherited 注解修饰的注解是具有可继承性的，也就说我们的注解修饰了一个类，而该类的子类将自动继承父类的该注解。 

## 3. 内置注解

### 3.1 @Override

```
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.SOURCE) 
public @interface Override {
 } 
```

它没有任何的属性，所以并不能存储任何其他信息。它只能作用于方法之上，编译结束后将被丢弃。它就是一种典型的『标记式注解』，仅被编译器可知，编译器在对 java 文件进行编译成字节码的过程中，一旦检测到某个方法上被修饰了该注解，就会去匹对父类中是否具有一个同样方法签名的函数，如果不是，自然不能通过编译。

### 3.2  @Deprecated 

```
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}
```

 依然是一种『标记式注解』，永久存在，可以修饰所有的类型，作用是，标记当前的类或者方法或者字段等已经不再被推荐使用了，可能下一次的 JDK 版本就会删除。 