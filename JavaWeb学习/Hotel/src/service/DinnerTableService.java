package service;

import entity.DinnerTable;

import java.util.List;

public interface DinnerTableService {

    /**
     * 查询所有未预定的餐桌
     */
    List<DinnerTable> findNoUseTable();

    /**
     * 根据主键查询
     */
    DinnerTable findById(int id);
}
