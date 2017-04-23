package ru.rooh.bsgdx.objects;

import com.badlogic.gdx.math.Vector2;

public class Ship {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // For handling bird rotation
    private int width;
    private int height;
    private Boolean moving = false;

    private float lastVelocity;

    public Ship(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    public void update(float delta) {

        //velocity.add(acceleration.cpy().scl(delta));

        /*if (velocity.y > 200) {
            velocity.y = 200;
        }*/
        if (moving) {
            if (position.y >= 222 && position.y <= 230) {
                velocity.y = -10;
            } else if (position.y <= 170 && position.y >= 160) {
                velocity.y = 10;
            }
            lastVelocity = velocity.y;
        } else velocity.y = 0;
        //if(!moving) velocity.y = 0;
        //Gdx.app.log("Ship", position.y + "");
        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
        //velocity.y = -140;
        /*if(moving) moving = false;
                else moving = true;*/
        moving = !moving;
        if (lastVelocity < 0)
            velocity.y = -10;
        else
            velocity.y = 10;

        //Gdx.app.log("Ship", moving + "");
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

}
