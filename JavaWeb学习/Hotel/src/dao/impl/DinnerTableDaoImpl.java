package dao.impl;

import dao.DinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class DinnerTableDaoImpl implements DinnerTableDao {


    @Override
    public List<DinnerTable> findByStatus(TableStatus tableStatus) {
        String sql = "select * from dinnerTable where tableStatus = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), tableStatus.ordinal());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinnerTable where id = ?";

        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
