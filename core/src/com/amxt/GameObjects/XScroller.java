package com.amxt.GameObjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by amit on 02/03/16.
 */

//creates constant speed scrolling object (x-direction)
public class XScroller
{
    private int height,width, posX, posY;
    private float x, y, speed;

    private boolean isScrolled;

    private Vector2 position, velocity;


    public XScroller(int posX, int posY, int width, int height, float speed)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = (width + 30);
        this.height = height;
        this.speed = speed;

        isScrolled = false;

        position = new Vector2(posX, posY);
        velocity = new Vector2(speed,0);
    }

    public void update(float delta)
    {
        x = velocity.x;
        y = velocity.y;
        x = x * delta;            //scales by frame rate
        y = y * delta;
        position.add(x, y);

        if(position.x > (width -30))
        {
            isScrolled = true;
        }
    }

    public boolean isScrolled(){return isScrolled;}

    public void reset(XScroller temp)
    {
        position.x = (int) (temp.getPosX()+12 - (width));  //object resets slightly overlapping the object passed in
        isScrolled = false;
    }


    public void stop()
    {
        velocity.x = 0;
    }

    public void restart(int posX)
    {
        position.x = posX;
        velocity.y = speed;
    }

    public float getPosX(){return position.x;}
    public float getPosY(){return position.y;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
}
