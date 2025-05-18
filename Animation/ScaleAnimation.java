package Animation;

import android.view.View;
import android.animation.ValueAnimator;

public class ScaleAnimation {

    // 静态方法：默认放大到1.5倍，缩小到0.5倍
    public static void start(View view, long duration) {
        start(view, duration, 1.5f, 0.5f);
    }

    // 静态方法：自定义放大倍数和缩小倍数
    public static void start(final View view, long duration, final float scaleUp, final float scaleDown) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					float fraction = animation.getAnimatedFraction();
					// 根据动画进度计算当前的缩放值
					float scale = scaleUp + (scaleDown - scaleUp) * fraction;
					view.setScaleX(scale);
					view.setScaleY(scale);
				}
			});
        animator.start();
    }
}

