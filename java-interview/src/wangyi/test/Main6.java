package wangyi.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String string = in.next();
            System.out.println(solution1(string));
        }
    }

    public static int solution(String string){
        int countB = 0;
        int countW = 0;
        char[] chs = string.toCharArray();
        for(int i=0;i<chs.length;i++){
            if(chs[i]=='b'){
                countB++;
            }else{
                countW++;
            }
        }
        int min= countB<countW ? countB:countW;
        return min * 2 +1;
    }

    public static int solution1(String string){
        int count=0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        char[] chs = string.toCharArray();
        for(int i=0;i<chs.length-1;i++){
            if(chs[i] != chs[i+1]){
                count++;
            }else{
                arrayList.add(count);
                count = 0;
            }
        }
        return Collections.max(arrayList);
    }
}
