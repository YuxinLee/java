package base.enum_learning;

public class DirectionDemo {
    public static void main(String[] args) {
        System.out.println(Direction.FRONT.getName());
    }
}

enum Direction{
    FRONT("前"), BEHIND("后"), LEFT("作"), RIGHT("右");

    private String name;

    private Direction(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
