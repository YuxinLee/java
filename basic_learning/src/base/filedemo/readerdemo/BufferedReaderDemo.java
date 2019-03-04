package base.filedemo.readerdemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        // 创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader("bw.txt"));

        // 方式1
        // int ch = 0;
        // while ((ch = br.read()) != -1) {
        // System.out.print((char) ch);
        // }

        // 方式2
        char[] chs = new char[1024];
        int len = 0;
        while ((len = br.read(chs)) != -1) {
            System.out.print(new String(chs, 0, len));
        }

        // 释放资源
        br.close();
    }
}
