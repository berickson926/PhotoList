package com.enplug.photolist;

import com.enplug.test.TestContext;
import com.enplug.test.TestRun;

/**
 * Created by berickson926 on 2/10/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class PhotoListTestRun extends TestRun
{
    public PhotoListTestRun(Class gameClass, TestContext module)
    {
        super(gameClass, module);
    }

    @Override
    public void setUp()
    {
        setLanguage("English");
        setRequestShow(true);

        InstagramItemFactory factory = new InstagramItemFactory();

        addCachedSocialItem(factory.createNewItem());
        addCachedSocialItem(factory.createNewItem());
    }
}
