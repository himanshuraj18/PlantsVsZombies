package sample;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Actor implements Serializable {
    protected int x;
    protected int y;
    protected int speed;
    protected boolean dead;
    private transient ImageView imView;

    public Actor(int x, int y, int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.dead=false;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y += y;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setImview(ImageView imview) {
        this.imView = imview;
    }

    public ImageView getImview() {
        return imView;
    }

    public abstract void act();
}