package service.impl;

import dao.FoodTypeDao;
import entity.FoodType;
import factory.BeanFactory;
import service.FoodTypeService;

import java.util.List;

/**
 * 菜系模块业务逻辑层，接口实现
 *
 */
public class FoodTypeServiceImpl implements FoodTypeService {

    // 调用dao
    //private FoodTypeDao foodTypeDao = new FoodTypeDaoImpl();// 对象的创建，不能写死。

    // 工厂创建对象
    private FoodTypeDao foodTypeDao = BeanFactory.getInstance("foodTypeDao");

    @Override
    public void save(FoodType foodType) {
        try {
            foodTypeDao.save(foodType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(FoodType foodType) {
        try {
            foodTypeDao.update(foodType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            foodTypeDao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public FoodType findById(int id) {
        try {
            return foodTypeDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        try {
            return foodTypeDao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String typeName) {
        try {
            return foodTypeDao.getAll(typeName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
