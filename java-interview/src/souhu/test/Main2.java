package souhu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
        System.out.println(process(str1,str2));
    }

    public static int process(String str1, String str2){
//        ArrayList<Character> arrayList1 = new ArrayList<>();
//        ArrayList<Character> arrayList2 = new ArrayList<>();
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
//        for(int i=0;i<str1.length();i++){
//            arrayList1.add(chs1[i]);
//            arrayList2.add(chs2[i]);
//        }

        if(!str1.equals(str2)){
//            Collections.sort(arrayList1);
//            Collections.sort(arrayList2);
            Arrays.sort(chs1);
            Arrays.sort(chs2);
            String str3 = String.valueOf(chs1);
            String str4 = String.valueOf(chs2);
            if(str3.equals(str4)){
                return 1;
            }else{
                return 0;
            }
        }

        if(str1.equals(str2)){
//            Collections.sort(arrayList1);
//            Collections.sort(arrayList2);
            Arrays.sort(chs1);
            Arrays.sort(chs2);
            if(chs1[0]==chs1[chs1.length-1]){
                return 1;
            }else{
                return 0;
            }
        }

        return 1;

    }
}
