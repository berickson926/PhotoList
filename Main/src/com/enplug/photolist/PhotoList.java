package com.enplug.photolist;

import com.badlogic.gdx.Screen;
import com.enplug.common.logging.ILog;
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
    private static final String TAG = "PhotoList";

    // Enplug utility services
    //
    private ILog _log;
    private IServiceProvider _serviceProvider;

    private World _world;

    private Screen _photoListScreen;

    private List<ISocialItemListener> _listeners;

    @Override
    public void initialize(IServiceProvider serviceProvider, List<SocialFeedDefinition> feeds, boolean isLandscape, String language)
    {
        _serviceProvider = serviceProvider;
        _log = _serviceProvider.getLog().getSubLog(TAG);

        _world = new World();

        _photoListScreen = new PhotoListScreen(_serviceProvider, _log, _world);

        initializeSocialItemListeners(feeds);
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
        return 1;
    }

    @Override
    public void dispose()
    {
        if(_listeners != null)
        {
            for(ISocialItemListener listener : _listeners)
            {
                String feedId = ((InstagramListener)listener).getFeedId();
                _serviceProvider.getSocialProvider().removeListener(feedId, listener);
            }

            _listeners = null;
        }
    }

    private void initializeSocialItemListeners(Iterable<SocialFeedDefinition> feeds)
    {
        _listeners = new ArrayList<ISocialItemListener>();

        for(SocialFeedDefinition feed : feeds)
        {
            ISocialItemListener listener = new InstagramListener(feed.getId(), _world, _log, _serviceProvider.getAppStatusListener());
            _listeners.add(listener);
            _serviceProvider.getSocialProvider().addListener(feed.getId(), listener);
        }
    }
}
