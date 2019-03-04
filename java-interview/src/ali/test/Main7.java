package ali.test;

import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int n = in.nextInt();
        int count = 0;
        for(int i=l;i<=n;i++){
            int last = i;
            if(last>10){
                last = i%10;
            }


            int len = String.valueOf(i).length();
            int first = i;
            if(len-1>0){
                first = i/(10*(len-1));
            }

            if(last==first){
                count++;
            }
        }
        System.out.println(count);
    }
}
