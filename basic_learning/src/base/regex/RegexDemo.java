package base.regex;

/*
校验qq号码
要求：
1.要求必须是5-15位数字
2.0不能开头
 */

import java.util.Scanner;

public class RegexDemo {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你的QQ号码：");
        String qq = in.next();
        System.out.println("checkQQ：" + checkQQ(qq));
    }

    public static boolean checkQQ(String qq){
        //return qq.matches("[1-9][0-9]{4,14}");
        return qq.matches("[1-9]\\d{4,14}");
    }
}
