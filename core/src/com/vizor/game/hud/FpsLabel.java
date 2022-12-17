package com.vizor.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class FpsLabel implements Disposable {
    BitmapFont font;
    int fps = 0;

    final float margin = 20;
    public FpsLabel(){
        font = new BitmapFont();
    }

    public void update(float dt){
        fps = Gdx.graphics.getFramesPerSecond();
    }

    public void render(SpriteBatch batch){
        font.draw(batch, "FPS: " + fps, margin, Gdx.graphics.getHeight() - margin);
    }

    public void dispose(){
        font.dispose();
    }
}
