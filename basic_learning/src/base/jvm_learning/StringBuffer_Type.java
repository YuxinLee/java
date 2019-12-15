package base.jvm_learning;

public class StringBuffer_Type {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("hello");
        StringBuffer b = a;
        b.append("123");

        System.out.println(a);
        System.out.println(b);
    }
}
