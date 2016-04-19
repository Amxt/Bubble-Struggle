package com.amxt.GameWorld;

import com.amxt.GameObjects.Ball;
import com.amxt.GameObjects.Block;
import com.amxt.GameObjects.Scroller;
import com.amxt.GameObjects.XScroller;
import com.amxt.LSHelpers.AssetLoader;
import com.amxt.LSHelpers.InputHandler;
import com.amxt.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by amit on 23/02/16.
 */

//class handles all of the rendering -messy class
public class GameRenderer
{
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    //used to animate bubble movement
    private float bWidth, bPositionChange, bNewposition, bHeight, bPositionChangeH, bNewpositionH;
    private boolean isMinWidth = true;
    private boolean isMinHeight = false;

    private float runTime;
    private int gameWidth, gameHeight;

    //objects
    private Ball ball;
    private Block BL1, BL2;
    private Scroller BG, BGSecond, BG2, BG2Second, BG3, BG3Second;
    private XScroller BG4, BG4Second, BG5, BG5Second, BG6, BG6Second, BG7, BG7Second;
    private SimpleButton Button;

    //Assets
    private BitmapFont font, shadow;
    private TextureAtlas.AtlasRegion  bg, bgSecond, bgLayer2, bgLayer3 , bgLayer4, bgLayer5 , bgLayer6, bgLayer7;
    private TextureAtlas.AtlasRegion block, rocket, black, highScore, currentScore;
    private Animation bubblePop;

    private String score;

    //unused/experimental
    private Rectangle rect1, rect2;
    private Circle circle;
    private Sprite Ball;


    public GameRenderer(GameWorld world, int gameWidth, int gameHeight)
    {
        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);     //set camera projection

        shapeRenderer = new ShapeRenderer();                //used to render shapes
        shapeRenderer.setProjectionMatrix(cam.combined);  //attach batcher to camera

        batcher = new SpriteBatch();                        //used to render more efficiently
        batcher.setProjectionMatrix(cam.combined);         // Attach batcher to camera

        //variables to handle bubble movement animation
        bWidth = 38;
        bPositionChange = 0;
        bHeight = 42;
        bPositionChangeH = 0;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        initAssets();
        initObjects();

        runTime = 0;
    }

    private void initAssets()  //to load in assets
    {
        block = AssetLoader.block;    //enemy
        rocket = AssetLoader.rocket;  //bubble

        bg = AssetLoader.bg;
        bgSecond = AssetLoader.bg;
        bgLayer2 = AssetLoader.bgLayer2;
        bgLayer3 = AssetLoader.bgLayer3;
        bgLayer4 = AssetLoader.bgLayer4;
        bgLayer5 = AssetLoader.bgLayer5;
        bgLayer6 = AssetLoader.bgLayer6;
        bgLayer7 = AssetLoader.bgLayer7;

        font = AssetLoader.font;
        shadow = AssetLoader.shadow;

        //Ball= AssetLoader.Ball;

        bubblePop = AssetLoader.bubblePopAnimation;  //bubble popping animation
        black = AssetLoader.black;         //used to dull screen at gameover
        highScore = AssetLoader.highScore;
        currentScore = AssetLoader.score;
    }

    private void initObjects()  //loads objects - better way to do this?
    {
        ball = world.getBall();             //bubble
        BL1 = world.getSpawn().getBL1();    //enemy
        BL2 = world.getSpawn().getBL2();

        BG = world.getScroll().getBg();
        BGSecond = world.getScroll().getBgSecond();

        BG2 = world.getScroll().getBg2();
        BG2Second = world.getScroll().getBg2Second();

        BG3 = world.getScroll().getBg3();
        BG3Second = world.getScroll().getBg3Second();

        BG4 = world.getXScroll().getBg4();
        BG4Second = world.getXScroll().getBg4Second();

        BG5 = world.getXScroll().getBg5();
        BG5Second = world.getXScroll().getBg5Second();

        BG6 = world.getXScroll().getBg6();
        BG6Second = world.getXScroll().getBg6Second();

        BG7 = world.getXScroll().getBg7();
        BG7Second = world.getXScroll().getBg7Second();

        //unused/experimental
        circle = ball.getCircle();
        rect1 = BL1.getRect();
        rect2 = BL2.getRect();
    }

    public void render(float runTime)
    {
        /*shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        // shapeRenderer.ellipse(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);

        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(rect1.x, rect1.y, rect1.width, rect1.height);
        shapeRenderer.rect(rect2.x, rect2.y, rect2.width, rect2.height);
        // shapeRenderer.rect(BL2.getPosX(), BL2.getPosY(), BL2.getWidth(), BL2.getHeight());
        shapeRenderer.end();*/

        batcher.begin();

        batcher.enableBlending();   //enables rendering alpha

        batcher.draw(bgLayer7, BG7Second.getPosX(), BG7Second.getPosY() + 25, BG7Second.getWidth(), gameHeight);
        batcher.draw(bgLayer7, BG7.getPosX(), BG7.getPosY() + 25, BG7.getWidth(), gameHeight);

        batcher.draw(bgLayer6, BG6Second.getPosX(), BG6Second.getPosY() + 15, BG6Second.getWidth(), gameHeight);
        batcher.draw(bgLayer6, BG6.getPosX(), BG6.getPosY() +15, BG6.getWidth(), gameHeight);

        batcher.draw(bgLayer3, BG3.getPosX(), BG3.getPosY(), gameWidth, gameHeight);
        batcher.draw(bgLayer3, BG3Second.getPosX(), BG3Second.getPosY(), gameWidth, gameHeight);

        batcher.draw(bgLayer5, BG5Second.getPosX(), BG5Second.getPosY() +5, BG5Second.getWidth(), gameHeight);
        batcher.draw(bgLayer5, BG5.getPosX(), BG5.getPosY() +5, BG5.getWidth(), gameHeight);

        batcher.draw(bgLayer2, BG2.getPosX(), BG2.getPosY(), gameWidth, gameHeight);
        batcher.draw(bgLayer2, BG2Second.getPosX(), BG2Second.getPosY(), gameWidth, gameHeight);

        batcher.draw(bgLayer4, BG4Second.getPosX(), BG4Second.getPosY(), BG4Second.getWidth(), gameHeight);
        batcher.draw(bgLayer4, BG4.getPosX(), BG4.getPosY(), BG4.getWidth(), gameHeight);


        batcher.draw(block, BL1.getPosX(), BL1.getPosY(), BL1.getWidth(), BL1.getHeight());
        batcher.draw(block, BL2.getPosX(), BL2.getPosY(), BL2.getWidth(), BL2.getHeight());
       // batcher.draw(bgLayer4, 0, 0, gameWidth, gameHeight);


        //animates bubble movement
        if(bWidth >40)    //creates virtual boundary x-axis
        {
            isMinWidth = false;
        }
        if(bWidth <38)
        {
            isMinWidth = true;
        }
        if(isMinWidth)     //handles movement x-direction
        {
            bWidth =(bWidth+0.01f);
            bPositionChange = bPositionChange -0.005f;
            bNewposition = ball.getPosX() -19 +bPositionChange;
        };
        if(!isMinWidth)
        {
            bWidth= bWidth-0.01f;
            bPositionChange = bPositionChange+0.005f;
            bNewposition = ball.getPosX() -19 +bPositionChange;
        }


        if(bHeight >40)     //creates virtual boundary y-axis
        {
            isMinHeight = false;

        }
        if(bHeight <38)
        {
            isMinHeight = true;
        }
        if(isMinHeight)      //handles movement y-direction
        {
            bHeight =(bHeight+0.02f);
            bPositionChangeH = bPositionChangeH -0.01f;
            bNewpositionH = ball.getPosY() -19 +bPositionChangeH;
        };
        if(!isMinHeight)
        {
            bHeight= bHeight-0.02f;
            bPositionChangeH = bPositionChangeH+0.01f;
            bNewpositionH = ball.getPosY() -19 +bPositionChangeH;
        }


        if(!world.isGameOver())
        {
            batcher.draw(rocket, bNewposition, bNewpositionH, bWidth, 36);
        }

        score = world.getScore() +"";     //current score

        shadow.draw(batcher, score, 95, 0);   //displays score
        font.draw(batcher, score, 95, 0);


        if(world.isReady())
        {
           // setSize(.25f);
           // shadow.draw(batcher, "Touch me", 24, 80);
           // font.draw(batcher, "Touch me", 24, 80);

            batcher.draw(AssetLoader.touch,0,0, gameWidth, gameHeight );

            this.runTime = 0;  //used to check if bubble pop animation has finished
        }
        if(world.isGameOver())
        {
             if( !bubblePop.isAnimationFinished(this.runTime))
            {
                this.runTime += Gdx.graphics.getDeltaTime();
                batcher.draw(bubblePop.getKeyFrame(this.runTime),bNewposition, bNewpositionH, bWidth, 36);
            }

            if( bubblePop.isAnimationFinished(this.runTime)) //runs after animation has finished
            {
                batcher.draw(black, 0, 0, gameWidth, gameHeight); //dulls screen on game over
                batcher.draw(currentScore, 0, gameHeight / 4 - 30, 80, 60);
                batcher.draw(highScore, gameWidth - 80, gameHeight - gameHeight / 4 - 30, 80, 60);

                String highScore = AssetLoader.getHighScore() + "";  //better way to do this?

                setSize(.20f);  //sets font size
                shadow.draw(batcher, highScore, gameWidth - 41, gameHeight - gameHeight / 4);
                font.draw(batcher, highScore, gameWidth - 41, gameHeight - gameHeight / 4);

                shadow.draw(batcher, score, 45, gameHeight / 4);
                font.draw(batcher, score, 45, gameHeight / 4);
            }
            SimpleButton Button = ((InputHandler) Gdx.input.getInputProcessor())
                    .getPlayButton(); //error if put in initObjects() - why?
            Button.draw(batcher);
        }

        batcher.end();
    }

    public void setSize(float size)          //set size of font
    {
        shadow.getData().setScale(size, -size);
        font.getData().setScale(size, -size);
    }
}

