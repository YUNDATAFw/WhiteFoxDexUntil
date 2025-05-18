import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

public class DeviceInfoUtil {

    // 获取设备型号
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    // 获取设备品牌
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    // 获取设备制造商
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    // 获取设备处理器信息
    public static String getDeviceCpuInfo() {
        return Build.BOARD;
    }

    // 获取设备的IP地址
    public static String getIpAddress(Context context) {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            Log.e("DeviceInfoUtil", "Error getting IP address", e);
        }
        return "Unknown";
    }

    // 获取设备的网络类型（WiFi或移动网络）
    public static String getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return "Unknown";
        }
        NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return "Unknown";
        }
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return "WiFi";
        } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return "Mobile";
        }
        return "Unknown";
    }

    // 获取设备的MAC地址
    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager == null) {
            return "Unknown";
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null || wifiInfo.getMacAddress() == null) {
            return "Unknown";
        }
        return wifiInfo.getMacAddress().trim();
    }

    // 获取设备的Android版本
    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    // 获取设备的SDK版本号
    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    // 获取设备的总存储容量
    public static String getTotalStorage() {
        long totalStorage = Runtime.getRuntime().totalMemory();
        return String.format("%.2f MB", totalStorage / (1024.0 * 1024));
    }

    // 获取设备的可用存储容量
    public static String getAvailableStorage() {
        long availableStorage = Runtime.getRuntime().freeMemory();
        return String.format("%.2f MB", availableStorage / (1024.0 * 1024));
    }

    // 获取设备的CPU核心数
    public static int getCpuCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

}

