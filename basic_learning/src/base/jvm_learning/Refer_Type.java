package base.jvm_learning;

public class Refer_Type {
    public static void main(String[] args) {
        int age = 28;
        Deparmrnt deparmrnt1 = new Deparmrnt("开发");
        Deparmrnt deparmrnt2 = new Deparmrnt("测试");
        Person person1 = new Person("xiaowang", 30, deparmrnt1);
        Person person2 = new Person("xiaozhang", 22, deparmrnt2);
        swap1(age);
        swap2(person1);
        swap3(person2);
        deparmrnt1.setName("运维");
        System.out.println(age);
        System.out.println(person1);
        System.out.println(person2);
    }
    public static void swap1(int i) {
        i = 88;
    }
    public static void swap2(Person person) {
        person = new Person("xiaohong",18, new Deparmrnt("运维"));
    }
    public static void swap3(Person person) {
        person.setName("xiaosun");
        person.setAge(55);
    }
}

class Person{
    private String name;
    private int age;
    private Deparmrnt deparmrnt;

    public Person() {
    }

    public Person(String name, int age, Deparmrnt deparmrnt) {
        this.name = name;
        this.age = age;
        this.deparmrnt = deparmrnt;
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

    public Deparmrnt getDeparmrnt() {
        return deparmrnt;
    }

    public void setDeparmrnt(Deparmrnt deparmrnt) {
        this.deparmrnt = deparmrnt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", deparmrnt=" + deparmrnt.getName() +
                '}';
    }
}

class Deparmrnt{
    private String name;

    public Deparmrnt() {
    }

    public Deparmrnt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
