package toutiao.test;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.next();
        System.out.println(solution(string));
    }

    public static int solution(String string){
        if(string.length()<4){
            return 0;
        }else if(string.length()==4){
            return 1;
        }else{
            char[] chs = string.toCharArray();
            int count = 0;
            for(int i=1;i<chs.length-1;i++){
                if(chs[i]=='0'){
                    count++;
                }
            }
            if(count==chs.length-2){
                return 1;
            }
        }
        return string.length();
    }
}
