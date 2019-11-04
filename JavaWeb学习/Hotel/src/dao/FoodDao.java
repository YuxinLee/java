package dao;

import entity.Food;
import utils.PageBean;

/**
 * 菜品管理
 */
public interface FoodDao {

    /**
     * 分页且按条件查询所有的菜品
     */
    void getAll(PageBean<Food> pageBean);

    /**
     * 按条件统计菜品的总记录数
     */
    int getTotalCount(PageBean<Food> pageBean);

    /**
     * 根据id查询
     */
    Food findById(int id);
}
