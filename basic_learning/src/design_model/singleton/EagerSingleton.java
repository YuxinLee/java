package design_model.singleton;

/**
 * 饿汉式单例：类一加载就创建对象，不会出现多线程同步的问题
 */
public class EagerSingleton {

    /**
     * 静态方法才能访问静态变量
     */
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    /**
     * 私有构造方法
     */
    private EagerSingleton() {}

    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}
