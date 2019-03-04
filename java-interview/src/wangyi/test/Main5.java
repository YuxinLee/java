package wangyi.test;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] arsg){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            System.out.println(solution(n));
        }
    }

    public static int solution(int n){
        int count = 0;
        while (n>5){
            n = n-5;
            count++;
        }
        if(n>0){
            return count+1;
        }else{
            return count;
        }
    }
}
