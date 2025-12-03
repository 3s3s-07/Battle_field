package com.example.battle_graphics.base;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Projectile {

    private final double damage;
    private final double speed;
    private double xPosition;
    private double yPosition;
    private final int direction;
    private final Shape projectileShape;

    public Projectile(double x, double y, double damage, double speed, int direction) {
        this.xPosition = x;
        this.yPosition = y;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction;

        this.projectileShape = new Circle(5, Color.RED);
        this.projectileShape.setTranslateX(x);
        this.projectileShape.setTranslateY(y);
    }

    public void updatePosition() {
        this.xPosition += this.speed * this.direction;
        this.projectileShape.setTranslateX(this.xPosition);
    }

    public boolean checkCollision(Shape opponentShape) {
        return projectileShape.getBoundsInParent().intersects(opponentShape.getBoundsInParent());
    }

    // Getters
    public double getDamage() { return damage; }
    public Shape getShape() { return projectileShape; }
    public double getXPosition() { return xPosition; }
    public int getDirection() { return direction; }

}