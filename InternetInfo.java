package BaiFox;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InternetInfo {

    public static void main(String[] args) {
        try {
            // 获取本机所有网络接口
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                System.out.println("网络接口名称：" + networkInterface.getName());
                System.out.println("网络接口显示名称：" + networkInterface.getDisplayName());

                // 获取网络接口的IP地址
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("IP地址：" + inetAddress.getHostAddress());
                    System.out.println("是否为回环地址：" + inetAddress.isLoopbackAddress());
                    System.out.println("是否为站点本地地址：" + inetAddress.isSiteLocalAddress());
                    System.out.println("是否为链接本地地址：" + inetAddress.isLinkLocalAddress());
                }
                System.out.println("--------------------------------");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
