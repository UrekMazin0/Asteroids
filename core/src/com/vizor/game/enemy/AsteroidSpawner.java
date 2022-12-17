package com.vizor.game.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class AsteroidSpawner implements Disposable {
    Asteroid[] asteroidContainer;
    final int MAX_ASTEROIDS = 30;
    int space_width;
    int space_height;
    public AsteroidSpawner(int width, int height){
        asteroidContainer = new Asteroid[MAX_ASTEROIDS];
        space_width  = width;
        space_height = height;

        _asteroidInit();
    }
    
    private void _asteroidInit(){
        if(asteroidContainer == null)
            return;

        for (int i = 0; i < asteroidContainer.length; i++) {
            asteroidContainer[i] = new Asteroid(_getRandomPosition());
        }
    }

    public void update(float dt){
        if(asteroidContainer == null)
            return;

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
            item.renderCollisionShape(shapeRenderer);
        }
    }

    private Vector2 _getRandomPosition(){
        return new Vector2( MathUtils.random(20f, space_width),
                            MathUtils.random(20f, space_height));
    }

    public Asteroid Get(int key){
        return asteroidContainer[key];
    }

    public int Length(){
        return asteroidContainer.length;
    }

    public void dispose(){
        if(asteroidContainer == null)
            return;

        for (Asteroid asteroid : asteroidContainer) {
            if (asteroid != null)
                asteroid.dispose();
        }
    }
}
