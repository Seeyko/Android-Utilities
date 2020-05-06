package com.tomandrieu.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView implements View.OnClickListener {


    private Context context;

    public CustomImageView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        TypedValue outValueSelectable = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValueSelectable, true);

        setBackgroundResource(outValueSelectable.resourceId);
        setOnClickListener(this);
        Log.e("CustomImageView", "activity name : " + context.getClass().getSimpleName());
    }

    @SuppressLint("WrongThread")
    @Override
    public void onClick(View view) {
        if (getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            new PhotoFullPopupWindow(context, R.layout.popup_photo_full, view, bitmap, null);
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.wait_load), Toast.LENGTH_SHORT).show();
        }

    }
}
