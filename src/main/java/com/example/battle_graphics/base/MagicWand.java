package com.example.battle_graphics.base;

public class MagicWand extends weapon{
    public MagicWand() {
        super("Magic Wand",15, 5.0, (long)600);
    }
    @Override
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

