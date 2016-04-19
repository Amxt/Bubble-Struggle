package com.amxt.screens;


import com.amxt.Buttons.ButtonHandler;
import com.amxt.LSHelpers.AssetLoader;

import com.amxt.LSHelpers.MenuInputHandler;
import com.amxt.laneswitch.LaneSwitch;
import com.amxt.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by amit on 03/03/16.
 */

//class to display menu screen (shows after splash screen)
public class FrontScreen implements Screen
{

    private SplashScreen splash;
    public LaneSwitch game;

    private OrthographicCamera cam;
    private SpriteBatch batcher;

    private TextureRegion play, bg, title, tries, best;
    private BitmapFont font, shadow;

    private MenuInputHandler handler;
    private ButtonHandler buttonHandler;
    private SimpleButton startButton, bestButton, triesButton, fill1, fill2, fill3, fill4;

    private String score, totalTries;

    private int gameWidth;
    private int gameHeight;

    public FrontScreen(LaneSwitch game)
    {
        gameWidth = 136;
        gameHeight = 244;

        this.game = game;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);                  //set camera projection

        batcher = new SpriteBatch();                     //used to render more efficiently
        batcher.setProjectionMatrix(cam.combined);       // Attach batcher to camera

        //load assets
        play = AssetLoader.PlayButtonUp;
        bg = AssetLoader.bg;
        title = AssetLoader.title;
        best = AssetLoader.best;
        tries = AssetLoader.tries;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        buttonHandler = new ButtonHandler();

        handler = new MenuInputHandler((screenWidth/gameWidth), (screenHeight/ gameHeight),buttonHandler);
        Gdx.input.setInputProcessor(handler);

        //buttons
        bestButton = buttonHandler.getBestButton();
        startButton = buttonHandler.getStartButton();
        triesButton = buttonHandler.getTriesButton();
        fill1 = buttonHandler.getFill1();
        fill2 = buttonHandler.getFill2();
        fill3 = buttonHandler.getFill3();
        fill4 = buttonHandler.getFill4();

        //font
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;

        score = AssetLoader.getHighScore() + " ";
        totalTries = AssetLoader.getTries() + " ";
    }

    @Override
    public void show()
    {
       // sprite = new Sprite(AssetLoader.block);
       // sprite.flip(false, true);
       // sprite.setColor(0.5f, 1, 1, 0);
       // sprite.setSize(50, 50);
      //  sprite.setPosition(0, 0);
    }

    @Override
    public void render(float delta)
    {
        //set background colour
       // Gdx.gl.glClearColor(50 / 255, 50 / 255, 255 / 255, 1);
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glClearColor(120 / 255.0f, 200 / 255.0f, 255 / 255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buttonHandler.update(delta);


        batcher.begin();

        batcher.draw(bg, 0, 0, 136, 244);

        if(bestButton.isPressed())
        {
            setSize(.20f);
            shadow.draw(batcher, score, 26, 54);
            font.draw(batcher, score, 26, 54);
        }

        if(triesButton.isPressed())
        {
            setSize(.20f);
            shadow.draw(batcher, totalTries, 94, 64);
            font.draw(batcher, totalTries, 94, 64);
        }

        triesButton.draw2(batcher);
        startButton.draw2(batcher);
        bestButton.draw2(batcher);
        fill1.draw2(batcher);
        fill2.draw2(batcher);
        fill3.draw2(batcher);
        fill4.draw2(batcher);

        batcher.end();


        if(startButton.isPressed())  //changes to GameScreen on touch
        {
            game.setScreen(new GameScreen());
            dispose();
        }

        /*
         if (Gdx.input.isTouched())
         {
            game.setScreen(new GameScreen());
            dispose();
         }
        */
    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    public void setSize(float size)    //sets font size
    {
        shadow.getData().setScale(size, -size);
        font.getData().setScale(size, -size);
    }
}




