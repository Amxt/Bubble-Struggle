package com.amxt.LSHelpers;

import com.amxt.GameObjects.Ball;
import com.amxt.GameWorld.GameWorld;
import com.amxt.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by amit on 23/02/16.
 */



//class handles input on GameScreen
public class InputHandler implements InputProcessor
{

    private SimpleButton playButton;
    private float scaleFactorX, scaleFactorY;

    private GameWorld world;
    private Ball ball;
    private Sound move;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY)
    {
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.world = world;
        ball = world.getBall();
        move= AssetLoader.move;
        playButton = new SimpleButton(42, 92, 50, 50, AssetLoader.PlayButtonUp, AssetLoader.PlayButtonDown);
    }

    private int scaleX(int screenX){return (int) (screenX / scaleFactorX);}

    private int scaleY(int screenY){return (int) (screenY / scaleFactorY);}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if(world.isRunning())
        {
            ball.onClick();      //moves bubble on touch
          //move.play();
            return true;
        }

        else if(world.isReady())
        {
            world.start();         //starts game
        }

        else if(world.isGameOver())
        {
            playButton.isTouchDown(screenX, screenY);    //changes button asset on touch
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(world.isGameOver())
        {
            if (playButton.isTouchUp(screenX, screenY))
            {
                world.restart();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode){return false;}

    @Override
    public boolean keyUp(int keycode){return false;}

    @Override
    public boolean keyTyped(char character){return false;}

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){return false;}

    @Override
    public boolean mouseMoved(int screenX, int screenY){return false;}

    @Override
    public boolean scrolled(int amount){return false;}

    public SimpleButton getPlayButton(){return playButton;}
}
