package com.vizor.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.vizor.game.shared.Object2D;

public class Ship extends Object2D implements Disposable {
    int texture_width;
    int texture_height;

    float velocity;
    Vector2 direction;
    final float MAX_VELOCITY = 400f;
    final float ACCELERATION = 20f;
    final float DECELERATION = 2f;

    float angle = 0;
    final float ROTATE_ANGLE = 3f;

    public Circle collisionShape;
    final float COLLISION_CIRCLE_RADIUS = 20f;

    public Ship(Vector2 start_pos){
        center = new Vector2(start_pos);

        texture        = new Texture(Gdx.files.internal("ship/ship_1.png"));
        texture_width  = texture.getWidth();
        texture_height = texture.getHeight();
        sprite = new Sprite(texture, 0,0,texture_width, texture_height);

        direction = new Vector2(0,1);
        velocity  = 0f;

        OBJECT_SCALE = 0.5f;

        collisionShape = new Circle(center.x, center.y, COLLISION_CIRCLE_RADIUS);
    }
    public void update(float dt){
        center.x += direction.x * velocity * dt;
        center.y += direction.y * velocity * dt;
        collisionShape.setPosition(center);

        direction.rotateDeg(angle);

        velocity = velocity - DECELERATION < 0 ? 0 : velocity - DECELERATION;
    }

    public void render(SpriteBatch batch){
        sprite.setRotation(direction.angleDeg(Vector2.Y));
        sprite.setScale(OBJECT_SCALE);
        sprite.setPosition( center.x - texture_width/2,
                            center.y - texture_height/2);
        sprite.draw(batch);
    }

    public void renderCollisionShape(ShapeRenderer shapeRenderer){
        shapeRenderer.circle(collisionShape.x, collisionShape.y, 20);
    }

    public void input_handler(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (velocity > MAX_VELOCITY)
                return;
            velocity += ACCELERATION;
        }

        float A = Gdx.input.isKeyPressed(Input.Keys.A) ? 1 : 0;
        float D = Gdx.input.isKeyPressed(Input.Keys.D) ? 1 : 0;
        angle = (A - D) * ROTATE_ANGLE;
    }
    public void dispose(){
        texture.dispose();
    }
}
