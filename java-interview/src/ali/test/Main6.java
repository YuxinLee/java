package ali.test;


import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strs = str.split(",");
        String str1 = in.nextLine();
        String[] str1s = str.split(",");
        String str2 = in.nextLine();
        String[] str2s = str.split(",");
        String str3 = in.nextLine();
        String[] str3s = str.split(",");
        if(strs[0].equals(strs[1])){
            System.out.println(""+0+":"+strs[0]+","+strs[1]);
        }
        if(Integer.valueOf(str1s[2]+str3s[2])<Integer.valueOf(str2s[2])){
            System.out.println(""+(str1s[2]+str3s[2])+":"+strs[0]+","+str2s[0]+","+strs[1]);
        }
        else{
            System.out.println(""+str2s[2]+":"+strs[0]+","+strs[1]);
        }
    }
}
