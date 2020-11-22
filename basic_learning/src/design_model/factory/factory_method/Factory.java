package design_model.factory.factory_method;

/**
 * 工厂方法模式的核心是一个抽象工厂类，而简单工厂模式把核心放在一个具体类上。
 * 工厂方法模式可以允许很多具体工厂类从抽象工厂类中继承下来，从而形成多个简单工厂模式的综合。
 * 对于新加入的产品，只需要向系统中加入这个产品类以及它对应的工厂类，没有必要改变原代码以及客户端，符合完全的开闭原则。
 */
public class Factory {
    public static void main(String[] args) {
        Creator creator1 = new ConcereCreator1();
        Creator creator2 = new ConcereCreator2();
        Product product1 = creator1.factory();
        Product product2 = creator2.factory();

    }
}
