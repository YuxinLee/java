package design_model.factory.abstract_factory;


/**
 * 工厂具体实现类
 */
public class ConcereCreator1 implements Creator {

    /**
     * 产品A的工厂方法
     */
    @Override
    public ProductA factoryA() {
        return new ProductA1();
    }

    /**
     * 产品B的工厂方法
     */
    @Override
    public ProductB factoryB() {
        return new ProductB1();
    }
}
