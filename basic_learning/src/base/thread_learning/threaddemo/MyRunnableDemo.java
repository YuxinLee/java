package base.thread_learning.threaddemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * 方式2：实现Runnable接口
 * 步骤：
 * 		A:自定义类MyRunnable实现Runnable接口
 * 		B:重写run()方法
 * 		C:创建MyRunnable类的对象
 * 		D:创建Thread类的对象，并把C步骤的对象作为构造参数传递
 */
public class MyRunnableDemo {
    public static void main(String[] args) {
        MyRunnable my = new MyRunnable();
        // Thread(Runnable target, String name)
        Thread t1 = new Thread(my, "线程1");
        Thread t2 = new Thread(my, "线程2");
        Thread t3 = new Thread(my, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyRunnable implements Runnable{

    private static Map<String, People> hashMap = new HashMap<>();

    static {
        hashMap.put("s1", new People("people1", 11));
        hashMap.put("s2", new People("people2", 12));
        hashMap.put("s3", new People("people3", 11));
    }

//    private static Iterator<Map.Entry<String, People>> iterator = hashMap.entrySet().iterator();
//
    @Override
    public void run() {
        People p = hashMap.entrySet().iterator().next().getValue();
        System.out.println(p);
//        synchronized(this) {
//            People p = iterator.next().getValue();
//            System.out.println(p);
//        }

    }
}

class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

