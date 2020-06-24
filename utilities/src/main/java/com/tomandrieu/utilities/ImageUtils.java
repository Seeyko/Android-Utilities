package com.tomandrieu.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    public final static BitmapDescriptor bitmapDescriptorFromVector(Drawable vectorDrawable) {
        vectorDrawable.setBounds(0, 0, (int) (vectorDrawable.getIntrinsicWidth() * 1.3), (int) (vectorDrawable.getIntrinsicHeight() * 1.3));
        Bitmap bitmap = Bitmap.createBitmap((int) (vectorDrawable.getIntrinsicWidth() * 1.3), (int) (vectorDrawable.getIntrinsicHeight() * 1.3), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static byte[] getBytes(ImageView view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return getBytes(((BitmapDrawable) view.getDrawable()).getBitmap());
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static String getStringImage(ImageView view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return getStringImage(((BitmapDrawable) view.getDrawable()).getBitmap());
    }

    public static String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
