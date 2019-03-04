package souhu.test;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String v1 = in.next();
        String v2 = in.next();
        System.out.println(process(v1,v2));

//        String str1 = v1.substring(10,v1.length()-1);
//        System.out.println(str1);
    }

    public static int process(String v1, String v2){
        String str1 = v1.substring(10,v1.length()-1);
        String str2 = v1.substring(10,v2.length()-1);
        String[] strs1 = str1.split(".");
        String[] strs2 = str2.split(".");
        int min = strs1.length >strs2.length ? strs2.length : strs1.length;
        for(int i=0;i<min;i++){
            if(Integer.valueOf(strs1[i])>Integer.valueOf(strs2[i])){
                return 1;
            }else if(Integer.valueOf(strs1[i])<Integer.valueOf(strs2[i])){
                return -1;
            }else{
                continue;
            }
        }
        return 0;
    }
}
