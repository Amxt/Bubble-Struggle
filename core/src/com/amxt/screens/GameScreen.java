package com.amxt.screens;

import com.amxt.GameWorld.GameRenderer;
import com.amxt.GameWorld.GameWorld;
import com.amxt.LSHelpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by amit on 23/02/16.
 */

//screen class for main game
public class GameScreen implements Screen
{
    private GameWorld world;            //holds the game objects
    private GameRenderer renderer;      //handles all rendering

    private int gameWidth;
    private int gameHeight;

    public float runTime;


    public GameScreen()
    {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        gameWidth = 136;
        gameHeight = 224;

        world = new GameWorld(gameWidth, gameHeight);
        renderer = new GameRenderer(world, gameWidth, gameHeight);

        //sets input handler
        Gdx.input.setInputProcessor(new InputHandler(world, (screenWidth/gameWidth), (screenHeight/ gameHeight)));
    }

    @Override
    public void render(float delta)
    {
        //sets background colour
        Gdx.gl.glClearColor(120 / 255.0f, 200 / 255.0f, 255 / 255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        runTime += delta;  //total game run time

        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height){}

    @Override
    public void show(){}

    @Override
    public void hide(){}

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void dispose(){}
}
