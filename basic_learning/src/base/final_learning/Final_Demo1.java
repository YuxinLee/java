package base.final_learning;

/**
 *
 * 当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，则编译器会把它当做编译期常量使用
 * （只有在编译期间能确切知道final变量值的情况下，编译器才会进行这样的优化，如test()的例子，编译器就不会优化）
 */

public class Final_Demo1 {
    public static void main(String[] args) {
        String a = "Hello2";
        final String b = "Hello";
        String d = "Hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));   //true
        System.out.println((a == e));   //false
        test();
    }

    public static String getHello(){
        return "Hello";
    }

    public static void test(){
        String a = "Hello2";
        final String b = getHello();
        String c = b+2;
        System.out.println(a == c); //false
    }
}
