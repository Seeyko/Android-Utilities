package com.tomandrieu.utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class PagingViewPager extends ViewPager {

    private boolean pagingEnabled;

    public PagingViewPager(Context context) {
        super(context);
        this.pagingEnabled = true;
    }

    public PagingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.pagingEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.pagingEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.pagingEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.pagingEnabled = enabled;
    }
}
