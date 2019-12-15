package base.jvm_learning;

import java.util.Arrays;

public class Array_Type {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        swap(a, b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static void swap(int[] a, int[] b) {
        a[1] = 10;
        b[1] = 20;
    }
}

