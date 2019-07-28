package base.IO_learning.CopyFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileDemo2 {
    public static void main(String[] args) throws IOException {
        // 封装数据源
        FileInputStream fis = new FileInputStream("c:\\a.txt");
        FileOutputStream fos = new FileOutputStream("d:\\b.txt");
        // 复制数据
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }

        // 释放资源
        fos.close();
        fis.close();
    }
}
