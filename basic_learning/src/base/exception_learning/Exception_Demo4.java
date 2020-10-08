package base.exception_learning;

import java.util.ArrayList;
import java.util.List;


/**
 * 空指针异常
 */
public class Exception_Demo4 {

	public static void main(String[] args) {
		
        Exception exception = new NullPointerException();
        System.out.println(exception.getMessage());
        String str1 = exception.toString();
        String str2 = exception.getMessage();
        System.out.println(str2);
		
		

		
	}

}
