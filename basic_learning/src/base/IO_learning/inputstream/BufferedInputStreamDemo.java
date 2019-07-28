package base.IO_learning.inputstream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * 缓冲流就是每一个数据流分配一个缓冲区，一个缓冲区就是一个临时存储数据的内存。这样可以减少访问硬盘的次数,提高传输效率。
 * BufferedInputStream当向缓冲流读取数据时，数据先写到缓冲区，待缓冲区写满后，系统一次性将数据发送给输出设备 
 * BufferedOutputStream当从缓冲区读取数据时，系统先从缓冲区读出数据，待缓冲区为空时，系统再从输入设备读取到缓冲区。 
 *
 */

public class BufferedInputStreamDemo {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                "bos.txt"));
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            System.out.print(new String(bys, 0, len));
        }

        // 释放资源
        bis.close();
    }
}
