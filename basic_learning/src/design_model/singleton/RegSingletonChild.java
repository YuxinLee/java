package design_model.singleton;

public class RegSingletonChild extends RegSingleton {

    public RegSingletonChild() {}

    static public RegSingletonChild getInstance() {
        return (RegSingletonChild) RegSingleton.getInstance("design_model.singleton.RegSingletonChild");
    }
}
