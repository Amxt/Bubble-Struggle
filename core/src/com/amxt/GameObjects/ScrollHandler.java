package com.amxt.GameObjects;

import com.badlogic.gdx.Gdx;

/**
 * Created by amit on 01/03/16.
 */

//class handles scroll objects (y-direction)
public class ScrollHandler
{
    private Scroller bg, bgSecond, bg2, bg2Second, bg3, bg3Second ;  //for different layers of the background(rain) - bad names
    private int gameWidth, gameHeight;


    public ScrollHandler(int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        bg = new Scroller(0, 0, gameWidth, gameHeight, 0, 0.3f, 20 );
        bgSecond = new Scroller(0, -gameHeight, gameWidth, gameHeight, 0, 0.3f, 20 ); //holds same asset as bg

        bg2 = new Scroller(0, -gameHeight, gameWidth, gameHeight, 40, 8, 200 );
        bg2Second = new Scroller(0, -(2*gameHeight), gameWidth, gameHeight, 40, 8, 200 ); //holds same asset as bg2

        bg3 = new Scroller(0, gameHeight, gameWidth, gameHeight, 42, 6, 160 );
        bg3Second = new Scroller(0, -(2*gameHeight), gameWidth, gameHeight, 42, 6, 160 ); //holds same asset as bg3

        //the second objects are used to make it appear like the asset is infinitely scrolling,
        //upon scrolling offscreen, object is reset so that the asset is always visible on screen
    }

    public void update(float delta)
    {
        bg.update(delta);
        bgSecond.update(delta);

        if(bg.isScrolled())   //objects are reset is scrolled offscreen
        {
            bg.reset((int) (bgSecond.getPosY() - gameHeight));

        }

        else if(bgSecond.isScrolled())
        {
            bgSecond.reset((int) (bg.getPosY() - gameHeight));

        }


        bg2.update(delta);
        bg2Second.update(delta);

        if(bg2.isScrolled())
        {
            bg2.reset((int) (bg2Second.getPosY() - gameHeight));
        }

        else if(bg2Second.isScrolled())
        {
            bg2Second.reset((int) (bg2.getPosY() - gameHeight));

        }



        bg3.update(delta);
        bg3Second.update(delta);

        if(bg3.isScrolled())
        {
            bg3.reset((int)(bg3Second.getPosY() -gameHeight) );
        }

        else if(bg3Second.isScrolled())
        {
            bg3Second.reset((int)(bg3.getPosY() -gameHeight));

        }



    }

    public Scroller getBg(){return bg;}

    public Scroller getBgSecond(){return bgSecond;}

    public Scroller getBg2(){return bg2;}

    public Scroller getBg2Second(){return bg2Second;}

    public Scroller getBg3(){return bg3;}

    public Scroller getBg3Second(){return bg3Second;}

    public void stop()
    {
        bg.stop();
        bgSecond.stop();

        bg2.stop();
        bg2Second.stop();

        bg3.stop();
        bg3Second.stop();
    }

    public void onRestart()
    {
        bg.restart(0,0);
        bgSecond.restart(0, -gameHeight);

        bg2.restart(0,-gameHeight);
        bg2Second.restart(0, -(2*gameHeight));

        bg3.restart(0,-gameHeight);
        bg3Second.restart(0, -(2*gameHeight));
    }

}
