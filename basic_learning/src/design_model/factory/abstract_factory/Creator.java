package design_model.factory.abstract_factory;

/**
 * 工厂的接口
 */
public interface Creator {

    /**
     * 产品A的工厂方法
     */
    public ProductA factoryA();

    /**
     * 产品B的工厂方法
     */
    public ProductB factoryB();

}
