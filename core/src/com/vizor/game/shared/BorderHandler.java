package com.vizor.game.shared;

import com.badlogic.gdx.math.Vector2;

import javax.swing.border.Border;
import java.util.Vector;

public class BorderHandler {
    public int space_width;
    public int space_height;

    public BorderHandler(int width, int height){
        space_width  = width;
        space_height = height;
    }

    public BorderHandler(Vector2 space){
        space_width  = (int)space.x;
        space_height = (int)space.y;
    }

    public void CheckReflection(Object2D obj){
        
    }
}
