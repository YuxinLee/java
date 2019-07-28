package base.static_learning;

/**
 *
 * 由于父类的<clinit>()方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作
 * <clinit>()方法只会执行一次，即类的初始化只进行一次。
 */

public class Static_Demo2 {
    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}

class Parent{
    public static int A = 1;
    static {
        A = 2;
    }
}

class Sub extends Parent{
    public static int B = A;
}
