package com.example.battle_graphics.base;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class Fighter {

    private String name;
    private int health;
    private int maxHp;
    private double speed;
    protected double xPosition, yPosition;
    protected boolean facingRight;
    protected Shape fighterShape;
    private Color fighterColor;
    private List<Weapon> weapons = new ArrayList<>();
    private int currentWeaponIndex = 0;
    protected long lastShoot;

    public Fighter() {}

    public Fighter(String name, int health, double x, double y, double speed, boolean facingRight, long lastShoot, Color color, int maxHp) {
        this.name = name;
        this.health = health;
        this.xPosition = x;
        this.yPosition = y;
        this.speed = speed;
        this.facingRight = facingRight;
        this.lastShoot = lastShoot;
        this.fighterColor = color;
        this.fighterShape = null;
        this.maxHp = maxHp;
    }

    public abstract void createShape();

    public int getHealth() {
        return health;
    }

    public Shape getFighterShape() {
        return fighterShape;
    }

    protected Color getFighterColor() {
        return fighterColor;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void move(String direction, double minX, double maxX, double minY, double maxY) {
        double newX = xPosition;
        double newY = yPosition;

        switch (direction.toUpperCase()) {
            case "UP" -> newY -= speed;
            case "DOWN" -> newY += speed;
            case "LEFT" -> {
                newX -= speed;
                facingRight = false;
            }
            case "RIGHT" -> {
                newX += speed;
                facingRight = true;
            }
        }

        if (fighterShape == null) {
            if (newX >= minX && newX <= maxX) xPosition = newX;
            if (newY >= minY && newY <= maxY) yPosition = newY;
            return;
        }

        Bounds bounds = fighterShape.getBoundsInLocal();
        if (newX >= minX && (newX + bounds.getWidth() <= maxX)) {
            xPosition = newX;
            fighterShape.setTranslateX(xPosition);
        }
        if (newY >= minY && (newY + bounds.getHeight() <= maxY)) {
            yPosition = newY;
            fighterShape.setTranslateY(yPosition);
        }
    }

    public Projectile shoot() {
        if (fighterShape == null || weapons.isEmpty()) return null;

        Weapon weapon = getCurrentWeapon();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShoot >= weapon.getCooldown()) {
            lastShoot = currentTime;
            double startOffset = facingRight ? fighterShape.getBoundsInLocal().getWidth() : 0;
            return new Projectile(
                    xPosition + startOffset,
                    yPosition + fighterShape.getBoundsInLocal().getHeight() / 2,
                    weapon.getProjectileSpeed(),
                    weapon.getDamage(),
                    facingRight,
                    this
            );
        }
        return null;
    }

    public void decreaseHealth(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public double getX() { return xPosition; }
    public double getY() { return yPosition; }
    public int getMaxHp() { return maxHp; }

    public void addWeapon(Weapon weapon) { weapons.add(weapon); }

    public Weapon getCurrentWeapon() { return weapons.get(currentWeaponIndex); }

    public void switchWeapon() {
        if (weapons.size() > 1) {
            currentWeaponIndex = (currentWeaponIndex + 1) % weapons.size();
            System.out.println(name + " switched to " + getCurrentWeapon().getName());
        }
    }
}
