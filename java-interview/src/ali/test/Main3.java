package ali.test;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long w = in.nextLong();
        System.out.println(process(n,w));
    }

    public static long mi(long n, long w){
        int mo = 100003;
        long count = 1;
        n = n%mo;
        while (w!=0){
                count = (count*n)%mo;
                w = w >> 2;
                n = (n*n)%mo;
            }
        return (count%mo);
        }

    public static long process(long n, long w){
        long tmp = mi(n,w)-n*mi(n-1,w-1);
        while (tmp<0){
            tmp+=10003;

        }
        return tmp;
    }


    }

