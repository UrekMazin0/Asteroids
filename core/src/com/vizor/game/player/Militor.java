package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Militor extends Ship{

    final int MAX_HEALTH = 1;

    final float MAX_VELOCITY = 170f;

    float current_gun_cooldown = 5f;
    final float GUN_COOLDOWN = 5f;
    boolean gun_on_cooldown = false;

    public Militor(Vector2 start_pos){
        super(start_pos);

        texture        = new Texture(Gdx.files.internal("ship/militor/ship.png"));
        texture_width  = texture.getWidth();
        texture_height = texture.getHeight();
        sprite         = new Sprite(texture, 0,0,texture_width, texture_height);
        sprite.setScale(OBJECT_SCALE);

        health = MAX_HEALTH;
    }

    @Override
    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;

        collisionShape.setPosition(center);

        Vector2 mouse_on_screen = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        direction = mouse_on_screen.sub(center).nor();

        sprite.setRotation(direction.angleDeg(Vector2.Y));

        velocity = velocity - DECELERATION < 0 ? 0 : velocity - DECELERATION;

        if(projectile != null)
            projectile.update(dt);

        if(gun_on_cooldown){
            current_gun_cooldown -= dt;
            gun_on_cooldown = !(current_gun_cooldown <= 0);
        }
        else {
            current_gun_cooldown = GUN_COOLDOWN;
        }
    }

    @Override
    public void render(SpriteBatch batch){
        super.render(batch);

        if(projectile != null)
            projectile.render(batch);
    }

    @Override
    public void input(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (velocity > MAX_VELOCITY)
                return;
            velocity += ACCELERATION;
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !gun_on_cooldown){
            projectile = new Projectile(new Vector2(center), new Vector2(direction));
            gun_on_cooldown = true;
        }
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
        projectile.dispose();
    }
}
