package com.vizor.game.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.vizor.game.shared.Object2D;

public class Asteroid extends Object2D {
    int texture_width;
    int texture_height;
    public Asteroid(){
        texture = new Texture(Gdx.files.internal("meteor/medium_med.png"));
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();
    }
}
