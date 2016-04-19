package com.amxt.GameObjects;
import java.util.Random;

/**
 * Created by amit on 23/02/16.
 */

//class handles enemies
public class SpawnHandler
{

    private Block BL1, BL2;
    private Random r;
    private boolean ran;
    private int pos;
    private int speed;    //initial velocity
    private int score;

    private int gameWidth;
    private int gameHeight;

    public SpawnHandler(int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        speed = 120;        //y-direction speed (make it a constant?)
        r = new Random();

        //BL1 + BL2 are the two drops/enemies -bad names
        BL1 = new Block(10, -(gameHeight/3),  gameHeight/4, gameHeight/3, speed, gameWidth, gameHeight);
        BL2 = new Block(((gameWidth -gameHeight/3) +10), -(gameHeight),  gameHeight/4,gameHeight/3, speed, gameWidth, gameHeight);

        score = 0;
    }

    public void update(float delta)
    {
        ran = r.nextBoolean();  //to decide whether enemy spawns on the left or right

        if (ran){pos = 10;}
        else
        {
            pos = ((gameWidth -gameHeight/3) +9);
        }

        BL1.update(delta);
        if (BL2.getPosY() > gameHeight/3)   //reset if enemy scrolled offscreen
        {
            if (BL1.isScrolled())
            {
                BL1.reset(pos);
                score++;
            }
        }

        BL2.update(delta);
        if (BL1.getPosY() > (gameHeight/3))  //reset if enemy scrolled off screen
        {
            if (BL2.isScrolled())
            {

                BL2.reset(pos);
                score++;
            }
        }
    }


    public boolean collides(Ball ball)   //collision detection
    {
        return(BL1.collides(ball)||BL2.collides(ball));
    }


    public void stop()
    {
        BL1.stop();
        BL2.stop();
    }

    public void onRestart()
    {
        BL1.restart(10, -(gameHeight/3));
        BL2.restart(gameWidth - (gameHeight / 3) + 9, -(gameHeight));
        score = 0;
    }

    public int getScore(){return score;}
    public Block getBL1(){return BL1;}
    public Block getBL2(){return BL2;}

}
