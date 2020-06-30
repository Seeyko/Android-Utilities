package com.tomandrieu.utilities;

import android.content.Context;
import android.util.Log;

import static com.tomandrieu.utilities.constants.PreferenceConstants.INTRO_PREFERENCE_FILE_KEY;
import static com.tomandrieu.utilities.constants.PreferenceConstants.KEY_DISPLAY_INTRO_ON_CREATED;

public interface SeeykoIntroScreens {

    void showIntroScreen();

    default void doNotShowIntroScreenAgain() {
        Log.w("IntroScreens", "doNotShowIntroAgain: " + this.getClass().getSimpleName());
        getClassContext().getSharedPreferences(INTRO_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).edit().putBoolean(KEY_DISPLAY_INTRO_ON_CREATED + getIntroTag(), false).apply();
    }

    default void displayIntroScreen() {
        if (isIntroDisplayable()) {
            showIntroScreen();
        }
    }

    default boolean isIntroDisplayable() {
        return getClassContext().getSharedPreferences(INTRO_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).getBoolean(KEY_DISPLAY_INTRO_ON_CREATED + getIntroTag(), true);
    }

    Context getClassContext();

    String getIntroTag();

}