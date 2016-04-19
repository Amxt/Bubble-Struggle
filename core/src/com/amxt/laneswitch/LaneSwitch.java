package com.amxt.laneswitch;
import com.amxt.LSHelpers.AssetLoader;

import com.amxt.screens.FrontScreen;
import com.amxt.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.amxt.screens.GameScreen;

public class LaneSwitch extends Game  //main
{

	@Override
	public void create()
	{
		AssetLoader.load();    //loads all assets

		setScreen(new SplashScreen(this));    //displays splashscreen upon starting the app
		//setScreen(new FrontScreen(this));
	}

	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}

}
