package haoweilai.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            ArrayList<Integer> arrayList = new ArrayList<>();
            ArrayList<Integer> arrayListy = new ArrayList<>();
            int t = in.nextInt();
            for(int i=0;i<t;i++){
                int x = in.nextInt();
                arrayList.add(x);
                int k = in.nextInt();
                arrayList.add(k);
            }
            for(int i=0;i<2*t;i+=2){
                int y=1;
                int count = 0;
                while(count<arrayList.get(i+1)){
                    if((arrayList.get(i)+y)==(arrayList.get(i)|y)){
                        count++;
                    }
                    y++;
                }
                arrayListy.add(y-1);
            }

            for(int i=0;i<arrayListy.size();i++){
                System.out.println(arrayListy.get(i));
            }

        }
    }
}
