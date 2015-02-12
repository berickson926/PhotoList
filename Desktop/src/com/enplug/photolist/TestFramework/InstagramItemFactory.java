package com.enplug.photolist.testframework;

import com.badlogic.gdx.Gdx;
import com.enplug.sdk.model.social.instagram.*;
import org.joda.time.DateTime;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by: berickson926
 * Date: 1/23/15
 * Time: 4:40 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class InstagramItemFactory
{
    private static final long UNIX_TIMESTAMP_CONV = 1000L;
    private static final int POST_DELAY = 50;
    private static final String IMAGE_DIR = "/photolist_images/";

    private final String _homeBasedPath;

    private int _itemId;

    public InstagramItemFactory()
    {
        _itemId = 0;
        _homeBasedPath = initializeFilepath();
        Gdx.app.log("Home class path: ", _homeBasedPath);
    }

    public InstagramItem createNewItem()
    {
        InstagramItem newItem = new InstagramItem();

        newItem.setCreatedTimeTicks((new DateTime()).getMillis() / UNIX_TIMESTAMP_CONV);
        newItem.setCreatedTime(new DateTime().minusMillis(POST_DELAY));

        newItem.setId(Integer.toString(_itemId));
        newItem.setSocialItemId(Integer.toString(_itemId));
        _itemId++;

        InstagramUser newUser = new InstagramUser();
        newUser.setUserName(generateUsername());
        newItem.setUserImageLocalPath(generateAvatarFilepath());

        InstagramCaption newCaption = new InstagramCaption();
        newCaption.setText(generatePostMessage());
        newItem.setCaption(newCaption);
        newItem.setUser(newUser);

        newItem.setImageLocalPath(generateImageFilepath());

        // leave remote url info empty
        //
        InstagramImage emptyImage = new InstagramImage();
        InstagramImages emptyImages = new InstagramImages();
        emptyImages.setStandardResolution(emptyImage);
        newItem.setImages(emptyImages);

        Gdx.app.log("InstagramItemFactory", newItem.toString());

        return newItem;
    }

    private String initializeFilepath()
    {
        String userHome = System.getProperty("user.home");
        String workingDir = System.getProperty("user.dir");
        return workingDir.substring(userHome.length()+1, workingDir.length());
    }

    private String generateUsername()
    {
        int item = new Random().nextInt(4);

        switch(item)
        {
            case 0:
                return "coffeeFiend9000";

            case 1:
                return "nithinisreddy";

            case 2:
                return "zspitulski";

            case 3:
            default:
                return "areteross";
        }
    }

    private String generatePostMessage()
    {
        int item = new Random().nextInt(4);

        switch(item)
        {
            case 0:
                return "Sunday night #Enplug house dinners as prepared by chef Colin #enplugkitchen";

            case 1:
                return "Powered by #enplug. #santabarbara";

            case 2:
                return "So excited for @enplug 2015!";

            case 3:
            default:
                return "Can't believe I was standing right there on our homecourt, shooting free throws. So much greatness. #lakeshow #enplug";
        }
    }

    private String generateImageFilepath()
    {
        String imagePath = IMAGE_DIR;

        int item = new Random().nextInt(4);

        switch(item)
        {
            case 0:
                imagePath += "basketball.jpg";
                break;

            case 1:
                imagePath += "clouds.jpg";
                break;

            case 2:
                imagePath += "coffee.jpg";
                break;

            case 3:
                imagePath += "salad.jpg";
                break;
        }

        return _homeBasedPath + imagePath;
    }

    private String generateAvatarFilepath()
    {
        String avatarFilepath = IMAGE_DIR;

        int item = new Random().nextInt(4);

        switch(item)
        {
            case 0:
                avatarFilepath += "alex_avatar.jpg";
                break;

            case 1:
                avatarFilepath += "angry_cat_avatar.jpg";
                break;

            case 2:
                avatarFilepath += "bruno_avatar.jpg";
                break;

            case 3:
                avatarFilepath += "zach_avatar.jpg";
                break;
        }

        return _homeBasedPath + avatarFilepath;
    }
}
