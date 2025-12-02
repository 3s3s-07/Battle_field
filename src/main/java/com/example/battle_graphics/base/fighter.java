package com.example.battle_graphics.base;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
public abstract class fighter {
    private String name;
    private int health;
    private double speed;
    private double xPosition, yPosition;
    private Weapon currentweapon;
    protected long lastshoot;
    protected boolean facingright;
    protected Shape fighterShape;
    private Color fighterColor; //momekn n3adel el shape bs lazem n8er el import//
    public fighter() {
    }

    public fighter(String name, int health, double x, double y, double speed, Weapon currentweapon, boolean facingright, long lastshoot,Color fighterColor) {
        this.name = name;
        this.health = health;
        this.xPosition = x;
        this.yPosition = y;
        this.speed = speed;
        this.currentweapon = currentweapon;
        this.lastshoot = lastshoot;
        this.facingright = facingright;
        this.fighterColor=fighterColor;
        this.fighterShape = null;

    }

    public double getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public double getX() {
        return xPosition;
    }

    public double getY() {
        return yPosition;
    }

    public Weapon getCurrentweapon() {
        return currentweapon;
    }

    public long getLastshoot() {
        return lastshoot;
    }

    public void setHealth(int health) {
        this.health = health;//this.health = Math.max(0, health);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacingright(boolean facingright) {
        this.facingright = facingright;
    }

    public void setCurrentweapon(Weapon currentweapon) {
        this.currentweapon = currentweapon;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setLastshoot(long lastshoot) {
        this.lastshoot = lastshoot;
    }

    public void setX(double x) {
        this.xPosition = x;
        if (fighterShape != null) fighterShape.setLayoutX(x);
    }

    public void setY(double y) {
        this.yPosition = y;
        if (fighterShape != null) fighterShape.setLayoutX(y);
    }
    public abstract void createShape();
    public Shape getFighterShape() { return fighterShape; }
    protected Color getFighterColor() { return fighterColor; }

    public void move(String direction, double minX, double maxX, double minY, double maxY) {
        double newX = xPosition;
        double newY = yPosition;

        if (direction.equalsIgnoreCase("UP")) newY -= speed;
        else if (direction.equalsIgnoreCase("DOWN")) newY += speed;
        else if (direction.equalsIgnoreCase("LEFT")) newX -= speed;
        else if (direction.equalsIgnoreCase("RIGHT")) newX += speed;

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
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastshoot >= currentweapon.getCooldownTime()) {

            // تحديث آخر وقت للإطلاق
            lastshoot = currentTime;

            double projectileStartX = xPosition + fighterShape.getBoundsInLocal().getWidth();
            return new Projectile(
                    projectileStartX,
                    yPosition + fighterShape.getBoundsInLocal().getHeight() / 2,
                    currentweapon.getDamageValue(),
                    currentweapon.getProjectileSpeed()
            );
        }
        return null;
    }

    public void decreaseHealth(double damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }

    }

}
