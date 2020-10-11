package base.list_learning;

import java.util.TreeSet;

/**
 * Java对对象按照其属性排序的两种方法
 * 方法一：将要排序的对象类实现Comparable<>接口。
 * TreeSet进行add的添加功能，实际上使用的是TreeMap的put功能，put功能中实现了这两种比较器的功能
 */
public class TreeSet_Comparable {
    public static void main(String[] args) {
        // 创建集合对象
        TreeSet<Student> ts = new TreeSet<Student>();

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

    /**
     * 如果一个类的元素要想能够进行自然排序，就必须实现自然排序接口
     */
    static class Student implements Comparable<Student>{
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

        public int compareTo(Student s){
            // 主要条件 姓名的长度
            int num = this.name.length() - s.name.length();
            // 姓名的长度相同，不代表姓名的内容相同
            int num2 = num == 0 ? this.name.compareTo(s.name) : num;
            // 姓名的长度和内容相同，不代表年龄相同，所以还得继续判断年龄
            int num3 = num2 == 0 ? this.age - s.age : num2;
            return num3;
        }
    }

}

