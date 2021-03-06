package com.enplug.photolist.control.listeners;

import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.PhotoListState;
import com.enplug.photolist.model.World;
import com.enplug.sdk.hosting.AppState;
import com.enplug.sdk.interfaces.IAppStatusListener;
import com.enplug.sdk.interfaces.ISocialItemListener;
import com.enplug.sdk.model.ObservableCollection;
import com.enplug.sdk.model.social.SocialItem;
import com.enplug.sdk.model.social.instagram.InstagramItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by: berickson926
 * Date: 1/23/15
 * Time: 11:49 AM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class InstagramListener implements ISocialItemListener
{
    private final World _world;
    private final ILog _log;
    private final IAppStatusListener _appStatusListener;

    public InstagramListener(World world, ILog log, IAppStatusListener appStatusListener)
    {
        _world = world;
        _log = log.getSubLog(this);
        _appStatusListener = appStatusListener;
    }

    @Override
    public void onNewItem(String feedId, SocialItem socialItem)
    {
        _log.debug("Received new social interaction");

        ObservableCollection<InstagramItem> instagramPosts = _world.getPosts();

        instagramPosts.add((InstagramItem) socialItem);

        readyCheck();
    }

    @Override
    public void onUpdateItem(String feedId, SocialItem socialItem)
    {
        _log.debug("Received social interaction to update.");

        ObservableCollection<InstagramItem> instagramPosts = _world.getPosts();
        List<SocialItem> items = new ArrayList<SocialItem>();
        items.addAll(instagramPosts.getItems());

        if(items.contains(socialItem))
        {
            _log.debug("Item match found in list, updating.");

            int index = items.indexOf(socialItem);
            instagramPosts.updateItem((InstagramItem)socialItem, index);
        }
    }

    @Override
    public void onDeleteItem(String feedId, String itemId)
    {
        _log.debug("Received social interaction to delete.");

        ObservableCollection<InstagramItem> instagramPosts = _world.getPosts();
        Collection<InstagramItem> items = instagramPosts.getItems();
        Collection<InstagramItem> itemsToDelete = new ArrayList<InstagramItem>();

        for (InstagramItem nextItem : items)
        {
            if (nextItem.getId().equalsIgnoreCase(itemId))
            {
                _log.debug("Match found to delete.");
                itemsToDelete.add(nextItem);
            }
        }

        for(InstagramItem deleteItem : itemsToDelete)
        {
            _log.verbose("Deleting : " + deleteItem);
            instagramPosts.remove(deleteItem);
            readyCheck();
        }
    }

    private void readyCheck()
    {
        int postCount = _world.getPosts().getItems().size();

        if((postCount > 0) && (_world.getPhotoListState() == PhotoListState.NotReady))
        {
            _world.setPhotoListState(PhotoListState.Ready);
            _appStatusListener.onStateChanged(AppState.Ready);
        }

        if((postCount <= 0) && (_world.getPhotoListState() == PhotoListState.Ready))
        {
            _appStatusListener.onStateChanged(AppState.Error);
        }
    }
}
