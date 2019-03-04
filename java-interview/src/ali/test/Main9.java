package ali.test;

import java.util.ArrayList;

public class Main9 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        for(int i=0;i<3;i++){
            arrayList.remove(0);
        }

        System.out.println(arrayList);
    }
}
