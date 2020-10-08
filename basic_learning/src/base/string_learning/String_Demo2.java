package base.string_learning;

/**
 * String：字符串常量, 适用于少量的字符串操作的情况
 * StringBuffer：字符串变量（线程安全）,适用于单线程下在字符缓冲区进行大量操作的情况
 * StringBuilder：字符串变量（非线程安全）,适用多线程下在字符缓冲区进行大量操作的情况
 *
 * 三种字符串的操作效率：StringBuilder > StringBuffer > String
 *
 */

public class String_Demo2 {
    public static void main(String[] args){

        String str1 = "ABC";
        String str2 = "您好呀";

        System.out.println(str1.length());
        System.out.println(str2.length());

    }

}
