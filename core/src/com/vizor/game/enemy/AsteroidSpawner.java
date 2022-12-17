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

    final float RADIUS_AROUND_SHIP = 200;

    public AsteroidSpawner(Vector2 ship_start_pos, int width, int height){
        asteroidContainer = new Asteroid[MAX_ASTEROIDS];
        space_width  = width;
        space_height = height;

        _asteroidInit(ship_start_pos);
    }
    
    private void _asteroidInit(Vector2 ship){
        if(asteroidContainer == null)
            return;

        for (int i = 0; i < asteroidContainer.length; i++) {
            Vector2 buffer = new Vector2(ship);
            asteroidContainer[i] = new Asteroid(buffer.add(_getRandomVector()));
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

    private Vector2 _getRandomVector(){
        return new Vector2( MathUtils.random(RADIUS_AROUND_SHIP, space_width),
                            MathUtils.random(RADIUS_AROUND_SHIP, space_height));
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
