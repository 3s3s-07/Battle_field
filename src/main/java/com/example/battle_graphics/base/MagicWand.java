package com.example.battle_graphics.base;

public class MagicWand extends Weapon {
    public MagicWand() {
        super("Magic Wand",15, 5.0, (long)600);
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

