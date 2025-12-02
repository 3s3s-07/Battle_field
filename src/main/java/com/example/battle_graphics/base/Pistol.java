package com.example.battle_graphics.base;

public class Pistol extends Weapon {

    public Pistol() {
        super("Pistol",10,6.0, (long) 400.0);
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
