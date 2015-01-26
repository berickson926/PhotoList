package com.enplug.photolist.view;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.view.list.DefaultContentRenderer;
import com.enplug.sdk.view.list.ItemStyle;
import com.enplug.sdk.view.list.ListBox;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class ListRenderer
{
    public static final String TAG = "[PhotoList]:ListRenderer";

    private final World _world;
    private final IFontGenerator _fontGenerator;
    private final FileHandleResolver _assetResolver;
    private final ILog _log;

    private Layout _layout;

    private ListBox _list;

    public ListRenderer(World world, IFontGenerator fontGenerator, FileHandleResolver assetResolver, ILog log, Layout layout)
    {
        _world = world;
        _fontGenerator = fontGenerator;
        _assetResolver = assetResolver;
        _log = log.getSubLog(TAG);
        _layout = layout;

        initializeListBox();
    }

    public void draw(SpriteBatch batch)
    {
        _list.draw(batch);
    }

    public void resize(Layout layout)
    {
        _layout = layout;

        if(_world.getPosts() != null)
        {
            _world.getPosts().removeObserver(_list);
        }

        _list.dispose();

        _list = null;

        initializeListBox();
    }

    public void dispose()
    {

    }

    private void initializeListBox()
    {
        initializeContentRenderers();

        _list = new ListBox(new DefaultContentRenderer(_fontGenerator, _assetResolver));
        //_list = new ListBox(_listContentRenderer, _footerRenderer);
        initializeListSettings();

        _list.setItems(_world.getPosts());
    }

    private void initializeContentRenderers()
    {
//        VenueInfo venueInfo = _world.getVenueInfo();
//        boolean showPartyWaitTime = venueInfo.showPartyWait();
//
//        _listContentRenderer = new ListContentRenderer(showPartyWaitTime, _fontGenerator, _assetResolver, _localizer);
//        _listContentRenderer.setContentAlignment(ContentItemAlignment.CENTERED_VERTICAL);
//
//        _footerRenderer = new FooterRenderer(_layout, _fontGenerator, _assetResolver, _localizer);
//        _footerRenderer.setContentAlignment(ContentItemAlignment.CENTERED);
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

        _list.setSelectedItem(_world.getHighlightedPost());
//        if(_layout.IS_LANDSCAPE)
//        {
//            _list.setColumnQty(2);
//        }
//        else
//        {
//            _list.setColumnQty(1);
//        }
    }
}
