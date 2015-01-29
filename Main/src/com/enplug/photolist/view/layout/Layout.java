package com.enplug.photolist.view.layout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by berickson926 on 1/21/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class Layout
{
    public Color _listBackgroundColor;
    public Color _listHighlightColor;
    public Color _listAlternateColor;

    public Vector2 _screenSize;

    public Rectangle _itemImageDim;
    public Rectangle _itemAvatarDim;
    public Rectangle _iconDim;
    public Rectangle _itemMessageDim;
    public Vector2 _itemUserNamePos;
    public int _itemUserNameSize;
    public int _itemMessageSize;

    public Rectangle _listDimensions;
    public float _listRowHeight;

    public float _rowAvatarPosX;
    public float _rowAvatarSize;

    public float _rowMessagePosX;
    public float _rowMessageWidth;

    public int _listMessageLength;
    public int _listMessageSize;
}
