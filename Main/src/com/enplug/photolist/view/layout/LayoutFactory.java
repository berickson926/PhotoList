package com.enplug.photolist.view.layout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by berickson926 on 1/21/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public final class LayoutFactory
{
    private static final float WORLD_WIDTH_PORTRAIT = 1080.0f;
    private static final float WORLD_WIDTH_LANDSCAPE = 1920.0f;

    private static final Vector2 _scale = new Vector2(1.0f, 1.0f);

    private LayoutFactory(){}

    public static Layout getLayout(float screenWidth, float screenHeight)
    {
        setScale(screenWidth, screenHeight);

        Layout layout = (screenWidth > screenHeight) ? buildLandscapeLayout() : buildPortraitLayout();

        layout._screenSize = new Vector2(screenWidth, screenHeight);

        // Main post related config
        //
        layout._itemMessageSize = (int) (50 * _scale.x);
        layout._itemUserNameSize = (int) (60 * _scale.x);

        // List related config
        //
        layout._listBackgroundColor = new Color(0.4f, 0.4f, 0.4f, 1.0f);
        layout._listAlternateColor = new Color(0.2f, 0.2f, 0.5f, 1.0f);
        layout._listHighlightColor = new Color(0.0f, 0.5f, 0.5f, 1.0f);

        layout._listRowHeight = 148.0f * _scale.y;

        layout._rowAvatarPosX = 25.0f * _scale.x;
        layout._rowAvatarSize = 90.0f * _scale.x;

        layout._rowMessagePosX = layout._rowAvatarSize + layout._rowAvatarPosX + (50.0f * _scale.x);
        layout._rowMessageWidth = 300.0f;

        layout._listMessageLength = 40;
        layout._listMessageSize = (int) (40 * _scale.x);

        return layout;
    }

    private static void setScale(float screenWidth, float screenHeight)
    {
        _scale.x = screenWidth > screenHeight ? screenWidth / WORLD_WIDTH_LANDSCAPE : screenWidth / WORLD_WIDTH_PORTRAIT;
        _scale.y = screenWidth > screenHeight ? screenHeight / WORLD_WIDTH_PORTRAIT : screenHeight / WORLD_WIDTH_LANDSCAPE;
    }

    private static Layout buildLandscapeLayout()
    {
        Layout layout = new Layout();

        float scale = Math.max(_scale.x, _scale.y);

        layout._itemImageDim = new Rectangle(1450.0f * _scale.x, 450.0f * _scale.y, 400.0f * scale, 400.0f * scale);
        layout._itemAvatarDim = new Rectangle(1550.0f * _scale.x, 375.0f * _scale.y, 150.0f * scale, 150.0f * scale);
        layout._iconDim = new Rectangle(1350.0f * _scale.x, 375.0f * _scale.y, 150.0f * scale, 150.0f * scale);

        layout._itemMessageDim = new Rectangle(1050.0f * _scale.x, 300.0f * _scale.y, 850.0f * _scale.x, /*unused*/ 0.0f);
        layout._itemUserNamePos = new Vector2(1450.0f * _scale.x, 375.0f * _scale.y);

        layout._listDimensions = new Rectangle(20.0f * _scale.x, 20.0f * _scale.y, 1000.0f * _scale.x, 1040.0f * _scale.y);

        return layout;
    }

    private static Layout buildPortraitLayout()
    {
        Layout layout = new Layout();

        float scale = Math.max(_scale.x, _scale.y);

        layout._itemImageDim = new Rectangle(220.0f * _scale.x, 1300.0f * _scale.y, 400.0f * scale, 400.0f * scale);
        layout._itemAvatarDim = new Rectangle(850.0f * _scale.x, 1650.0f * _scale.y, 150.0f * scale, 150.0f * scale);
        layout._iconDim = new Rectangle(650.0f * _scale.x,1650.0f * _scale.y, 150.0f * scale, 150.0f * scale);

        layout._itemMessageDim = new Rectangle(20.0f * _scale.x, 1475.0f * _scale.y, 1020.0f * _scale.x, /*unused*/ 0.0f);
        layout._itemUserNamePos = new Vector2(750.0f * _scale.x, 1675.0f * _scale.y);

        layout._listDimensions = new Rectangle(20.0f * _scale.x, 20.0f * _scale.y, 1040.0f * _scale.x, 1200.0f * _scale.y);

        return layout;
    }
}
