package base.network.udpdemo;

import java.io.IOException;
import java.net.*;

/*
 * UDP协议发送数据：
 * A:创建发送端Socket对象
 * B:创建数据，并把数据打包
 * C:调用Socket对象的发送方法发送数据包
 * D:释放资源
 */
public class SendDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        byte[] bys = "hello,udp,我来了".getBytes();
        int length = bys.length;
        int port = 10086;
        // IP地址对象
        InetAddress address = InetAddress.getByName("192.168.12.92");
        // DatagramPacket(byte[] buf, int length, InetAddress address, int port)
        DatagramPacket dp = new DatagramPacket(bys, length, address, port);
        // 调用Socket对象的发送方法发送数据包
        // public void send(DatagramPacket p)
        ds.send(dp);

        // 释放资源
        ds.close();
    }
}
