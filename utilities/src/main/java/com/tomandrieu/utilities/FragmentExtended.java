package com.tomandrieu.utilities;

import android.content.Context;

import androidx.fragment.app.Fragment;

public abstract class FragmentExtended extends Fragment implements SeeykoIntroScreens {

    /**
     * Handle back press in fragment
     * Return false to fire activity back press
     * Return true to cancel activity back press
     *
     * @return
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public Context getClassContext() {
        return getContext();
    }

    @Override
    public void showIntroScreen() {
        
    }
}
