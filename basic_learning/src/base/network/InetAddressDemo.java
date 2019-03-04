package base.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("192.168.64.1");
        // 获取两个东西：主机名，IP地址
        String name = address.getHostName();
        String ip = address.getHostAddress();
        System.out.println(name +"---"+ip);

    }
}
