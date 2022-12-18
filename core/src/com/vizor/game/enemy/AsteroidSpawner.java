package com.vizor.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.vizor.game.player.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsteroidSpawner implements Disposable {
    public Asteroid[] asteroidContainer;
    final int MAX_ASTEROIDS = 40;

    int space_width;
    int space_height;

    final float RADIUS_AROUND_SHIP = 200;

    Texture big_brown_texture;
    Texture big_med_texture;
    Texture medium_brown_texture;
    Texture medium_med_texture;
    Map<Integer, Texture> textureMap;
    int current_texture_key = 0;

    float asteroidReviveTimer = 0;
    final float REVIVE_TIME = 5f;

    Ship ship_ref;

    public AsteroidSpawner(Ship ship, int width, int height){
        asteroidContainer = new Asteroid[MAX_ASTEROIDS];

        space_width  = width;
        space_height = height;

        ship_ref = ship;

        _loadTextures();
        _asteroidInit();
    }
    
    private void _asteroidInit(){
        if(asteroidContainer == null)
            return;

        for (int i = 0; i < asteroidContainer.length; i++) {
            Vector2 buffer = new Vector2(ship_ref.center);
            asteroidContainer[i] = new Asteroid(buffer.add(_getRandomVector()), _getNextTexture());
        }
    }

    public void update(float dt){
        if(asteroidContainer == null)
            return;

        asteroidReviveTimer += dt;
        if(asteroidReviveTimer > REVIVE_TIME){
            _reviveAsteroid();
            asteroidReviveTimer = 0;
        }

        for (Asteroid item: asteroidContainer) {
            if (item !=null)
                item.update(dt);
        }
    }

    public void render(SpriteBatch batch){
        if(asteroidContainer==null)
            return;

        for (Asteroid item: asteroidContainer) {
            if (item !=null)
                item.render(batch);
        }
    }

    public void renderCollisionShape(ShapeRenderer shapeRenderer){
        for (Asteroid item: asteroidContainer) {
            if(item == null)
                continue;
            item.renderCollisionShape(shapeRenderer);
        }
    }

    private Vector2 _getRandomVector(){
        return new Vector2( MathUtils.random(RADIUS_AROUND_SHIP, space_width),
                            MathUtils.random(RADIUS_AROUND_SHIP, space_height));
    }

    public void restart(){
        for (int i = 0; i < asteroidContainer.length; i++) {
            if(asteroidContainer[i] == null)
                continue;

            Vector2 buffer = new Vector2(space_width/2, space_height/2);
            asteroidContainer[i].center = buffer.add(_getRandomVector());
            asteroidContainer[i].restart();
        }
    }

    private Texture _getNextTexture(){
        current_texture_key = current_texture_key == 4? 0 : current_texture_key;
        return textureMap.get(current_texture_key++);
    }

    private void _loadTextures(){
        medium_brown_texture = new Texture(Gdx.files.internal("meteor/medium_brown.png"));
        medium_med_texture   = new Texture(Gdx.files.internal("meteor/medium_med.png"));
        big_brown_texture    = new Texture(Gdx.files.internal("meteor/big_brown.png"));
        big_med_texture      = new Texture(Gdx.files.internal("meteor/big_med.png"));

        textureMap = new HashMap<Integer, Texture>();
        textureMap.put(0, medium_brown_texture);
        textureMap.put(1, medium_med_texture);
        textureMap.put(2, big_brown_texture);
        textureMap.put(3, big_med_texture);
    }

    public void destroyAsteroid(int key){
        asteroidContainer[key] = null;
    }

    private void _reviveAsteroid(){
        for (int i = 0; i < asteroidContainer.length; i++) {
            if (asteroidContainer[i] == null) {
                asteroidContainer[i] = new Asteroid(_getRandomVector(), _getNextTexture());
                return;
            }
        }
    }

    public int Length(){
        return asteroidContainer.length;
    }

    public void dispose(){
        big_med_texture.dispose();
        big_brown_texture.dispose();
        medium_med_texture.dispose();
        medium_brown_texture.dispose();

        if(asteroidContainer == null)
            return;

        for (Asteroid asteroid : asteroidContainer) {
            if (asteroid != null)
                asteroid.dispose();
        }
    }
}
