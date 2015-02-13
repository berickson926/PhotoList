package com.enplug.photolist.testframework;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.enplug.photolist.PhotoList;
import com.enplug.test.DesktopTestContext;
import com.enplug.test.TestRun;

/**
 * Created by berickson926 on 1/22/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class PhotoListTest
{
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "PhotoList App";
        cfg.width = 540;
        cfg.height = 960;

        TestRun test = new PhotoListTestRun(PhotoList.class, new DesktopTestContext());
        new LwjglApplication(test.getHost(), cfg);
        test.setUp();
        test.run();
    }
}
