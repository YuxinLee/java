package base.collection_learning;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo2 {
    public static void main(String[] args){
        TreeSet<Student1> ts = new TreeSet<>(new Comparator<Student1>() {
            @Override
            public int compare(Student1 s1, Student1 s2) {
                // 姓名长度
                int num = s1.getName().length() - s2.getName().length();
                // 姓名内容
                int num2 = num == 0 ? s1.getName().compareTo(s2.getName())
                        : num;
                // 年龄
                int num3 = num2 == 0 ? s1.getAge() - s2.getAge() : num2;
                return num3;
            }


        });

        // 创建元素
        Student1 s1 = new Student1("linqingxia", 27);
        Student1 s2 = new Student1("zhangguorong", 29);
        Student1 s3 = new Student1("wanglihong", 23);
        Student1 s4 = new Student1("linqingxia", 27);
        Student1 s5 = new Student1("liushishi", 22);
        Student1 s6 = new Student1("wuqilong", 40);
        Student1 s7 = new Student1("fengqingy", 22);
        Student1 s8 = new Student1("linqingxia", 29);

        // 添加元素
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        ts.add(s6);
        ts.add(s7);
        ts.add(s8);

        // 遍历
        for (Student1 s : ts) {
            System.out.println(s.getName() + "---" + s.getAge());
        }
    }
}

class Student1{
    private String name;
    private int age;

    public Student1() {
        super();
    }

    public Student1(String name, int age) {
        super();
        this.name = name;
        this.age = age;
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
}
