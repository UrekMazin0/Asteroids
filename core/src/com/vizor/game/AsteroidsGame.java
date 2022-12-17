package com.vizor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vizor.game.enemy.AsteroidSpawner;
import com.vizor.game.player.Ship;
import com.vizor.game.shared.BorderHandler;

public class AsteroidsGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Ship ship;
	AsteroidSpawner asteroidSpawner;
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


		asteroidSpawner = new AsteroidSpawner(Gdx.graphics.getWidth(),
											  Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		input_handler();
		update();

		borderHandler.CheckReflection(ship);
		for (int i = 0; i < asteroidSpawner.Length(); i++) {
			borderHandler.CheckReflection(asteroidSpawner.Get(i));
		}

		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();

		batch.draw(background,
					0, 0,
					0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		ship.render(batch);
		asteroidSpawner.render(batch);

		batch.end();

		renderShape();
	}

	public void update(){
		float dt = Gdx.graphics.getDeltaTime();
		ship.update(dt);
		asteroidSpawner.update(dt);
	}

	private void renderShape(){
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		ship.renderCollisionShape(shapeRenderer);
		asteroidSpawner.renderCollisionShape(shapeRenderer);

		shapeRenderer.end();
	}

	public void input_handler(){
		ship.input_handler();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		asteroidSpawner.dispose();
		ship.dispose();
	}
}
