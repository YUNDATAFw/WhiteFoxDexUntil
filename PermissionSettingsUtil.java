import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class PermissionSettingsUtil {

    /**
     * 跳转到应用的权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用的通知设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppNotificationSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            openAppSettings(context, packageName); // 兼容低版本
        }
    }

    /**
     * 跳转到应用的电量优化设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppBatterySettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            openAppSettings(context, packageName); // 兼容低版本
        }
    }

    /**
     * 跳转到应用的悬浮窗权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppOverlaySettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用的存储权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppStorageSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用的位置权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppLocationSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用的相机权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppCameraSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用的麦克风权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppMicrophoneSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    /**
     * 跳转到应用的所有文件访问权限设置页面
     *
     * @param context 上下文
     * @param packageName 应用的包名
     */
    public static void openAppAllFilesAccessSettings(Context context, String packageName) {
        if (context == null || packageName == null) {
            throw new IllegalArgumentException("Context and packageName cannot be null");
        }

        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    
}

