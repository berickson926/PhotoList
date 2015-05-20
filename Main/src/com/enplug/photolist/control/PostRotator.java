package com.enplug.photolist.control;

import com.badlogic.gdx.Gdx;
import com.enplug.common.logging.ILog;
import com.enplug.photolist.model.World;
import com.enplug.photolist.view.PhotoListScreen;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by berickson926 on 1/27/15
 * Copyright (c) 2014 Enplug, Inc. All rights reserved.
 *
 */
public class PostRotator
{
    private static final long ROTATION_FREQ = 10L;

    private final World _world;
    private final PhotoListScreen _screen;
    private final ILog _log;

    private final ScheduledExecutorService _postRotateScheduler;
    private ScheduledFuture _postRotateTask;

    public PostRotator(World world, PhotoListScreen screen, ILog log)
    {
        _world = world;
        _screen = screen;
        _log = log.getSubLog(this);

        _postRotateScheduler = Executors.newScheduledThreadPool(1);
    }

    public void dispose()
    {
        if(!_postRotateTask.isCancelled())
        {
            _postRotateTask.cancel(/*interrupt if running*/ true);
        }

        _postRotateScheduler.shutdownNow();

        _log.debug("Post rotate task and scheduler disposed.");
    }

    public void pause()
    {
        _log.info("Pausing post rotate task.");
        if(!_postRotateTask.isCancelled())
        {
            _postRotateTask.cancel(/*interrupt if running*/ false);
        }
    }

    public void resume()
    {
        _log.info("Resuming post rotation task cycle.");
        _postRotateTask = _postRotateScheduler.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                Gdx.app.postRunnable(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       rotatePost();
                    }
                });

            }
        }, /*initial delay*/ 0L, ROTATION_FREQ, TimeUnit.SECONDS);
    }

    private void rotatePost()
    {
        _log.debug("Rotating highlighted post.");

        int highlightedPost = selectNextPost();
        _world.setHighlightedPost(highlightedPost);

        _screen.refresh();
    }

    private int selectNextPost()
    {
        int listSize = _world.getPosts().getItems().size();
        int highlight = _world.getHighlightedPost();

        highlight++;

        if(highlight >= listSize)
        {
            highlight = 0;
        }

        return highlight;
    }
}
