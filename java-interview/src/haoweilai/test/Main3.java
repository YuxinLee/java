package haoweilai.test;

import java.util.Scanner;

public class Main3 {
    public static  void  main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String string = in.next();
            char[] chs = string.toCharArray();
            int len = chs.length;
            int[] arr = new int[len];
            for(int i=0;i<len;i++){
                arr[i] = Integer.parseInt(String.valueOf(chs[i]));
            }
            solution(arr);

        }
    }

    public static void solution(int[] arr){
        int i = 0;
        int m = 0;
        int c = arr[0];
        while(i<arr.length){

            if(c/3==0){
                m++;
                i++;
            }else{

                c = c + arr[++i];
            }
        }
        System.out.println(m);
    }
}
