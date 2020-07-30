package com.tomandrieu.utilities;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PhotoFullPopupWindow extends PopupWindow {

    private static final String TAG = "PhotoFullPopupWindow";
    View view;
    Context mContext;
    PhotoView photoView;


    public PhotoFullPopupWindow(Context ctx, View clickedImage, List<String> imageUrl) {
        super(((LayoutInflater) Objects.requireNonNull(ctx.getSystemService(LAYOUT_INFLATER_SERVICE))).inflate(R.layout.popup_photo_full, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (Build.VERSION.SDK_INT >= 21) {
            setElevation(5.0f);
        }
        this.mContext = ctx;
        if (Build.VERSION.SDK_INT >= 21) {
            setElevation(5.0f);
        }
        this.mContext = ctx;
        this.view = getContentView();

        View closeButton = view.findViewById(R.id.ib_close);

        setOutsideTouchable(true);

        setFocusable(true);
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                dismiss();
            }
        });

        //---------Begin customising this popup--------------------
        ViewPager viewPager = view.findViewById(R.id.details_image_pager);
        Log.e(TAG, "viewPager: "  + viewPager);

        PhotoFullPagePager photoPager = new PhotoFullPagePager(getContentView().getContext(), imageUrl);
        viewPager.setAdapter(photoPager);
        CircleIndicator circleIndicator = view.findViewById(R.id.details_circle_indicator);
        circleIndicator.setViewPager(viewPager);


        setAnimationStyle(R.style.AnimationFade);

        showAtLocation(clickedImage, Gravity.CENTER, 0, 0);
        //------------------------------

    }

    public void onPalette(Palette palette) {
        if (null != palette) {
            ViewGroup parent = (ViewGroup) photoView.getParent().getParent();
            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
        }
    }

}