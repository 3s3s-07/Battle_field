package com.example.battle_graphics.base;

public abstract class Weapon {

    private final String name;
    private final double damageValue;
    private final double projectileSpeed;
    private final long cooldownTime;

    public Weapon(String name, double damage, double speed, long cooldown) {
        this.name = name;
        this.damageValue = damage;
        this.projectileSpeed = speed;
        this.cooldownTime = cooldown;
    }
    // Getters (Encapsulation)
    public double getDamageValue() { return damageValue; }
    public double getProjectileSpeed() { return projectileSpeed; }
    public long getCooldownTime() { return cooldownTime; }
    public String getName() { return name; }
}