package dao.impl;

import dao.FoodTypeDao;
import entity.FoodType;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 菜系模块，dao接口实现
 *
 */
public class FoodTypeDaoImpl implements FoodTypeDao {
    @Override
    public void save(FoodType foodType) {
        String sql = "INSERT INTO foodType(typeName) VALUES(?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        String sql = "UPDATE foodType SET typeName=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName(), foodType.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM foodType where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public FoodType findById(int id) {
        String sql = "SELECT * FROM foodType where id=?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        String sql = "SELECT * FROM foodType";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll(String typeName) {
        String sql = "SELECT * FROM foodType where typeName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class), "%"+typeName+"%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
