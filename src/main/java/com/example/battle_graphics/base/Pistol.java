package com.example.battle_graphics.base;

public class Pistol extends weapon {

    public Pistol() {
        super("Pistol",10,6.0, (long) 400.0);
    }

    @Override
    public Projectile createProjectile(Fighter owner) {
        public Projectile createProjectile(Fighter owner) {
            return new Projectile(
                    owner.getFighterShape().getX() + (owner.isFacingRight() ? 40 : -10),
                    owner.getY +owner.getFighterShape.getBoundsInLocal().getHeight() / 2,
                    projectileSpeed,
                    damage,
                    owner.isFacingRight()
            );
        }
    }
