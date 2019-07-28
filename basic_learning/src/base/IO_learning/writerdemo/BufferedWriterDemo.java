package base.IO_learning.writerdemo;

/**
 * 字符流为了高效读写，也提供了对应的字符缓冲流。
 * BufferedWriter:字符缓冲输出流
 * BufferedReader:字符缓冲输入流
 *
 * BufferedWriter:字符缓冲输出流
 * 将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
 * 可以指定缓冲区的大小，或者接受默认的大小。在大多数情况下，默认值就足够大了。
 *
 * 主要方法： 
　　(1) public void write(int c) throws IOException； //将整型值c的低16位写入输出流 
　　(2) public void write(char cbuf[]) throws IOException； //将字符数组cbuf[]写入输出流 
　　(3) public abstract void write(char cbuf[],int off,int len) throws IOException； //将字符数组cbuf[]中的从索引为off的位置处开始的len个字符写入输出流 
　　(4) public void write(String str) throws IOException； //将字符串str中的字符写入输出流 
　　(5) public void write(String str,int off,int len) throws IOException； //将字符串str 中从索引off开始处的len个字符写入输出流 
　　(6) flush( ) //刷空输出流，并输出所有被缓存的字节。 
　　(7)close() 关闭流 public abstract void close() throws IOException 
 *
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {
    public static void main(String[] args) throws IOException {
        // BufferedWriter(Writer out)
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        // new FileOutputStream("bw.txt")));

        BufferedWriter bw = new BufferedWriter(new FileWriter("bw.txt"));

        bw.write("hello");
        bw.write("world");
        bw.write("java");
        bw.flush();

        bw.close();
    }
}
