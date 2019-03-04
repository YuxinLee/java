package qunaer.test;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        System.out.println(process(n,m,k));
    }

    public static int process(int n, int m, int k){
        int count = 0;
        while (m>0){
            count = 0;
            for(int i=k;i>0;i--){
                if(i/n==0){
                    continue;
                }
                if(m>=i){
                    m = m - i;
                    count++;

                }
            }
        }

        return count;
    }
}
