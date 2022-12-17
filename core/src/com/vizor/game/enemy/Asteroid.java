package com.vizor.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vizor.game.shared.Object2D;

public class Asteroid extends Object2D {
    int texture_width;
    int texture_height;
    public Asteroid(Vector2 start_pos){
        texture = new Texture(Gdx.files.internal("meteor/medium_med.png"));
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();
        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        center = start_pos.sub( (float)texture_width/2,
                                (float)texture_height/2);
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){
        sprite.setPosition(center.x, center.y);
    }

    public void dispose(){
        texture.dispose();;
    }
}
