package guanglianda.test;

/*
*
将给定的数转换为字符串，原则如下：1对应 a，2对应b，…..26对应z，例如12258可以转换为"abbeh",
"aveh", "abyh", "lbeh" and "lyh"，个数为5，编写一个函数，给出可以转换的不同字符串的个数。
* */

public class 数转换为字符串 {
    public static void main(String[] args){
        System.out.println(cal26("12258"));
    }

    public static int cal26(String num){
        int len = num.length();
        if(len==1){
            return 1;
        }else if(len==2){
            if(Integer.parseInt(num)<=26){
                return 2;
            }else{
                return 1;
            }
        }else{
            return cal26(num.substring(1,len))+((cal26(num.substring(0,2)))-1)*cal26(num.substring(2,len));
        }
    }
}
