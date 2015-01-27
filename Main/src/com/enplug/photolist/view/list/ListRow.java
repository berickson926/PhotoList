package com.enplug.photolist.view.list;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.view.list.ContentItem;
import com.enplug.sdk.view.list.IDrawable;

/**
 * Created by: berickson926
 * Date: 1/26/15
 * Time: 1:39 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class ListRow implements IDrawable
{
    public static final String AVATAR = "avatar";
    public static final String MESSAGE = "message";

    private final ObjectMap<String, ContentItem> _rowItems;

    private final Layout _layout;
    private Rectangle _dimensions;

    public ListRow(Layout layout, ContentItem userAvatar, ContentItem shortMessage)
    {
        _layout = layout;
        _rowItems = new ObjectMap<String, ContentItem>();
        _rowItems.put(AVATAR, userAvatar);
        _rowItems.put(MESSAGE, shortMessage);
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for(ContentItem item : _rowItems.values())
        {
            item.draw(batch);
        }
    }

    @Override
    public void setDimensions(Rectangle dimensions)
    {
        _dimensions = dimensions;

        setPosition(_dimensions.getPosition(new Vector2()));
    }

    @Override
    public void setPosition(Vector2 position)
    {
        _dimensions.x = position.x;
        _dimensions.y = position.y;

        setContentItemPosition(_rowItems.get(AVATAR), _layout._rowAvatarPosX, _layout._rowAvatarWidth);
        setContentItemPosition(_rowItems.get(MESSAGE), _layout._rowMessagePosX, _layout._rowMessageWidth);
    }

    @Override
    public void setPosition(float x, float y)
    {
        setPosition(new Vector2(x, y));
    }

    @Override
    public Vector2 getDrawableSize()
    {
        float height = 0.0f;
        float width = 0.0f;
        for(ContentItem item : _rowItems.values())
        {
            float itemHeight = item.getDrawableSize().y;
            if(itemHeight > height)
            {
                height = itemHeight;
            }

            width += item.getDrawableSize().x;
        }

        return new Vector2(width, height);
    }

    @Override
    public void dispose()
    {
        for(ContentItem item : _rowItems.values())
        {
            item.dispose();
        }

        _rowItems.clear();
    }

    private void setContentItemPosition(IDrawable item, float widthOffset, float itemWidth)
    {
        float itemX = _dimensions.x + widthOffset;
        float itemY = _dimensions.y;
        float itemHeight = _dimensions.height;

        Rectangle itemDimensions = new Rectangle(itemX, itemY, itemWidth, itemHeight);

        item.setDimensions(itemDimensions);
    }
}
