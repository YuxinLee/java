java程序运行时的内存分配情况

代码如下：

```java
Test test = new Test();
int date = 9;
BrithDate d1 = new BirthDate(7, 7, 1970);
BrithDate d2 = new BirthDate(1, 1, 2000);
test.change1(date);
test.change2(d1);
test.change3(d2);

public void change1(int i) {
    i = 1234;
}

public void change2(BrithDate b) {
    b = new BirthDate(22, 2, 2004);
}

public void change2(BrithDate b) {
    b.setDay(22);
}

```

1. JVM自动寻找main方法，执行第一句代码，创建一个Test类的实例，在栈中分配一块内存，存放一个指向堆区对象的指针110925。
2. 创建一个int型的变量date，由于是基本类型，直接在栈中存放date对应的值9
3. 创建两个BrithDate类的实例d1、d2，在栈中分别存放了对应的指针指向各自的对象（堆中）。他们在实例化时调用了有参数的构造方法，因此对象中有自定义的初始值。
4. 调用test对象的change1方法，并且以date为参数。JVM读到这段代码时，检测到i是局部变量，因此会把它放在栈中，并且把date的值赋给i。



d1：2384943

d2：4839042

date：9

test：110925

