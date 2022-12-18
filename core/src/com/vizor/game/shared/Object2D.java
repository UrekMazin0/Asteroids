package com.vizor.game.shared;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Object2D {
    public Vector2 center;
    public Texture texture;
    public Sprite sprite;

    public int texture_width;
    public int texture_height;
    public float OBJECT_SCALE = 1f;

}
