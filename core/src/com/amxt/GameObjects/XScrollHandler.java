package com.amxt.GameObjects;

/**
 * Created by amit on 02/03/16.
 */
public class XScrollHandler
{

    private XScroller bg4, bg4Second, bg5, bg5Second, bg6, bg6Second, bg7, bg7Second ; //scrolling clouds -bad names
    private int gameWidth, gameHeight;

    public XScrollHandler(int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        bg4 = new XScroller(0, 0, gameWidth, gameHeight, 4);
        bg4Second = new XScroller(-(gameWidth)-12, 0, gameWidth, gameHeight, 4 ); //holds same asset as bg4

        bg5 = new XScroller(0, 0, gameWidth, gameHeight, 3 );
        bg5Second = new XScroller(-(gameWidth)-12, 0, gameWidth, gameHeight, 3); //holds same asset as bg5

        bg6 = new XScroller(0, 0, gameWidth, gameHeight, 2);
        bg6Second = new XScroller(-(gameWidth)-12, 0, gameWidth, gameHeight, 2); //holds same asset as bg6

        bg7 = new XScroller(0, 0, gameWidth, gameHeight, 1);
        bg7Second = new XScroller(-(gameWidth)-12, 0, gameWidth, gameHeight, 1); //holds same asset as bg7

        //the second objects are used to make it appear like the asset is infinitely scrolling,
        //upon scrolling offscreen, object is reset so that the asset is always visible on screen
    }

    public void update(float delta)
    {
        updater(delta, bg4, bg4Second);
        updater(delta, bg5, bg5Second);
        updater(delta, bg6, bg6Second);
        updater(delta, bg7, bg7Second);
    }


    public void updater(float delta, XScroller temp, XScroller temp2)
    {
        //objects are reset if scrolled off screen
        temp.update(delta);
        temp2.update(delta);

        if(temp.isScrolled())
        {
            temp.reset(temp2);  //to reset behind the other object which is currently on screen
        }

        else if(temp2.isScrolled())
        {
            temp2.reset(temp);

        }
    }


    public void stop()
    {
        bg4.stop();
        bg4Second.stop();

        bg5.stop();
        bg5Second.stop();

        bg6.stop();
        bg6Second.stop();

        bg7.stop();
        bg7Second.stop();
    }

    public void onRestart()
    {
        bg4.restart(0);
        bg4Second.restart(-gameWidth);

        bg5.restart(0);
        bg5Second.restart(-gameWidth);

        bg6.restart(0);
        bg6Second.restart(-gameWidth);

        bg7.restart(0);
        bg7Second.restart(-gameWidth);
    }


    public XScroller getBg4(){return bg4;}

    public XScroller getBg4Second(){return bg4Second;}

    public XScroller getBg5(){return bg5;}

    public XScroller getBg5Second(){return bg5Second;}

    public XScroller getBg6(){return bg6;}

    public XScroller getBg6Second(){return bg6Second;}

    public XScroller getBg7(){return bg7;}

    public XScroller getBg7Second(){return bg7Second;}
}

