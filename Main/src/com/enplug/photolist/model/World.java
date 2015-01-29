package com.enplug.photolist.model;

import com.enplug.sdk.model.ObservableCollection;
import com.enplug.sdk.model.social.instagram.InstagramItem;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class World
{
    private PhotoListState _photoListState;

    private final ObservableCollection<InstagramItem> _posts;
    private int _highlightedPost;

    public World()
    {
        _photoListState = PhotoListState.NotReady;
        _highlightedPost = -1;
        _posts = new ObservableCollection<InstagramItem>();
    }

    public void setPhotoListState(PhotoListState state)
    {
        _photoListState = state;
    }

    public PhotoListState getPhotoListState()
    {
        return _photoListState;
    }

    public void setHighlightedPost(int highlight)
    {
        _highlightedPost = highlight;
    }

    public int getHighlightedPost()
    {
        return _highlightedPost;
    }

    public ObservableCollection<InstagramItem> getPosts()
    {
        return _posts;
    }
}
