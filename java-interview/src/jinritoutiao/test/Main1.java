package jinritoutiao.test;



import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();

            String[] strs1 = new String[n];
            String[] strs2 = new String[m];
            String kongge = in.nextLine();
            for(int i=0;i<n;i++){
                strs1[i] = in.nextLine();

            }
            for(int j=0;j<m;j++){
                strs2[j] = in.nextLine();

            }

//            System.out.println(strs1);
//            System.out.println(strs2);
            ArrayList<String> arrayList1 = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            ArrayList<String> arrayList3 = new ArrayList<>();
            for(int i=0;i<n;i++){
                char[] chs = strs1[i].toCharArray();
                arrayList1.add(String.valueOf(chs[0]));
                arrayList2.add(String.valueOf(chs[4]));
                arrayList3.add(String.valueOf(chs[8]));
            }

            System.out.println(arrayList1);
            System.out.println(arrayList2);
            System.out.println(arrayList3);

            for(int i=0;i<m;i++){
                char[] chs = strs2[i].toCharArray();
                String s1 = String.valueOf(chs[0]);
                String s2 = String.valueOf(chs[4]);

            }


        }
    }
}
