package service.impl;

import dao.DinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import factory.BeanFactory;
import service.DinnerTableService;

import java.util.List;

public class DinnerTableServiceImpl implements DinnerTableService {

    // 调用的Dao, 通常工厂创建实例
    private DinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao");

    @Override
    public List<DinnerTable> findNoUseTable() {
        try {
            // 调用dao的根据状态查询的方法，查询没有预定的餐桌
            return dinnerTableDao.findByStatus(TableStatus.Free);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DinnerTable findById(int id) {
        try {
            return dinnerTableDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
