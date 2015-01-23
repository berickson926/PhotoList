package com.enplug.photolist;

import com.badlogic.gdx.Screen;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.PhotoListScreen;
import com.enplug.sdk.hosting.HostedGame;
import com.enplug.sdk.interfaces.IServiceProvider;
import com.enplug.sdk.model.social.SocialFeedDefinition;

import java.util.List;

public class PhotoList extends HostedGame
{
    private static final String TAG = "PhotoList";

    // Enplug utility services
    //
    private ILog _log;
    private IServiceProvider _serviceProvider;

    private World _world;

    private Screen _photoListScreen;

    @Override
    public void initialize(IServiceProvider serviceProvider, List<SocialFeedDefinition> feeds, boolean isLandscape, String language)
    {
        _serviceProvider = serviceProvider;
        _log = _serviceProvider.getLog();

        _world = new World();

        _photoListScreen = new PhotoListScreen(_serviceProvider, _log, _world);
    }

    @Override
    public void resume()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void create()
    {
        setScreen(_photoListScreen);
    }

    @Override
    public int getVersion()
    {
        return 0;
    }

    @Override
    public void dispose()
    {

    }
}
