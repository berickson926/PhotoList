package com.enplug.photolist.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.photolist.view.list.ListContentRenderer;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.view.list.*;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class ListRenderer
{
    private final World _world;
    private final IFontGenerator _fontGenerator;
    private final ILog _log;

    private Layout _layout;

    private ListBox _list;

    private ListContentRenderer _contentRenderer;

    public ListRenderer(World world, IFontGenerator fontGenerator, ILog log, Layout layout)
    {
        _world = world;
        _fontGenerator = fontGenerator;
        _log = log.getSubLog(this);
        _layout = layout;

        initializeListBox();
    }

    public void draw(SpriteBatch batch)
    {
        _list.draw(batch);
    }

    public void resize(Layout layout)
    {
        _log.debug("Resizing listbox");
        _layout = layout;

        dispose();

        initializeListBox();
    }

    public void dispose()
    {
        _log.debug("Disposing listbox resources.");
        if(_list != null)
        {
            _list.dispose();
            _list = null;
        }

        if(_contentRenderer != null)
        {
            _contentRenderer.dispose();
            _contentRenderer = null;
        }
    }

    public void refresh()
    {
        _list.onCollectionChanged();
        _list.setSelectedItem(_world.getHighlightedPost());
    }

    private void initializeListBox()
    {
        initializeContentRenderer();

        _list = new ListBox(_contentRenderer);
        initializeListSettings();

        _list.setItems(_world.getPosts());
    }

    private void initializeContentRenderer()
    {
        _contentRenderer = new ListContentRenderer(_fontGenerator, _layout);
        _contentRenderer.setContentAlignment(ContentItemAlignment.CENTERED_VERTICAL);
    }

    private void initializeListSettings()
    {
        _list.setListBackgroundColor(_layout._listBackgroundColor);
        _list.setDimensions(_layout._listDimensions);
        _list.setUseAlternateColumn(false);

        ItemStyle itemStyle = new ItemStyle(_layout._listBackgroundColor,
                                            _layout._listAlternateColor,
                                            _layout._listHighlightColor,
                                            /*uniform padding*/ 0.0f,
                                            /*uniform margins*/ 0.0f);

        _list.setItemStyle(itemStyle);
        _list.setItemHeight(_layout._listRowHeight);
        _list.setMargins(0.0f);
    }
}
