package Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;

public class Toast {

    private static Toast toast;

    public static void showToast(Context context, String message) {
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		// 设置卡片视图
		CardView cardView = new CardView(context);
		cardView.setRadius(16f);
		cardView.setCardBackgroundColor(Color.WHITE);
		cardView.setCardElevation(4f);
		LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		cardParams.setMargins(16, 16, 16, 16);
		cardView.setLayoutParams(cardParams);

		// 设置横向线性布局
		LinearLayout horizontalLayout = new LinearLayout(context);
		horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		horizontalParams.setMargins(16, 0, 16, 0);
		horizontalLayout.setLayoutParams(horizontalParams);

		// 设置消息文本
		TextView messageTextView = new TextView(context);
		messageTextView.setText(message);
		LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		messageParams.gravity = Gravity.CENTER_VERTICAL;
		messageParams.weight = 1;
		messageParams.setMargins(16, 0, 0, 0);
		messageTextView.setLayoutParams(messageParams);
		messageTextView.setTextSize(16f);
		messageTextView.setTextColor(Color.BLACK);
		messageTextView.setMaxWidth(context.getResources().getDimensionPixelSize(
										android.R.dimen.notification_large_icon_width
									));
		messageParams.setMargins(0, 0, 0, 0); // 将Margin设置为0

		// 添加到布局中
		horizontalLayout.addView(messageTextView);
		cardView.addView(horizontalLayout);
		linearLayout.addView(cardView);

		// 显示Toast
		if (toast != null) {
			toast.cancel();
		}
		toast = new Toast(context);
		toast.setView(linearLayout);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public static void showToast(Context context, String message, float textSize, String backgroundColor, float radius, String textColor, int padding) {
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		// 设置卡片视图
		CardView cardView = new CardView(context);
		cardView.setRadius(radius); // 设置圆角大小
		cardView.setCardBackgroundColor(Color.parseColor(backgroundColor)); // 设置背景颜色
		cardView.setCardElevation(4f);
		LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		cardParams.setMargins(16, 16, 16, 16);
		cardView.setLayoutParams(cardParams);

		// 设置横向线性布局
		LinearLayout horizontalLayout = new LinearLayout(context);
		horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams horizontalParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		horizontalParams.setMargins(16, 0, 16, 0);
		horizontalLayout.setLayoutParams(horizontalParams);

		// 设置消息文本
		TextView messageTextView = new TextView(context);
		messageTextView.setText(message);
		LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		);
		messageParams.gravity = Gravity.CENTER_VERTICAL;
		messageParams.weight = 1;
	
		messageParams.setMargins(padding, padding, padding, padding);
		messageTextView.setMaxLines(1);
		messageTextView.setLayoutParams(messageParams);
		messageTextView.setTextSize(textSize);
		messageTextView.setTextColor(Color.parseColor(textColor)); // 设置字体颜色
	
		// 添加到布局中
		horizontalLayout.addView(messageTextView);
		cardView.addView(horizontalLayout);
		linearLayout.addView(cardView);

		// 显示Toast
		if (toast != null) {
			toast.cancel();
		}
		toast = new Toast(context);
		toast.setView(linearLayout);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setDuration(Toast.LENGTH_SHORT); // 设置显示时间
		toast.show();
	}
	
	
}

