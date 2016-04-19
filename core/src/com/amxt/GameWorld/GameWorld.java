package com.amxt.GameWorld;

import com.amxt.Buttons.ButtonHandler;
import com.amxt.GameObjects.Ball;
import com.amxt.GameObjects.Block;
import com.amxt.GameObjects.ScrollHandler;
import com.amxt.GameObjects.SpawnHandler;
import com.amxt.GameObjects.XScrollHandler;
import com.amxt.LSHelpers.AssetLoader;
import com.amxt.LSHelpers.InputHandler;
import com.amxt.LSHelpers.MenuInputHandler;
import com.badlogic.gdx.Gdx;



/**
 * Created by amit on 23/02/16.
 */
public class GameWorld
{
    private int gameWidth, gameHeight;

    private Ball ball;
    private SpawnHandler spawn;
    private ScrollHandler scroll;
    private XScrollHandler xScroll;

    private int score, tries;

    //used to change gamestate to determine which methods will be called
    private GameState currentState;
    public enum GameState
    {
        READY,       //before game has started
        RUNNING,       //while game is running
        GAMEOVER        //upon collision
    }

    public GameWorld(int gameWidth, int gameHeight)
    {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        //create game objects
        ball = new Ball(38, gameHeight- gameHeight/5, 9);    //bubble/character controlled object
        spawn = new SpawnHandler(gameWidth, gameHeight);    //handles drops/enemies
        scroll = new ScrollHandler(gameWidth, gameHeight);   //handles scrolling y- direction objects
        xScroll = new XScrollHandler(gameWidth,gameHeight);  //handles scrolling x- direction objects

        currentState = GameState.READY;

        //set/play background music
        AssetLoader.music.setLooping(true);
        AssetLoader.music.setVolume(0.8f);
       // AssetLoader.music.play();
    }

    //updates depending on the gamestate
    public void update(float delta)
    {
        switch(currentState)
        {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            case GAMEOVER:
                updateGameOver(delta);
                break;

            default:
                updateRunning(delta);
        }
    }

    public void updateReady(float delta){xScroll.update(delta);}

    public void updateRunning(float delta)
    {
        if(spawn.collides(ball))
        {
            AssetLoader.dead.play();
            AssetLoader.rain.stop();

            spawn.stop();
            scroll.stop();
            ball.die();
            currentState = GameState.GAMEOVER;

            tries = AssetLoader.getTries();  //number of attempts made at the game
            tries +=1;
            AssetLoader.setTries(tries);   //updates total tries
        }
        else
        {
            spawn.update(delta);
            scroll.update(delta);
            xScroll.update(delta);
            ball.update(delta);
        }
    }



    public void updateGameOver(float delta)
    {
        score = spawn.getScore();  //gets current score

        if(score > AssetLoader.getHighScore())  //checks if there is a new high score
        {
            AssetLoader.setHighScore(score);
        }
    }


    public void start()
    {
        currentState = GameState.RUNNING;
        AssetLoader.rain.setLooping(true);
        AssetLoader.rain.setVolume(0.5f);
        //AssetLoader.rain.play();
    }

    public void restart()   //resets variables upon restarting game
    {
        currentState = GameState.READY;

        ball.onRestart();
        scroll.onRestart();
        spawn.onRestart();
        ((InputHandler) Gdx.input.getInputProcessor()).getPlayButton().resetButton();
    }

    public int getScore()  //gets current score
    {
        score = spawn.getScore();
        return score;
    }

    public Boolean isReady(){return currentState == GameState.READY;}
    public Boolean isGameOver(){return currentState == GameState.GAMEOVER;}
    public Boolean isRunning(){return  currentState == GameState.RUNNING;}

    public Ball getBall(){return ball;}
    public SpawnHandler getSpawn(){return spawn;}
    public ScrollHandler getScroll(){return scroll;}
    public XScrollHandler getXScroll(){return xScroll;}

}
