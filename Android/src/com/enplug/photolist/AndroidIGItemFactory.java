package com.enplug.photolist;

import com.badlogic.gdx.Gdx;
import com.enplug.sdk.model.social.instagram.*;
import org.joda.time.DateTime;

import java.util.Random;

/**
 * Created by: berickson926
 * Date: 1/23/15
 * Time: 4:40 PM
 * Copyright (c) 2012 Enplug, Inc. All rights reserved.
 */
public class AndroidIGItemFactory
{
    private static final long UNIX_TIMESTAMP_CONV = 1000L;
    private static final int POST_DELAY = 50;

    public InstagramItem createNewItem()
    {
        InstagramItem newItem = new InstagramItem();

        newItem.setCreatedTimeTicks((new DateTime()).getMillis() / UNIX_TIMESTAMP_CONV);
        newItem.setCreatedTime(new DateTime().minusMillis(POST_DELAY));

        newItem.setId(Integer.toString(0));
        newItem.setSocialItemId(Integer.toString(0));

        InstagramUser newUser = new InstagramUser();
        newUser.setUserName(generateUsername());

        InstagramCaption newCaption = new InstagramCaption();
        newCaption.setText(generatePostMessage());
        newItem.setCaption(newCaption);
        newItem.setUser(newUser);

        InstagramImage image = new InstagramImage();
        image.setUrl("http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/e15/10004133_1401630066808516_826278056_n.jpg");
        InstagramImages images = new InstagramImages();
        images.setStandardResolution(image);
        newItem.setImages(images);

        newItem.getUser().setProfilePicture("http://photos-c.ak.instagram.com/hphotos-ak-xfa1/t51.2885-15/10946228_917728404918170_68127849_n.jpg");

        Gdx.app.log("AndroidIGItemFactory", newItem.toString());

        return newItem;
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
}
