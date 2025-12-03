package com.example.battle_graphics.base;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.geometry.Bounds;
public abstract class Fighter {

    // 1. الخصائص (Fields) - Encapsulation:
    private double health;
    protected double xPosition;
    protected double yPosition;
    private Weapon currentWeapon;
    private final Color fighterColor;
    private double movementSpeed = 4.0;
    private long lastShotTime = 0;
    private final int playerSide; // 1 for Player 1 (Right direction), -1 for Player 2 (Left direction)

    protected Shape fighterShape;

    // 2. Constructor:
    public Fighter(double initialHealth, double x, double y, Weapon initialWeapon, Color color, int side) {
        this.health = initialHealth;
        this.xPosition = x;
        this.yPosition = y;
        this.currentWeapon = initialWeapon;
        this.fighterColor = color;
        this.playerSide = side;
    }

    // 3. الدوال المجردة (Abstract Methods) - Abstraction:
    public abstract void specialAbility();
    public abstract void createShape();

    // 4. دالة الحركة (Concrete Method):
    public void move(String direction, double minX, double maxX, double minY, double maxY) {
        double newX = xPosition;
        double newY = yPosition;

        if (direction.equalsIgnoreCase("UP")) newY -= movementSpeed;
        else if (direction.equalsIgnoreCase("DOWN")) newY += movementSpeed;
        else if (direction.equalsIgnoreCase("LEFT")) newX -= movementSpeed;
        else if (direction.equalsIgnoreCase("RIGHT")) newX += movementSpeed;

        Bounds bounds = fighterShape.getBoundsInLocal();
        // التحقق من الحدود الأفقية
        if (newX >= minX && (newX + bounds.getWidth() <= maxX)) {
            xPosition = newX;
            fighterShape.setTranslateX(xPosition);
        }
        // التحقق من الحدود العمودية
        if (newY >= minY && (newY + bounds.getHeight() <= maxY)) {
            yPosition = newY;
            fighterShape.setTranslateY(yPosition);
        }
    }

    // 5. دالة إطلاق النار (مع Cooldown):
    public Projectile shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= currentWeapon.getCooldownTime()) {
            lastShotTime = currentTime;

            // تحديد موقع الإطلاق واتجاهه بناءً على playerSide
            double startOffset = (playerSide == 1) ? fighterShape.getBoundsInLocal().getWidth() : 0;
            double projectileStartX = xPosition + startOffset;

            return new Projectile(
                    projectileStartX,
                    yPosition + fighterShape.getBoundsInLocal().getHeight() / 2,
                    currentWeapon.getDamageValue(),
                    currentWeapon.getProjectileSpeed(),
                    playerSide // تمرير الاتجاه
            );
        }
        return null;
    }

    // 6. دالة تقليل الصحة:
    public void decreaseHealth(double damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    // 7. Getters & Setters
    public double getHealth() { return health; }
    public Shape getFighterShape() { return fighterShape; }
    protected Color getFighterColor() { return fighterColor; }
    public void setWeapon(Weapon newWeapon) { this.currentWeapon = newWeapon; }
    protected void setMovementSpeed(double speed) { this.movementSpeed = speed; }
    public int getPlayerSide() { return playerSide; }
}