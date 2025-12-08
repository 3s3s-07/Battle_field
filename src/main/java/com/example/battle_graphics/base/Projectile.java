package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public class Projectile {

    private double x, y;
    private double speed;
    private int damage;
    private int directionRight;
    private Shape shape;

    public Projectile(double x, double y, int damage , double speed, int directionRight) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.directionRight = directionRight;

        this.shape = new Rectangle(12, 4, Color.BLACK);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
    }

    public void updatePosition() {
        this.x+= this.speed * this.directionRight;
        this.shape.setTranslateX(this.x);
    }

    public boolean checkCollision(Shape opponentShape) {
        return shape.getBoundsInParent().intersects(opponentShape.getBoundsInParent());
    }


    public Shape getShape() {
        return shape;
    }

    public int getDamage() {
        return damage;
    }

    public int getDirectionRight() {
        return directionRight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}