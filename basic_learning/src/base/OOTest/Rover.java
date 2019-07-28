package base.OOTest;

public class Rover {
    public static void main(String[] args) {
        Position position = new Position(0,0);
        Direction direction = new Direction('N');
        direction.moveForword(position);
        System.out.print("横坐标为" + position.getI() + "  ");
        System.out.println("总坐标为" + position.getJ());
    }

}
