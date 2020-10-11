package base.list_learning;

import java.util.ArrayList;
import java.util.Date;

public class ArrayListDemo3 {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");

        String str = "hello";
        arrayList.remove(str);

        System.out.println(arrayList);
        System.out.println(arrayList.contains("world"));

        Date date  = new Date();
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(date1);
        System.out.println(date.getTime());


    }
}
