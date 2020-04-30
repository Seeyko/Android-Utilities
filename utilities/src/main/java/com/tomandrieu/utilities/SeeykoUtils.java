package com.tomandrieu.utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class SeeykoUtils {
    public static int pixelsInDp(int pixels, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pixels * density + 0.5f);
    }

    public static void openAppInGooglePlay(AppCompatActivity appCompatActivity, boolean closeApp) {
        final String appPackageName = appCompatActivity.getPackageName(); // getPackageName() from Context or Activity object
        try {
            appCompatActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            appCompatActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        if (closeApp) {
            closeApplication(appCompatActivity);
        }
    }

    public static void closeApplication(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appCompatActivity.finishAndRemoveTask();
        } else {
            appCompatActivity.finishAffinity();
        }
    }
}
