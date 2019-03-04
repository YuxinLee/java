package base.regex;

/*
校验邮箱:
定义邮箱的规则
  			1517806580@qq.com
  			liuyi@163.com
  			linqingxia@126.com
 			fengqingyang@sina.com.cn
 			fqy@itcast.cn
 */

import java.util.Scanner;

public class RegexDemo2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入邮箱：");
        String email = in.next();
        //[a-zA-Z_0-9]包括下划线_
//        String regex = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]{2,6}(\\.[a-zA-Z_0-9]{2,3})+";
        String regex = "\\w+@\\w{2,6}(\\.\\w{2,3})+";
        //调用功能，判断即可
        boolean flag = email.matches(regex);

        //输出结果
        System.out.println("flag:"+flag);
    }
}
