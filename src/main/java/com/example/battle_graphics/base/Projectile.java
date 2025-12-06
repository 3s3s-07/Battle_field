package com.example.battle_graphics.base;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Projectile {

    private double x, y;
    private double speed;
    private int damage;
    private boolean directionRight;
    private boolean active = true;
    private Rectangle shape;
    private Fighter Owner;

    public Projectile(double x, double y, double speed, int damage, boolean directionRight) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.directionRight = directionRight;

        shape = new Rectangle(12, 4, Color.BLACK);
        shape.setX(x);
        shape.setY(y);
    }

    public void update() {
        if (!active) return;
        if (directionRight) {
            x += speed;
        } else {
            x -= speed;
        }
        shape.setX(x);
    }

    public boolean checkCollision(Fighter target) {
        if (!active) return false;

        if (shape.getBoundsInParent().intersects(target.getFighterShape().getBoundsInParent())) {
            target.decreaseHealth(damage);
            deactivate();
            return true;
        }
        return false;
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 800;
    }

    public Rectangle getShape() {
        return shape;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
        shape.setVisible(false);
    }

    public Fighter getOwner() { return Owner;
    }
}

