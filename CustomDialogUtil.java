import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CustomDialogUtil {

    private static Dialog dialog;

    /**
     * 弹出自定义对话框
     *
     * @param context 上下文
     * @param view    自定义的View
     * @return 返回Dialog对象，可用于外部关闭对话框
     */
    public static Dialog showDialog(Context context, View view) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题栏
        dialog.setContentView(view);
        dialog.setCancelable(true); // 设置点击外部可关闭

        // 设置背景透明
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽度为MATCH_PARENT
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);

        dialog.show();

        return dialog; // 返回Dialog对象
    }

    /**
     * 关闭对话框
     */
    public static void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}

