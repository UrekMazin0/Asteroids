package com.vizor.game.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreLabel {
    BitmapFont font;
    public float score = 0;

    final float margin_x = 100;
    final float margin_y = 20;

    public ScoreLabel(){
        font = new BitmapFont();
    }

    public void update(float dt){
        score += dt;
    }

    public void render(SpriteBatch batch){
        font.draw(batch, "SCORE: " + score, margin_x, margin_y);
    }

    public void dispose(){
        font.dispose();
    }
}
