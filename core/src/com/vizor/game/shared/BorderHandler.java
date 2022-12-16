package com.vizor.game.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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

    public void CheckReflection(Object2D obj) {

        //
        // края карты, с добавлением ~размера текстуры
        // что бы объект перет зеркальным отражением скрылся
        // за краем полностью
        //
        int reflection_bound_right = space_width + (int) (obj.texture.getWidth() / 2);
        int reflection_bound_left = -(int) (obj.texture.getWidth() / 2);
        int reflection_bound_up = space_height + (int) (obj.texture.getHeight() / 2);
        int reflection_bound_down = -(int) (obj.texture.getHeight() / 2);

        if(obj.center.x >= reflection_bound_right)
            obj.center.x = space_width - obj.center.x;

        if(obj.center.y >= reflection_bound_up) {
            System.out.print("before:" + obj.center.y);
            obj.center.y = space_height - obj.center.y;
            System.out.print("  after: " +obj.center.y + "\n");
        }

        if(obj.center.x <= reflection_bound_left)
            obj.center.x = space_width + -obj.center.x;

        if(obj.center.y <= reflection_bound_down)
            obj.center.y = space_height + -obj.center.y;

        // check all statements
//        if( obj.center.x >= reflection_bound_right ||
//            obj.center.y >= reflection_bound_up){
//            System.out.println("X Y UP");
//            obj.center.x = space_width - obj.center.x;
//            obj.center.y = space_height - obj.center.y;
//        }
//
//        if( obj.center.x <= reflection_bound_left ||
//            obj.center.y <= reflection_bound_down){
//            System.out.println("X Y DOWN");
//            obj.center.x = space_width + -(obj.center.x);
//            obj.center.y = space_height + -(obj.center.y);
//        }
    }
}
