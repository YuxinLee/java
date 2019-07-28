package base.IO_learning.outputstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * OutputStream的Write方法
 * （1） public void write(byte b[ ])：将参数b中的字节写到输出流。 
 * （2） public void write(byte b[ ], int off, int len) ：将参数b的从偏移量off开始的len个字节写到输出流。 
 * （3）public abstract void write(int b) ：先将int转换为byte类型，把低字节写入到输出流中
 *  (4）public void flush( ) : 将数据缓冲区中数据全部输出，并清空缓冲区。 
 */

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("fos4.txt");
            fos.write("java".getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
