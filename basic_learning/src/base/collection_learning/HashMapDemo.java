package base.collection_learning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, People> hashMap = new HashMap<>();
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";
        People people1 = new People("people1", 11);
        People people2 = new People("people2", 22);
        People people3 = new People("people3", 33);
        hashMap.put(s1, people1);
        hashMap.put(s2, people2);
        hashMap.put(s3, people3);

        Set<Map.Entry<String, People>> setEntry = hashMap.entrySet();
        for (Map.Entry<String, People> entry : setEntry) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Iterator<Map.Entry<String, People>> iterator = hashMap.entrySet().iterator();
        iterator.next();
        for (int i=0; i<2;i++) {
            Map.Entry<String, People> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }


    }
}

class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
