package base.exception_learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异常：程序出现了不正常的情况。
 *
 * 程序的异常：Throwable
 *      严重的问题：Error,不处理,这种问题一般都是严重的问题,比如内存溢出
 *      异常：Exception
 *          编译器异常：不是RuntimeException的异常，必须进行处理，不处理的话编译不能通过
 *          运行期异常：RuntimeException,这个问题的出现表明代码不够严谨，需要修正代码
 *
 * 如何程序出现了问题，我们没有做任何处理，最终jvm会做出默认的处理。
 *     把异常的名称，原因及出现的问题等信息输出在控制台。同时会结束程序。
 *
 * 处理异常的方法1：
 *      (1)try...catch...finally
 *      (2)try{
 *
 *      }catch(异常类名 变量名){
 *
 *      }catch(异常类名 变量名){
 *
 *      }
 *      (3)JDK7
 *      try{
 *
 *      }catch(异常名1 | 异常名2 | ...  变量 ){
 *          1.处理方式是一致的。2.多个异常间必须是平级关系。
 *      }
 *
 *    注意：
 *      A:try里面的代码越少越好
 *      B:catch里面必须有内容，给出一个简单的提示也可以
 *      C:平级关系的异常谁前谁后无所谓，如果出现了子父关系，父必须在后面。
 *      D:一旦try里面出了问题，就会在这里把问题给抛出去，然后和catch里面的问题进行匹配，
 *      一旦有匹配的，就执行catch里面的处理，然后结束了try...catch,继续执行后面的语句。
 *
 * 异常中的几个方法：
 *  1.public String getMessage():异常的消息字符串
 *  2.public String toString():返回异常的简单信息描述（此对象的类的 name(全路径名)+“： ”+getMessage()的内容）
 *  3.printStackTrace() 获取异常类名和异常信息，以及异常出现在程序中的位置。返回值void。把信息输出在控制台。
 *  使用printStackTrace()方法会显示错误信息，但是不会终止程序，程序会依然运行
 */

public class Exception_Demo1 {
    public static void main(String[] args) {
        String s = "2014-11-20";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(s); // 创建了一个ParseException对象，然后抛出去，和catch里面进行匹配
            System.out.println(d);
        } catch (ParseException e) { // ParseException e = new ParseException();
            // ParseException
            // e.printStackTrace();

            // getMessage()
            // System.out.println(e.getMessage());
            // Unparseable date: "2014-11-20"

            // toString()
            // System.out.println(e.toString());
            // java.text.ParseException: Unparseable date: "2014-11-20"

            e.printStackTrace();
            //跳转到某个指定的页面(index.html)
        }

        System.out.println("over");
    }

}
