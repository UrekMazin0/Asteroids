package com.vizor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vizor.game.player.Ship;

public class Asteroids extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Ship ship;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// background
		background = new Texture("background/black.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		// ship
		ship = new Ship(new Vector2((float)Gdx.graphics.getWidth()  /2,
									(float)Gdx.graphics.getHeight() /2));
	}

	@Override
	public void render () {
		input_handler();
		update();

		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();

		batch.draw(background,
					0, 0,
					0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		ship.render(batch);

		batch.end();
	}

	public void update(){
		float dt = Gdx.graphics.getDeltaTime();
		ship.update(dt);
	}
	public void LoadTextures()
	{

	}

	public void input_handler(){
		ship.input_handler();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
