package com.tomandrieu.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class ImageUtils {
    public final static BitmapDescriptor bitmapDescriptorFromVector(Drawable vectorDrawable) {
        vectorDrawable.setBounds(0, 0, (int) (vectorDrawable.getIntrinsicWidth() * 1.3), (int) (vectorDrawable.getIntrinsicHeight() * 1.3));
        Bitmap bitmap = Bitmap.createBitmap((int) (vectorDrawable.getIntrinsicWidth() * 1.3), (int) (vectorDrawable.getIntrinsicHeight() * 1.3), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
