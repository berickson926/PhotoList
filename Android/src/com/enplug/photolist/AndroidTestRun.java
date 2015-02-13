package com.enplug.photolist;

import com.enplug.test.TestContext;
import com.enplug.test.TestRun;

/**
 * Created by berickson926 on 2/10/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class AndroidTestRun extends TestRun
{
    public AndroidTestRun(Class gameClass, TestContext module)
    {
        super(gameClass, module);
    }

    @Override
    public void setUp()
    {
        setLanguage("English");
        setRequestShow(true);

        AndroidIGItemFactory factory = new AndroidIGItemFactory();

        addCachedSocialItem(factory.createNewItem());
        addCachedSocialItem(factory.createNewItem());
    }
}
