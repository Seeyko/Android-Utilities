package com.tomandrieu.utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tomandrieu.utilities.ui.TextBottomSheetFragment;

public class SeeykoViewUtils {

    /**
     * @param view         View to animate
     * @param toVisibility Visibility at the end of animation
     * @param toAlpha      Alpha at the end of animation
     * @param duration     Animation duration in ms
     */
    public static void fadeView(final View view, final int toVisibility, float toAlpha, int duration) {
        boolean show = toVisibility == View.VISIBLE;
        if (show) {
            view.setAlpha(view.getAlpha() > 0f ? 1f : 0);
            view.setVisibility(View.VISIBLE);
        } else {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(View.VISIBLE);
            }
        }
        view.animate()
                .setDuration(duration)
                .alpha(show ? toAlpha : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(toVisibility);
                    }
                });
    }

    public static void showBottomSheetWithText(AppCompatActivity activity, String text) {
        TextBottomSheetFragment textBottomSheetFragment = new TextBottomSheetFragment("\" " + text + " \"");
        textBottomSheetFragment.show(activity.getSupportFragmentManager(), textBottomSheetFragment.getTag());
    }

}
