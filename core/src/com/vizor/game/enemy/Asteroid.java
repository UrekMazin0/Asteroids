package com.vizor.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vizor.game.shared.Object2D;

public class Asteroid extends Object2D {
    int texture_width;
    int texture_height;

    Vector2 direction;

    final float MAX_VELOCITY = 250f;
    final float ACCELERATION = 1f;

    final float ACCELERATION_TIME = 1f;
    float current_acce_time = 0;
    float velocity;
    public Asteroid(Vector2 start_pos){
        texture = new Texture(Gdx.files.internal("meteor/medium_med.png"));
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();

        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        center = start_pos;

        direction = new Vector2(Vector2.Y)
                .rotateDeg(MathUtils.random(0, 360))
                .nor();

        velocity = MathUtils.random(50f, 170f);
    }

    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;

        current_acce_time += dt;
        if(current_acce_time >= ACCELERATION_TIME){
            velocity += ACCELERATION;
            current_acce_time = 0;
        }
    }

    public void render(SpriteBatch batch){
//        sprite.setRotation(direction.angleDeg(Vector2.Y));
        sprite.setScale(OBJECT_SCALE);
        sprite.setPosition( center.x - (float)texture_width /2,
                            center.y - (float)texture_height/2);
        sprite.draw(batch);
    }

    public void dispose(){
        texture.dispose();;
    }
}
