package yixin.test;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String str1 = in.next();
            String str2 = in.next();
            System.out.println(solution(str1,str2));
        }

    }

    public static int solution(String str1, String str2){
        int result = str1.indexOf(str2);
        return result;
    }
}
