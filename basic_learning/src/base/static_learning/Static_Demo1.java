package base.static_learning;

/**
 *
 * <clinit>()方法是由编译器自动收集类中的所有类变量(static变量)的赋值动作和静态语句块(static{}块)中的语句合并产生的，
 * 编译器收集的顺序是由语句在源文件中出现的顺序所决定的,静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，
 * 在前面的静态语句块可以赋值，但是不能访问
 */

public class Static_Demo1 {
    static {
        i = 1;  //编译通过
        //System.out.println(i);    //编译报错，非法向前引用
    }

    static int i = 5;
}
