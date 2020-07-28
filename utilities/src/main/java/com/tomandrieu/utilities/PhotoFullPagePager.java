package com.tomandrieu.utilities;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.tomandrieu.utilities.constants.ImageConstants;

import java.util.List;

public class PhotoFullPagePager extends PagerAdapter {
    private Context context;
    private List<String> photosUrl;

    public PhotoFullPagePager(Context context) {
        this.context = context;
    }

    public PhotoFullPagePager(Context context, List<String> photosUrl) {
        this.context = context;
        this.photosUrl = photosUrl;
    }

    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_popup_pager_item, null);
        PhotoView photoView = view.findViewById(R.id.image);
        View loading = view.findViewById(R.id.progress_overlay);
        ViewGroup parent = (ViewGroup) photoView.getParent();

        String imageUri = photosUrl.get(position);

        Bitmap bitmap = null;
        if (photoView.getDrawable() != null) {
            bitmap = ((BitmapDrawable) photoView.getDrawable()).getBitmap();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.wait_load), Toast.LENGTH_SHORT).show();
        }

        //----------------------------

        loading.setVisibility(View.VISIBLE);
        Glide.with(context)
                .asBitmap()
                .load(imageUri)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        loading.setBackgroundColor(Color.LTGRAY);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            parent.setBackground(new BitmapDrawable(context.getResources(), ImageConstants.fastblur(Bitmap.createScaledBitmap(resource, 50, 50, true))));// ));
                        } else {
                            onPalette(Palette.from(resource).generate());
                        }
                        photoView.setImageBitmap(resource);

                        loading.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoView);

        container.addView(view);
        return view;
    }

    private void onPalette(Palette generate) {

    }

    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return photosUrl.size();
    }

    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }


    public void setPhotosUrl(List<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

}
