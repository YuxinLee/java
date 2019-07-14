package cookie_learning.HistProduct.servlet;

import cookie_learning.HistProduct.dao.ProductDao;
import cookie_learning.HistProduct.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 显示商品详细
 *
 */
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.获取编号
        String id = request.getParameter("id");

        //2.到数据库中查询对应编号的商品
        ProductDao dao = new ProductDao();
        Product product = dao.findById(id);

        //3.显示到浏览器
        PrintWriter writer = response.getWriter();
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>显示商品详细</title>";
        html += "</head>";
        html += "<body>";
        html += "<table border='1' align='center' width='300px'>";
        if(product!=null){
            html += "<tr><th>编号:</th><td>"+product.getId()+"</td></tr>";
            html += "<tr><th>商品名称:</th><td>"+product.getProName()+"</td></tr>";
            html += "<tr><th>商品型号:</th><td>"+product.getProType()+"</td></tr>";
            html += "<tr><th>商品价格:</th><td>"+product.getPrice()+"</td></tr>";
        }

        html += "</table>";
        html += "<center><a href='"+request.getContextPath()+"/ListServlet'>[返回列表]</a></center>";
        html += "</body>";
        html += "</html>";

        writer.write(html);

        /**
         * 创建cookie，并发送
         */
        //1.创建cookie
        Cookie cookie = new Cookie("prodHist",createValue(request,id));
        cookie.setMaxAge(1*30*24*60*60);//一个月
        //2.发送cookie
        response.addCookie(cookie);
    }

    /**
     * 生成cookie的值
     * 分析：
     * 			当前cookie值                     传入商品id               最终cookie值
     *      null或没有prodHist          1                     1    （算法： 直接返回传入的id ）
     *             1                  2                     2,1 （没有重复且小于3个。算法：直接把传入的id放最前面 ）
     *             2,1                1                     1,2（有重复且小于3个。算法：去除重复id，把传入的id放最前面 ）
     *             3,2,1              2                     2,3,1（有重复且3个。算法：去除重复id，把传入的id放最前面）
     *             3,2,1              4                     4,3,2（没有重复且3个。算法：去最后的id，把传入的id放最前面）
     * @return
     */
    private String createValue(HttpServletRequest request,String id) {
        Cookie[] cookies = request.getCookies();
        String prodHist = null;
        if (cookies != null){
            for(Cookie cookie: cookies){
                if (cookie.getName().equals("prodHist")){
                    prodHist = cookie.getValue();
                    break;
                }
            }
        }

        if(cookies == null || prodHist == null){
            //直接返回传入的id
            return id;
        }

        // 3,2,1          2
        String[] ids = prodHist.split("-");
        Collection<String> colls = Arrays.asList(ids);
        // LinkedList 方便地操作（增删改元素）集合
        LinkedList<String> list = new LinkedList<String>(colls);

        //不超过3个
        if(list.size()<3){
            //id重复
            if (list.contains(id)){
                //去除重复id，把传入的id放最前面
                //remove方法传参有两种形式，此处传入的是String类型的id值，如果传入的是int类型的id值，则为索引值而不是元素值。
                list.remove(id);
                list.addFirst(id);
            }else{
                //直接把传入的id放最前面
                list.addFirst(id);
            }
        }else {
            //等于3个
            //id重复
            if(list.contains(id)){
                //去除重复id，把传入的id放最前面
                list.remove(id);
                list.addFirst(id);
            }else {
                //去最后的id，把传入的id放最前面
                list.removeLast();
                list.addFirst(id);
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (Object object : list){
            stringBuffer.append(object+"-");
        }

        //去掉最后的逗号
        String result = stringBuffer.toString();
        result = result.substring(0, result.length()-1);
        return result;
    }
}
