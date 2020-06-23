package com.tomandrieu.utilities;

import android.content.Context;
import static com.tomandrieu.utilities.constants.PreferenceConstants.INTRO_PREFERENCE_FILE_KEY;
import static com.tomandrieu.utilities.constants.PreferenceConstants.KEY_DISPLAY_INTRO_ON_CREATED;

public interface SeeykoIntroScreens {

    void showIntroScreen();

    default void doNotShowIntroScreenAgain() {
        getClassContext().getSharedPreferences(INTRO_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).edit().putBoolean(KEY_DISPLAY_INTRO_ON_CREATED + this.getClass().getSimpleName(), false).apply();
    }

    default void displayIntroScreen() {
        if (isIntroDisplayable()) {
            showIntroScreen();
        }
    }

    default boolean isIntroDisplayable() {
        return getClassContext().getSharedPreferences(INTRO_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE).getBoolean(KEY_DISPLAY_INTRO_ON_CREATED + this.getClass().getSimpleName(), true);
    }

    Context getClassContext();
}