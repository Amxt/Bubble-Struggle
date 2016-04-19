package com.amxt.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Animation;

import javax.xml.soap.Text;

/**
 * Created by amit on 03/03/16.
 */

//class used to create button objects
public class SimpleButton
{
    private float runTime = 0;

    private float x, y, width, height;

    private TextureRegion buttonUp, buttonDown;

    private Rectangle bounds;

    protected boolean isPressed = false;

    private Animation pop;

    //constructor for non-animated buttons
    public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);
    }

    //constructor for Bubble buttons that pop on touch
    public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, Animation pop)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.pop = pop;

        bounds = new Rectangle(x, y, width, height);
    }


    private boolean isClicked(int screenX, int screenY){return bounds.contains(screenX,screenY);}

    //render method for non-animated buttons
    public void draw(SpriteBatch batcher)              //called in GameRenderer class
    {
        if(isPressed)
        {
            batcher.draw(buttonDown, x, y, width, height);    //draws pressed button asset
        }
        else
        {
            batcher.draw(buttonUp, x, y, width, height);       //draws un-pressed button asset
        }
    }

    //render method for bubble buttons
    public void draw2(SpriteBatch batcher )              //called in GameRenderer class
    {
        if(isPressed)
        {
           // batcher.draw(buttonDown, x, y, width, height);

            if( !pop.isAnimationFinished(runTime))      //To limit animation to one loop
            {
                runTime += Gdx.graphics.getDeltaTime();         //used to determine if animation has finished
                batcher.draw(pop.getKeyFrame(runTime),x, y, width, height);     //draws animation
            }

          //  if(pop.isAnimationFinished(runTime)) {}
        }
        else
        {
            batcher.draw(buttonUp, x, y, width, height);        //draws un-popped bubble button
        }
    }

    public boolean isTouchDown(int screenX, int screenY)
    {
        if (bounds.contains(screenX, screenY))            //if button is touched
        {
            isPressed = true;
            return true;
        }

       // isPressed = false;
        return false;
    }

    public boolean isTouchUp(int screenX, int screenY)
    {
       // if (bounds.contains(screenX, screenY) && isPressed)
        if(isPressed)           //if button has already been touched
        {
            //isPressed = false;
            return true;
        }

        isPressed = false;
        return false;
    }

    public void resetButton(){isPressed = false;}

    public void update(float x,float y)         //update button position
    {
        this.x = x;
        this.y = y;
        bounds.setX(x);
        bounds.setY(y);
    }

    public Boolean isPressed(){return isPressed;}
}
