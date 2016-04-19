package com.amxt.screens;

import com.amxt.LSHelpers.AssetLoader;
import com.amxt.TweenAccessors.SpriteAccessor;
import com.amxt.laneswitch.LaneSwitch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by amit on 03/03/16.
 */

//class to display splash screen on startup
public class SplashScreen implements Screen
{
    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    private LaneSwitch game;
    private OrthographicCamera cam;


    public SplashScreen(LaneSwitch game)
    {
        this.game = game;
       // cam = new OrthographicCamera();
      //  cam.setToOrtho(true, 272, 448);
    }

    @Override
    public void show()
    {
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * 1f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.flip(false,true);
        sprite.setSize((sprite.getWidth()/1.4f) * scale, (sprite.getHeight()/1.2f) * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
                - (sprite.getHeight() / 2f));

        setupTween();

        batcher = new SpriteBatch();
      //  batcher.setProjectionMatrix(cam.combined);
    }

    private void setupTween()  //not my code (mostly)
    {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                game.setScreen(new FrontScreen(game));
                //game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, 0.8f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 0.4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void render(float delta)
    {
        manager.update(delta);

        //sets background colour
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin(); //unnecessary?
        sprite.draw(batcher);
        batcher.end();
    }

    @Override
    public void resize(int width, int height){}

    @Override
    public void hide(){}

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void dispose(){}
}
