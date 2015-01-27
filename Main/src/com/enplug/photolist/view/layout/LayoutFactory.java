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

        layout._listBackgroundColor = new Color(0.4f, 0.4f, 0.4f, 1.0f);
        layout._listAlternateColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
        layout._listHighlightColor = new Color(0.0f, 0.5f, 0.5f, 1.0f);

        layout._listRowHeight = 148.0f * _scale.y;

        layout._rowAvatarPosX = 10.0f;
        layout._rowAvatarWidth = 30.0f;

        layout._rowMessagePosX = 50.0f;
        layout._rowMessageWidth = 300.0f;

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

        layout._itemImagePos = new Vector2(540.0f * _scale.x, 1700.0f * _scale.y);
        layout._itemAvatarPos = new Vector2(300.0f * _scale.x, 1400.0f * _scale.y);
        layout._itemMessagePos = new Vector2(450.0f * _scale.x, 1400.0f * _scale.y);

        layout._listDimensions = new Rectangle(20.0f * _scale.x, 20.0f * _scale.y, 1200.0f * _scale.x, 1040.0f * _scale.y);

        return layout;
    }

    private static Layout buildPortraitLayout()
    {
        Layout layout = new Layout();

        layout._itemImagePos = new Vector2(1700.0f * _scale.x, 540.0f * _scale.y);
        layout._itemAvatarPos = new Vector2(1400.0f * _scale.x, 300.0f * _scale.y);
        layout._itemMessagePos = new Vector2(1400.0f * _scale.x, 450.0f * _scale.y);

        layout._listDimensions = new Rectangle(20.0f * _scale.x, 20.0f * _scale.y, 1040.0f * _scale.x, 1200.0f * _scale.y);

        return layout;
    }
}
