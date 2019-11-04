package service.impl;

import dao.FoodDao;
import entity.Food;
import factory.BeanFactory;
import service.FoodService;
import utils.PageBean;

public class FoodServiceImpl implements FoodService {

    // 创建dao
    private FoodDao foodDao = BeanFactory.getInstance("foodDao");

    @Override
    public Food findById(int id) {
        try {
            return foodDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Food> pageBean) {
        try {
            foodDao.getAll(pageBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
