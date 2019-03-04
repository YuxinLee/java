package base.pattern.factorydemo;

public class AnimalDemo {
    public static void main(String[] args) {
        // 具体类调用
        Dog d = new Dog();
        d.eat();
        Cat c = new Cat();
        c.eat();
        System.out.println("------------");

        // 工厂改进后
        Animal a = AnimalFactory.createAnimal("dog");
        a.eat();
        a = AnimalFactory.createAnimal("cat");
        a.eat();

        // NullPointerException
        a = AnimalFactory.createAnimal("pig");
        if (a != null) {
            a.eat();
        } else {
            System.out.println("对不起，暂时不提供这种动物");
        }
    }
}

abstract class Animal{
    public abstract void eat();
}

class Cat extends  Animal{
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

class Dog extends Animal{
    public void eat(){
        System.out.println("狗吃肉");
    }
}

class AnimalFactory{
    private  AnimalFactory(){}

    public static Animal createAnimal(String type){
       if("dog".equals(type)){
           return new Dog();
       } else if("cat".equals(type)){
           return new Cat();
        }else {
           return null;
       }
    }
}
