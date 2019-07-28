package base.collection_learning;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("java");

        //第一种：迭代器
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }
        System.out.println("------------------");

        //第二种：普通for
        for(int i=0;i<arrayList.size();i++){
            String s = arrayList.get(i);
            System.out.println(s);
        }
        System.out.println("------------------");

        //第三种：增强for
        for(String s : arrayList){
            System.out.println(s);
        }

    }
}
