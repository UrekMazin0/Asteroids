package com.vizor.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.vizor.game.shared.Object2D;

public class Asteroid extends Object2D implements Disposable {
    int texture_width;
    int texture_height;

    float velocity;
    Vector2 direction;

    final float MAX_VELOCITY = 250f;
    final float ACCELERATION = 2f;

    public Circle collisionShape;
    final float COLLISION_CIRCLE_RADIUS = 20;

    public Asteroid(Vector2 start_pos){
        center = start_pos;

        texture = new Texture(Gdx.files.internal("meteor/medium_med.png"));
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();
        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        direction = new Vector2(Vector2.Y)
                .rotateDeg(MathUtils.random(0, 360))
                .nor();

        velocity = MathUtils.random(150f, 200f);

        collisionShape = new Circle(center.x, center.y, COLLISION_CIRCLE_RADIUS);
    }

    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;


        if(!((velocity + dt * ACCELERATION) < MAX_VELOCITY))
            velocity += dt*ACCELERATION;

        collisionShape.setPosition(center);
    }

    public void render(SpriteBatch batch){
//        sprite.setRotation(direction.angleDeg(Vector2.Y));
        sprite.setScale(OBJECT_SCALE);
        sprite.setPosition( center.x - (float)texture_width /2,
                            center.y - (float)texture_height/2);
        sprite.draw(batch);
    }

    public void renderCollisionShape(ShapeRenderer shapeRenderer){
        shapeRenderer.circle(collisionShape.x, collisionShape.y, 20);
    }

    public void dispose(){
        texture.dispose();
    }
}
