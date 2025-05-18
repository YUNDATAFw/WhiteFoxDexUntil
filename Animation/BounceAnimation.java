package Animation;


import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.View;

public class BounceAnimation {

    // 静态方法：默认振幅100dp
    public static void start(View view, long duration) {
        start(view, duration, 100f);
    }

    // 静态方法：自定义振幅
    public static void start(final View view, long duration, float amplitudeInDp) {
        // 将dp转换为像素值，并取负实现向上移动
        final float amplitude = -TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            amplitudeInDp,
            view.getResources().getDisplayMetrics()
        );

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float fraction = animation.getAnimatedFraction();
                    // 使用正弦函数计算位移
                    float y = (float) (amplitude * Math.sin(Math.PI * fraction));
                    view.setTranslationY(y);
                }
            });
        animator.start();
    }
}

