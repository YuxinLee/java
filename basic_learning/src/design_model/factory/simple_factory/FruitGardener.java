package design_model.factory.simple_factory;

/**
 * 园丁
 */
public class FruitGardener {

    /**
     * 静态工厂方法
     */
    public static Fruit factory(String which) throws BadFruitException{
        if (which.equalsIgnoreCase("apple")) {
            return new Apple();
        } else if (which.equalsIgnoreCase("strawberry")) {
            return new Strawberry();
        } else if (which.equalsIgnoreCase("grape")) {
            return new Grape();
        } else {
            throw new BadFruitException("Bad fruit request");
        }
    }
}
