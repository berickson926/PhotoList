package com.enplug.photolist.view;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.interfaces.IFontGenerator;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class ItemRenderer
{
    private static final String TAG = "[PhotoList]:ItemRenderer";

    private final World _world;
    private final IFontGenerator _fontGenerator;
    private final FileHandleResolver _assetResolver;
    private final ILog _log;

    private Layout _layout;

    public ItemRenderer(World world, IFontGenerator fontGenerator, FileHandleResolver assetResolver, ILog log)
    {
        _world = world;
        _fontGenerator = fontGenerator;
        _assetResolver = assetResolver;
        _log = log.getSubLog(TAG);
    }

    public void draw(SpriteBatch batch)
    {

    }

    public void resize(Layout layout)
    {
        _layout = layout;
    }
}
