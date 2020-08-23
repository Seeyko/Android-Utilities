package com.tomandrieu.utilities.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.tomandrieu.utilities.R;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {
    private static final String PERFIX_DEFAULT_NAVIGATE_LINK_BY_COORDINATES = "geo:";
    private static final String PERFIX_DEFAULT_NAVIGATE_LINK_BY_ADDRESS = "geo:0,0?q=";
    private static final String PERFIX_DEFAULT_GOOGLE_NAVIGATE = "google.navigation:q=";

    private static Intent getChooserNavigateIntent(Context context, Intent navigateIntent, LatLng posInfo) {

        // Check if there is a default app opener for this type of content.
        final PackageManager packageManager = context.getPackageManager();
        ResolveInfo defaultAppInfo = packageManager.resolveActivity(navigateIntent,
                PackageManager.MATCH_DEFAULT_ONLY);


        // create the intent for intent chooser
        List<Intent> targetedOpenIntents = new ArrayList<Intent>();
        List<ResolveInfo> appInfoList = packageManager.queryIntentActivities(navigateIntent,
                PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo appInfo : appInfoList) {
            String packageName = appInfo.activityInfo.packageName;
            Intent targetedOpenIntent = new Intent(android.content.Intent.ACTION_VIEW).setPackage(packageName);
            targetedOpenIntent = getNavigateIntent(appInfo, targetedOpenIntent, posInfo);
            targetedOpenIntents.add(targetedOpenIntent);
        }

        // create the intent chooser. delete the first member in the list(the default activity)
        Intent chooserIntent = Intent.createChooser(targetedOpenIntents.remove(0),
                context.getString(R.string.navigate_intent_chooser_title)).putExtra(Intent.EXTRA_INITIAL_INTENTS,
                targetedOpenIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    private static Intent getNavigateIntent(ResolveInfo appInfo, Intent navigateIntent, LatLng posInfo) {
        // link urls
        final String GOOGLE_NAVIGATE_PACKAGE = "com.google.android.apps.maps";
        String suffixCoordinatesLink = posInfo.latitude + "," + posInfo.longitude;

        boolean isGoogleMaps = GOOGLE_NAVIGATE_PACKAGE.equals(appInfo.activityInfo.packageName);

        //build the link url
        StringBuilder link = new StringBuilder();
        link.append((isGoogleMaps ? PERFIX_DEFAULT_GOOGLE_NAVIGATE : PERFIX_DEFAULT_NAVIGATE_LINK_BY_COORDINATES));
        link.append(suffixCoordinatesLink);

        return navigateIntent.setData(Uri.parse(link.toString()));
    }
}
