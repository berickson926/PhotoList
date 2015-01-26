package com.enplug.photolist.TestFramework;

import com.badlogic.gdx.Gdx;
import com.enplug.sdk.interfaces.ISocialItemListener;
import com.enplug.sdk.interfaces.ISocialProvider;
import com.enplug.sdk.model.social.instagram.InstagramItem;

/**
 * Created by: berickson926
 * Date: 1/23/15
 * Time: 4:21 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class SocialProvider implements ISocialProvider
{
    private String _feedId;
    private ISocialItemListener _listener;

    @Override
    public void addListener(String feedId, ISocialItemListener listener)
    {
        Gdx.app.log("Test Launcher", "Initializing listener: " + listener.getClass().toString() + ", for feed ID: " + feedId);
        _feedId = feedId;
        _listener = listener;
    }

    @Override
    public void removeListener(String feedId, ISocialItemListener listener)
    {
        Gdx.app.log("Test Launcher", "Received listener removal request for feed id: " + feedId);
        _feedId = null;
        _listener = null;
    }

    public void onNewItem(InstagramItem newItem)
    {
        if((_listener != null) && (_feedId != null))
        {
            _listener.onNewItem(_feedId, newItem);
        }
    }

    public void onUpdateItem(InstagramItem updateItem)
    {
        if((_listener != null) && (_feedId != null))
        {
            _listener.onUpdateItem(_feedId, updateItem);
        }
    }

    public void onDeleteItem(InstagramItem deleteItem)
    {
        if((deleteItem != null) && (_listener != null) && (_feedId != null))
        {
            String deleteItemId = deleteItem.getId();

            _listener.onDeleteItem(_feedId, deleteItemId);
        }
    }
}
