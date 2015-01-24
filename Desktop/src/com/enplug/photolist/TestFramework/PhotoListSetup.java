package com.enplug.photolist.TestFramework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.enplug.apps.test.AppLoader;
import com.enplug.apps.test.TestBase;
import com.enplug.apps.test.TestServiceProviderFactory;
import com.enplug.photolist.PhotoList;
import com.enplug.player.hosting.launcher.AppDefinition;
import com.enplug.player.hosting.launcher.AppInstance;
import com.enplug.player.hosting.launcher.AppLauncher;
import com.enplug.player.hosting.launcher.AppSchedule;
import com.enplug.player.hosting.model.Platform;
import com.enplug.player.model.event.system.ImpressionEvent;
import com.enplug.sdk.hosting.HostedGame;
import com.enplug.sdk.model.social.SocialFeedDefinition;
import com.enplug.sdk.model.social.SocialNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berickson926 on 1/22/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class PhotoListSetup extends TestBase implements InputProcessor
{
    @Override
    public void setup()
    {
        _schedule = new AppSchedule();
        AppDefinition photoListApp = createPhotoListApp();
        _schedule.getApps().add(photoListApp);
        _appLoader = new AppLoader(photoListApp, _bus);

        _providerFactory = new TestServiceProviderFactory(_downloader, _videoPlayer, _social, _notifications, _adProvider, _bus, _log);

        _launcher = new AppLauncher(Platform.Desktop,
                _gameHost,
                _appLoader,
                _providerFactory,
                _callToActionService,
                _eventService,
                _socialService,
                null,
                _localizer,
                _settings,
                null,
                _metricProvider,
                _mapper,
                _bus,
                _log);

        _launcher.initialize(/*is landscape*/ false, /*language*/ "English");

        _bus.register(_launcher);
        _bus.register(_callToActionService);

        _gameHost.run();
    }

    public AppDefinition createPhotoListApp()
    {
        List<SocialFeedDefinition> feeds = new ArrayList<SocialFeedDefinition>();
        SocialFeedDefinition instagramFeed = new SocialFeedDefinition("InstagramFeed", SocialNetwork.Instagram);
        feeds.add(instagramFeed);

        AppDefinition photoListDefinition = createAppDefinition("PhotoListAppId", feeds);

        AppInstance photoListInstance = new AppInstance();
        HostedGame photoListGame = new PhotoList();
        photoListInstance.setApp(photoListGame);
        photoListDefinition.setInstance(photoListInstance);

        return photoListDefinition;
    }

    @Override
    public void registerTestInputProcessor()
    {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int key)
    {
        if (key == Input.Keys.I)
        {
            _log.debug("Debugging Input: Triggering impression event.");
            _launcher.onImpression(new ImpressionEvent());
        }

        return true;
    }

    @Override
    public boolean keyUp(int i)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char c)
    {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4)
    {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2)
    {
        return false;
    }

    @Override
    public boolean scrolled(int i)
    {
        return false;
    }
}
