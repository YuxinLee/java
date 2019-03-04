package yuanfudao.test;

/*
* 用字符串中的每一个字符(取出换行符和结束符)代表一位老师，输出排好后的队形。要求Y字
* 除去中心点外，上下半部分登高，按照从左到右，从上到下进行排序队形中没人的地方用空格
* 占位，输入数据保证可以排出一个完整的Y字，即长度为3K+1(k>=1)
*
* 例如7个x
* x---x
* -x-x
* --x
* --x
* --x
*
* 示例：
* 输入：
* 7
* abcdefg
*
* 输出：
* a   b
*  c d
*   e
*   f
*   g
*
* */

import java.util.Scanner;

public class Y型排队 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            String string = in.next();
            int number = (n-1)/3;
            char[] chs = string.toCharArray();

            for(int i=0;i<number;i++){
                printKongGe(i);
                System.out.print(chs[i*2]);
                printKongGe(2*(number-i-1)+1);
                System.out.print(chs[i*2+1]);
                System.out.println();
            }
            for(int i=number*2;i<n;i++){
                printKongGe(number);
                System.out.println(chs[i]);
            }
        }
    }

    public static void printKongGe(int count){
        for(int i=0;i<count;i++){
            System.out.print(" ");
        }
    }
}
