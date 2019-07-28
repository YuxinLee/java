package base.IO_learning.CopyFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        FileOutputStream fos = new FileOutputStream("b.txt");
        int by = 0;
        while ((by = fis.read())!=-1){
            fos.write(by);
        }
        fos.close();
        fis.close();
    }
}
