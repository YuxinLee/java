package design_model.singleton;

/**
 * 懒汉式单例：用的时候，才会创建，会出现多线程同步的问题
 * 如果加载器是静态的，则不会创建单例
 */
public class LazySingleton {

    /**
     * 静态方法才能访问静态变量
     */
    private static LazySingleton lazySingleton = null;

    /**
     * 私有构造方法
     */
    private LazySingleton() {}

    /**
     * 静态工厂方法
     */
    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }
}
