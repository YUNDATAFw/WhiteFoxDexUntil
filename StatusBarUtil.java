import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtil {

    /**
     * 设置状态栏颜色
     *
     * @param activity 当前的Activity
     * @param colorStr 状态栏颜色的字符串（例如：#ffffff）
     */
    public static void setStatusBarColor(Activity activity, String colorStr) {
        // 检查是否支持状态栏颜色设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 获取Window对象
            Window window = activity.getWindow();
            // 清除可能存在的透明状态栏标志
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 添加系统UI可见性标志
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 设置状态栏颜色
            window.setStatusBarColor(Color.parseColor(colorStr));
        }
    }

    /**
     * 将#ffffff格式的颜色字符串转换为int类型的颜色值
     *
     * @param colorStr 颜色字符串（例如：#ffffff）
     * @return int类型的颜色值
     */
    public static int colorStringToInt(String colorStr) {
        return Color.parseColor(colorStr);
    }
}

