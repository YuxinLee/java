package base.filedemo.file;

/*
*需求：递归删除目录
 */

import java.io.File;

public class FileDeleteDemo {
    public static void main(String[] args) {
        // 封装目录
        File srcFolder = new File("demo");
        // 递归实现
        deleteFolder(srcFolder);
    }

    public static void deleteFolder(File srcFolder){
        // 获取该目录下的所有文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();
        // 遍历该File数组，得到每一个File对象
        if(fileArray != null){
            for (File file : fileArray){
                if(file.isDirectory()){
                    deleteFolder(file);
                }else {
                    System.out.println(file.getName()+"---"+file.delete());
                }
            }
        }
    }
}
