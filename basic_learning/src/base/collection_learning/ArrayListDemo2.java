package base.collection_learning;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo2 {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");

        String[] str=new String[arrayList.size()];

        arrayList.toArray(str);

//        String[] strings = (String[]) arrayList.toArray();

        for (String string : str) {
            System.out.println(string);
        }


    }
}
