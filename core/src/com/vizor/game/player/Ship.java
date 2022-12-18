package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.vizor.game.shared.Object2D;

public abstract class Ship extends Object2D implements Disposable {
    int texture_width;
    int texture_height;

    float velocity;
    Vector2 direction;
    final float MAX_VELOCITY = 400f;
    final float ACCELERATION = 20f;
    final float DECELERATION = 2f;

    float angle = 0;
    final float ROTATE_ANGLE = 3f;

    public Circle collisionShape;
    final float COLLISION_CIRCLE_RADIUS = 20f;

    int health;
    final int MAX_HEALTH = 1;

    public Ship(Vector2 start_pos){
        center = new Vector2(start_pos);

        direction = new Vector2(0,1);
        velocity  = 0f;

        OBJECT_SCALE = 0.5f;
        health = MAX_HEALTH;

        collisionShape = new Circle(center.x, center.y, COLLISION_CIRCLE_RADIUS);
    }

    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;
        collisionShape.setPosition(center);

        direction.rotateDeg(angle);
        sprite.setRotation(direction.angleDeg(Vector2.Y));

        velocity = velocity - DECELERATION < 0 ? 0 : velocity - DECELERATION;
    }

    public void render(SpriteBatch batch){
        sprite.setPosition( center.x - texture_width/2,
                            center.y - texture_height/2);
        sprite.draw(batch);
    }

    public boolean GetHit(){
        boolean isDied = (--health == 0);
        return isDied;
    }

    public void restart(){
        center.x = Gdx.graphics.getWidth()/2;
        center.y = Gdx.graphics.getHeight()/2;

        health = MAX_HEALTH;
    }

    public void renderCollisionShape(ShapeRenderer shapeRenderer){
        shapeRenderer.circle(collisionShape.x, collisionShape.y, 20);
    }

    public void input(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (velocity > MAX_VELOCITY)
                return;
            velocity += ACCELERATION;
        }

        float A = Gdx.input.isKeyPressed(Input.Keys.A) ? 1 : 0;
        float D = Gdx.input.isKeyPressed(Input.Keys.D) ? 1 : 0;
        angle = (A - D) * ROTATE_ANGLE;
    }
    public void dispose(){
        texture.dispose();
    }
}
