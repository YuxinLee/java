package design_model.factory.simple_factory;

/**
 * 葡萄
 */
public class Grape implements Fruit {

    /**
     * 有籽无籽
     */
    private boolean seedLess;

    /**
     * 生长
     */
    @Override
    public void grow() {
        log("Grape is growing...");
    }

    /**
     * 收获
     */
    @Override
    public void harvest() {
        log("Grape has been harvested.");
    }

    /**
     * 种植
     */
    @Override
    public void plant() {
        log("Grape has been planted.");
    }

    /**
     * 打印信息
     * @param msg 信息
     */
    private static void log(String msg) {
        System.out.println(msg);
    }

    /**
     * 有籽无籽的取值方法
     */
    public boolean getSeedless() {
        return seedLess;
    }

    /**
     * 有籽无籽的赋值方法
     */
    public void setSeedless(boolean seedLess) {
        this.seedLess = seedLess;
    }
}
