package base.enum_learning;

public class Direction2Demo {
    public static void main(String[] args) {
        Direction2 direction2 = Direction2.FRONT;
        direction2.show();

    }
}

enum Direction2{
    FRONT("前") {
        @Override
        public void show() {
            System.out.println("前");
        }
    },
    BEHIND("后") {
        @Override
        public void show() {
            System.out.println("后");
        }
    },
    LEFT("左") {
        @Override
        public void show() {
            System.out.println("左");
        }
    },
    RIGHT("右") {
        @Override
        public void show() {
            System.out.println("右");
        }
    };

    private String name;

    private Direction2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void show();
}
