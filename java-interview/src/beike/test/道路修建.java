package beike.test;

/*
* 山区里有N个村庄，第i个村庄的海拔为Ai，现在要在村庄之间修建道路，使得从每个村庄出发，都能到达
* 其他所有的村庄，在第i和第j个村庄之间修建道路的费用取决于海拔较高的村庄的高度，即费用为
* max(Ai, Aj)，那么修建道路的最小费用为多少？
*
* 输入：
* 第一行包含一个整数N
* 第二行包含N个空格隔开的整数A1到AN
*
* 输出：
* 修建道路的最小费用
*
* 样例输入：
* 5
* 4 1 8 2 5
*
* 输出
* 19
*
* */

import java.util.Arrays;
import java.util.Scanner;

public class 道路修建 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                int number = in.nextInt();
                arr[i] = number;
            }

            Arrays.sort(arr);
            int total = 0;
            for(int i=1;i<arr.length;i++){
                total += arr[i];
            }
            System.out.println(total);
        }
    }
}
