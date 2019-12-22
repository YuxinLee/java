package base.enum_learning;

public class SexEnumDemo {
    public static void main(String[] args) {
        for (SexEnum sexEnum : SexEnum.values()) {
            System.out.println();
        }
    }
}

enum SexEnum {

    MALE(1), FEMALE(2);

    private int num;

    SexEnum(int num) {
        this.num = num;
    }
}
