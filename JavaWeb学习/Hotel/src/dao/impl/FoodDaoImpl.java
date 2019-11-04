package dao.impl;

import dao.FoodDao;
import entity.Food;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.Condition;
import utils.JdbcUtils;
import utils.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    @Override
    public void getAll(PageBean<Food> pageBean) {
        // 获取条件对象
        Condition condition = pageBean.getCondition();

        // 条件之类别id
        int typeId = condition.getFoodTypeId();

        // 条件之菜品名称
        String foodName = condition.getFoodName();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" f.id,");
        stringBuffer.append(" f.foodName,");
        stringBuffer.append(" f.price,");
        stringBuffer.append(" f.mprice,");
        stringBuffer.append(" f.remark,");
        stringBuffer.append(" f.img,");
        stringBuffer.append(" f.foodType_id,");
        stringBuffer.append(" t.typeName");
        stringBuffer.append(" from");
        stringBuffer.append(" food f,");
        stringBuffer.append(" foodType t");
        stringBuffer.append(" where f.foodTye_id=t.id");

        // 存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();

        /*******拼接查询条件*********/
        // 类别id条件
        if (typeId > 0) {
            stringBuffer.append(" and f.foodType_id=?");
            list.add(typeId);       // 组装条件值
        }

        // 菜品名称
        if (foodName != null && "".equals(foodName.trim())) {
            stringBuffer.append(" and f.foodName like ?");
            list.add(foodName);     // 组装条件值
        }

        /*********分页条件**********/
        stringBuffer.append(" limit ?,?");

        /*****判断：当当前页< 1， 设置当前页为1；  当当前页>总页数，设置当前页为总页数******/
        // 先查询总记录数
        int totalCount = getTotalCount(pageBean);

        // 设置分页bean参数之总记录数
        pageBean.setTotalCount(totalCount);

        if (pageBean.getCurrentPage() < 1) {
            pageBean.setCurrentPage(1);
        } else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
            pageBean.setCurrentPage(pageBean.getTotalPage());
        }

        // 起始行
        int index = (pageBean.getCurrentPage() - 1) * pageBean.getPageCount();

        // 返回记录行
        int count = pageBean.getPageCount();

        list.add(index);        // 组装条件值 - 起始行
        list.add(count);        // 组装条件值 - 查询返回的行

        // 按条件、分页查询
        try {
            List<Food> pageData = JdbcUtils.getQueryRunner().query(stringBuffer.toString(),
                    new BeanListHandler<Food>(Food.class), list.toArray());

            // 把查询到的数据设置到分页对象中
            pageBean.setPageData(pageData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getTotalCount(PageBean<Food> pageBean) {
        // 获取条件对象
        Condition condition = pageBean.getCondition();

        // 条件之类别id
        int typeId = condition.getFoodTypeId();

        // 条件之菜品名称
        String foodName = condition.getFoodName();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" count(*)");
        stringBuffer.append(" from");
        stringBuffer.append(" food f,");
        stringBuffer.append(" foodType t");
        stringBuffer.append(" where f.foodType_id=t.id ");

        // 存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();

        /*******拼接查询条件*********/
        // 类别id条件
        if (typeId > 0) {
            stringBuffer.append(" and f.foodType_id=?");
            list.add(typeId);   // 组装条件值
        }

        // 菜品名称
        if (foodName != null && "".equals(foodName.trim())) {
            stringBuffer.append(" and f.foodName like ?");
            list.add(foodName);     // 组装条件值
        }

        try {
            Long num = JdbcUtils.getQueryRunner().query(stringBuffer.toString(), new ScalarHandler<Long>(), list.toArray());
            return num.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Food findById(int id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" f.id,");
        stringBuffer.append(" f.foodName,");
        stringBuffer.append(" f.price,");
        stringBuffer.append(" f.mprice,");
        stringBuffer.append(" f.remark,");
        stringBuffer.append(" f.img,");
        stringBuffer.append(" f.foodType_id,");
        stringBuffer.append(" t.typeName");
        stringBuffer.append(" from");
        stringBuffer.append(" food f,");
        stringBuffer.append(" foodType t");
        stringBuffer.append(" where f.foodTye_id=t.id");

        try {
            return JdbcUtils.getQueryRunner().query(stringBuffer.toString(), new BeanHandler<Food>(Food.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
