package com.amxt.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

/**
 * Created by amit on 23/02/16.
 */

//class to create the character controlled bubble
public class Ball
{
    private int posX;  //store initial x- position
    private int posY; //store initial y- position   - unnecessary?
    private int radius;
    private int speed;
    private boolean isLeft;     //determine whether Ball is left or right on screen
    float x, y;   //temporary variables used to scale

    private Vector2 position;
    private Vector2 velocity;

    private Circle circle;

    private boolean isAlive;  //unnecessary?

    public Ball(int posX, int posY, int radius)
    {
            isLeft = false;
            this.posX = posX;
            this.posY = posY;
            this.radius = radius;

            position = new Vector2(posX, posY);
            velocity = new Vector2(20, 0);

            speed = 650;

            circle = new Circle();
            isAlive = true;
    }


    public void update(float delta)
    {
        if(isAlive)
        {
            if (isLeft)
            {
                velocity.x = speed;

                if (position.x > 95)
                {
                    velocity.x = 0;
                }

            }
            else if (!isLeft)
            {
                velocity.x = -speed;

                if (position.x < 40)
                {
                    velocity.x = 0;
                }
            }

            x = velocity.x;
            y = velocity.y;
            x = x * delta;   //scale by frame rate to keep things smooth
            y = y * delta;
            position.add(x, y);   //sets new position

            circle.set(position.x + 2, position.y + 2, radius);
        }
    }


    public void onClick(){ if(isAlive) {isLeft = !isLeft;} }

    public void die(){isAlive = false;}  //unnecessary?

    public boolean isAlive(){return isAlive;} //?

    public void onRestart()
    {
        isAlive = true;
        position.x=posX;
    }

    public float getPosX(){return position.x;}

    public float getPosY(){return position.y;}

    public Circle getCircle(){return circle;}

}
