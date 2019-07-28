package base.IO_learning.CopyFile;

import java.io.*;

public class CopyFileDemo6 {
    public static void main(String[] args) throws IOException {
        // 封装数据源
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));

        // 读写数据
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 释放资源
        bw.close();
        br.close();
    }
}
