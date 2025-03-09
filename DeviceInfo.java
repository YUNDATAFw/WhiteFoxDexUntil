package BaiFox;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class DeviceInfo {
    // 获取操作系统信息
    public String getOSInfo() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        String osVersion = props.getProperty("os.version");
        String osArch = props.getProperty("os.arch");
        return "操作系统名称：" + osName + "\n操作系统版本：" + osVersion + "\n操作系统架构：" + osArch;
    }

    // 获取IP地址
    public String getIPInfo() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return "IP地址：" + addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "获取IP地址失败";
        }
    }

    // 获取Java版本信息
    public String getJavaVersionInfo() {
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        String javaVendor = props.getProperty("java.vendor");
        return "Java版本：" + javaVersion + "\nJava供应商：" + javaVendor;
    }

    // 获取设备名称
    public String getDeviceName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return "设备名称：" + addr.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "获取设备名称失败";
        }
    }

    public static void main(String[] args) {
        DeviceInfo deviceInfo = new DeviceInfo();
        System.out.println(deviceInfo.getOSInfo());
        System.out.println(deviceInfo.getIPInfo());
        System.out.println(deviceInfo.getJavaVersionInfo());
        System.out.println(deviceInfo.getDeviceName());
    }
}
