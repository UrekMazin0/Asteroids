package com.vizor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vizor.game.collision.CollisionManager;
import com.vizor.game.enemy.AsteroidSpawner;
import com.vizor.game.hud.FpsLabel;
import com.vizor.game.hud.GameOverLabel;
import com.vizor.game.hud.ScoreLabel;
import com.vizor.game.player.Ship;
import com.vizor.game.shared.BorderHandler;

public class AsteroidsGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Ship ship;
	AsteroidSpawner asteroidSpawner;
	BorderHandler borderHandler;
	ShapeRenderer shapeRenderer;

	CollisionManager collisionManager;

	FpsLabel fps;
	ScoreLabel score;
	GameOverLabel gameOver;
	private boolean _gameEnd = false;

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


		asteroidSpawner = new AsteroidSpawner(ship.center,
											  Gdx.graphics.getWidth(),
											  Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		collisionManager = new CollisionManager(ship, asteroidSpawner);

		fps = new FpsLabel();
		score = new ScoreLabel();
		gameOver = new GameOverLabel(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		input();
		update();

		//====================
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();


		batch.draw(background,
					0, 0,
					0, 0,
					Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		asteroidSpawner.render(batch);
		ship.render(batch);

		fps.render(batch);
		score.render(batch);

		if(_gameEnd)
			gameOver.render(batch);

		batch.end();
		//====================

		renderShape();
	}

	public void update(){
		if(_gameEnd)
			return;

		float dt = Gdx.graphics.getDeltaTime();
		ship.update(dt);
		asteroidSpawner.update(dt);

		fps.update(dt);
		score.update(dt);

		borderReflection();

		if(collisionManager.updateCollision())
			_gameEnd = true;
	}

	private void renderShape(){
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		ship.renderCollisionShape(shapeRenderer);
		asteroidSpawner.renderCollisionShape(shapeRenderer);

		shapeRenderer.end();
	}

	private void input(){
		ship.input();

		// для того, что бы space нельзя было зажать
		if(_gameEnd)
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				restart();
				_gameEnd = false;
			}
	}

	private void restart(){
		ship.center.x = Gdx.graphics.getWidth()/2;
		ship.center.y = Gdx.graphics.getHeight()/2;

		asteroidSpawner.restart();
		score.restart();
	}

	private void borderReflection(){
		borderHandler.CheckReflection(ship);

		for (int i = 0; i < asteroidSpawner.Length(); i++) {
			borderHandler.CheckReflection(asteroidSpawner.Get(i));
		}
	}
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		asteroidSpawner.dispose();
		ship.dispose();
		fps.dispose();
		score.dispose();
	}
}
