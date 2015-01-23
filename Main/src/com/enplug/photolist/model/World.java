package com.enplug.photolist.model;

import com.enplug.sdk.model.ObserveableCollection;
import com.enplug.sdk.model.social.instagram.InstagramItem;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class World
{
    private int _highlightedPost;
    private ObserveableCollection<InstagramItem> _posts;

    public World()
    {
        _highlightedPost = -1;
        _posts = new ObserveableCollection<InstagramItem>();
    }

    public void setHighlightedPost(int highlight)
    {
        _highlightedPost = highlight;
    }

    public int getHighlightedPost()
    {
        return _highlightedPost;
    }

    public ObserveableCollection<InstagramItem> getPosts()
    {
        return _posts;
    }
}
