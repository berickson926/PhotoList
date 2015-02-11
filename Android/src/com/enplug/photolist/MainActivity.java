package com.enplug.photolist;

import android.app.ActivityManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.enplug.test.AndroidTestContext;
import com.enplug.test.TestRun;

public class MainActivity extends AndroidApplication
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        TestRun test = new PhotoListTestRun(PhotoList.class, new AndroidTestContext(this, getContext(), activityManager));
        initialize(test.getHost(), cfg);
        test.setUp();
        test.run();
    }
}