package design_model.factory.factory_method;

/**
 * 工厂具体实现类
 */
public class ConcereCreator2 implements Creator {
    @Override
    public Product factory() {
        return new ConcreteProduct2();
    }
}
