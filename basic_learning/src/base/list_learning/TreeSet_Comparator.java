package base.list_learning;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Java对对象按照其属性排序的两种方法
 * 方法二：使用Comparator匿名内部类实现。
 */
public class TreeSet_Comparator {
    public static void main(String[] args){
        TreeSet<Student> ts = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
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
        Student s1 = new Student("linqingxia", 27);
        Student s2 = new Student("zhangguorong", 29);
        Student s3 = new Student("wanglihong", 23);
        Student s4 = new Student("linqingxia", 27);
        Student s5 = new Student("liushishi", 22);
        Student s6 = new Student("wuqilong", 40);
        Student s7 = new Student("fengqingy", 22);
        Student s8 = new Student("linqingxia", 29);

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
        for (Student s : ts) {
            System.out.println(s.getName() + "---" + s.getAge());
        }
    }

    static class Student{
        private String name;
        private int age;

        public Student() {
            super();
        }

        public Student(String name, int age) {
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
}


