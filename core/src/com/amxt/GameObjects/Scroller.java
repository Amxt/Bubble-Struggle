package com.amxt.GameObjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by amit on 01/03/16.
 */

//class creates a accelerating scrolling object (y-direction)
public class Scroller
{
    private int height,width, posX, posY,  maxSpeed;
    private float x, y, speed, accel;
    private boolean isScrolled;
    private Vector2 position,  velocity, acceleration;


    public Scroller(int posX, int posY, int width, int height, float speed, float accel, int maxSpeed)
    {
        this.posX = posX;
        this.posY = posY;
        this.width =  width;
        this.height = height;
        this.speed = speed;
        this.accel = accel;     //initial acceleration
        this.maxSpeed = maxSpeed;

        isScrolled = false;     //whether object has scrolled off screen or not

        position = new Vector2(posX, posY);
        velocity = new Vector2(0, speed);
        acceleration = new Vector2(0, accel);

    }

    public void update(float delta)
    {
        // velocity.add(acceleration.cpy().scl(delta));  bad- creates new object each loop
        x = acceleration.x;
        y = acceleration.y;
        x = x * delta;           //scales by frame rate to keep things smooth
        y = y * delta;
        velocity.add(x , y);

        if(velocity.y > maxSpeed)     //prevents velocity exceeding maxspeed
        {
            velocity.y = maxSpeed;
        }

        //position.add(velocity.cpy().scl(delta));
        x = velocity.x;
        y = velocity.y;
        x = x * delta;
        y = y * delta;
        position.add(x, y);

        if(position.y > height)
        {
            isScrolled = true;
        }
    }


    public void reset(int posY)
    {
        position.y = posY ;
        isScrolled = false;
    }

    public void stop()
    {
        velocity.y = 0;
        acceleration.y = 0;
    }

    public void restart(int posX, int posY)
    {
        position.x = posX;
        position.y = posY;
        velocity.y = speed;
        acceleration.y = accel;
    }

    public boolean isScrolled(){return isScrolled;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public float getPosX(){return position.x;}
    public float getPosY(){return position.y;}
}
