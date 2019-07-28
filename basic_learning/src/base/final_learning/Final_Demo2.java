package base.final_learning;

/**
 *
 * 引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的。
 */

public class Final_Demo2 {
    private final int i = 0;
    private Object obj;


    public static void main(String[] args) {


        //***************************************//
        final MyClass myClass = new MyClass();
        System.out.println(++myClass.i);
        //执行结果为1，这说明引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的。

    }

    static class MyClass {
        public int i = 0;
    }
}
