package qq.test;

/*
*
小Q有X首长度为A的不同的歌和Y首长度为B的不同的歌，现在小Q想用这些歌组成一个总长度正好为K的歌单，
每首歌最多只能在歌单中出现一次，在不考虑歌单内歌曲的先后顺序的情况下，请问有多少种组成歌单的方法。

输入描述:
每个测试用例的第一行包含一个整数，表示歌单的总长度K
接下来的一行包含四个正整数，分别表示歌的第一种长度A(A<=10)和数量X(X<=100)以及
歌的第二种长度B(B<=10)和数量Y(Y<=100)。保证A不等于B。

输出描述:
输出一个整数,表示组成歌单的方法取模。因为答案可能会很大,输出对1000000007取模的结果。

示例：
输入：
5
2 3 3 3

输出：
9

知识点：组会表达式：
C(n,k) = C(n-1,k) + C(n-1,k-1)
*
* */

import java.util.Scanner;

public class 小Q的歌单 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int k = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            long ans = 0;
            long[][] c = new long[105][105];
            long mod = 1000000007;
            c[0][0] = 1;
            for(int i=1;i<=100;i++){
                c[i][0] = 1;
                for(int j=1;j<=100;j++){
                    c[i][j] = c[i-1][j-1] + c[i-1][j];
                }
            }

            for(int i=0;i<=x;i++){
                if(i*a <=k && (k-i*a) % b ==0 && (k-i*a)/b <= y){
                    ans = ans + c[x][i] * c[y][(k-i*a)/b]%mod;
                }
            }
            System.out.println(ans);

        }
    }
}
