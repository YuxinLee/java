package base.filedemo.inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
