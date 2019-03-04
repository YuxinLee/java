package yongyou.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        while(true){
            int n = in.nextInt();
            if(String.valueOf(n).equals("\r\n")){
                break;
            }
            arrayList.add(n);
        }
        int count=0;
        for(int i=0;i<arrayList.size()-1;i++){
            if((arrayList.get(i))==(arrayList.get(i+1)-1)){
               count++;
            }else{
                arrayList1.add(count);
                count=0;
            }
        }
        System.out.println(Collections.max(arrayList1));
    }
}
