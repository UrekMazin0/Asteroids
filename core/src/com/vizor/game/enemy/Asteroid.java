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
    final float ROTATE_FACTOR = 62.5f;

    public Asteroid(Vector2 start_pos, Texture tex){
        center = start_pos;

        texture = tex;
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();
        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        direction = new Vector2(Vector2.Y)
                        .rotateDeg(MathUtils.random(0, 360))
                        .nor();

        velocity = MathUtils.random(150f, 200f);

        float collision_radius = (float)(texture_height + texture_width)/4;
        collisionShape = new Circle(center.x, center.y, collision_radius);
    }

    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;


        if(!((velocity + dt * ACCELERATION) < MAX_VELOCITY))
            velocity += dt*ACCELERATION;

        sprite.rotate(velocity / ROTATE_FACTOR);
        collisionShape.setPosition(center);
    }

    public void render(SpriteBatch batch){
        sprite.setScale(OBJECT_SCALE);
        sprite.setPosition( center.x - (float)texture_width /2,
                            center.y - (float)texture_height/2);
        sprite.draw(batch);
    }

    public void restart(){
        velocity = MathUtils.random(150f, 200f);
    }

    public void renderCollisionShape(ShapeRenderer shapeRenderer){
        shapeRenderer.circle(collisionShape.x, collisionShape.y, collisionShape.radius);
    }

    public void dispose(){
        texture.dispose();
    }
}
