package haoweilai.test;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String string = in.next();
            char[] chs = string.toCharArray();
            int len = chs.length;
            int[] arr = new int[len];
            for(int i=0;i<len;i++){
                arr[i] = Integer.parseInt(String.valueOf(chs[i]));
            }

//            for(int i=0;i<len;i++){
//                System.out.println(arr[i]);
//            }
            int m = 0;
            for(int i=0;i<len;i++){
                if(arr[i]/3==0){
                    m++;
                    continue;
                }else{
                    int tmp = arr[i];
                }
            }
        }
    }
}
