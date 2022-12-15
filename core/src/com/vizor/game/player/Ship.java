package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import sun.security.krb5.internal.crypto.DesCbcCrcEType;

public class Ship {
    Texture texture;
    int texture_width;
    int texture_height;

    Vector2 current_pos;
    float velocity;
    Vector2 direction;
    final float MAX_VELOCITY = 200f;
    final float ACCELERATION = 20f;
    final float DECELERATION = 2f;

    float angle = 0;
    final float MAX_ANGLE = 10f;

    public Ship(Vector2 start_pos){
        texture        = new Texture(Gdx.files.internal("ship/ship_1.png"));
        texture_width  = texture.getWidth();
        texture_height = texture.getHeight();

        current_pos = start_pos.sub((float)texture_width/2,
                                    (float)texture_height/2);

        direction = Vector2.Y;
        velocity  = 0f;

    }
    public void update(float dt){
        current_pos.x += direction.x * velocity * dt;
        current_pos.y += direction.y * velocity * dt;

        System.out.println("DeltaTime: " + dt);

        direction.rotateDeg(angle);
        angle = 0f;

        velocity = velocity - DECELERATION < 0 ? 0 : velocity - DECELERATION;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, current_pos.x, current_pos.y);
    }

    public void input_handler(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (velocity > MAX_VELOCITY)
                return;
            velocity += ACCELERATION;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            angle = 10f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            angle = -10f;
        }
    }
    public void dispode(){
        texture.dispose();
    }
}
