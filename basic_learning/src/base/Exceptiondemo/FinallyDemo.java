package base.Exceptiondemo;

import java.io.File;

public class FinallyDemo {
    public static void main(String[] args) {
        File file = new File("f:\\");

        // public String[] list():获取指定目录下的所有文件或者文件夹的名称数组
        String[] strArray = file.list();
        for (String s : strArray) {
            System.out.println(s);
        }
        System.out.println("------------");
    }


}
