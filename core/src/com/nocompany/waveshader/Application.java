package com.nocompany.waveshader;

import com.badlogic.gdx.*;

public class Application implements ApplicationListener
{

	private Scene scene;

	@Override
	public void create()
	{
		scene = new Scene(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render()
	{        
		scene.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose()
	{
		scene.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
