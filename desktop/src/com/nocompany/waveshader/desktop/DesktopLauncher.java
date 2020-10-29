package com.nocompany.waveshader.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nocompany.waveshader.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.samples 			= 2;
		config.width 			= 800;
		config.height 			= 800;
		config.backgroundFPS 	= 60;
		config.vSyncEnabled 	= true;
		config.title			= "water";
		new LwjglApplication(new Application(), config);
	}
}
