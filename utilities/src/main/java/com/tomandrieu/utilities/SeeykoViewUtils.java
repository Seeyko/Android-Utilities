package com.tomandrieu.utilities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.InputType;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.lb.auto_fit_textview.AutoResizeTextView;
import com.tomandrieu.utilities.ui.TextBottomSheetFragment;

import static com.tomandrieu.utilities.StringUtils.firstCharUppercase;

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

    public static AppCompatImageView createIcon(Context context, int resId, AppCompatImageView.ScaleType scaleType, TableLayout.LayoutParams iconParams) {
        AppCompatImageView icon = new AppCompatImageView(context);
        icon.setLayoutParams(iconParams);
        icon.setScaleType(scaleType);

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.colorOnBackground, typedValue, true);

        icon.setColorFilter(ContextCompat.getColor(context, typedValue.resourceId), android.graphics.PorterDuff.Mode.SRC_IN);
        icon.setImageDrawable(context.getResources().getDrawable(resId));
        return icon;
    }

    public static TextView createAutoResizeTextView(Context context, String text, boolean singleLine, int replacementStringId, boolean selectable) {

        TextView textView = new AutoResizeTextView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(SeeykoUtils.pixelsInDp(20, context), 0, 0, 0);


        if (text != null && !text.trim().isEmpty()) {
            textView.setText((Html.fromHtml(text)));
        } else if (replacementStringId != 0) {
            textView.setText(firstCharUppercase(context.getResources().getString(replacementStringId)));
            textView.setTypeface(null, Typeface.ITALIC);
        } else {
            textView.setText(firstCharUppercase(context.getResources().getString(R.string.hint_provide_information)));
            textView.setTypeface(null, Typeface.ITALIC);
        }
        ((AutoResizeTextView) textView).setMinTextSize(context.getResources().getDimension(R.dimen.text_medium));

        if (singleLine) {
            textView.setSingleLine(true);
        } else {
            textView.setMaxLines(2);
        }
        textView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        Linkify.addLinks(textView, Linkify.WEB_URLS);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextIsSelectable(selectable);
        return textView;
    }

    public static LinearLayout createLinearLayout(Context context, int backgroundRessource, String textToCopy, TableLayout.LayoutParams layoutParams, String toastText) {
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(layoutParams);

        if (toastText != null && textToCopy != null && !textToCopy.isEmpty()) {
            layout.setBackgroundResource(backgroundRessource);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SeeykoUtils.setClipboard(context, textToCopy);
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }

        return layout;
    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (Drawable drawable : textView.getCompoundDrawablesRelative()) {
                if (drawable != null) {
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
        } else {
            for (Drawable drawable : textView.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    public static void resetTextInputErrorsOnChanged(TextInputLayout... textInputLayouts) {
        for (final TextInputLayout inputLayout : textInputLayouts) {
            EditText editText = inputLayout.getEditText();
            if (editText != null) {
                editText
                        .setOnTouchListener((view, motionEvent) -> {
                            if (inputLayout.getError() != null) inputLayout.setError(null);

                            return false;
                        });
                inputLayout
                        .setOnTouchListener((view, motionEvent) -> {
                            if (inputLayout.getError() != null) inputLayout.setError(null);

                            return false;
                        });
            }

        }
    }
}
