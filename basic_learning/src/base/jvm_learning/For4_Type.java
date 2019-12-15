package base.jvm_learning;

import java.util.ArrayList;
import java.util.List;

public class For4_Type {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (long i = 0; i < 10L; i++) {
            Animal animal = new Animal();
//            list.add("" + i);
            System.out.println(System.identityHashCode(animal));
        }
    }
}
