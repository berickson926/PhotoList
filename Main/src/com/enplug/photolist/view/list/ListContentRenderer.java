package com.enplug.photolist.view.list;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.interfaces.FontType;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.model.social.instagram.InstagramItem;
import com.enplug.sdk.view.list.ContentItem;
import com.enplug.sdk.view.list.ContentItemAlignment;
import com.enplug.sdk.view.list.IContentRenderer;
import com.enplug.sdk.view.list.IDrawable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by: berickson926
 * Date: 1/26/15
 * Time: 1:39 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class ListContentRenderer implements IContentRenderer<InstagramItem>
{
    private static final String ELLIPSIS = "...";

    private final Layout _layout;

    private ContentItemAlignment _alignment;

    private BitmapFont _messageFont;

    public ListContentRenderer(IFontGenerator fontGenerator, Layout layout)
    {
        _layout = layout;

        _messageFont = fontGenerator.generateDefaultFont(_layout._listMessageSize, FontType.BOLD);
    }

    @Override
    public void setContentAlignment(ContentItemAlignment contentItemAlignment)
    {
        _alignment = contentItemAlignment;
    }

    @Override
    public IDrawable renderItem(InstagramItem post)
    {
        String avatarPath = post.getUserImageLocalPath();
        ContentItem avatar = renderAvatar(avatarPath);

        String messsage = post.getCaption().getText();
        ContentItem message = renderMessage(messsage);

        return new ListRow(_layout, avatar, message);
    }

    @Override
    public void dispose()
    {
        if(_messageFont != null)
        {
            _messageFont.dispose();
            _messageFont = null;
        }
    }

    private ContentItem renderAvatar(String avatarPath)
    {
        FileHandle avatarFile = Gdx.files.external(avatarPath);
        Texture avatarTex = new Texture(avatarFile, true);
        avatarTex.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);

        Sprite avatarSprite = new Sprite(avatarTex);

        avatarSprite.setSize(_layout._rowAvatarSize, _layout._rowAvatarSize);

        return new ContentItem(avatarSprite, _alignment);
    }

    private ContentItem renderMessage(String message)
    {
        BitmapFontCache messageCache = new BitmapFontCache(_messageFont, true);
        messageCache.setColor(Color.WHITE);

        String shortenedText = StringUtils.substring(message, 0, _layout._listMessageLength);

        if(message.length() > _layout._listMessageLength)
        {
            shortenedText += ELLIPSIS;
        }

        messageCache.setText(shortenedText, 0.0f, 0.0f);

        return new ContentItem(messageCache, _alignment);
    }
}
