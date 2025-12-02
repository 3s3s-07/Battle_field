package com.example.battle_graphics.Base;
import javafx.scene.shape.Rectangle;
public abstract class fighter {
    private String name;
    private int health;
    private double speed;
    private double xPosition, yPosition;
    private Weapon currentweapon;
    protected long lastshoot;
    protected boolean facingright;
    protected Rectangle fighterShape; //momekn n3adel el shape bs lazem n8er el import//

    public fighter() {
    }

    public fighter(String name, int health, double x, double y, double speed, Weapon currentweapon, boolean facingright, long lastshoot) {
        this.name = name;
        this.health = health;
        this.xPosition = x;
        this.yPosition = y;
        this.speed = speed;
        this.currentweapon = currentweapon;
        this.lastshoot = lastshoot;
        this.facingright = facingright;
        this.fighterShape = new Rectangle(x, y, 40, 60);//fighter size//
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
        this.health = health;
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
    }

    public void setY(double y) {
        this.yPosition = y;
    }

    public void move(String direction, double arenaWidth, double halfLineX) {
        double newX = xPosition;
        double newY = yPosition;

        if (direction.equalsIgnoreCase("UP")) newY += speed;//3aks
        else if (direction.equalsIgnoreCase("DOWN")) newY -= speed;
        else if (direction.equalsIgnoreCase("LEFT")) newX -= speed;
        else if (direction.equalsIgnoreCase("RIGHT")) newX += speed;

        if (this instanceof Player1) {
            if (newX >= 0 && newX <= halfLineX - fighterShape.getWidth()) {
                xPosition = newX;
            }
        } else if (this instanceof Player2) {
            if (newX >= halfLineX && newX <= arenaWidth - fighterShape.getWidth()) {
                xPosition = newX;
            }
        }

        yPosition = newY;

        fighterShape.setX(xPosition);
        fighterShape.setY(yPosition);
    }

    public Projectile shoot() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastshoot >= currentweapon.getCooldownTime()) {

            // تحديث آخر وقت للإطلاق
            lastshoot = currentTime;

            double projectileStartX = xPosition + fighterShape.getWidth();

            return new Projectile(
                    projectileStartX,
                    yPosition + fighterShape.getHeight() / 2,
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
