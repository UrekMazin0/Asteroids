package com.vizor.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

public class ScoreLabel implements Disposable {
    BitmapFont font;
    public float score = 0;
    public float max_score = 0;
    final float margin_x = 20;
    final float margin_y = 20;

    public ScoreLabel(){
        font = new BitmapFont();
    }

    public void update(float dt){
        score += dt;
    }

    public void render(SpriteBatch batch){
        font.draw(batch, "MAX SCORE: " + max_score, margin_x, Gdx.graphics.getHeight() - margin_y*2);
        font.draw(batch, "SCORE: " + score, margin_x, Gdx.graphics.getHeight() - margin_y*3);
    }

    public void restart(){
        max_score = Math.max(score, max_score);
        score = 0;
    }

    public void dispose(){
        font.dispose();
    }
}
