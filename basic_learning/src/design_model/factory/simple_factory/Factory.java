package design_model.factory.simple_factory;

/**
 * 简单工厂模式：静态工厂方法模式，三者都是类的创建模式,工厂类集中了所有产品的创建逻辑，形成了一个上帝类，客户端不需要任何逻辑
 * 如果增加一个类别的话也需要修该工厂类。
 * 工厂方法模式：多态性工厂模式，类的创建模式,创建工厂接口，将实际创建工作推迟到子类中。对于新增加的类别，不需要做任何改变
 * 抽象工厂模式：工具箱模式
 */
public class Factory {
    public static void main(String[] args) {
        try {
            FruitGardener.factory("grape");
            FruitGardener.factory("apple");
            FruitGardener.factory("strawberry");
        } catch (BadFruitException e) {
            e.printStackTrace();
        }
    }
}
