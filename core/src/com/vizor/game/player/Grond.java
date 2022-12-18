package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Grond extends Ship{

    Texture shield_texture;
    int shield_width;
    int shield_height;
    Sprite shield_sprite;

    final int MAX_HEALTH = 2;

    float shieldRecoveryTimer = 0f;
    final float RECOVERY_TIMER = 5f;

    public Grond(Vector2 start_pos){
        super(start_pos);

        texture        = new Texture(Gdx.files.internal("ship/grond/ship.png"));
        texture_width  = texture.getWidth();
        texture_height = texture.getHeight();
        sprite         = new Sprite(texture, 0,0,texture_width, texture_height);
        sprite.setScale(OBJECT_SCALE);

        shield_texture = new Texture(Gdx.files.internal("ship/grond/shield.png"));
        shield_width   = shield_texture.getWidth();
        shield_height  = shield_texture.getHeight();
        shield_sprite  = new Sprite(shield_texture, 0,0, shield_width, shield_height);
        shield_sprite.setScale(OBJECT_SCALE);

        health = MAX_HEALTH;
    }

    @Override
    public void update(float dt){
        super.update(dt);

        shieldRecoveryTimer +=dt;
        if(shieldRecoveryTimer > RECOVERY_TIMER){
            health = health + 1 > MAX_HEALTH ? health : health + 1;
            shieldRecoveryTimer = 0f;
        }
    }

    @Override
    public void render(SpriteBatch batch){
        super.render(batch);

        // draw shield
        // if we have a lot of health
        if(health == 1)
            return;

        shield_sprite.setRotation(direction.angleDeg(Vector2.Y));
        shield_sprite.setScale(OBJECT_SCALE);
        shield_sprite.setPosition(center.x - shield_width/2,
                                  center.y - shield_height/2);
        shield_sprite.draw(batch);
    }

    @Override
    public void restart(){
        center.x = Gdx.graphics.getWidth()/2;
        center.y = Gdx.graphics.getHeight()/2;

        health = MAX_HEALTH;
    }

    @Override
    public void dispose(){
        texture.dispose();
        shield_texture.dispose();
    }
}
