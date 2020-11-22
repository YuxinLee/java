package design_model.factory.simple_factory;

/**
 * 草莓
 */
public class Strawberry implements Fruit {

    /**
     * 生长
     */
    @Override
    public void grow() {
        log("Strawberry is growing...");
    }

    /**
     * 收获
     */
    @Override
    public void harvest() {
        log("Strawberry has been harvested.");
    }

    /**
     * 种植
     */
    @Override
    public void plant() {
        log("Strawberry has been planted.");
    }

    /**
     * 打印信息
     * @param msg 信息
     */
    private static void log(String msg) {
        System.out.println(msg);
    }

}
