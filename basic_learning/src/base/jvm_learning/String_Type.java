package base.jvm_learning;

public class String_Type {
    public static void main(String[] args) {
        String a1 = "hello";
        String a2 = "hello";
        String b1 = new String("hello");
        String b2 = new String("hello");

        String a3 = a1;
        a3 = a3 + "123";

        System.out.println(a1 == a2);
        System.out.println(b1 == b2);
        System.out.println(a1);
        System.out.println(a3);
    }
}
