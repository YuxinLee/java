package jindong.test;

/*
* 1.问题：
* 小B喜欢用计算机程序来解决数学问题。现在，她正在玩一个数值变换的游戏。她发现计算机中经常用不同的进制表示同一个数，
* 如十进制数123表达为16进制时只包含两位数7、11（B），用八进制表示时为三位数1、7、3。按不同进制表达时，
* 各个位数的和也不同，如上述例子中十六进制和八进制中各位数的和分别是18和11。
* 小B感兴趣的是，一个数A如果按2到A-1进制表达时，各个位数之和的均值是多少？她希望你能帮她解决这个问题？
*所有的计算均基于十进制进行，结果也用十进制表示为不可约简的分数形式。
*
* 2.输入：
* 输入中有多组测试数据。每组测试数据为一个整数A（1=＜A=＜5000）。
*
* 3.输出：
* 对每组测试数据，在单独的行中以X/Y的形式输出结果。
*
* 4.测试用例
* 样例输入
* 5
* 3
*
* 样例输出
* 7/3
* 2/1
* */

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){
        //Scanner in = new Scanner(System.in);
        //while(in.hasNext()){
            //int n = in.nextInt();
            solution(100);
        //}

    }

    public static void solution(int n){
        int number = n - 2;
        int count = 0;
        for(int i=2;i<n;i++){
            String value = Integer.toString(n,i);
            char[] chs = value.toCharArray();
            for(int j=0;j<chs.length;j++){
                int val = Integer.parseInt(chs[j]+"");
                count+=val;
            }
        }

        System.out.println(count +"/"+ number);
    }
}
