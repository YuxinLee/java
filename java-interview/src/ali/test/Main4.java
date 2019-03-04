package ali.test;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
        System.out.println(process(str1,str2));
    }

    public static boolean process(String str1,String str2){
        String[] strs = str2.split(",");
        for(int i=0;i<strs.length;i++){
            if(!str1.contains(strs[i]));
            return false;
        }
        return true;
    }
}
