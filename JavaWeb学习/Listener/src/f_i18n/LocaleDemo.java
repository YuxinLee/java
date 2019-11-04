package f_i18n;

import org.junit.Test;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleDemo {

    // 国际化 - 静态数据
    @Test
    public void testI18N() throws Exception {
        // 中文语言环境
        Locale locale = Locale.US;

        // 创建工具类对象ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("f_i18n.msg", locale);
        // 根据key获取配置文件中的值
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("username"));
        System.out.println(bundle.getString("pwd"));
    }

    // 国际化 - 动态文本 - 0. 概述
    @Test
    public void testI18N2() throws Exception {
        // 国际化货币
        NumberFormat.getCurrencyInstance();
        // 国际化数字
        NumberFormat.getNumberInstance();
        // 国际化百分比
        NumberFormat.getPercentInstance();
        // 国际化日期
        //DateFormat.getDateTimeInstance(dateStyle, timeStyle, aLocale)
    }

    // 国际化 - 动态文本 - 1. 国际化货币
    @Test
    public void testI18N3() throws Exception {
        // 模拟语言环境
        Locale locale = Locale.CHINA;
        // 数据准备
        double number = 100;
        // 工具类
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        // 国际化货币
        String m = nf.format(number);
        // 测试
        System.out.println(m);
    }

    //面试题：  代码计算：  $100 * 10
    @Test
    public void eg() throws Exception {
        String str = "$100";
        int num = 10;

        // 1. 分析str值是哪一国家的货币
        Locale us = Locale.US;
        // 2. 国际化工具类
        NumberFormat nf = NumberFormat.getCurrencyInstance(us);
        // 3. 解析str国币
        Number n = nf.parse(str);

        System.out.println(n.intValue() * num);
    }

    // 国际化 - 动态文本 - 2. 国际化数值
    @Test
    public void testI18N4() throws Exception {
        // 模拟语言环境
        Locale locale = Locale.CHINA;
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        String str = nf.format(1000000000);
        System.out.println(str);
    }

    // 国际化 - 动态文本 - 3. 国际化日期
    /*
     * 日期
     * 	  FULL   2015年3月4日 星期三
     * 	  LONG   2015年3月4日
     * 	  FULL   2015年3月4日 星期三
     *    MEDIUM 2015-3-4
     *    SHORT  15-3-4
     *
     * 时间
     * 	  FULL   下午04时31分59秒 CST
     * 	  LONG   下午04时32分37秒
     *    MEDIUM 16:33:00
     *    SHORT  下午4:33
     *
     *
     */

    @Test
    public void testI18N5() throws Exception {

        // 日期格式
        int dateStyle = DateFormat.SHORT;
        // 时间格式
        int timeStyle = DateFormat.SHORT;

        // 工具类
        DateFormat df =
                DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.CHINA);
        String date = df.format(new Date());

        System.out.println(date);
    }

    // 面试2： 请将时间值：09-11-28 上午10时25分39秒 CST，反向解析成一个date对象。
    @Test
    public void eg2() throws Exception {
        String str = "09-11-28 上午10时25分39秒 CST";
        // 创建DateFormat工具类，国际化日期
        DateFormat df = DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.FULL, Locale.getDefault());
        Date d = df.parse(str);

        System.out.println(d);
    }
}
