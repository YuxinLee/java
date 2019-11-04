package dao;

import entity.FoodType;

import java.util.List;

/**
 * 菜系模块，dao接口设计
 *
 */
public interface FoodTypeDao {

    /**
     * 添加
     */
    void save (FoodType foodType);

    /**
     * 更新
     */
    void update(FoodType foodType);

    /**
     * 删除
     */
    void delete(int id);

    /**
     * 根据主键查询
     */
    FoodType findById(int id);

    /**
     * 查询全部
     */
    List<FoodType> getAll();

    /**
     * 根据菜系名称查询
     */
    List<FoodType> getAll(String typeName);
}
