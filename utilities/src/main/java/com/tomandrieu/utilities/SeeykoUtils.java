package com.tomandrieu.utilities;

import android.content.Context;

public class SeeykoUtils {
    public static int pixelsInDp(int pixels, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pixels * density + 0.5f);
    }

}
