package com.tomandrieu.utilities.drawables;

import android.os.Build;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Helper class to avoid boilerplate Build version checks
 *
 * @hide
 */
class Android {

    private static int VERSION = Build.VERSION.SDK_INT;

    /**
     * Return whether current {@link #VERSION} is equals Lollipop(21)
     * sdk version
     *
     * @return whether current version is equals Lollipop(21)
     * sdk version
     */
    static boolean isLollipop() {
        return VERSION >= LOLLIPOP;
    }

}
