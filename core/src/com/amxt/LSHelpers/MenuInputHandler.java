package com.amxt.LSHelpers;

import com.amxt.Buttons.ButtonHandler;
import com.amxt.ui.SimpleButton;
import com.badlogic.gdx.InputProcessor;


/**
 * Created by amit on 23/02/16.
 */

//class handles input on FrontScreen
public class MenuInputHandler implements InputProcessor
{

    private float scaleFactorX;
    private float scaleFactorY;

    private ButtonHandler buttonHandler;    //handles all menu buttons


    public MenuInputHandler(float scaleFactorX, float scaleFactorY, ButtonHandler buttonHandler)
    {
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        this.buttonHandler = buttonHandler;
    }

    private int scaleX(int screenX){return (int)(screenX / scaleFactorX);}

    private int scaleY(int screenY){return (int)(screenY / scaleFactorY);}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        return buttonHandler.touchDown(screenX,screenY);  //calls touchDown method for touched button
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return buttonHandler.touchUp(screenX,screenY);    //calls touchUp method for touched button
    }

    @Override
    public boolean keyDown(int keycode){return false;}

    @Override
    public boolean keyUp(int keycode){return false;}

    @Override
    public boolean keyTyped(char character){return false;}

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

    @Override
    public boolean mouseMoved(int screenX, int screenY) {return false;}

    @Override
    public boolean scrolled(int amount) {return false;}

}

