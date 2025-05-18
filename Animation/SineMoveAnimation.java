package Animation;

import android.view.View;
import android.animation.ValueAnimator;
import android.util.TypedValue;

public class SineMoveAnimation {

    // 静态方法：默认振幅100dp，动画时间为1000ms，执行两次
    public static void start(View view) {
        start(view, 1000, 100f, 2);
    }

    // 静态方法：自定义动画时间、振幅和执行次数
    public static void start(final View view, long duration, float amplitudeInDp, final int repeatCount) {
        // 将dp转换为像素值
        final float amplitude = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            amplitudeInDp,
            view.getResources().getDisplayMetrics()
        );

        ValueAnimator animator = ValueAnimator.ofFloat(0f, repeatCount * 1f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					float fraction = animation.getAnimatedFraction();
					// 计算当前动画进度对应的正弦值
					float x = (float) (amplitude * Math.sin(2 * Math.PI * fraction * repeatCount));
					view.setTranslationX(x);
				}
			});
        animator.start();
    }
}

