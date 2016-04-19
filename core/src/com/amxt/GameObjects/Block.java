package com.amxt.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Ellipse;


/**
 * Created by amit on 23/02/16.
 */

//class to create droplets/enemies
public class Block
{
    int posX;
    int posY;
    int width;
    int height;
    int speed;
    float x , y;   //temporary variables used for scaling
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    boolean isScrolled;    //to determine if object has scrolled off screen

    private Rectangle rect;

    private int gameWidth;
    private int gameHeight;



    public Block(int posX, int posY, int width, int height, int speed, int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.posX = posX;                //initial position x
        this.posY = posY;               //initial position y
        this.width = width;             //object width
        this.height = height;           //object height
        this.speed = speed;
        position = new Vector2(posX, posY);
        velocity = new Vector2(0, speed);
        acceleration = new Vector2(0, 7);
        isScrolled = false;

        rect = new Rectangle(posX, posY, width, height);


    }


    public void update(float delta)
    {
        //velocity.add(acceleration.cpy().scl(delta));   bad - creates object each loop

        x = acceleration.x;
        y = acceleration.y;
        x = x * delta;        //to scale by frame rate to keep things smooth
        y = y * delta;

        velocity.add(x, y);   //sets new velocity

        if(velocity.y >350)
        {
            velocity.y = 350;
            //Gdx.app.log("Block", "MAX SPEED");
        }

        x = velocity.x;
        y = velocity.y;
        x = x * delta;
        y = y * delta;

        position.add(x,y);  //sets new position

        //position.add(velocity.cpy().scl(delta));

        rect.set(position.x, position.y, width,height);

        if(position.y > gameHeight)   //determine if object is still on screen
        {
            isScrolled = true;
        }
    }

    public void reset( int posX)
    {
        isScrolled = false;

        position.y = -(gameHeight/3);
        position.x = posX;

    }

    public boolean isScrolled()
    {
        return isScrolled;
    }

    public boolean collides(Ball ball)  //collision detection
    {

        //more efficient than intersector method
        if(  ( position.y+height ) >  ball.getPosY()-8 )
        {
            if( position.y+10 <  ( ball.getPosY()-8 ) )
            {
                if(
                        !((
                        (ball.getPosX()-12 < ( position.x)+width ) &&
                        (ball.getPosX()+12  < (position.x+width) ) &&
                        (ball.getPosX()-12 < position.x ) &&
                        (ball.getPosX()+12  < position.x )
                        )||
                        (
                        (ball.getPosX()-12 > ( position.x+width )) &&
                        (ball.getPosX()+12  > (position.x+width) ) &&
                        (ball.getPosX()-12 > ( position.x )) &&
                        (ball.getPosX()+12  > position.x )
                        ))
                 ){return true;}
            }

        }

        return false;

        /*
        if(position.y > (ball.getPosY() - 77  ) )  //should be ball.getheight
        {

              return Intersector.overlaps(ball.getCircle(), rect);
        }
        */

    }

    public void stop()  //stop all movement
    {
        velocity.y =0;
        acceleration.y = 0;
    }

    public void restart(int posX, int posY)   // when game is reset
    {
        position.x = posX;
        position.y = posY;

        velocity.y = speed;
        acceleration.y = 7;
    }


    public float getPosX()
    {
        return position.x;
    }

    public float getPosY()
    {
        return position.y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Rectangle getRect()
    {
        return rect;
    }
}
