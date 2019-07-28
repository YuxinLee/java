package base.exception_learning;

/**
 * 针对有些异常我们没有权限处理，此时需要使用另一种处理方案：抛出
 *
 * 格式：
 *      throws 异常类名
 *      注意：这个格式必须跟在方法的括号后面。尽量不要在main方法抛出异常
 *          编译期异常抛出，将来调用者必须处理。
 *          运行期异常抛出，将来调用可以不用处理。
 *
 *      throw:如果出现了异常情况，我们可以把该异常抛出，这个时候的抛出的应该是异常的对象。
 *
 * throws和throw的区别(面试题)
 * 	throws
 * 		用在方法声明后面，跟的是异常类名
 * 		可以跟多个异常类名，用逗号隔开
 * 		表示抛出异常，由该方法的调用者来处理
 * 		throws表示出现异常的一种可能性，并不一定会发生这些异常
 * 	throw
 * 		用在方法体内，跟的是异常对象名
 * 		只能抛出一个异常对象名
 * 		表示抛出异常，由方法体内的语句处理
 * 		throw则是抛出了异常，执行throw则一定抛出了某种异常
 *
 * 	自定义异常：
 * 	   两种方式：
 * 	       A:继承Exception
 *         B:继承RuntimeException
 *      注意事项：
 *          A：子类重写父类方法时，子类的方法必须抛出相同的异常或父类异常的子类。
 *          B:如果父类抛出了多个异常,子类重写父类时,只能抛出相同的异常或者是他的子集,子类不能抛出父类没有的异常
 *          C:如果被重写的方法没有异常抛出,那么子类的方法绝对不可以抛出异常,如果子类方法内有异常发生,那么子类只能try,不能throws
 */

public class Exception_Demo2 {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        try {
            teacher.check(100);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}

class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}

class Teacher {
    public void check(int score) throws MyException {
        if (score > 100 || score < 0) {
            throw new MyException("分数必须在0-100之间");
        }
    }
}
