package base.exception_learning;

import java.util.ArrayList;
import java.util.List;


/**
 * 结论
 * 1、不管有没有异常，finally中的代码都会执行
 * 2、当try、catch中有return时，finally中的代码依然会继续执行
 * 3、finally是在return后面的表达式运算之后执行的，此时并没有返回运算之后的值，
 *    而是把值保存起来，不管finally对该值做任何的改变，返回的值都不会改变，依然返回保存起来的值。
 *    也就是说方法的返回值是在finally运算之前就确定了的。（如果try、catch中有return语句，finally中没有return，
 *    那么在finally中修改除包装类型和静态变量、全局变量以外的数据都不会对try、catch中返回的变量有任何的影响
 *    （包装类型、静态变量会改变、全局变量））
 * 4、finally代码中最好不要包含return，程序会提前退出，也就是说返回的值不是try或catch中的值
 * 5、finally中避免再次抛出异常，一旦finally中发生异常，代码执行将会抛出finally中的异常信息，
 *    try、catch中的异常将被忽略
 *    
 *   在实际项目中，finally常常是用来关闭流或者数据库资源的，并不额外做其他操作。 
 *   
 *   
 * 6、在执行try、catch中的return之前一定会执行finally中的代码（如果finally存在）
 *    如果finally中有return语句，就会直接执行finally中的return方法，
 *    所以finally中的return语句一定会被执行的。编译器把finally中的return语句标识为一个warning.
 *
 * 注意：如果在执行到finally之前jvm退出了，就不能执行了。System.exit(0);
 */
public class Exception_Demo3 {

	public static void main(String[] args) {
		
//		int rs = foo2();
//		System.out.println("main rs = " + rs);
		
//		List<Object>  obList = testWrap();
//		System.out.println("main test " + obList);
		
		
		int rs = testBasic();
		System.out.println("main rs = " + rs);
		
	}
	
	/**
	 * 基本类型
	 * try中无return，finally会执行
	 */
	public static int foo(){
		
		int i = 1; 
        try{
            i++;
            System.out.println("try block, i = "+i);
        }catch(Exception e){
            i++;
            System.out.println("catch block i = "+i);
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
        }
        return i;
	}
	
	/**
	 * 基本类型
	 * try中有return
	 */
	public static int foo2(){
		int i = 1; 
        try{
            i++;
            System.out.println("try block, i = "+i);
            return i;
        }catch(Exception e){
            i++;
            System.out.println("catch block i = "+i);
            return i;
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
        }
	}
	
	/**
	 * 包装类
	 * finally里对list集合的操作生效了，这是为什么呢。
	 * 我们知道基本类型在栈中存储，而对于非基本类型是存储在堆中的，返回的是堆中的地址，因此内容被改变了。
	 */
	public static  List<Object> testWrap(){
		List<Object> list = new ArrayList<>();
        try{
            list.add("try");
            System.out.println("try block");
            return list;
        }catch(Exception e){
            list.add("catch");
            System.out.println("catch block");
            return list;
        }finally{
            list.add("finally");
            System.out.println("finally block ");
        }
	}
	
	/**
	 * 如果不加@SuppressWarnings("finally")，eclipse会出现黄色警告
	 * 最后结果return是从finally中返回，也就是说JVM是忽略了try中的return语句
	 * 
	 * finally中有return语句，try、catch中的异常被消化掉了，屏蔽了异常的发生，
	 * 这与初期使用try、catch的初衷是相违背的，因此编译器也会提示警告。
	 */
	@SuppressWarnings("finally")
	public static  int testBasic(){
        int i = 1; 
        try{
            i++;
            System.out.println("try block, i = "+i);
            return i;
        }catch(Exception e){
            i ++;
            System.out.println("catch block i = "+i);
            return i;
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
            return i;
        }
	}

    public static int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            return a;
            /*
             * return a在程序执行到这一步的时候，这里不是return a而是return 30;这个返回路径就形成了。
             * 但是呢，它发现后面还有finally，所以继续执行finally的内容，a=40
             * 再次回到以前的返回路径，继续走return 30;
             */
        } finally {
            a = 40;
            return a;//如果这样结果就是40了。
        }
        // return a;
    }

}
