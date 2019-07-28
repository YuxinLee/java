package base.IO_learning.readerdemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * java中字符是采用Unicode标准，一个字符是16位，使用两个字节表示。为此，java中引入了处理字符的流。
 * read主要方法： 
 * (1) public int read() throws IOException;  读取一个字符，返回值为读取的字符 
 * (2) public int read(char cbuf[]) throws IOException;  读取一系列字符到数组cbuf[]中，返回值为实际读取的字符的数量 
 * (3) public abstract int read(char cbuf[],int off,int len) throws IOException; 
 * 　　读取len个字符，从数组cbuf[]的下标off处开始存放，返回值为实际读取的字符数量，该方法必须由子类实现 　 
 *
 */

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
