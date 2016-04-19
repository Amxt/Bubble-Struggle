package com.amxt.Buttons;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by amit on 29/03/2016.
 */

// Class for menu button animation/movement

public class Bubble
{

    private float mWidth, mHeight,ix, iy,v1, v2, a1, a2, maxX, maxY, tempx, tempy;

    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;


    public Bubble(float x, float y ,float mWidth, float mHeight, float v1, float v2, float a1, float a2, float maxX, float maxY)
    {
        ix = x;       //Initial position x-axis
        iy = y;         //Initial position y-axis

        this.mWidth = mWidth;      //max change in x-position permitted  (difference  between initial x position and current x position)
        this.mHeight = mHeight;     //max change in y-position permitted
        this.v1 = v1;      //Initial x - velocity
        this.v2 = v2;       //Initial y - velocity
        this.a1 = a1;       //Initial x- acceleration
        this.a2 = a2;       //Initial y - acceleration
        this.maxX = maxX;   //max x - velocity
        this.maxY = maxY;   //max y - velocity

        position = new Vector2(ix, iy);
        velocity = new Vector2(v1, v2);
        acceleration = new Vector2(a1, a2);
    }

    public void update(float delta)
    {
        //limits bubbles movement i.e. sets up a virtual box
        if(position.x < ix - mWidth ){ acceleration.x = a1;}
        if(position.x > ix + mWidth ){ acceleration.x = -a1;}

        if(position.y < iy - mHeight){ acceleration.y = a2;}
        if(position.y > iy + mHeight){ acceleration.y = -a2;}


        //logic to control bubbles movements
        tempx = acceleration.x;
        tempy = acceleration.y;
        tempx = tempx * delta;
        tempy = tempy * delta;

       // velocity.add(acceleration.cpy().scl(delta));    bad- creates new object every loop
        velocity.add(tempx, tempy);
        if(velocity.x > maxX){ velocity.x = maxX;}     //limits max x- velocity
        if(velocity.y > maxY){ velocity.y = maxY;}      //limits max y - velocity


        tempx = velocity.x;
        tempy = velocity.y;
        tempx = tempx * delta;      //scales by frame rate to keep things smooth
        tempy = tempy * delta;

        position.add(tempx, tempy);    //sets new position

       // position.add(velocity.cpy().scl(delta));
    }

    public float getPosX(){return position.x;}

    public float getPosY(){return position.y;}
}
