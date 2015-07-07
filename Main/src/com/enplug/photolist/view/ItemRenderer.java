package com.enplug.photolist.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.sdk.interfaces.FontType;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.model.social.instagram.InstagramItem;

import java.util.Collection;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class ItemRenderer
{
    private static final String IG_ICON = "instagram.png";

    private final World _world;
    private final IFontGenerator _fontGenerator;
    private final FileHandleResolver _assetResolver;

    private Layout _layout;

    private BitmapFont _usernameFont;
    private BitmapFont _messageFont;

    private BitmapFontCache _userName;
    private BitmapFontCache _message;

    private Sprite _igIcon;
    private Sprite _avatar;
    private Sprite _mainImage;

    private InstagramItem _currentItem;

    private static GlyphLayout _glyphLayout;

    public ItemRenderer(World world, IFontGenerator fontGenerator, FileHandleResolver assetResolver, Layout layout)
    {
        _world = world;
        _fontGenerator = fontGenerator;
        _assetResolver = assetResolver;
        _layout = layout;

        initializeFonts();
        initializeIcon();
        setIcon();
    }

    public void draw(SpriteBatch batch)
    {
        drawAvatar(batch);
        drawImage(batch);
        drawMessage(batch);
        drawUserName(batch);
        drawIcon(batch);
    }

    public void resize(Layout layout)
    {
        _layout = layout;

        disposeFonts();
        initializeFonts();

        setIcon();

        disposeUserImages();

        loadMainImage(_currentItem.getImageLocalPath());
        loadAvatarImage(_currentItem.getUserImageLocalPath());
        loadUserName(_currentItem.getUser().getUserName());
        loadMessage(_currentItem.getCaption().getText());
    }

    public void dispose()
    {
        disposeFonts();
        disposeIGIcon();
        disposeUserImages();
    }

    public void refresh()
    {
        Collection<InstagramItem> items = _world.getPosts().getItems();
        int hightlightedPost = _world.getHighlightedPost();

        if((hightlightedPost >= 0) && (hightlightedPost < items.size()))
        {
            _currentItem = (InstagramItem) items.toArray()[hightlightedPost];

            disposeUserImages();

            loadMainImage(_currentItem.getImageLocalPath());
            loadAvatarImage(_currentItem.getUserImageLocalPath());
            loadUserName(_currentItem.getUser().getUserName());
            loadMessage(_currentItem.getCaption().getText());
        }
    }

    private void initializeFonts()
    {
        _usernameFont = _fontGenerator.generateDefaultFont(_layout._itemUserNameSize, FontType.NORMAL);
        _messageFont = _fontGenerator.generateDefaultFont(_layout._itemMessageSize, FontType.NORMAL);
    }

    private void initializeIcon()
    {
        Texture tex = new Texture(_assetResolver.resolve(IG_ICON), /*use mipmaps*/ true);
        tex.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);

        _igIcon = new Sprite(tex);
    }

    private void setIcon()
    {
        Rectangle iconDim = _layout._iconDim;

        Vector2 centeredPos = centerImage(iconDim);

        _igIcon.setPosition(centeredPos.x, centeredPos.y);
        _igIcon.setSize(iconDim.width, iconDim.height);
    }

    private void disposeFonts()
    {
        if(_messageFont != null)
        {
            _messageFont.dispose();
            _messageFont = null;
        }

        if(_usernameFont != null)
        {
            _usernameFont.dispose();
            _usernameFont = null;
        }
    }

    private void disposeIGIcon()
    {
        if(_igIcon != null)
        {
            _igIcon.getTexture().dispose();
            _igIcon = null;
        }
    }

    private void disposeUserImages()
    {
        if(_avatar != null)
        {
            _avatar.getTexture().dispose();
            _avatar = null;
        }

        if(_mainImage != null)
        {
            _mainImage.getTexture().dispose();
            _mainImage = null;
        }
    }

    private void drawImage(Batch batch)
    {
        if(_mainImage != null)
        {
            _mainImage.draw(batch);
        }
    }

    private void drawUserName(Batch batch)
    {
        if(_userName != null)
        {
            _userName.draw(batch);
        }
    }

    private void drawMessage(Batch batch)
    {
        if(_message != null)
        {
            _message.draw(batch);
        }
    }

    private void drawIcon(Batch batch)
    {
        if(_igIcon != null)
        {
            _igIcon.draw(batch);
        }
    }

    private void drawAvatar(Batch batch)
    {
        if(_avatar != null)
        {
            _avatar.draw(batch);
        }
    }

    private void loadMainImage(String imageFilePath)
    {
        FileHandle file = Gdx.files.external(imageFilePath);
        Texture imageTex = new Texture(file, true);
        imageTex.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);

        _mainImage = new Sprite(imageTex);

        Rectangle dimensions = _layout._itemImageDim;
        Vector2 centeredPos = centerImage(dimensions);
        _mainImage.setPosition(centeredPos.x, centeredPos.y);
        _mainImage.setSize(dimensions.width, dimensions.height);
    }

    private void loadAvatarImage(String avatarFilePath)
    {
        FileHandle file = Gdx.files.external(avatarFilePath);
        Texture avatarTex = new Texture(file, true);
        avatarTex.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);

        _avatar = new Sprite(avatarTex);

        Rectangle dimensions = _layout._itemAvatarDim;
        Vector2 centeredPos = centerImage(dimensions);
        _avatar.setPosition(centeredPos.x, centeredPos.y);
        _avatar.setSize(dimensions.width, dimensions.height);
    }

    private void loadUserName(String userName)
    {
        Vector2 pos = _layout._itemUserNamePos;
        Vector2 centeredPos = getCenteredText(userName, _usernameFont, pos);

        _userName = new BitmapFontCache(_usernameFont, true);
        _userName.setText(userName, centeredPos.x, centeredPos.y);
    }

    private void loadMessage(String message)
    {
        Rectangle messageDim = _layout._itemMessageDim;

        _message = new BitmapFontCache(_messageFont, true);
        _message.setText(message, messageDim.x, messageDim.y, messageDim.width, Align.left, true);
    }

    private Vector2 centerImage(Rectangle dim)
    {
        Vector2 centeredPoint = new Vector2(dim.x, dim.y);

        centeredPoint.x -= (dim.width / 2.0f);
        centeredPoint.y += (dim.height / 2.0f);

        return centeredPoint;
    }

    private static Vector2 getCenteredText(String text, BitmapFont font, Vector2 pos)
    {
        _glyphLayout.setText(font, text);

        float centeredY = pos.y + (_glyphLayout.height / 2.0f);
        float centeredX = pos.x - (_glyphLayout.width / 2.0f);

        return new Vector2(centeredX, centeredY);
    }
}
