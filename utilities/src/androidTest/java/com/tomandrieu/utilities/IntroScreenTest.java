package com.tomandrieu.utilities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.junit.Test;

public class IntroScreenTest {

    @Test
    public void introScreenTestDoNotShowAgain() {
        Home home = Home.newInstance();
        Form form = Form.newInstance();
    }

}

class Home extends FragmentExtended{

    public static Home newInstance() {

        Bundle args = new Bundle();

        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HOME", "on create");
        doNotShowIntroScreenAgain();
    }
}

class Form extends FragmentExtended{

    public static Form newInstance() {

        Bundle args = new Bundle();
        Log.e("FORM", "new instance");
        System.out.println("FORM : new instance");

        Form fragment = new Form();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FORM", "on create");
        System.out.println("FORM : on create");
        doNotShowIntroScreenAgain();
    }
}