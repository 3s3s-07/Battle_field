package com.example.battle_graphics.base;

public class cannon extends weapon {
    public cannon() {
        super("cannon",25, 3.0, (long) 1000);
    }

    @Override
    public Projectile createProjectile(Fighter owner) {
        public Projectile createProjectile (Fighter owner){
            return new Projectile(
                    owner.getFighterShape().getX() + (owner.isFacingRight() ? 40 : -10),
                    owner.getY + owner.getFighterShape.getBoundsInLocal().getHeight() / 2,
                    projectileSpeed,
                    damage,
                    owner.isFacingRight()
            );
        }
    }
