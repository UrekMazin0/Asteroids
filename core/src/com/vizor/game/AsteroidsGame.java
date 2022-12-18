package com.vizor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.vizor.game.player.Grond;
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

	Sound loseSound;
	Music backgroundMusic;
	final float backMusicVolume = 0.1f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		borderHandler = new BorderHandler(Gdx.graphics.getWidth(),
										  Gdx.graphics.getHeight());

		// background
		background = new Texture("background/black.png");
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		// ship
		ship = new Grond(new Vector2((float)Gdx.graphics.getWidth()  /2,
									 (float)Gdx.graphics.getHeight() /2));


		asteroidSpawner = new AsteroidSpawner(ship,
											  Gdx.graphics.getWidth(),
											  Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		collisionManager = new CollisionManager(ship, asteroidSpawner);

		fps = new FpsLabel();
		score = new ScoreLabel();
		gameOver = new GameOverLabel(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		_loadSounds();
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

		_borderReflection();

		if(collisionManager.updateCollision()) {
			backgroundMusic.stop();
			loseSound.play(1f);
			_gameEnd = true;
		}
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
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
				restart();
	}

	private void restart(){
		ship.restart();
		asteroidSpawner.restart();
		score.restart();

		backgroundMusic.play();
		_gameEnd = false;
	}

	private void _borderReflection(){
		borderHandler.CheckReflection(ship);

		for (int i = 0; i < asteroidSpawner.Length(); i++) {
			if(asteroidSpawner.asteroidContainer[i] != null)
				borderHandler.CheckReflection(asteroidSpawner.asteroidContainer[i]);
		}
	}

	private void _loadSounds(){
		loseSound = Gdx.audio.newSound(Gdx.files.internal("sound/sfx_lose.ogg"));
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/background.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(backMusicVolume);
		backgroundMusic.play();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		asteroidSpawner.dispose();
		ship.dispose();
		fps.dispose();
		score.dispose();
		loseSound.dispose();
		backgroundMusic.dispose();
	}
}
