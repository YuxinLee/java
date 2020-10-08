package advance.pagebean;

import java.util.ArrayList;
import java.util.List;

public class PageDemo {
    public static void main(String[] args) {

    }

    private PageBean findByPage(int currPage) {
        PageBean pageBean = new PageBean();

        // 设置pageBean的参数:
        // 设置当前页数:
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数:
        int pageSize = 10;
        pageBean.setPageSize(pageSize);

        // 设置总记录数:
//        ProductDao productDao = new ProductDao();
//        int totalCount = productDao.findCount();
        int totalCount = 1000;
        pageBean.setTotalCount(totalCount);

        // 设置总页数
        int totalPage = 0;
        if(totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageBean.setTotalPage(totalPage);

        // 设置每页显示的数据的集合
        int begin = (currPage - 1)* pageSize;
//        List<Product> list = productDao.findByPage(begin,pageSize);
        List list = new ArrayList();
        pageBean.setList(list);
        
        return pageBean;
    }
}


class PageBean<T> {
    private int currPage;   //当前页数
    private int totalCount; //总记录数
    private int totalPage;  //总页数
    private int pageSize;   //每页记录数
    private List<T> list;   //每页的数据集合

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
