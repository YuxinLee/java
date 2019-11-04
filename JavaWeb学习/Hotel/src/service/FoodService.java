package service;

import entity.Food;
import utils.PageBean;

public interface FoodService {

    /**
     * 主键查询
     */
    Food findById(int id);

    /**
     * 分页查询
     */
    void getAll(PageBean<Food> pageBean);
}
