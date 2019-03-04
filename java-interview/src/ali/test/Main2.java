package ali.test;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = in.nextInt();
        }
        System.out.println(process(arr));
    }

    public static int process(int[] arr){
        int len = arr.length;
        int count = 0;
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(arr[i]>arr[j]){
                    count++;
                }
            }
        }
        return count;
    }
}
