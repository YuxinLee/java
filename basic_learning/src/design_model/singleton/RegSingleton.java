package design_model.singleton;

import java.util.HashMap;

/**
 * 登记式单例
 */
public class RegSingleton {

    private static HashMap hashMap = new HashMap();

    static {
        RegSingleton regSingleton = new RegSingleton();
        hashMap.put(regSingleton.getClass().getName(), regSingleton);
    }

    /**
     * 保护构造方法
     */
    protected RegSingleton() {}

    static public RegSingleton getInstance(String name) {
        if (name == null) {
            name = "design_model.singleton.RegSingleton";
        }

        if (hashMap.get(name) == null) {
            try {
                hashMap.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                System.out.println("error");
            }
        }

        return (RegSingleton) hashMap.get(name);
    }

}
