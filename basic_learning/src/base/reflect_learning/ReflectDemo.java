package base.reflect_learning;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射：就是通过class文件对象，去使用该文件中的成员变量，构造方法，成员方法。
 *
 * Person p = new Person();
 * p.使用
 *
 * 要想这样使用，首先你必须得到class文件对象，其实也就是得到Class类的对象。
 * Class类：
 * 		成员变量	Field
 * 		构造方法	Constructor
 * 		成员方法	Method
 *
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        /**
         *
         * 获取class文件对象的方式：
         * 1. Object类的getClass()方法
         * 2. 数据类型的静态属性class
         * 3. Class类中的静态方法:Class.forName(全限定名)
         * 一般使用第3种，因为第三种是一个字符串，而不是一个具体的类名。这样我们就可以把这样的字符串配置到配置文件中。
         *
         */
        //方式1
        Person p = new Person();
        Class c = p.getClass();

        Person p2 = new Person();
        Class c2 = p2.getClass();

        System.out.println(p == p2);// false
        System.out.println(c == c2);// true

        //方式2
        Class c3 = Person.class;
        System.out.println(c==c3);// true

        //方式3
        Class c4 = Class.forName("base.reflect_learning.Person");//带包的全路径
        System.out.println(c4==c);// true

        /**
         *
         * 获取构造方法:
         * 1. public Constructor[] getConstructors()：获取所有"公有的"构造方法
         * 2. public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
         * 3. public Constructor getConstructor(Class... parameterTypes):获取某个"公有的"构造方法
         * 4. public Constructor getDeclaredConstructor(Class... parameterTypes):
         *    获取"某个构造方法"可以是私有的，或受保护、默认、公有
         *
         * 调用构造方法:Constructor.newInstance(Object... initargs)
         *
         */

        // public Constructor<T> getConstructor(Class<?>... parameterTypes)
        // 参数表示的是：你要获取的构造方法的构造参数个数及数据类型的class字节码文件对象
        Constructor con = c.getConstructor();// 返回的是构造方法对象
        // 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
        Object obj = con.newInstance();
        System.out.println(obj);
        //获取带参构造方法创建对象
        Constructor con1 = c.getConstructor(String.class, int.class, String.class);
        Object obj1 = con1.newInstance("xiaowang", 27, "北京");
        System.out.println(obj1);

        // 获取私有构造方法对象
        Constructor con2 = c.getDeclaredConstructor(String.class);
        // 用该私有构造方法创建对象
        // IllegalAccessException:非法的访问异常。
        // 暴力访问
        con2.setAccessible(true);// 值为true则指示反射的对象在使用时应该取消Java语言访问检查。
        Object obj2 = con2.newInstance("xaiozhang");

        System.out.println(obj2);


        // 通过无参构造方法创建对象
        Constructor con3 = c.getConstructor();
        Object obj3 = con3.newInstance();
        System.out.println(obj3);

        /**
         *
         * 获取成员变量：
         * public Field[] getFields():获取所有的公有字段
         * public Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有
         * public Field getField(String name):获取某个"公有的"字段
         * public Field getDeclaredField(String name):获取某个字段(可以是私有的)
         *
         * 设置成员变量的值:Field.set(Object obj, Object value)
         *
         */

        // 获取单个的成员变量
        // 获取address并对其赋值
        Field addressField = c.getField("address");
        // public void set(Object obj,Object value)
        addressField.set(obj3, "北京"); // 给obj对象的addressField字段设置值为"北京"
        System.out.println(obj3);

        // 获取name并对其赋值
        // NoSuchFieldException
        Field nameField = c.getDeclaredField("name");
        // IllegalAccessException
        nameField.setAccessible(true);
        nameField.set(obj3, "xiaoxiao");
        System.out.println(obj3);

        // 获取age并对其赋值
        Field ageField = c.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(obj3, 27);
        System.out.println(obj3);


        Constructor con4 = c.getConstructor();
        Object obj4 = con.newInstance();

        /**
         *
         * 获取成员方法：
         * public Method[] getMethods():获取所有公有方法（包含了父类的方法也包含Object类）
         * public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括父类方法)
         * public Method getMethod(String name, Class<?>... parameterTypes)：获取某个公有方法
         * public Method getDeclaredMethod(String name, Class<?>... parameterTypes)：获取某个方法（可以是私有的）
         * 调用成员方法:Method. invoke(Object obj, Object... args)
         *
         */

        // 获取单个方法并使用
        // public void show()
        // public Method getMethod(String name,Class<?>... parameterTypes)
        // 第一个参数表示的方法名，第二个参数表示的是方法的参数的class类型
        Method m1 = c.getMethod("show");
        // public Object invoke(Object obj,Object... args)
        // 返回值是Object接收,第一个参数表示对象是谁，第二参数表示调用该方法的实际参数
        m1.invoke(obj4); // 调用obj对象的m1方法

        Method m2 = c.getMethod("method", String.class);
        m2.invoke(obj4, "hello");

        // private void function()
        Method m4 = c.getDeclaredMethod("function");
        m4.setAccessible(true);
        m4.invoke(obj4);

        /**
         *
         * 获取类实现的接口：
         * public Class<?>[] getInterfaces()
         * 获取父类
         * public native Class<? super T> getSuperclass()
         * 获取修饰符
         * public native int getModifiers()
         *
         */
    }
}

class Person {
    private String name;
    int age;
    public String address;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void show() {
        System.out.println("show");
    }

    public void method(String s) {
        System.out.println("method " + s);
    }

    public String getString(String s, int i) {
        return s + "---" + i;
    }

    private void function() {
        System.out.println("function");
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", address=" + address
                + "]";
    }

}

