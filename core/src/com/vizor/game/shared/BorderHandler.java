package com.vizor.game.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BorderHandler {
    public int space_width;
    public int space_height;

    final float double_teleport_fix = 5;

    Rectangle screen;

    public BorderHandler(int width, int height){
        space_width  = width;
        space_height = height;
        screen = new Rectangle(0,0,(float)width, (float)height);
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

        if(obj.center.x > reflection_bound_right) {
            obj.center.x = space_width - obj.center.x + double_teleport_fix;
            obj.center.y += 50;
            return;
        }
        if(obj.center.x < reflection_bound_left) {
            obj.center.x = space_width + -obj.center.x - double_teleport_fix;
            obj.center.y -= 50;
            return;
        }

        if(obj.center.y > reflection_bound_up) {
            obj.center.y = space_height - obj.center.y + double_teleport_fix;
            obj.center.x += 50;
            return;
        }

        if(obj.center.y < reflection_bound_down) {
            obj.center.y = space_height + -obj.center.y - double_teleport_fix;
            obj.center.x -= 50;
            return;
        }

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
