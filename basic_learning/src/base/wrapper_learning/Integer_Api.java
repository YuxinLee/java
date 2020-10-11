package base.wrapper_learning;

/**
 * Integer包装类的API方法：
 * public static String toBinaryString(int i)：二进制
 * public static String toOctalString(int i)：八进制
 * public static String toHexString(int i)：十六进制
 * public static String toString(int i,int radix)：十进制到其他进制
 * public static int parseInt(String s,int radix)：其他进制到十进制
 * public static final int MAX_VALUE：最大值
 * public static final int MIN_VALUE：最小值
 *
 * Integer的构造方法：
 * public Integer(int value)
 * public Integer(String s)
 *
 * int类型和String类型的相互转换
 * int -- String： String.valueOf(number)
 * String -- int： Integer.parseInt(s)
 */
public class Integer_Api {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(100));
        System.out.println(Integer.toOctalString(100));
        System.out.println(Integer.toHexString(100));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    /**
     * 自动装箱：把基本类型转换为包装类类型
     * 自动拆箱：把包装类类型转换为基本类型
     */
    public static void demo() {
        // 定义了一个int类型的包装类类型变量i
        // Integer i = new Integer(100);
        Integer ii = 100;
        ii += 200;
        System.out.println("ii:" + ii);

        // 通过反编译后的代码
        // Integer ii = Integer.valueOf(100); //自动装箱
        // ii = Integer.valueOf(ii.intValue() + 200); //自动拆箱，再自动装箱
        // System.out.println((new StringBuilder("ii:")).append(ii).toString());
    }

}
