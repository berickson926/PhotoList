package com.enplug.photolist.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.layout.Layout;
import com.enplug.photolist.view.layout.LayoutFactory;
import com.enplug.sdk.interfaces.IFontGenerator;
import com.enplug.sdk.interfaces.IServiceProvider;

/**
 * Created by berickson926 on 1/20/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class PhotoListScreen implements Screen
{
    private static final String TAG = "[PhotoList]:PhotoListScreen";

    private final FileHandleResolver _assetResolver;
    private final IFontGenerator _fontGenerator;
    private final ILog _log;

    private final World _world;

    private SpriteBatch _batch;

    private ListRenderer _listRenderer;
    private ItemRenderer _itemRenderer;

    private Layout _layout;

    public PhotoListScreen(IServiceProvider serviceProvider, ILog log, World world)
    {
        _assetResolver = serviceProvider.getAssetHandleResolver();
        _fontGenerator = serviceProvider.getFontGenerator();
        _log = log.getSubLog(TAG);
        _world = world;
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        try
        {
            _batch.begin();

            _itemRenderer.draw(_batch);
            _listRenderer.draw(_batch);
        }
        finally
        {
            _batch.end();
        }
    }

    @Override
    public void resize(int width, int height)
    {
        Vector2 currentSize = _layout._screenSize;

        if((currentSize.x != width) || (currentSize.y != height))
        {
            _log.info("Resizing screen.");
            _layout = LayoutFactory.getLayout((float) width, (float) height);

            _itemRenderer.resize(_layout);
            _listRenderer.resize(_layout);

            updateCamera();
        }
    }

    @Override
    public void show()
    {
        _layout = LayoutFactory.getLayout(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        _batch = new SpriteBatch();

        _listRenderer = new ListRenderer(_world, _fontGenerator, _assetResolver, _log);
        _itemRenderer = new ItemRenderer(_world, _fontGenerator, _assetResolver, _log);
    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void pause()
    {
        _log.debug("Pause called.");
    }

    @Override
    public void resume()
    {
        _log.debug("Resume called.");
    }

    @Override
    public void dispose()
    {
        _log.info("Disposing PhotoList.");

        if(_batch != null)
        {
            _batch.dispose();
            _batch = null;
        }

        if(_listRenderer != null)
        {
            _listRenderer.dispose();
            _listRenderer = null;
        }

        if(_itemRenderer != null)
        {
            _itemRenderer.dispose();
            _itemRenderer = null;
        }
    }

    private void updateCamera()
    {
        float width = _layout._screenSize.x;
        float height = _layout._screenSize.y;

        Camera camera = new OrthographicCamera((float) width, (float) height);
        camera.position.set((float) width / 2.0f, (float) height / 2.0f, 0.0f);

        camera.update();

        _batch.setProjectionMatrix(camera.combined);
    }
}
