package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vizor.game.shared.Object2D;

public class Ship extends Object2D {
    Sprite ship_sprite;
    int texture_width;
    int texture_height;

    Vector2 center;
    float velocity;
    Vector2 direction;
    final float MAX_VELOCITY = 200f;
    final float ACCELERATION = 20f;
    final float DECELERATION = 2f;

    float angle = 0;
    final float ROTATE_TIME  = 0.33f;
    final float ROTATE_ANGLE = 1f;

    final float MAX_ANGLE = 10f;

    public Ship(Vector2 start_pos){
        texture        = new Texture(Gdx.files.internal("ship/ship_1.png"));
        texture_width  = texture.getWidth();
        texture_height = texture.getHeight();
        ship_sprite    = new Sprite(texture, 0,0,texture_width, texture_height);

        center = start_pos.sub( (float)texture_width/2,
                                (float)texture_height/2);

        direction = new Vector2(0,1);
        velocity  = 0f;

    }
    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;

        direction.rotateDeg(angle);

        velocity = velocity - DECELERATION < 0 ? 0 : velocity - DECELERATION;
    }

    public void render(SpriteBatch batch){
//        batch.draw( texture,
//                    current_pos.x - texture_width/2, current_pos.y - texture_height/2,
//                    current_pos.x, current_pos.y,
//                    texture_width, texture_height,
//                    1f, 1f,
//                    direction.angleDeg(Vector2.Y),
//                    0, 0,
//                    texture_width, texture_height,
//                    false, false);
        ship_sprite.setPosition(center.x , center.y);
        ship_sprite.setRotation(direction.angleDeg(Vector2.Y));
        ship_sprite.setScale(1f);
        ship_sprite.draw(batch);

    }

    public void input_handler(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (velocity > MAX_VELOCITY)
                return;
            velocity += ACCELERATION;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            angle = ROTATE_ANGLE;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            angle = -ROTATE_ANGLE;
        }
    }
    public void dispode(){
        texture.dispose();
    }
}
