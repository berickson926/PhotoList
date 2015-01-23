package com.enplug.photolist;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

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
        cfg.title = "PhotoListApp";
        cfg.width = 540;
        cfg.height = 960;
        cfg.resizable = true;

        PhotoListSetup desktopSetup = new PhotoListSetup();
        desktopSetup.createServices();

        new LwjglApplication(desktopSetup.getHost(), cfg);

        desktopSetup.setup();
        desktopSetup.loadSchedule();
        desktopSetup.registerTestInputProcessor();
    }
}
