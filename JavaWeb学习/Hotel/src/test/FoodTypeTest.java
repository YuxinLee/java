package test;

import entity.FoodType;
import factory.BeanFactory;
import org.junit.Test;
import service.FoodTypeService;

import java.util.List;


public class FoodTypeTest {

    // 调用的菜系Service
    private FoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService");

    @Test
    public void save() {
        FoodType foodType = new FoodType();
        foodType.setTypeName("北风");
        foodTypeService.save(foodType);
    }

    @Test
    public void update() {
        FoodType foodType = new FoodType();
        foodType.setId(1);
        foodType.setTypeName("西风");
        foodTypeService.update(foodType);

    }

    @Test
    public void delete(){
        foodTypeService.delete(1);
    }

    @Test
    public void findById() {
        FoodType foodType = foodTypeService.findById(2);
        System.out.println(foodType);
    }

    @Test
    public void findAll1() {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        for (FoodType foodType : foodTypeList) {
            System.out.println(foodType);
        }
    }

    @Test
    public void findAll2() {
        List<FoodType> foodTypeList = foodTypeService.getAll("风");
        for (FoodType foodType : foodTypeList) {
            System.out.println(foodType);
        }
    }

}
