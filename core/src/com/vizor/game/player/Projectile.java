package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.vizor.game.shared.Object2D;

public class Projectile extends Object2D implements Disposable {
    Vector2 direction;
    Vector2 center;

    float velocity = 250f;
    Circle collisionShape;
    final float COLLISION_CIRCLE_RADIUS = 3f;
    public Projectile(Vector2 start_pos, Vector2 _direction){
        texture = new Texture(Gdx.files.internal("ship/militor/laser.png"));
        texture_width = texture.getWidth();
        texture_height = texture.getHeight();
        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        direction = _direction;
        center = start_pos;

        sprite.setRotation(direction.angleDeg(Vector2.Y));

        collisionShape = new Circle(center.x, center.y, COLLISION_CIRCLE_RADIUS);
    }

    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;

        collisionShape.setPosition(center);

        sprite.setRotation(direction.angleDeg(Vector2.Y));
    }

    public void render(SpriteBatch batch){
        sprite.setPosition( center.x - texture_width/2,
                            center.y - texture_height/2);
        sprite.draw(batch);
    }

    public void dispose(){
        texture.dispose();
    }
}
