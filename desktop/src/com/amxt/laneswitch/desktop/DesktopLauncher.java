package com.amxt.laneswitch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amxt.laneswitch.LaneSwitch;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Lane Switch";
		config.width = 450;
		config.height = 800;
		new LwjglApplication(new LaneSwitch(), config);
	}
}
