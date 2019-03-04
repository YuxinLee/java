package meituan.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println(process(n,m));

        }
    }

    public static int process(int n, int m){
        int res = 0;
        while (n > 0 && m > 0 && m + n >= 3){
            if(n>m){
                n -= 2;
                m -= 1;
                res++;
            }else{
                m -= 2;
                n -= 1;
                res++;
            }
        }
        return res;
    }
}

