package com.vizor.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxNativesLoader;

public class GameOverLabel implements Disposable {
    BitmapFont font;
    int height, width;
    GlyphLayout layout;

    String gameOver = "GAME OVER";

    public GameOverLabel(int w, int h){
        width = w;
        height = h;

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setLineHeight(2);
        font.getData().setScale(4f);

        layout = new GlyphLayout(font, gameOver);
    }

    public void render(SpriteBatch batch){
        font.draw(batch, gameOver,
                 (width - layout.width)/2, (height + layout.height)/2);
    }

    public void dispose(){
        font.dispose();
    }
}
