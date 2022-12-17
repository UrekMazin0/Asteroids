package com.vizor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vizor.game.player.Ship;
import com.vizor.game.shared.BorderHandler;

import javax.swing.border.Border;

public class Asteroids extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Ship ship;
	BorderHandler borderHandler;
	ShapeRenderer shapeRenderer;
	@Override
	public void create () {
		batch = new SpriteBatch();
		borderHandler = new BorderHandler(Gdx.graphics.getWidth(),
										  Gdx.graphics.getHeight());

		// background
		background = new Texture("background/black.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		// ship
		ship = new Ship(new Vector2((float)Gdx.graphics.getWidth()  /2,
									(float)Gdx.graphics.getHeight() /2));
//		ship = new Ship(new Vector2(0, 0));

		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		input_handler();
		update();
		borderHandler.CheckReflection(ship);

		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();

		batch.draw(background,
					0, 0,
					0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		ship.render(batch);

		batch.end();

		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.circle(ship.center.x, ship.center.y, 50);
		shapeRenderer.rect(ship.center.x - ship.texture.getWidth()/2,
							ship.center.y - ship.texture.getHeight()/2,
							ship.texture.getWidth(), ship.texture.getHeight());
		shapeRenderer.end();
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
