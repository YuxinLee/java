package base.string_learning;

/**
 * String：字符串常量, 适用于少量的字符串操作的情况
 * StringBuffer：字符串变量（线程安全）,适用于单线程下在字符缓冲区进行大量操作的情况
 * StringBuilder：字符串变量（非线程安全）,适用多线程下在字符缓冲区进行大量操作的情况
 *
 * 三种字符串的操作效率：StringBuilder > StringBuffer > String
 *
 */

public class String_Demo1 {
    public static void main(String[] args){
//        String s3 = "Hello" + "World";
//        String s4 = "HelloWorld";
//        System.out.println(s3==s4);   //true

        String s1 = "Hello";
        String s2 = "World";
        String s3 = s1 + "World";   //进行StringBuilder转换
        String s4 = s1 + s2;    //进行StringBuilder转换
        System.out.println(s3==s4); //false
    }

}
