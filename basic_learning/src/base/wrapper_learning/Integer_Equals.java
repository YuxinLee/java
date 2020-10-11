package base.wrapper_learning;

/**
 * Integer的数据直接赋值，如果在-128到127之间，会直接从缓冲池里获取数据
 * new Integer()生成的变量指向堆中新建的对象，因此肯定不相等
 */
public class Integer_Equals {
    public static void main(String[] args) {
        Integer i1 = new Integer(127);
        Integer i2 = new Integer(127);
        System.out.println(i1 == i2);       // false
        System.out.println(i1.equals(i2));  // true
        System.out.println("-------------------");

        Integer i3 = new Integer(128);
        Integer i4 = new Integer(128);
        System.out.println(i3 == i4);       // false
        System.out.println(i3.equals(i4));  // true
        System.out.println("-----------");

        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i5 == i6);       // false
        System.out.println(i5.equals(i6));  // true
        System.out.println("-----------");

        Integer i7 = 127;
        Integer i8 = 127;
        System.out.println(i7 == i8);       // true
        System.out.println(i7.equals(i8));  // true

    }
}
