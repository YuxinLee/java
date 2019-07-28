package base.uuid_learning;

import java.util.UUID;

public class UUID_Demo1 {
    public static void main(String[] args) {
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        String id2 = id.replace("-","");
        System.out.println(id2);


    }
}
