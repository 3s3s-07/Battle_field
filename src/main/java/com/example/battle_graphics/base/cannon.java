package com.example.battle_graphics.base;

public class cannon extends Weapon {
    public cannon() {
        super("cannon", 25, 3.0, (long) 1000);
    }

    @Override
    public Projectile createProjectile(Fighter fighter) {
        return new Projectile(
                fighter.getFighterShape().getLayoutX() + (fighter.getFacingright() ? 40 : -10),
                fighter.getFighterShape().getLayoutY() + fighter.getFighterShape().getBoundsInLocal().getHeight() / 2,
                projectileSpeed,
                damage,
                fighter.getFacingright()
        );
    }
}

