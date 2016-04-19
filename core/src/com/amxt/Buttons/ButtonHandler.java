package com.amxt.Buttons;

import com.amxt.LSHelpers.AssetLoader;
import com.amxt.ui.SimpleButton;


/**
 * Created by amit on 29/03/2016.
 */

//class to handle menu buttons
public class ButtonHandler
{
    private Bubble startBubble, bestBubble, triesBubble, fillBubble1, fillBubble2, fillBubble3, fillBubble4;
    private SimpleButton startButton, bestButton, triesButton, fill1, fill2, fill3, fill4;

    public ButtonHandler()
    {
        //Initialise menu buttons with different values to create illusion of randomness
        startBubble = new Bubble(27, 90, 0.4f,-0.6f, -0.8f, 0.8f, 0.2f, 0.2f, 1, 1);
        bestBubble = new Bubble(10, 30, 0.2f,0.6f, 0.5f, 0.3f, 0.2f, 0.3f, 1, 1);
        triesBubble = new Bubble(80, 50, 0.4f,0.8f, -0.4f, 0.8f, 0.3f, 0.1f, 1, 1);
        fillBubble1 = new Bubble(75, 10, 0.4f,-0.6f, 0.3f, 0.6f, 0.3f, 0.3f, 1, 1);
        fillBubble2 = new Bubble(10, 165, 0.6f,0.4f, -0.9f, 0.3f, 0.4f, 0.2f, 1, 1);
        fillBubble3 = new Bubble(40, 215, 0.4f,-0.8f, 0.6f, 0.7f, 0.1f, 0.4f, 1, 1);
        fillBubble4 = new Bubble(80, 185, 0.8f,0.8f, -0.3f, 0.3f, 0.3f, 0.4f, 1.4f, 1.4f);


        startButton = new SimpleButton(27, 90, 80, 80, AssetLoader.GameButtonPlay, AssetLoader.bubblePopAnimation);
        bestButton = new SimpleButton(10, 30, 50, 50, AssetLoader.best, AssetLoader.bubblePopAnimation);
        triesButton = new SimpleButton(80, 50, 42, 42, AssetLoader.tries, AssetLoader.bubblePopAnimation);
        fill1 = new SimpleButton(75, 10, 28, 28, AssetLoader.fillBubble1, AssetLoader.bubblePopAnimation);
        fill2 = new SimpleButton(10, 165, 34, 34, AssetLoader.fillBubble2, AssetLoader.bubblePopAnimation);
        fill3 = new SimpleButton(40,215, 20, 20, AssetLoader.fillBubble3, AssetLoader.bubblePopAnimation);
        fill4 = new SimpleButton(80, 185, 40, 40, AssetLoader.fillBubble4, AssetLoader.bubblePopAnimation);
    }


    public boolean touchDown(int screenX, int screenY)     //if screen is touched down
    {

        if(startButton.isTouchDown(screenX, screenY)){return true;}
        if(bestButton.isTouchDown(screenX, screenY)){return true;}
        if(triesButton.isTouchDown(screenX, screenY)){return true;}

        if(fill1.isTouchDown(screenX, screenY)){return true;}
        if(fill2.isTouchDown(screenX, screenY)){return true;}
        if(fill3.isTouchDown(screenX, screenY)){return true;}
        if(fill4.isTouchDown(screenX, screenY)){return true;}

        return false;
    }

    public boolean touchUp(int screenX, int screenY)   //upon letting go of the screen
    {
        if (startButton.isTouchUp(screenX, screenY)){return true;}
        if (bestButton.isTouchUp(screenX, screenY)){return true;}
        if (triesButton.isTouchUp(screenX, screenY)){return true;}

        if(fill1.isTouchUp(screenX, screenY)){return true;}
        if(fill2.isTouchUp(screenX, screenY)){return true;}
        if(fill3.isTouchUp(screenX, screenY)){return true;}
        if(fill4.isTouchUp(screenX, screenY)){return true;}

        return false;
    }



    public void update(float delta)  //runs the update methods for each menu button
    {
        startBubble.update(delta);
        startButton.update(startBubble.getPosX(), startBubble.getPosY());

        bestBubble.update(delta);
        bestButton.update(bestBubble.getPosX(), bestBubble.getPosY());

        triesBubble.update(delta);
        triesButton.update(triesBubble.getPosX(), triesBubble.getPosY());

        fillBubble1.update(delta);
        fill1.update(fillBubble1.getPosX(), fillBubble1.getPosY());

        fillBubble2.update(delta);
        fill2.update(fillBubble2.getPosX(), fillBubble2.getPosY());

        fillBubble3.update(delta);
        fill3.update(fillBubble3.getPosX(), fillBubble3.getPosY());

        fillBubble4.update(delta);
        fill4.update(fillBubble4.getPosX(), fillBubble4.getPosY());
    }

    // use list?
    public SimpleButton getStartButton(){return startButton;}
    public SimpleButton getBestButton() {return bestButton;}
    public SimpleButton getTriesButton() {return triesButton;}
    public  SimpleButton getFill1() {return fill1;}
    public  SimpleButton getFill2() {return fill2;}
    public  SimpleButton getFill3() {return fill3;}
    public  SimpleButton getFill4() {return fill4;}
}
