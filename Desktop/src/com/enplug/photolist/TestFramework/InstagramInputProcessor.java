package com.enplug.photolist.TestFramework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.enplug.player.hosting.launcher.AppLauncher;

/**
 * Created by: berickson926
 * Date: 1/23/15
 * Time: 4:33 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class InstagramInputProcessor implements InputProcessor
{
    private final AppLauncher _launcher;
    private final SocialProvider _socialProvider;

    private final InstagramItemFactory _itemFactory;

    public InstagramInputProcessor(AppLauncher launcher, SocialProvider socialProvider)
    {
        _launcher = launcher;
        _socialProvider = socialProvider;

        _itemFactory = new InstagramItemFactory();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if((_launcher != null) && (_socialProvider != null))
        {
            switch(keycode)
            {
                case Input.Keys.I:
                    Gdx.app.log("DEBUGGING INPUT", "Pressed I");
                    _launcher.onImpression(null);
                    break;

                case Input.Keys.A:
                    Gdx.app.log("DEBUGGING INPUT", "Pressed A");
                    Gdx.app.log("Test SocialProvider", "Creating new IG Item for _listener!");
                    _socialProvider.onNewItem(_itemFactory.createNewItem());
                    break;

                case Input.Keys.D:
                    Gdx.app.log("DEBUGGING INPUT", "Pressed D");
                    Gdx.app.log("Test SocialProvider", "Sending an item to delete from app");
                    _socialProvider.onDeleteItem(_itemFactory.createDeleteItem());
                    break;

                case Input.Keys.W:
                    Gdx.app.log("DEBUGGING INPUT", "Pressed W");
                    Gdx.app.log("Test SocialProvider", "Creating item to update a cached item");
                    _socialProvider.onUpdateItem(_itemFactory.createUpdateItem());
                    break;
            }
        }

        return true;
    }


    // Do not care about these but required for interface
    //
    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
