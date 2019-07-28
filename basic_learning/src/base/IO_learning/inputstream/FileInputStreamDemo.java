package base.IO_learning.inputstream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * InputStream的Read方法
 * 1. public abstract int read()：读取一个byte的数据，返回值是高位补0的int类型值。若返回值为-1说明没有读取到任何字节，读取工作结束
 * 2. public int read(byte b[])：读取b.length个字节的数据放到b数组中。返回值是读取的字节数
 * 3. public int read(byte b[], int off, int len)：从输入流中最多读取len个字节的数据，存放到偏移量为off发b数组中 
 */

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("fis2.txt");
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1){
            System.out.println(new String(bys, 0, len));
        }
        fis.close();
    }
}
