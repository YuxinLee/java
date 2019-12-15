package base.jvm_learning;

import java.util.ArrayList;
import java.util.List;

public class For3_Type {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();

        for (long i = 0; i < 100000L; i++) {
            Animal animal = new Animal();
            animalList.add(animal);
        }
    }
}

class Animal{}
