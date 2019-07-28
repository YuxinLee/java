package base.IO_learning.outputstream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("bos.txt"));

        // 写数据
        bos.write("hello".getBytes());

        // 释放资源
        bos.close();
    }
}
