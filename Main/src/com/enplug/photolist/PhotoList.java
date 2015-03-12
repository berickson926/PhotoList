package com.enplug.photolist;

import com.badlogic.gdx.Screen;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.control.PostRotator;
import com.enplug.photolist.control.listeners.InstagramListener;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.PhotoListScreen;
import com.enplug.sdk.hosting.HostedGame;
import com.enplug.sdk.interfaces.IServiceProvider;
import com.enplug.sdk.interfaces.ISocialItemListener;
import com.enplug.sdk.model.social.SocialFeedDefinition;

import java.util.ArrayList;
import java.util.List;

public class PhotoList extends HostedGame
{
    // Enplug utility services
    //
    private ILog _log;
    private IServiceProvider _serviceProvider;

    private World _world;

    private Screen _photoListScreen;

    private PostRotator _postRotator;

    @Override
    public void initialize(IServiceProvider serviceProvider)
    {
        _serviceProvider = serviceProvider;
        _log = _serviceProvider.getLog().getSubLog(this);

        _world = new World();

        _photoListScreen = new PhotoListScreen(_serviceProvider, _log, _world);

        List<SocialFeedDefinition> feeds = _serviceProvider.getSocialProvider().getFeedDefinitions();
        initializeSocialItemListeners(feeds);

        _postRotator = new PostRotator(_world, (PhotoListScreen) _photoListScreen, _log);
    }

    @Override
    public void resume()
    {
        _postRotator.resume();
    }

    @Override
    public void pause()
    {
        _postRotator.pause();
    }

    @Override
    public void create()
    {
        setScreen(_photoListScreen);
    }

    @Override
    public int getVersion()
    {
        return 1;
    }

    @Override
    public void dispose()
    {
        _postRotator.dispose();
    }

    private void initializeSocialItemListeners(Iterable<SocialFeedDefinition> feeds)
    {
        for(SocialFeedDefinition feed : feeds)
        {
            ISocialItemListener listener = new InstagramListener(feed.getId(), _world, _log, _serviceProvider.getAppStatusListener());
            _serviceProvider.getSocialProvider().connect(listener);
        }
    }
}
