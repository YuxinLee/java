package base.network.tcpdemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) throws IOException {
        // 创建服务器Socket对象
        ServerSocket ss = new ServerSocket(22222);

        // 监听客户端连接
        Socket s = ss.accept();

        // 包装通道内容的流
        BufferedReader br = new BufferedReader(new InputStreamReader(
                s.getInputStream()));
        // 封装文本文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        bw.close();
        // br.close();
        s.close();
        // ss.close();
    }
}
