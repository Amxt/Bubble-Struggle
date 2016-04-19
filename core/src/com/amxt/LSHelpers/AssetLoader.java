package com.amxt.LSHelpers;
import com.amxt.GameWorld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by amit on 27/02/16.
 */

//class handles/loads all assets
public class AssetLoader
{
    public static TextureAtlas atlas;

    public static TextureAtlas.AtlasRegion rocket, block, bg, bgLayer2, bgLayer3, bgLayer4, bgLayer5, bgLayer6, bgLayer7 ;
    public static TextureAtlas.AtlasRegion PlayButtonUp, PlayButtonDown, logo, score, highScore, black;
    public static TextureAtlas.AtlasRegion GameButtonPlay, tries, best, title, GameButtonUp, GameButtonDown, pop1, pop2, pop3, pop4;
    public static TextureAtlas.AtlasRegion fillBubble1, fillBubble2, fillBubble3, fillBubble4, touch;
    public static Animation bubblePopAnimation;

    public static BitmapFont font, shadow;
    public static Preferences prefs;          //to save variables
    public static Sound move, dead;
    public static Music rain, music;

    //unused/experimental
    public static Sprite Ball;


    public static void load()    //loads all assets
    {
        //sound/music
        move = Gdx.audio.newSound(Gdx.files.internal("data/Jump12.wav"));
        dead = Gdx.audio.newSound(Gdx.files.internal("data/blob.wav"));
        rain = Gdx.audio.newMusic(Gdx.files.internal("data/Rain.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("data/music.wav"));


        atlas = new TextureAtlas(Gdx.files.internal("data/TextureSheet.txt"));  //holds texture sheet


        rocket = atlas.findRegion("Bubble4");     //character controlled object
        rocket.flip(false, false);               //flips object y-direction

        block = atlas.findRegion("drop");      //enemy object
        block.flip(false, true);


        //gameover screen buttons
        PlayButtonUp = atlas.findRegion("RePlayUp");
        PlayButtonUp.flip(false, true);
        PlayButtonDown = atlas.findRegion("RePlayDown");
        PlayButtonDown.flip(false, true);


        //menu buttons
        GameButtonUp = atlas.findRegion("PlayButtonUp");
        GameButtonUp.flip(false, true);
        GameButtonDown = atlas.findRegion("PlayButtonDown");
        GameButtonDown.flip(false, true);

        GameButtonPlay = atlas.findRegion("PlayBub");
        GameButtonPlay.flip(false,true);

        best = atlas.findRegion("Bubble5");
        best.flip(false, true);

        tries = atlas.findRegion("Bubble6");
        tries.flip(false, true);

        fillBubble1 = atlas.findRegion("Bubble");
        fillBubble1.flip(false, true);
        fillBubble2 = atlas.findRegion("Bubble2");
        fillBubble2.flip(false, true);
        fillBubble3 = atlas.findRegion("Bubble3");
        fillBubble3.flip(false, true);
        fillBubble4 = atlas.findRegion("Bubble4");
        fillBubble4.flip(false, true);


        //splashscreen
        logo = atlas.findRegion("logo");
        logo.flip(false, true);


        //extras/text
        title = atlas.findRegion("Title");
        title.flip(false, true);

        touch = atlas.findRegion("touch");
        touch.flip(false,true);

        black = atlas.findRegion("Black");
        score = atlas.findRegion("Score");
        score.flip(false,true);

        highScore = atlas.findRegion("HighScore");
        highScore.flip(false,true);


        //background
        bg = atlas.findRegion("bg");
        bg.flip(false, true);

        bgLayer2 = atlas.findRegion("bgLayer3");
        bgLayer2.flip(false, true);

        bgLayer3 = atlas.findRegion("bgLayer2");
        bgLayer3.flip(false, true);

        bgLayer4 = atlas.findRegion("bgLayer4");
        bgLayer4.flip(false, true);

        bgLayer5 = atlas.findRegion("bgLayer5");
        bgLayer5.flip(false, true);

        bgLayer6 = atlas.findRegion("bgLayer6");
        bgLayer6.flip(false, true);

        bgLayer7 = atlas.findRegion("bgLayer7");
        bgLayer7.flip(false, true);


        //font
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);


        //creates prefs file if nonexistent
        prefs = Gdx.app.getPreferences("LaneSwitch");
        if(!prefs.contains("highScore"))
        {
            prefs.putInteger("highScore", 0);
        }

        prefs = Gdx.app.getPreferences("LaneSwitch");
        if(!prefs.contains("tries"))
        {
            prefs.putInteger("tries", 0);
        }


        //Animations
        pop1 = atlas.findRegion("Pop3");
        pop2 = atlas.findRegion("Bpop2");
        pop3 = atlas.findRegion("Bpop2");
        pop4 = atlas.findRegion("Bpop1");

        TextureRegion[] bubblePop = {pop1, pop2, pop3, pop4};

        bubblePopAnimation = new Animation(0.02f,bubblePop);

        bubblePopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public static void setHighScore(int val)     //sets high score in prefs file
    {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static void setTries(int val)       //sets total tries in prefs file
    {
        prefs.putInteger("tries", val);
        prefs.flush();
    }

    public static int getHighScore(){return prefs.getInteger("highScore");}
    public static int getTries(){return prefs.getInteger("tries");}

    public static void dispose()
    {
        atlas.dispose();
        font.dispose();
        music.dispose();
        move.dispose();
        dead.dispose();
        rain.dispose();
    }
}



