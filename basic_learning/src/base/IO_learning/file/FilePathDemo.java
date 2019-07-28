package base.IO_learning.file;

/**
 *
 * Java中的流分为两种：
 * 1) 字节流：数据流中最小的数据单元是字节
 * 2) 字符流：数据流中最小的数据单元是字符， Java中的字符是Unicode编码，一个字符占用两个字节。
 */

import java.io.File;

public class FilePathDemo {
    public static void main(String[] args) {

    }

    private static void getAllJavaFilePaths(File srcFolder){
        // 获取该目录下所有的文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();
        // 遍历该File数组，得到每一个File对象
        for (File file : fileArray){
            // 判断该File对象是否是文件夹
            if (file.isDirectory()){
                getAllJavaFilePaths(file);
            }else {
                // 继续判断是否以.java结尾
                if (file.getName().endsWith(".java")){
                    // 就输出该文件的绝对路径
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
}
