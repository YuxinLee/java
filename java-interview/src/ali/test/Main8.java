package ali.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<n;i++){
            arrayList.add(in.nextInt());
        }

        for(int i=0;i<m;i++){
            int index = Collections.min(arrayList);
            int indexs;
            if(index==0){
                indexs = 1;
            }else if(index>=arrayList.size()-1){
                indexs = arrayList.size()-2;
            }else{
                indexs = arrayList.get(index+1)>arrayList.get(index-1) ? index-1 : index+1;
            }
            arrayList.set(index,arrayList.get(index)+arrayList.get(indexs));
            arrayList.remove(indexs);
        }

        System.out.println(Collections.min(arrayList));
    }
}
