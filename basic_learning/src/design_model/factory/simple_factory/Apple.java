package design_model.factory.simple_factory;

/**
 * 苹果
 */
public class Apple implements Fruit {

    /**
     * 树龄
     */
    private int treeAge;

    /**
     * 生长
     */
    @Override
    public void grow() {
        log("Apple is growing...");
    }

    /**
     * 收获
     */
    @Override
    public void harvest() {
        log("Apple has been harvested.");
    }

    /**
     * 种植
     */
    @Override
    public void plant() {
        log("Apple has been planted.");
    }

    /**
     * 打印信息
     * @param msg 信息
     */
    private static void log(String msg) {
        System.out.println(msg);
    }

    /**
     * 树龄的取值方法
     */
    public int getTreeAge() {
        return treeAge;
    }

    /**
     * 树龄的赋值方法
     */
    public void setTreeAge(int treeAge) {
        this.treeAge = treeAge;
    }
}
