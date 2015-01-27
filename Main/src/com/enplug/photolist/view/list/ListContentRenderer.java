package com.enplug.photolist.view.list;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.model.social.instagram.InstagramItem;
import com.enplug.sdk.view.list.ContentRenderer;
import com.enplug.sdk.view.list.IDrawable;

/**
 * Created by: berickson926
 * Date: 1/26/15
 * Time: 1:39 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class ListContentRenderer extends ContentRenderer<InstagramItem>
{
    private Layout _layout;

    protected ListContentRenderer(IFontGenerator fontGenerator, FileHandleResolver assetResolver)
    {
        super(fontGenerator, assetResolver);


    }

    @Override
    public IDrawable renderItem(InstagramItem listRow)
    {
        return null;
    }

    @Override
    public void dispose()
    {

    }
}
