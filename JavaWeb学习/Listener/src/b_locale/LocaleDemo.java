package b_locale;

import java.util.Locale;

//1. 本地化对象:Locale
// 封装语言、国家信息的对象，有java.util提供
public class LocaleDemo {
    public static void main(String[] args) {

        // 模拟中国语言等环境
        //Locale locale = Locale.CHINA;

        Locale locale = Locale.getDefault();			// 当前系统默认的语言环境
        System.out.println(locale.getCountry());   		// CN  国家的简称
        System.out.println(locale.getDisplayCountry()); // 国家名称
        System.out.println(locale.getLanguage());		// zh 语言简称

        // 模拟美国国家
        Locale l_us = Locale.US;
        System.out.println(l_us.getCountry());
        System.out.println(l_us.getDisplayCountry());

    }
}
